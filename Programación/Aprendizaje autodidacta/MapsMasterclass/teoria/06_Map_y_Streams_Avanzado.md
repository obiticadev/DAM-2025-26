# Coleccionistas en Cascada (Downstream Collectors)

Hasta ahora dominamos `groupingBy(Clasificador)`. Esto nos da como resultado algo como `Map<Clave, List<Objeto>>`.
Pero,... ¿qué pasa si no quiero la lista completa de objetos? ¿Qué pasa si solo quiero contar cuántos hay en ese grupo, o sumar sus salarios, o guardar solo sus nombres?

Aquí es donde entra la sobrecarga dorada de `groupingBy`:
`Collectors.groupingBy(Clasificador, DownstreamCollector)`

El `DownstreamCollector` es otro recolector que procesa la `List<Objeto>` antes de asignarla a la clave final del mapa. Es como un mini-pipeline al final del pipeline.

## 1. Conteo: `Collectors.counting()`

"Dime CUÁNTOS empleados hay por departamento, no quiénes son".
```java
Map<String, Long> cuantosPorDpto = empleados.stream()
    .collect(Collectors.groupingBy(
        Empleado::getDepartamento,
        Collectors.counting()       // <-- Downstream Collector
    ));
// Resultado: { "Ventas" = 4, "IT" = 10, "RRHH" = 2 }
```

## 2. Sumas y Medias: `Collectors.summingInt() / averagingDouble()`

"Dime la SUMA TOTAL de salarios por departamento".
```java
Map<String, Integer> costePorDpto = empleados.stream()
    .collect(Collectors.groupingBy(
        Empleado::getDepartamento,
        Collectors.summingInt(Empleado::getSalario)  // <-- Extrae el sueldo y lo suma
    ));
// Resultado: { "Ventas" = 8000, "IT" = 25000 }
```

## 3. Mapeo final: `Collectors.mapping()`

"Dime solo los NOMBRES de los empleados por departamento, no todo el objeto Empleado".
```java
Map<String, List<String>> nombresPorDpto = empleados.stream()
    .collect(Collectors.groupingBy(
        Empleado::getDepartamento,
        Collectors.mapping(
            Empleado::getNombre,    // Transforma el Empleado a su Nombre (String)
            Collectors.toList()     // Y lo vuelve a recolectar en una Lista
        )
    ));
// Resultado: { "Ventas" = ["Ana", "Luis"], "IT" = ["Pepe", "Marta"] }
```

## 4. Filtrado Avanzado: `Collectors.filtering()` *(Java 9+)*

"Dime los empleados por departamento, PERO ignorando en las listas a los becarios".
```java
Map<String, List<Empleado>> seniorsPorDpto = empleados.stream()
    .collect(Collectors.groupingBy(
        Empleado::getDepartamento,
        Collectors.filtering(
            e -> !e.esBecario(),   // Filtra ANTES de empaquetar en la lista
            Collectors.toList()
        )
    ));
```

¡Dominar estos 4 constructos te permitirá hacer un `SELECT departamento, COUNT(*), SUM(salario) FROM Empleados GROUP BY departamento` íntegro en memoria!
