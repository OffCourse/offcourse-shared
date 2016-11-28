(ns shared.protocols.convertible)

(defprotocol Convertible
  "The Convertible protocol allows data structures to be converted to others
  after they are checked against their specification"
  (-to-model        [this])
  (-to-event        [this])
  (-to-db-events    [this])
  (-to-query        [this])
  (-to-bookmark     [this])
  (-to-bookmarks    [this])
  (-to-payload      [this])
  (-to-action       [this])
  (-to-clj          [this])
  (-to-json         [this])
  (-to-credentials  [this])
  (-to-viewmodel    [this])
  (-to-route        [this])
  (-to-db           [this])
  (-to-bucket       [this])
  (-to-stream       [this])
  (-to-search       [this])
  (-to-url          [this routes] [this course routes]))

(defn to-credentials
  "Converts the given data to an credentials object, if the object meets the
  corresponding model specification"
  [this] (-to-credentials this))

(defn to-db
  "Converts the given data into a valid search item, if the object meets the
  corresponding model specification"
  [this] (-to-db this))

(defn to-search
  "Converts the given data into a valid db item, if the object meets the
  corresponding model specification"
  [this] (-to-search this))

(defn to-stream
  "Converts the given data into a valid stream item, if the object meets the
  corresponding model specification"
  [this] (-to-stream this))

(defn to-bucket
  "Converts the given data into a bucket item, if the object meets the
  corresponding model specification"
  [this] (-to-bucket this))

(defn to-bookmark
  "Converts the given data and offcourse bookmark, if the object meets the
  corresponding model specification"
  [this] (-to-bookmark this))

(defn to-bookmarks
  "Converts the given data to offcourse bookmarks, if the data meets the
  corresponding model specification"
  [this] (-to-bookmarks this))

(defn to-model
  "Converts raw data to one or more offcourse models, if the object meets the
  corresponding model specification"
  [this] (-to-model this))

(defn to-db-events
  "Converts an js api event to an offcourse event, if the object meets the
  corresponding model specification"
  [this] (-to-db-events this))

(defn to-event
  "Converts an js api event to an offcourse event, if the object meets the
  corresponding model specification"
  [this] (-to-event this))

(defn to-action
  "Converts an js api action to an offcourse action, if the object meets the
  corresponding model specification"
  [this] (-to-action this))

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

(defn to-json
  "converts javascript data to a json object"
  ([this] (-to-json this)))

(defn to-viewmodel
  "Given the provided route-params, it returns a viewmodel"
  ([this] (-to-viewmodel this)))

(defn to-route
  "Given the provided viewmodel, it returns the route-params"
  ([this] (-to-route this)))
