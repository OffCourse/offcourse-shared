(ns shared.models.bookmark.index
  (:require [shared.specs.core :as specs]))

(defrecord Bookmark [])

(defn create [raw-bookmark]
  (-> raw-bookmark
      map->Bookmark
      (with-meta {:spec ::specs/bookmark})))
