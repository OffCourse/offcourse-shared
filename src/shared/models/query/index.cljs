(ns shared.models.query.index
  (:require [shared.specs.core :as specs]
            [cljs.spec :as spec]
            [cljs.spec.test :as stest]
            [cuerdas.core :as str]
            [shared.protocols.loggable :as log]
            [shared.protocols.specced :as sp]))

(defn create [query]
  (with-meta query {:spec ::specs/query}))
