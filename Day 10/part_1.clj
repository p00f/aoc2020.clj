(ns d10_1
  (:require [clojure.string :as s]))

(defn get-input [filename]
  (->> filename
       slurp
       s/split-lines
       (map #(Integer/parseInt %))
       sort
       vec))

(defn complete-vec [v]
  (let [l (last v)]
    (concat [0] (conj v (+ l 3)))))

(defn answer [v]
  (let [diffs (map
                (fn [[a b]] (- b a))
                (partition 2 1 v))
        {ones 1, threes 3} (frequencies diffs)]
    (* ones threes)))

(-> *command-line-args*
    first
    get-input
    complete-vec
    vec
    answer
    println)
