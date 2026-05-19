package com.masterclass.api.b09_err;

import org.springframework.http.ResponseEntity;

/**
 * Ejercicio 081 · 404 vs 409 semánticos.
 *
 * <p>Teoría: {@code teoria/09_Manejo_de_Errores.md} (sección 9.3).
 *
 * <p>El test: crear "ana" -> 201; crear "ana" otra vez -> 409; leer "zoe" -> 404.
 */
// TODO 1: anota la clase con @RestController y @RequestMapping("/api/users").
public class Ej081NotFoundAndConflict {

    private final java.util.Set<String> usuarios = java.util.concurrent.ConcurrentHashMap.newKeySet();

    /**
     * @param nombre nombre del usuario a crear
     * @return 201 si se creó; 409 si ya existía
     */
    // TODO 2: anota con @PostMapping("/{nombre}") y 'nombre' con @PathVariable.
    public ResponseEntity<String> crear(String nombre) {
        // TODO 3: intenta añadir 'nombre' al set 'usuarios'.
        // TODO 4: si add() devuelve false (ya existía) -> 409 Conflict con body "duplicado".
        // TODO 5: si se añadió -> 201 Created con body "creado".
        // TODO 6: usa ResponseEntity.status(HttpStatus.CONFLICT) / CREATED según el caso.
        return null;
    }

    /**
     * @param nombre usuario a consultar
     * @return 200 si existe; 404 si no
     */
    // TODO 7: anota con @GetMapping("/{nombre}") y 'nombre' con @PathVariable.
    public ResponseEntity<String> obtener(String nombre) {
        // TODO 8: si 'usuarios' contiene el nombre -> 200 ok con el nombre.
        // TODO 9: si no -> 404 Not Found (ResponseEntity.notFound().build()).
        // TODO 10: 404 = no existe; 409 = existe pero choca. No los confundas.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Ej081NotFoundAndConflict().crear("ana"));
    }
}
