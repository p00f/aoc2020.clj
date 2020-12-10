;; Credits - Fred Overflow on YouTube
(ns d8_1
  (:require [clojure.string :as s]))

(defn get-list [filename]
  (->> filename
       slurp
       s/split-lines
       vector
       first))

(defn parse-intrn [intrn-str]
  (let [[ _ op arg]
        (re-matches #"(\w+) ([+-]\d+)" intrn-str)]
    [(keyword op)
     (Integer/parseInt arg)]))

(defn execute [program]
  (loop [acc 0
         pc 0
         visited #{}]
    (if (contains? visited pc)
      acc
      (let [[op arg] (program pc)]
        (case op
          :acc (recur (+ acc arg) (inc pc) (conj visited pc))
          :jmp (recur acc (+ pc arg) (conj visited pc))
          :nop (recur acc (inc pc) (conj visited pc)))))))

(let [program (->> "/home/p00f/stuff/aoc2020/Day 8/input.txt"
               get-list
               (mapv parse-intrn))])
