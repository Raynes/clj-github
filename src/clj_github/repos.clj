(ns #^{:doc "Impelements the API functions described here: http://develop.github.com/p/repo.html"}
  clj-github.repos
  (:use [clj-github.core]))

(defn search-repos
  "Searches for repos. Optionally supply language and start page to narrow the search."
  [auth query & {:keys [language start-page]}]
  (make-request auth ["repos/search" query]
                :data {"language" language "startpage" start-page}
                :sift :repositories))

(defn show-repo-info
  "Detailed information about a repo."
  [auth user repo]
  (make-request auth ["repos/show" user repo] :sift :repository))

(defn set-repo-info
  "Set info about your repo. Possible targets are description, homepage, has_wiki,
  has_downloads, and has_issues."
  [auth user repo key value]
  (make-request auth ["repos/show" user repo]
                :type :post
                :data {(str "values[" key "]") value}
                :sift :repository))

(defn show-repos
  "List all the repos a user has."
  [auth user]
  (make-request auth ["repos/show" user] :sift :repositories))

(defn watch-repo
  "Watch a repo."
  [auth user repo]
  (make-request auth ["repos/watch" user repo] :sift :repository))

(defn unwatch-repo
  "Unwatch a repo."
  [auth user repo]
  (make-request auth ["repos/unwatch" user repo] :sift :repository))

(defn fork-repo
  "Fork a repo."
  [auth user repo]
  (make-request auth ["repos/fork" user repo] :sift :repository))

(defn create-repo
  "Create a repository. You need to supply at least name, but you can supply as many of
  :description, :homepage, :public, as you want."
  [auth name & {:keys [public homepage description] :or {public true}}]
  {:pre [(not (nil? name))]}
  (make-request auth "repos/create"
                :type :post            ; obviously.
                :data {"name" name
                       "public" (t-to-n public)
                       "homepage" homepage
                       "description" description}
                :sift :repository))

(defn delete-repo
  "Delete a repo. Cannot be undone."
  [auth repo]
  (let [delete-token (make-request auth ["repos/delete" repo]
                                   :type :post)]
    (if (map? delete-token)
      (make-request auth ["repos/delete" repo]
                    :type :post
                    :data {"delete_token" (:delete_token delete-token)}
                    :sift :status)
      delete-token)))

(defn set-repo-visibility
  "Set a repositories visibility. Either public or private."
  [auth repo visibility]
  (make-request auth ["repos/set" visibility repo] :type :post :sift :repository))


(defn show-deploy-keys
  "Get a list of deploy keys setup for a repository."
  [auth repo]
  (make-request auth ["repos/keys" repo] :sift :public_keys))

(defn add-deploy-key
  "Add a deploy key to a repo."
  [auth repo title key]
  (make-request auth ["repos/key" repo "add"] :data {"title" title "key" key} :type :post :sift :public_keys))

(defn remove-deploy-key
  "Remove a deploy key from a repo."
  [auth repo id]
  (make-request auth ["repos/key" repo "remove"] :type :post :data {"id" id} :sift :public_keys))

(defn show-collaborators
  "Get a list of collaborators on a repo."
  [auth user repo]
  (make-request auth ["repos/show" user repo "collaborators"] :sift :collaborators))

(defn add-collaborator
  "Add a collaborator to a project."
  [auth user repo]
  (make-request auth ["repos/collaborators" repo "add" user] :type :post :sift :collaborators))

(defn remove-collaborator
  "Remove a collaborator from a project."
  [auth user repo]
  (make-request auth ["repos/collaborators" repo "remove" user] :type :post :sift :collaborators))

(defn show-pushable
  "List of repos that are not your own that you can push to. Must be authenticated for this
  to return something meaningful."
  [auth] (make-request auth "repos/pushable" :sift :repositories))

(defn show-contributors
  "List of people who have contributed to a project. Default value of include-anon? is false.
  If set to true, will include all non-users who have contributed to this project."
  [auth user repo & {include-anon? :include-anon? :or {include-anon? false}}]
  (make-request auth ["repos/show" user repo "contributors" include-anon?] :sift :contributors))

(defn show-network
  "Look at a repo's full network."
  [auth user repo]
  (make-request auth ["repos/show" user repo "network"] :sift :network))

(defn show-languages
  "Look at the languages used by a project. Values are in bytes calculated."
  [auth user repo]
  (make-request auth ["repos/show" user repo "languages"] :sift :languages))

(defn show-tags
  "List of tags on a repo."
  [auth user repo]
  (make-request auth ["repos/show" user repo "tags"] :sift :tags))

