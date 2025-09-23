# #osm #map #tag #export #eurovelo #znak #bicikl
```
  bicycle = yes
  guidepost = bicycle
  information = guidepost
  tourism = information
  ref = Xли
```
# #osm #map #tag #export #mlekomat
trek-mate tag #mlekomat
```
amenity=vending_machine
vending=milk
```
# #osm #map #tag #export #vinarija #winery
trek-mate tag #winery
```
  craft=winery
```
ukoliko prodaju vino dodati
```
  shop=alcohol
  drink:wine=retail
```
# #osm #map #tag #export #banka #postanskastedionica
```
  amenity = bank
  brand = Поштанска штедионица
  brand:wikidata = Q2882644
  name = Поштанска штедионица
  name:sr = Поштанска штедионица
  name:sr-Latn = Poštanska štedionica
  operator = Поштанска штедионица
```
# #osm #map #tag #export #atm #postanskastedionica
```
  amenity = atm
  brand = Поштанска штедионица
  brand:wikidata = Q2882644
  name = Поштанска штедионица
  name:sr = Поштанска штедионица
  name:sr-Latn = Poštanska štedionica
  operator = Поштанска штедионица
```
# #osm #map #tag #export #postanskastedionica
```
  amenity = bank
  brand = Поштанска штедионица
  brand:wikidata = Q2882644
  name = Поштанска штедионица
  name:sr = Поштанска штедионица
  name:sr-Latn = Poštanska štedionica
  operator = Поштанска штедионица
```
# #osm #map #tag #export #dm
trek-mate tag #dm
```
  brand = dm
  brand:wikidata = Q266572
  brand:wikipedia = en:Dm-drogerie markt
  name = dm
  shop = chemist
  website = https://www.dm.rs
  
  changing_table = yes
  changing_table:location = sales_area
  baby_feeding = yes
  kids_area = yes
  kids_area:indoor = yes
```
# #osm #map #tag #export #bike #parking #bikeep
trek-mate tag #bikeep
```
amenity = bicycle_parking
brand=Bikeep
name = Bikeep
authentication:app = yes
authentication:nfc = yes
bicycle = yes
bicycle_parking = stands
covered = no
fee = no
scooter = yes
website = https://bikeep.com/sr/
capacity=5
ref=
```
# #osm #map #tag #export #paketomat #posta #postexpress
trek-mate tag #paketomat
```
amenity = parcel_locker
name:sr = Поштин пакетомат
name = Поштин пакетомат
name:sr-Latn = Poštin paketomat
operator = Пошта Србије
website = https://www.posta.rs/cir/alati/paketomati.aspx
ref=
```
ref uzeti sa https://www.posta.rs/cir/alati/lokacije.aspx
Post Express paketomati imaju drugaciji ref, za sada oznacavati isto posle
moze lako da se podeli prema podacima od Poste

# #osm #map #tag #export #paketomat #dexpress
trek-mate tag #paketomat
```
amenity = parcel_locker
contact:email = office@dexpress.rs
contact:phone = +381113313333
name = DExpress
operator = DExpress
website = https://www.dexpress.rs/
```
# #osm #map #tag #export #kamp #kamper #parcela #prikolica
trek-mate tag #camp za camp_site i caravan_site
постоје две ознаке, камп у традиционалном смислу:
```
tourism = camp_site
```
и преноћиште за кампере / приколице
```
tourism=caravan_site
```
користити
```
tents = yes/no
caravans = yes/no
motorhome = yes/no
```
појединачна парцела се обележава са
```
tourism = camp_pitch
ref = broj parcele 
```
остали делови унутар кампа налазе се на
```
amenity	= toilets
amenity	= shower
amenity = reception_desk
amenity	= sanitary_dump_station
amenity	= waste_disposal
amenity	= recycling
amenity	= washing_machine
dish_washing = yes
amenity	= power_supply
amenity	water_point
```
више на:
https://wiki.openstreetmap.org/wiki/Tag:tourism%3Dcamp_site
https://wiki.openstreetmap.org/wiki/Key:dish_washing
# #osm #map #tag #export #kamp
пражњење сивог танка:
```
amenity=sanitary_dump_station
```
веш машине:
```
amenity=washing_machine
```
# #osm #map #tag #export #kamper #prikolica #oprema #prodavnica
```
shop = outdoor
```
https://www.openstreetmap.org/node/5258000352
# #osm #map #tag #export #igraonica
trek-mate tag #igraoica
igraonica na zatvorenom koja se placa, kafic / igraonica / rodjendaonica
```
leisure = playground
indoor = yes
access = customers
fee = yes
```
deluje da postoji i
```
leisure=indoor_play
```
https://wiki.openstreetmap.org/wiki/Tag:leisure%3Dindoor_play
# #osm #map #tag #export #kladenac
```
natural=spring
```
pitao 20231221 u grupi, nema preciznijeg oznacavanja
# #osm #map #tag #export #poljoprivreda #poljoprivrednaapoteka
Poljoprivredna apoteka
```
shop=agrarian
agrarian=seed
```
https://wiki.openstreetmap.org/wiki/Tag:shop%3Dagrarian
# #osm #map #tag #export #lovackidom
Ловачки дом
```
club=hunting
```
ranije mapirao kao tourism = hunting_lodge, 20250218 dogovorio se sa Borovcem
da koristimo club=hunting, lovci introvertni, naziv u Srbiji je dosta sirok
https://wiki.openstreetmap.org/wiki/Tag:club%3Dhunting
https://wiki.openstreetmap.org/wiki/Tag:tourism%3Dhunting_lodge
```
club = hunting
```
# #osm #map #tag #export #reciklomat
делује да је пројекат угашен
```
amenity=recycling
recycling_type=container
recycling:pet_drink_bottles=yes
recycling:glass_bottles=yes
recycling:cans=yes
recycling:tetrapak=yes
operator=Reciklomat
website = http://reciklomat.rs/
```

Overpass за проверу мапирања
```
[out:json];
node[natural=tree][religion=christian][denomination=serbian_orthodox](area:3601741311);
out geom;

node[natural=tree][amenity=community_centre][community_centre=cultural_centre](area:3601741311);
```
# #osm #map #tag #export #poi
name онако како је на табли / логу / сајту. ако има смисла додати name:sr и / или name:sr-Latn
# #osm #map #tag #export #prelaz #reka
прелаз преко реке када се река гази
```
ford = yes
```
# #osm #map #tag #export #planinarska #staza #deo
```
trailblazed=symbols
trailblazed:visibility=intermediate
osmc:symbol=red:red_round:white_dot
```
https://wiki.openstreetmap.org/wiki/Key:trailblazed#Values

# #osm #map #tag #export #walter #cevapi
trek-mate tag #walter
```
amenity = restaurant
name = Walter
```
# #osm #map #tag #export #diskont #pica
```
shop = alcohol
```
# #osm #map #tag #export #institut
```
office=research
```
ili ako je veci institut sa vise objekata
```
amenity=research institute
```
https://wiki.openstreetmap.org/wiki/Proposed_features/amenity%3Dresearch_institute
https://wiki.openstreetmap.org/wiki/Tag:office%3Dresearch
# #osm #map #tag #export #paket #dostava #paketomat #nis #dexpress
https://wiki.openstreetmap.org/wiki/Proposed_features/amenity%3Dparcel_locker
# #osm #map #tag #export #zabranjeno #pusenje #nosmoking
```
smoking=no
```
or
```
smoking=outside
```
# #osm #map #tag #export #arheoloskonalaziste #arheolosko #nalaziste
```
historic=archaeological_site
```

# #osm #map #tag #export #ulica #street #relacija
kada se ulica sastoji iz vise puteva
```
type=street
```
each way with role street
https://wiki.openstreetmap.org/wiki/Relation:street
# #osm #map #tag #export #zgrada #delovi
kompleksna zgrada

relation type=building, building=* members ways sa building:part=yes ako su istog tipa ili za svaki building=* drugaciji tag 

primer
https://www.openstreetmap.org/relation/13236307
# #osm #map #tag #export #prerast
ne postoji specifican tag
```
natural=rock
```
# #osm #map #tag #export #survey #check #date
survey:date - use to mark when last on site check was
check_date - should be used to mark all tags are correct

# #osm #map #tag #export #cvecara
```
shop=florist
```
# #osm #map #tag #export #prihvatiliste
https://wiki.openstreetmap.org/wiki/Key:social_facility

# #osm #map #tag #export #ev #punjac #elektro
```
amenity=charging_station
capacity=
```
# #osm #map #tag #export #policija
```
amenity=police
```
ако објекат не служи за контакт са грађанством користити
```
police=offices
```
# #osm #map #tag #export #kamen #zid
```
barrier=wall
wall=stone_wall
```
# #osm #map #tag #export #mobilni #operater #prodavnica
```
shop = mobile_phone
```
# #osm #map #tag #export #bazen
```
leisure=swimming_pool
```
# #osm #map #tag #export #sportskicentar #bazen
```
leisure=sports_centre
sport=swimming
```
# #osm #map #tag #export #paintball #teren
```
leisure=pitch 
sport=paintball
```
# #osm #map #tag #export #javni #toalet #wc
```
amenity=toilets
```
ukoliko je gradjevina predvidjena za wc
```
building=toilets
```
# #osm #map #tag #export #pijaca
```
amenity=marketplace
```
# #osm #map #tag #export #antena #repetitor #toranj #komunikacija
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
# #osm #map #tag #export #zaselak
```
place=hamlet
name=
```
# #osm #map #tag #export #put #relacija
```
type = route
route = road
```
# #osm #map #tag #export #hotel #napusten
```
disused:tourism=hotel
building=hotel
```
# #osm #map #tag #export #drvo #stablo #lipa
```
natural=tree
genus=Tilia
leaf_type=broadleaved
```
# #osm #map #tag #export #mapa turisticka
```
tourism=information
information=map

```
# #osm #map #tag #export #bolnica
trek-mate tag #doktor
```
amenity = hospital
```

# #osm #map #tag #export #domzdravlja
trek-mate tag #doktor
```
amenity = doctors
healthcare = doctor
healthcare:speciality = general
```
# #osm #map #tag #export #ambulanta
trek-mate tag #doktor
```
amenity = doctors
healthcare = doctor
healthcare:speciality = general
```
# #osm #map #tag #export #oftamolog #opticar #naocare
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
# #osm #map #tag #export #staracki dom
```
amenity = social_facility 
social_facility = nursing_home
```
# #osm #map #tag #export #apoteka
trek-mate tag #apoteka
```
amenity = pharmacy
```
# #osm #map #tag #export #veterinar
```
amenity = veterinary
```
# #osm #map #tag #export #zubar
trek-mate tag #zubar
```
amenity = dentist
healthcare = dentist
```
# #osm #map #tag #export #mesnazajednica
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
# #osm #map #tag #export #stanica #autobus
```
highway=bus_stop
```
ukoliko je i zastita od kise
```
amenity=shelter
shelter_type=public_transport
```
# #osm #map #tag #export #reka #potok
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
# #osm #map #tag #export #restoran
trek-mate tag #eat, #restoran
```
  amenity=restaurant
```
# #osm #map #tag #export #planinarski #znak
```
  information = guidepost
  tourism = information
  hiking = yes
```
# #osm #map #tag #export #planinarska #staza
```
  type = route
  route = hiking
  network = rwn
  operator = 
  note = staza nije mapirana u potpunosti
  complete = no
  name = 
```
# #osm #map #tag #export #prodavnica #mala
prodancica osnovih namernica, hrana, pice
```
shop = convenience
```
# #osm #map #tag #export #spomenik #statua #glava #bista
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
# #osm #map #tag #export #spomenik #statua #telo
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
# #osm #map #tag #export #spomenik #rat
kada nije moguce uci u spomenik  
https://wiki.openstreetmap.org/wiki/Tag:historic%3Dmemorial  
```
historic=memorial
memorial=war_memorial
```
# #osm #map #tag #export #spomenik #monumentalni
kada je moguce uci u spomenik, monumentalan spomenik  
https://wiki.openstreetmap.org/wiki/Tag:historic%3Dmonument  
```
historic=monument
```
# #osm #map #tag #export #planinarski #klub
```
office=association
association=sport
```
https://www.openstreetmap.org/node/8527932758
# #osm #map #tag #export #trafostanica
```
nodeman_made=street_cabinet
street_cabinet=power
power=substation
substation=minor_distribution
```
https://wiki.openstreetmap.org/wiki/Tag:power%3Dtransformer
https://wiki.openstreetmap.org/wiki/Tag:power%3Dsubstation
# #osm #map #tag #export #cesma #vanupotrebe
za mapiranje cesmi koji se koriste kao toponim
```
abandoned:amenity=drinking_water
historic=ruins
```
# #osm #map #tag #export #kanalizacija #cevovod
```
layer=-1
location=underground
man_made=pipeline
usage=sewage
```
# #osm #map #tag #export #virtuelni #footway #link
kada je potrebno povezati footway sa centrom puta
```
footway=link
highway=footway
```
# #osm #map #tag #export #pozoriste #otvoreno
```
amenity=theatre
theatre:type=open_air
```
# #osm #map #tag #export #domkulture
```
amenity=community_centre
community_centre=cultural_centre
```
# #osm #map #tag #export #potok #kanal #prepust
koristiti na delu potoka koji ide preko puta
```
tunnel=culvert
waterway=stream
```
# #osm #map #tag #export #skola
```
amenity=school
name=ОШ ”<ime>”
```
za building
```
building=school
```
moze i da se koristi landuse=school za oblast skole
# #osm #map #tag #export #sportskiteren #teren #sport
```
leisure=pitch
surface=asphalt
```
# #osm #map #tag #export #skloniste #vreme #picnic #kisa
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
# #osm #map #tag #export #posta
trek-mate tag #posta
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
# #osm #map #tag #export #apartman #iznajmljivanje #smestaj
```
tourism=apartment
```
# #osm #map #tag #export #pekara
trek-mate tag #eat, #pekara
```
shop = bakery
```
# #osm #map #tag #export #knjizara
```
shop = books
```
# #osm #map #tag #export #kljucar
```
craft = locksmith
```
# #osm #map #tag #export #pumpa #nis
trek-mate tag #nis
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
# #osm #map #tag #export #pumpa
trek-mate tag #pumpa
```
amenity = fuel
```
# #osm #map #tag #export #drvo #javor
```
natural=tree
genus=Fraxinus
leaf_type=broadleaved
```
# #osm #map #tag #export #drvo #bor
```
natural=tree
genus=Pinus
leaf_type=needleleaved
```
# #osm #map #tag #export #reciklaza #baterija
trek-mate tag #reciklazabaterija
```
amenity=recycling
recycling_type=bin
recycling:batteries=yes
```
# #osm #map #tag #export #reciklaza #staklo #kontejner
trek-mate tag #reciklazastaklo
```
amenity=recycling
recycling_type=container
recycling:glass_bottles=yes
```
# #osm #map #tag #export #reciklaza #mesano #kontejner
trek-mate tag #reciklazapapir #reciklazalimenka #reciklazaplastika
```
amenity=recycling
recycling_type=container
recycling:cans=yes
recycling:paper=yes
recycling:plastic=yes
```
# #osm #map #tag #export #reciklaza #cep
trek-mate tag #reciklazacep
```
amenity=recycling
recycling_type=bin
recycling:plastic_bottle_caps=yes
```
# #osm #map #tag #export #reciklaza #cep #cepzahendikep
trek-mate tag #reciklazacep
```
amenity=recycling
recycling_type=bin
recycling:plastic_bottle_caps=yes
operator="Чеп за хендикеп"
```
# #osm #map #tag #export #groblje
kada je blizu crkve
```
amenity = grave_yard
```
groblje bez crkve
```
landuse = cemetery
```
# #osm #map #tag #export #struja #bandera #dalekovod
za manje vodove, većinom na banderama
```
power=minor_line
```
za veće vodove, dalekovode
```
power=line
```
# #osm #map #tag #export #note #put #nepotpun
```
mapirati ostatak puta
```
# #osm #map #tag #export #beton #stepenice
```
highway=steps
surface=concrete
```
# #osm #map #tag #export #drveni #most
```
bridge=yes
highway=footway
surface=wood
```
# #osm #map #tag #export #kontejner
```
amenity=waste_disposal
```
# #osm #map #tag #export #voda #cesma
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
# #osm #map #tag #export #voda #izvor
```
natural=spring
```
ako je voda za pice
```
drinking_water=yes
```
ili ako je poznato da nije
```
drinking_water=no
```
# #osm #map #tag #export #staza #planinarskastaza
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
# #osm #map #tag #export #gazebo #shelter #zastitaodkise
postoje i drugi prikladni tipovi
```
amenity=shelter
shelter_type=gazebo
```
# #osm #map #tag #export #klupa
```
amenity=bench
material=wood|metal
backrest=yes|no
```
# #osm #map #tag #export #kancelarija #firma
```
office=company
name=
website=
phone=
```
# #osm #map #tag #export Воћњак
#vocnjak
```
landuse=orchard
```
# #osm #map #tag #export #zapis  Запис
trek-mate tag #zapis
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
# #osm #map #tag #export #srpska #pravoslavna #crkva 
ukoliko se dodaju i konture crkve izdvojiti building = church
```
  amenity = place_of_worship
  denomination = serbian_orthodox
  religion = christian
  building = church
```
https://openstreetmap.rs/kartografisanje-crkava-manastira-i-grobalja-srpske-pravoslavne-crkve/
### #osm #map #tag #export #manastir
Манастир
```
amenity=place_of_worship
denomination=serbian_orthodox
religion=christian
name
wikipedia
wikidata
```
### #osm #map #tag #export #osmatracnica
Осматрачница
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

### #osm #map #tag #export Прелазак потока у природи ( камен или дебло )
```
ford=yes
```
### #osm #map #tag #export Планинарски дом
```
tourism=alpine_hut
building=yes
ele
name
website
```
# #osm #map #tag #export #rasadnik
```
  shop = garden_centre
```

# #osm #map #tag #export #bancaintesa #atm
```
  amenity = atm
  brand = Banca Intesa
  brand:wikidata = Q647092
  brand:wikipedia = en:Banca Intesa
  cash_in = no
  name = Banca Intesa
  name:sr-Latn = Banca Intesa
  operator = Banca Intesa
  operator:wikidata = Q647092
  website = https://www.bancaintesa.rs/
```
