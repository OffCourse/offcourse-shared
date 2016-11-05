(ns shared.specs.payload
  (:require [cljs.spec :as spec]
            [shared.specs.course :as course]
            [shared.specs.appstate :as appstate]
            [shared.specs.resource :as resource]
            [shared.specs.checkpoint :as checkpoint]
            [shared.specs.github :as github]
            [shared.specs.route :as route]
            [shared.specs.aws :as aws]
            [shared.specs.raw :as raw]
            [shared.specs.bookmark :as bookmark]
            [shared.specs.auth :as auth]
            [shared.specs.identity :as identity]
            [shared.specs.profile :as profile]))

(spec/def ::payload (spec/or :route           ::route/route
                             :resource        ::resource/resource
                             :appstate        ::appstate/appstate
                             :raw-user        ::raw/user
                             :raw-course      ::raw/course
                             :credentials     ::auth/credentials
                             :raw-courses     (spec/coll-of ::raw/course)
                             :courses         (spec/coll-of ::course/course)
                             :raw-profiles    (spec/coll-of ::auth/auth-profile)
                             :raw-users       (spec/coll-of ::raw/user)
                             :bookmarks       (spec/coll-of ::bookmark/bookmark)
                             :resources       (spec/coll-of ::resource/resource)
                             :github-courses  (spec/coll-of ::github/course)
                             :portraits       (spec/coll-of ::profile/portrait)
                             :dynamodb-events (spec/coll-of ::aws/dynamodb-event)))
