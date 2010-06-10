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

(defn following
  "Get a list of the users that a user is following."
  [user]
  (handle (make-request (str "/user/show/" user "/following")) :users))

(defn followers
  "Get a list of users that are following a user."
  [user]
  (handle (make-request (str "/user/show/" user "/followers")) :users))

(defn follow
  "Follow a user."
  [user]
  (handle (make-request (str "/user/follow/" user) :type "POST")))

(defn unfollow
  "Unfollow a user."
  [user]
  (handle (make-request (str "/user/unfollow/" user) :type "POST")))

(defn watching
  "Get a list of repos that user is watching."
  [user]
  (handle (make-request (str "/repos/watched/" user)) :repositories))