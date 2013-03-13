(ns wagr.core
  (:require
    [wagr.views.common :as common-views]
    [ring.util.response :as resp]
    ))

(def bets (atom '()))

; GET /
(defn index [& args]
  (common-views/index-tpl (take 5 @bets)))

; POST /
(defn create-wager [bet]
  (swap! bets conj bet)
  (swap! bets (partial take 5))
  (resp/redirect "/"))
