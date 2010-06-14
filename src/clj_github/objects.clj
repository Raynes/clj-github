(ns #^{:doc "Implements the API functions described here: http://develop.github.com/p/object.html"}
  clj-github.objects
  (:use clj-github.core))

(defn show-tree
  "Get the contents of a tree by tree sha."
  [user repo sha]
  (make-request ["tree/show" user repo sha] :sift :tree))

(defn show-blob
  "Get data about a blob by tree sha and path. If you want meta, add :meta? true to the parameters."
  [user repo sha path & {meta? :meta?}]
  (make-request ["blob/show" user repo sha path] :data {"meta" (t-to-n meta?)} :sift :blob))

(defn list-all-blobs
  "Get a list of all blobs for a specific sha."
  [user repo sha]
  (make-request ["blob/all" user repo sha] :sift :blobs))

(defn show-blob-meta
  "Get metadata of blobs."
  [user repo sha]
  (make-request ["blob/full" user repo sha] :sift :blobs))

(defn show-tree-meta
  "Get metadata of trees."
  [user repo sha]
  (make-request ["tree/full" user repo sha] :sift :tree))

(defn show-raw-blob
  "Returns raw blob data."
  [user repo sha]
  (make-request ["blob/show" user repo sha] :raw? true))