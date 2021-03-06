(ns wagr.data.wagers
  (:require [schemongo.models :as models]
            [somnium.congomongo :as mongo]
            [wagr.helpers :as helpers]
            ))

(def wager-schema
  [
   [:better :str]
   [:bettee :str]
   [:bet :str]
   [:wager :str]
   [:date :str]
   [:timestamp :int] ; The timestamp version of the date input
   [:created :int]
   [:email1 :str]
   [:email2 :str]
   [:resolved :bool?]
   ])

(models/model "wager-" :wagers wager-schema)

(defn create-wager! [data]
  (let [timestamp (helpers/parse-date (:date data))
        timedata {
                  :timestamp timestamp
                  :created (helpers/now)
                  }
        ]
    (create-wager-! (merge data timedata))))

(def update-wager! update-wager-!)
(def fetch-wager fetch-wager-)

(defn top-wagers [n]
  (mongo/fetch :wagers :sort {:_id -1} :limit n))
