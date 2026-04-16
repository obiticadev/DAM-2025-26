# Inmutabilidad Moderna en Maps (Java 9+)

En versiones antiguas de Java (8 y anteriores), crear un mapa pequeño y fijo requería mucho código *boilerplate*:

```java
// Java 8: Verboso y mutable por defecto
Map<String, Integer> puntos = new HashMap<>();
puntos.put("A", 1);
puntos.put("B", 2);
puntos = Collections.unmodifiableMap(puntos); // Protegerlo
```

Con Java 9 llegaron las **Factórías de Colecciones** (`Map.of` y `Map.ofEntries`), y Java 10 introdujo `Map.copyOf`. Estos métodos devuelven implementaciones puramente inmutables.

## 1. `Map.of()` (Para mapas pequeños, < 10 entradas)

Se alternan las llaves y los valores como parámetros.

```java
Map<String, String> configuracion = Map.of(
    "host", "localhost",
    "puerto", "8080",
    "protocolo", "https"
);
```

> [!WARNING]
> PRO TIP: `Map.of()` **NO PERMITE CLAVES NULAS NI VALORES NULOS**. Si intentas meter un `null`, lanzará `NullPointerException` inmediatamente. Un `HashMap` normal sí permite un `null` como llave y múltiples valores nulos. Los mapas fijos prohíben esto estrictamente por seguridad y rendimiento.
> 
> Además, los elementos se iterarán en un oden pseudoaleatorio que puede cambiar entre ejecuciones de la JVM (es una medida de seguridad contra ataques de denegación de servicio por colisiones de hash iterativas).

## 2. `Map.ofEntries()` (Para cualquier tamaño)

Si tienes más de 10 pares, Java no tiene sobrecargas de `Map.of` suficientes (están limitadas a 10 pares). Debes usar `Map.entry(K,V)`.

```java
import static java.util.Map.entry; // Importación estática muy común

Map<Integer, String> errores = Map.ofEntries(
    entry(404, "Not Found"),
    entry(500, "Internal Server Error"),
    entry(403, "Forbidden")
);
```

## 3. Diferencia CRÍTICA: `Collections.unmodifiableMap` vs `Map.copyOf`

Uno es una *Vista* y el otro es una *Copia Dura*.

*   **`Collections.unmodifiableMap(mapaOriginal)`:** Devuelve una funda o "wrapper". Si alguien que tenga la referencia al `mapaOriginal` inserta un nuevo valor, **el wrapper también se actualizará**. Solo prohíbe que se modifique directamente a través del wrapper.
*   **`Map.copyOf(mapaOriginal)` (Java 10):** Lee los datos en ese milisegundo exacto y crea un clon en memoria 100% aislado, inmutable e independiente. Si mutas el original después, al `copyOf` no le afecta.

> [!TIP]
> PRO TIP DE ARQUITECTURA: En microservicios (Spring Boot), si devuelves la configuración interna de la clase en un *getter*, devuelve siempre un `Map.copyOf(...)` o `Map.of()` para garantizar que ningún código de terceros altere los datos fundacionales del servicio. Usar `unmodifiableMap` aún conlleva el riesgo de que la clase propietaria mute su estado y esto impacte "hacia afuera".
