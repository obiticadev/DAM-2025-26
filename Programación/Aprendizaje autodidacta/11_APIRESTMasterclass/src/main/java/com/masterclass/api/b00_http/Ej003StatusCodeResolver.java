package com.masterclass.api.b00_http;

/**
 * Ejercicio 003 · Clasificador de códigos de estado.
 *
 * <p>Teoría: {@code teoria/00_Fundamentos_HTTP_Web.md} (sección 0.4).
 */
public final class Ej003StatusCodeResolver {

    private Ej003StatusCodeResolver() {
    }

    /**
     * Devuelve la familia textual del código.
     *
     * @param status código HTTP entre 100 y 599
     * @return uno de: "Informativa", "Exito", "Redireccion", "ErrorCliente", "ErrorServidor"
     * @throws IllegalArgumentException si el código está fuera de [100, 599]
     */
    public static String family(int status) {
        // TODO 1: si status < 100 o status > 599, lanza IllegalArgumentException.
        // TODO 2: rango [100,199] -> "Informativa".
        // TODO 3: rango [200,299] -> "Exito".
        // TODO 4: rango [300,399] -> "Redireccion".
        // TODO 5: rango [400,499] -> "ErrorCliente".
        // TODO 6: rango [500,599] -> "ErrorServidor".
        return "";
    }

    /**
     * Indica si el código representa un error (4xx o 5xx).
     *
     * @param status código HTTP
     * @return true si es 4xx o 5xx
     */
    public static boolean isError(int status) {
        // TODO 7: reutiliza family(status) y compara contra "ErrorCliente"/"ErrorServidor".
        // TODO 8: no dupliques la lógica de rangos: apóyate en family().
        return false;
    }

    /**
     * Indica si la culpa del error es del cliente (4xx).
     *
     * @param status código HTTP
     * @return true solo para la familia 4xx
     */
    public static boolean isClientFault(int status) {
        // TODO 9: true únicamente si family(status) == "ErrorCliente".
        // TODO 10: un 5xx NO es culpa del cliente -> debe devolver false.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("404 -> " + family(404) + " error=" + isError(404) + " cliente=" + isClientFault(404));
        System.out.println("200 -> " + family(200) + " error=" + isError(200));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si status < 100 o status > 599, lanza IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: rango [100,199] -> "Informativa".
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: rango [200,299] -> "Exito".
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: rango [300,399] -> "Redireccion".
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: rango [400,499] -> "ErrorCliente".
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: rango [500,599] -> "ErrorServidor".
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: reutiliza family(status) y compara contra "ErrorCliente"/"ErrorServidor".
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: no dupliques la lógica de rangos: apóyate en family().
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: true únicamente si family(status) == "ErrorCliente".
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: un 5xx NO es culpa del cliente -> debe devolver false.
    }

}
