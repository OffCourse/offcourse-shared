(ns shared.specs.collection
  (:require [cljs.spec :as spec]))

(spec/def :collection/collection-name string?)
(spec/def :collection/collection-type string?)

(spec/def :collection/valid (spec/keys :req-un [:collection/collection-type
                                                :collection/collection-name]))
(spec/def :collection/query (spec/keys :req-un [:collection/collection-type]
                                       :opt-un [:collection/collection-name]))
