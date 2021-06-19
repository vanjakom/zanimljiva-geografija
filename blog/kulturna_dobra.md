# Споменици културе у Србији  

[Tag:heritage](https://wiki.openstreetmap.org/wiki/Key:heritage)  
[Tag:boundary=administrative](https://wiki.openstreetmap.org/wiki/Tag:boundary%3Dadministrative)  

## Републички завод за заштиту споменика културе  
http://www.heritage.gov.rs/cirilica/nepokretna_kulturna_dobra.php  

### СПОМЕНИЦИ КУЛТУРЕ  
http://www.heritage.gov.rs/cirilica/Download/SK.xls  
https://sr.wikipedia.org/wiki/Списак_споменика_културе_у_Србији  
https://sr.wikipedia.org/wiki/Списак_споменика_културе_у_Београду  


### ПРОСТОРНО КУЛТУРНО ИСТОРИЈСКЕ ЦЕЛИНЕ  
http://www.heritage.gov.rs/cirilica/Download/PKIC.xls  

### АРХЕОЛОШКА НАЛАЗИШТА  
http://www.heritage.gov.rs/cirilica/Download/AN.xls  

### ЗНАМЕНИТА МЕСТА  
http://www.heritage.gov.rs/cirilica/Download/ZM.xls  

### НКД ОД ИЗУЗЕТНОГ ЗНАЧАЈА  
http://www.heritage.gov.rs/cirilica/Download/Kategorisani_izuz.xls  
https://sr.wikipedia.org/wiki/Списак_споменика_културе_од_изузетног_значаја  

### НКД ОД ВЕЛИКОГ ЗНАЧАЈА  
http://www.heritage.gov.rs/cirilica/Download/Kategorisani_veliki.xls  

## Unesco World Heritage  
https://whc.unesco.org/en/statesparties/rs  

overpass  
```
[out:json];
nwr[heritage=1]["heritage:operator"=whc](area:3609088937);
out center;
```
[overpass-turbo](https://overpass-turbo.eu/?Q=%5Bout%3Ajson%5D%3B%0Anwr%5Bheritage%3D1%5D%5B%22heritage%3Aoperator%22%3Dwhc%5D%28area%3A3609088937%29%3B%0Aout%20center%3B)  

## како мапирати?  
https://wiki.openstreetmap.org/wiki/Key:heritage#Serbia  
https://sr.wikipedia.org/wiki/Списак_споменика_културе_у_Србији  
https://sr.wikipedia.org/wiki/Списак_споменика_културе_у_Београду  
https://www.openstreetmap.org/way/295686327  
```
heritage = 2
heritage:operator = rzzsk
heritage:ref = SK 2229

name = Зграда Хемпро
name:sr = Зграда Хемпро
name:sr-latn = Zgrada Hempro

wikipedia = sr:Зграда_Хемпро
wikidata = Q64049210
```

## референце  
[Споменици културе у Србији](http://spomenicikulture.mi.sanu.ac.rs)  
