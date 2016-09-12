(ns shared.specs.action
  (:require [cljs.spec :as spec]
            [shared.specs.viewmodel :as viewmodel]
            [shared.specs.course :as course]
            [shared.specs.helpers :as helpers]
            [shared.specs.base :as base]))

(spec/def ::action-payload (spec/nilable (spec/or :viewmodel    ::viewmodel/viewmodel
                                                  :credentials  ::base/credentials
                                                  :profile      ::base/profile
                                                  :courses      (spec/* ::course/course)
                                                  :course       ::course/course)))


(def action-types #{:update :sign-in :sign-out :add})

(spec/def ::action (spec/cat :action-type (spec/+ action-types)
                             :action-payload (spec/? ::action-payload)))
