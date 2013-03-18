 (ns wagr.mail
    (:require
      [postal.core :as postal]))

(defn getenv
  ([val] (getenv val nil))
  ([val default] (get (System/getenv) val default)))

(def smtp-info
  {
    :host (getenv "SMTP_HOST")
    :user (getenv "SMTP_USER")
    :pass (getenv "SMTP_PASS")
    :port (getenv "SMTP_PORT")
   })

(defn- send_ [msg]
  (postal/send-message
    #^{
      :host (getenv "SMTP_HOST")
      :user (getenv "SMTP_USER")
      :pass (getenv "SMTP_PASS")
      :port (Integer. (getenv "SMTP_PORT"))
       }
    {
     :from (getenv "MAIL_FROM" "wagr@adambard.com")
     :to (:to msg)
     :subject (str "[WAGR] " (:subject msg))
     :body [{:type "text/html"
             :content (:body msg)
             }]
    }))

(defn send [to subject body]
  (println "Sending to" to)
  (send_ {:to to :subject subject :body body}))
