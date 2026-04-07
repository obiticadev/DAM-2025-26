# Patrones de Diseño Avanzados y Concurrencia

Los diccionarios (`Maps`) no solo sirven para guardar "datos", sirven maravillosamente para guardar **comportamientos** (funciones). Esto te permite limpiar código espagueti.

## 1. El Patrón "Command Dispatcher" (Adiós Switch)

Imagínate un servidor web que recibe `/login`, `/logout`, `/perfil`.
Normalmente harías un `switch(ruta)` de 500 líneas.
Con un `Map<String, Runnable>` o `Map<String, Consumer<T>>`, esto desaparece:

```java
// Inicialización del "Router"
Map<String, Runnable> router = new HashMap<>();
router.put("/login", () -> ejecutarModuloLogin());
router.put("/logout", () -> limpiarSesion());

// Ejecución
String rutaPedida = "/login";
router.getOrDefault(rutaPedida, () -> presentarError404()).run();
```
O(1) puro. Sin *switches*, escalable y limpio. Se llama Patrón *Strategy* o *Command Table*.

## 2. Caché "Memoización" con `computeIfAbsent`
A veces tienes funciones matemáticas o llamadas a BDD muy lentas.
Puedes usar un mapa de caché: `Map<String, String> cache = new WeakHashMap<>();`

```java
// computeIfAbsent SÓLO ejecuta la segunda parte si la llave no existe.
// Si existe, te devuelve el valor de la RAM directamente en O(1).
String resultado = cache.computeIfAbsent("query1", k -> funcionLentaQueTarda10Segundos());
```

> [!TIP]
> **PRO TIP:** En arquitecturas sin servidor (Serverless como AWS Lambda), tener un `Map` estático usando `computeIfAbsent` reduce el tiempo de arranque (Cold Start) porque "recuerda" cálculos de invocaciones anteriores de forma gratis.

---

## 3. Mapas Concurrentes (Multihilo Real)

Si lanzas 1000 Hilos asíncronos a hacer un `.put()` dentro de un `HashMap` normal de forma paralela... **se romperá el array interno**. En Java 8 hacia atrás podías incluso provocar un "Bucle Infinito" (Infinite Loop en el rehashing) que congelaba los Servidores para siempre.

### Opcion A: `Collections.synchronizedMap` (El Conservador)
Capa protectora que bloquea el Mapa entero.
*   **Problema:** Si tienes 1,000,000 de dólares en un banco, y el Hilo A hace `get("cliente1")`, el Hilo B **DEBE ESPERAR** para hacer un `put("cliente2")`. Hay un "cuello de botella".

### Opcion B: `ConcurrentHashMap` (La Obra Maestra)
Usa algo llamado "Segment Locks" (o *lock-striping* en versiones viejas, y CAS-Locks en versiones modernas).
En vez de bloquear el mapa entero, **sólo bloquea el diminuto contenedor del Hash que se está mutando**.
*   **Ventaja:** El Hilo A puede escribir al cliente 1, y el Hilo B puede escribir al cliente 2 AL MISMO TIEMPO y ambos sobreviven sin bloqueos si no chocan en el mismo contenedor interno.
*   **Restricción Estricta:** `ConcurrentHashMap` **NO** permite llaves nulas y **NO** permite valores nulos (para evitar la ambigüedad en lecturas multihilo no bloqueantes).

### Operaciones Atómicas Extra de ConcurrentHashMap
Además de los *Segment Locks*, te da métodos nativos para no tener que abrir cerrojos `synchronized`:
*   `putIfAbsent(K, V)` -> Totalmente atómico (Mete el valor solo si ningún otro hilo te ganó al mismo tiempo).
*   Operaciones Bulk: `forEach(parallelismThreshold, action)`, `reduceValues(...)` o `search(...)`. Usan múltiples hilos por detrás para procesar sus propios datos.
