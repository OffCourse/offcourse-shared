(ns shared.models.appstate.perform
  (:require [shared.models.query.index :as query]
            [shared.protocols.queryable :as qa]
            [shared.protocols.specced :as sp]
            [shared.models.user.index :as user]
            [shared.protocols.loggable :as log]))

(defn- add [store course]
  (if-not (qa/get store (query/create course))
    (update-in store [(sp/resolve course)] #(conj % course))
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

(defmethod perform [:add :course] [store [_ course]]
  (add store course))
