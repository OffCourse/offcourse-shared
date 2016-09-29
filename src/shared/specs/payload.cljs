(ns shared.specs.payload
  (:require [cljs.spec :as spec]
            [shared.specs.course :as course]
            [shared.specs.appstate :as appstate]
            [shared.specs.viewmodel :as viewmodel]
            [shared.specs.resource :as resource]
            [shared.specs.checkpoint :as checkpoint]
            [shared.specs.base :as base]
            [shared.specs.user :as user]
            [shared.specs.bookmark :as bookmark]
            [shared.specs.route-params :as route-params]))

(spec/def ::new-checkpoint (spec/keys :req-un [::checkpoint/task
                                           ::checkpoint/resource-url]))

(spec/def ::checkpoints (spec/* ::new-checkpoint))

(spec/def ::new-course (spec/keys :req-un [::course/goal
                                           ::course/curator
                                           ::course/organization
                                           ::checkpoints]))

(spec/def ::payload (spec/or :courses      (spec/* ::course/course)
                             :course       ::course/course
                             :new-course   ::new-course
                             :collection   ::base/collection
                             :new-courses  (spec/* ::new-course)
                             :viewmodel    ::viewmodel/viewmodel
                             :profile      ::user/user
                             :route-params ::route-params/route-params
                             :credentials  ::user/credentials
                             :checkpoint   ::checkpoint/checkpoint
                             :resources    (spec/* ::resource/resource)
                             :bookmarks    (spec/* ::bookmark/bookmark)
                             :bookmark     ::bookmark/bookmark
                             :appstate     ::appstate/appstate))
