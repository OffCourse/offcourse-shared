(ns shared.specs.payload
  (:require [cljs.spec :as spec]
            [shared.specs.course :as course]
            [shared.specs.appstate :as appstate]
            [shared.specs.resource :as resource]
            [shared.specs.checkpoint :as checkpoint]
            [shared.specs.user :as user]
            [shared.specs.route :as route]
            [shared.specs.bookmark :as bookmark]))

(spec/def ::new-checkpoint (spec/keys :req-un [::checkpoint/task
                                               ::checkpoint/resource-url]))

(spec/def ::checkpoints (spec/* ::new-checkpoint))

(spec/def ::new-course (spec/keys :req-un [::course/goal
                                           ::course/curator
                                           ::course/organization
                                           ::checkpoints]))

(spec/def ::payload (spec/or :route        ::route/route
                             :new-course   ::new-course
                             :new-courses  (spec/* ::new-course)
                             :resource     ::resource/resource
                             :resources    (spec/* ::resource/resource)
                             :bookmarks    (spec/* ::bookmark/bookmark)
                             :bookmark     ::bookmark/bookmark
                             :s3-keys      (spec/* int?)
                             :appstate     ::appstate/appstate
                             :credentials  ::user/credentials))
