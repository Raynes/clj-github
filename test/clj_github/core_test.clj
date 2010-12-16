(ns clj-github.core-test
  (:use [clj-github.core] :reload-all)
  (:use [clojure.test]))

(deftest test-slash-join
  (let [str-case ["a" "b" "c"]
        expected-str-case "a/b/c"
        num-case ["a" 2 "c"]
        expected-num-case "a/2/c"
        vec-case ["a" [1] "c"]
        expected-vec-case "a/[1]/c"]
    (is (= expected-str-case (apply slash-join str-case)))
    (is (= expected-num-case (apply slash-join num-case)))
    (is (= expected-vec-case (apply slash-join vec-case)))
    (is (= "" (apply slash-join [])))
    (is (= "" (slash-join nil)))
    (is (= "" (slash-join)))))



