# jvm-ug

### Wykonanie programu
`mvn exec:java`

### Przebieg testu: 
Program iterował dziesięciokrotnie w pętli, w każdej z nich wykonywane były następujące czynności: `odczytanie publicznej wartości`, `zapisanie publicznej wartości` oraz `wywołanie metody z jednym argumentem`, zarówno standardowym jak i refleksyjnym sposobem. Każda z czynności wykonywana była 10000000 razy.

Najgorsze i najlepsze czasy nie są uwzględniane w wynikach. 

Aplikacja została uruchomiona ze środowiska IntelliJ IDEA.

```
Native get public value, averaged:            7486064
Native set public value, averaged:            8046402
Native invoke method, averaged:               5874937

Reflection get public value, averaged:        1636231257
Reflection set public value, averaged:        1683529238
Reflection invoke method, averaged:           5048142527
```
Łatwo można obliczyć, że dla `odczytywania wartości`, `ustawiania wartości` i `wywoływania metody`, natywny sposób jest odpowiednio **`218`**, **`209`** i **`859`** razy szybszy od wykonania tego samego za pomocą refleksji.
