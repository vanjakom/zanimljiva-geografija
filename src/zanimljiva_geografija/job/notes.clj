(ns zanimljiva-geografija.job.notes
  (:use
   clj-common.clojure)
  (:require
   [clojure.data.xml :as xml]
   [clj-common.as :as as]
   [clj-common.context :as context]
   [clj-common.localfs :as fs]
   [clj-geo.import.osmapi :as osmapi]
   [clj-geo.math.polygon :as polygon]
   [clj-geo.dot.store.humandot :as humandot]
   [clj-scheduler.core :as core])
  (:import
   java.io.BufferedInputStream
   org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream))

;; extracts notes from planet notes dump ( see
;; https://planet.openstreetmap.org/notes/ ) that fall inside a country (
;; or any other administrative area ) boundary, boundary is retrieved from
;; OSM API using relation id, matching notes are stored as humandot

;; example usage serbia
#_(core/job-sumbit
 (core/job-create
  "extract-serbia-notes"
  {
   :relation-id 1741311
   :notes-path ["Users" "vanja" "dataset-local" "osm-notes" "planet-notes-latest.osn.bz2"]
   :dot-path ["Users" "vanja" "dataset-git" "notes" "serbia-notes.dot"]}
  extract-country-notes))
;; example usage beograd
#_(core/job-sumbit
 (core/job-create
  "extract-serbia-notes"
  {
   :relation-id 2728438
   :notes-path ["Users" "vanja" "dataset-local" "osm-notes" "planet-notes-latest.osn.bz2"]
   :dot-path ["Users" "vanja" "dataset-git" "notes" "belgrade-notes.dot"]}
  extract-country-notes))

;; boundary construction, relation members of role outer / inner are ways,
;; each way is a chain of node ids, chains sharing members need to be
;; joined into closed rings before they could be used for point in polygon
;; testing

(defn- way-node-seq [dataset way-id]
  (:nodes (get-in dataset [:way way-id])))

(defn- relation-role-segment-seq
  "Returns seq of node id seqs ( segments ) for members of given role,
  members which are not ways ( nested relations ) are traced and skipped"
  [context dataset relation role]
  (let [members (filter #(= (:role %) role) (:members relation))]
    (doseq [member (remove #(= (:type %) :way) members)]
      (context/trace
       context
       (str "skipping non way member of role " role ": " member)))
    (filter
     seq
     (map
      #(way-node-seq dataset (:id %))
      (filter #(= (:type %) :way) members)))))

(defn- close-segment-seq
  "Joins segments ( seq of node id seqs ) sharing endpoints into closed
  rings, best effort, segments which can't be attached to any other are
  kept as is"
  [segment-seq]
  (loop [remaining (map vec segment-seq)
         rings []]
    (if (empty? remaining)
      rings
      (let [ring (first remaining)
            rest-segments (rest remaining)]
        (if (= (first ring) (last ring))
          (recur rest-segments (conj rings ring))
          (if-let [match (first
                          (filter
                           (fn [segment]
                             (or
                              (= (last ring) (first segment))
                              (= (last ring) (last segment))
                              (= (first ring) (first segment))
                              (= (first ring) (last segment))))
                           rest-segments))]
            (let [joined (vec
                          (cond
                            (= (last ring) (first match))
                            (concat ring (rest match))

                            (= (last ring) (last match))
                            (concat ring (rest (reverse match)))

                            (= (first ring) (last match))
                            (concat match (rest ring))

                            :else
                            (concat (reverse match) (rest ring))))]
              (recur
               (conj (remove #(= % match) rest-segments) joined)
               rings))
            (recur rest-segments (conj rings ring))))))))

(defn- node-id->location [dataset node-id]
  (let [node (get-in dataset [:node node-id])]
    (polygon/location
     (as/as-double (:longitude node))
     (as/as-double (:latitude node)))))

(defn- relation->boundary
  "Retrieves full dataset for given relation id from OSM API and assembles
  outer / inner rings of locations to be used for point in polygon testing.
  Note: doesn't support relations with sub relation members ( e.g. some
  country boundaries composed of admin_level relations per region )"
  [context relation-id]
  (context/trace context (str "retrieving relation " relation-id " from OSM API"))
  (let [dataset (osmapi/relation-full relation-id)
        relation (get-in dataset [:relation relation-id])
        ring-seq (fn [role]
                   (map
                    (fn [segment]
                      (map #(node-id->location dataset %) segment))
                    (close-segment-seq
                     (relation-role-segment-seq context dataset relation role))))
        outer (ring-seq "outer")
        inner (ring-seq "inner")]
    (context/trace
     context
     (str
      "boundary assembled, outer rings: " (count outer)
      ", inner rings: " (count inner)))
    {:outer outer :inner inner}))

(defn- boundary->bbox [boundary]
  (let [location-seq (mapcat identity (:outer boundary))]
    {
     :min-longitude (apply min (map polygon/longitude location-seq))
     :max-longitude (apply max (map polygon/longitude location-seq))
     :min-latitude (apply min (map polygon/latitude location-seq))
     :max-latitude (apply max (map polygon/latitude location-seq))}))

(defn- location-in-bbox? [bbox location]
  (and
   (<= (:min-longitude bbox) (polygon/longitude location) (:max-longitude bbox))
   (<= (:min-latitude bbox) (polygon/latitude location) (:max-latitude bbox))))

(defn- location-in-boundary? [boundary location]
  (and
   (some #(polygon/location-inside % location) (:outer boundary))
   (not (some #(polygon/location-inside % location) (:inner boundary)))))

;; note -> dot, note-xml->note parsing itself lives in clj-geo.import.osmapi
;; since shape is shared between /api/0.6/notes and planet notes dump

(defn- note->dot
  "default-tags, if given, are appended after note derived tags, useful for
  e.g. marking all notes from a given extraction with a common tag"
  ([note] (note->dot note nil))
  ([note default-tags]
   (humandot/create-dot
    (:longitude note)
    (:latitude note)
    (concat
     [(str "|url|osm|https://www.openstreetmap.org/note/" (:id note))
      (if (:closed? note) "#closed" "#open")]
     (map
      (fn [comment]
        (str (or (:user comment) "anonymous") ": " (:text comment)))
      (:comments note))
     default-tags))))

(defn extract-country-notes
  "Retrieves country ( or other area ) boundary from OSM API based on
  relation id given in configuration, streams notes bz2 dump ( see
  https://planet.openstreetmap.org/notes/ ) from path given in
  configuration and stores notes falling inside boundary as humandot on
  path given in configuration. Optional default-tags in configuration are
  appended to the end of every note's tags"
  [context]
  (let [relation-id (as/as-long (get (context/configuration context) :relation-id))
        notes-path (get (context/configuration context) :notes-path)
        dot-path (get (context/configuration context) :dot-path)
        state-done-node (get (context/configuration context) :state-done-node)
        default-tags (get (context/configuration context) :default-tags)
        timestamp (System/currentTimeMillis)
        boundary (relation->boundary context relation-id)
        bbox (boundary->bbox boundary)
        note-counter (atom 0)
        match-counter (atom 0)]
    (context/trace context "streaming notes dump")
    (with-open [is (BZip2CompressorInputStream.
                    (BufferedInputStream. (fs/input-stream notes-path))
                    ;; planet dumps are compressed with pbzip2, producing
                    ;; multiple concatenated bzip2 streams, without this
                    ;; only first stream would be decompressed, leading to
                    ;; XMLStreamException once truncated content is reached
                    true)
                os (fs/output-stream dot-path)]
      (let [root (xml/parse is)
            dot-seq (->>
                     (:content root)
                     (filter #(= (:tag %) :note))
                     (map osmapi/note-xml->note)
                     (filter
                      (fn [note]
                        (swap! note-counter inc)
                        (when (zero? (mod (deref note-counter) 500000))
                          (context/trace
                           context
                           (str
                            "processed " (deref note-counter) " notes, matched "
                            (deref match-counter))))
                        (location-in-bbox? bbox note)))
                     (filter #(location-in-boundary? boundary %))
                     (map
                      (fn [note]
                        (swap! match-counter inc)
                        note))
                     (map #(note->dot % default-tags)))]
        (humandot/write
         os
         [(str "notes extracted for relation " relation-id
               " from planet notes dump")]
         dot-seq)))
    (context/trace
     context
     (str
      "processed " (deref note-counter) " notes total, matched "
      (deref match-counter)))
    (context/store-set context state-done-node timestamp)
    (context/trace context (str "state set at " state-done-node))))

;; example usage belgrade, fast bbox based search
#_(core/job-sumbit
 (core/job-create
  "extract-zemun-notes-fast"
  {
   :relation-id 10476357
   :dot-path ["Users" "vanja" "dataset-git" "notes" "zemun-notes-api.dot"]
   :open-only true
   :default-tags #{"osmnote"}}
  extract-area-notes))

(defn- relation->bbox
  "Retrieves full dataset for given relation id from OSM API and returns
  its bounding box. Cheaper than relation->boundary since it skips ring
  assembly, to be used when exact polygon match is not required"
  [context relation-id]
  (context/trace context (str "retrieving relation " relation-id " from OSM API"))
  (let [location-seq (map
                      (fn [node]
                        (polygon/location
                         (as/as-double (:longitude node))
                         (as/as-double (:latitude node))))
                      (vals (:node (osmapi/relation-full relation-id))))]
    {
     :min-longitude (apply min (map polygon/longitude location-seq))
     :max-longitude (apply max (map polygon/longitude location-seq))
     :min-latitude (apply min (map polygon/latitude location-seq))
     :max-latitude (apply max (map polygon/latitude location-seq))}))

(defn extract-area-notes
  "Retrieves bounding box of area given as relation id in configuration and
  retrieves notes falling inside that bbox directly from OSM API ( fast,
  no need for local planet notes dump ), stores them as humandot on path
  given in configuration. Set open-only to true in configuration to keep
  only currently open notes, filtering is done by OSM API itself. Optional
  default-tags in configuration are appended to the end of every note's
  tags.
  Note: matches bbox, not exact boundary polygon, expect notes just outside
  area to be included too. Note: OSM API rejects bbox with area above 25
  square degrees and returns at most 10000 notes, without paging, meant
  for city sized areas, not countries"
  [context]
  (let [relation-id (as/as-long (get (context/configuration context) :relation-id))
        dot-path (get (context/configuration context) :dot-path)
        state-done-node (get (context/configuration context) :state-done-node)
        open-only (get (context/configuration context) :open-only false)
        default-tags (get (context/configuration context) :default-tags)
        timestamp (System/currentTimeMillis)
        bbox (relation->bbox context relation-id)
        area (*
              (- (:max-longitude bbox) (:min-longitude bbox))
              (- (:max-latitude bbox) (:min-latitude bbox)))]
    (when (> area 25)
      (context/trace
       context
       (str
        "warning, bbox area " area
        " square degrees is above OSM API limit of 25")))
    (context/trace context (str "retrieving notes for bbox " bbox))
    (let [note-seq (osmapi/notes-bounding-box
                    (:min-longitude bbox) (:min-latitude bbox)
                    (:max-longitude bbox) (:max-latitude bbox)
                    10000
                    (if open-only 0 -1))]
      (context/trace context (str "retrieved " (count note-seq) " notes"))
      (with-open [os (fs/output-stream dot-path)]
        (humandot/write
         os
         [(str "notes retrieved for relation " relation-id " bbox from OSM API")]
         (map #(note->dot % default-tags) note-seq))))
    (context/store-set context state-done-node timestamp)
    (context/trace context (str "state set at " state-done-node))))

#_(extract-area-notes
 (context/create-stdout-context
  {
   :relation-id 2728438
   :dot-path ["Users" "vanja" "dataset-git" "notes" "belgrade-notes-api.dot"]}))

#_(extract-area-notes
 (context/create-stdout-context
  {
   :relation-id 10476357
   :dot-path ["Users" "vanja" "dataset-git" "notes" "belgrade-notes-api.dot"]}))
