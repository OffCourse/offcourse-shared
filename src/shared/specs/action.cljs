(ns shared.specs.action
  (:require [cljs.spec :as spec]
            [shared.specs.viewmodel :as viewmodel]
            [shared.specs.course :as course]
            [shared.specs.resource :as resource]
            [shared.specs.helpers :as helpers]
            [shared.specs.base :as base]))

(spec/def ::user (spec/keys :req-un [::base/user-name]))

(spec/def ::action-payload (spec/nilable (spec/or :viewmodel    ::viewmodel/viewmodel
                                                  :credentials  ::base/credentials
                                                  :profile      ::base/profile
                                                  :courses      (spec/* ::course/course)
                                                  :resources    ::resource/resources
                                                  :course       ::course/course
                                                  :home         #{:home}
                                                  :new-user     #{:new-user})))

(def action-types #{:go :update :sign-in :sign-out :save :add :fork :create})

(spec/def ::action (spec/cat :action-type (spec/+ action-types)
                             :action-payload (spec/? ::action-payload)))
