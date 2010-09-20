(ns #^{:doc "Implements the API functions described here: http://develop.github.com/p/gist.html"}
  clj-github.gists
  (:use clj-github.core))

(defn- make-gist-request
  "Constructs a gist API request. Same as make-request, just using the gist URL instead."
  [path & rest]
  (apply make-request path :gist? true rest))

(defn show-gist-meta
  "Get a gist's metadata."
  [id]
  (make-gist-request (str id) :sift :gists))

(defn show-gist
  "Get the contents of a gist."
  [id file-name]
  (make-gist-request [id file-name] :special "/raw/" :raw? true))

(defn show-users-gists
  "Show metadata for gists by another user."
  [user]
  (make-gist-request ["gists" user] :sift :gists))

(defn new-gist
  "Create a new gist. You can supply as many filename -> contents pairs as you like.
  Syntax highlighting is determined by extension."
  [& files]
  (first (make-gist-request "new"
                            :type :post
                            :data (into {}
                                        (for [[k v] (partition 2 files)]
                                          [(str "files[" k "]") v]))
                            :sift :gists)))