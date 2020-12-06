(ns d1_2
  (:require [clojure.string :as s]))

(defn get-list [filename]
  (->> filename
       slurp
       s/split-lines
       (map #(Integer/parseInt %))
       set))

(defn answer [numbers]
  (for [a numbers
        b numbers
        :let [c (- 2020 a b)]
        :when (<= a b c)
        :when (contains? numbers c)]
    (* a b c)))

(->> *command-line-args*
     first
     get-list
     answer
     println)
