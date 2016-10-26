(ns shared.specs.aws
  (:require [cljs.spec :as spec]))

(spec/def ::event-name keyword?)
(spec/def ::new-image (spec/nilable map?))
(spec/def ::old-image (spec/nilable map?))
(spec/def ::dynamodb-event (spec/keys :req-un [::event-type ::new-image ::old-image]))

(spec/def ::kinesis map?)
(spec/def ::s3 map?)
(spec/def ::dynamodb map?)

(spec/def ::record      (spec/or :kinesis (spec/keys :req-un [::kinesis])
                                 :dynamodb (spec/keys :req-un [::dynamodb])
                                 :s3 (spec/keys :req-un [::s3])))

(spec/def ::type string?)
(spec/def ::auth-id (spec/nilable string?))
(spec/def ::methodArn string?)
(spec/def ::authorizationToken string?)

(spec/def ::Records (spec/* ::record))

(spec/def ::stage string?)
(spec/def ::body map?)

(spec/def ::stream-event (spec/keys :req-un [::Records]))

(spec/def ::api-event (spec/keys :req-un [::body ::stage]))

(spec/def ::auth-event (spec/keys :req-un [::type ::methodArn ::authorizationToken]
                                  :opt-un [::auth-id]))

(spec/def ::aws-event (spec/or
                       :auth   ::auth-event
                       :api     ::api-event
                       :stream ::stream-event))

(spec/def ::keys            (spec/* int?))
(spec/def ::bucket-items    (spec/keys :req-un [::keys]))
