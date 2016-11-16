(ns shared.models.course.fork
  (:require [clojure.string :as clj-str]
            [shared.protocols.actionable :as ac]))


(defn create-fork [{:keys [base-id course-id repository] :as course} {:keys [user-name]}]
  (let [hash (last (clj-str/split base-id "::"))
        new-id (str repository "::" user-name "::" hash)]
    (assoc course
           :course-id new-id
           :revision 1
           :forked-from course-id
           :curator "charlotte")))

(defn update-original [course fork]
  (-> course
      (ac/perform [:add fork])
      (ac/perform [:update :revision])))

(defn fork [{:keys [base-id curator course-id repository goal] :as course} {:keys [user-name]}]
  (when (not= user-name curator)
    (let [fork  (create-fork course user-name)
          original (update-original course fork)]
      {:original original :fork fork})))
