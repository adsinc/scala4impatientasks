(ns org.noip.sinc.clojure.collections)

(defn swap-pairs
  [sequential]
  (into (empty sequential)
        (interleave
          (take-nth 2 (drop 1 sequential))
          (take-nth 2 sequential))))

(swap-pairs (apply list (range 10)))