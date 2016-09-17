(ns shared.specs.query
  (:require [cljs.spec :as spec]
            [shared.specs.base :as base]
            [shared.specs.appstate :as appstate]
            [shared.specs.viewmodel :as viewmodel]))

(spec/def ::course-id (spec/nilable string?))
(spec/def ::checkpoint-slug (spec/nilable ::base/checkpoint-slug))
(spec/def ::collection      (spec/keys :req-un [::base/collection-type ::base/collection-name]))
(spec/def ::course          (spec/keys :req-un [::base/course-slug ::base/curator]))
(spec/def ::checkpoint      (spec/keys :req-un [::checkpoint-slug]))
(spec/def ::tags            (spec/keys :req-un [::base/tags]))
(spec/def ::resource-url    ::base/url)

(spec/def ::resource (spec/keys :req-un [::resource-url ::course-id]))

(spec/def ::resources (spec/* ::resource))

(spec/def ::query (spec/or :collection ::collection
                           :tags ::tags
                           :user ::base/user
                           :resource ::resource
                           :resources ::resources
                           :viewmodel ::viewmodel/viewmodel
                           :checkpoint ::checkpoint
                           :course ::course))
