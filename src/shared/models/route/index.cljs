(ns shared.models.route.index
  (:require [shared.protocols.loggable :as log]
            [shared.protocols.convertible :refer [Convertible]]
            [shared.specs.core :as specs]))

(defn- override [params]
  (specify params
    Convertible
    (to-url [this routes] (log/log "...."))))

(defmulti create (fn [handler params] (if params :params :handler)))

(defmethod create :handler [handler _]
  (-> {handler nil}
      (with-meta {:spec ::specs/route})
      override))

(defmethod create :params [_ params]
  (-> params
      (with-meta {:spec ::specs/route})
      override))
