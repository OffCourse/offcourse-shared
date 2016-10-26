(ns shared.specs.base
  (:require [cljs.spec :as spec]
            [shared.specs.helpers :as helpers]))

(spec/def ::flags (spec/* string?))
(spec/def ::tags (spec/* string?))
(spec/def ::url string?)

(spec/def ::user-name (spec/and string? #(helpers/min-length % 3)))
(spec/def ::timestamp int?)

(spec/def ::slug string?)
(spec/def ::organization string?)

(def valid-modes #{:view-mode :edit-mode})
