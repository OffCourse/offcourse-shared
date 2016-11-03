(ns shared.specs.resource
  (:require [shared.specs.base :as base]
            [cljs.spec :as spec]))

(spec/def ::resource-url ::base/url)

(spec/def ::content (spec/nilable string?))
(spec/def ::description (spec/nilable string?))
(spec/def ::resource-type string?)
(spec/def ::tags (spec/* string?))

(spec/def ::resource (spec/keys :req-un [::resource-url
                                         ::resource-type]
                                :opt-un [::content
                                         ::bookmark-url
                                         ::tags
                                         ::description]))

(spec/def ::resources (spec/* ::resource))

(spec/def ::query     (spec/keys :req-un [::resource-url]))
