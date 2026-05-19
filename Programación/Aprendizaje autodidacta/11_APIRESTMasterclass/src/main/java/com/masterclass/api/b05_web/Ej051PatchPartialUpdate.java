package com.masterclass.api.b05_web;

import java.util.Map;

/**
 * Ejercicio 051 · PATCH (modificación parcial).
 *
 * <p>Teoría: {@code teoria/05_Controllers_REST.md} (sección 5.3).
 *
 * <p>El test parte de un item {id=1, nombre="viejo", activo=true} y envía
 * {@code PATCH /api/items/1} con {@code {"nombre":"nuevo"}} esperando que solo
 * cambie 'nombre'.
 */
// TODO 1: anota la clase con @RestController.
// TODO 2: anota la clase con @RequestMapping("/api/items").
public class Ej051PatchPartialUpdate {

    public record ItemDto(long id, String nombre, boolean activo) {
    }

    /**
     * Aplica un parche parcial: solo los campos presentes en el mapa cambian.
     *
     * @param id     identificador del recurso
     * @param cambios mapa con los campos a modificar ("nombre" y/o "activo")
     * @return el item resultante tras aplicar solo los cambios indicados
     */
    // TODO 3: anota el método con @PatchMapping("/{id}").
    // TODO 4: anota 'id' con @PathVariable y 'cambios' con @RequestBody.
    public ItemDto patch(long id, Map<String, Object> cambios) {
        // TODO 5: parte de un estado base: nombre="viejo", activo=true (simulado en memoria).
        // TODO 6: si 'cambios' contiene la clave "nombre", actualiza solo ese campo.
        // TODO 7: si 'cambios' contiene "activo", actualiza solo ese campo (cast a boolean).
        // TODO 8: los campos NO presentes en 'cambios' deben conservar su valor base.
        // TODO 9: a diferencia de PUT, PATCH NO exige el recurso completo.
        // TODO 10: construye y devuelve el ItemDto con id + estado resultante.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Ej051PatchPartialUpdate().patch(1, Map.of("nombre", "nuevo")));
    }
}
