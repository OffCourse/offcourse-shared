(ns shared.specs.core
  (:require [cljs.spec :as spec]
            [shared.specs.event :as event]
            [shared.specs.base :as base]
            [shared.specs.query :as query]
            [shared.specs.action :as action]
            [shared.specs.payload :as payload]
            [shared.specs.viewmodel :as viewmodel]
            [shared.specs.appstate :as appstate]
            [shared.specs.course :as course]
            [shared.specs.resource :as resource]
            [shared.specs.helpers :as helpers]
            [shared.specs.user :as user]
            [shared.specs.route :as route]
            [shared.specs.bookmark :as bookmark]))

(spec/def ::query ::query/query)
(spec/def ::payload ::payload/payload)
(spec/def ::viewmodel ::viewmodel/viewmodel)

(spec/def ::event ::event/event)
(spec/def ::action ::action/action)

(spec/def ::appstate ::appstate/appstate)
(spec/def ::course ::course/course)
(spec/def ::resource ::resource/resource)
(spec/def ::bookmark ::bookmark/bookmark)
(spec/def ::route ::route/route)

(spec/def ::spec spec/spec?)
(spec/def ::meta (spec/keys :req-un [::spec]))
(spec/def ::user (spec/keys :req-un [::user/user-name]))

(spec/def ::single-or-multiple? (spec/or :single map?
                                         :multiple (spec/* map?)))

(spec/def ::credentials ::user/credentials)

(def action-types base/valid-actions)
