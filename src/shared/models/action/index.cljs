(ns shared.models.action.index
  (:require [shared.protocols.specced :refer [Specced]]
            [shared.specs.core :as specs]
            [shared.specs.action :as as]
            [cljs.spec :as spec]
            [cljs.spec.test :as stest]
            [shared.protocols.loggable :as log]))

(spec/fdef create
           :args (spec/cat :action ::specs/action)
           :ret ::specs/action
           :fn #(spec/valid? ::specs/meta (-> %1 :ret :meta)))

(defn- override [action]
  (specify action
    Specced
    (-resolve [this]
      (log/log (.stringify js/JSON (clj->js (spec/explain ::as/action-payload (second this)))))
      (let [{:keys [action-type action-payload] :as action} (spec/conform (:spec (meta this)) this)]
        [(first action-type) (first action-payload)]))))

(defn create
  "creates a new action"
  [action]
  (-> action
      (with-meta {:spec ::specs/action})
      override))

#_(stest/instrument `create)
