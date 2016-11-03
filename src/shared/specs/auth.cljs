(ns shared.specs.auth
  (:require [cljs.spec :as spec]
            [shared.specs.base :as base]))

(spec/def ::picture string?)
(spec/def ::auth-token (spec/nilable string?))
(spec/def ::identities (spec/+ map?))
(spec/def ::auth-profile (spec/keys :req-un [::identities ::picture]))
(spec/def ::auth-id string?)
(spec/def ::credentials (spec/keys :req-un  [::auth-token]
                                   :opt-un  [::auth-profile]))
