(ns org.noip.sinc.clojure.arrayset)

(defn scaffold
      [interface]
      (doseq [[iface methods] (->> interface
                                   .getMethods
                                   (map #(vector (.getName (.getDeclaringClass %))
                                                 (symbol (.getName %))
                                                 (count (.getParameterTypes %))))
                                   (group-by first))]
             (println (str " " iface))
             (doseq [[_ name argcount] methods]
                    (println
                      (str " "
                           (list name (into '[this]
                                            (take argcount (repeatedly gensym)))))))))

(scaffold clojure.lang.IPersistentSet)