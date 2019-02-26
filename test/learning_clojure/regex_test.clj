(ns learning-clojure.regex-test
  (:require [clojure.test :refer :all]
            [learning-clojure.regex :refer :all]))

(deftest fn-parse-date
  (testing "parses a string dd-mm-yyyy into a map"
    (is (= {:day "17" :month "10" :year "1991"} (parse-date "17-10-1991")))))

(deftest fn-sanitize-cpf
  (testing "removes dots and dashes"
    (is (= "00011122233" (sanitize-cpf "000.111.222-33")))))
