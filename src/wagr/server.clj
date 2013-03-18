(ns wagr.server
  (:use org.httpkit.server)
  (:require
    [somnium.congomongo :as mongo]
    [wagr.routes :as routes]
    [compojure.handler :as cmpj-handler]))

(def conn (atom nil))

(defn async-handler [handler]
  (fn [request]
    (async-response respond
      (future
        (respond (handler request))))))

(defn connect-mongo []
  (let [uri (get (System/getenv) "MONGOHQ_URL" "mongodb://127.0.0.1:27017/wagr")]
    (println uri)
    (reset! conn (mongo/make-connection uri))
    (mongo/set-write-concern @conn :safe)
    (mongo/set-connection! @conn)))

(defn -main []
  ; Connect to Mongo
  (connect-mongo)

  ; Run the server
  (let [port (Integer. (get (System/getenv) "PORT" "8080"))]
    (run-server
      (async-handler (cmpj-handler/site #'routes/main-routes))
      {:port port})))
