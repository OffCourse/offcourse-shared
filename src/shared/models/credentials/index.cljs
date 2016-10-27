(ns shared.models.credentials.index
  (:require [shared.specs.core :as specs]))

(defrecord Credentials [auth-token])

(defn create [config]
  (-> config
      map->Credentials
      (with-meta {:spec ::specs/credentials})))
