(ns org.noip.sinc.clojure.aset
  (:import (clojure.lang IPersistentSet IPersistentCollection Seqable IFn ArityException)
           (java.util Set Collection)))

(declare empty-array-set)

(def ^:private ^:const max-size 4)

(deftype ArraySet [^objects items
                   ^int size
                   ^:unsynchronized-mutable ^int hashcode]
  IPersistentSet
  (get [_ x]
    (loop [i 0]
      (when (< i size)
        (if (= x (aget items i))
          (aget items i)
          (recur (inc i))))))
  (contains [_ x]
    (boolean
      (loop [i 0]
        (when (< i size)
          (or (= x (aget items i)) (recur (inc i)))))))
  (disjoin [this x]
    (loop [i 0]
      (if (== i size)
        this
        (if (not= x (aget items i))
          (recur (inc i))
          (ArraySet. (doto (aclone items)
                       (aset i (aget items (dec size)))
                       (aset (dec size) nil))
                     (dec size)
                     -1)))))
  IPersistentCollection
  (count [_] size)
  (cons [this x]
    (cond
      (.contains this x) this
      (== size max-size) (into #{x} this)
      :else (ArraySet. (doto (aclone items)
                         (aset size x))
                       (inc size)
                       -1)))
  (empty [_] empty-array-set)
  (equiv [this that] (.equals this that))
  Seqable
  (seq [_] (take size items))
  Object
  (hashCode [_]
    (when (== -1 hashcode)
      (set! hashcode (int (areduce items idx ret 0
                                   (unchecked-add-int ret (hash (aget items idx)))))))
    hashcode)
  (equals [this that]
    (or
      (identical? this that)
      (and (instance? Set that)
           (= (count this) (count that))
           (every? #(contains? this %) that))))
  IFn
  (invoke [this key] (get this key))
  (applyTo [this args]
    (when (not= 1 (count args))
      (throw (ArityException. (count args) "ArraySet")))
    (this (first args)))
  Set
  (isEmpty [_] (zero? size))
  (size [_] size)
  (toArray [_ array]
    (.toArray ^Collection (sequence items) array))
  (toArray [this] (into-array (seq this)))
  (iterator [this] (.iterator ^Collection (sequence this)))
  (containsAll [this coll]
    (every? #(contains? this %) coll)))

(def ^:private empty-array-set (ArraySet. (object-array max-size) 0 -1))

(defn array-set
  [& vals]
  (into empty-array-set vals))

(defn microbenchmark
  [f & {:keys [size trials] :or {size 4 trials 1e6}}]
  (let [items (repeatedly size gensym)]
    (time (loop [s (apply f items)
                 n trials]
            (when (pos? n)
              (doseq [x items] (contains? s x))
              (let [x (rand-nth items)]
                (recur (-> s (disj x) (conj x)) (dec n))))))))

(doseq [n (range 1 5)
        f [#'array-set #'hash-set]]
  (print n (-> f meta :name) ": ")
  (microbenchmark @f :size n))