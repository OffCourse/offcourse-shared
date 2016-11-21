(ns shared.specs.query
  (:require [cljs.spec :as spec]
            [shared.specs.base :as base]
            [shared.specs.course :as course]
            [shared.specs.resource :as resource]
            [shared.specs.github :as github]
            [shared.specs.identity :as identity]
            [shared.specs.collection :as collection]
            [shared.specs.checkpoint :as checkpoint]
            [shared.specs.aws :as aws]
            [shared.specs.profile :as profile]))

(spec/def ::tags #{:all})
(spec/def ::tags-query (spec/keys :req-un [::tags]))

(spec/def ::url string?)
(spec/def ::original_url string?)
(spec/def ::embedly (spec/keys :req-un [::url ::original_url]))

(spec/def ::table-name string?)
(spec/def ::item-key   map?)
(spec/def ::db-item    (spec/keys :req-un [::table-name ::item-key]))

(spec/def :es/query map?)
(spec/def ::es-query (spec/keys :req-un [:es/query]))

(spec/def ::query (spec/or :collection          ::collection/query
                           :bucket-items        ::aws/bucket-items
                           :db-item             ::db-item
                           :tags                ::tags-query
                           :github-repo         ::github/repo
                           :resource            ::resource/query
                           :checkpoint          ::checkpoint/query
                           :course              ::course/query
                           :identity            ::identity/query
                           :profile             ::profile/profile
                           :es-query            ::es-query
                           :embedly             (spec/coll-of ::embedly)
                           :resources           (spec/coll-of ::resource/query)
                           :github-courses      (spec/coll-of ::github/course)
                           :github-repos        (spec/coll-of ::github/repo)))
