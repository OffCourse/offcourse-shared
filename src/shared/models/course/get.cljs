(ns shared.models.course.get
  (:refer-clojure :exclude [get])
  (:require [shared.specs.core :as specs]
            [shared.protocols.queryable :refer [Queryable]]
            [clojure.set :as set]
            [shared.protocols.specced :as sp]
            [cuerdas.core :as str]
            [shared.models.appstate.paths :as paths]
            [shared.protocols.loggable :as log])
  (:require-macros [com.rpl.specter.macros :refer [select-first]]))

(defmulti get (fn [_ query] (sp/resolve query)))

(defmethod get :tags [course _]
  (->> course
       :checkpoints
       (map :tags)
       (apply set/union)
       (into #{})))

(defmethod get :urls [course _]
  (->> course
       :checkpoints
       (map :url)
       (into #{})))

(defmethod get :checkpoint [{:keys [checkpoints]} query]
  (select-first (paths/checkpoint query) checkpoints))
