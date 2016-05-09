# jvm-ug

### Wykonanie programu
`mvn exec:java`

### Informacje o maszynie, na której wykonywany był test:
```
Procesor: 2,6 GHz Intel Core i7
Pamięć: 16 GB 1600 MHz DDR3
Dysk: SSD
System: OS X Yosemite 10.10.5
Java: 1.8.0_45
```

### Java:
```
java version "1.8.0_45"
Java(TM) SE Runtime Environment (build 1.8.0_45-b14)
Java HotSpot(TM) 64-Bit Server VM (build 25.45-b02, mixed mode)
```

### Testy:
```
Test 1: Alokacje w 1 wątku, obiekty o stałym rozmiarze
Test 2: Alokacje w 4 wątkach, obiekty o stałym rozmiarze
Test 3: Alokacje w 1 wątku, obiekty o zmiennym rozmiarze
Test 4: Alokacje w 4 wątkach, obiekty o zmiennym rozmiarze
```

### Test 1 Alokacje w 1 wątku, obiekty o stałym rozmiarze:
![alt tag](https://github.com/waveq/jvm-ug/blob/lab-09/charts/Screen%20Shot%202016-05-09%20at%2022.46.28.png)

![alt tag](https://github.com/waveq/jvm-ug/blob/lab-09/charts/Screen%20Shot%202016-05-09%20at%2022.47.28.png)


### Test 2: Alokacje w 4 wątkach, obiekty o stałym rozmiarze:
![alt tag](https://github.com/waveq/jvm-ug/blob/lab-09/charts/Screen%20Shot%202016-05-09%20at%2022.46.48.png)

![alt tag](https://github.com/waveq/jvm-ug/blob/lab-09/charts/Screen%20Shot%202016-05-09%20at%2022.47.38.png)

### Test 3: Alokacje w 1 wątku, obiekty o zmiennym rozmiarze:
![alt tag](https://github.com/waveq/jvm-ug/blob/lab-09/charts/Screen%20Shot%202016-05-09%20at%2022.46.56.png)

![alt tag](https://github.com/waveq/jvm-ug/blob/lab-09/charts/Screen%20Shot%202016-05-09%20at%2022.47.48.png)

### Test 4: Alokacje w 4 wątkach, obiekty o zmiennym rozmiarze:
![alt tag](https://github.com/waveq/jvm-ug/blob/lab-09/charts/Screen%20Shot%202016-05-09%20at%2022.47.14.png)

![alt tag](https://github.com/waveq/jvm-ug/blob/lab-09/charts/Screen%20Shot%202016-05-09%20at%2022.47.56.png)
