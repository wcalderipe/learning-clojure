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
    (is (= ["aA" "bB"] (list (str "a" "A") (str "b" "B"))))))

                                        ; Vampire diet
(deftest fn-unify-diet-data
  (testing "maps human and critter"
    (is (= {:human 1.5 :critter 0.1} (unify-diet-data 1.5 0.1))))
  (testing "maps multiple human consumption and critter"
    (is [{:human 1.5 :critter 0.1}
         {:human 3.0 :critter 0.2}] (map unify-diet-data [1.5 3.0] [0.1 0.2]))))
