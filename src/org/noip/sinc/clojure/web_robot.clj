(ns org.noip.sinc.clojure.web-robot)

(import '(java.util.concurrent LinkedBlockingDeque BlockingQueue))
(import '(java.net MalformedURLException URL))
(use '[clojure.string :only (lower-case)])
(require '[net.cgrand.enlive-html :as enlive])

(defn- links-from
  [base-url html]
  (remove nil? (for [link (enlive/select html [:a])]
                 (when-let [href (-> link :attrs :href)]
                   (try
                     (URL. base-url href)
                     (catch MalformedURLException e))))))

(defn- words-from
  [html]
  (let [chunks (-> html
                   (enlive/at [:script] nil)
                   (enlive/select [:body enlive/text-node]))]
    (->> chunks
         (mapcat (partial re-seq #"\w+"))
         (remove (partial re-matches #"\d+"))
         (map lower-case))))

(def url-queue (LinkedBlockingDeque.))
(def crawled-urls (atom #{}))
(def word-freqs (atom #{}))

(declare get-url)

(def agents
  (set (repeatedly 25 #(agent {::t #'get-url :queue url-queue}))))

(declare run process handle-results)

(defn ^::blocking get-url
      [{:keys [^BlockingQueue queue] :as state}]
      (let [url (as-url (.take queue))]
        (try
          (if (@crawled-urls url)
            state
            {:url url
             :content (slurp url)
             ::t #'process})
          (catch Exception e
            ;; пропустить URL, вызвавший ошибку при загрузке содержимого
            state)
          (finally (run *agent*)))))

(defn process
  [{:keys [url content]}]
  (try
    (let [html (enlive/html-resource (java.io.StringReader. content))]
      {::t #'handle-results
       :url url
       :links (links-from url html)
       :words (reduce (fn [m word]
                        (update-in m [word] (fnil inc 0)))
                      {}
                      (words-from html))})
    (finally (run *agent*))))

(defn ^::blocking handle-results
  [{:keys [url links words]}]
  (try
    (swap! crawled-urls conj url)
    (doseq [url links]
      (.put url-queue url))
    (swap! word-freqs (partial merge-with +) words)

    {::t #'get-url :queue url-queue}
    (finally (run *agent*))))

(defn paused? [agent] (::paused (meta agent)))

(defn run
  ([] (doseq [a agents] (run a)))
  ([a]
   (when (agents a)
     (send a (fn [{transition ::t :as state}]
               (when-not (paused? *agent*)
                         (let [dispatch-fn (if (-> transition meta ::blocking)
                                             send-off
                                             send)]
                           (dispatch-fn *agent* transition)))
               state)))))

(defn pause
  ([] (doseq [a agents] (pause a)))
  ([a] (alter-meta! a assoc ::paused true)))

(defn restart
  ([] (doseq [a agents] (restart a)))
  ([a]
   (alter-meta! a dissoc ::paused)
   (run a)))

(defn test-crawler
  "Сбрасывает веб-робота в исходное состояние, добавляет указанный URL
  в url-queue и запускает работа на 60 секунд. Возвращает вектор,
  содержащий количество просмотренных URL и число URL, накопленных
  в процессе обхода, которые еще не были посещены."
  [agent-count starting-url]
  (def agents (set (repeatedly agent-count
                               #(agent {::t #'get-url :queue url-queue}))))
  (.clear url-queue)
  (swap! crawled-urls empty)
  (swap! word-freqs empty)
  (.add url-queue starting-url)
  (run)
  (Thread/sleep 60000)
  (pause)
  [(count @crawled-urls) (count url-queue)])