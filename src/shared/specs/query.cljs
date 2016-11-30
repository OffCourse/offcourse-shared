(ns shared.specs.query
  (:require [cljs.spec :as spec]))

(spec/def :query/course              :course/query)
(spec/def :query/collection          :collection/query)
(spec/def :query/checkpoint          :checkpoint/query)
(spec/def :query/checkpoint          :checkpoint/query)
(spec/def :query/resource            :resource/query)
(spec/def :query/identity            :identity/query)
