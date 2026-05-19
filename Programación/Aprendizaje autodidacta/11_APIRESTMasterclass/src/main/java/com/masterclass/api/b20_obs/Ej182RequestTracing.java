package com.masterclass.api.b20_obs;

/**
 * Ejercicio 182 · Trazas y correlacion de peticiones.
 *
 * <p>Teoria: {@code teoria/20_Observabilidad_Documentacion.md} (seccion 20.6).
 *
 * <p>El tracing distribuido propaga un {@code traceId} entre servicios y genera
 * un {@code spanId} por salto. Aqui modelamos la propagacion como funcion pura:
 * dado el traceId entrante (cabecera) y el numero de salto, devolvemos el
 * contexto de traza que viajaria en la siguiente llamada.
 */
public final class Ej182RequestTracing {

    private Ej182RequestTracing() {
    }

    /**
     * Propaga o genera el contexto de traza para un salto.
     *
     * @param traceIdEntrante traceId recibido en la cabecera (null/blanco = origen)
     * @param salto           numero de salto en la cadena (&gt;= 0)
     * @return contexto de traza propagado para este salto
     * @throws IllegalArgumentException si salto &lt; 0
     */
    public static TraceContext182 propagar(String traceIdEntrante, int salto) {
        // TODO 1: si salto < 0 -> IllegalArgumentException.
        // TODO 2: determina si traceIdEntrante esta ausente (null o en blanco).
        // TODO 3: si esta ausente, este nodo es el origen de la traza.
        // TODO 4: en el origen, genera un traceId nuevo (p.ej. UUID sin guiones).
        // TODO 5: si viene un traceId, reutilizalo intacto (trim) para correlacionar.
        // TODO 6: el spanId es unico por salto: derivalo de traceId + ":" + salto.
        // TODO 7: marca "raiz"=true solo si es el origen (salto 0 y sin entrante).
        // TODO 8: nunca devuelvas traceId null ni vacio (invariante de la traza).
        // TODO 9: construye el TraceContext182 con traceId, spanId y raiz.
        // TODO 10: devuelve el contexto de traza.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(propagar(null, 0));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si salto < 0 -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: determina si traceIdEntrante esta ausente (null o en blanco).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si esta ausente, este nodo es el origen de la traza.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: en el origen, genera un traceId nuevo (p.ej. UUID sin guiones).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: si viene un traceId, reutilizalo intacto (trim) para correlacionar.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: el spanId es unico por salto: derivalo de traceId + ":" + salto.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: marca "raiz"=true solo si es el origen (salto 0 y sin entrante).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: nunca devuelvas traceId null ni vacio (invariante de la traza).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: construye el TraceContext182 con traceId, spanId y raiz.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve el contexto de traza.
    }

}

/** Contexto de traza propagado entre saltos. */
record TraceContext182(String traceId, String spanId, boolean raiz) {
}
