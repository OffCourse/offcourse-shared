(ns shared.specs.payload
  (:require [cljs.spec :as spec]
            [shared.specs.course :as course]
            [shared.specs.appstate :as appstate]
            [shared.specs.viewmodel :as viewmodel]
            [shared.specs.resource :as resource]
            [shared.specs.checkpoint :as checkpoint]
            [shared.specs.base :as base]))

(spec/def ::payload (spec/or :course      ::course/course
                             :new-course  ::course/new-course
                             :profile     ::base/profile
                             :user        ::base/user
                             :credentials ::base/credentials
                             :resource    ::resource/resource
                             :resources   ::resource/resources
                             :checkpoint  ::checkpoint/checkpoint
                             :courses     ::course/courses
                             :new-courses (spec/* ::course/new-course)
                             :appstate    ::appstate/appstate
                             :viewmodel   ::viewmodel/viewmodel))
