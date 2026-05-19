package com.masterclass.api.b09_err;

import java.util.Map;

/**
 * Ejercicio 082 · Traza y correlación en errores.
 *
 * <p>Teoría: {@code teoria/09_Manejo_de_Errores.md} (sección 9.2).
 *
 * <p>Cada error debe llevar un traceId para poder buscarlo en los logs.
 */
public final class Ej082ErrorTraceAndCorrelation {

    private Ej082ErrorTraceAndCorrelation() {
    }

    /**
     * Construye el cuerpo de error incluyendo un traceId de correlación.
     *
     * @param status        código HTTP
     * @param mensaje       detalle del error
     * @param incomingTrace traceId que venía en la petición (puede ser null)
     * @return mapa con claves "status", "error", "traceId"
     */
    public static Map<String, Object> errorBody(int status, String mensaje, String incomingTrace) {
        // TODO 1: si status < 400 -> IllegalArgumentException (no es un error).
        // TODO 2: decide el traceId: si incomingTrace no es null/blank, reúsalo (correlación end-to-end).
        // TODO 3: si no viene, genera uno nuevo con UUID.randomUUID().toString().
        // TODO 4: el traceId nunca debe ser null en la respuesta.
        // TODO 5: usa un LinkedHashMap para orden estable de claves.
        // TODO 6: añade "status" = status.
        // TODO 7: añade "error" = mensaje.
        // TODO 8: añade "traceId" = el traceId resuelto.
        // TODO 9: reusar el trace entrante permite seguir la petición entre microservicios.
        // TODO 10: devuelve el mapa.
        return Map.of();
    }

    public static void main(String[] args) {
        System.out.println(errorBody(500, "boom", "abc-123"));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si status < 400 -> IllegalArgumentException (no es un error).
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: decide el traceId: si incomingTrace no es null/blank, reúsalo (correlación end-to-end).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si no viene, genera uno nuevo con UUID.randomUUID().toString().
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: el traceId nunca debe ser null en la respuesta.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: usa un LinkedHashMap para orden estable de claves.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: añade "status" = status.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: añade "error" = mensaje.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: añade "traceId" = el traceId resuelto.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: reusar el trace entrante permite seguir la petición entre microservicios.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve el mapa.
    }

}
