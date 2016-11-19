(ns shared.paths.index
  (:require [com.rpl.specter :refer [ALL]]
            [clojure.string :as c-str]
            [shared.protocols.loggable :as log]
            [cuerdas.core :as str]))

(defn course [{:keys [course-id goal course-slug curator goal] :as query}]
  (if course-id
    [ALL #(= (-> % :course-id) course-id)]
    (let [course-slug (or course-slug (str/slugify goal))]
      [ALL #(and (= (-> % :goal str/slugify) course-slug)
                 (= (-> % :curator c-str/lower-case) curator))])))

(defn checkpoint [{:keys [course-id checkpoint-id checkpoint-slug] :as query}]
  [ALL #(= (-> % :task str/slugify) checkpoint-slug)])

(defn resource [{:keys [resource-url]}]
  [ALL #(= (:resource-url %) resource-url)])

