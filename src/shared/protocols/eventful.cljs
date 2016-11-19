(ns shared.protocols.eventful)

(defprotocol Eventful
  "The Eventful protocol allows offcourse components and adapters to handle
  and respond to events"
  (-react [this event])
  (-listen [this])
  (-mute [this])
  (-respond [this event]))

(defn react
  "Has a service react to an event based on the event's
  specification type"
  [this event] (-react this event))

(defn respond
  "Puts an event on the output channel of a component"
  [this event] (-respond this event))

(defn listen
  "Has a component listen for events on the input channel,
  and reacts when the event is relevant"
  [this] (-listen this))

(defn mute
  "Makes a component stop listening for events"
  [this] (-mute this))
