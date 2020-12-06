(ns d6_1
  (:require [clojure.string :as s]))

(defn get-groups
  [filename]
  (list (s/split (slurp filename) #"\n\n")))

(defn get-group-answers
  [group]
  (vector (s/split group #"\n")))

(defn unique-letters
  [group-answers]
  (let [string_ (reduce str (first group-answers))]
    (count (frequencies string_))))
(defn answer
  [groups]
  (reduce + (for [group groups]
              (unique-letters (get-group-answers group)))))

(->> *command-line-args*
     first
     get-groups
     first
     answer
     println)
