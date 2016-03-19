(ns org.noip.sinc.clojure.macro)

(defn ensure-seq [x]
  (if (seq? x) x (list x)))

(defn insert-second
  [x ys]
  (let [ys (ensure-seq ys)]
    (list* (first ys) x (rest ys))))

(defmacro thread
  ([x] x)
  ([x form] (insert-second x form))
  ([x form & more] `(thread (thread ~x ~form) ~@more)))

(defmacro thread-fn
  ([x] x)
  ([x form] (form x))
  ([x form & more] (apply thread-fn (form x) more)))
