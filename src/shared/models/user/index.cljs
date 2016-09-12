(ns shared.models.user.index)

(defrecord User [])

(defn create [raw-user]
  (-> raw-user
      (assoc :logged-in-at (.now js/Date))
      map->User))
