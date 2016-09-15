(ns shared.models.credentials.index
  (:require [shared.specs.core :as specs]))

(defrecord Credentials [auth-token])

(defn create [token profile]
  (-> {:auth-token token
       :auth-profile profile}
      map->Credentials
      (with-meta {:spec ::specs/credentials})))
