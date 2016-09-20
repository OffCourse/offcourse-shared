(ns shared.models.appstate.perform
  (:require [shared.models.query.index :as query]
            [shared.models.appstate.paths :as paths]
            [shared.models.course.index :as course]
            [com.rpl.specter :refer [ALL]]
            [shared.protocols.queryable :as qa]
            [shared.protocols.specced :as sp]
            [shared.models.user.index :as user]
            [shared.protocols.loggable :as log])
  (:require-macros [com.rpl.specter.macros :refer [select-first setval transform]]))

(defn- add [store item]
  (if-not (qa/get store (query/create item))
    (update-in store [(sp/resolve item)] #(conj % item))
    store))

(defmulti perform (fn [as action] (sp/resolve action)))

(defmethod perform [:update :viewmodel] [state [_ viewmodel]]
  (-> state (assoc :viewmodel viewmodel)))

(defmethod perform [:sign-out nil] [state [_ payload]]
  (dissoc state :user))

(defmethod perform [:add :credentials] [state [_ payload]]
  (assoc-in state [:user] (user/create payload)))

(defmethod perform [:add :profile] [state [_ payload]]
  (update-in state [:user] #(merge %1 payload)))

(defmethod perform [:add :courses] [store [_ courses]]
  (reduce add store courses))

(defmethod perform [:add :resources] [store [_ resources]]
  (reduce add store resources))

(defmethod perform [:fork :course] [{:keys [courses] :as store} [_ course]]
  (let [{:keys [course-id] :as fork} (course/fork course store)
        courses (transform [(paths/course course) :forks] #(conj % course-id) courses)]
    (-> store
        (assoc-in [:courses] courses)
        (add fork))))

(defmethod perform [:update :checkpoint] [store [_ checkpoint]]
  (let [course-id (:course-id (meta checkpoint))
        checkpoint-id (:checkpoint-id checkpoint)
        checkpoint (assoc checkpoint :course-id course-id)]
    (setval [:courses ALL #(= (:course-id %) course-id)
             :checkpoints ALL #(= (:checkpoint-id %) checkpoint-id)]  checkpoint store)))

(defmethod perform [:add :course] [store [_ course]]
  (add store course))
