# 11. Interfaces funcionales personalizadas y patrones avanzados

## 11.1 ¿Qué es una interfaz funcional?

Una interfaz funcional tiene **exactamente un método abstracto**. Se puede anotar con
`@FunctionalInterface` para que el compilador lo verifique.

```java
@FunctionalInterface
public interface Transformador<T, R> {
    R transformar(T entrada);
}
```

Cualquier interfaz funcional puede ser el target de una **lambda** o **method reference**.

## 11.2 Interfaces funcionales del JDK más importantes

| Interfaz             | Método abstracto       | Uso típico                         |
|----------------------|------------------------|------------------------------------|
| `Function<T,R>`      | `R apply(T t)`         | Transformar T → R                  |
| `Predicate<T>`       | `boolean test(T t)`    | Filtrar                            |
| `Consumer<T>`        | `void accept(T t)`     | Ejecutar acción                    |
| `Supplier<T>`        | `T get()`              | Proveer un valor                   |
| `UnaryOperator<T>`   | `T apply(T t)`         | Transformar T → T                  |
| `BinaryOperator<T>`  | `T apply(T a, T b)`    | Combinar T,T → T                   |
| `BiFunction<T,U,R>`  | `R apply(T t, U u)`    | Transformar T,U → R                |
| `BiPredicate<T,U>`   | `boolean test(T t, U u)` | Filtrar con 2 argumentos         |
| `BiConsumer<T,U>`    | `void accept(T t, U u)` | Acción con 2 argumentos           |

## 11.3 Crear interfaces funcionales propias

### Ejemplo: Validador genérico
```java
@FunctionalInterface
public interface Validador<T> {
    boolean validar(T objeto);

    // Métodos default para composición
    default Validador<T> y(Validador<T> otro) {
        return t -> this.validar(t) && otro.validar(t);
    }

    default Validador<T> o(Validador<T> otro) {
        return t -> this.validar(t) || otro.validar(t);
    }

    default Validador<T> negar() {
        return t -> !this.validar(t);
    }
}
```

### Uso:
```java
Validador<Empleado> esActivo = Empleado::isActivo;
Validador<Empleado> esSenior = e -> e.getExperienciaAnios() >= 5;
Validador<Empleado> tieneEmail = e -> e.getEmail() != null;

Validador<Empleado> filtroElite = esActivo.y(esSenior).y(tieneEmail);

empresa.stream()
    .filter(filtroElite::validar)   // adaptar a Predicate
    .forEach(System.out::println);
```

## 11.4 BiFunction y BiConsumer

```java
// BiFunction: 2 argumentos → 1 resultado
BiFunction<Empleado, Double, String> formatSalario = 
    (e, bonus) -> e.getNombre() + ": " + (e.getSalario() + bonus) + "€";

// BiConsumer: 2 argumentos → acción
BiConsumer<String, List<Empleado>> imprimirDepto = 
    (depto, emps) -> System.out.println(depto + ": " + emps.size() + " personas");

// Uso con Map.forEach (que recibe BiConsumer)
mapaDeptos.forEach(imprimirDepto);
```

## 11.5 Patrón Strategy con interfaces funcionales

```java
// En vez de clases Strategy, usa lambdas:
Function<List<Empleado>, List<Empleado>> strategyPorSalario =
    lista -> lista.stream()
        .sorted(Comparator.comparing(Empleado::getSalario).reversed())
        .collect(Collectors.toList());

Function<List<Empleado>, List<Empleado>> strategyPorExperiencia =
    lista -> lista.stream()
        .sorted(Comparator.comparing(Empleado::getExperienciaAnios).reversed())
        .collect(Collectors.toList());

// Aplicar la estrategia elegida
List<Empleado> resultado = strategyPorSalario.apply(empresa);
```

## 11.6 Patrón Template Method con Consumer

```java
// Definir pasos como Consumers
Consumer<List<Empleado>> cabecera = l -> System.out.println("=== INFORME ===");
Consumer<List<Empleado>> cuerpo = l -> l.forEach(e -> 
    System.out.println("  " + e.getNombre()));
Consumer<List<Empleado>> pie = l -> 
    System.out.println("Total: " + l.size());

// Componer el template
Consumer<List<Empleado>> informe = cabecera.andThen(cuerpo).andThen(pie);
informe.accept(empresa);
```

## 11.7 Patrón Decorator con Function

```java
Function<String, String> base = Function.identity();
Function<String, String> conParentesis = s -> "(" + s + ")";
Function<String, String> enMayusculas = String::toUpperCase;
Function<String, String> conEstrella = s -> "* " + s + " *";

// Decorar progresivamente
Function<String, String> decorado = base
    .andThen(enMayusculas)
    .andThen(conParentesis)
    .andThen(conEstrella);

decorado.apply("hola");  // → "* (HOLA) *"
```

## 11.8 Map.forEach, Map.replaceAll, Map.compute

```java
// forEach sobre Map — recibe BiConsumer<K,V>
mapa.forEach((clave, valor) -> System.out.println(clave + " = " + valor));

// replaceAll — recibe BiFunction<K,V,V>
mapa.replaceAll((clave, valor) -> valor.toUpperCase());

// compute — recibe BiFunction<K,V,V>
mapa.compute("Backend", (k, v) -> v == null ? "nuevo" : v + " actualizado");

// merge — recibe BiFunction para resolver conflictos
mapa.merge("Backend", "extra", (old, nuevo) -> old + ", " + nuevo);
```
