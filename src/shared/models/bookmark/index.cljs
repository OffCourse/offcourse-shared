(ns shared.models.bookmark.index
  (:require [shared.specs.core :as specs]
            [shared.protocols.convertible :refer [Convertible]]
            [shared.models.query.index :as query]))

(defrecord Bookmark []
  Convertible
  (-to-query [{:keys [resource-url]}] (query/create {:resource-url resource-url})))

(defn create [raw-bookmark]
  (-> raw-bookmark
      map->Bookmark
      (with-meta {:spec ::specs/bookmark})))
