(ns shared.models.course.convertible
  (:require [shared.models.query.index :as query]
            [cuerdas.core :as str]
            [shared.models.bookmark.index :as bookmark]))

(defn to-bookmark [{:keys [resource-url checkpoint-id]} {:keys [curator revision course-id]}]
  (bookmark/create {:resource-url resource-url
                    :curator      curator
                    :timestamp    (.now js/Date)
                    :offcourse-id (str course-id "::" revision "::" checkpoint-id)}))

(defn to-bookmarks [{:keys [checkpoints] :as course}]
  (map #(to-bookmark %1 course) checkpoints))

(defn to-query [{:keys [course-id curator goal]}]
  (query/create {:curator     curator
                 :course-id   course-id
                 :course-slug (str/slugify goal)}))
