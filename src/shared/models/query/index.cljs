(ns shared.models.query.index)

(defn create [query]
  (with-meta query {:spec :offcourse/query}))
