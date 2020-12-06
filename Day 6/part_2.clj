(ns d6_2
  (:require [clojure.string :as s]))

(defn get-groups
  [filename]
  (list (s/split (slurp filename) #"\n\n")))

(defn get-group-answers
  [group]
  (first (vector (s/split group #"\n"))))

(defn all-yes-letters
  "Returns the number of letters which were answered 'Yes' in the given vector of answers"
  [group-answers]
  (let [string_ (reduce str group-answers)
        letter-freq (frequencies string_)
        letters (keys letter-freq)
        members (count group-answers)]
    (count (for [l letters
                 :when (= (get letter-freq l) members)]
             1))))

(defn answer
  [groups]
  (reduce + (for [group groups]
              (all-yes-letters (get-group-answers group)))))

(->> *command-line-args*
     first
     get-groups
     first
     answer
     println)
