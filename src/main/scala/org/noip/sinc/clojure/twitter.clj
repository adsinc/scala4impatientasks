(ns
  ^{:author sinc}
  org.noip.sinc.twitter)

(require 'clojure.xml)

(defn twitter-followers
  [username]
  (->> (str "https://api.twitter.com/1.1/users/show.xml?screen_name="
         username)
    clojure.xml/parse
    :content
    (filter (comp #{:folowers_count} :tag))
  first
  :content
  first
  Integer/parseInt))

(twitter-followers "ClojureBook")