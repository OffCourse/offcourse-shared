(ns shared.specs.identity
  (:require [cljs.spec :as spec]
            [shared.specs.auth :as auth]
            [shared.specs.base :as base]))

(spec/def ::identity   (spec/keys :req-un [::base/user-name ::auth/auth-id]))
(spec/def ::query      (spec/keys :opt-un [::base/user-name ::auth/auth-id]))