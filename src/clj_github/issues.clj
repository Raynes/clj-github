(ns #^{:doc "Implements the API functions described here: http://develop.github.com/p/issues.html
            In any function that takes a 'state' argument, state is either \"open\" or \"closed\""}
  clj-github.issues
  (:use [clj-github.core]))

(defn search-issues
  "Search through a project's issues."
  [user repo state term]
  (handle (make-request (str "/issues/search/" (slash-join user repo state term))) :issues))

(defn list-issues
  "List of issues a project has."
  [user repo state]
  (handle (make-request (str "/issues/list/" (slash-join user repo state))) :issues))