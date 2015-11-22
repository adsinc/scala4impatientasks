(ns org.noip.sinc.clojure.protocols
  (:import (clojure.lang IDeref)
           (org.noip.sinc.clojure.protocols Matrix)))

(defprotocol Matrix
  "Протокол для работы с 2-мерными структурами данных"
  (lookup [matrix i j])
  (update [matrix i j value])
  (rows [matrix])
  (cols [matrix])
  (dims [matrix]))

(extend-protocol Matrix
  clojure.lang.IPersistentVector
  (lookup [vov i j]
    (get-in vov [i j]))
  (update [vov i j value]
    (assoc-in vov [i j] value))
  (rows [vov]
    (seq vov))
  (cols [vov]
    (apply map vector vov))
  (dims [vov]
    [(count vov) (count (first vov))]))

(extend-protocol Matrix nil
  (lookup [vov i j])
  (update [vov i j value])
  (rows [vov])
  (cols [vov])
  (dims [vov]))

(defn vov
  "Создает вектор из h векторов, каждый из которых сожержит w элементов."
  [h w]
  (vec (repeat h (vec (repeat w nil)))))

(def matrix (vov 3 4))

(extend-protocol Matrix
  (Class/forName "[[D")
  (lookup [matrix i j]
    (aget matrix i j))
  (update [matrix i j value]
    (let [clone (aclone matrix)]
      (aset clone i
            (doto (aclone (aget clone 1))
              (aset j value)))
      clone))
  (rows [matrix]
    (map vec matrix))
  (cols [matrix]
    (apply map vector matrix))
  (dims [matrix]
    (let [rs (count matrix)]
      (if (zero? rs)
        [0 0]
        [rs (count (aget matrix 0))]))))

(deftype SrodingerCat [^:unsynchronized-mutable state]
  clojure.lang.IDeref
  (deref [sc]
    (locking sc
      (or state
          (set! state (if (zero? (rand-int 2))
                        :dead
                        :alive))))))

(defn shrodinger-cat
  []
  (SrodingerCat. nil))

(defrecord Point [x y]
  Matrix
  (lookup [_ i j]
    (when (zero? j)
      (case i
        0 x
        1 y)))
  (update [pt i j value]
    (if (zero? j)
      (condp = i
        0 (Point. value y)
        1 (Point. x value))
      pt))
  (rows [_] [[x] [y]])
  (cols [_] [[x y]])
  (dims [_] [2 1]))