# 10. Composición funcional, Parallel Streams y técnicas maestras

## 10.1 Function.compose() y Function.andThen()

`Function<T, R>` tiene dos métodos de composición que permiten encadenar transformaciones:

```java
Function<String, String> trim = String::trim;
Function<String, String> upper = String::toUpperCase;
Function<String, Integer> length = String::length;

// andThen: ejecuta PRIMERO la función actual, LUEGO la siguiente
Function<String, String> trimYUpper = trim.andThen(upper);
trimYUpper.apply("  hola  ");  // → "HOLA"

// compose: ejecuta PRIMERO el argumento, LUEGO la función actual
Function<String, Integer> upperYLength = length.compose(upper);
upperYLength.apply("hola");  // → 4
```

### Orden de ejecución

```
andThen:  A.andThen(B)  →  primero A, luego B  →  B(A(x))
compose:  A.compose(B)  →  primero B, luego A  →  A(B(x))
```

### Uso con streams

```java
Function<Empleado, String> nombre = Empleado::getNombre;
Function<String, String> mayusculas = String::toUpperCase;
Function<String, String> decorar = s -> "★ " + s + " ★";

Function<Empleado, String> pipeline = nombre.andThen(mayusculas).andThen(decorar);

empleados.stream()
    .map(pipeline)
    .forEach(System.out::println);
// ★ ANA GARCÍA ★
// ★ LUIS PÉREZ ★
```

## 10.2 UnaryOperator como Function especializada

`UnaryOperator<T>` extiende `Function<T, T>` (mismo tipo entrada y salida).
Soporta `compose` y `andThen`:

```java
UnaryOperator<String> trim = String::trim;
UnaryOperator<String> upper = String::toUpperCase;
UnaryOperator<String> exclamar = s -> s + "!";

// Encadenar: trim → upper → exclamar
Function<String, String> transformar = trim.andThen(upper).andThen(exclamar);
transformar.apply("  hola  ");  // → "HOLA!"
```

## 10.3 Parallel Streams — Introducción

Los streams pueden ejecutarse en **paralelo**, dividiendo el trabajo entre múltiples hilos:

```java
// Dos formas de crear un parallel stream:
lista.parallelStream()          // directamente desde la colección
lista.stream().parallel()       // convirtiendo un stream secuencial

// Volver a secuencial:
stream.sequential()
```

### ¿Cuándo usar parallel?

| ✅ Usar cuando                          | ❌ Evitar cuando                          |
|-----------------------------------------|-------------------------------------------|
| Colecciones MUY grandes (>100k)         | Colecciones pequeñas (overhead > ganancia) |
| Operaciones CPU-intensivas              | Operaciones con I/O (ficheros, red)       |
| Datos independientes entre sí           | Estado compartido/mutable                 |
| ArrayList, arrays (buen split)          | LinkedList (mal split)                    |

### Ejemplo básico

```java
long inicio = System.currentTimeMillis();

long cuenta = IntStream.rangeClosed(1, 10_000_000)
    .parallel()
    .filter(n -> esPrimo(n))
    .count();

long fin = System.currentTimeMillis();
System.out.println("Primos: " + cuenta + " en " + (fin - inicio) + "ms");
```

## 10.4 Reduce en parallel streams

Con streams paralelos, `reduce` necesita que la operación sea **asociativa**:

```java
// ✅ CORRECTO — suma es asociativa: (a+b)+c == a+(b+c)
int total = lista.parallelStream()
    .reduce(0, Integer::sum);

// ❌ PELIGROSO — resta NO es asociativa
int mal = lista.parallelStream()
    .reduce(0, (a, b) -> a - b);  // resultado impredecible
```

### reduce con 3 argumentos (para parallel)

```java
// reduce(identidad, acumulador, combinador)
int sumaEdades = empleados.parallelStream()
    .reduce(
        0,                                          // identidad
        (parcial, emp) -> parcial + emp.getEdad(),  // acumulador
        Integer::sum                                // combinador (une resultados parciales)
    );
```

## 10.5 forEachOrdered vs forEach (en paralelo)

```java
// forEach en parallel → orden NO garantizado
List.of(1,2,3,4,5).parallelStream()
    .forEach(System.out::print);  // Posible: 31245

// forEachOrdered → mantiene orden original
List.of(1,2,3,4,5).parallelStream()
    .forEachOrdered(System.out::print);  // Siempre: 12345
```

## 10.6 Supplier<Stream<T>> — Streams reutilizables

Un stream solo se puede consumir UNA vez. Para "reutilizarlo", usa un `Supplier`:

```java
Supplier<Stream<Empleado>> streamActivos = () -> 
    empleados.stream().filter(Empleado::isActivo);

// Usar múltiples veces:
long count = streamActivos.get().count();
double media = streamActivos.get().mapToDouble(Empleado::getSalario).average().orElse(0);
streamActivos.get().forEach(System.out::println);
```

## 10.7 Patrón: cadena de transformaciones reutilizable

```java
// Definir transformaciones como funciones componibles
Function<Stream<Empleado>, Stream<Empleado>> soloActivos = 
    s -> s.filter(Empleado::isActivo);
Function<Stream<Empleado>, Stream<String>> aNombres = 
    s -> s.map(Empleado::getNombre);
Function<Stream<Empleado>, Stream<String>> pipeline =
    soloActivos.andThen(aNombres);

pipeline.apply(empleados.stream()).forEach(System.out::println);
```

## 10.8 Collectors.collectingAndThen avanzado

```java
// Resultado inmutable
List<String> inmutable = stream.collect(
    Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));

// Extraer el primero de un grupo
Map<String, Empleado> mejorPorDepto = empleados.stream()
    .collect(Collectors.groupingBy(
        Empleado::getDepartamento,
        Collectors.collectingAndThen(
            Collectors.maxBy(Comparator.comparing(Empleado::getSalario)),
            Optional::get
        )
    ));
```
