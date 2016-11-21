(ns shared.models.course.get
  (:refer-clojure :exclude [get])
  (:require [clojure.set :as set]
            [shared.paths.index :as paths]
            [shared.protocols.specced :as sp])
  (:require-macros [com.rpl.specter.macros :refer [select-first]]))

(defmulti get (fn [_ query]
                (sp/resolve query)))

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
