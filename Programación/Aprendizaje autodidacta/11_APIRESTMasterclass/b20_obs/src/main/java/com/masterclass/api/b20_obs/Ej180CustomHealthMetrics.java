package com.masterclass.api.b20_obs;

import java.util.Map;

/**
 * Ejercicio 180 · Health y metricas propias.
 *
 * <p>Teoria: {@code teoria/20_Observabilidad_Documentacion.md} (seccion 20.4).
 *
 * <p>Un {@link org.springframework.boot.actuate.health.HealthIndicator} propio
 * y metricas Micrometer. Aqui calculamos como funcion pura una tasa de error y
 * un estado de salud custom a partir de contadores acumulados.
 */
public final class Ej180CustomHealthMetrics {

    private Ej180CustomHealthMetrics() {
    }

    /**
     * Calcula metricas propias y health custom a partir de contadores.
     *
     * @param total   total de peticiones procesadas (&gt;= 0)
     * @param errores numero de peticiones con error (&gt;= 0, &lt;= total)
     * @param umbral  tasa de error a partir de la cual el health es "DOWN" (0..1)
     * @return mapa con "errorRate" (String 4 decimales) y "status" (UP/DOWN)
     * @throws IllegalArgumentException si total&lt;0, errores&lt;0, errores&gt;total o umbral fuera de [0,1]
     */
    public static Map<String, String> salud(long total, long errores, double umbral) {
        // TODO 1: si total < 0 -> IllegalArgumentException.
        // TODO 2: si errores < 0 -> IllegalArgumentException.
        // TODO 3: si errores > total -> IllegalArgumentException.
        // TODO 4: si umbral < 0 o umbral > 1 -> IllegalArgumentException.
        // TODO 5: si total == 0, la tasa de error es 0.0 (evita division por cero).
        // TODO 6: en otro caso errorRate = errores / total (en double).
        // TODO 7: formatea errorRate con exactamente 4 decimales (Locale.ROOT, punto).
        // TODO 8: el status es "DOWN" si errorRate > umbral; "UP" si es <=.
        // TODO 9: construye el Map con claves "errorRate" y "status".
        // TODO 10: devuelve el mapa de metricas/health.
        return Map.of();
    }

    public static void main(String[] args) {
        System.out.println(salud(100, 5, 0.1));
    }

        /**
     * RETO EXTRA 01: Valida total >= 0.
     */
    public static boolean esTotalValido(long t) {
        // GUÍA: una línea — total no negativo (teoría 20.4, validaciones).
        // PISTA: return t >= 0;
        // OJO: el test manda 0 y espera true (cero es válido).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esTotalValido");
    }

    /**
     * RETO EXTRA 02: Valida errores.
     */
    public static boolean esErroresValido(long e, long t) {
        // GUÍA: errores válidos = no negativos y no mayores que el total.
        // PISTA: return e >= 0 && e <= t;
        // OJO: el test manda (5,10) -> true. No olvides el límite superior: no
        // puede haber más errores que peticiones totales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esErroresValido");
    }

    /**
     * RETO EXTRA 03: Valida umbral.
     */
    public static boolean esUmbralValido(double u) {
        // GUÍA: umbral es una tasa, debe caer en [0,1] (ambos inclusive).
        // PISTA: return u >= 0 && u <= 1;
        // OJO: el test manda 0.5 -> true; 2.0 sería inválido (no es una tasa).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esUmbralValido");
    }

    /**
     * RETO EXTRA 04: Calcula tasa real.
     */
    public static double calcularTasaError(long t, long e) {
        // GUÍA: tasa = errores/total con división segura (teoría 20.4).
        // OJO al orden de parámetros: la firma es (long t, long e) -> t es total, e errores.
        // 1. Si t == 0 -> 0.0 (evita dividir por cero).
        // 2. Si no, e / (double) t.
        // PISTA: return t == 0 ? 0.0 : (double) e / t;
        // ⚠ CUIDADO: el cast a double ANTES de dividir; "e / t" con dos long da 0.
        // El test manda (10, 5) y espera 0.5.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularTasaError");
    }

    /**
     * RETO EXTRA 05: Formatea con 4 decimales.
     */
    public static String formatearTasa(double tasa) {
        // GUÍA: una línea — formato con 4 decimales y punto decimal (teoría 20.4).
        // PISTA: return String.format(java.util.Locale.ROOT, "%.4f", tasa);
        // ⚠ CUIDADO: SIN Locale.ROOT, en España saldría "0,5000" (coma) y el test
        // espera EXACTAMENTE "0.5000". Este es el error nº 5 del bloque.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearTasa");
    }

    /**
     * RETO EXTRA 06: Calcula status.
     */
    public static String determinarStatus(double tasa, double umbral) {
        // GUÍA: status según umbral (teoría 20.4: DOWN si tasa SUPERA el umbral).
        // PISTA: return tasa > umbral ? "DOWN" : "UP";
        // OJO: estricto mayor (>). El límite exacto (tasa == umbral) es UP.
        // El test manda (0.2, 0.1) -> "DOWN".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para determinarStatus");
    }

    /**
     * RETO EXTRA 07: Valida si es UP.
     */
    public static boolean esStatusUp(String status) {
        // GUÍA: una línea — comparación segura contra null.
        // PISTA: return "UP".equals(status);
        // (Pon el literal a la izquierda para no petar con status null.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esStatusUp");
    }

    /**
     * RETO EXTRA 08: Valida si es DOWN.
     */
    public static boolean esStatusDown(String status) {
        // GUÍA: espejo de esStatusUp contra "DOWN".
        // PISTA: return "DOWN".equals(status);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esStatusDown");
    }

    /**
     * RETO EXTRA 09: Extrae tasa del mapa.
     */
    public static String obtenerTasaDelMapa(java.util.Map<String, String> m) {
        // GUÍA: una línea — lee la clave "errorRate" del mapa que devuelve salud().
        // PISTA: return m.get("errorRate");
        // El test manda {errorRate=0.0500} y espera "0.0500".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerTasaDelMapa");
    }

    /**
     * RETO EXTRA 10: Extrae status del mapa.
     */
    public static String obtenerStatusDelMapa(java.util.Map<String, String> m) {
        // GUÍA: espejo del reto 09 con la clave "status".
        // PISTA: return m.get("status");
        // El test manda {status=UP} y espera "UP".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerStatusDelMapa");
    }

}
