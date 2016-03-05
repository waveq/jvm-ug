# jvm-ug

### Wykonanie programu
`mvn exec:java`

### Przebieg testu: 
Program iterował dziesięciokrotnie w pętli, w każdej z nich wykonywane były następujące czynności: `odczytanie publicznej wartości`, `zapisanie publicznej wartości` oraz `wywołanie metody z jednym argumentem`, zarówno standardowym jak i refleksyjnym sposobem. Każda z czynności wykonywana była 10000000 razy.

Najgorsze i najlepsze czasy nie są uwzględniane w wynikach. 

Aplikacja została uruchomiona w środowisku IntelliJ IDEA.


```
Native get public value, averaged:            7486064 ns = 0.007 s
Native set public value, averaged:            8046402 ns = 0.008 s
Native invoke method, averaged:               5874937 ns = 0.005 s

Reflection get public value, averaged:        1636231257 ns = 1.636 s
Reflection set public value, averaged:        1683529238 ns = 1.683 s
Reflection invoke method, averaged:           5048142527 ns = 5.048 s
```
Łatwo można obliczyć, że dla `odczytywania wartości`, `ustawiania wartości` i `wywoływania metody`, natywny sposób jest odpowiednio **`218`**, **`209`** i **`859`** razy szybszy od wykonania tego samego za pomocą refleksji.
