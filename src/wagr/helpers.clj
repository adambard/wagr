(ns wagr.helpers
  (:import
    java.util.Date
    com.mdimension.jchronic.Chronic))

(defn parse-date [s]
  (-> (Chronic/parse s)
      .getBeginCalendar
      .getTime
      .getTime))

(defn now []
  (.getTime (Date.)))
