(ns shared.specs.viewmodel
  (:require [cljs.spec :as spec]
            [shared.specs.base :as base]))

(spec/def ::slug string?)
(spec/def ::course-slug ::slug)
(spec/def ::checkpoint-slug ::slug)

(spec/def :vm/collection (spec/keys :req-un [::base/collection-type ::base/collection-name]))
(spec/def :vm/course     (spec/keys :req-un [::course-slug ::base/curator]))
(spec/def :vm/checkpoint (spec/keys :req-un [::checkpoint-slug]))

(spec/def :new-course/course    nil?)

(spec/def ::user         map?)
(spec/def ::collection   (spec/keys :req-un [:vm/collection]))
(spec/def ::checkpoint   (spec/keys :req-un [:vm/course :vm/checkpoint]))
(spec/def ::course       (spec/keys :req-un [:vm/course]))
(spec/def ::new-course   (spec/keys :req-un [:new-course/course]))
(spec/def ::signup       (spec/keys :req-un [::user]))

(spec/def ::viewmodel    (spec/or :collection-view ::collection
                                  :checkpoint-view ::checkpoint
                                  :new-course-view ::new-course
                                  :signup-view     ::signup
                                  :course-view     ::course))
