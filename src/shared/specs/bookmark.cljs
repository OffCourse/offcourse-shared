(ns shared.specs.bookmark
  (:require [shared.specs.resource :as resource]
            [shared.specs.course :as course]
            [cljs.spec :as spec]
            [shared.specs.user :as user]
            [shared.specs.base :as base]))


(spec/def ::bookmark (spec/keys :req-un [::resource/resource-url
                                         ::base/timestamp
                                         ::course/curator]
                                :opt-un [::course/course-id]))
