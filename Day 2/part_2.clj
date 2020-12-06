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
    (let [positions (-> (first p-list)
                        (s/split #"\-")
                        (->> (map #(Integer/parseInt %))))
          letter (first (second p-list))
          pwd (get p-list 2)]
      (xor (-> positions
               first
               (as-> f (nth pwd (- f 1)))
               (= letter))
           (-> positions
               second
               (as-> s (nth pwd (- s 1)))
               (= letter))))))

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
