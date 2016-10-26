(ns shared.specs.raw
  (:require [cljs.spec :as spec]
            [shared.specs.checkpoint :as checkpoint]
            [shared.specs.course :as course]
            [shared.specs.auth :as auth]
            [shared.specs.resource :as resource]
            [shared.specs.base :as base]))

(spec/def ::checkpoint  (spec/keys :req-un [::checkpoint/task ::resource/resource-url]))
(spec/def ::checkpoints (spec/coll-of ::checkpoint))
(spec/def ::course      (spec/keys :req-un [::course/curator ::course/goal ::checkpoints]))
(spec/def ::user        (spec/keys :req-un [::base/user-name]))
