(ns shared.models.route-params.index
  (:require [shared.protocols.loggable :as log]
            [shared.protocols.convertible :refer [Convertible]]
            [shared.specs.core :as specs]
            [shared.models.route-params.to-viewmodel :as tvm-impl]))

(defn- override [params]
  (specify params
    Convertible
    (-to-viewmodel [params] (tvm-impl/to-viewmodel params))))


(defmulti create (fn [handler params]
                   (if (= handler (or :home-view :signup-view))
                     :handler
                     :params)))

(defmethod create :handler [handler _]
  (-> {handler nil}
      (with-meta {:spec ::specs/route-params})
      override))

(defmethod create :params [_ params]
  (-> params
      (with-meta {:spec ::specs/route-params})
      override))
