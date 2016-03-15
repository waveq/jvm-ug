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
Program iterował dziesięciokrotnie w pętli, w każdej z nich wykonywane było parsowanie klasy do jsona za pomoca refleksji, `Gsona` i `Jacksona`. W każdym przebiegu pętli parsowanie wykonywane było 1000000 razy, każdym sposobem.


### Wyniki:
Najgorsze i najlepsze czasy nie są uwzględniane w wynikach.
```
Removing worst result from Reflection:  1806903300 ns  - which was in 0 iteration
Removing best result from Reflection:   1192563508 ns  - which was in 9 iteration

Removing worst result from Gson:        9315343605 ns  - which was in 0 iteration
Removing best result from Gson:         8503716275 ns  - which was in 2 iteration

Removing worst result from Jackson:     824210463 ns   - which was in 0 iteration
Removing best result from Jackson:      769223448 ns   - which was in 1 iteration
```

Aplikacja została uruchomiona w środowisku IntelliJ IDEA.
```
Reflection, averaged:   1245962980 ns
Gson, averaged:         8626714761 ns
Jackson, averaged:      782904115 ns
```

Parsowany [obiekt](src/main/java/object/FunnyObject.java) zawierał zarówno publiczne jak i prywatne pola typu: `String`, `int`, `long` i `Integer`. Niektóre z prywatnych pól nie miały getterów, więc trzeba było ustawić im odpowiedni dostęp.

Gson był prawie **7** razy wolniejszy niż wykonanie tego samego autorskim sposobem. Jackson był natomiast **11** razy szybszy od refleksyjnego betonu.

### Output generowany przez biblioteki:
[Refleksja](output/reflection.json)

[Gson](output/gson.json)

[Jackson](output/jackson.json)


