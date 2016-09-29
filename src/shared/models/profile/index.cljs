(ns shared.models.profile.index)

(defrecord Profile [])

(defn create [raw-profile]
  (-> raw-profile
      map->Profile))
