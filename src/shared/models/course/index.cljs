(ns shared.models.course.index
  (:require [shared.models.checkpoint.index :as checkpoint]
            [shared.models.course.actionable :as ac-impl]
            [shared.models.course.convertible :as cv-impl]
            [shared.models.course.get :as get-impl]
            [shared.models.course.missing-data :as md-impl]
            [shared.protocols.actionable :as ac :refer [Actionable]]
            [shared.protocols.convertible :refer [Convertible]]
            [shared.protocols.queryable :refer [Queryable]]
            [shared.protocols.specced :refer [Specced]]
            [shared.specs.core :as specs]))

(defrecord Course []
  Queryable
  (-get [this query] (get-impl/get this query))
  (-missing-data [this query] (md-impl/missing-data this query))
  Actionable
  (-perform [this action] (ac-impl/perform this action))
  Convertible
  (-to-bookmarks [this] (cv-impl/to-bookmarks this))
  (-to-query [this] (cv-impl/to-query this))
  Specced
  (-resolve [this] :courses))

(defn create [raw-course]
  (-> raw-course
      (update :checkpoints #(map checkpoint/create %1))
      map->Course
      (with-meta {:spec ::specs/course})))

(defn initialize [raw-course]
  (-> raw-course
      create
      (ac/perform [:index :checkpoints])
      (ac/perform [:add :meta])
      (ac/perform [:add :id])))
