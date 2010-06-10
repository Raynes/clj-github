(ns clj-github.users
  (:use [clj-github.core]))

(defn search
  "Searches for all users on github with term in their name."
  [term]
  (make-request (str "/user/search/" term)))

(defn get-user-info
  "Grabs information about a specific user."
  [user]
  (handle (make-request (str "/user/show/" user)) :user))

(defn user-set
  "Set certain information for a user. Accepted inputs for target are
  :name, :email, :blog, :company, and :location."
  [user target value]
  {:pre [(#{"name" "email" "blog" "company" "location"} target)]}
  (handle (make-request (str "/user/show/" user) :type "POST" :data {(str "values[" target "]") value})))