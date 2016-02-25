# jvm-ug

### OutOfMemoryError
W nieskończonej pętli dodawane były do listy obiekty typu String spowodowało to, że pamięć jaka była przeznaczona na wykonanie programu się wyczerpała, co poskutkowało wyrzuceniem wyjątku **OutOfMemoryError**.
Pamięć przeznaczoną na wykonanie programu można zwiększyć poprzez dodanie flagi `-Xmxsize`.


### StackOverflowError
Miejsce na stosie na którym były przetrzymywane informacje o wywołaniach wyczerpało się z powodu nieprzerwanego wywoływania się metody stackOverflowg.

### Wykonanie programu
```mvn exec:java```
