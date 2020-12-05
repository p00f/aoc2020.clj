(ns q1
  (:require [clojure.string :as s]))

(defn get-list [filename]
  (->> filename
       slurp
       s/split-lines
       (map #(Integer/parseInt %))
       set))

(let [numbers (get-list "/home/p00f/stuff/aoc2020/input/1.txt")]
  (for [a numbers
        b numbers
        :let [c (- 2020 a b)]
        :when (<= a b c)
        :when (contains? numbers c)]
       (* a b c)))
