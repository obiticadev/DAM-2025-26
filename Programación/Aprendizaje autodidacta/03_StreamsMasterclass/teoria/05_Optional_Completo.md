# Optional<T>: El Guardián contra el NullPointerException

## El Problema que Resuelve

`NullPointerException` (NPE) es el error más común en la historia de Java. Ocurre cuando intentas usar un objeto que es `null`:

```java
Empleado emp = buscarPorNombre("Inexistente"); // Devuelve null
System.out.println(emp.getNombre()); // BOOM! NullPointerException
```

Antes de Java 8, la solución era saturar el código de `if (x != null)` en todas partes. Horrible.

## ¿Qué es Optional?

`Optional<T>` es una **caja** que puede contener un valor de tipo T o estar **vacía**. Es una declaración explícita de que "este método podría no devolver nada".

```
┌─────────────────────────────────────────────────┐
│  Optional CON valor:                            │
│  ┌──────────────────────┐                       │
│  │  Optional<Empleado>  │ ──> Empleado("Ana")   │
│  └──────────────────────┘                       │
│                                                 │
│  Optional VACÍO:                                │
│  ┌──────────────────────┐                       │
│  │  Optional<Empleado>  │ ──> (vacío, nada)     │
│  └──────────────────────┘                       │
└─────────────────────────────────────────────────┘
```

## Creación de un Optional

### 1. `Optional.of(valor)` — Valor garantizado NO nulo
```java
Optional<String> nombre = Optional.of("Carlos");
// Si le pasas null, lanza NullPointerException inmediatamente
```

### 2. `Optional.ofNullable(valor)` — Valor que PODRÍA ser null
```java
String email = empleado.getEmail(); // Podría ser null
Optional<String> optEmail = Optional.ofNullable(email);
// Si es null, crea un Optional vacío. Si no, lo envuelve.
```

### 3. `Optional.empty()` — Optional vacío explícito
```java
Optional<Empleado> vacio = Optional.empty();
```

## Métodos de Consulta

### `.isPresent()` / `.isEmpty()` (Java 11+)
```java
Optional<String> opt = Optional.ofNullable(valor);
if (opt.isPresent()) {
    System.out.println("Tiene valor: " + opt.get());
}
```

### `.get()` — Extraer el valor (¡PELIGROSO!)
```java
String nombre = opt.get(); // Si está vacío, lanza NoSuchElementException
// NUNCA uses .get() sin antes verificar .isPresent()
```

## Métodos de Acción (Los Importantes)

### `.orElse(valorDefault)` — Valor por defecto síncrono
```java
String nombre = optNombre.orElse("Desconocido");
// Si optNombre está vacío, devuelve "Desconocido"
```

### `.orElseGet(Supplier)` — Valor por defecto evaluado lazy
```java
String nombre = optNombre.orElseGet(() -> generarNombreAleatorio());
// El Supplier SOLO se ejecuta si el Optional está vacío
```

### `.orElseThrow()` — Lanzar excepción si vacío
```java
Empleado emp = optEmpleado.orElseThrow();
// Lanza NoSuchElementException si vacío

Empleado emp2 = optEmpleado.orElseThrow(
    () -> new RuntimeException("Empleado no encontrado")
);
```

### `.ifPresent(Consumer)` — Ejecutar acción solo si hay valor
```java
optEmpleado.ifPresent(e -> System.out.println("Encontrado: " + e));
// No hace nada si está vacío. No necesitas if/else.
```

### `.ifPresentOrElse(Consumer, Runnable)` (Java 9+)
```java
optEmpleado.ifPresentOrElse(
    e -> System.out.println("Encontrado: " + e),
    () -> System.out.println("No se encontró ningún empleado")
);
```

## Transformación con Optional (Estilo Stream)

### `.map(Function)` — Transformar el contenido
```java
Optional<String> optNombre = optEmpleado.map(Empleado::getNombre);
// Si optEmpleado tiene valor, extrae el nombre y lo envuelve en Optional
// Si optEmpleado está vacío, devuelve Optional.empty()
```

### `.flatMap(Function)` — Cuando la función ya devuelve Optional
```java
// Si buscarEmail ya devuelve Optional<String>:
Optional<String> email = optEmpleado.flatMap(e -> buscarEmail(e.getId()));
// .map() aquí daría Optional<Optional<String>> (horror)
// .flatMap() aplana a Optional<String>
```

### `.filter(Predicate)` — Filtrar el contenido
```java
Optional<Empleado> seniorOpt = optEmpleado.filter(Empleado::esSenior);
// Si hay empleado Y es senior => lo mantiene
// Si hay empleado pero NO es senior => Optional.empty()
// Si ya estaba vacío => sigue vacío
```

## Encadenamiento Elegante (El Poder Real)

```java
// Antes (infierno de nulls):
String resultado = "N/A";
if (empleado != null) {
    if (empleado.getEmail() != null) {
        if (empleado.getEmail().contains("@")) {
            resultado = empleado.getEmail().toUpperCase();
        }
    }
}

// Después (flujo limpio con Optional):
String resultado = Optional.ofNullable(empleado)
    .map(Empleado::getEmail)
    .filter(email -> email.contains("@"))
    .map(String::toUpperCase)
    .orElse("N/A");
```

## Optional y Streams: La Conexión

Muchas operaciones terminales de Stream devuelven Optional:

```java
Optional<Empleado> primero = lista.stream().findFirst();
Optional<Empleado> mejor = lista.stream().max(Comparator.comparing(Empleado::getSalario));
Optional<Double> maxSalario = lista.stream().map(Empleado::getSalario).reduce(Double::max);
```

### `.stream()` en Optional (Java 9+)
Convierte un Optional en un Stream de 0 o 1 elementos:
```java
Stream<Empleado> s = optEmpleado.stream(); // Stream vacío o con 1 elemento
```

## Reglas de Oro

1. **NUNCA** uses Optional como campo de una clase (no es serializable ni está diseñado para eso).
2. **NUNCA** uses `Optional.of(null)` — usa `Optional.ofNullable()`.
3. **NUNCA** hagas `if (opt.isPresent()) { opt.get() }` — usa `opt.ifPresent()` o `opt.map()`.
4. **USA** Optional como tipo de retorno de métodos que podrían no devolver resultado.
5. **NO USES** Optional como parámetro de métodos (es un antipatrón).
