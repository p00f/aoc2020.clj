(ns d8_1
  (:require [clojure.string :as s]))

(defn get-list [filename]
  (->> filename
       slurp
       s/split-lines
       vector
       first))

;(defvar accumulator 0)

(defn parse-intrn [intrn-str]
  (let [[ _ op sgn count] (re-matches #"(\w+) (-|\+)(\d+)" intrn-str)]
    {:op op
     :sgn (nth sgn 0)
     :count (Integer/parseInt count)}))
(defn execute-intrn [intrn pos list]
  (let [[op sgn count] intrn]
    ()))
  
(parse-intrn (second (get-list "/home/p00f/stuff/aoc2020/Day 8/input.txt")))
