package com.masterclass.api.b05_web;

/**
 * Ejercicio 050 · PUT (reemplazo total, idempotente).
 *
 * <p>Teoría: {@code teoria/05_Controllers_REST.md} (sección 5.3).
 *
 * <p>El test espera {@code PUT /api/items/{id}} con body {@code {"nombre":"x"}}
 * devolviendo 200 y el item con ese id y nombre.
 */
// TODO 1: anota la clase con @RestController.
// TODO 2: anota la clase con @RequestMapping("/api/items").
public class Ej050PutFullUpdate {

    public record ItemDto(long id, String nombre) {
    }

    public record ItemBody(String nombre) {
    }

    /**
     * Reemplaza por completo el recurso identificado por id.
     *
     * @param id     identificador en la ruta
     * @param cuerpo nueva representación completa
     * @return el item actualizado (status 200)
     */
    // TODO 3: anota el método con @PutMapping("/{id}").
    // TODO 4: anota 'id' con @PathVariable.
    // TODO 5: anota 'cuerpo' con @RequestBody.
    public ItemDto reemplazar(long id, ItemBody cuerpo) {
        // TODO 6: PUT reemplaza TODO el recurso (no parchea campos sueltos).
        // TODO 7: valida que cuerpo.nombre() no sea null (PUT exige representación completa).
        // TODO 8: construye un ItemDto con el id de la ruta y el nombre del cuerpo.
        // TODO 9: PUT es idempotente: repetir la misma petición da el mismo resultado.
        // TODO 10: devuelve el ItemDto (200 implícito).
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Ej050PutFullUpdate().reemplazar(7, new ItemBody("nuevo")));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: anota la clase con @RestController.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: anota la clase con @RequestMapping("/api/items").
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: anota el método con @PutMapping("/{id}").
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: anota 'id' con @PathVariable.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: anota 'cuerpo' con @RequestBody.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: PUT reemplaza TODO el recurso (no parchea campos sueltos).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: valida que cuerpo.nombre() no sea null (PUT exige representación completa).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: construye un ItemDto con el id de la ruta y el nombre del cuerpo.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: PUT es idempotente: repetir la misma petición da el mismo resultado.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve el ItemDto (200 implícito).
    }

}
