(ns wagr.routes
  (:use compojure.core)
  (:require [compojure.route :as route]
            [wagr.core :as wagr]
            ))

(defroutes main-routes
  (GET "/" [] (wagr/index))
  (POST "/" [& params] (wagr/create-wager params))
  (GET "/wager/show/:id/" [id] (wagr/show-wager id))
  (route/resources "/")
  (route/not-found "404 Not Found")
  )


