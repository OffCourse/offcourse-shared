(ns shared.specs.event
  (:require [cljs.spec :as spec]))

(spec/def ::event-type keyword?)

(defmulti  event-spec (fn [[event-type _ :as event]] event-type))
(spec/def ::event (spec/multi-spec event-spec ::event-type))
