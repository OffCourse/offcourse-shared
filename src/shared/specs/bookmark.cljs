(ns shared.specs.bookmark
  (:require [cljs.spec :as spec]))

(spec/def :bookmark/valid (spec/keys :req-un [:resource/resource-url
                                              :base/timestamp
                                              :course/curator]
                                     :opt-un [:offcourse/offcourse-id]))
