(ns shared.specs.index
  (:require [cljs.spec :as spec]
            [shared.specs.base]
            [shared.specs.error]
            [shared.specs.query]
            [shared.specs.bookmark]
            [shared.specs.collection]
            [shared.specs.action]
            [shared.specs.course]
            [shared.specs.checkpoint]
            [shared.specs.resource]
            [shared.specs.identity]
            [shared.specs.auth]
            [shared.specs.raw]
            [shared.specs.profile]))

(spec/def :offcourse/bookmark        :bookmark/valid)
(spec/def :offcourse/credentials     :auth/credentials)
(spec/def :offcourse/course          :course/valid)
(spec/def :offcourse/new-course      :raw/new-course)
(spec/def :offcourse/collection      :collection/valid)
(spec/def :offcourse/checkpoint      :checkpoint/valid)
(spec/def :offcourse/resource        :resource/valid)
(spec/def :offcourse/profile         :profile/valid)
(spec/def :offcourse/portrait        :profile/portrait)
(spec/def :offcourse/identity        :identity/valid)
(spec/def :offcourse/actions         :action/types)
(spec/def :offcourse/error           :error/error)
