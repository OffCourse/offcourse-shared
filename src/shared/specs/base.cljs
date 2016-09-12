(ns shared.specs.base
  (:require [cljs.spec :as spec]))

(defn user-name-length [str]
  (>= (count str) 3))

(spec/def ::user-name (spec/and string? #(user-name-length %)))
(spec/def ::logged-in-at int?)
(spec/def ::flags (spec/* string?))
(spec/def ::tags #{:all})
(spec/def ::urls #{:all})
(spec/def ::url string?)
(spec/def ::profile (spec/keys :req-un [::user-name]))
(spec/def ::collection-name string?)
(spec/def ::collection-type string?)
(spec/def ::course-slug string?)
(spec/def ::curator ::user-name)
(spec/def ::checkpoint-slug string?)
(spec/def ::checkpoint-slug string?)
(spec/def ::auth-token (spec/nilable string?))
(spec/def ::site-title string?)
(spec/def ::user (spec/keys :req-un [::auth-token ::logged-in-at]
                            :opt-un [::user-name]))
(spec/def ::credentials (spec/keys :req-un [::auth-token]))
