(ns wagr.views.common
  (:require
    [net.cgrand.enlive-html :as enlive]
    ))


(enlive/defsnippet show-bet "templates/index.html" [:.latest-bet-item]
  [{better :better bettee :bettee bet :bet wager :wager}] ; ARGS
  ; TRANSFORMATIONS
  [:.better] (enlive/content better)
  [:.bettee] (enlive/content bettee)
  [:.bet] (enlive/content bet)
  [:.wager] (enlive/content wager))


(enlive/deftemplate index-tpl "templates/index.html" ; EL
  [latest-bets] ; ARGS
  ; TRANSFORMATIONS
  [:.latest-bets] (enlive/content (map show-bet latest-bets))
  [:#bet-form] (enlive/set-attr :action "/" :method "post")
  [:#better] (enlive/set-attr :name "better"))
