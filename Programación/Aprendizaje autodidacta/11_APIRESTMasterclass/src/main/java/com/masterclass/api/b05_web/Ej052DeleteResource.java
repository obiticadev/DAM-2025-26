package com.masterclass.api.b05_web;

import org.springframework.http.ResponseEntity;

/**
 * Ejercicio 052 · DELETE y 204 No Content.
 *
 * <p>Teoría: {@code teoria/05_Controllers_REST.md} (sección 5.3).
 *
 * <p>El test espera {@code DELETE /api/items/{id}}: 204 si id &gt; 0,
 * 404 si id &lt;= 0 (recurso inexistente simulado).
 */
// TODO 1: anota la clase con @RestController.
// TODO 2: anota la clase con @RequestMapping("/api/items").
public class Ej052DeleteResource {

    /**
     * Borra el recurso identificado por id.
     *
     * @param id identificador (válido si &gt; 0 en esta simulación)
     * @return 204 si se borró; 404 si el recurso no existía
     */
    // TODO 3: anota el método con @DeleteMapping("/{id}").
    // TODO 4: anota 'id' con @PathVariable.
    public ResponseEntity<Void> borrar(long id) {
        // TODO 5: simula la existencia: el recurso "existe" si id > 0.
        // TODO 6: si id <= 0, devuelve ResponseEntity.notFound().build() (404).
        // TODO 7: si existe, ejecuta el borrado (aquí no-op simulado).
        // TODO 8: una respuesta 204 NO lleva body (ResponseEntity<Void>).
        // TODO 9: usa ResponseEntity.noContent().build() para el 204.
        // TODO 10: devuelve la ResponseEntity adecuada según el caso.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Ej052DeleteResource().borrar(5));
    }
}
