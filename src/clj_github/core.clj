(ns #^{:doc "Core of clj-github. This namespace is used by the API files. Only thing useful
            it provides to users is the with-auth convenience function."}
  clj-github.core
  (:use [clojure.string :only [join]]
        [clj-http.client :only [request]]
        [clojure.data.json :only [read-json]])
  (:import java.net.URI))

(def ^:dynamic *auth* nil)

(defmacro with-auth [auth & body]
  (binding [*auth* ~auth]
    ~@body))

(defn slash-join
  "Returns a string of its arguments separated by forward slashes."
  [& args]
  (join "/" args))

(defn create-url [rest gist? & {special :special}]
  (let [rest (if (string? rest)
               rest
               (apply slash-join (filter identity rest)))]
    (URI. "http" (if gist? "gist.github.com" "github.com")
          (if special
            (str special rest)
            (str "/api/" (if gist? "v1" "v2") "/json/" rest))
          nil)))

(defn t-to-n
  "Returns 1 for true and 0 for false"
  [n]
  (if n 1 0))

(defn handle
  "Checks for an error, and if one has happened, returns the message.
  Otherwise, spits out results."
  ([data sifter] (if-let [result (:error data)] result (if sifter (sifter data) data)))
  ([data] (handle data identity)))

(defn extract-auth [auth]
  (when auth
    [(if (:token *auth*)
       (str (:username *auth*) "/token")
       (:username *auth*))
     (or (:token *auth*) (:password *auth*))]))

(defn make-request
  "Constructs a basic authentication request. Path is either a seq of URL segments to be joined
  with slashes, or a full string depicting a path that will be used directly.
  The path should never start with a forward slash; it will be added automatically."
  [path & {:keys [type data sift raw? gist? special] :or {type :get data {} raw? false gist? false}}]
  (let [req (delay
             (request
              {:method type
               :url (str (create-url path  gist? :special special))
               :query-params (into {} (filter #(val %) data))
               :basic-auth (extract-auth *auth*)}))]
    (if raw?
      (->> req force :body)
      (try (-> req force :body read-json (handle sift))
           (catch Exception e
             (if (= "404" (.getMessage e))
               (str "If you're seeing this message, it means that Github threw down a "
                    "Page Not Found page that could not be parsed as JSON. This is usually "
                    "caused by giving invalid information that caused the URL to route to an "
                    "unknown page.")
               (throw e)))))))

