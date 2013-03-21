(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all ])
  (:gen-class ))

(defn search-result? [n]
  (and
    (= (tag n) :a )
    (= (:class (attributes n)) "l")))

(defn get-links []
  (let [data (parse "clojure_google.html")]
    (map #(:href (attributes %))
      (filter search-result?
        (tree-seq #(keyword? (tag %)) children
          (parse "clojure_google.html"))))))

(defn -main []
  (println (str "Found " (count (get-links)) " links!")))
