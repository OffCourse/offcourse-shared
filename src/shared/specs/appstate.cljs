(ns shared.specs.appstate
  (:require [cljs.spec :as spec]
            [shared.specs.viewmodel :as viewmodel]
            [shared.specs.base :as base]
            [shared.specs.user :as user]))

(spec/def ::token ::user/auth-token)
(spec/def ::appstate (spec/keys :req-un [::base/site-title ::viewmodel/viewmodel]
                                :opt-un [::token ::user/user]))
