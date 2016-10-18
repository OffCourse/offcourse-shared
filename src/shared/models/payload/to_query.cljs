(ns shared.models.payload.to-query
  (:require [shared.protocols.specced :as sp]
            [shared.models.query.index :as query]))

(defmulti to-query (fn [payload] (sp/resolve payload)))

(defmethod to-query :bookmarks [bookmarks]
  (->> bookmarks
       (map #(select-keys % [:resource-url]))
       query/create))
