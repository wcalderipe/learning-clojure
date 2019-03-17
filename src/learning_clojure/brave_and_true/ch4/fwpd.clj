(ns learning-clojure.brave-and-true.ch4.fwpd)

(def vampire-keys [:name :glitter-index])

(def convertions {:name identity :glitter-index str->int})

(def suspect-keys [:name :glitter-index])

(defn safe-load-suspects
  []
  (try
    (slurp "resources/fwpd_suspects.csv")))

(defn str->int
  [str]
  (Integer. str))

(defn convert
  [vampire-key value]
  ((get convertions vampire-key) value))

(defn parse
  "Convert a CSV into rows and columns"
  [string]
  (map #(clojure.string/split % #",") (clojure.string/split string #"\n")))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vampire-key value]]
                   (assoc row-map vampire-key (convert vampire-key value)))
                 {}
                 (map vector vampire-keys unmapped-row)))
       rows))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

(defn add-suspect
  [suspects new-suspect]
  (if (suspect? new-suspect)
    (conj suspects new-suspect)
    suspects))

(defn suspect?
  [suspect]
  (contains-many? suspect :name :glitter-index))

(defn contains-many?
  [m & ks]
  (every? #(contains? m %) ks))

(defn convert-to-csv
  [suspects]
  (reduce (fn [suspect-map [key value]]
            (println key value))
          {} suspects))

(convert-to-csv (mapify (parse (safe-load-suspects))))
