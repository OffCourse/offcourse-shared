(ns shared.specs.query
  (:require [cljs.spec :as spec]
            [shared.specs.base :as base]
            [shared.specs.viewmodel :as viewmodel]
            [shared.specs.course :as course]
            [shared.specs.resource :as resource]
            [shared.specs.github :as github]
            [shared.specs.identity :as identity]
            [shared.specs.collection :as collection]
            [shared.specs.checkpoint :as checkpoint]
            [shared.specs.aws :as aws]
            [shared.models.profile.index :as profile]))

(spec/def ::tags #{:all})

(spec/def ::query (spec/or :collection     ::collection/query
                           :bucket-items   ::aws/bucket-items
                           :tags           ::tags
                           :github-repo    ::github/repo
                           :resource       ::resource/query
                           :viewmodel      ::viewmodel/viewmodel
                           :checkpoint     ::checkpoint/query
                           :course         ::course/query
                           :identity       ::identity/query
                           :profile        ::profile/profile
                           :resources      (spec/coll-of ::resource/query)
                           :github-courses (spec/coll-of ::github/course)
                           :github-repos   (spec/coll-of ::github/repo)))
