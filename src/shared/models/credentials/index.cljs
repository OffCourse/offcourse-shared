(ns shared.models.credentials.index)

(defrecord Credentials [auth-token])

(defn create [config]
  (-> config
      map->Credentials
      (with-meta {:spec :offcourse/credentials})))
