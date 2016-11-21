(ns shared.specs.checkpoint
  (:require [cljs.spec :as spec]
            [shared.specs.helpers :as helpers]
            [shared.specs.resource :as resource]
            [shared.specs.base :as base]))

(spec/def :checkpoint/checkpoint-slug :base/slug)
(spec/def :checkpoint/task            (and string? #(helpers/min-length % 4)))
(spec/def :checkpoint/checkpoint-id   int?)

(spec/def :checkpoint/valid (spec/keys :req-un [:checkpoint/task ::resource/resource-url
                                                :checkpoint/checkpoint-id]
                                       :opt-un [::base/tags]))

(spec/def :checkpoint/query (spec/keys :req-un [:checkpoint/checkpoint-slug]))
