# Како мапирати
Overpass за проверу мапирања
```
[out:json];
node[natural=tree][religion=christian][denomination=serbian_orthodox](area:3601741311);
out geom;

node[natural=tree][amenity=community_centre][community_centre=cultural_centre](area:3601741311);
```

# #mobilni #operater #prodancica
```
shop = mobile_phone
```

# #bazen
```
leisure=swimming_pool
```
# #sportskicentar #bazen
```
leisure=sports_centre
sport=swimming
```
# #paintball #teren
```
leisure=pitch 
sport=paintball
```

# #javni #toalet #wc
```
amenity=toilets
```
# #pijaca
```
amenity=marketplace
```
# #antena #repetitor #toranj #komunikacija
https://wiki.openstreetmap.org/wiki/Tag:man_made%3Dtower  
ako je samo odasiljac, bez platformi, manji  
```
man_made=mast
tower:type=communication
```
ako je toranj, ima platforme, srednja velicina  
```
man_made=tower
tower:type=communication
```
ako je veliki toranj za komunikaciju, > 100m  
https://wiki.openstreetmap.org/wiki/Tag:man_made%3Dcommunications_tower  
```
man_made=communications_tower
```

# #zaselak
```
place=hamlet
name=
```

# #put #relacija
```
type = route
route = road
```

# #hotel #napusten
```
disused:tourism=hotel
building=hotel
```

# #drvo #stablo #lipa
```
natural=tree
genus=Tilia
leaf_type=broadleaved
```

# #mapa turisticka
```
tourism=information
information=map

```

# #bolnica
```
amenity = hospital
```

# #domzdravlja
```
amenity = doctors
healthcare = doctor
healthcare:speciality = general
```

# #ambulanta
```
amenity = doctors
healthcare = doctor
healthcare:speciality = general
```

# #oftamolog #opticar #naocare
prodaja
```
shop=optician
```
pregled
```
healthcare=optometrist 
```
operacija
```
amenity = doctors
healthcare:speciality = ophthalmology 
```

# #staracki dom
```
amenity = social_facility 
social_facility = nursing_home
```

# #apoteka
```
amenity = pharmacy
```

# #veterinar
```
amenity = veterinary
```

# #zubar
```
amenity = dentist
healthcare = dentist
```

# #mesnazajednica
name in format "Месна заједница X"
```
amenity=townhall
```
https://wiki.openstreetmap.org/wiki/Tag:office%3Dgovernment
https://wiki.openstreetmap.org/wiki/Tag:amenity%3Dtownhall
https://wiki.openstreetmap.org/wiki/Tag:amenity%3Dcommunity_centre
```
[out:json][timeout:25];
(
  nwr[office=government](area:3601741311);
  nwr[amenity=townhall](area:3601741311);
  nwr[amenity=community_centre](area:3601741311);
);
out center;
```


# #stanica #autobus
```
highway=bus_stop
```
ukoliko je i zastita od kise
```
amenity=shelter
shelter_type=public_transport
```

# #reka #potok
delovi reka, potok
```
waterway=river|stream
```
relacija za reku
```
type = waterway
waterway = river|stream
destination = 
```

# #restoran
```
  amenity=restaurant
```

# #planinarski #znak
```
  information = guidepost
  tourism = information
  hiking = yes
```

# #planinarska #staza
```
  type = route
  route = hiking
  network = lwn
  note = staza nije mapirana u potpunosti
  name = 
  name:sr = 
  name:sr-Latn = 
```

# #prodavnica #mala
prodancica osnovih namernica, hrana, pice
```
shop = convenience
```

# #spomenik #statua #glava #bista
spomenik osobi, glava i grudi
```
historic = memorial
memorial = bust
subject=
subject:wikidata = 
subject:wikipedia = 
wikidata = 
wikipedia = 
name = 
name:sr = 
name:sr-Latn = 
```

# #spomenik #statua #telo
spomenik osobi, celo telo
```
historic = memorial
memorial = statue
subject:wikidata = 
subject:wikipedia = 
wikidata = 
wikipedia = 
name = 
name:sr = 
name:sr-Latn = 
```

# #spomenik #rat
kada nije moguce uci u spomenik  
https://wiki.openstreetmap.org/wiki/Tag:historic%3Dmemorial  
```
historic=memorial
memorial=war_memorial
```

# #spomenik #monumentalni
kada je moguce uci u spomenik, monumentalan spomenik  
https://wiki.openstreetmap.org/wiki/Tag:historic%3Dmonument  
```
historic=monument
```

# #znak #planinarski
```
  information = guidepost
  tourism = information
  hiking = yes
```

# #planinarski #klub
```
office=association
association=sport
```
https://www.openstreetmap.org/node/8527932758

# #trafostanica
```
nodeman_made=street_cabinet
street_cabinet=power
power=substation
substation=minor_distribution
```
https://wiki.openstreetmap.org/wiki/Tag:power%3Dtransformer
https://wiki.openstreetmap.org/wiki/Tag:power%3Dsubstation

# #cesma #vanupotrebe
za mapiranje cesmi koji se koriste kao toponim
```
abandoned:amenity=drinking_water
historic=ruins
```

# #igraliste
```
leisure=playground
```

# #kanalizacija #cevovod
```
layer=-1
location=underground
man_made=pipeline
usage=sewage
```

# #virtuelni #footway #link
kada je potrebno povezati footway sa centrom puta
```
footway=link
highway=footway
```

# #pozoriste #otvoreno
```
amenity=theatre
theatre:type=open_air
```

# #domkulture
```
amenity=community_centre
community_centre=cultural_centre
```

# #potok #kanal #prepust
koristiti na delu potoka koji ide preko puta
```
tunnel=culvert
waterway=stream
```
# #skola
```
amenity=school
name=ОШ ”<ime>”
```
za building
```
building=school
```
moze i da se koristi landuse=school za oblast skole

# #sportskiteren #teren #sport
```
leisure=pitch
surface=asphalt
```

# #skloniste #vreme #picnic #kisa
picnic
```
amenity=shelter
shelter_type=picnic_shelter
```
autobuska stanica
```
shelter_type=public_transport
amenity=shelter

```

# #posta
name in following format
11080 Београд-Земун
spisak
https://www.posta.rs/DocumentViewer.aspx?IdDokument=1550&Dokument=spisak-posta-cir.pdf
https://www.posta.rs/DocumentViewer.aspx?IdDokument=1001550&Dokument=spisak-posta-lat.pdf

```
amenity = post_office
operator = Пошта Србије
ref=

```

# #apartman #iznajmljivanje #smestaj
```
tourism=apartment
```

# #pekara
```
shop = bakery

```

# #knjizara
```
shop = books
```

# #kljucar
```
craft = locksmith
```

# #pumpa #nis
http://localhost:7077/howto/NIS
```
amenity = fuel
name = NIS
name:sr = НИС
name:sr-Latn = NIS
brand = NIS
brand:wikipedia = sr:Нафтна_индустрија_Србије
brand:wikidata = Q1279721
website = https://www.nispetrol.rs/
```

# #pumpa
```
amenity = fuel
```

# #drvo #javor
```
natural=tree
genus=Fraxinus
leaf_type=broadleaved
```

# #drvo #bor
```
natural=tree
genus=Pinus
leaf_type=needleleaved
```

# #reckilaza #staklo #kontejner
```
xamenity=recycling
recycling:glass_bottles=yes
recycling_type=container
```

# #groblje
kada je blizu crkve
```
amenity = grave_yard
```
groblje bez crkve
```
landuse = cemetery
```

# #struja #bandera #dalekovod
za manje vodove, većinom na banderama
```
power=minor_line
```
za veće vodove, dalekovode
```
power=line
```

# #note #put #nepotpun
```
mapirati ostatak puta
```

# #beton #stepenice
```
highway=steps
surface=concrete
```

# #drveni #most
```
bridge=yes
highway=footway
surface=wood
```

# #kontejner
```
amenity=waste_disposal
```

# #voda #cesma
```
amenity=drinking_water
```
ili kada voda nije za pice
```
amenity=watering_place
```
moguce je mapirati i samu cesmu kao objekat
```
man_made=water_tap
```
# #staza #planinarskastaza
```
  type = route
  route = hiking
  network = lwn
  name = 
  name:sr = 
  name:sr-Latn = 
  roundtrip = yes | no
  operator=
```

# #gazebo #shelter #zastitaodkise
postoje i drugi prikladni tipovi
```
amenity=shelter
shelter_type=gazebo
```

# #klupa
```
amenity=bench
material=wood|metal
backrest=yes|no
```

# #zubar
node
	amenity=dentist
	name=
	website=
	phone=

# #kancelarija #firma
```
office=company
name=
website=
phone=
```

### Воћњак
#vocnjak
```
landuse=orchard
```

### Запис
#zapis  
https://sr.wikipedia.org/wiki/Запис  
https://wiki.openstreetmap.org/wiki/Serbia/Projekti/Mapiranje_zapisa  
```
amenity=place_of_worship
natural=tree
denomination=serbian_orthodox
religion=christian
genus=
leaf_type=
```
ukoliko se zapis nalazi na privatnom posedu i pristup nije dozvoljen
```
access=private
```

# #srpska #pravoslavna #crkva 
ukoliko se dodaju i konture crkve izdvojiti building = church
```
  amenity = place_of_worship
  denomination = serbian_orthodox
  religion = christian
  building = church
```

### Манастир
#manastir
```
amenity=place_of_worship
denomination=serbian_orthodox
religion=christian
name
wikipedia
wikidata
```

### Осматрачница
#osmatracnica  
```
man_made=tower
tower:type=observation
material=
```
za vojni toranj, utvrdjenje
```
man_made=tower
tower:type=watchtower
material=wood|metal
```
https://www.openstreetmap.org/way/785053578  

### Прелазак потока у природи ( камен или дебло )
```
ford=yes
```

### Планинарски дом
```
tourism=alpine_hut
building=yes
ele
name
website
```

