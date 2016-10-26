(ns shared.specs.bookmark
  (:require [shared.specs.resource :as resource]
            [shared.specs.course :as course]
            [cljs.spec :as spec]
            [shared.specs.base :as base]
            [shared.specs.checkpoint :as checkpoint]))

(spec/def ::source (spec/keys :req-un [::course/course-id ::checkpoint/checkpoint-id]))

(spec/def ::bookmark (spec/keys :req-un [::resource/resource-url
                                         ::base/timestamp
                                         ::course/curator
                                         ::source]))
