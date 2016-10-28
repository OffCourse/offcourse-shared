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
            [shared.specs.identity :as identity]))

(spec/def ::payload (spec/or :route           ::route/route
                             :raw-course      ::raw/course
                             :bookmark        ::bookmark/bookmark
                             :resource        ::resource/resource
                             :appstate        ::appstate/appstate
                             :identity        ::identity/identity
                             :credentials     ::auth/credentials
                             :raw-courses     (spec/coll-of ::raw/course)
                             :courses         (spec/coll-of ::course/course)
                             :raw-profiles    (spec/coll-of ::auth/auth-profile)
                             :raw-users       (spec/coll-of ::raw/user)
                             :bookmarks       (spec/coll-of ::bookmark/bookmark)
                             :resources       (spec/coll-of ::resource/resource)
                             :github-courses  (spec/coll-of ::github/course)
                             :s3-keys         (spec/coll-of int?)
                             :dynamodb-events (spec/coll-of ::aws/dynamodb-event)))
