package com.masterclass.api.b05_web;

import org.springframework.http.ResponseEntity;
import java.util.List;

/**
 * Ejercicio 053 · CRUD completo en memoria.
 *
 * <p>Teoría: {@code teoria/05_Controllers_REST.md} (todo el bloque).
 *
 * <p>Integra GET/POST/PUT/DELETE sobre un almacén en memoria. El test ejercita
 * el ciclo de vida completo de un recurso.
 */
// TODO 1: anota la clase con @RestController y @RequestMapping("/api/tareas").
public class Ej053CrudInMemory {

    public record Tarea(Long id, String titulo) {
    }

    // Almacén en memoria (ya provisto): clave id -> Tarea.
    private final java.util.Map<Long, Tarea> db = new java.util.concurrent.ConcurrentHashMap<>();
    private final java.util.concurrent.atomic.AtomicLong seq = new java.util.concurrent.atomic.AtomicLong(0);

    /** @return todas las tareas (200). */
    // TODO 2: anota con @GetMapping; devuelve la colección de db.values() como List.
    public List<Tarea> listar() {
        // TODO 3: devuelve una nueva List a partir de db.values().
        return List.of();
    }

    /**
     * @param id identificador
     * @return la tarea (200) o 404 si no existe
     */
    // TODO 4: anota con @GetMapping("/{id}") y 'id' con @PathVariable.
    public ResponseEntity<Tarea> obtener(Long id) {
        // TODO 5: busca en db; si está -> ResponseEntity.ok(tarea); si no -> notFound().
        return null;
    }

    /**
     * @param nueva cuerpo con el titulo
     * @return 201 con la tarea creada (id autogenerado)
     */
    // TODO 6: anota con @PostMapping y 'nueva' con @RequestBody.
    public ResponseEntity<Tarea> crear(Tarea nueva) {
        // TODO 7: genera id con seq.incrementAndGet(), crea la Tarea y guárdala en db.
        // TODO 8: devuelve 201 (ResponseEntity.status(CREATED).body(creada)).
        return null;
    }

    /**
     * @param id identificador a borrar
     * @return 204 si existía; 404 si no
     */
    // TODO 9: anota con @DeleteMapping("/{id}") y 'id' con @PathVariable.
    public ResponseEntity<Void> borrar(Long id) {
        // TODO 10: db.remove(id); si devolvió algo -> 204 noContent; si null -> 404 notFound.
        return null;
    }

    public static void main(String[] args) {
        var c = new Ej053CrudInMemory();
        System.out.println(c.crear(new Tarea(null, "x")));
    }

    // --- MÉTODOS Y DTOs DE RETOS EXTRA ---

    public record TareaCompleta(Long id, String titulo, boolean completada) {}

    // BD adicional en memoria para retos extra (se sincroniza con db para IDs)
    private final java.util.Map<Long, Boolean> completadasDb = new java.util.concurrent.ConcurrentHashMap<>();

    /**
     * Reto Extra 1: PUT para actualizar una tarea por completo.
     */
    // GUÍA: teoría 5.3 — PUT sobre el almacén REAL en memoria 'db' (ConcurrentHashMap).
    // 1. Si !db.containsKey(id) → ResponseEntity.notFound().build() (404).
    // 2. Si existe, crea una Tarea(id, tarea.titulo()) y db.put(id, esa) (reemplazo
    //    total); devuelve ResponseEntity.ok(esa).
    // OJO: el test arranca con db VACÍA y hace PUT a /api/tareas/1 → espera 404.
    //   El id sale de la ruta, no del cuerpo.
    // TODO extra: anota con @PutMapping("/{id}").
    public ResponseEntity<Tarea> actualizarTarea(
            @org.springframework.web.bind.annotation.PathVariable Long id,
            @org.springframework.web.bind.annotation.RequestBody Tarea tarea) {
        // TODO extra: si el id no existe en `db`, devuelve 404 (Not Found).
        // Si existe, reemplaza la tarea en `db` con una nueva Tarea usando el 'id' de la ruta y el titulo recibido.
        // Retorna 200 OK con la tarea actualizada.
        return null;
    }

    /**
     * Reto Extra 2: PATCH para actualizar parcialmente el título de una tarea.
     */
    // GUÍA: teoría 5.3 — PATCH parcial sobre 'db': solo cambia lo que venga en el mapa.
    // 1. Si !db.containsKey(id) → 404.
    // 2. Si existe y cambios contiene "titulo", construye una Tarea(id, nuevoTitulo),
    //    db.put(id, esa) y devuelve ResponseEntity.ok(esa).
    // OJO: el test arranca con db vacía y hace PATCH a /api/tareas/1 → 404.
    // TODO extra: anota con @PatchMapping("/{id}").
    public ResponseEntity<Tarea> parchearTarea(
            @org.springframework.web.bind.annotation.PathVariable Long id,
            @org.springframework.web.bind.annotation.RequestBody java.util.Map<String, Object> cambios) {
        // TODO extra: si el id no existe en `db`, devuelve 404 (Not Found).
        // Si existe, y el mapa contiene "titulo", actualiza sólo el título.
        // Retorna 200 OK con la tarea resultante.
        return null;
    }

    /**
     * Reto Extra 3: GET con filtrado de tareas por título (Query Param).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/filtrar")
    public List<Tarea> buscarPorTitulo(
            @org.springframework.web.bind.annotation.RequestParam("q") String q) {
        // GUÍA: teoría 5.2 + streams (1.3) — filtra db.values() por título que
        //   contenga 'q'.
        // 1. return db.values().stream()
        //        .filter(t -> t.titulo().contains(q))
        //        .toList();
        // OJO: el test solo comprueba status 200 (db vacía → lista vacía es válida).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 4: DELETE global para vaciar la base de datos de tareas.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.DeleteMapping("")
    public ResponseEntity<Void> limpiarTodo() {
        // GUÍA: teoría 5.3 — DELETE sobre la colección entera.
        // 1. db.clear(); return ResponseEntity.noContent().build();
        // OJO: el test espera 204. @DeleteMapping("") mapea la raíz de /api/tareas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para DeleteMapping");
    }

    /**
     * Reto Extra 5: GET para contar la cantidad total de tareas registradas.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/count")
    public java.util.Map<String, Long> contarTareas() {
        // GUÍA: teoría 5.2 — devuelve un dato agregado como objeto JSON.
        // 1. return Map.of("total", (long) db.size());
        //    (el cast a long encaja con el tipo de retorno Map<String,Long>).
        // OJO: el test (db vacía) espera $.total == 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 6: POST para creación masiva (Bulk POST).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/bulk")
    public ResponseEntity<List<Tarea>> crearVarias(
    @org.springframework.web.bind.annotation.RequestBody List<Tarea> tareas) {
        // GUÍA: teoría 5.3 — alta masiva: por cada entrada genera id con seq y guarda.
        // 1. Recorre 'tareas'; para cada una: long id = seq.incrementAndGet();
        //    Tarea creada = new Tarea(id, t.titulo()); db.put(id, creada); acumúlala.
        // 2. return ResponseEntity.status(HttpStatus.CREATED).body(listaCreadas);
        // OJO: el test manda dos tareas y espera 201 con $[0].id==1 y $[1].id==2:
        //   el seq (AtomicLong, ya declarado) reparte ids 1,2 en orden.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PostMapping");
    }

    /**
     * Reto Extra 7: POST para duplicar una tarea existente.
     */
    // GUÍA: teoría 5.3 — POST que clona un recurso existente (acción, no CRUD puro).
    // 1. Si !db.containsKey(id) → 404.
    // 2. Si existe: long nuevoId = seq.incrementAndGet();
    //    Tarea copia = new Tarea(nuevoId, original.titulo() + " (copia)");
    //    db.put(nuevoId, copia); return 201 con copia.
    // OJO: el test (db vacía) pega a /api/tareas/1/duplicar → 404.
    // TODO extra: anota con @PostMapping("/{id}/duplicar").
    public ResponseEntity<Tarea> duplicarTarea(@org.springframework.web.bind.annotation.PathVariable Long id) {
        // TODO extra: si el id no existe en `db`, devuelve 404 (Not Found).
        // Si existe, genera un nuevo id con seq.incrementAndGet(), guarda una copia de la tarea con el nuevo id
        // y el sufijo " (copia)" al final del título. Retorna 201 Created con la tarea duplicada.
        return null;
    }

    /**
     * Reto Extra 8: PATCH para conmutar el estado de completada de una tarea (toggle).
     */
    // GUÍA: teoría 5.3 — PATCH que conmuta un flag usando el mapa auxiliar
    //   'completadasDb' (ya declarado).
    // 1. Si !db.containsKey(id) → 404.
    // 2. Si existe: boolean nuevo = !completadasDb.getOrDefault(id, false);
    //    completadasDb.put(id, nuevo); devuelve 200 con
    //    new TareaCompleta(id, db.get(id).titulo(), nuevo).
    // OJO: el test (db vacía) pega a /api/tareas/1/toggle → 404.
    // TODO extra: anota con @PatchMapping("/{id}/toggle").
    public ResponseEntity<TareaCompleta> toggleCompletada(@org.springframework.web.bind.annotation.PathVariable Long id) {
        // TODO extra: si el id no existe en `db`, devuelve 404.
        // Si existe, cambia su valor booleano en `completadasDb` (si no existía, ponlo a true; si ya existía, inviértelo).
        // Devuelve estatus 200 OK con un objeto `TareaCompleta` conteniendo la información actualizada.
        return null;
    }

    /**
     * Reto Extra 9: GET con soporte de paginación simple.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/paginadas")
    public List<Tarea> listarPaginado(
            @org.springframework.web.bind.annotation.RequestParam(value = "page", defaultValue = "0") int page,
            @org.springframework.web.bind.annotation.RequestParam(value = "size", defaultValue = "5") int size) {
        // GUÍA: teoría 5.2 + streams — paginación manual con skip/limit.
        // 1. return db.values().stream()
        //        .skip((long) page * size)   // salta las páginas anteriores
        //        .limit(size)                // toma como mucho 'size'
        //        .toList();
        // OJO: el test (page=0,size=2) solo comprueba 200. La paginación "de verdad"
        //   (Pageable de Spring Data) llega en el bloque 15.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 10: GET para obtener la primera tarea registrada (ID mínimo).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/primera")
    public ResponseEntity<Tarea> obtenerPrimera() {
        // GUÍA: teoría 5.2 + streams/Optional — la tarea de id mínimo, o 204 si no hay.
        // 1. return db.values().stream()
        //        .min(java.util.Comparator.comparing(Tarea::id))
        //        .map(ResponseEntity::ok)
        //        .orElseGet(() -> ResponseEntity.noContent().build());
        // OJO: el test (db vacía) espera 204 No Content. El patrón map/orElseGet
        //   evita el if-null (teoría 1.2).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

}
