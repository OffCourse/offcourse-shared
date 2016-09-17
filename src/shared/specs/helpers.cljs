(ns shared.specs.helpers
  (:require [cljs.spec :as spec]))

(defn tuple-spec [valid-types payload]
  (spec/tuple (into #{} valid-types) payload))

(defn one-or-many? [item]
  (first (spec/conform (spec/or :one map?
                          :many (spec/* map?)) item)))

(defn one? [item]
  (= (one-or-many? item) :one))

(defn many? [item]
  (= (one-or-many? item) :many))
