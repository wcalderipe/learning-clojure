(ns learning-clojure.brave-and-true.ch4.lazy-seq
  (:require  [clojure.test :as t]))

; Infinite sequences
(take 3 (repeatedly (fn [] (rand-int 10))))

(concat (take 8 (repeat "na")) ["Batman!"])

(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

(take 10 (even-numbers))

(apply max [1 10 3])
