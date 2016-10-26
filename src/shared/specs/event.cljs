(ns shared.specs.event
  (:require [cljs.spec :as spec]
            [shared.specs.payload :as payload]
            [shared.specs.query :as query]
            [shared.specs.action :as action]))

(spec/def ::event-type keyword?)

(spec/def ::action  ::action/action)
(spec/def ::payload ::payload/payload)
(spec/def ::query   ::query/query)

(defmulti  event-spec (fn [[event-type _ :as event]] event-type))

(defmethod event-spec :requested [_] (spec/tuple ::event-type ::action))
(defmethod event-spec :not-found [_] (spec/tuple ::event-type ::query))
(defmethod event-spec :refreshed [_] (spec/tuple ::event-type ::payload))
(defmethod event-spec :found     [_] (spec/tuple ::event-type ::payload))
(defmethod event-spec :granted   [_] (spec/tuple ::event-type ::payload))
(defmethod event-spec :revoked   [_] (spec/tuple ::event-type ::payload))
(defmethod event-spec :rendered  [_] (spec/tuple ::event-type nil?))
(defmethod event-spec :failed    [_] (spec/tuple ::event-type any?))

(spec/def ::event (spec/multi-spec event-spec ::event-type))
