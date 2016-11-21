(ns shared.specs.collection
  (:require [cljs.spec :as spec]))

(spec/def ::collection-name string?)
(spec/def ::collection-type string?)

(spec/def :collection/valid (spec/keys :req-un [::collection-type ::collection-name]))
(spec/def :collection/query (spec/keys :req-un [::collection-type]
                                       :opt-un [::collection-name]))
