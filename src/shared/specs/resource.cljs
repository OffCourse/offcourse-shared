(ns shared.specs.resource
  (:require [shared.specs.base :as base]
            [cljs.spec :as spec]))

(spec/def :resource/resource-url :base/url)

(spec/def ::content        (spec/nilable string?))
(spec/def ::description    (spec/nilable string?))
(spec/def ::resource-type  string?)
(spec/def ::tags           (spec/* string?))

(spec/def :resource/valid  (spec/keys :req-un [:resource/resource-url
                                               ::resource-type]
                                      :opt-un [::content
                                               ::bookmark-url
                                               ::tags
                                               ::description]))

(spec/def :resource/query     (spec/keys :req-un [::resource-url]))
