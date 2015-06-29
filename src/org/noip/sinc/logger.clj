(defn print-logger
  [writer]
  #(binding [*out* writer]
     (println %)))

(def *out*-logger (print-logger *out*))
(*out*-logger "Hello")

(def writer (java.io.StringWriter.))
(def retained-logger (print-logger *out*))
(retained-logger "Hello")
(str writer)

(require 'clojure.java.io)
(defn file-logger
  [file]
  #(with-open [f (clojure.java.io/writer file :append true)]
     ((print-logger f) %)))

(def log->file (file-logger "messages.log"))
(log->file "hello")

(defn multi-logger
  [& logger-fns]
  #(doseq [f logger-fns]
     (f %)))

(def log (multi-logger
           (print-logger *out*)
           (file-logger "messages.log")))

(log "Hello again!")

(defn timestamped-logger
  [logger]
  #(logger (format "[%1$tY-%1$tm-%1$te %1tH:%1$tM:%1$ts] %2$s"
             (java.util.Date.) %)))

(def log-timestamped (timestamped-logger
                       (multi-logger
                         (print-logger *out*)
                         (file-logger "messages.log"))))

(log-timestamped "Goodbye now")