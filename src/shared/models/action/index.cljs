(ns shared.models.action.index
  (:require [shared.protocols.specced :refer [Specced]]
            [cljs.spec :as spec]
            [shared.protocols.loggable :as log]))

(defn- override [action]
  (specify action
    Specced
    (-resolve [this]
      (let [[action-type action-payload] (spec/conform (:spec (meta this)) this)]
        [action-type (first action-payload)]))))

(defn create
  "creates a new action"
  [action]
  (-> action
      (with-meta (merge (meta action) {:spec :offcourse/action}))
      override))
