# Creación de Maps mediante Streams

Hasta ahora hemos creado mapas vacíos y hemos usado `put` o `compute`. Pero en el mundo real, los datos suelen venir en forma de Listas, Bases de Datos o JSONs, y usaremos **Streams** para transformar esas listas en un `Map` eficiente en una sola línea de código.

## `Collectors.toMap` (La asimilación)

Transforma un Stream de elementos en un Map. Requiere al menos dos funciones (Lambdas o Method References):
1. **KeyMapper:** ¿Cómo obtengo la Clave `K` de este elemento?
2. **ValueMapper:** ¿Cómo obtengo el Valor `V` de este elemento? (Se suele usar `p -> p` o `Function.identity()` si el valor es el objeto completo).

```java
// Convertir List<Empleado> a Map<DNI, Empleado>
Map<String, Empleado> mapa = lista.stream()
    .collect(Collectors.toMap(
        Empleado::getDni,      // K
        Function.identity()    // V
    ));
```

### ¿Y si hay Claves Repetidas? (Colisiones)
Si dos empleados tienen el mismo DNI, `toMap` lanzará `IllegalStateException`.
Para evitarlo, existe una versión de `toMap` que recibe un tercer parámetro: La **MergeFunction** (cómo resuelvo la colisión).

```java
// Si hay colisión, me quedo con el empleado nuevo
Collectors.toMap(
    Empleado::getDni, 
    e -> e, 
    (viejo, nuevo) -> nuevo 
)
```

## Agrupación: `Collectors.groupingBy()`

El equivalente al `GROUP BY` de SQL. Transforma milagrosamente un `Stream<T>` en un `Map<K, List<T>>`.
Agrupa elementos que compartan la misma clave.

```java
// Map<Departamento, List<Empleado>>
Map<String, List<Empleado>> porDepartamento = lista.stream()
    .collect(Collectors.groupingBy(Empleado::getDepartamento));
```
Al correr esto, todas las personas de "Ventas" acabarán en una lista bajo la clave "Ventas". *Maravilloso.*

## Partición: `Collectors.partitioningBy()`

Es un primo hermano de `groupingBy`. Se usa **exclusivamente con un Predicate**.
Solo puede generar un `Map<Boolean, List<T>>`.
Es decir, divide el Stream en dos cajas: los que cumplen la condición (`true`) y los que no (`false`).

```java
// Dividir empleados entre los que cobran > 2000 y los que no
Map<Boolean, List<Empleado>> ricosYNoRicos = lista.stream()
    .collect(Collectors.partitioningBy(e -> e.getSalario() > 2000));
```

En este nivel dominaremos cómo extraer mapas de colecciones pre-existentes de forma idiomática y limpia.
