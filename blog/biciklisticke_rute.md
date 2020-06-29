## Бициклистичке руте у Србији

Потрага ѕа обележеним бициклистичким стазама у Србији.  

Преглед бициклистичких рута могућ је на неком од специјализованих сајтова:  
https://cycling.waymarkedtrails.org/#?map=8!44.7352!20.5686  

или уз помоћ следећег overpass упита:
```
[out:json];
(
  relation[type=route][route=bicycle](area:3601741311);
  relation[type=route][route=mtb](area:3601741311);
);
out geom;
```
[overpass-turbo](https://overpass-turbo.eu/?Q=%5Bout%3Ajson%5D%3B%0A%28%0A%20%20relation%5Btype%3Droute%5D%5Broute%3Dbicycle%5D%28area%3A3601741311%29%3B%0A%20%20relation%5Btype%3Droute%5D%5Broute%3Dmtb%5D%28area%3A3601741311%29%3B%0A%29%3B%0Aout%20geom%3B)  

**како мапирати:**  
https://wiki.openstreetmap.org/wiki/Cycle_routes  
https://wiki.openstreetmap.org/wiki/Tag:route%3Dbicycle  
https://wiki.openstreetmap.org/wiki/Tag:information%3Dguidepost  
учитати GPX kао позадину у iD  
додати локације знакова, пример  
```
  information = guidepost
  tourism = information
  bicycle=yes
  ref=1320
```
прећи руту и поделити путеве где је потребно, унети информације о подлози и проверити класификацију пута  
https://wiki.openstreetmap.org/wiki/Key:highway  
https://wiki.openstreetmap.org/wiki/Key:surface  
отворити Level0 и постојећу руту или додати нову  
```
  name = 
  name:sr = 
  name:sr-Latn = 
  network = icn|lcn|rcn
  ref = 
  route = bicycle
  type = route
  website = 
```
користећи overpass додавати путеве и знакове редоследом којим наилазе, од почетка руте  
```
[out:json];
(
  way[highway]({{bbox}});
  node[tourism=information][information=guidepost]({{bbox}});
);
out geom;
```

### EuroVelo 6 - Atlantic – Black Sea
https://en.eurovelo.com/ev6  
https://cycling.waymarkedtrails.org/#route?id=2938  
https://www.openstreetmap.org/relation/2938  
https://www.openstreetmap.org/relation/2764744 (Србија)  

### EuroVelo 11 - East Europe Route
Рута је у фази планирања на територији Србије.  
https://en.eurovelo.com/ev11  
https://cycling.waymarkedtrails.org/#route?id=2766981  
https://www.openstreetmap.org/relation/2766981  
део у Србији:  
https://www.openstreetmap.org/relation/11124003  
главна рута:  
https://www.openstreetmap.org/relation/11122764  
алтернативна рута:  
https://www.openstreetmap.org/relation/11123588  
```
  information = guidepost
  tourism = information
  bicycle=yes
  ref=
```

**дневник мапирања**
20200605, track 1591332700  
мото тура, Београд - Перлез  
20200517, track 1589720664  
наишли на знакове у Кањижи, постоји и мапа руте кроз Србију  


### EuroVelo 13 - Iron Curtain Trail
https://en.eurovelo.com/ev13  
https://cycling.waymarkedtrails.org/#route?id=1664664  
https://www.openstreetmap.org/relation/1664664  
https://www.openstreetmap.org/relation/9921011 (Мађарска / Србија)  
https://www.openstreetmap.org/relation/10290907 (Србија / Румунија)   
https://www.openstreetmap.org/relation/9241810 (Србија)  

### Danube Cycle Path
https://cycling.waymarkedtrails.org/#route?id=103398  
https://www.openstreetmap.org/relation/103398  
https://www.openstreetmap.org/relation/2910889 (Србија)  

### Рута Сава
Нисам успео да нађем званични сајт. Почео са мапирањем на терену, дневник у наставку.  

Тренутно стање:  
Граница Србија - Сремска Митровица - није мапирано  
Сремска Митровица - Скела - мапирано  
Купиново - Бољевац - није мапирано  
Бољевац - Ушће у Дунаб - мапирано  
Детур Обедска бара - мапирано  

**Део кроз Словенију**  

**Део кроз Хрватску**  

**Део кроз Босну и Херцеговину**  

**Део кроз Србију**  
https://cycling.waymarkedtrails.org/#route?id=10833727  
https://www.openstreetmap.org/relation/10833727  
Детур Обердска бара  
https://www.openstreetmap.org/relation/10967738  

### Еко Тамиш
Наишли на бициклистичку стазу у селу Ботош, води преко Томашевца, Уздина до старог моста, нов пут.
Знакови обележене са @eko-tamis, није унешено у ОСМ.   
https://www.ecotamis.rs  
http://eco-tamis.com/  
http://www.romania-serbia.net/?page_id=218  
http://www.romania-serbia.net/wp-content/uploads/lists/fise/2call/RORS-39.docx  

**Корисни линкови:**  
http://sava.pedala.hr  
http://www.ciklonaut.com/index.htm  
https://www.slavonia-bike.com/ruta/308-ruta-sava/  

**одржавање**  
оборен знак у Јакову, 710  
Купиново, Тарзан плажа, 600, недостају табле, делује као да главна рута иде на плажу  
скела је на мапи 550 а на путоказу пре 560  
знак 530, 540, делује да су обрнути бројеви  

**наставак**  
мапирати део од Сремске Митровице до границе  
мапирати алтернативну руту преко Обреновца  
мапирати скелу, проверити пре како функционише  

**Дневник мапирања:**  
20200320, track 1584692828  
Обишао мотором део од Бољевца до Купинова, прелазак Саве. Мапирао и секцију око Обедске баре.  

20200318, track 1584517825  
Обишао мотором део од Скеле, прелазак Саве код Обреновца преко Шапца до Сремске Митровице.  

20200314, track 1584178724  
Обишао бајсем туру од ушћа Саве до Бољевца, мапирао путоказе касније додао и руту.  


### Београд

#### Рута 2
Наишао на путоказе, рута 2. На Савском кеју пре почетка насипа, у Јакову пре укључења на главни пут из правца Браће Путниковић, у Бољевцу поред бензинске, валда води у Бојчинску шуму.

#### МТБ Баберијус
Бициклистичка стаза у околини Раље.  
http://ciklonaut.com/svrbiguz/baberijus/mtb_baberijus.htm  
https://www.zvoncara.com/mtb-kros-u-srcu-sumadije-baberijus/  
https://www.mtbproject.com/trail/7020417/baberijus  
[знак са мапом стазе](https://commons.wikimedia.org/wiki/File:%D0%9C%D0%A2%D0%91_%D0%A1%D1%82%D0%B0%D0%B7%D0%B0_%D0%91%D0%B0%D0%B1%D0%B5%D1%80%D0%B8%D1%98%D1%83%D1%81.jpeg)  
https://www.openstreetmap.org/relation/11227980  

### Дрина
Постоји знак и на Дивчибарама, Каона, изнад каменолома. Креирана рута.  
https://www.openstreetmap.org/relation/11161806  
Видео знак негде и у Кузманимa, село поред Радановаца, Косјерић.  
https://www.openstreetmap.org/node/7320378820  
референце:  
https://www.zvoncara.com/na-povlenu-dovoljno-je-da-pratite-znakove-pored-puta/


### Дивчибаре
Зелена стаза  
https://www.openstreetmap.org/relation/10948917  
Плава стаза  
https://www.openstreetmap.org/relation/10948905  
Магента стаза  
https://www.openstreetmap.org/relation/10950281  

**инфо**  
Постоје три кружне стазе на Дивчибарама, обележене су кружним таблама у три боје.  
https://divcibareskiresort.com/biciklisticke-staze/  
http://tov.rs/divcibare/  
http://tov.rs/wp-content/uploads/2019/11/DIVCIBARE-PROSPEKT.pdf  
http://www.divcibare.rs/wp-content/uploads/2018/02/Mapa-biciklistickih-staza.pdf  
http://vilaplamenac.rs/wp-content/uploads/2017/08/Mapa-biciklistickih-staza-001-1.jpg  

**дневник мапирања**  
20200402  
креирао три руте према трацковима ( 1584797934, 1582967020 ) и локацијама знакова.  


### Косјерић, Дивчибаре, Западна Србија
Постоје табле у Косјерићу, Каони и Дивчибарама.  
https://pedja.supurovic.net/karta-biciklistickih-staza-zlatibor-mokra-gora-tara/  
https://pedja.supurovic.net/biciklisticka-ruta-carska-tara/?lang=lat  
https://www.mapa.iz.rs/  
https://sumskirajdivcibare.com/wp-content/uploads/2014/08/biciklisticke_rute_zapadne_srbije.pdf  
https://www.zlatibor.rs/sites/default/files/vesti_preuzmi/16975/mapa_bikestaze.pdf  

### Овчар и Каблар
постоји штампани водич и апликација  
https://www.wikiloc.com/mountain-biking-trails/kablar-bike-trail-3517693  
https://www.wikiloc.com/mountain-biking-trails/biciklisticka-ruta-ovcar-bike-trail-ovcar-cicling-route-srb-eng-deu-rus-21687621  

### Бициклистичка рута Срем
https://srembike.rs/rs/rute/glavna-biciklisticka-ruta-srem  


