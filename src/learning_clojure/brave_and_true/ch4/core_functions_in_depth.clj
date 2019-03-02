(ns learning-clojure.brave-and-true.ch4.core-functions-in-depth)

; https://www.braveclojure.com/core-functions-in-depth/

; Seq abstract implementation

(defn titleize
  [topic]
  (str topic " for the Brave and True"))

; Vector
(map titleize ["Humans" "Ragnarok"])

; List
(map titleize '("Empathy" "Decorating"))

; Set
(map titleize #{"Elbows" "Soap Carving"})

; Map
(map #(titleize (second %)) {:uncofortable-thing "Winking"})

; Convert map into a list
(seq {:name "Goat" :occupation "Surfer"})

; Convert list into a map
(into {} [[:name "Goat"] [:occupation "Surfer"]])
