(ns #^{:doc "Implements the API functions described here: http://develop.github.com/p/commits.html"}
  clj-github.commits
  (:use [clj-github.core]))

(defn list-commits
  "Get the latest commits in a branch of a repository. If path is supplied, the path
  will be considered a path to a file, and list-commits will return the commits for
  that file. There should be no trailing slashes in the path."
  [auth user repo branch & [path]]
  (make-request auth ["commits/list" user repo branch path] :sift :commits))

(defn show-commit
  "Show data about a specific commit."
  [auth user repo sha]
  (make-request auth ["commits/show" user repo sha] :sift :commit))