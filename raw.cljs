(ns backend-shared.specs.raw
  (:require [cljs.spec :as spec]
            [shared.specs.checkpoint :as checkpoint]
            [shared.specs.course :as course]
            [shared.specs.auth :as auth]
            [shared.specs.user :as user]
            [shared.specs.resource :as resource]))

(spec/def ::checkpoint  (spec/keys :req-un [::checkpoint/task ::resource/resource-url]))
(spec/def ::profile     (spec/keys :req-un [::user/user-name ::auth/identities ::auth/picture]))
(spec/def ::checkpoints (spec/coll-of ::checkpoint))
(spec/def ::course      (spec/keys :req-un [::course/curator ::course/goal ::checkpoints]))
