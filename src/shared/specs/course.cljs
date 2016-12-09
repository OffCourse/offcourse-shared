(ns shared.specs.course
  (:require [cljs.spec :as spec]
            [shared.specs.base :as base]
            [shared.protocols.loggable :as log]
            [shared.specs.helpers :as helpers]))

(spec/def :course/curator     :base/user-name)
(spec/def :course/course-id   string?)
(spec/def :course/base-id     :course/course-id)
(spec/def :course/goal        (spec/and string? #(helpers/min-length % 4)))
(spec/def :course/version     (spec/tuple int? int? int?))
(spec/def :course/revision    int?)
(spec/def :course/checkpoints (spec/* :offcourse/checkpoint))
(spec/def :course/forked-from :course/course-id)
(spec/def :course/forks       (spec/coll-of :course/course-id))
(spec/def :course/course-slug :base/slug)

(spec/def :course/valid (spec/keys :req-un [:course/course-id
                                            :course/base-id
                                            :base/repository
                                            :course/curator
                                            :course/goal
                                            :course/version
                                            :course/revision
                                            :course/checkpoints]
                                   :opt-un [::base/flags
                                            :course/forks
                                            :course/forked-from]))

(spec/def :course/query       (spec/or :by-slug (spec/keys :req-un [:course/course-slug
                                                                    :course/curator])
                                       :by-id (spec/keys :req-un [:course/course-id])))
