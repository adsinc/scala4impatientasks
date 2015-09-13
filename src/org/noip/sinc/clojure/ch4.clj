(ns org.noip.sinc.clojure.ch4)

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

(defn attack
  [agressor target]
  (dosync
    (let [damage (* (rand 0.1) (:strength @agressor))]
      (commute target update-in [:health] #(max 0 (- % damage))))))

(defn heal
  [healer target]
  (dosync
    (let [aid (* (rand 0.1) (:mana @healer))]
      (when (pos? aid)
        (commute healer update-in [:mana] - (max 5 (/ aid 5)))
        (commute target update-in [:health] + aid)))))

(def alive? (comp pos? :health))

(defn play
  [character action other]
  (while (and (alive? @character)
              (alive? @other)
              (action character other))
    (Thread/sleep (rand-int 50))))

(def a "A simple value" 5)

(defn b
  "A simple calcultation using `a`"
  [c]
  (+ a c))
