# Како мапирати

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

# #drvo #most
```
bridge=yes
highway=footway
surface=wood
```

# #kontejner
```
amenity=waste_disposal
```

# #voda
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

