(ns q2
  (:require [clojure.string :as s]))

(defn get-list [filename]
  (->> filename
       slurp
       s/split-lines
       set))

(defn check-validity
  "checks the validity of password+conditions"
  [password]
  (let [p-list (s/split password #"\ ")]
    (let [range (map #(Integer/parseInt %) (s/split (first p-list) #"\-"))
          letter (first (second p-list))
          pwd (nth p-list 2)]
      (let [occurrences (get (frequencies pwd) letter)]
        (if (nil? occurrences)
          false
          (<= (first range) occurrences (second range)))))))

(let [passwords (get-list "/home/p00f/stuff/aoc2020/input/2.txt")
      valid-passwords 0]
  (count (for [pwd passwords
               :when (check-validity pwd)]
          (inc valid-passwords))))
