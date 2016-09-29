(ns shared.specs.base
  (:require [cljs.spec :as spec]
            [shared.specs.user :as user]))

(spec/def ::flags (spec/* string?))
(spec/def ::tags #{:all})
(spec/def ::urls #{:all})
(spec/def ::url string?)
(spec/def ::collection-name string?)
(spec/def ::collection-type string?)
(spec/def ::curator ::user/user-name)
(spec/def ::site-title string?)

(spec/def ::collection (spec/keys :req-un [::collection-name ::collection-type]))

(spec/def ::slug string?)
(spec/def ::course-slug ::slug)
(spec/def ::checkpoint-slug ::slug)
(spec/def ::organization string?)

(def valid-actions #{:go :extract :update :put :switch-to
                     :sign-in :sign-out :save :add :fork :create})

(def valid-modes #{:view-mode :edit-mode})
