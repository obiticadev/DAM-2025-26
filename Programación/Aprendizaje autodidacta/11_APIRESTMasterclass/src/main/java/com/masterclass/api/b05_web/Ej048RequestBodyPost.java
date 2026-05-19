package com.masterclass.api.b05_web;

import org.springframework.http.ResponseEntity;

/**
 * Ejercicio 048 · @RequestBody y 201 Created.
 *
 * <p>Teoría: {@code teoria/05_Controllers_REST.md} (sección 5.3).
 *
 * <p>El test espera {@code POST /api/items} con body JSON {@code {"nombre":"x"}}
 * devolviendo 201 y el mismo item con un id asignado.
 */
// TODO 1: anota la clase con @RestController.
// TODO 2: anota la clase con @RequestMapping("/api/items").
public class Ej048RequestBodyPost {

    public record ItemIn(String nombre) {
    }

    public record ItemOut(long id, String nombre) {
    }

    /**
     * Crea un item a partir del cuerpo JSON.
     *
     * @param entrada cuerpo deserializado
     * @return 201 Created con el item resultante (id = 1 fijo para el test)
     */
    // TODO 3: anota el método con @PostMapping.
    // TODO 4: anota 'entrada' con @RequestBody para deserializar el JSON.
    public ResponseEntity<ItemOut> crear(ItemIn entrada) {
        // TODO 5: valida que entrada.nombre() no sea null/vacío (si lo es, 400 con ResponseEntity.badRequest()).
        // TODO 6: construye un ItemOut con id = 1L y el nombre recibido.
        // TODO 7: usa ResponseEntity.status(HttpStatus.CREATED) (201).
        // TODO 8: añade la cabecera Location "/api/items/1" con .location(URI.create(...)).
        // TODO 9: incluye el ItemOut en el body con .body(...).
        // TODO 10: devuelve esa ResponseEntity.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Ej048RequestBodyPost().crear(new ItemIn("café")));
    }
}
