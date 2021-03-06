(ns #^{:doc "Implements the API functions described here: http://develop.github.com/p/issues.html
            In any function that takes a 'state' argument, state is either \"open\" or \"closed\""}
  clj-github.issues
  (:use [clj-github.core]))

(defn search-issues
  "Search through a project's issues."
  [user repo state term]
  (make-request ["issues/search" user repo state term] :sift :issues))

(defn list-issues
  "List of issues a project has."
  [user repo state]
  (make-request ["issues/list" user repo state] :sift :issues))

(defn list-label-issues
  "List of issues a project has."
  [user repo label]
  (make-request ["issues/list" user repo "label" label] :sift :issues))

(defn show-issue
  "Shows the data on an individual issue."
  [user repo number]
  (make-request ["issues/show" user repo number] :sift :issue))

(defn issue-comments
  "List of comments on an issue/"
  [user repo number]
  (make-request ["issues/comments" user repo number] :sift :comments))

(defn open-issue
  "Open an issue."
  [user repo title body]
  (make-request ["issues/open" user repo] :type :post :data {"title" title "body" body} :sift :issue))

(defn close-issue
  "Closes an issue."
  [user repo number]
  (make-request ["issues/close" user repo number] :sift :issue))

(defn reopen-issue
  "Reopens a previously closed issue."
  [user repo number]
  (make-request ["issues/reopen" user repo number] :sift :issue))

(defn edit-issue
  "Edit an issue."
  [user repo number & {:keys [title body]}]
  (make-request ["issues/edit" user repo number]
                :type :post
                :data (remove (comp nil? val) {"body" body "title" title})
                :sift :issue))

(defn list-labels
  "Lists a project's issue labels."
  [user repo]
  (make-request ["issues/labels" user repo] :sift :labels))

(defn add-label
  "Add a label. If you supply an issue number, the label will be added to that issue.
  Otherwise, the label will be added to the system, but not attached to an issue."
  [user repo label & [number]]
  (make-request ["issues/label/add" user repo label (when number number)] :sift :labels))

(defn remove-label
  "Remove a label. If you supply an issue number, the label will be removed from that issue
  alone. Otherwise, the label will be removed from all issues."
  [user repo label & [number]]
  (make-request ["issues/label/remove" user repo label (when number number)] :sift :labels))

(defn comment-issue
  "Comment on an issue."
  [user repo number comment]
  (make-request ["issues/comment" user repo number]
                :data {:comment comment} :sift :comment))

