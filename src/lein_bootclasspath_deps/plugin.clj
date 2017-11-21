(ns lein-bootclasspath-deps.plugin
  (:require [leiningen.core.main :refer [debug]]
            [leiningen.core.classpath :as cp]
            [cemerick.pomegranate.aether :as aether]))

(defn- path-for-deps [deps project]
  (when (not-empty deps)
    (let [btcp-deps-tree (cp/get-dependencies :boot-dependencies nil project)
          hierarchy (aether/dependency-hierarchy deps btcp-deps-tree)
          deps-paths (aether/dependency-files hierarchy)]
      (clojure.string/join java.io.File/pathSeparatorChar
                           (map str deps-paths)))))

(defn- deps-type [dep]
  (let [[_ _ & {:keys [prepend]}] dep]
    (if prepend :prepend :append)))

(defn middleware [project]
  (if-let [deps (->> project
                     :boot-dependencies
                     not-empty)]
    (do
      (debug "bootclasspath dependencies:" deps)
      (let [{prepends :prepend appends :append}
            (group-by deps-type deps)

            [prepend-paths append-paths]
            (map #(path-for-deps % project) [prepends appends])]
        (debug "prepend bootclasspath:" prepend-paths)
        (debug "append bootclasspath:" append-paths)
        (-> project
            (update-in [:jvm-opts] concat
                       (when prepend-paths
                         [(str "-Xbootclasspath/p:" prepend-paths)]))
            (update-in [:jvm-opts] concat
                       (when append-paths
                         [(str "-Xbootclasspath/a:" append-paths)])))))
    project))
