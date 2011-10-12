(ns #^{:doc "Implements the API functions described here: http://develop.github.com/p/users.html"}
  clj-github.users
  (:use [clj-github.core]))

(defn search-users
  "Searches for all users on github with term in their name."
  [term]
  (make-request ["user/search" term] :sift :users))

(defn show-user-info
  "Grabs information about a specific user."
  [user]
  (make-request ["user/show" user] :sift :user))

(defn set-user
  "Set certain information for a user. Accepted inputs for target are
  name, email, blog, company, and location."
  [user target value]
  {:pre [(#{"name" "email" "blog" "company" "location"} target)]}
  (make-request ["user/show" user] :type :post :data {(str "values[" target "]") value} :sift :user))

(defn show-following
  "Get a list of the users that a user is following."
  [user]
  (make-request ["user/show" user "following"] :sift :users))

(defn show-followers
  "Get a list of users that are following a user."
  [user]
  (make-request ["user/show" user "followers"] :sift :users))

(defn follow
  "Follow a user."
  [user]
  (make-request ["user/follow" user] :type :post :sift :users))

(defn unfollow
  "Unfollow a user."
  [user]
  (make-request ["user/unfollow" user] :type :post :sift :users))

(defn show-watching
  "Get a list of repos that user is watching."
  [user]
  (make-request ["repos/watched" user] :sift :repositories))

