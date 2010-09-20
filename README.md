# clj-github

Much needed Github API bindings for Clojure.

## Usage


    (use '[clj-github.users :only [search]])
    (def auth {:username "Raynes" :password "asifimgoingtotellyou"})
    (search auth "Raynes")

It's worth it to note that authentication is not enforced by clj-github. If you pass an empty map or nil rather than real credentials, and the API call requires it, clj-github will not complain. clj-github will not complain. Rather, the API will error out with "not authorizied" or something. No errors are ever thrown by clj-github. It simply returns the error message given by the API. The only way to know if something requires authentication is to look it up in the API docs. After everything is finished, I'll add notices detailing what requires authentication and what doesn't to docstrings.

API functions are sectioned into categories and placed in different files. Autodoc-generated documentation is [here](http://raynes.github.com/clj-github/). Have fun exploring. :)

## Installation

It's on clojars: http://clojars.org/clj-github

## License

Licensed under the EPL. You can find a copy of this license at the root of this directory.
