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


(defn list-deploy-keys
  "Get a list of deploy keys setup for a repository."
  [repo]
  (make-request ["repos/keys" repo] :sift :public_keys))

(defn add-deploy-key
  "Add a deploy key to a repo."
  [repo title key]
  (make-request ["repos/key" repo "add"] :data {"title" title "key" key} :type "POST" :sift :public_keys))

(defn remove-deploy-key
  "Remove a deploy key from a repo."
  [repo id]
  (make-request ["repos/key" repo "remove"] :type "POST" :data {"id" id} :sift :public_keys))

(defn list-collaborators
  "Get a list of collaborators on a repo."
  [user repo]
  (make-request ["repos/show" user repo "collaborators"] :sift :collaborators))

(defn add-collaborator
  "Add a collaborator to a project."
  [user repo]
  (make-request ["repos/collaborators" repo "add" user] :type "POST" :sift :collaborators))

(defn remove-collaborator
  "Remove a collaborator from a project."
  [user repo]
  (make-request ["repos/collaborators" repo "remove" user] :type "POST" :sift :collaborators))

(defn list-pushable
  "List of repos that are not your own that you can push to. Must be authenticated for this
  to return something meaningful."
  [] (make-request "repos/pushable" :sift :repositories))

(defn list-contributors
  "List of people who have contributed to a project. Default value of include-anon? is false.
  If set to true, will include all non-users who have contributed to this project."
  [user repo & {include-anon? :include-anon? :or {include-anon? false}}]
  (make-request ["repos/show" user repo "contributors" include-anon?] :sift :contributors))

(defn show-network
  "Look at a repo's full network."
  [user repo]
  (make-request ["repos/show" user repo "network"] :sift :network))

(defn show-languages
  "Look at the languages used by a project. Values are in bytes calculated."
  [user repo]
  (make-request ["repos/show" user repo "languages"] :sift :languages))

(defn list-tags
  "List of tags on a repo."
  [user repo]
  (make-request ["repos/show" user repo "tags"] :sift :tags))