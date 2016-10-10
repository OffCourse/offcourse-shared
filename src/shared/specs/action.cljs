(ns shared.specs.action
  (:require [cljs.spec :as spec]))

(defmulti  action-spec (fn [[action-type _ :as action]] action-type))

(spec/def ::action (spec/multi-spec action-spec :action-type))
