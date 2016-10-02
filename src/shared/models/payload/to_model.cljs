(ns shared.models.payload.to-model
  (:require [shared.models.course.index :as co]
            [shared.models.resource.index :as rs]
            [shared.protocols.specced :as sp]
            [shared.models.profile.index :as profile]
            [shared.protocols.loggable :as log]))

(defmulti to-model (fn [payload] (sp/resolve payload)))

(defmethod to-model :profile [payload]
  (profile/create payload))

(defmethod to-model :resources [payload]
  (map rs/create payload))

(defmethod to-model :courses [payload]
  (map co/create payload))

(defmethod to-model :new-courses [payload]
  (map co/initialize payload))

(defmethod to-model :new-course [payload]
  (co/initialize payload))

(defmethod to-model :course [payload]
  (co/create (dissoc payload :course-slug)))
