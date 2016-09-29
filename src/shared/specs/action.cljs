(ns shared.specs.action
  (:require [cljs.spec :as spec]
            [shared.specs.viewmodel :as viewmodel]
            [shared.specs.course :as course]
            [shared.specs.resource :as resource]
            [shared.specs.helpers :as helpers]
            [shared.specs.base :as base]
            [shared.specs.bookmark :as bookmark]
            [shared.specs.checkpoint :as checkpoint]
            [shared.specs.user :as user]))

(spec/def ::app-modes base/valid-modes)
(spec/def ::action-types base/valid-actions)

(defmulti  action-spec (fn [[action-type _ :as action]] action-type))

(defmethod action-spec :create [_]
  (spec/tuple ::action-types (spec/or :new-user #{:new-user}
                                      :profile ::user/profile )))

(defmethod action-spec :update [_]
  (spec/tuple ::action-types (spec/or :viewmodel ::viewmodel/viewmodel
<<<<<<< HEAD
                                      :checkpoint ::checkpoint/checkpoint)))
=======
                                    :checkpoint ::checkpoint/checkpoint)))
>>>>>>> 48b5c34594ec199c5cce1f87419da8ce98b5fd6f

(defmethod action-spec :save [_]
  (spec/tuple ::action-types (spec/or :profile ::user/profile)))


(defmethod action-spec :sign-in [_]
  (spec/tuple ::action-types))

(defmethod action-spec :sign-out [_]
  (spec/tuple ::action-types))

(defmethod action-spec :go [_]
  (spec/tuple ::action-types (spec/or :home #{:home})))

(defmethod action-spec :switch-to [_]
  (spec/tuple ::action-types (spec/or :app-mode ::app-modes)))


(defmethod action-spec :fork [_]
  (spec/tuple ::action-types (spec/or :course      ::course/course)))


(defmethod action-spec :add [_]
  (spec/tuple ::action-types (spec/or  :credentials ::user/credentials
                                       :profile     ::user/profile
<<<<<<< HEAD
                                       :course      ::course/course
                                       :courses     (spec/* ::course/course)
                                       :resources   ::resource/resources)))
=======
                                       :resources   ::resource/resources
                                       :course      ::course/course
                                       :courses     (spec/* ::course/course))))

>>>>>>> 48b5c34594ec199c5cce1f87419da8ce98b5fd6f

;; server

(defmethod action-spec :put [_]
  (spec/tuple ::action-types (spec/or :bookmarks  (spec/* ::bookmark/bookmark))))

(spec/def ::action (spec/multi-spec action-spec ::action-type))
