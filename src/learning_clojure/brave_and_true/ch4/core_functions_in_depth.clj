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

; The following example shows how you could use this capability if you were a vampire trying
; to curb your human consumption. You have two vectors, one representing human intake in liters
; and another representing critter intake for the past four days. The unify-diet-data function
; takes a single day’s data for both human and critter feeding and unifies the two into a single map
(def human-consumption [8.1 7.3 6.6])
(def critter-consumption [0.0 0.2 0.3])

(defn unify-diet-data
  [human critter]
  {:human human
   :critter critter})
