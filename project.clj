(defproject com.mungolab/zanimljiva-geografija "0.1.0"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [
                 [org.clojure/clojure "1.11.1"]
                 [com.mungolab/clj-common "0.3.3"]
                 [com.mungolab/clj-geo "0.2.0"]
                 [com.mungolab/clj-scheduler "0.1.0"]
                 ;; bz2 decompression of osm notes planet dump, java has no
                 ;; built in support for it
                 [org.apache.commons/commons-compress "1.26.2"]])
