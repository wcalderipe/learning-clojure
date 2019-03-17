(ns learning-clojure.brave-and-true.ch4.fwpd-test
  (:require [clojure.test :refer :all]
            [learning-clojure.brave-and-true.ch4.fwpd :refer :all]))

(deftest fn-str->int
  (testing "converts a string into integer"
    (is (= 1 (str->int "1")))))

(deftest fn-convert
  (testing "returns the name as it is"
    (is (= "foo" (convert :name "foo"))))
  (testing "returns the glitter-index as string"
    (is (= 10 (convert :glitter-index "10")))))

(deftest fn-parse
  (testing "converts a csv string into rows and columns"
    (is (= [["foo" "1"] ["bar" "2"]] (parse "foo,1\nbar,2")))))

(deftest fn-mapify
  (testing "maps key-value into :name and :glitter-index"
    (is (= [{:name "foo" :glitter-index 1}
            {:name "bar" :glitter-index 2}] (mapify [["foo" "1"]
                                                     ["bar" "2"]])))))

(deftest fn-glitter-filter
  (testing "filters vampires by the given minimum glitter"
    (is (= [{:name "foo" :glitter-index 10}
            {:name "bar" :glitter-index 5}] (glitter-filter 5 [{:name "foo" :glitter-index 10}
                                                              {:name "baz" :glitter-index 2}
                                                              {:name "bar" :glitter-index 5}])))))

(deftest fn-safe-load-suspects
  (testing "loads suspects csv file content"
    (is (string? (safe-load-suspects)))))

(deftest fn-add-suspect
  (testing "adds a new suspect to a given collection"
    (is (= [{:name "foo" :glitter-index 10}
            {:name "bar" :glitter-index 5}] (add-suspect
                                             [{:name "foo" :glitter-index 10}]
                                             {:name "bar" :glitter-index 5})))))

(deftest fn-add-suspect
  (testing "does not add a new suspect if validation fail"
    (is (= [] (add-suspect [] {:invalid-key "foo"})))))
