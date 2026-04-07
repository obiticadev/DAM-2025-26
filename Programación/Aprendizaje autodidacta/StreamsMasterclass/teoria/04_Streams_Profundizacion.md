# Streams: Profundización Completa

## Operaciones Intermedias Avanzadas

### `.flatMap()` — El Aplanador de Colecciones Anidadas

Cuando tienes un Stream de Listas (o cualquier colección dentro de otra), `.map()` te daría un `Stream<List<T>>`. Eso no sirve. Necesitas un `Stream<T>` plano.

`.flatMap()` toma cada elemento, lo convierte en un Stream, y FUSIONA todos esos mini-streams en uno solo.

```java
// Problema: Lista de Pedidos, cada uno con Lista de Productos
List<Pedido> pedidos = ...;

// MAL: .map() te da Stream<List<Producto>>
pedidos.stream().map(Pedido::getProductos); // Stream<List<Producto>> 😱

// BIEN: .flatMap() aplana todo a Stream<Producto>
pedidos.stream().flatMap(p -> p.getProductos().stream()); // Stream<Producto> ✅

// Ejemplo real completo: Todos los nombres de productos de todos los pedidos
List<String> todosLosProductos = pedidos.stream()
    .flatMap(pedido -> pedido.getProductos().stream())
    .map(Producto::getNombre)
    .distinct()
    .collect(Collectors.toList());
```

```
┌─────────────────────────────────────────────────────────┐
│  Pedido1 -> [ProdA, ProdB]                              │
│  Pedido2 -> [ProdC]                                     │
│  Pedido3 -> [ProdD, ProdE, ProdF]                       │
│                                                         │
│  .map(Pedido::getProductos)                             │
│  => Stream<List<Producto>>: [[A,B], [C], [D,E,F]]      │
│                                                         │
│  .flatMap(p -> p.getProductos().stream())               │
│  => Stream<Producto>: [A, B, C, D, E, F]    ← PLANO    │
└─────────────────────────────────────────────────────────┘
```

### `.peek()` — El Espía de la Tubería

Ejecuta una acción sobre cada elemento SIN modificar el stream. Perfecto para depuración:

```java
lista.stream()
    .filter(e -> e.isActivo())
    .peek(e -> System.out.println("Pasó filtro: " + e))  // DEBUG
    .map(Empleado::getNombre)
    .collect(Collectors.toList());
```

### `.distinct()` — Eliminación de Duplicados

Usa `equals()` y `hashCode()` del objeto para detectar duplicados.

### `.limit(n)` y `.skip(n)` — Paginación

```java
// Simular paginación: Página 3 con 10 elementos por página
lista.stream().skip(20).limit(10).collect(Collectors.toList());
```

### `.mapToInt()`, `.mapToDouble()`, `.mapToLong()` — Streams Primitivos

Evitan el autoboxing y desbloquean métodos estadísticos:

```java
IntStream edades = empleados.stream().mapToInt(Empleado::getExperienciaAnios);
int suma = edades.sum();

DoubleSummaryStatistics stats = empleados.stream()
    .mapToDouble(Empleado::getSalario)
    .summaryStatistics();
// stats.getAverage(), stats.getMax(), stats.getMin(), stats.getSum(), stats.getCount()
```

## Operaciones Terminales Avanzadas

### `.reduce()` — La Operación Terminal Suprema

Combina todos los elementos del stream en uno solo mediante una función acumuladora.

```java
// Forma 1: Con valor inicial (identidad)
double totalSalarios = empleados.stream()
    .mapToDouble(Empleado::getSalario)
    .reduce(0.0, (acumulador, salario) -> acumulador + salario);

// Forma 2: Sin identidad (devuelve Optional porque la lista podría estar vacía)
Optional<Double> maxSalario = empleados.stream()
    .map(Empleado::getSalario)
    .reduce(Double::max);

// Forma 3: Con Method Reference
int sumaExperiencia = empleados.stream()
    .mapToInt(Empleado::getExperienciaAnios)
    .reduce(0, Integer::sum);
```

### `.anyMatch()`, `.allMatch()`, `.noneMatch()` — Consultas Booleanas

```java
boolean hayJuniors = empleados.stream().anyMatch(Empleado::esJunior);
boolean todosActivos = empleados.stream().allMatch(Empleado::isActivo);
boolean nadieGanaPocoONada = empleados.stream().noneMatch(e -> e.getSalario() <= 0);
```

### `.findFirst()` y `.findAny()` — Búsqueda (devuelven Optional)

```java
Optional<Empleado> primerSenior = empleados.stream()
    .filter(Empleado::esSenior)
    .findFirst();

// Con streams paralelos, findAny es más eficiente
Optional<Empleado> cualquierSenior = empleados.parallelStream()
    .filter(Empleado::esSenior)
    .findAny();
```

### `.count()`, `.min()`, `.max()`

```java
long totalActivos = empleados.stream().filter(Empleado::isActivo).count();

Optional<Empleado> mejorPagado = empleados.stream()
    .max(Comparator.comparing(Empleado::getSalario));
```

## Collectors Avanzados

### `Collectors.partitioningBy()` — División Binaria

Igual que `groupingBy`, pero la clave es siempre `true/false`:

```java
Map<Boolean, List<Empleado>> particion = empleados.stream()
    .collect(Collectors.partitioningBy(Empleado::isActivo));

List<Empleado> activos = particion.get(true);
List<Empleado> inactivos = particion.get(false);
```

### `Collectors.joining()` — Concatenación Elegante

```java
String nombres = empleados.stream()
    .map(Empleado::getNombre)
    .collect(Collectors.joining(", ", "[", "]"));
// Resultado: "[Ana, Carlos, Lucía]"
```

### `Collectors.counting()`, `Collectors.summingDouble()`, `Collectors.averagingInt()`

Usados dentro de `groupingBy` como segundo argumento (downstream collector):

```java
// Contar empleados por departamento
Map<String, Long> cuentaPorDepto = empleados.stream()
    .collect(Collectors.groupingBy(Empleado::getDepartamento, Collectors.counting()));

// Suma de salarios por departamento
Map<String, Double> sumaSalarios = empleados.stream()
    .collect(Collectors.groupingBy(
        Empleado::getDepartamento, 
        Collectors.summingDouble(Empleado::getSalario)
    ));

// Media de experiencia por departamento
Map<String, Double> mediaExp = empleados.stream()
    .collect(Collectors.groupingBy(
        Empleado::getDepartamento, 
        Collectors.averagingInt(Empleado::getExperienciaAnios)
    ));
```

### `Collectors.toUnmodifiableList()` / `Collectors.toUnmodifiableSet()`

Producen colecciones inmutables (no se pueden añadir ni quitar elementos después):

```java
List<String> nombresInmutables = empleados.stream()
    .map(Empleado::getNombre)
    .collect(Collectors.toUnmodifiableList());
// nombresInmutables.add("X"); // UnsupportedOperationException!
```

## Streams Paralelos

```java
// Convertir un stream secuencial a paralelo
long count = listaGigante.parallelStream()
    .filter(e -> e.getSalario() > 50000)
    .count();
```

**Precaución:** Los streams paralelos usan el ForkJoinPool común. Solo úsalos con colecciones grandes (>10000 elementos) y operaciones sin estado. Nunca mutes estado compartido dentro de un stream paralelo.
