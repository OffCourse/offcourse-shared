(ns shared.specs.github
  (:require [cljs.spec :as spec]
            [shared.specs.base :as base]))

(spec/def ::sha string?)
(spec/def ::owner string?)
(spec/def ::name string?)
(spec/def ::mode string?)
(spec/def ::type string?)
(spec/def ::size int?)
(spec/def ::url ::base/url)

(spec/def ::repo   (spec/keys :req-un [::name ::owner ::sha]))
(spec/def ::path   string?)
(spec/def ::course (spec/keys :req-un [::path ::sha ::url]))
