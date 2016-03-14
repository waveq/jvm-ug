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

### Przebieg testu:
Program iterował dziesięciokrotnie w pętli, w każdej z nich wykonywane były następujące czynności: `odczytanie publicznej wartości`, `zapisanie publicznej wartości` oraz `wywołanie metody z jednym argumentem`, zarówno standardowym jak i refleksyjnym sposobem. Każda z czynności wykonywana była 10000000 razy.

Najgorsze i najlepsze czasy nie są uwzględniane w wynikach.

Aplikacja została uruchomiona w środowisku IntelliJ IDEA.


