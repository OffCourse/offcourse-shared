(ns shared.protocols.convertible)

(defprotocol Convertible
  "The Convertible protocol allows data structures to be converted to others
  after they are checked against their specification"
  (-to-model       [this])
  (-to-event        [this])
  (-to-query        [this])
  (-to-payload      [this])
  (-to-clj          [this])
  (-to-viewmodel    [this])
  (-to-route        [this])
  (-to-url          [this routes] [this course routes]))

(defn to-model
  "Converts raw data to one or more offcourse models, if the object meets the
  corresponding model specification"
  [this] (-to-model this))

(defn to-event
  "Converts an js api event to an offcourse event, if the object meets the
  corresponding model specification"
  [this] (-to-event this))

(defn to-query
  "Converts an js object to an offcourse query, if the object meets the
  corresponding model specification"
  [this] (-to-query this))

(defn to-payload
  "Converts an js object to an offcourse query, if the object meets the
  corresponding model specification"
  [this] (-to-payload this))

(defn to-url
  "Given a set of routes, it converts a model to a url"
  ([this routes] (-to-url this routes))
  ([this course routes] (-to-url this course routes)))

(defn to-clj
  "converts javascript data to its cljs equivalent"
  ([this] (-to-clj this)))

(defn to-viewmodel
  "Given the provided route-params, it returns a viewmodel"
  ([this] (-to-viewmodel this)))

(defn to-route
  "Given the provided viewmodel, it returns the route-params"
  ([this] (-to-route this)))
