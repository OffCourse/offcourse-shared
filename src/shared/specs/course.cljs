(ns shared.specs.course
  (:require [cljs.spec :as spec]
            [shared.specs.checkpoint :as checkpoint]
            [shared.specs.base :as base]
            [shared.protocols.loggable :as log]))

(defn goal-length [str] (>= (count str) 8))

(spec/def ::course-id string?)

(spec/def ::goal (spec/and string? #(goal-length %)))

(spec/def ::version (spec/* int?))
(spec/def ::revision int?)
(spec/def ::forked-from (spec/or :course-id ::course-id :root nil))
(spec/def ::forks (spec/* ::course-id))

(spec/def ::checkpoints ::checkpoint/checkpoints)

(spec/def ::course (spec/keys :req-un [::course-id
                                       ::base-id
                                       ::base/curator
                                       ::base/flags
                                       ::goal
                                       ::version
                                       ::revision
                                       ::checkpoints]
                              :opt-un [::forks
                                       ::course-id
                                       ::forked-from]))

(spec/def ::courses (spec/* ::course))
