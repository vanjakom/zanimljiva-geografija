(ns zanimljiva-geografija.maps.belgrade-events
  (:require
   [clj-common.context :as context]
   [clj-common.io :as io]
   [clj-common.json :as json]
   [clj-common.localfs :as fs]
   [clj-common.path :as path]
   [clj-geo.dotstore.humandot :as humandot]
   [clj-geo.import.geojson :as geojson]
   [clj-geo.visualization.map :as map]
   [trek-mate.map :as trek-mate]
   [trek-mate.pin :as pin]))

(defn create-map [context]
  (let [configuration (context/configuration context)
        dot-path (get configuration :dot-path)
        export-root-path (get configuration :export-root-path)
        export-path (path/child export-root-path "ravni.html")]
   (context/trace context (str "creating map for: ravni" ))
   (with-open [os (fs/output-stream export-path)]
     (io/write-string
      os
      (map/render-raw
       {}
       [
        (map/tile-layer-osm false)
        (map/tile-layer-bing-satellite false)
        (map/tile-layer-google-satellite true)
        (binding [geojson/*style-stroke-color* geojson/color-orange]
          (map/geojson-layer
           "имање границе"
           (with-open [is (fs/input-stream (path/child dataset-root-path "plac.geojson"))]
             (json/read-keyworded is))
           true
           true))
        (binding [geojson/*style-stroke-color* geojson/color-orange]
          (map/geojson-layer
           "шума границе"
           (with-open [is (fs/input-stream (path/child dataset-root-path "suma.geojson"))]
             (json/read-keyworded is))))
        (binding [geojson/*style-stroke-color* geojson/color-orange]
          (map/geojson-layer
           "Бекан границе"
           (with-open [is (fs/input-stream (path/child dataset-root-path "bekan.geojson"))]
             (json/read-keyworded is))))
        (binding [geojson/*style-stroke-color* geojson/color-red]
          (map/geojson-layer
           "комшија Шљивар"
           (with-open [is (fs/input-stream (path/child dataset-root-path "sljivar.geojson"))]
             (json/read-keyworded is))))
        (binding [geojson/*style-stroke-color* geojson/color-red]
          (map/geojson-layer
           "комшија неки"
           (with-open [is (fs/input-stream (path/child dataset-root-path "neko.geojson"))]
             (json/read-keyworded is))))
        (binding [geojson/*style-stroke-color* geojson/color-green]
          (map/geojson-layer
           "Нуклеус"
           (with-open [is (fs/input-stream (path/child dataset-root-path "nukleus.geojson"))]
             (json/read-keyworded is))))
        (binding [geojson/*style-stroke-color* geojson/color-green]
          (map/geojson-layer
           "Воћњак"
           (with-open [is (fs/input-stream (path/child dataset-root-path "vocnjak.geojson"))]
             (json/read-keyworded is))))
       
        (map/geojson-style-extended-layer
         "треба да се уради"
         (map
          (fn [location]
            (geojson/point
             (:longitude location)
             (:latitude location)
             {
              :marker-icon (trek-mate/pin-url
                            (pin/calculate-pins (:tags location)))
              :marker-body (trek-mate/build-description location false)}))
          (filter
           #(let [tags (into #{} (:tags %))]
              (contains? tags "#todo"))
           (with-open [is (fs/input-stream (path/child dot-root-path "ravni.dot"))]
             (humandot/read is))))
         false
         true)
        (map/geojson-style-extended-layer
         "урадити јесен 2025"
         (map
          (fn [location]
            (geojson/point
             (:longitude location)
             (:latitude location)
             {
              :marker-icon (trek-mate/pin-url
                            (pin/calculate-pins (:tags location)))
              :marker-body (trek-mate/build-description location false)}))
          (filter
           #(let [tags (into #{} (:tags %))]
              (contains? tags "#todojesen2025"))
           (with-open [is (fs/input-stream (path/child dot-root-path "ravni.dot"))]
             (humandot/read is))))
         false
         true)        
        (map/geojson-style-extended-layer
         "информације"
         (map
          (fn [location]
            (geojson/point
             (:longitude location)
             (:latitude location)
             {
              :marker-icon (trek-mate/pin-url
                            (pin/calculate-pins (:tags location)))
              :marker-body (trek-mate/build-description location false)}))
          (filter
           #(let [tags (into #{} (:tags %))]
              (contains? tags "#info"))
           (with-open [is (fs/input-stream (path/child dot-root-path "ravni.dot"))]
             (humandot/read is))))
         false
         false)
        (map/geojson-style-extended-layer
         "топоними"
         (map
          (fn [location]
            (geojson/point
             (:longitude location)
             (:latitude location)
             {
              :marker-icon (trek-mate/pin-url
                            (pin/calculate-pins (:tags location)))
              :marker-body (trek-mate/build-description location false)}))
          (filter
           #(let [tags (into #{} (:tags %))]
              (contains? tags "#toponim"))
           (with-open [is (fs/input-stream (path/child dot-root-path "ravni.dot"))]
             (humandot/read is))))
         true
         false)
        (map/geojson-style-extended-layer
         "индекс стабала и знаменитог биља"
         (map
          (fn [location]
            (geojson/point
             (:longitude location)
             (:latitude location)
             {
              :marker-icon (trek-mate/pin-url
                            (pin/calculate-pins (:tags location)))
              :marker-body (trek-mate/build-description location false)}))
          (filter
           #(let [tags (into #{} (:tags %))]
              (contains? tags "#tree"))
           (with-open [is (fs/input-stream (path/child dot-root-path "ravni.dot"))]
             (humandot/read is))))
         false
         false)
        (map/geojson-style-extended-layer
         "остало"
         (map
          (fn [location]
            (geojson/point
             (:longitude location)
             (:latitude location)
             {
              :marker-icon (trek-mate/pin-url
                            (pin/calculate-pins (:tags location)))
              :marker-body (trek-mate/build-description location false)}))
          (filter
           #(let [tags (into #{} (:tags %))]
              (and
               (not (contains? tags "#todo"))
               (not (contains? tags "#info"))
               (not (contains? tags "#toponim"))
               (not (contains? tags "#tree"))))
           (with-open [is (fs/input-stream (path/child dot-root-path "ravni.dot"))]
             (humandot/read is))))
         false
         false)

        
        (with-open [is (fs/input-stream (path/string->path
                                         "/Users/vanja/dataset-cloud/garmin/gpx/Track_2025-06-17 174743.gpx"))]
          (map/geojson-gpx-layer "трек за преглед" is true false))])))
   (context/trace
   context
   (str
    "map created, view <a href='file://"
    (path/path->string export-path)
    "'>map</a>")))) 

#_(create-map (context/create-stdout-context))

