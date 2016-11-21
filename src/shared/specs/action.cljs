(ns shared.specs.action
  (:require [cljs.spec :as spec]))

(defmulti action-spec (fn [[action-type _ :as action]] action-type))

(spec/def :action/valid (spec/multi-spec action-spec :action-type))

(spec/def :action/types #{:go :extract :verify :update :put :import :download :filter
                          :split :switch-to :transform :authenticate :sign-in :sign-up
                          :sign-out :save :add :index :remove :fork :create})
