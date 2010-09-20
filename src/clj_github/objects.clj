(ns #^{:doc "Implements the API functions described here: http://develop.github.com/p/object.html"}
  clj-github.objects
  (:use clj-github.core))

(defn show-tree
  "Get the contents of a tree by tree sha."
  [user auth repo sha]
  (make-request auth ["tree/show" user repo sha] :sift :tree))

(defn show-blob
  "Get data about a blob by tree sha and path. If you want meta, add :meta? true to the parameters."
  [user auth repo sha path & {meta? :meta?}]
  (make-request auth ["blob/show" user repo sha path] :data {"meta" (t-to-n meta?)} :sift :blob))

(defn show-all-blobs
  "Get a list of all blobs for a specific sha."
  [user auth repo sha]
  (make-request auth ["blob/all" user repo sha] :sift :blobs))

(defn show-blob-meta
  "Get metadata of blobs."
  [user auth repo sha]
  (make-request auth ["blob/full" user repo sha] :sift :blobs))

(defn show-tree-meta
  "Get metadata of trees."
  [user auth repo sha]
  (make-request auth ["tree/full" user repo sha] :sift :tree))

(defn show-raw-blob
  "Returns raw blob data."
  [user auth repo sha]
  (make-request auth ["blob/show" user repo sha] :raw? true))