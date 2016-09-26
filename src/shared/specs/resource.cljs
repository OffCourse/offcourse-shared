(ns shared.specs.resource
  (:require [shared.specs.base :as base]
            [cljs.spec :as spec]))

(spec/def ::resource-url ::base/url)

(spec/def ::content (spec/nilable string?))
(spec/def ::description (spec/nilable string?))
(spec/def ::type string?)

(spec/def ::resource (spec/keys :opt-un [::type ::description ::content]))
(spec/def ::resources (spec/* ::resource))
