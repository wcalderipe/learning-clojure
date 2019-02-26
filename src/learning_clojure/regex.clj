(ns learning-clojure.regex)

(clojure.string/replace "17/10/1991" #"/" "-")

(clojure.string/replace "foo bar Bar bar" #"Bar" "foo")

; Case sensitive
(clojure.string/replace "foo Bar bar Bar" #"(?i)bar" "foo")

; Matches any part of the string
(re-find #"bar" "foo bar")

; Only match the entire string
(re-matches #"foo.*" "foo bar")

(defn parse-date [date]
  "Expects a date string dd-mm-yyyy"
  (zipmap [:day :month :year]
          (rest (re-find #"([0-9]{2})-([0-9]{2})-([0-9]{4})" "17-10-1991"))))

(defn sanitize-cpf [cpf]
  "Expects a CPF string xxx.yyy.zzz-qq"
  (clojure.string/replace cpf #"\.|-" ""))
