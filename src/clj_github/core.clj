(ns clj-github.core
  (:use [clojure.contrib.base64 :only [encode-str]]
	[clojure-http.client :only [request]]
	[org.danlarkin.json :only [decode-from-str]])
  (:import java.net.URI))

(defn create-url [rest] (str (URI. "http" "github.com" (str "/api/v2/json" rest) nil)))

(def #^{:doc "This var will be rebound to hold authentication information."}
     *authentication*)

(defn make-request
  "Constructs a basic authentication request."
  [url & {:keys [type] :or {type "GET"}}]
  (-> (request (create-url url) type
	       {"Authentication: Basic " (encode-str
					  (str (:user *authentication*) ":" (:pass *authentication*)))})
      :body-seq
      first
      decode-from-str))

(defmacro with-auth [auth body]
  `(binding [*authentication* ~auth] ~body))