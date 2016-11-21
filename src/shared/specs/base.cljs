(ns shared.specs.base
  (:require [cljs.spec :as spec]
            [shared.specs.helpers :as helpers]))

(spec/def :base/flags (spec/* string?))
(spec/def :base/tags (spec/* string?))
(spec/def :base/url string?)

(spec/def :base/user-name (spec/and string? #(helpers/min-length % 3)))
(spec/def :base/timestamp int?)

(spec/def :base/slug string?)
(spec/def :base/organization string?)
