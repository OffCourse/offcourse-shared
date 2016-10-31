(ns shared.specs.profile
  (:require [cljs.spec :as spec]
            [shared.specs.helpers :as helpers]
            [shared.specs.base :as base]))

(spec/def ::name string?)
(spec/def ::emails (spec/* map?))
(spec/def ::portrait-url string?)
(spec/def ::portrait-data string?)

(spec/def ::portrait  (spec/keys :req-un [::base/user-name ::portrait-url]
                                 :opt-un [::portrait-data]))

(spec/def ::profile (spec/keys :req-un [::base/user-name ::name ::emails]))
