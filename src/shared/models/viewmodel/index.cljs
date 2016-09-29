(ns shared.models.viewmodel.index
  (:require [shared.protocols.convertible :refer [Convertible]]
            [shared.protocols.queryable :refer [Queryable]]
            [shared.models.viewmodel.missing-data :as md-impl]
            [shared.models.viewmodel.to-url :refer [to-url]]
            [shared.specs.core :as specs]
            [shared.protocols.specced :as sp]
            [shared.protocols.loggable :as log]
            [cljs.spec :as spec]))


(defrecord Viewmodel []
  Queryable
  (-missing-data [this query] (md-impl/missing-data this query))
  Convertible
  (-to-url [this routes] (to-url this routes)))

(defn create [vm]
  (with-meta (map->Viewmodel vm) {:spec ::specs/viewmodel}))
