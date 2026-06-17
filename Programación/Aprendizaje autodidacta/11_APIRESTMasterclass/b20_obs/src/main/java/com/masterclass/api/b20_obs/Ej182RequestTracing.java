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

        /**
     * RETO EXTRA 01: Valida salto >= 0.
     */
    public static boolean esSaltoValido(int s) {
        // GUÍA: una línea — el salto es un índice no negativo (teoría 20.6).
        // PISTA: return s >= 0;
        // El test manda 1 -> true. (0 también sería válido: es el origen.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSaltoValido");
    }

    /**
     * RETO EXTRA 02: Valida traceId no nulo/blanco.
     */
    public static boolean esTraceIdValido(String id) {
        // GUÍA: la invariante de oro (teoría 20.6): un traceId nunca null ni blanco.
        // PISTA: return id != null && !id.isBlank();
        // OJO: usa isBlank (cubre " "), no isEmpty. El test manda "123" -> true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esTraceIdValido");
    }

    /**
     * RETO EXTRA 03: Crea contexto.
     */
    public static TraceContext182 crearTraceContext(String t, String s, boolean r) {
        // GUÍA: una línea — factoría del record (orden: traceId, spanId, raiz).
        // return new TraceContext182(t, s, r);
        // El test solo comprueba assertNotNull.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearTraceContext");
    }

    /**
     * RETO EXTRA 04: Obtiene traceId.
     */
    public static String obtenerTraceId(TraceContext182 ctx) {
        // GUÍA: una línea — accesor del record.
        // return ctx.traceId();
        // El test manda TraceContext182("a","b",true) y espera "a".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerTraceId");
    }

    /**
     * RETO EXTRA 05: Obtiene spanId.
     */
    public static String obtenerSpanId(TraceContext182 ctx) {
        // GUÍA: una línea — accesor del record.
        // return ctx.spanId();
        // El test espera "b".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerSpanId");
    }

    /**
     * RETO EXTRA 06: Verifica si es raiz.
     */
    public static boolean esRaiz(TraceContext182 ctx) {
        // GUÍA: una línea — accesor del boolean del record.
        // return ctx.raiz();
        // El test manda raiz=true y espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRaiz");
    }

    /**
     * RETO EXTRA 07: Genera un traceId UUID sin guiones.
     */
    public static String generarTraceIdAleatorio() {
        // GUÍA: una línea — UUID sin guiones (teoría 20.6, el mismo que usa
        // propagar() en el origen).
        // return java.util.UUID.randomUUID().toString().replace("-", "");
        // OJO: un UUID tiene 36 chars con 4 guiones; al quitarlos quedan 32.
        // El test comprueba exactamente length()==32.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarTraceIdAleatorio");
    }

    /**
     * RETO EXTRA 08: Obtiene una derivacion del spanId.
     */
    public static String obtenerDobleSalto(TraceContext182 ctx, int s) {
        // GUÍA: deriva un spanId con el salto DUPLICADO -> traceId + ":" + (s*2).
        // PISTA: return ctx.traceId() + ":" + (s * 2);
        // OJO: el test manda traceId="a" y s=2, y espera "a:4" (NO "a:2"): el
        // salto se multiplica por 2. Mismo formato traceId+":"+n que el spanId base.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerDobleSalto");
    }

    /**
     * RETO EXTRA 09: Verifica si los contextos comparten traceId.
     */
    public static boolean esMismoTraceId(TraceContext182 c1, TraceContext182 c2) {
        // GUÍA: dos contextos están en la MISMA traza si comparten traceId
        // (aunque su spanId difiera: teoría 20.6).
        // PISTA: return java.util.Objects.equals(c1.traceId(), c2.traceId());
        // OJO: el test manda dos ctx con traceId="a" pero spanId distinto ("b"/"c")
        // y raiz distinta -> true. Compara SOLO traceId, no el contexto entero.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMismoTraceId");
    }

    /**
     * RETO EXTRA 10: Formatea el flujo.
     */
    public static String formatearTrazado(TraceContext182 ctx) {
        // GUÍA: representación legible "traceId->spanId".
        // PISTA: return ctx.traceId() + "->" + ctx.spanId();
        // OJO: el separador es la flecha EXACTA "->" (sin espacios). El test manda
        // ("a","b",true) y espera "a->b".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearTrazado");
    }

}

/** Contexto de traza propagado entre saltos. */
record TraceContext182(String traceId, String spanId, boolean raiz) {
}
