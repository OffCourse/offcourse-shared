(ns shared.models.course.index
  (:refer-clojure :exclude [get -reset remove])
  (:require [shared.specs.core :as specs]
            [shared.protocols.queryable :refer [Queryable]]
            [shared.models.course.get :as get-impl]
            [shared.models.course.missing-data :as md-impl]
            [shared.models.checkpoint.index :as checkpoint]
            [shared.protocols.specced :refer [Specced]]
            [cljs.spec :as spec]
            [shared.protocols.loggable :as log]
            [clojure.string :as str]))

(defrecord Course []
  Queryable
  (-get [this query] (get-impl/get this query))
  (-missing-data [this query] (md-impl/missing-data this query))
  Specced
  (-resolve [this] :courses))

(defn add-checkpoints [{:keys [checkpoints] :as course}]
  (let [checkpoints (map checkpoint/create checkpoints)]
    (assoc course :checkpoints checkpoints)))

(defn order-checkpoints [{:keys [checkpoints] :as course}]
  (let [checkpoints (map-indexed #(assoc %2 :checkpoint-id %1) checkpoints)]
    (assoc course :checkpoints checkpoints)))

(defn fork [{:keys [base-id course-id organization goal] :as course} {:keys [user]}]
  (let [hash (last (str/split base-id "::"))
        new-id (str organization "::" (:user-name user) "::" hash)]
    (assoc course
           :course-id new-id
           :forked-from course-id
           :curator (:user-name user))))

(defn create [raw-course]
  (-> raw-course
      map->Course
      add-checkpoints
      (with-meta {:spec ::specs/course})))

(defn add-id [{:keys [organization curator] :as course}]
  (let [hash (hash course)
        id (str organization "::" curator "::" hash)]
    (assoc course
           :base-id id
           :course-id id)))

(defn add-meta [course]
  (assoc course
         :flags ["featured"]
         :version [0 0 1]
         :revision 1
         :forks []
         :forked-from nil))

(defn normalize-user [{:keys [curator] :as course}]
  (assoc course :curator (str/lower-case curator)))

(defn initialize [raw-course]
  (->> raw-course
       create
       normalize-user
       order-checkpoints
       add-id
       add-meta))