(ns shared.specs.bookmark
  (:require [shared.specs.resource :as resource]
            [shared.specs.course :as course]
            [cljs.spec :as spec]
            [shared.specs.base :as base]
            [shared.specs.checkpoint :as checkpoint]))

(spec/def ::offcourse-id string?)
(spec/def ::provider-id string?)
(spec/def ::processed? boolean?)

(spec/def ::bookmark (spec/keys :req-un [::resource/resource-url
                                         ::base/timestamp
                                         ::course/curator]
                                :opt-un [::offcourse-id]))
