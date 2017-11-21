# lein-bootclasspath-deps

A Leiningen plugin to manage your bootclasspath.

## Usage

Use this for project-level plugins:

Put [![Clojars Project](http://clojars.org/info.sunng/lein-bootclasspath-deps/latest-version.svg)](http://clojars.org/info.sunng/lein-bootclasspath-deps) into the `:plugins`
vector of your project.clj.

Specify jars to loaded from bootclasspath in `:boot-dependencies`, like:

```clojure
:boot-dependencies [[org.mortbay.jetty.alpn/alpn-boot "8.1.3.v20150130"]]
```

By default, the jar will be **appended** to bootclasspath, to
prepend a jar, add an option `:prepend true`:

```clojure
:boot-dependencies [[org.mortbay.jetty.alpn/alpn-boot "8.1.3.v20150130" :prepend true]]
```

## License

Copyright © 2015-2017 Ning Sun

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
