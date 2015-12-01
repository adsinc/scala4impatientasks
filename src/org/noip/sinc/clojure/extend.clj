(ns org.noip.sinc.clojure.extend
  (:import (org.noip.sinc.clojure.protocols Matrix)
           (org.noip.sinc.clojure.extend Measurable)))

(defrecord Point [x y])

(extend Point
  Matrix
  {:lookup (fn [pt i j]
             (when (zero? j)
               (case i
                 0 (:x pt)
                 1 (:y pt))))
  :update (fn [pt i j value]
            (if (zero? j)
              (condp = i
                0 (Point. value (:y pt))
                1 (Point. (:x pt) value))
              pt))
   :rows (fn [pt]
           [[(:x pt)][:y pt]])
   :cols (fn [pt]
           [[(:x pt)(:y pt)]])
   :dims (fn [pt] [2 1])})

(def abstract-matrix-impl
  {:cols (fn [pt]
           (let [[h w] (dims pt)]
             (map
               (fn [x] (map #(lookup pt x %) (range 0 w)))
               (range 0 h))))
   :rows (fn [pt]
           (apply map vector (cols pt)))})

(extend Point
  Matrix
  (assoc abstract-matrix-impl
    :lookup (fn [pt i j]
              (when (zero? j)
                (case i
                  0 (:x pt)
                  1 (:y pt))))
    :update (fn [pt i j value]
              (if (zero? j)
                (condp = i
                  0 (Point. value (:y pt))
                  1 (Point. (:x pt) value))
                pt))
    :dims (fn [pt] [2 1])))

(defprotocol Measurable
  (width [measurable])
  (height [measurable]))

(defrecord Button [text])

(extend-type Button
  Measurable
  (width [btn]
    (* 8 (-> btn :text count)))
  (height [btn] 8))

(def bordered
  {:width #(* 2 (:border-width %))
   :heigth #(* 2 (:border-heigth %))})

(defn combine
  [op f g]
  (fn [& args]
    (op (apply f args) (apply g args))))

(defrecord BorderedButton [text border-width border-height])

(extend BorderedButton
  Measurable
  (merge-with (partial combine +)
              (get-in Measurable [:impls Button])
              bordered))

(let [btn (Button. "Hello world")]
  [(width btn) (height btn)])

(let [bbtn (BorderedButton. "Hello world" 6 4)]
  [(width bbtn) (height bbtn)])