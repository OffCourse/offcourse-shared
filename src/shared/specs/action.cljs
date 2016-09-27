(ns shared.specs.action
  (:require [cljs.spec :as spec]
            [shared.specs.viewmodel :as viewmodel]
            [shared.specs.course :as course]
            [shared.specs.resource :as resource]
            [shared.specs.helpers :as helpers]
            [shared.specs.base :as base]
            [shared.specs.checkpoint :as checkpoint]))

(spec/def ::user (spec/keys :req-un [::base/user-name]))

(spec/def ::resource-url ::base/url)
(spec/def ::bookmark (spec/keys :req-un [::resource-url]
                                :opt-un [::course/course-id]))

(spec/def ::action-payload (spec/nilable (spec/or :viewmodel    ::viewmodel/viewmodel
                                                  :credentials  ::base/credentials
                                                  :profile      ::base/profile
                                                  :checkpoint   ::checkpoint/checkpoint
                                                  :courses      (spec/* ::course/course)
                                                  :resources    ::resource/resources
                                                  :bookmarks    (spec/* ::bookmark)
                                                  :bookmark     ::bookmark
                                                  :course       ::course/course
                                                  :app-mode    #{:view-mode :edit-mode}
                                                  :home         #{:home}
                                                  :new-user     #{:new-user})))

(def action-types #{:go :extract :update :put :switch-to :sign-in :sign-out :save :add :fork :create})

(spec/def ::action (spec/cat :action-type (spec/+ action-types)
                             :action-payload (spec/? ::action-payload)))
