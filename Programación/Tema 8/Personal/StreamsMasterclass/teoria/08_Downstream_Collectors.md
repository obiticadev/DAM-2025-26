# 08 — Downstream Collectors: Collectors dentro de Collectors

## ¿Qué es un Downstream Collector?

Cuando usas `groupingBy`, el segundo argumento es un **downstream collector**: un Collector
que se aplica a cada grupo después de agrupar.

Por defecto, `groupingBy(clasificador)` usa `toList()` como downstream.
Pero puedes cambiarlo por cualquier otro Collector.

---

## groupingBy con downstream

### Contar elementos por grupo
```java
Map<String, Long> conteo = empleados.stream()
    .collect(Collectors.groupingBy(
        Empleado::getDepartamento,
        Collectors.counting()          // downstream
    ));
// {Backend=3, Frontend=2, Data=1}
```

### Sumar valores por grupo
```java
Map<String, Double> sumaSalarios = empleados.stream()
    .collect(Collectors.groupingBy(
        Empleado::getDepartamento,
        Collectors.summingDouble(Empleado::getSalario)
    ));
```

### Máximo/Mínimo por grupo
```java
Map<String, Optional<Empleado>> mejorPagado = empleados.stream()
    .collect(Collectors.groupingBy(
        Empleado::getDepartamento,
        Collectors.maxBy(Comparator.comparing(Empleado::getSalario))
    ));
```

### Media por grupo
```java
Map<String, Double> mediaSalarial = empleados.stream()
    .collect(Collectors.groupingBy(
        Empleado::getDepartamento,
        Collectors.averagingDouble(Empleado::getSalario)
    ));
```

### Transformar los valores del grupo con mapping
```java
Map<String, List<String>> nombresPorDepto = empleados.stream()
    .collect(Collectors.groupingBy(
        Empleado::getDepartamento,
        Collectors.mapping(Empleado::getNombre, Collectors.toList())
    ));
// {Backend=[Ana, Luis], Frontend=[Carlos]}
```

### Unir strings por grupo con joining
```java
Map<String, String> resumen = empleados.stream()
    .collect(Collectors.groupingBy(
        Empleado::getDepartamento,
        Collectors.mapping(Empleado::getNombre, Collectors.joining(", "))
    ));
// {Backend="Ana, Luis", Frontend="Carlos"}
```

---

## Collectors.summarizing[Int|Long|Double]

Calcula estadísticas completas de una sola pasada:

```java
DoubleSummaryStatistics stats = empleados.stream()
    .collect(Collectors.summarizingDouble(Empleado::getSalario));

stats.getCount();    // número de elementos
stats.getSum();      // suma total
stats.getMin();      // valor mínimo
stats.getMax();      // valor máximo
stats.getAverage();  // media aritmética
```

### Combinado con groupingBy
```java
Map<String, DoubleSummaryStatistics> statsPorDepto = empleados.stream()
    .collect(Collectors.groupingBy(
        Empleado::getDepartamento,
        Collectors.summarizingDouble(Empleado::getSalario)
    ));
```

---

## Collector.of() — Collector personalizado

Puedes crear tu propio Collector con `Collector.of()`:

```java
Collector<Empleado, StringJoiner, String> miCollector = Collector.of(
    () -> new StringJoiner(", "),           // supplier: crea el acumulador
    (sj, e) -> sj.add(e.getNombre()),       // accumulator: añade al acumulador
    (sj1, sj2) -> sj1.merge(sj2),           // combiner: para parallel streams
    StringJoiner::toString                   // finisher: resultado final
);

String nombres = empleados.stream().collect(miCollector);
// "Ana, Carlos, Lucía"
```

Los 4 componentes son:
1. **Supplier**: Crea el contenedor mutable inicial
2. **Accumulator**: Añade un elemento al contenedor
3. **Combiner**: Fusiona dos contenedores (para paralelismo)
4. **Finisher**: Transforma el contenedor en el resultado final

---

## Encadenamiento multinivel

Puedes anidar `groupingBy` para agrupaciones multinivel:

```java
Map<String, Map<Boolean, List<Empleado>>> porDeptoYActividad =
    empleados.stream().collect(
        Collectors.groupingBy(
            Empleado::getDepartamento,
            Collectors.partitioningBy(Empleado::isActivo)
        )
    );
// {Backend={true=[Ana, Luis], false=[Pedro]}, ...}
```

---

## Resumen de Downstream Collectors

| Downstream | Resultado | Uso |
|---|---|---|
| `counting()` | `Long` | Contar por grupo |
| `summingDouble(fn)` | `Double` | Sumar un campo |
| `averagingDouble(fn)` | `Double` | Media de un campo |
| `maxBy(comparator)` | `Optional<T>` | Máximo por grupo |
| `minBy(comparator)` | `Optional<T>` | Mínimo por grupo |
| `mapping(fn, downstream)` | Variable | Transformar antes de recoger |
| `joining(delim)` | `String` | Concatenar strings |
| `toSet()` | `Set<T>` | Recoger en Set |
| `summarizingDouble(fn)` | `DoubleSummaryStatistics` | Estadísticas completas |
| `collectingAndThen(dc, fn)` | Variable | Post-procesar resultado |
