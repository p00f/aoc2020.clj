(ns d2_2
  (:require [clojure.string :as s]))

(defn get-list [filename]
  (->> filename
       slurp
       s/split-lines
       set))

(defn xor [& args]
  (odd? (count (filter true? args))))

(defn check-validity
  "checks the validity of password+conditions"
  [password]
  (let [p-list (s/split password #"\ ")]
    (let [positions 
          (map #(Integer/parseInt %) (s/split (first p-list) #"\-"))
          letter (first (second p-list))
          pwd (get p-list 2)]
      (xor
        (= letter
           (nth pwd (- (first positions) 1)))
        (= letter
           (nth pwd (- (second positions) 1)))))))

(defn answer [passwords]
  (let [valid-passwords 0]
    (count (for [pwd passwords
                 :when (check-validity pwd)]
            (inc valid-passwords)))))

(->> *command-line-args*
     first
     get-list
     answer
     println)
