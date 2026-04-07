# Lambdas Avanzadas: Más allá de la Sintaxis Básica

## Recapitulación Rápida

Una Lambda es un bloque de código anónimo que sustituye la instanciación de una interfaz SAM:

```java
// Antes (Clase Anónima):
Comparator<String> c = new Comparator<String>() {
    @Override
    public int compare(String a, String b) { return a.compareTo(b); }
};

// Después (Lambda):
Comparator<String> c = (a, b) -> a.compareTo(b);
```

## Variables Capturadas (Closure)

Las Lambdas pueden **capturar** variables del ámbito exterior, pero con una restricción brutal:

> La variable capturada debe ser **effectively final** (nunca se le reasigna un valor después de su declaración).

```java
String prefijo = "Sr. ";  // effectively final (nunca cambia)
Function<String, String> formal = nombre -> prefijo + nombre;
// formal.apply("García") => "Sr. García"

// ILEGAL:
String titulo = "Dr.";
titulo = "Prof.";  // ¡REASIGNACIÓN! Ya no es effectively final
Function<String, String> rota = nombre -> titulo + nombre; // ERROR DE COMPILACIÓN
```

**¿Por qué esta limitación?** Porque las Lambdas pueden ejecutarse en otro hilo o en un momento posterior. Si la variable cambiara mientras la Lambda la usa, tendríamos condiciones de carrera catastróficas.

## Lambdas Multilínea

Cuando la lógica dentro de la Lambda requiere más de una expresión, necesitas llaves `{}` y `return` explícito:

```java
Function<Empleado, String> resumen = emp -> {
    String estado = emp.isActivo() ? "EN ACTIVO" : "BAJA";
    double bonus = emp.esSenior() ? emp.getSalario() * 0.1 : 0;
    return emp.getNombre() + " | " + estado + " | Bonus: " + bonus + "€";
};
```

## Lambdas como Parámetros de Métodos Propios

Puedes diseñar tus propios métodos que acepten interfaces funcionales como parámetro:

```java
// Tu método personalizado que acepta un Predicate
public static List<Empleado> filtrar(List<Empleado> lista, Predicate<Empleado> criterio) {
    return lista.stream().filter(criterio).collect(Collectors.toList());
}

// Uso: pasas la Lambda directamente como argumento
List<Empleado> seniors = filtrar(plantilla, e -> e.esSenior());
List<Empleado> backend = filtrar(plantilla, e -> e.getDepartamento().equals("Backend"));
```

## Almacenamiento en Variables

Las Lambdas se pueden guardar en variables tipadas con su interfaz funcional correspondiente:

```java
// Almacenar predicados reutilizables
Predicate<Empleado> activo = Empleado::isActivo;
Predicate<Empleado> ganaEnorme = e -> e.getSalario() > 60000;
Predicate<Empleado> activoYRico = activo.and(ganaEnorme);

// Almacenar funciones de transformación
Function<Empleado, String> aPresentacion = e -> 
    e.getNombre() + " (" + e.getDepartamento() + ")";

// Almacenar comparadores
Comparator<Empleado> porSalario = Comparator.comparing(Empleado::getSalario);
Comparator<Empleado> porSalarioDesc = porSalario.reversed();
```

## Interfaces Funcionales Primitivas

Java incluye versiones especializadas para evitar el autoboxing de tipos primitivos:

```
┌────────────────────────────────────────────────────┐
│ IntPredicate    │  int  -> boolean                 │
│ IntFunction<R>  │  int  -> R                       │
│ IntConsumer     │  int  -> void                    │
│ IntSupplier     │  ()   -> int                     │
│ IntUnaryOperator│  int  -> int                     │
│ ToIntFunction<T>│  T    -> int                     │
├────────────────────────────────────────────────────┤
│ (Mismo patrón para Long y Double)                  │
└────────────────────────────────────────────────────┘
```

Estas versiones primitivas se usan internamente con `mapToInt()`, `mapToDouble()`, etc.

## Errores Comunes

1. **Intentar modificar variables externas:** La Lambda captura una copia, no una referencia mutable.
2. **Olvidar el return en Lambdas multilínea:** Si usas `{}`, necesitas `return` explícito.
3. **Confundir el tipo de la interfaz:** Una Lambda `(a, b) -> ...` con dos params podría ser `BiFunction`, `Comparator`, o `BiPredicate` dependiendo del contexto.
4. **Excepción en Lambda:** Las interfaces funcionales estándar NO declaran excepciones checked. Hay que envolverlas en try-catch dentro de la Lambda.
