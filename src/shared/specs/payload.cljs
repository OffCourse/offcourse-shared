(ns shared.specs.payload
  (:require [cljs.spec :as spec]
            [shared.specs.course :as course]
            [shared.specs.appstate :as appstate]
            [shared.specs.resource :as resource]
            [shared.specs.checkpoint :as checkpoint]
            [shared.specs.github :as github]
            [shared.specs.user :as user]
            [shared.specs.route :as route]
            [shared.specs.bookmark :as bookmark]))

(spec/def ::raw-checkpoint (spec/keys :req-un [::checkpoint/task
                                               ::checkpoint/resource-url]))

(spec/def ::checkpoints (spec/* ::raw-checkpoint))

(spec/def ::raw-course (spec/keys :req-un [::course/goal
                                           ::course/curator
                                           ::checkpoints]))

(spec/def ::payload (spec/or :route        ::route/route
                             :courses      (spec/coll-of ::course/course)
                             :raw-course   ::raw-course
                             :raw-courses  (spec/* ::raw-course)
                             :bookmarks    (spec/* ::bookmark/bookmark)
                             :bookmark     ::bookmark/bookmark
                             :resource     ::resource/resource
                             :resources    (spec/* ::resource/resource)
                             :github-courses (spec/* ::github/course)
                             :s3-keys      (spec/* int?)
                             :appstate     ::appstate/appstate
                             :credentials  ::user/credentials))
