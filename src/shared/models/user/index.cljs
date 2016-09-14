(ns shared.models.user.index
  (:require [shared.specs.core :as specs]))

(defrecord User [])

(defn create [raw-user]
  (-> raw-user
      (assoc :logged-in-at (.now js/Date))
      map->User
      (with-meta {:spec ::specs/user})))
