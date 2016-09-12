(ns shared.models.credentials.index
  (:require [shared.specs.core :as specs]))

(defrecord Credentials [auth-token])

(defn create [token]
  (-> {:auth-token token}
      map->Credentials
      (with-meta {:spec ::specs/credentials})))
