(ns #^{:doc "Implements the API functions described here: http://develop.github.com/p/issues.html
            In any function that takes a 'state' argument, state is either \"open\" or \"closed\""}
  clj-github.issues
  (:use [clj-github.core]))

(defn search-issues
  "Search through a project's issues."
  [auth user repo state term]
  (make-request auth ["issues/search " user repo state term] :sift :issues))

(defn show-issues
  "List of issues a project has."
  [auth user repo state]
  (make-request auth ["issues/list" user repo state] :sift :issues))

(defn show-issue
  "Shows the data on an individual issue."
  [auth user repo number]
  (make-request auth ["issues/show" user repo number] :sift :issue))

(defn issue-comments
  "List of comments on an issue/"
  [auth user repo number]
  (make-request auth ["issues/comments" user repo number] :sift :comments))

(defn open-issue
  "Open an issue."
  [auth user repo title body]
  (make-request auth ["issues/open" user repo] :type :post :data {"title" title "body" body} :sift :issue))

(defn close-issue
  "Closes an issue."
  [auth user repo number]
  (make-request auth ["issues/close" user repo number] :sift :issue))

(defn reopen-issue
  "Reopens a previously closed issue."
  [auth user repo number]
  (make-request auth ["issues/reopen" user repo number] :sift :issue))

(defn edit-issue
  "Edit an issue."
  [auth user repo number & {:keys [title body]}]
  (make-request auth ["issues/edit" user repo number]
                :type :post
                :data (remove (comp nil? val) {"body" body "title" title})
                :sift :issue))

(defn show-labels
  "Lists a project's issue labels."
  [auth user repo]
  (make-request auth ["issues/labels" user repo] :sift :labels))

(defn add-label
  "Add a label. If you supply an issue number, the label will be added to that issue.
  Otherwise, the label will be added to the system, but not attached to an issue."
  [auth user repo label & [number]]
  (make-request auth ["issues/label/add" user repo label (when number number)] :sift :labels))

(defn remove-label
  "Remove a label. If you supply an issue number, the label will be removed from that issue
  alone. Otherwise, the label will be removed from all issues."
  [auth user repo label & [number]]
  (make-request auth ["issues/label/remove" user repo label (when number number)] :sift :labels))

(defn comment-issue
  "Comment on an issue."
  [auth user repo number comment]
  (make-request auth ["issues/comment" user repo number]
        	:data {:comment comment} :sift :comment))