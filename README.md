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

### Przebieg testów:
```
Wykonane zostały testy serializacji dla 1, 10 oraz 10000 obiektów czterema metodami. Każdy z testów został wykonany 100 razy, by nieco uśrednić wyniki.
```
### Wyniki:
![alt tag](https://github.com/waveq/jvm-ug/blob/lab-09//charts/1%20object%20serialization.png)

![alt tag](https://github.com/waveq/jvm-ug/blob/lab-09/charts/10%20objects%20serialization.png)

![alt tag](https://github.com/waveq/jvm-ug/blob/lab-09/charts/10000%20objects%20serialization.png)
