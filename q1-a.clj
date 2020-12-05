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
        :let [b (- 2020 a)]
        :when (<= a b)               ;; avoid going a > 1010 because after this it'll be images
        :when (contains? numbers b)]
       (* a  b)))
