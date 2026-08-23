(ns zanimljiva-geografija.print.boka
  (:use
   clj-common.clojure)
  (:require
   [clj-common.2d :as draw]
   [clj-common.as :as as]
   [clj-common.http :as http]
   [clj-common.io :as io]
   [clj-common.json :as json]
   [clj-common.localfs :as fs]
   [clj-common.path :as path]

   [clj-geo.import.geojson :as geojson]
   [clj-geo.import.gpx :as gpx]
   [clj-geo.import.osmapi :as osmapi]
   [clj-geo.osm.dataset :as dataset]
   [clj-geo.math.tile :as tile-math]
   [clj-geo.visualization.tile :as tile]))

#_(def min-tile-map [13 4513 3022])
#_(def max-tile-map [13 4526 3031])

(def tile-cache-path ["Users" "vanja" "dataset-local" "tile-cache"])
(def dataset-root-path ["Users" "vanja" "dataset-cloud" "print"])
(def garmin-track-root-path ["Users" "vanja" "projects" "dataset-garmin" "gpx"])

;; download and prepare background
(let [min-tile [14 9033 6049]
      max-tile [14 9047 6059]

      [zoom min-x min-y] min-tile
      [_ max-x max-y] max-tile
      tile-width (- (inc max-x) min-x)
      tile-height (- (inc max-y) min-y)
      image-width (* tile-width 256)
      image-height (* tile-height  256)
      image-context (draw/create-image-context image-width image-height)]
  (println "tile width:" tile-width "tile height:" tile-height)
  (println "width:" image-width "height:" image-height)
  (doseq [x (range min-x (inc max-x))]
    (doseq [y (range min-y (inc max-y))]
      (let [path (path/child tile-cache-path "osm" zoom (str x "_" y ".png"))]
        (println (str "processing [" zoom " " x " " y "]"))
        (when (not (fs/exists? path))
          (let [url (->
                     "https://tile.openstreetmap.org/{z}/{x}/{y}.png"
                     (.replace "{z}" (str zoom))
                     (.replace "{x}" (str x))
                     (.replace "{y}" (str y)))]
            (println "[downloading]" url)
            (let [is (http/get-as-stream
                      url
                      {
                       :headers {"User-Agent" "trek-mate"}})]
              (with-open [os (fs/output-stream path)]
                (io/copy-input-to-output-stream is os))))
          (sleep 500))
        (with-open [is (fs/input-stream path)]
          (let [tile (draw/input-stream->image-context is)]
            (draw/draw-image
             image-context
             [(+ (* (- x min-x) 256) 128) (+ (* (- y min-y) 256) 128)]
             tile))))))
  (with-open [os (fs/output-stream
                  (path/child
                   dataset-root-path
                   (str
                    "boka" "."
                    (first min-tile) "." (second min-tile) "." (nth min-tile 2)
                    ".png")))]
    (draw/write-png-to-stream image-context os)))

;; download and prepare background
(let [min-tile [15 18066 12098]
      max-tile [15 18095 12119]

      [zoom min-x min-y] min-tile
      [_ max-x max-y] max-tile
      tile-width (- (inc max-x) min-x)
      tile-height (- (inc max-y) min-y)
      image-width (* tile-width 256)
      image-height (* tile-height  256)
      image-context (draw/create-image-context image-width image-height)]
  (println "tile width:" tile-width "tile height:" tile-height)
  (println "width:" image-width "height:" image-height)
  (doseq [x (range min-x (inc max-x))]
    (doseq [y (range min-y (inc max-y))]
      (let [path (path/child tile-cache-path "osm" zoom (str x "_" y ".png"))]
        (println (str "processing [" zoom " " x " " y "]"))
        (when (not (fs/exists? path))
          (let [url (->
                     "https://tile.openstreetmap.org/{z}/{x}/{y}.png"
                     (.replace "{z}" (str zoom))
                     (.replace "{x}" (str x))
                     (.replace "{y}" (str y)))]
            (println "[downloading]" url)
            (let [is (http/get-as-stream
                      url
                      {
                       :headers {"User-Agent" "trek-mate"}})]
              (with-open [os (fs/output-stream path)]
                (io/copy-input-to-output-stream is os))))
          (sleep 500))
        (with-open [is (fs/input-stream path)]
          (let [tile (draw/input-stream->image-context is)]
            (draw/draw-image
             image-context
             [(+ (* (- x min-x) 256) 128) (+ (* (- y min-y) 256) 128)]
             tile))))))
  (with-open [os (fs/output-stream
                  (path/child
                   dataset-root-path
                   (str
                    "boka" "."
                    (first min-tile) "." (second min-tile) "." (nth min-tile 2)
                    ".png")))]
    (draw/write-png-to-stream image-context os)))
