# clj-github

Much needed Github API bindings for Clojure.

## Usage

clj-github works by using a top-level *authentication* var that holds a map containing keys :user and :pass. This var is meant to be rebound to hold your authentication information before you make any Github API requests. Here's an example:

    (use '[clj-github.users :only [search]])
    (binding [*authentication* {:user "Raynes" :pass "asifimgoingtotellyou"}] (search "Licenser"))

In clj-github.core, I've defined a helper-macro for this called with-auth. Here is the example above written using with-auth:

    (use '[clj-github [core :only [with-auth]] [users :only [search]]])
    (with-auth {:user "Raynes" :pass "asifimgoingtotellyou"} (search "Licenser"))

While *authentication* var could have been replaced with each of the API functions taking an auth map explicitly, I think the rebinding scheme that clj-github uses is better suited for this particular task, simply because it's much easier to chain API calls together without having an extra parameter to pass to each of them.

API functions are sectioned into categories and placed in different files. Have fun exploring. :)

## Installation

Once this is in a usable state, I'll put it on Clojars. You'll be able to get it the normal Leiningen way.

## License

Licensed under the EPL. You can find a copy of this license at the root of this directory.
