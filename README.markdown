# wagr

A demo clojure app. This app is running on Heroku at http://wagr.herokuapp.com/.

## Running on Heroku

If you want to do this, you'll need to set up some environment variables:

* **SMTP_HOST** - SMTP vars should be self-explanatory
* **SMTP_USER**
* **SMTP_PASS**
* **SMTP_PORT**
* **MAIL_FROM** - An email address to mail from
* **MONGOHQ_URL** - (Default `mongodb://127.0.0.1:27017/wagr`) A mongo URL. If you just enable mongohq on heroku this will be there
* **PORT** - (Default 8080) Heroku will provide this

## License

Copyright (C) 2013 Adam Bard

Distributed under the Eclipse Public License, the same as Clojure.
