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

        /**
     * RETO EXTRA 01: Comprueba que la correlacion tenga un formato UUID/hexadecimal estandar.
     */
    public static boolean esTraceIdValido(String traceId) {
        // GUÍA: valida que el traceId tenga un formato aceptable (9.6).
        // 1. null o en blanco → false.
        // 2. El test pasa "uuid123" (NO es un UUID canónico con guiones), así que
        //    el criterio es laxo: no vacío y solo caracteres seguros.
        //    PISTA: return traceId != null && !traceId.isBlank()
        //                  && traceId.matches("[a-zA-Z0-9-]+");
        // OJO: no exijas el formato 8-4-4-4-12 de UUID o "uuid123" fallaría.
        // El objetivo real es rechazar trazas con espacios/saltos que permitirían
        // inyección en logs (lo afina longitudCorrectaTraza, reto 10).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esTraceIdValido");
    }

    /**
     * RETO EXTRA 02: Genera un identificador aleatorio UUID para seguimiento.
     */
    public static String crearTraceIdNuevo() {
        // GUÍA: una línea — return java.util.UUID.randomUUID().toString();
        // Es exactamente el "si no viene, genero uno" del ejercicio base (9.6).
        // OJO: el test solo exige assertNotNull; UUID.randomUUID() nunca es null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearTraceIdNuevo");
    }

    /**
     * RETO EXTRA 03: Concatena identificador de flujo y log.
     */
    public static String formatearLogConTrace(String traceId, String log) {
        // GUÍA: una línea — return "[" + traceId + "] " + log;
        // OJO: el test pasa ("TRACE-1", "log msg") y espera EXACTAMENTE
        // "[TRACE-1] log msg": corchetes alrededor del trace y un espacio tras "]".
        // Es la versión rigurosa de construirCuerpoLog (Ej077 reto 9).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearLogConTrace");
    }

    /**
     * RETO EXTRA 04: Identifica si es el cabezal HTTP de seguimiento standard.
     */
    public static boolean esHeaderCorrelacion(String headerName) {
        // GUÍA: reconoce las cabeceras estándar de correlación.
        // 1. null → false.
        // 2. Compara IGNORANDO mayúsculas (los nombres de cabecera HTTP no
        //    distinguen caso) con los nombres conocidos:
        //       return headerName != null &&
        //           (headerName.equalsIgnoreCase("X-Correlation-ID")
        //         || headerName.equalsIgnoreCase("X-Request-ID")
        //         || headerName.equalsIgnoreCase("X-Trace-ID"));
        // OJO: el test pasa "X-Correlation-ID" → true. Usa equalsIgnoreCase, no
        // equals (una cabecera "x-correlation-id" en minúsculas es la misma).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esHeaderCorrelacion");
    }

    /**
     * RETO EXTRA 05: Limpia y valida el valor de correlacion recibido.
     */
    public static String obtenerTraceIdDeCabezal(String headerValue) {
        // GUÍA: normaliza el valor de la cabecera quitando espacios sobrantes.
        // 1. null → null o "" (defensa).
        // 2. return headerValue.trim();
        // OJO: el test pasa " val " (con espacios) y espera "val". trim() quita
        // los extremos. Un valor con espacios sin limpiar contaminaría el log.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerTraceIdDeCabezal");
    }

    /**
     * RETO EXTRA 06: Determina si pertenecen al mismo flujo global.
     */
    public static boolean esErrorRelacionado(String trace1, String trace2) {
        // GUÍA: dos errores son del mismo flujo si comparten traceId.
        // 1. Si alguno es null → false (no se puede correlacionar).
        // 2. return trace1 != null && trace1.equals(trace2);
        // OJO: el test pasa ("TR-1", "TR-1") → true. Usa equals (compara
        // contenido), nunca == (compararía referencias). Esta es la base de
        // agrupar logs por petición (9.6).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esErrorRelacionado");
    }

    /**
     * RETO EXTRA 07: Obtiene el valor del cabezal de respuesta HTTP.
     */
    public static String generarCabeceraRespuesta(String traceId) {
        // GUÍA: el valor que devolverás en la cabecera de respuesta ES el traceId.
        // PISTA: return traceId;
        // OJO: el test pasa "trace-id-1" y espera "trace-id-1" (equals). El método
        // existe para dejar claro el contrato: el servidor DEVUELVE el mismo
        // traceId en la respuesta para que el cliente lo vea y lo registre.
        // (Defensa: si fuera null, genera uno con crearTraceIdNuevo del reto 2.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarCabeceraRespuesta");
    }

    /**
     * RETO EXTRA 08: Determina si el error apunta a problemas del colector de trazas.
     */
    public static boolean esExcepcionDeSeguimiento(Throwable t) {
        // GUÍA: detecta un fallo del sistema de trazas por su mensaje.
        // 1. null o sin mensaje → false.
        // 2. return t != null && t.getMessage() != null
        //        && t.getMessage().toLowerCase().contains("trace");
        // OJO: el test pasa new IllegalArgumentException("trace") → true. El
        // criterio es por mensaje, no por tipo (cualquier excepción que mencione
        // "trace" cuenta).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionDeSeguimiento");
    }

    /**
     * RETO EXTRA 09: Resuelve una traza invalida asignando un fallback seguro.
     */
    public static String crearContingenciaCorrelacion(String badTrace) {
        // GUÍA: si la traza entrante es inválida, genera una nueva (9.6, política
        // "el traceId nunca es null").
        // 1. Si badTrace es válido (no null/blank), reúsalo.
        // 2. Si no, devuelve uno nuevo: UUID.randomUUID().toString()
        //    (o reutiliza crearTraceIdNuevo del reto 2).
        //    PISTA: return (badTrace != null && !badTrace.isBlank())
        //                  ? badTrace : crearTraceIdNuevo();
        // OJO: el test pasa null y exige assertNotNull → el resultado NUNCA es
        // null. Es el mismo "reusa o genera" del errorBody base.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearContingenciaCorrelacion");
    }

    /**
     * RETO EXTRA 10: Comprueba limites seguros de la traza para evitar inyecciones en logs.
     */
    public static boolean longitudCorrectaTraza(String traceId) {
        // GUÍA: la traza debe tener una longitud razonable (ni vacía ni enorme)
        // para no permitir inyecciones de log.
        // 1. null → false.
        // 2. Comprueba un rango de longitud, p.ej. entre 1 y 64:
        //       return traceId != null && traceId.length() >= 8
        //              && traceId.length() <= 64;
        // OJO: el test pasa "1234567890abcdef" (16 chars) → true. Elige unos
        // límites que dejen pasar 16; un techo evita que un atacante meta un
        // traceId gigante con saltos de línea para falsear los logs.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para longitudCorrectaTraza");
    }

}