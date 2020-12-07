;; Credits - Fred OVerflow on YouTube

(ns d2_1_i
  (:require [clojure.string :as s]))

(defn get-list [filename]
  (->> filename
       slurp
       s/split-lines
       vector))

(defn parse-pwd-cond-str
  [pwd-cond-str]
  (let [[_ min max letter pwd] (re-matches #"(\d+)-(\d+) (\w): (\w+)" pwd-cond-str)]
    {:min (Integer/parseInt min)
     :max (Integer/parseInt max)
     :letter (nth letter 0)
     :pwd pwd}))

(defn valid?
  "checks the validity of password+conditions"
  [pwd-cond]
  (let [min (pwd-cond :min)
        max (pwd-cond :max)
        letter (pwd-cond :letter)
        pwd (pwd-cond :pwd)
        freq (get (frequencies pwd) letter)]
    (if (nil? freq)
      false
      (<= min freq max))))

(defn answer [pwd-cond-list]
  (count (for [pwd-cond-str pwd-cond-list
               :when (valid? (parse-pwd-cond-str pwd-cond-str))]
          1)))

(->> "/home/p00f/stuff/aoc2020/Day 2/input.txt"
     get-list
     first
     answer)
