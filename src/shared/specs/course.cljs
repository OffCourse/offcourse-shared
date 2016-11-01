(ns shared.specs.course
  (:require [cljs.spec :as spec]
            [shared.specs.checkpoint :as checkpoint]
            [shared.specs.base :as base]
            [shared.protocols.loggable :as log]
            [shared.specs.helpers :as helpers]))

(spec/def ::course-id string?)
(spec/def ::course-slug ::base/slug)
(spec/def ::goal (spec/and string? #(helpers/min-length % 4)))
(spec/def ::version (spec/* int?))
(spec/def ::revision int?)
(spec/def ::forked-from (spec/nilable ::course-id))
(spec/def ::forks (spec/* ::course-id))
(spec/def ::curator ::base/user-name)
(spec/def ::checkpoints (spec/* ::checkpoint/checkpoint))

(spec/def ::course (spec/keys :req-un [::course-id
                                       ::base-id
                                       ::base/organization
                                       ::curator
                                       ::goal
                                       ::version
                                       ::revision
                                       ::checkpoints]
                              :opt-un [::base/flags
                                       ::forks
                                       ::forked-from]))

(spec/def ::query       (spec/keys :req-un [::base/course-slug ::base/curator]))
