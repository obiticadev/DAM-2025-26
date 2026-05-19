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

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si total < 0 -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si errores < 0 -> IllegalArgumentException.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si errores > total -> IllegalArgumentException.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: si umbral < 0 o umbral > 1 -> IllegalArgumentException.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: si total == 0, la tasa de error es 0.0 (evita division por cero).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: en otro caso errorRate = errores / total (en double).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: formatea errorRate con exactamente 4 decimales (Locale.ROOT, punto).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: el status es "DOWN" si errorRate > umbral; "UP" si es <=.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: construye el Map con claves "errorRate" y "status".
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve el mapa de metricas/health.
    }

}
