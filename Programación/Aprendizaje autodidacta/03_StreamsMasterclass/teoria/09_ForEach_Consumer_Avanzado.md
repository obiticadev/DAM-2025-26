# 9. forEach, Consumer avanzado y pintado en pantalla

## 9.1 forEach como operación terminal

`forEach` es una **operación terminal** que ejecuta una acción (un `Consumer`) sobre cada
elemento del stream. A diferencia de `collect`, `reduce` o `count`, **no devuelve nada** (`void`).

```java
// Forma básica con lambda
lista.stream()
    .filter(e -> e.isActivo())
    .forEach(e -> System.out.println(e.getNombre()));

// Con method reference — EQUIVALENTE y más limpio
lista.stream()
    .filter(Empleado::isActivo)
    .forEach(System.out::println);  // Consumer<Empleado> = System.out::println
```

### ¿Por qué `System.out::println` funciona?

`System.out` es un objeto de tipo `PrintStream`. El método `println(Object o)` acepta
un argumento y no devuelve nada → encaja con `Consumer<T>`:

```java
// Estas dos líneas son EQUIVALENTES:
.forEach(x -> System.out.println(x));
.forEach(System.out::println);
```

## 9.2 Method references en forEach — Tipos clave

| Tipo                          | Ejemplo en forEach              | Equivalente lambda               |
|-------------------------------|---------------------------------|----------------------------------|
| Referencia a método estático  | `forEach(MiClase::procesar)`    | `forEach(x -> MiClase.procesar(x))` |
| Referencia a instancia concreta | `forEach(sistema::registrar)` | `forEach(x -> sistema.registrar(x))` |
| Referencia a instancia del tipo | `forEach(Empleado::presentarse)` | `forEach(e -> e.presentarse())` |

### Ejemplo: método propio como method reference

```java
public class Formateador {
    public static void imprimirFicha(Empleado e) {
        System.out.printf("| %-15s | %-10s | %,8.0f€ |%n",
            e.getNombre(), e.getDepartamento(), e.getSalario());
    }
}

// Uso en forEach con method reference:
empleados.stream()
    .sorted(Comparator.comparing(Empleado::getSalario).reversed())
    .forEach(Formateador::imprimirFicha);
```

## 9.3 Consumer y Consumer.andThen()

`Consumer<T>` es una interfaz funcional que acepta un argumento y no devuelve nada.
Su método `andThen` permite **encadenar acciones**:

```java
Consumer<Empleado> imprimir = e -> System.out.println(e.getNombre());
Consumer<Empleado> registrar = e -> log.add(e.getNombre());

// Encadenar: primero imprime, luego registra
Consumer<Empleado> imprimirYRegistrar = imprimir.andThen(registrar);

empleados.stream()
    .filter(Empleado::isActivo)
    .forEach(imprimirYRegistrar);
```

### Encadenamiento múltiple

```java
Consumer<String> paso1 = s -> System.out.print("[" + s);
Consumer<String> paso2 = s -> System.out.print("|OK");
Consumer<String> paso3 = s -> System.out.println("]");

Consumer<String> pipeline = paso1.andThen(paso2).andThen(paso3);
// Resultado por cada elemento: [elemento|OK]
```

## 9.4 Formateo de tablas con forEach

```java
// Cabecera
System.out.println("+" + "-".repeat(17) + "+" + "-".repeat(12) + "+" + "-".repeat(10) + "+");
System.out.printf("| %-15s | %-10s | %8s |%n", "NOMBRE", "DEPTO", "SALARIO");
System.out.println("+" + "-".repeat(17) + "+" + "-".repeat(12) + "+" + "-".repeat(10) + "+");

// Filas con forEach
empleados.stream()
    .sorted(Comparator.comparing(Empleado::getSalario).reversed())
    .forEach(e -> System.out.printf("| %-15s | %-10s | %,8.0f€ |%n",
        e.getNombre(), e.getDepartamento(), e.getSalario()));

// Pie
System.out.println("+" + "-".repeat(17) + "+" + "-".repeat(12) + "+" + "-".repeat(10) + "+");
```

## 9.5 Stream.concat() — Combinar dos streams

```java
Stream<String> a = Stream.of("Ana", "Luis");
Stream<String> b = Stream.of("Carlos", "Marta");

Stream.concat(a, b)
    .forEach(System.out::println);  // Ana, Luis, Carlos, Marta
```

Útil para fusionar flujos de datos de distintas fuentes antes de procesarlos.

## 9.6 Collectors.collectingAndThen()

Aplica una **transformación final** al resultado de un collector:

```java
// Obtener lista inmutable
List<String> inmutable = empleados.stream()
    .map(Empleado::getNombre)
    .collect(Collectors.collectingAndThen(
        Collectors.toList(),
        Collections::unmodifiableList   // transformación final
    ));

// Obtener el resultado de groupingBy como TreeMap (ordenado)
TreeMap<String, List<Empleado>> ordenado = empleados.stream()
    .collect(Collectors.collectingAndThen(
        Collectors.groupingBy(Empleado::getDepartamento),
        TreeMap::new
    ));
```

## 9.7 forEachOrdered vs forEach

En streams **secuenciales** ambos son equivalentes. En **parallel streams**:
- `forEach` → NO garantiza orden
- `forEachOrdered` → SÍ garantiza orden (pero pierde rendimiento)

```java
lista.parallelStream()
    .forEachOrdered(System.out::println);  // Orden garantizado
```

## 9.8 Patrón: pipeline completo con salida visual

```java
empleados.stream()
    .filter(Empleado::isActivo)                          // filtrar
    .sorted(Comparator.comparing(Empleado::getSalario).reversed())  // ordenar
    .peek(e -> contadorActivos[0]++)                     // efecto lateral (contar)
    .map(e -> String.format("%-15s %,10.0f€", e.getNombre(), e.getSalario()))
    .forEach(System.out::println);                       // pintar en pantalla
```
