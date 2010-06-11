(ns #^{:doc "Implements the API functions described here: http://develop.github.com/p/commits.html"}
  clj-github.commit
  (:use [clj-github.core]))

(defn list-commits
  "Get the latest commits in a branch of a repository."
  [user repo branch]
  (handle (make-request ["/commits/list" user repo branch]) :commits))