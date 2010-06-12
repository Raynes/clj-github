(ns #^{:doc "Implements the API functions described here: http://develop.github.com/p/commits.html"}
  clj-github.commit
  (:use [clj-github.core]))

(defn list-commits
  "Get the latest commits in a branch of a repository. If path is supplied, the path
  will be considered a path to a file, and list-commits will return the commits for
  that file. There should be no trailing slashes in the path."
  [user repo branch & [path]]
  (handle (make-request ["commits/list" user repo branch path]) :commits))

(defn show-commit
  "Show data about a specific commit."
  [user repo sha]
  (handle (make-request ["commits/show" user repo sha]) :commit))