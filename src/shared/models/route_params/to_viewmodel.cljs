(ns shared.models.route-params.to-viewmodel
  (:require [shared.models.viewmodel.index :as viewmodel]
            [shared.protocols.specced :as sp]
            [shared.protocols.loggable :as log]))

(defmulti to-viewmodel (fn [params] (sp/resolve params)))

(defmethod to-viewmodel :checkpoint-view [params]
  (viewmodel/create {:course     (select-keys params [:curator :organization :course-slug])
                     :checkpoint (select-keys params [:checkpoint-slug :checkpoint-id])}))

(defmethod to-viewmodel :course-view [params]
  (viewmodel/create {:course (select-keys params [:curator :organization :course-slug])}))

(defmethod to-viewmodel :collection-view [params]
  (viewmodel/create {:collection (select-keys params [:collection-type :collection-name])}))

(defmethod to-viewmodel :home-view [params]
  (viewmodel/create {:collection {:collection-type "flags"
                                   :collection-name "featured"}}))
