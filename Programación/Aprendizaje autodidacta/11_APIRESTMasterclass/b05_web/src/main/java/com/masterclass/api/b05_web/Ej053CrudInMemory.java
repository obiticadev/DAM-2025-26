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
    // TODO extra: anota con @org.springframework.web.bind.annotation.PutMapping("/{
        // TODO extra: Reto Extra 1: PUT para actualizar una tarea por completo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PutMapping");
    }")
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
    // TODO extra: anota con @org.springframework.web.bind.annotation.PatchMapping("/{
        // TODO extra: Reto Extra 2: PATCH para actualizar parcialmente el título de una tarea.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PatchMapping");
    }")
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
        // TODO extra: Reto Extra 3: GET con filtrado de tareas por título (Query Param).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 4: DELETE global para vaciar la base de datos de tareas.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.DeleteMapping("")
    public ResponseEntity<Void> limpiarTodo() {
        // TODO extra: Reto Extra 4: DELETE global para vaciar la base de datos de tareas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para DeleteMapping");
    }

    /**
     * Reto Extra 5: GET para contar la cantidad total de tareas registradas.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/count")
    public java.util.Map<String, Long> contarTareas() {
        // TODO extra: Reto Extra 5: GET para contar la cantidad total de tareas registradas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 6: POST para creación masiva (Bulk POST).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/bulk")
    public ResponseEntity<List<Tarea>> crearVarias(
            @org.springframework.web.bind.annotation.RequestBody List<Tarea> tareas) {
        // TODO extra: Reto Extra 6: POST para creación masiva (Bulk POST).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PostMapping");
    }

    /**
     * Reto Extra 7: POST para duplicar una tarea existente.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/{
        // TODO extra: Reto Extra 7: POST para duplicar una tarea existente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PostMapping");
    }/duplicar")
    public ResponseEntity<Tarea> duplicarTarea(@org.springframework.web.bind.annotation.PathVariable Long id) {
        // TODO extra: si el id no existe en `db`, devuelve 404 (Not Found).
        // Si existe, genera un nuevo id con seq.incrementAndGet(), guarda una copia de la tarea con el nuevo id
        // y el sufijo " (copia)" al final del título. Retorna 201 Created con la tarea duplicada.
        return null;
    }

    /**
     * Reto Extra 8: PATCH para conmutar el estado de completada de una tarea (toggle).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PatchMapping("/{
        // TODO extra: Reto Extra 8: PATCH para conmutar el estado de completada de una tarea (toggle).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PatchMapping");
    }/toggle")
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
        // TODO extra: Reto Extra 9: GET con soporte de paginación simple.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 10: GET para obtener la primera tarea registrada (ID mínimo).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/primera")
    public ResponseEntity<Tarea> obtenerPrimera() {
        // TODO extra: Reto Extra 10: GET para obtener la primera tarea registrada (ID mínimo).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

}
