(ns shared.specs.payload
  (:require [cljs.spec :as spec]
            [shared.specs.course :as course]
            [shared.specs.appstate :as appstate]
            [shared.specs.resource :as resource]
            [shared.specs.checkpoint :as checkpoint]
            [shared.specs.route-params :as route-params]))

(spec/def ::new-checkpoint (spec/keys :req-un [::checkpoint/task
                                               ::checkpoint/resource-url]))

(spec/def ::checkpoints (spec/* ::new-checkpoint))

(spec/def ::new-course (spec/keys :req-un [::course/goal
                                           ::course/curator
                                           ::course/organization
                                           ::checkpoints]))

(spec/def ::payload (spec/or :route-params ::route-params/route-params
                             :new-course   ::new-course
                             :new-courses  (spec/* ::new-course)
                             :resources    (spec/* ::resource/resource)
                             :appstate     ::appstate/appstate))
