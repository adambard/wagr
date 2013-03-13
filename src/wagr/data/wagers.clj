(ns wagr.data.wagers
  (:require [schemongo.models :as models]
            [somnium.congomongo :as mongo]
            ))

(def wager-schema
  [
   [:better :str]
   [:bettee :str]
   [:bet :str]
   [:wager :str]
   [:date :str]
   [:email1 :str]
   [:email2 :str]
   ])

(models/model "wager" :wagers wager-schema)

(defn top-wagers [n]
  (mongo/fetch :wagers :sort {:_id -1} :limit n))
