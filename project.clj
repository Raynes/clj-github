(defproject clj-github "1.0.1"
  :description "Bindings to the Github and Gist API."
  :dependencies [[org.clojure/clojure "1.2.0"]
		         [clj-http "0.1.2"]
                 [org.clojure/clojure-contrib "1.2.0"]]
  :dev-dependencies [[swank-clojure "1.2.1"]
                     [cake-autodoc "0.0.1-SNAPSHOT"]]
  :tasks [cake-autodoc.tasks])
