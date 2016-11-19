(ns shared.specs.viewmodel
  (:require [cljs.spec :as spec]
            [shared.specs.collection :as collection]
            [shared.specs.course :as course]
            [shared.specs.checkpoint :as checkpoint]))

(spec/def :new-course/course    nil?)
(spec/def ::course     ::course/query)
(spec/def ::checkpoint ::checkpoint/query)
(spec/def ::collection ::collection/query)

(spec/def ::collection-view (spec/keys :req-un [::collection]))
(spec/def ::checkpoint-view (spec/keys :req-un [::course ::checkpoint]))
(spec/def ::course-view     (spec/keys :req-un [::course]))
(spec/def ::new-course-view (spec/keys :req-un [:new-course/course]))

(spec/def ::viewmodel    (spec/or :collection-view ::collection-view
                                  :checkpoint-view ::checkpoint-view
                                  :course-view     ::course-view
                                  :new-course-view ::new-course-view))
