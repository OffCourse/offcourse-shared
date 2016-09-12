(ns shared.specs.viewmodel
  (:require [cljs.spec :as spec]
            [shared.specs.base :as base]))

(spec/def :vm/collection (spec/keys :req-un [::base/collection-type ::base/collection-name]))
(spec/def :vm/course     (spec/keys :req-un [::base/course-slug ::base/curator]))
(spec/def :vm/checkpoint (spec/keys :req-un [::base/checkpoint-slug]))
(spec/def ::new-user      map?)
(spec/def ::collection   (spec/keys :req-un [:vm/collection]))
(spec/def ::checkpoint   (spec/keys :req-un [:vm/course :vm/checkpoint]))
(spec/def ::course       (spec/keys :req-un [:vm/course]))
(spec/def ::signup       (spec/keys :req-un [::new-user]))

(spec/def ::viewmodel    (spec/or :collection-view ::collection
                                  :checkpoint-view ::checkpoint
                                  :signup-view     ::signup
                                  :course-view     ::course))
