# Métodos por Referencia (Method References `::`)

## ¿Qué son?

Son un atajo sintáctico para Lambdas que **solamente llaman a un método existente sin añadir lógica extra**. Si tu Lambda es tan simple que literalmente solo invoca un método, puedes reemplazarla por `::`.

```java
// Lambda completa:
.map(e -> e.getNombre())

// Method Reference equivalente:
.map(Empleado::getNombre)
```

El compilador deduce que `Empleado::getNombre` significa: "toma cada elemento del stream (que es un Empleado) y llama a su método getNombre()".

## Los 4 Tipos de Method Reference

### 1. Referencia a Método de Instancia de un Tipo Arbitrario
**Patrón:** `Clase::metodoDeInstancia`

El objeto sobre el que se invoca el método viene del propio Stream.

```java
// Lambda:                     Method Reference:
e -> e.getNombre()             Empleado::getNombre
s -> s.toUpperCase()           String::toUpperCase
s -> s.length()                String::length
e -> e.isActivo()              Empleado::isActivo
```

**Uso masivo en:** `.map()`, `.filter()`, `.sorted(Comparator.comparing(...))`

### 2. Referencia a Método de una Instancia Concreta
**Patrón:** `objeto::metodoDeInstancia`

El objeto ya existe y está fijado. La Lambda solo le pasa parámetros.

```java
// Lambda:                         Method Reference:
x -> System.out.println(x)        System.out::println
x -> miLista.add(x)               miLista::add
x -> miValidador.validar(x)       miValidador::validar
```

**Uso masivo en:** `.forEach()`, `.peek()`

### 3. Referencia a Método Estático
**Patrón:** `Clase::metodoEstatico`

```java
// Lambda:                              Method Reference:
(a, b) -> Integer.compare(a, b)        Integer::compare
s -> Integer.parseInt(s)               Integer::parseInt
d -> Math.abs(d)                       Math::abs
(a, b) -> Double.max(a, b)            Double::max
```

**Uso masivo en:** `.map()`, `.reduce()`, Comparators

### 4. Referencia a Constructor
**Patrón:** `Clase::new`

```java
// Lambda:                          Method Reference:
() -> new ArrayList<>()            ArrayList::new
s -> new StringBuilder(s)          StringBuilder::new
```

**Uso masivo en:** `.collect()`, `Supplier` para Optional

## Tabla de Conversión Rápida

```
┌──────────────────────────────────────────────────────────────────┐
│  LAMBDA                          │  METHOD REFERENCE             │
├──────────────────────────────────┼───────────────────────────────┤
│  e -> e.getNombre()              │  Empleado::getNombre          │
│  e -> e.isActivo()               │  Empleado::isActivo           │
│  s -> s.toUpperCase()            │  String::toUpperCase          │
│  s -> s.length()                 │  String::length               │
│  x -> System.out.println(x)     │  System.out::println          │
│  s -> Integer.parseInt(s)        │  Integer::parseInt            │
│  (a,b) -> Integer.compare(a,b)  │  Integer::compare             │
│  (a,b) -> a.compareTo(b)        │  String::compareTo            │
│  () -> new ArrayList<>()         │  ArrayList::new               │
│  d -> Math.abs(d)                │  Math::abs                    │
└──────────────────────────────────┴───────────────────────────────┘
```

## ¿Cuándo NO puedes usar Method Reference?

Cuando la Lambda hace **algo más** que simplemente delegar la llamada a un método:

```java
// IMPOSIBLE convertir a :: porque hay lógica adicional
.filter(e -> e.getSalario() > 50000)           // Hay una comparación
.map(e -> e.getNombre() + " - " + e.getDepartamento())  // Hay concatenación
.filter(e -> !e.getNombre().isEmpty())          // Hay negación
.sorted((a, b) -> b.getSalario() - a.getSalario())  // Hay aritmética
```

En estos casos, la Lambda es obligatoria porque el `::` no puede expresar operaciones intermedias.

## Method Reference en Comparator.comparing()

Una de las combinaciones más elegantes de Java moderno:

```java
// Paso 1: Lambda tradicional
lista.sort((a, b) -> a.getNombre().compareTo(b.getNombre()));

// Paso 2: Comparator.comparing con Lambda
lista.sort(Comparator.comparing(e -> e.getNombre()));

// Paso 3: Comparator.comparing con Method Reference (CIMA)
lista.sort(Comparator.comparing(Empleado::getNombre));

// Multicriterio con encadenamiento:
lista.sort(Comparator.comparing(Empleado::getDepartamento)
                      .thenComparing(Empleado::getSalario)
                      .reversed());
```
