(ns learning-clojure.brave-and-true.ch4.core-functions-in-depth-test
  (:require [learning-clojure.brave-and-true.ch4.core-functions-in-depth :refer :all]
            [clojure.test :refer :all]))

(deftest fn-titleize
  (testing "concats the majestic title to a given string"
    (is (= "foo for the Brave and True" (titleize "foo")))))

; Seq functions first, rest and cons implements a sequence abstraction which allow it
; interact with different types of collections,
(deftest fn-first
  (testing "returns the first element of a vector, list and set"
    (is (= "foo" (first ["foo" "bar"])))
    (is (= "foo" (first '("foo" "bar"))))
    (is (= "foo" (first #{"foo" "bar"})))
  (testing "returns the first key-value of a map"
    (is (= [:foo "bar"] (first {:foo "bar" :baz "bar"}))))))

(deftest fn-rest
  (testing "returns everything except the first element of a vector, list and set"
    (is (= ["bar" "baz"] (rest ["foo" "bar" "baz"])))
    (is (= ["bar" "baz"] (rest '("foo" "bar" "baz"))))
    (is (= ["bar" "baz"] (rest #{"foo" "bar" "baz"}))))
  (testing "returns a vector with key-value vectors for every tuple except the first"
    (is (= [[:bar "bar"]] (rest {:foo "foo" :bar "bar"})))))

(deftest fn-cons
  (testing "adds a new value in the beginning of a vector, list and set"
    (is (= ["foo" "bar"] (cons "foo" ["bar"])))
    (is (= ["foo" "bar"] (cons "foo" '("bar"))))
    (is (= ["foo" "bar"] (cons "foo" #{"bar"}))))
  (testing "adds a new value to the beginning of a map"
    ; The cons will convert {:bar "bar"} into a seq (use assoc to add new elements to a map)
    (is (= [{:foo "foo"} [:bar "bar"]] (cons {:foo  "foo"} {:bar "bar"})))))

(deftest fn-map
  (testing "passing multiple collections"
    (is (= ["aA" "bB"] (map str ["a" "b"] ["A" "B"])))
    (is (= ["aA" "bB"] (list (str "a" "A") (str "b" "B")))))
  (testing "passing a collection of functions (sum and count)"
    (is (= [15 2] (map #(% [5 10]) [#(reduce + %) #(count %)]))))
  (testing "returns a vector of the value associeted with a keyword from a collection"
    (is (= ["Bruce Wayne" "Peter Parker"] (map :real [{:alias "Batman" :real "Bruce Wayne"}
                                                      {:alias "Spider-Man" :real "Peter Parker"}])))))
(deftest fn-reduce
  (testing "transforms a map's values, producing a new map with the same keys but update values"
    (is (= {:max 31 :min 11} (reduce (fn [new-map [key value]]
                                       (assoc new-map key (inc value)))
                                     {}
                                     {:max 30 :min 10}))))
  (testing "filters out keys based on their values"
    (is (= {:human 4.5} (reduce (fn [new-map [key value]]
                                  (if (> value 4)
                                    (assoc new-map key value)
                                    new-map))
                                {}
                                {:human 4.5 :critter 3.5})))))

(deftest fn-take-and-take-while
  (testing "returns the first n elements"
    (is (= [1 2] (take 2 [1 2 3]))))
  (testing "returns"
    (is (= [{:max 2 :min 1}
            {:max 3 :min 2}] (take-while #(> (:min %) 3) [{:max 2 :min 1}
                                                          {:max 3 :min 2}
                                                          {:max 10 :min 5}
                                                          {:max 15 :min 10}])))))

; Vampire diet
(deftest fn-unify-diet-data
  (testing "maps human and critter"
    (is (= {:human 1.5 :critter 0.1} (unify-diet-data 1.5 0.1))))
  (testing "maps multiple human consumption and critter"
    (is [{:human 1.5 :critter 0.1}
         {:human 3.0 :critter 0.2}] (map unify-diet-data [1.5 3.0] [0.1 0.2]))))
