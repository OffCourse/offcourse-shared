(ns shared.specs.viewmodel
  (:require [cljs.spec :as spec]
            [shared.specs.collection :as collection]))


(spec/def :new-course/course    nil?)

(spec/def ::collection   (spec/keys :req-un [:collection/query]))
(spec/def ::checkpoint   (spec/keys :req-un [:course/query :checkpoint/query]))
(spec/def ::course       (spec/keys :req-un [:course/query]))
(spec/def ::new-course   (spec/keys :req-un [:new-course/course]))

(spec/def ::viewmodel    (spec/or :collection-view ::collection
                                  :checkpoint-view ::checkpoint
                                  :new-course-view ::new-course
                                  :course-view     ::course))
