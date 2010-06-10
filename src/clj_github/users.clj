(ns clj-github.users
  (:use [clj-github.core]))

(defn search
  "Searches for all users on github with term in their name."
  [term]
  (make-request (str "/user/search/" term)))

(defn get-user-info
  "Grabs information about a specific user."
  [user]
  (let [result (make-request (str "/user/show/" user))]
    (if-let [error (:error result)] error (:user result))))