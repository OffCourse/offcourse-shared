(ns shared.specs.user
  (:require [cljs.spec :as spec]))

(defn user-name-length [str]
  (>= (count str) 3))

(spec/def ::user-name (spec/and string? #(user-name-length %)))
(spec/def ::logged-in-at int?)
(spec/def ::profile (spec/keys :req-un [::user-name]))
(spec/def ::auth-token (spec/nilable string?))
(spec/def ::auth-profile map?)

(spec/def ::credentials (spec/keys :req-un [::auth-token ::auth-profile]))

(spec/def ::user (spec/keys :req-un [::auth-token ::logged-in-at]
                            :opt-un [::user-name]))
