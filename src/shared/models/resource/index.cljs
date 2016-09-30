(ns shared.models.resource.index
  (:require [shared.specs.core :as specs]
            [shared.protocols.specced :refer [Specced]]
            [shared.protocols.convertible :refer [Convertible]]
            [shared.protocols.loggable :as log]
            [shared.models.query.index :as query]))

(defrecord Resource []
  Convertible
  (-to-query [{:keys [resource-url]}] (query/create {:resource-url resource-url}))
  Specced
  (-resolve [this] :resources))

(defn create [raw-resource]
  (-> raw-resource
      map->Resource
      (with-meta {:spec ::specs/resource})))
