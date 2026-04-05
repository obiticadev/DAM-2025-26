# Interfaces Funcionales y el Contrato SAM

## ¿Qué es una Interfaz Funcional?

Una **Interfaz Funcional** es cualquier interfaz de Java que contenga **exactamente UN método abstracto** (sin cuerpo). Este concepto es la piedra angular sobre la que se construyen las Lambdas.

Java las marca opcionalmente con la anotación `@FunctionalInterface`, pero NO es obligatoria. Si la interfaz cumple la regla de "solo 1 método abstracto", ya es funcional automáticamente.

```java
@FunctionalInterface
public interface Comparator<T> {
    int compare(T o1, T o2);  // El ÚNICO método abstracto
    // (tiene más métodos, pero son default o static, no abstractos)
}
```

## El Contrato SAM (Single Abstract Method)

SAM = **Single Abstract Method**. Es simplemente el nombre técnico que recibe esa regla del "único método abstracto".

**¿Por qué importa?** Porque el compilador de Java, al ver que una interfaz solo tiene 1 método posible, puede "adivinar" automáticamente qué método estás implementando cuando escribes una Lambda. No necesitas decirle el nombre del método ni los tipos.

## Las 4 Interfaces Funcionales Fundamentales de java.util.function

Java 8 incluyó un paquete completo de interfaces SAM listas para usar:

### 1. `Predicate<T>` — El Juez Booleano
```java
boolean test(T t);
```
- **Entrada:** Un objeto de tipo T
- **Salida:** `true` o `false`
- **Uso estrella:** `.filter()` en Streams
- **Ejemplo:** `Predicate<Empleado> esSenior = e -> e.getExperienciaAnios() >= 5;`

### 2. `Function<T, R>` — El Transformador
```java
R apply(T t);
```
- **Entrada:** Un objeto de tipo T
- **Salida:** Un objeto de tipo R (puede ser diferente)
- **Uso estrella:** `.map()` en Streams
- **Ejemplo:** `Function<Empleado, String> aNombre = e -> e.getNombre();`

### 3. `Consumer<T>` — El Devorador (No devuelve nada)
```java
void accept(T t);
```
- **Entrada:** Un objeto de tipo T
- **Salida:** Ninguna (void)
- **Uso estrella:** `.forEach()` en Streams
- **Ejemplo:** `Consumer<Empleado> imprimir = e -> System.out.println(e);`

### 4. `Supplier<T>` — El Generador (No recibe nada)
```java
T get();
```
- **Entrada:** Ninguna
- **Salida:** Un objeto de tipo T
- **Uso estrella:** `Optional.orElseGet()`, fábricas de objetos
- **Ejemplo:** `Supplier<Empleado> crearDefault = () -> new Empleado("Sin Nombre", ...);`

```
┌────────────────────────────────────────────────────────┐
│            MAPA DE INTERFACES FUNCIONALES              │
├──────────────┬──────────┬──────────┬──────────────────┤
│  Interfaz    │ Entrada  │  Salida  │  Método          │
├──────────────┼──────────┼──────────┼──────────────────┤
│ Predicate<T> │    T     │ boolean  │ test(T)          │
│ Function<T,R>│    T     │    R     │ apply(T)         │
│ Consumer<T>  │    T     │  void    │ accept(T)        │
│ Supplier<T>  │  nada    │    T     │ get()            │
├──────────────┼──────────┼──────────┼──────────────────┤
│ BiFunction   │  T, U    │    R     │ apply(T, U)      │
│ BiPredicate  │  T, U    │ boolean  │ test(T, U)       │
│ BiConsumer   │  T, U    │  void    │ accept(T, U)     │
│ UnaryOp<T>   │    T     │    T     │ apply(T)         │
│ BinaryOp<T>  │  T, T    │    T     │ apply(T, T)      │
└──────────────┴──────────┴──────────┴──────────────────┘
```

## Composición de Interfaces Funcionales

Las interfaces funcionales no son cajas aisladas. Vienen con métodos `default` que permiten **encadenarlas**:

### Predicate: `.and()`, `.or()`, `.negate()`
```java
Predicate<Empleado> activo = Empleado::isActivo;
Predicate<Empleado> senior = Empleado::esSenior;
Predicate<Empleado> seniorActivo = activo.and(senior);
Predicate<Empleado> noActivo = activo.negate();
```

### Function: `.andThen()`, `.compose()`
```java
Function<Empleado, String> aNombre = Empleado::getNombre;
Function<String, String> aMayusculas = String::toUpperCase;
Function<Empleado, String> nombreMayus = aNombre.andThen(aMayusculas);
// nombreMayus.apply(emp) => "CARLOS"
```

### Consumer: `.andThen()`
```java
Consumer<Empleado> imprimir = System.out::println;
Consumer<Empleado> desactivar = Empleado::desactivar;
Consumer<Empleado> imprimirYDesactivar = imprimir.andThen(desactivar);
```

## Regla de Oro

> Si una interfaz tiene **1 solo método abstracto**, puedes sustituir su instanciación por una Lambda `->` o un Method Reference `::`. Punto.
