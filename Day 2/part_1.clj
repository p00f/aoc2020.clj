(ns d2_1
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
    (let [range (-> (first p-list)
                    (s/split #"\-")
                    (->> (map #(Integer/parseInt %))))
          letter (first (second p-list))
          pwd (nth p-list 2)]
      (let [occurrences (get (frequencies pwd) letter)]
        (if (nil? occurrences)
          false
          (<= (first range) occurrences (second range)))))))

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
