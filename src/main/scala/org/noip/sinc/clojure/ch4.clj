(ns org.noip.sinc.clojure.ch4
  (:import (java.net HttpURLConnection)))

(defmacro futures
  [n & exprs]
  (vec (for [_ (range n)
             expr exprs]
         `(future ~expr))))

(defmacro wait-futures
  [& args]
  `(doseq [f# (futures ~@args)]
     @f#))

(defn character
  [name & {:as opts}]
  (ref (merge {:name name :items #{} :health 500}
              opts)))

(def smaug (character
             "Smaug" :health 500 :strength 400 :items (set (range 50))))

(def bilbo (character "Bilbo" :health 100 :strength 100))

(def gandalf (character "Gandalf" :health 75 :strength 75 :mana 750))

(defn loot
  [from to]
  (dosync
    (when-let [item (first (:items @from))]
      (alter to update-in [:items] conj item)
      (alter from update-in [:items] disj item))))

;(wait-futures 1
;               (while (loot smaug bilbo))
;               (while (loot smaug gandalf)))

(defn flawed-loot
  [from to]
  (dosync
    (when-let [item (first (:items @from))]
      (commute to update-in [:items] conj item)
      (commute from update-in [:items] disj item))))

;(wait-futures 1
;              (while (flawed-loot smaug bilbo))
;              (while (flawed-loot smaug gandalf)))

(defn fixed-loot
  [from to]
  (dosync
    (when-let [item (first (:items @from))]
      (commute to update-in [:items] conj item)
      (alter from update-in [:items] disj item))))

(wait-futures 1
              (while (fixed-loot smaug bilbo))
              (while (fixed-loot smaug gandalf)))

(def daylight (ref 1))

(declare console)
(declare write)

(defn attack
  [agressor target]
  (dosync
    (let [damage (* (rand 0.1) (:strength @agressor) (ensure daylight))]
      (send-off console write
                (:name @agressor) "hits" (:name @target) "for" damage)
      (commute target update-in [:health] #(max 0 (- % damage))))))

(defn heal
  [healer target]
  (dosync
    (let [aid (min (* (rand 0.1) (:mana @healer))
                   (- (:max-health @target) (:health @target)))]
      (when (pos? aid)
        (send-off console write
                  (:name @healer) "heals" (:name @target) "for" aid)
        (commute healer update-in [:mana] - (max 5 (/ aid 5)))
        (alter target update-in [:health] + aid)))))

(def alive? (comp pos? :health))

(defn play
  [character action other]
  (while (and (alive? @character)
              (alive? @other)
              (action character other))
    (Thread/sleep (rand-int 50))))

(defn- enforce-max-health
  [name health]
  (fn [character-data]
    (or (<= (:health character-data) health)
        (throw (IllegalStateException.
                 (str name " is already at max health!"))))))

(defn character
  [name & {:as opts}]
  (let [cdata (merge {:name name :items #{} :health 500}
                     opts)
    cdata (assoc cdata :max-health (:health cdata))
    validators (list* (enforce-max-health name (:health cdata))
                      (:validators cdata))]
    (ref (dissoc cdata :validators)
         :validator #(every? (fn [v] (v %)) validators))))

(def a "A simple value" 5)

(defn b
  "A simple calcultation using `a`"
  [c]
  (+ a c))

(def ^:dynamic *response-code* nil)

(defn http-get
  [url-string]
  (let [conn (cast java.net.HttpURLConnection (-> url-string java.net.URL. .openConnection))
        responce-code (.getResponseCode conn)]
    (when (thread-bound? #'*response-code*)
      (set! *response-code* responce-code))
    (when (not= 404 responce-code) (-> conn .getInputStream slurp))))

(binding [*response-code* nil]
  (let [content (http-get "http://google.com/bad-url")]
    (println "Responce code was:" *response-code*)))

(require '[clojure.java.io :as io])

(def console (agent *out*))
(def character-log (agent (io/writer "character-states.log" :append true)))

(defn write
  [^java.io.Writer w & content]
  (doseq [x (interpose " " content)]
    (.write w (str x)))
  (doto w
    (.write "\n")
    .flush))

(defn log-reference
  [reference & writer-agents]
  (add-watch reference :log
             (fn [_ reference old new]
               (doseq [writer-agent writer-agents]
                 (send-off writer-agent write new)))))

(def smaug (character "Smaug" :health 500 :strength 400))
(def bilbo (character "Bilbo" :health 100 :strength 100))
(def gandalf (character "Gandalf" :health 75 :mana 1000))

(log-reference bilbo console character-log)
(log-reference smaug console character-log)
(log-reference gandalf console character-log)

;(wait-futures 1
;              (play bilbo attack smaug)
;              (play smaug attack bilbo)
;              (play gandalf heal bilbo))
;
;(dosync
;  (alter smaug assoc :health 500)
;  (alter bilbo assoc :health 100))