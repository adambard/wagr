(ns wagr.routes
  (:use compojure.core)
  (:require [compojure.route :as route]
            [wagr.core :as wagr]
            [wagr.views.common :as wagr-views]
            ))

(defroutes main-routes
  (GET "/" [] (wagr/index))
  (POST "/" [& params] (wagr/create-wager params))
  (GET "/wager/show/:id/" [id] (wagr/show-wager id))
  (GET "/about" [] (wagr-views/about-tpl))
  (route/resources "/")
  (route/not-found "404 Not Found")
  )


