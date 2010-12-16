(ns #^{:doc "Implements the API functions described here: http://develop.github.com/p/gist.html"}
  clj-github.gists
  (:use clj-github.core))

(defn- make-gist-request
  "Constructs a gist API request. Same as make-request, just using the gist URL instead."
  [auth path & rest]
  (apply make-request auth path :gist? true rest))

(defn show-gist-meta
  "Get a gist's metadata."
  [auth id]
  (make-gist-request auth (str id) :sift :gists))

(defn show-gist
  "Get the contents of a gist."
  [auth id file-name]
  (make-gist-request auth [id file-name] :special "/raw/" :raw? true))

(defn show-users-gists
  "Show metadata for gists by another user."
  [auth user]
  (make-gist-request auth ["gists" user] :sift :gists))

(defn new-gist
  "Create a new gist. You can supply as many filename -> contents pairs as you like.
  Syntax highlighting is determined by extension."
  [auth & files]
  (first (make-gist-request auth "new"
                            :type :post
                            :data (into {}
                                        (for [[k v] (partition 2 files)]
                                          [(str "files[" k "]") v]))
                            :sift :gists)))

