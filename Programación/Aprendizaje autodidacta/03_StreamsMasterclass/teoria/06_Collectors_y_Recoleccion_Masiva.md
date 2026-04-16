# Collectors: La Fábrica de Recolección Masiva

## Resumen de Todos los Collectors

El método terminal `.collect()` recibe un `Collector` que define CÓMO embotellar el resultado del Stream.

## Recolección Básica

### `Collectors.toList()` / `.toList()` (Java 16+)
```java
List<String> nombres = empleados.stream()
    .map(Empleado::getNombre)
    .collect(Collectors.toList());
```

### `Collectors.toSet()`
```java
Set<String> departamentosUnicos = empleados.stream()
    .map(Empleado::getDepartamento)
    .collect(Collectors.toSet());
```

### `Collectors.toMap(keyMapper, valueMapper)`
```java
Map<String, Double> nombreSalario = empleados.stream()
    .collect(Collectors.toMap(Empleado::getNombre, Empleado::getSalario));
```

**Problema:** Si hay claves duplicadas, lanza `IllegalStateException`. Solución:
```java
Map<String, Double> nombreSalario = empleados.stream()
    .collect(Collectors.toMap(
        Empleado::getNombre, 
        Empleado::getSalario,
        (existente, nuevo) -> existente  // Función de merge para duplicados
    ));
```

## Recolección de Agrupación

### `Collectors.groupingBy(clasificador)`
```java
Map<String, List<Empleado>> porDepto = empleados.stream()
    .collect(Collectors.groupingBy(Empleado::getDepartamento));
```

### `groupingBy` con downstream collector (segundo argumento)
```java
// Contar por departamento
Map<String, Long> conteo = empleados.stream()
    .collect(Collectors.groupingBy(Empleado::getDepartamento, Collectors.counting()));

// Suma de salarios por departamento
Map<String, Double> sumaSalarios = empleados.stream()
    .collect(Collectors.groupingBy(
        Empleado::getDepartamento, 
        Collectors.summingDouble(Empleado::getSalario)
    ));

// Mejor pagado por departamento
Map<String, Optional<Empleado>> mejorPagado = empleados.stream()
    .collect(Collectors.groupingBy(
        Empleado::getDepartamento,
        Collectors.maxBy(Comparator.comparing(Empleado::getSalario))
    ));

// Solo nombres por departamento
Map<String, List<String>> nombresPorDepto = empleados.stream()
    .collect(Collectors.groupingBy(
        Empleado::getDepartamento,
        Collectors.mapping(Empleado::getNombre, Collectors.toList())
    ));
```

### `Collectors.partitioningBy(predicate)`
```java
Map<Boolean, List<Empleado>> actVsInact = empleados.stream()
    .collect(Collectors.partitioningBy(Empleado::isActivo));
```

## Recolección de Texto

### `Collectors.joining()`
```java
// Simple
String csv = nombres.stream().collect(Collectors.joining(", "));
// "Ana, Carlos, Lucía"

// Con prefijo y sufijo
String json = nombres.stream().collect(Collectors.joining(", ", "[", "]"));
// "[Ana, Carlos, Lucía]"
```

## Recolección Estadística

### `Collectors.summarizingDouble()` / `summarizingInt()` / `summarizingLong()`
```java
DoubleSummaryStatistics stats = empleados.stream()
    .collect(Collectors.summarizingDouble(Empleado::getSalario));

stats.getCount();    // Número de elementos
stats.getSum();      // Suma total
stats.getAverage();  // Media
stats.getMin();      // Mínimo
stats.getMax();      // Máximo
```

## Collectors Especializados

### `Collectors.toUnmodifiableList()` / `toUnmodifiableSet()` / `toUnmodifiableMap()`
Producen colecciones inmutables:
```java
List<String> inmutable = nombres.stream()
    .collect(Collectors.toUnmodifiableList());
// inmutable.add("X"); => UnsupportedOperationException
```

### `Collectors.toCollection(Supplier)` — Elegir tipo de colección
```java
TreeSet<String> nombresOrdenados = empleados.stream()
    .map(Empleado::getNombre)
    .collect(Collectors.toCollection(TreeSet::new));

LinkedList<Empleado> comoLinkedList = empleados.stream()
    .collect(Collectors.toCollection(LinkedList::new));
```

### `Collectors.collectingAndThen()` — Post-procesar el resultado
```java
// Recolectar en lista y luego hacerla inmutable
List<String> inmutable = empleados.stream()
    .map(Empleado::getNombre)
    .collect(Collectors.collectingAndThen(
        Collectors.toList(), 
        Collections::unmodifiableList
    ));
```

## Tabla Resumen

```
┌───────────────────────────────────────────────────────────────────┐
│  Collector                │  Resultado                           │
├───────────────────────────┼──────────────────────────────────────┤
│  toList()                 │  List<T>                             │
│  toSet()                  │  Set<T>                              │
│  toMap(k, v)              │  Map<K, V>                           │
│  toCollection(Supplier)   │  Collection personalizada            │
│  groupingBy(func)         │  Map<K, List<T>>                     │
│  groupingBy(f, downstream)│  Map<K, ???>                         │
│  partitioningBy(pred)     │  Map<Boolean, List<T>>               │
│  joining(delim)           │  String                              │
│  counting()               │  Long (downstream)                   │
│  summingDouble(func)      │  Double (downstream)                 │
│  averagingInt(func)       │  Double (downstream)                 │
│  maxBy(comp)              │  Optional<T> (downstream)            │
│  minBy(comp)              │  Optional<T> (downstream)            │
│  mapping(func, down)      │  ??? (downstream)                    │
│  summarizingDouble(func)  │  DoubleSummaryStatistics             │
│  collectingAndThen(c, f)  │  R (post-procesado)                  │
└───────────────────────────┴──────────────────────────────────────┘
```
