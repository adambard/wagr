(ns wagr.server
  (:use org.httpkit.server)
  (:require
    [wagr.routes :as routes]
    [compojure.handler :as cmpj-handler]))

(defn async-handler [handler]
  (fn [request]
    (async-response respond
      (future
        (respond (handler request))))))

(defn -main []
  (run-server
    (async-handler (cmpj-handler/site #'routes/main-routes))
    {:port 8080}))
