(ns shared.models.user.index
  (:require [shared.specs.core :as specs]
            [shared.protocols.loggable :as log]))

(defrecord User [])

(defn create [{:keys [auth-profile] :as raw-user}]
  (let [credentials (select-keys raw-user [:auth-token])
        profile (select-keys auth-profile [:name :blog :email :location :company])
        {:keys [name blog]} profile]
    (-> credentials
        (merge profile)
        (dissoc :name :blog)
        (assoc :full-name name
               :website blog)
        (assoc :logged-in-at (.now js/Date))
        map->User
        (with-meta {} #_{:spec ::specs/user}))))
