(ns shared.specs.payload
  (:require [cljs.spec :as spec]
            [shared.specs.course :as course]
            [shared.specs.appstate :as appstate]
            [shared.specs.viewmodel :as viewmodel]
            [shared.specs.resource :as resource]
            [shared.specs.checkpoint :as checkpoint]
            [shared.specs.base :as base]))

(spec/def ::resource-url ::base/url)
(spec/def ::bookmark (spec/keys :req-un [::resource-url]
                                :opt-un [::course/course-id]))

(spec/def ::checkpoint (spec/keys :req-un [::checkpoint/task
                                            ::checkpoint/resource-url]))

(spec/def ::checkpoints (spec/* ::checkpoint))

(spec/def ::new-course (spec/keys :req-un [::course/goal
                                           ::course/curator
                                           ::course/organization
                                           ::checkpoints]))

(spec/def ::payload (spec/or :courses     (spec/* ::course/course)
                             :course      ::course/course
                             :new-course  ::new-course
                             :new-courses (spec/* ::new-course)
                             :viewmodel   ::viewmodel/viewmodel
                             :profile     ::base/profile
                             :user        ::base/user
                             :credentials ::base/credentials
                             :checkpoint  ::checkpoint/checkpoint
                             :resources   (spec/* ::resource/resource)
                             :bookmarks   (spec/* ::bookmark)
                             :bookmark    ::bookmark
                             :appstate    ::appstate/appstate))
