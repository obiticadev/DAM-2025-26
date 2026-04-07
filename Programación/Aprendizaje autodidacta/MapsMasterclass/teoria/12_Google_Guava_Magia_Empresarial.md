# Nivel 15: La Magia Empresarial (Google Guava)

Hasta ahora hemos trabajado con Java Vanilla. Es robusto, es seguro y es el estándar de la industria. Pero a veces, ciertas estructuras de datos requieren demasiada fricción (código redundante o *boilerplate*) en Vanilla.

**Google Guava** es la librería de colecciones más famosa del mundo Java. Llena los huecos que los arquitectos de Java no quisieron meter nativamente.

Para usarlo, necesitas tener la librería `guava.jar` en el Classpath (o vía Maven/Gradle).

## 1. El Multimap (Adiós al Map<K, List<V>>)

En Java, cuando una llave tiene múltiples valores, haces:
```java
Map<String, List<String>> favoritos = new HashMap<>(); // Feo.
favoritos.computeIfAbsent("Juan", k -> new ArrayList<>()).add("Rojo"); // Dificil de leer
```

Con `Multimap` de Guava todo ese dolor desaparece:
```java
Multimap<String, String> favoritos = ArrayListMultimap.create();
favoritos.put("Juan", "Rojo");
favoritos.put("Juan", "Azul");
// El tamaño de esto es 2, no 1 como en Vanilla.
```

## 2. El BiMap (El diccionario de dos direcciones)

En el Ejercicio 45 creaste *dos mapas Vanilla separados* para simular una búsqueda por Valor constante. Muy peligroso mantener la sincronía manualmente.

El `BiMap` (Bidirectional Map) te garantiza la unicidad bidireccional (Si intentas meter un valor duplicado lanza excepción) y te da gratis el método `.inverse()`.

```java
BiMap<Integer, String> idsToEmail = HashBiMap.create();
idsToEmail.put(1, "ana@test.com"); // BiMap asegura que los Valores tampoco se dupliquen.

// Buscar instantáneamente la Llave apartir del Valor. (O(1)).
Integer idDeAna = idsToEmail.inverse().get("ana@test.com");
```

## 3. El Table (Adiós al Map<Row, Map<Column, Value>>)

Si necesitamos una estructura en 2 dimensiones como una agenda, en vez de anidar mapas Vanilla con `computeIfAbsent`, usamos la elegante estructura `Table`.

```java
Table<String, String, Double> pasajes = HashBasedTable.create();
pasajes.put("Madrid", "Roma", 120.5); // (Fila, Columna, Valor)

// Magia: Extraer toda la columna "Roma" como un Mapa independiente
Map<String, Double> todosVuelosHaciaRoma = pasajes.column("Roma");
```

## 4. Constructores inmutables puros

Aunque Java 9 metió `Map.of()`, la API de `ImmutableMap` de Guava sigue siendo ampliamente utilizada en bases de código antiguas o para construcciones fluidas *(Builder pattern)*.

```java
ImmutableMap<String, Integer> inmutable = ImmutableMap.<String, Integer>builder()
        .put("A", 1)
        .put("B", 2)
        .build();
```

> [!TIP]
> **PRO TIP:** Si un proyecto ya está en Java 17+, evita usar `ImmutableMap` de Guava a menos que uses su *Builder*. Prefiere el nativo `Map.of()` porque evita arrastrar el peso de los `.jar` enteros de Guava al tamaño final del Microservicio, a menos que ya uses Guava para el brutal Multimap.
