(ns shared.specs.action
  (:require [cljs.spec :as spec]))

(defmulti  action-spec (fn [[action-type _ :as action]] action-type))

(spec/def ::action (spec/multi-spec action-spec :action-type))

(def types #{:go :extract :verify :update :put :import :filter :split :switch-to
             :transform :sign-in :sign-up :sign-out :save :add :fork :create})
