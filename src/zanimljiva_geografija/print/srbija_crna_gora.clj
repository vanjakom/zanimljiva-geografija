(ns zanimljiva-geografija.print.srbija-crna-gora
  (:use
   clj-common.clojure)
  (:require
   [clj-common.2d :as draw]
   [clj-common.http :as http]
   [clj-common.io :as io]
   [clj-common.localfs :as fs]
   [clj-common.path :as path]))

(def min-tile-map [8 140 90])
(def max-tile-map [8 144 95])

(def tile-cache-path ["Users" "vanja" "dataset-local" "tile-cache"])
(def dataset-root-path ["Users" "vanja" "dataset-cloud" "print"])

;; download and prepare background
(let [min-tile min-tile-map
      max-tile max-tile-map

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
  (with-open [os (fs/output-stream (path/child dataset-root-path
                                               "srbija_crna_gora.png"))]
    (draw/write-png-to-stream image-context os)))

;; same bounding box as above ( zoom 8, tile 140/90 - 144/95 ), moved to
;; zoom 10 ( x4, giving 20x24 tiles ), then extended 2 rows north and 2 rows
;; south ( 24 -> 28 ) so it splits evenly into four 10x14 sheets covering the
;; full original area, each meant to be printed on A4 paper and stuck
;; together into a 2x2 grid

(def a4-zoom 10)
(def a4-min-tile-map [a4-zoom 560 358])

(def a4-sheet-tile-width 10)
(def a4-sheet-tile-height 14)
(def a4-sheet-columns 2)
(def a4-sheet-rows 2)

(defn a4-download-tile [zoom x y]
  (let [path (path/child tile-cache-path "osm" zoom (str x "_" y ".png"))]
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
    path))

(defn a4-render-sheet [row column]
  (let [[zoom min-x min-y] a4-min-tile-map
        sheet-min-x (+ min-x (* column a4-sheet-tile-width))
        sheet-min-y (+ min-y (* row a4-sheet-tile-height))
        image-width (* a4-sheet-tile-width 256)
        image-height (* a4-sheet-tile-height 256)
        image-context (draw/create-image-context image-width image-height)]
    (println "rendering sheet [row" row "column" column "]")
    (doseq [x (range sheet-min-x (+ sheet-min-x a4-sheet-tile-width))]
      (doseq [y (range sheet-min-y (+ sheet-min-y a4-sheet-tile-height))]
        (let [tile-path (a4-download-tile zoom x y)]
          (with-open [is (fs/input-stream tile-path)]
            (let [tile (draw/input-stream->image-context is)]
              (draw/draw-image
               image-context
               [(+ (* (- x sheet-min-x) 256) 128) (+ (* (- y sheet-min-y) 256) 128)]
               tile))))))
    (with-open [os (fs/output-stream
                     (path/child
                      dataset-root-path
                      (str "srbija_crna_gora_a4_r" (inc row) "_c" (inc column) ".png")))]
      (draw/write-png-to-stream image-context os))))

(doseq [row (range a4-sheet-rows)]
  (doseq [column (range a4-sheet-columns)]
    (a4-render-sheet row column)))
