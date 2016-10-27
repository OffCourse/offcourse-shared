(ns shared.protocols.actionable
  (:require [shared.specs.core :as specs]
            [cljs.spec :as spec]
            [shared.models.action.index :as action]))

(defprotocol Actionable
  (-request [this action])
  (-perform [this action] [this resource-name action]))

(spec/fdef perform
           :args (spec/cat :datastore any?
                           :action ::specs/action))

(defn request
  "performs an asynchronize action with (possible) side-effects"
  ([this action] (-request this (action/create action))))

(defn perform
  "exectutes an synchronize action with (possible) side-effects"
  ([this action] (-perform this (action/create action)))
  ([this resource-name action] (-perform this resource-name (action/create action))))
