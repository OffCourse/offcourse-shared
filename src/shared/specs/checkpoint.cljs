(ns shared.specs.checkpoint
  (:require [cljs.spec :as spec]
            [shared.specs.helpers :as helpers]
            [shared.specs.resource :as resource]
            [shared.specs.base :as base]))

(spec/def ::checkpoint-slug ::base/slug)
(spec/def ::task (and string? #(helpers/min-length % 4)))
(spec/def ::checkpoint-id int?)
(spec/def ::checkpoint (spec/keys :req-un [::task ::resource/resource-url ::checkpoint-id]))
(spec/def ::query      (spec/keys :req-un [::checkpoint-slug]))

