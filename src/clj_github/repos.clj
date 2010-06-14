(ns #^{:doc "Impelements the API functions described here: http://develop.github.com/p/repo.html"}
  clj-github.repos
  (:use [clj-github.core]))

(defn search-repos
  "Searches for repos. Optionally supply language and start page to narrow the search."
  [query & {:keys [language start-page]}]
  (make-request ["repos/search" query]
                :data {"language" language "startpage" start-page}
                :sift :repositories))

(defn show-repo-info
  "Detailed information about a repo."
  [user repo]
  (make-request ["repos/show" user repo] :sift :repository))

(defn set-repo-info
  "Set info about your repo. Possible targets are description, homepage, has_wiki,
  has_downloads, and has_issues."
  [user repo key value]
  (make-request ["repos/show" user repo]
                :type "POST"
                :data {(str "values[" key "]") value}
                :sift :repository))

(defn list-repos
  "List all the repos a user has."
  [user]
  (make-request ["repos/show" user] :sift :repositories))

(defn watch-repo
  "Watch a repo."
  [user repo]
  (make-request ["repos/watch" user repo] :sift :repository))

(defn unwatch-repo
  "Unwatch a repo."
  [user repo]
  (make-request ["repos/unwatch" user repo] :sift :repository))

(defn fork-repo
  "Fork a repo."
  [user repo]
  (make-request ["repos/fork" user repo] :sift :repository))

(defn create-repo
  "Create a repository. You need to supply at least :name, but you can supply as many of
  :description, :homepage, :public, as you want. Note: :public is 0 for private, and 1 for
  public."
  [& {:keys [name public homepage description]}]
  {:pre [(not (nil? name))]}
  (make-request "repos/create"
                :type "POST"            ; obviously.
                :data {"name" name
                       "public" public
                       "homepage" homepage
                       "description" description}
                :sift :repository))

(defn delete-repo
  "Delete a repo. Cannot be undone."
  [repo]
  (let [delete-token (make-request ["repos/delete" repo]
                                   :type "POST")]
    (if (map? delete-token)
      (make-request ["repos/delete" repo]
                    :type "POST"
                    :data {"delete_token" (:delete_token delete-token)}
                    :sift :status)
      delete-token)))

(defn set-repo-visibility
  "Set a repositories visibility. Either public or private."
  [repo visibility]
  (make-request ["repos/set" visibility repo] :type "POST" :sift :repository))

