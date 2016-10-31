(ns shared.specs.user
  (:require [shared.specs.base :as base]
            [shared.specs.auth :as auth]
            [cljs.spec :as spec]))

(spec/def ::raw-user (spec/keys :req-un  [::base/user-name ::auth/profile]))
