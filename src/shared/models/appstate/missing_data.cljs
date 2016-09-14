(ns shared.models.appstate.missing-data
  (:refer-clojure :exclude [get -reset remove])
  (:require [clojure.set :as set]
            [shared.models.query.index :as query]
            [shared.protocols.queryable :as qa]
            [cljs.spec :as spec]
            [shared.specs.core :as specs]
            [shared.protocols.loggable :as log]
            [shared.protocols.specced :as sp]))

;; query to vm
(defmulti missing-data (fn [state query]
                         (first (spec/conform ::specs/viewmodel query))))

(defmethod missing-data :collection-view [state viewmodel]
  (query/create (:collection viewmodel)))

(defmethod missing-data :course-view [state viewmodel]
  (let [course-query (-> viewmodel :course)
        course (qa/get state course-query)]
    (if course
      (qa/missing-data course state)
      (query/create course-query))))

(defmethod missing-data :checkpoint-view [state viewmodel]
  (let [course-query (-> viewmodel :course)
        course (qa/get state course-query)]
    (if course
      (qa/missing-data course state)
      (query/create course-query))))

(defmethod missing-data :default [state viewmodel] nil)
