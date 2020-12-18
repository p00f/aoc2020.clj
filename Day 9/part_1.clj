(ns d9_1
  (:require [clojure.string :as s]))

(defn get-input [filename]
  (->> filename
       slurp
       s/split-lines
       (map #(Long/parseLong %))
       vec))

(defn subv-sums [i v]
  (let [subv (subvec v (- i 25) i)]
    (set
      (for [a subv
            b subv
            :when (> b a)]
        (+ a b)))))

(defn answer [v]
  (loop [i 25]
    (if-not (contains? (subv-sums i v) (get v i))
      (nth v i)
      (recur (inc i)))))

(-> *command-line-args*
    first
    get-input
    answer
    println)
