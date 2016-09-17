(ns shared.specs.event
  (:require [cljs.spec :as spec]
            [shared.specs.viewmodel :as viewmodel]
            [shared.specs.payload :as payload]
            [shared.specs.query :as query]
            [shared.specs.helpers :as helpers]
            [shared.specs.action :as action]
            [shared.specs.base :as base]))

(spec/def ::kinesis map?)
(spec/def ::dynamo map?)

(spec/def ::kinesis-record (spec/keys :req-un [::kinesis]))
(spec/def ::dynamo-record (spec/keys :req-un [::dynamodb]))

(spec/def ::Records (spec/or :kinesis (spec/* ::kinesis-record)
                             :dynamodb (spec/* ::dynamo-record)))

(spec/def ::type keyword?)

(spec/def ::api-event (spec/keys :req-un [::type]))

(spec/def ::kinesis-event (spec/and (spec/keys :req-un [::Records])
                                    #(= (first (:Records %)) :kinesis)))

(spec/def ::dynamo-event (spec/and (spec/keys :req-un [::Records])
                                    #(= (first (:Records %)) :dynamodb)))

#_(spec/def ::event (spec/or :kinesis ::kinesis-event
                             :dynamodb ::dynamo-event
                             :offcourse ::event
                             :api      ::api-event))

(spec/def ::data any?)
(spec/def ::explanation any?)
(spec/def ::error (spec/keys :req-un [::data ::explanation]))

(spec/def ::event-payload (spec/or :action              ::action/action
                                   :data                ::payload/payload
                                   :query               ::query/query
                                   :error               ::error))

(spec/def ::event (helpers/tuple-spec [:updated :found :not-found :granted :revoked :fetched
                                       :failed-to-convert :requested :requested-data
                                       :rendered :refreshed] ::event-payload))


#_(spec/def ::event (spec/or :kinesis ::kinesis-event
                             :dynamodb ::dynamo-event
                             :offcourse ::event
                             :api      ::api-event))
