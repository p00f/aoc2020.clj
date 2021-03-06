;; Credits - Fred Overflow on YouTube
(ns d8_2
  (:require [clojure.string :as s]))

(defn get-list [filename]
  (->> filename
       slurp
       s/split-lines
       vector
       first))

(defn parse-intrn [intrn-str]
  (let [[ _ op arg]
        (re-matches #"(\w+) ([+-]\d+)" intrn-str)]
    [(keyword op)
     (Integer/parseInt arg)]))

(defn execute [program]
  (loop [acc 0
         pc 0
         visited #{}]
    (if (contains? visited pc)
      {:status "loops", :acc acc}
      (if (= pc (count program))
        {:status "halts", :acc acc}
        (let [[op arg] (program pc)]
          (case op
            :acc (recur (+ acc arg) (inc pc) (conj visited pc))
            :jmp (recur acc (+ pc arg) (conj visited pc))
            :nop (recur acc (inc pc) (conj visited pc))))))))

(let [program (->> *command-line-args*
                   first
                   get-list
                   (mapv parse-intrn))]
  (->> program
       count
       range
       (map #(update-in program [% 0]
                        {:jmp :nop
                         :nop :jmp
                         :acc :acc}))
       (map execute)
       (filter #(= "halts" (:status %)))
       first
       :acc
       println))
