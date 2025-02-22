(ns zanimljiva-geografija.job.zabranjenopusenje
  (:use
   clj-common.clojure)
  (:require
   [clj-http.client :as http]
   [clj-common.context :as context]
   [clj-common.io :as io]
   [clj-common.json :as json]
   [clj-common.localfs :as fs]
   [clj-common.path :as path]
   [clj-common.pipeline :as pipeline]
   [clj-geo.import.geojson :as geojson]))

;; processing and conflation of sites / profiles that list restaurants in
;; which smoking is forbidden

;; processing and conflation of imabezdima dataset ( map ) with OSM
;; goals
;; map each property from map to osm, if does not exist add
;; report to check if smoking tags are aligned

;; advantages for imabezdima to use osm map
;; better precision
;; brother audience
;; feedback from customers / shop closed / does not apply nosmoking policy

(def dataset-path ["Users" "vanja" "projects" "zanimljiva-geografija" "dataset"
                   "zabranjenopusenje"])

;; files in dataset
;; imabezdima.geosjon - initial kml downloaded from https://linktr.ee/imabezdima
;;    converted to gejson with
;;    https://mygeodata.cloud ( choose to merge datasets into single geojson )
;;    20250220 - created imabezdima.geojson
;; imabezdima.csv - conflate mapping, imabezdima hash and name to osm id ( url )
;; imabezdima.todo.geojson - geojson containing object that are not conflated

;; my process, use imabezdima.todo.geojson in iD
;; to find property on OSM, map if not present
;; research property ( add instagram, website ), check tags
;; enter node/way id ( n<id> w<id> ) in notes copy of imabezdima.csv
;; return csv from time to time
;; call job to see stats, create report and create imabezdima.todo.geojson

(defn imabezdima-read-dataset []
  (with-open [is (fs/input-stream (path/child dataset-path "imabezdima.geojson"))]
    (let [report (with-open [is (fs/input-stream
                                 (path/child dataset-path "imabezdima.csv"))]
                   (reduce
                    (fn [state line]
                      (let [fields (.split line ", " -1)
                            name (nth fields 0)
                            key (nth fields 1)
                            osm-id (when (and
                                          (> (count fields) 2)
                                          (not (empty? (nth fields 2))))
                                     (nth fields 2))
                            note (when (> (count fields) 3) (nth fields 3))]
                        (if (some? osm-id)
                          (assoc state (str key "|" name) [osm-id note])
                          state)))
                    {}
                    (io/input-stream->line-seq is)))]
      (doall
       (sort-by
        :name
        (map
         (fn [feature]
           (let [name (get-in feature [:properties :Name])
                 longitude (get-in feature [:geometry :coordinates 0])
                 latitude (get-in feature [:geometry :coordinates 1])
                 key (str
                      ""
                      (Math/abs
                       (.hashCode
                        (str longitude "|" latitude "|" name))))
                 [osm-id note] (get report (str key "|" name))]
             {
              :key key
              :name name
              :longitude longitude
              :latitude latitude
              :osm-id osm-id
              :note note}))
         (:features (json/read-keyworded is))))))))


;; done only once to create initial file
#_(with-open [os (fs/output-stream (path/child dataset-path "imabezdima.csv"))]
    (doseq [feature (imabezdima-read-dataset)]
      (io/write-line os (str (:name feature) ", " (:key feature) ", "))))

(defn imabezdima-conflate-files [context]
  (context/trace context
                 "loading dataset from imabezdima.geojson and imabezdima.csv")
  (let [dataset (imabezdima-read-dataset)
        to-map (filter #(nil? (:osm-id %))
                       dataset)]
    (context/trace context (str "dataset contains:" (count dataset)))
    (context/trace context (str "left to map: " (count to-map)))
    
    (with-open [os (fs/output-stream (path/child dataset-path
                                                 "imabezdima.todo.geojson"))]
      (json/write-pretty-print
       (geojson/geojson
        (map
         (fn [property]
           (geojson/point
            (:longitude property)
            (:latitude property)
            {
             :name (str
                    (subs (:key property) (max 0 (- (count (:key property)) 3)))
                    " "
                    (:name property))
             :key (:key property)}))
         to-map))
       (io/output-stream->buffered-writer os)))
    (context/trace context "done")))

;; todo
;; wrap in job daily process
;; merge with osm, report smoking tag, instagram, create csv
;; other datasets
;; https://www.facebook.com/nepusackilokaliubeogradu/
;; https://www.google.com/maps/d/u/0/viewer?mid=1qkPRUNRiCqA-uFugbcuVy9INXgw
