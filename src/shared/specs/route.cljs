(ns shared.specs.route
  (:require [cljs.spec :as spec]
            [shared.specs.base :as base]
            [shared.specs.course :as course]))


(spec/def ::organization (spec/nilable ::base/organization))

(spec/def ::collection (spec/keys :req-un [::base/collection-type
                                           ::base/collection-name]))

(spec/def ::course     (spec/keys :req-un [::base/curator ::base/course-slug]
                                  :opt-un [::organization]))

(spec/def ::checkpoint (spec/keys :req-un [::base/checkpoint-slug ::base/curator
                                           ::base/course-slug]
                                  :opt-un [::organization]))

(spec/def ::home-view nil?)
(spec/def ::signup-view nil?)

(spec/def ::new-course-view nil?)

(spec/def ::home (spec/keys :req-un [::home-view]))
(spec/def ::sign-up (spec/keys :req-un [::signup-view]))
(spec/def ::new-course (spec/keys :req-un [::new-course-view]))

(spec/def ::route (spec/or :home-view       ::home
                           :signup-view     ::sign-up
                           :new-course-view ::new-course
                           :collection-view ::collection
                           :checkpoint-view ::checkpoint
                           :course-view     ::course))
