(ns shared.models.course.actionable
  (:require [clojure.string :as clj-str]
            [shared.protocols.specced :as sp]
            [com.rpl.specter :refer [ALL]]
            [shared.models.course.fork :as fork]
            [shared.protocols.loggable :as log]
            [shared.models.checkpoint.index :as checkpoint])
  (:require-macros [com.rpl.specter.macros :refer [setval]]))

(defmulti perform (fn [course action] (sp/resolve action)))

(defmethod perform [:fork :identity] [course [_  identity]]
  (fork/fork course identity))

(defmethod perform [:update :checkpoint] [course [_ {:keys [checkpoint-id] :as checkpoint}]]
  (setval [:checkpoints ALL #(= (:checkpoint-id %) checkpoint-id)] checkpoint course))

(defmethod perform [:remove :checkpoint] [{:keys [checkpoints] :as course}
                                          [_ {:keys [checkpoint-id] :as checkpoint}]]
  (let [checkpoints (filter #(not= (-> % :checkpoint-id) checkpoint-id) checkpoints)]
    (assoc course :checkpoints checkpoints)))

(defmethod perform [:add :new-checkpoint] [{:keys [checkpoints] :as course} _]
  (let [max-id (or (apply max (map #(:checkpoint-id %) checkpoints)) 0)
        checkpoint (checkpoint/create {:checkpoint-id (inc max-id)})
        checkpoints (concat checkpoints [checkpoint])]
    (assoc course :checkpoints checkpoints)))

(defmethod perform [:add :course] [course [_ {:keys [course-id]}]]
  (update course :forks conj course-id))

(defmethod perform [:update :revision] [course _]
  (update course :revision inc))

(defmethod perform [:index :checkpoints] [{:keys [checkpoints] :as course} _]
  (let [checkpoints (map-indexed #(assoc %2 :checkpoint-id %1) checkpoints)]
    (assoc course :checkpoints checkpoints)))

(defmethod perform [:add :meta] [course _]
  (assoc course :version [0 0 1] :revision 1))

(defmethod perform [:add :id] [{:keys [repository curator] :as course} _]
  (let [hash (hash course)
        id (str repository "::" curator "::" hash)]
    (assoc course :base-id id :course-id id)))
