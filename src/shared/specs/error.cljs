(ns shared.specs.error
  (:require [cljs.spec :as spec]))

(spec/def ::error any?)

(spec/def :error/error           (spec/keys :req-un [::error]))
