(ns shared.specs.bookmark
  (:require [shared.specs.resource :as resource]
            [shared.specs.course :as course]
            [cljs.spec :as spec]))

(spec/def ::bookmark (spec/keys :req-un [::resource/resource-url]
                                :opt-un [::course/course-id]))
