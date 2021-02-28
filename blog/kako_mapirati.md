# Како мапирати

# #potok #kanal #prepust
koristiti na delu potoka koji ide preko puta
```
tunnel=culvert
waterway=stream
```
# #skola
```
amenity=school
```
za building
```
building=school
```

# #sportskiteren #teren #sport
```
leisure=pitch
surface=asphalt
```

# #skloniste #vreme #picnic #kisa
```
amenity=shelter
shelter_type=picnic_shelter
```

# #posta
name in following format
11080 Београд-Земун
spisak
https://www.posta.rs/DocumentViewer.aspx?IdDokument=1001550&Dokument=spisak-posta-lat.pdf

```
amenity = post_office
operator = Пошта Србије

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
```
amenity=grave_yard
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
node
	office=company
	name=
	website=
	phone=

### Воћњак
#vocnjak
```
landuse=orchard
```

### Запис
#zapis  
https://sr.wikipedia.org/wiki/Запис  
```
natural=tree
zapis=yes
```

# #srpska #pravoslavna #crkva 
```
  building = church
  amenity = place_of_worship
  denomination = serbian_orthodox
  religion = christian
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

