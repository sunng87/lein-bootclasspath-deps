# bootclasspath-deps

A Leiningen plugin to manage your bootclasspath.

## Usage

Use this for project-level plugins:

Put `[info.sunng/lein-bootclasspath-deps "0.1.0]` into the `:plugins`
vector of your project.clj.

Specify jars to loaded from bootclasspath in `:boot-dependencies`, like:

```clojure
:boot-dependencies [[org.mortbay.jetty.alpn/alpn-boot "8.1.3.v20150130"]]
```

## License

Copyright © 2015 Ning Sun

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
