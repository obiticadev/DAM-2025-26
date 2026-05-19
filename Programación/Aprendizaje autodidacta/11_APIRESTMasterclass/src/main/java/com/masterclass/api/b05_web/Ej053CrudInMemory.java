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

    public static void pasoExtra01() {
        // TODO extra aislando concepto: anota la clase con @RestController y @RequestMapping("/api/tareas").
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: anota con @GetMapping; devuelve la colección de db.values() como List.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: devuelve una nueva List a partir de db.values().
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: anota con @GetMapping("/{id}") y 'id' con @PathVariable.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: busca en db; si está -> ResponseEntity.ok(tarea); si no -> notFound().
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: anota con @PostMapping y 'nueva' con @RequestBody.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: genera id con seq.incrementAndGet(), crea la Tarea y guárdala en db.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: devuelve 201 (ResponseEntity.status(CREATED).body(creada)).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: anota con @DeleteMapping("/{id}") y 'id' con @PathVariable.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: db.remove(id); si devolvió algo -> 204 noContent; si null -> 404 notFound.
    }

}
