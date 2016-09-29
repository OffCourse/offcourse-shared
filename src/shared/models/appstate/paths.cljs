(ns shared.models.appstate.paths
  (:require [com.rpl.specter :refer [ALL]]
            [cuerdas.core :as str]
            [shared.protocols.loggable :as log]))

(defn course [{:keys [course-id goal course-slug curator goal] :as course}]
  (let [course-slug (or course-slug (str/slugify goal))]
    [ALL #(and (= (-> % :goal str/slugify) course-slug)
               (= (:curator %) curator))]))

(defn checkpoint [{:keys [course-id checkpoint-id checkpoint-slug] :as query}]
  [ALL #(= (-> % :task str/slugify) checkpoint-slug)])

(defn resource [{:keys [resource-url]}]
  [ALL #(= (:resource-url %) resource-url)])
