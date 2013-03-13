(ns wagr.core
  (:require
    [wagr.views.common :as common-views]
    [wagr.data.wagers :as wagers]
    [ring.util.response :as resp]
    ))

(def bets (atom '()))

; GET /
(defn index [& args]
  (common-views/index-tpl (wagers/top-wagers 5)))

; POST /
(defn create-wager [bet]
  (let [[wager err] (wagers/create-wager! bet)]
    (when err
      (println "ERROR: " err)))

  (resp/redirect "/"))
