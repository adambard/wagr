(ns wagr.routes
  (:use compojure.core)
  (:require [compojure.route :as route]
            [wagr.core :as wagr]
            ))

(defroutes main-routes
  (GET "/" [] (wagr/index))
  (POST "/" [& params] (wagr/create-wager params))
  (route/resources "/"))


