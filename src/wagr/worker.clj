(ns wagr.worker
  (:require
    [wagr.mail :as mail]
    [wagr.helpers :as helpers]
    [wagr.server :as server]
    [wagr.data.wagers :as wagers]
    [somnium.congomongo :as mongo]))

(declare wagr-message)

(defn resolve-wager [wager]
  (let [msg (wagr-message wager)
        subj "Time's up"
        to (map wager [:email1 :email2])
        ]
    (map #(mail/send % subj msg) to)
    (wagers/update-wager! wager {:resolved true})))

(defn resolve-wagers []
  (let [wagers (mongo/fetch :wagers :where
      {
      :resolved {:$ne true}
      :timestamp {:$lt (helpers/now)}
      })]
    (map resolve-wager wagers)))

(defn -main []
  (server/connect-mongo)
  (resolve-wagers))


(defn wagr-message [wager]
  (format "
Your wager on Wagr is up!

In case you forgot, here's the gist:

%s bet %s that %s.

The loser must %s.
"
    (:better wager)
    (:bettee wager)
    (:bet wager)
    (:wager wager)))
