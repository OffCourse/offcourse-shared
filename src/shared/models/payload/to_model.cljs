(ns shared.models.payload.to-model
  (:require [shared.models.course.index :as co]
            [shared.models.profile.index :as profile]
            [shared.models.resource.index :as rs]
            [shared.protocols.specced :as sp]))

(defmulti to-model (fn [payload] (sp/resolve payload)))

(defmethod to-model :profile [payload]
  (profile/create payload))

(defmethod to-model :resource [payload]
  (rs/create payload))

(defmethod to-model :resources [payload]
  (map rs/create payload))

(defmethod to-model :course [payload]
  (co/create payload))

(defmethod to-model :courses [payload]
  (map co/create payload))
