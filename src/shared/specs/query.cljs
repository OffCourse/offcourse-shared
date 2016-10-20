(ns shared.specs.query
  (:require [cljs.spec :as spec]
            [shared.specs.base :as base]
            [shared.specs.viewmodel :as viewmodel]
            [shared.specs.course :as course]
            [shared.specs.resource :as resource]
            [shared.specs.user :as user]
            [shared.specs.github :as github]))

(spec/def ::course          (spec/keys :req-un [::base/course-slug ::base/curator]))
(spec/def ::checkpoint      (spec/keys :req-un [::base/checkpoint-slug]))
(spec/def ::tags            (spec/keys :req-un [::base/tags]))
(spec/def ::resource        (spec/keys :req-un [::resource/resource-url]))

(spec/def ::keys            (spec/* int?))
(spec/def ::bucket-items    (spec/keys :req-un [::keys]))

(spec/def ::query (spec/or :collection     ::base/collection
                           :bucket-items   ::bucket-items
                           :auth-id        (spec/keys :req-un [::auth-id])
                           :tags           ::tags
                           :user           ::user/user
                           :github-courses (spec/* ::github/course)
                           :github-repos   (spec/* ::github/repo)
                           :github-repo    ::github/repo
                           :resource       ::resource
                           :resources      (spec/* ::resource)
                           :viewmodel      ::viewmodel/viewmodel
                           :checkpoint     ::checkpoint
                           :course         ::course))
