(ns shared.specs.raw
  (:require [cljs.spec :as spec]
            [shared.specs.resource]
            [shared.specs.checkpoint]
            [shared.specs.course]
            [shared.protocols.loggable :as log]))

(spec/def ::checkpoint  (spec/keys :req-un [:checkpoint/task :resource/resource-url]))
(spec/def ::checkpoints (spec/coll-of ::checkpoint))

(spec/def :raw/new-course (spec/keys :req-un [:course/curator
                                              :course/goal
                                              ::checkpoints]))
