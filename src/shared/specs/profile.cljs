(ns shared.specs.profile
  (:require [cljs.spec :as spec]
            [shared.specs.helpers :as helpers]
            [shared.specs.base :as base]))

(spec/def ::portrait-url string?)
(spec/def ::portrait  (spec/keys :req-un [::portrait-url]))
(spec/def ::profile (spec/keys :req-un [::base/user-name]))
