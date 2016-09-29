(ns shared.models.course.missing-data
  (:require [shared.protocols.loggable :as log]
            [clojure.set :as set]))

(defn create-url-set [coll]
  (->> coll
       (map :resource-url)
       (into #{})))

(defn missing-data [{:keys [course-id checkpoints]} {:keys [resources]}]
  (let [resources-urls (create-url-set resources)
        checkpoint-urls (create-url-set checkpoints)
        missing-urls (set/difference checkpoint-urls resources-urls)]
  (when-not (empty? missing-urls)
    (map (fn [url] {:resource-url url
                    :course-id course-id}) (into [] missing-urls)))))
