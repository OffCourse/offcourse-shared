(ns shared.protocols.decoratable)

(defprotocol Decoratable
  "The Decoratable protocol augments offcourse models with a set of convenient
  function to deal with derived or other forms of extra data"
  (-decorate  [this] [this appstate] [this user-name slug routes]))

(defn decorate
  "The decoratable function augments offcourse models with data on their meta object
  are not part of their business logic, but may be convenient or necessary for the
  application to function"
  ([this] (-decorate this))
  ([this appstate] (-decorate this appstate))
  ([this user-name slug routes] (-decorate this user-name slug routes)))
