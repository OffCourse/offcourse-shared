(ns shared.models.course.missing-data
  (:require [shared.protocols.loggable :as log]))

(defn missing-data [{:keys [checkpoints]} {:keys [resources]}]
  (if-not resources
    {:urls (keep :resource-url checkpoints)}
    (log/log "check if urls are already there")))
