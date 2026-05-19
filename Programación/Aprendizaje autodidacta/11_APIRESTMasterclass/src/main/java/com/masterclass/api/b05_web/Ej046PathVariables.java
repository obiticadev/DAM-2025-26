package com.masterclass.api.b05_web;

/**
 * Ejercicio 046 · @PathVariable.
 *
 * <p>Teoría: {@code teoria/05_Controllers_REST.md} (sección 5.2).
 *
 * <p>El test espera {@code GET /api/echo/{valor}} devolviendo el valor recibido.
 */
// TODO 1: anota la clase con @RestController.
// TODO 2: anota la clase con @RequestMapping("/api").
public class Ej046PathVariables {

    /**
     * Devuelve el segmento de ruta recibido.
     *
     * @param valor segmento variable de la URL
     * @return el mismo valor como cuerpo
     */
    // TODO 3: anota el método con @GetMapping("/echo/{valor}").
    // TODO 4: anota el parámetro con @PathVariable para enlazarlo al segmento {valor}.
    public String echo(String valor) {
        // TODO 5: el nombre del @PathVariable debe coincidir con "{valor}" de la ruta.
        // TODO 6: si difieren, usa @PathVariable("valor").
        // TODO 7: no transformes el valor (eco literal).
        // TODO 8: el cuerpo de respuesta es exactamente 'valor'.
        // TODO 9: status 200 implícito al devolver normalmente.
        // TODO 10: devuelve 'valor'.
        return "";
    }

    public static void main(String[] args) {
        System.out.println(new Ej046PathVariables().echo("abc"));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: anota la clase con @RestController.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: anota la clase con @RequestMapping("/api").
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: anota el método con @GetMapping("/echo/{valor}").
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: anota el parámetro con @PathVariable para enlazarlo al segmento {valor}.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: el nombre del @PathVariable debe coincidir con "{valor}" de la ruta.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: si difieren, usa @PathVariable("valor").
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: no transformes el valor (eco literal).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: el cuerpo de respuesta es exactamente 'valor'.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: status 200 implícito al devolver normalmente.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve 'valor'.
    }

}
