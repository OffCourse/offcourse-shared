(ns shared.models.course.index
  (:refer-clojure :exclude [get -reset remove])
  (:require [shared.specs.core :as specs]
            [shared.protocols.queryable :refer [Queryable]]
            [com.rpl.specter :refer [ALL]]
            [shared.protocols.convertible :refer [Convertible]]
            [shared.models.course.get :as get-impl]
            [shared.models.course.missing-data :as md-impl]
            [shared.models.checkpoint.index :as checkpoint]
            [shared.protocols.specced :refer [Specced]]
            [cljs.spec :as spec]
            [shared.protocols.loggable :as log]
            [clojure.string :as clj-str]
            [shared.models.query.index :as query]
            [cuerdas.core :as str])
  (:require-macros [com.rpl.specter.macros :refer [setval]]))

(defrecord Course []
  Queryable
  (-get [this query] (get-impl/get this query))
  (-missing-data [this query] (md-impl/missing-data this query))
  Convertible
  (-to-bookmarks [{:keys [course-id revision organization curator checkpoints] :as this}]
    (map (fn [{:keys [resource-url checkpoint-id]}] {:resource-url resource-url
                                                     :curator      curator
                                                     :timestamp    (.now js/Date)
                                                     :offcourse-id (str course-id "::" revision "::" checkpoint-id)})
         checkpoints))
  (-to-query [{:keys [course-id curator goal]}] (query/create {:curator     curator
                                                               :course-id   course-id
                                                               :course-slug (str/slugify goal)}))
  Specced
  (-resolve [this] :courses))

(defn create-checkpoint [{:keys [checkpoints] :as course}]
  (let [max-id (or (apply max (map #(:checkpoint-id %) checkpoints)) 0)
        checkpoint (checkpoint/create {:checkpoint-id (inc max-id)})
        checkpoints (conj checkpoints checkpoint)]
    (assoc course :checkpoints checkpoints)))

(defn add-checkpoints [{:keys [checkpoints] :as course}]
  (let [checkpoints (->> checkpoints
                         (map checkpoint/create))]
    (assoc course :checkpoints checkpoints)))

(defn order-checkpoints [{:keys [checkpoints] :as course}]
  (let [checkpoints (map-indexed #(assoc %2 :checkpoint-id %1) checkpoints)]
    (assoc course :checkpoints checkpoints)))

(defn remove-checkpoint [{:keys [checkpoints] :as course} {:keys [checkpoint-id] :as checkpoint}]
  (let [checkpoints (filter #(not= (-> % :checkpoint-id) checkpoint-id) checkpoints)]
    (assoc course :checkpoints checkpoints)))

(defn update-checkpoint [{:keys [checkpoints] :as course} {:keys [checkpoint-id] :as checkpoint}]
  (setval [:checkpoints ALL #(= (:checkpoint-id %) checkpoint-id)] checkpoint course))

(defn fork [{:keys [base-id curator course-id repository goal] :as course} {:keys [user-name]}]
  (when (not= user-name curator)
    (let [hash (last (clj-str/split base-id "::"))
          new-id (str repository "::" user-name "::" hash)
          fork   (assoc course
                        :course-id new-id
                        :revision 1
                        :forked-from course-id
                        :curator user-name)
          original (-> course
                       (update :forks conj (:course-id fork))
                       (update :revision inc))]
      {:original original :fork fork})))

(defn create [raw-course]
  (-> raw-course
      map->Course
      add-checkpoints
      (with-meta {:spec ::specs/course})))

(defn add-id [{:keys [repository curator] :as course}]
  (let [hash (hash course)
        id (str repository "::" curator "::" hash)]
    (assoc course
           :base-id id
           :course-id id)))

(defn add-meta [course]
  (assoc course
         :version [0 0 1]
         :revision 1))

(defn normalize-user [course user-name]
  (assoc course :curator (clj-str/lower-case user-name)))

(defn initialize [raw-course user-name]
  (-> raw-course
      create
      (normalize-user user-name)
      order-checkpoints
      add-id
      add-meta))
