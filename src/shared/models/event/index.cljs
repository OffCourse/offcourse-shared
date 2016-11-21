(ns shared.models.event.index
  (:require [cljs.spec :as spec]
            [shared.protocols.convertible :as cv :refer [Convertible]]
            [shared.protocols.specced :refer [Specced]]
            [shared.protocols.loggable :as log]
            [shared.protocols.specced :as sp]))

(defn override [event]
  (specify event
    Convertible))

(defn create [[source type payload]]
  (-> [(keyword type) payload]
      (with-meta {:spec :offcourse/event
                  :source source
                  :credentials (-> payload meta :credentials)
                  :timestamp (.now js/Date)})
      override))
