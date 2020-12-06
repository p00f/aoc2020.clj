(ns d2_1
  (:require [clojure.string :as s]))

(defn get-list [filename]
  (->> filename
       slurp
       s/split-lines
       set))

(defn valid?
  "checks the validity of password+conditions"
  [password]
  (let [p-list (s/split password #"\ ")
        range (-> (first p-list)
                  (s/split #"\-")
                  (->> (map #(Integer/parseInt %))))
        letter (first (second p-list))
        pwd (nth p-list 2)
        occurrences (get (frequencies pwd) letter)]
       (if (nil? occurrences)
         false
         (<= (first range) occurrences (second range)))))

(defn answer [passwords]
  (count (for [pwd passwords
               :when (valid? pwd)]
          1)))

(->> *command-line-args*
     first
     get-list
     answer
     println)
