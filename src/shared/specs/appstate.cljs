(ns shared.specs.appstate
  (:require [cljs.spec :as spec]
            [shared.specs.viewmodel :as viewmodel]
            [shared.specs.base :as base]))


(spec/def ::token ::base/auth-token)
(spec/def ::appstate (spec/keys :req-un [::base/site-title ::viewmodel/viewmodel]
                                :opt-un [::token ::base/user]))
