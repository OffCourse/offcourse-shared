(ns shared.models.resource.index
  (:require [shared.specs.core :as specs]
            [shared.protocols.specced :refer [Specced]]
            [shared.protocols.loggable :as log]))

(defrecord Resource []
  Specced
  (-resolve [this] :resources))

(defn create [raw-resource]
  (-> raw-resource
      map->Resource
      (with-meta {:spec ::specs/resource})))
