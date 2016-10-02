(ns shared.models.payload.index
  (:require [shared.protocols.convertible :refer [Convertible]]
            [shared.specs.core :as specs]
            [shared.protocols.specced :refer [Specced]]
            [shared.models.payload.to-model :refer [to-model]]
            [shared.protocols.loggable :as log]
            [cljs.spec :as spec]))

(defn- override [payload]
  (specify payload
    Convertible
    (-to-model [this] (to-model this))))

(defn create
  "creates a new action"
  [raw-payload]
  (-> raw-payload
      (with-meta {:spec ::specs/payload})
      override))
