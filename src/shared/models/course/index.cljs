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
  (-to-bookmarks [{:keys [course-id curator checkpoints] :as this}]
    (map (fn [{:keys [resource-url checkpoint-id]}] {:resource-url resource-url
                                                     :curator curator
                                                     :timestamp (.now js/Date)
                                                     :source {:checkpoint-id checkpoint-id
                                                              :course-id course-id}}) checkpoints))
  (-to-query [{:keys [course-id curator goal]}] (query/create {:curator curator
                                                               :course-id course-id
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
                         (map #(assoc % :tags []))
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

(defn fork [{:keys [base-id course-id organization goal] :as course} {:keys [user]}]
  (let [hash (last (clj-str/split base-id "::"))
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
  (assoc course :curator (clj-str/lower-case curator)))

(defn initialize [raw-course]
  (->> raw-course
       create
       normalize-user
       order-checkpoints
       add-id
       add-meta))
