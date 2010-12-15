(ns #^{:doc "Implements the API functions described here: http://develop.github.com/p/users.html"}
  clj-github.users
  (:use [clj-github.core]))

(defn search-users
  "Searches for all users on github with term in their name."
  [auth term]
  (make-request auth ["user/search" term] :sift :users))

(defn show-user-info
  "Grabs information about a specific user."
  [auth user]
  (make-request auth ["user/show" user] :sift :user))

(defn set-user
  "Set certain information for a user. Accepted inputs for target are
  name, email, blog, company, and location."
  [auth user target value]
  {:pre [(#{"name" "email" "blog" "company" "location"} target)]}
  (make-request auth ["user/show" user] :type :post :data {(str "values[" target "]") value} :sift :user))

(defn show-following
  "Get a list of the users that a user is following."
  [auth user]
  (make-request auth ["user/show" user "following"] :sift :users))

(defn show-followers
  "Get a list of users that are following a user."
  [auth user]
  (make-request auth ["user/show" user "followers"] :sift :users))

(defn follow
  "Follow a user."
  [auth user]
  (make-request auth ["user/follow" user] :type :post :sift :users))

(defn unfollow
  "Unfollow a user."
  [auth user]
  (make-request auth ["user/unfollow" user] :type :post :sift :users))

(defn show-watching
  "Get a list of repos that user is watching."
  [auth user]
  (make-request auth ["repos/watched" user] :sift :repositories))

