(ns #^{:doc "Implements the API functions described here: http://develop.github.com/p/network.html"}
  clj-github.network
  (:use clj-github.core))

(defn show-network-meta
  "Show network metadata for a repo."
  [auth user repo]
  (make-request auth [user repo "network_meta"] :special "/"))

(defn show-network-data
  "Show network data for a repo. You'll need a nethash from a show-network-meta call."
  [auth user repo nethash]
  (make-request auth [user repo "network_data_chunk"]
                :special "/"
                :data {"nethash" nethash}))