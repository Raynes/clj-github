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

It's worth it to note that authentication is not enforced by clj-github. If you do not wrap API calls in with-auth, clj-github will not complain, even if the particular API function your calling requires that you be autheticated to use it. Rather, the API will error out with "not authorizied" or something. No errors are ever thrown by clj-github. It simply returns the error message given by the API. The only way to know if something requires authentication is to look it up in the API docs. After everything is finished, I'll add notices detailing what requires authentication and what doesn't to docstrings.

API functions are sectioned into categories and placed in different files. Autodoc-generated documentation is [http://raynes.github.com/clj-github/](here). Have fun exploring. :)

## Installation

Once this is in a usable state, I'll put it on Clojars. You'll be able to get it the normal Leiningen way.

## License

Licensed under the EPL. You can find a copy of this license at the root of this directory.
