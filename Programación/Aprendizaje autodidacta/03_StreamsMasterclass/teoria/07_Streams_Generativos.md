# 07 — Streams Generativos: generate, iterate y fuentes infinitas

## ¿Qué es un Stream generativo?

Hasta ahora siempre has creado streams desde colecciones existentes (`lista.stream()`).
Pero Java permite **crear streams desde la nada**, generando valores bajo demanda.

---

## Stream.generate(Supplier)

Crea un stream **infinito** donde cada elemento lo produce un `Supplier<T>`:

```java
// Stream infinito de números aleatorios
Stream.generate(Math::random)       // infinito
      .limit(5)                     // ¡SIEMPRE limitar!
      .forEach(System.out::println);

// Stream infinito de un valor constante
Stream.generate(() -> "Hola")
      .limit(3)
      .collect(Collectors.toList()); // ["Hola", "Hola", "Hola"]
```

> **CUIDADO:** Sin `.limit()`, un stream infinito nunca termina.

---

## Stream.iterate(seed, UnaryOperator)

Crea un stream infinito donde cada elemento se calcula a partir del anterior:

```java
// 0, 2, 4, 6, 8, ...
Stream.iterate(0, n -> n + 2)
      .limit(5)
      .forEach(System.out::println);

// Potencias de 2: 1, 2, 4, 8, 16, 32, ...
Stream.iterate(1, n -> n * 2)
      .limit(6)
      .collect(Collectors.toList());
```

### Java 9+: iterate con predicado (hasNext)

```java
// Equivalente a un for(int i = 1; i <= 100; i *= 2)
Stream.iterate(1, n -> n <= 100, n -> n * 2)
      .forEach(System.out::println); // 1, 2, 4, 8, 16, 32, 64
```

---

## IntStream.range / rangeClosed (repaso)

```java
IntStream.range(0, 5)         // 0, 1, 2, 3, 4
IntStream.rangeClosed(1, 5)   // 1, 2, 3, 4, 5
```

---

## UnaryOperator y BinaryOperator

Son interfaces funcionales especializadas de `Function`:

| Interfaz | Firma | Equivale a |
|---|---|---|
| `UnaryOperator<T>` | `T apply(T t)` | `Function<T, T>` |
| `BinaryOperator<T>` | `T apply(T t1, T t2)` | `BiFunction<T, T, T>` |

```java
UnaryOperator<Integer> duplicar = n -> n * 2;
BinaryOperator<Integer> sumar = (a, b) -> a + b;

// iterate usa UnaryOperator internamente
Stream.iterate(1, duplicar).limit(5); // 1, 2, 4, 8, 16

// reduce usa BinaryOperator internamente
List.of(1,2,3,4).stream().reduce(0, sumar); // 10
```

---

## Patrones útiles

### Fibonacci con iterate
```java
Stream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
      .limit(10)
      .map(f -> f[0])
      .forEach(System.out::println); // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
```

### Generador con estado (AtomicInteger)
```java
AtomicInteger counter = new AtomicInteger(0);
Stream.generate(counter::incrementAndGet)
      .limit(5)
      .forEach(System.out::println); // 1, 2, 3, 4, 5
```

---

## Resumen

| Método | Tipo | Uso típico |
|---|---|---|
| `Stream.generate(supplier)` | Infinito | Valores aleatorios, constantes, con estado |
| `Stream.iterate(seed, op)` | Infinito | Secuencias matemáticas, progresiones |
| `Stream.iterate(seed, pred, op)` | Finito (Java 9+) | Secuencias con condición de parada |
| `IntStream.range/rangeClosed` | Finito | Rangos de enteros |
