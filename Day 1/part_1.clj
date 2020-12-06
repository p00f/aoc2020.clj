(ns d1_1
  (:require [clojure.string :as s]))

(defn get-list [filename]
  (->> filename
       slurp
       s/split-lines
       (map #(Integer/parseInt %))
       set))

(defn answer [numbers]
  (for [a numbers
        :let [b (- 2020 a)]
        :when (<= a b)
        :when (contains? numbers b)]
    (* a b)))

(->> *command-line-args*
     first
     get-list
     answer
     println)
