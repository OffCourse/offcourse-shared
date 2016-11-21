(ns shared.models.payload.index
  (:require [shared.models.payload.to-model :refer [to-model]]
            [shared.models.payload.to-query :refer [to-query]]
            [shared.protocols.convertible :refer [Convertible]]))

(defn- override [payload]
  (specify payload
    Convertible
    (-to-query [this] (to-query this))
    (-to-model [this] (to-model this))))

(defn create
  "creates a new action"
  [raw-payload]
  (-> raw-payload
      (with-meta {:spec :offcourse/payload})
      override))
