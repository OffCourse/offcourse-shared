(ns shared.protocols.actionable
  (:require [shared.models.action.index :as action]))

(defprotocol Actionable
  "The Actionable protocol allows offcourse components, models, and adapters to
  handle and respond to offcourse events"
  (-request [this action])
  (-perform [this action] [this resource-name action]))

(defn request
  "performs an asynchronize action with (possible) side-effects"
  ([this action] (-request this (action/create action))))

(defn perform
  "exectutes an synchronize action with (possible) side-effects"
  ([this action] (-perform this (action/create action)))
  ([this resource-name action] (-perform this resource-name (action/create action))))
