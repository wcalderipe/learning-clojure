(ns learning-clojure.brave-and-true.ch4.lazy-seq-test
  (:require [learning-clojure.brave-and-true.ch4.core-functions-in-depth :refer :all]
            [clojure.test :refer :all]))

(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true  :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true  :name "McMackson"}
   2 {:makes-blood-puns? true,  :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true,  :has-pulse? true  :name "Mickey Mouse"}})
