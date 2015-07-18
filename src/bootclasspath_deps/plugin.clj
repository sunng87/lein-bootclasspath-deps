(ns bootclasspath-deps.plugin
  (:require [leiningen.core.main :refer [debug]]
            [leiningen.core.classpath :as cp]
            [cemerick.pomegranate.aether :as aether]))

(defn middleware [project]
  (if-let [deps (->> project
                     :boot-dependencies
                     not-empty)]
    (do
      (debug "bootclasspath dependencies:" deps)
      (let [btcp-deps-tree (cp/get-dependencies :boot-dependencies project)
            hierarchy (aether/dependency-hierarchy deps btcp-deps-tree)
            deps-paths (aether/dependency-files hierarchy)

            concat-path (clojure.string/join java.io.File/pathSeparatorChar
                                             (map str deps-paths))]
        (debug "bootclasspath dependencies:" concat-path)
        (update-in project [:jvm-opts] conj
                   (str "-Xbootclasspath/p:"  concat-path))))
    project))
