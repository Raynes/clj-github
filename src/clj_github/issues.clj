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

(defn show-issue
  "Shows the data on an individual issue."
  [user repo number]
  (handle (make-request (str "/issues/show/" (slash-join user repo number))) :issue))

(defn issue-comments
  "List of comments on an issue/"
  [user repo number]
  (handle (make-request (str "/issues/comments/" (slash-join user repo number))) :comments))

(defn open-issue
  "Open an issue."
  [user repo title body]
  (handle (make-request (str "/issues/open/" user "/" repo) :data {"title" title "body" body}) :issue))

(defn close-issue
  "Closes an issue."
  [user repo number]
  (handle (make-request (str "/issues/close/" (slash-join user repo number))) :issue))

(defn reopen-issue
  "Reopens a previously closed issue."
  [user repo number]
  (handle (make-request (str "/issues/reopen/" (slash-join user repo number))) :issue))

(defn edit-issue
  "Edit an issue."
  [user repo number & {:keys [title body]}]
  (handle (make-request (str "/issues/edit/" (slash-join user repo number))
			:data (remove (comp nil? val) {"body" body "title" title}))
    :issue))

(defn show-labels
  "Lists a project's issue labels."
  [user repo]
  (handle (make-request (str "/issues/labels/" user "/" repo)) :labels))

(defn add-label
  "Add a label. If you supply an issue number, the label will be added to that issue.
  Otherwise, the label will be added to the system, but not attached to an issue."
  [user repo label & [number]]
  (handle (make-request (str "/issues/label/add/"
			     (slash-join user repo label)
			     (when number (str "/" number))))
    :labels))

(defn remove-label
  "Remove a label. If you supply an issue number, the label will be removed from that issue
  alone. Otherwise, the label will be removed from all issues."
  [user repo label & number]
  (handle (make-request (str "/issues/label/remove/"
			     (slash-join user repo label)
			     (when number (str "/" number))))
    :labels))