(ns shared.specs.resource
  (:require [shared.specs.base :as base]
            [cljs.spec :as spec]))

(spec/def ::content string?)
(spec/def ::resource-url ::base/url)
(spec/def ::resource (spec/keys :req-un [::resource-url
                                         ::content]))

(spec/def ::resources (spec/* ::resource))
