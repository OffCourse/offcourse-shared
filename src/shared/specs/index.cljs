(ns shared.specs.index
  (:require [cljs.spec :as spec]
            [shared.specs.base]
            [shared.specs.bookmark]
            [shared.specs.collection]
            [shared.specs.course]
            [shared.specs.checkpoint]
            [shared.specs.resource]
            [shared.specs.identity]
            [shared.specs.auth]
            [shared.specs.profile]))

(spec/def ::error any?)

(spec/def :offcourse/offcourse-id    string?)
(spec/def :offcourse/bookmark        :bookmark/valid)
(spec/def :offcourse/credentials     :auth/credentials)
(spec/def :offcourse/course          :course/valid)
(spec/def :offcourse/collection      :collection/valid)
(spec/def :offcourse/checkpoint      :checkpoint/valid)
(spec/def :offcourse/resource        :resource/valid)
(spec/def :offcourse/profile         :profile/valid)
(spec/def :offcourse/identity        :identity/valid)
(spec/def :offcourse/actions         :action/types)
(spec/def :offcourse/error           (spec/keys :req-un [::error]))

(spec/def :query/course              :course/query)
(spec/def :query/collection          :collection/query)
(spec/def :query/checkpoint          :checkpoint/query)
(spec/def :query/checkpoint          :checkpoint/query)
(spec/def :query/resource            :resource/query)
(spec/def :query/identity            :identity/query)
