package com.masterclass.api.b01_java;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Ejercicio 020 · API java.time para APIs (fechas en JSON).
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.6).
 */
public final class Ej020DateTimeApi {

    private Ej020DateTimeApi() {
    }

    /**
     * Días entre dos fechas (puede ser negativo si fin &lt; inicio).
     *
     * @param inicio fecha inicial
     * @param fin    fecha final
     * @return número de días
     */
    public static long diasEntre(LocalDate inicio, LocalDate fin) {
        // TODO 1: usa ChronoUnit.DAYS.
        // TODO 2: aplica between(inicio, fin) (el orden importa: define el signo).
        // TODO 3: devuelve el resultado (negativo si fin < inicio).
        return Long.MIN_VALUE;
    }

    /**
     * Indica si un token emitido en {@code emitido} ya caducó respecto a {@code ahora}.
     *
     * @param emitido      instante de emisión
     * @param ahora        instante actual
     * @param minutosVida  validez en minutos
     * @return true si han pasado más de 'minutosVida' minutos
     */
    public static boolean haCaducado(LocalDateTime emitido, LocalDateTime ahora, long minutosVida) {
        // TODO 4: calcula el instante de expiración = emitido.plusMinutes(minutosVida).
        // TODO 5: compara 'ahora' con ese instante usando isAfter.
        // TODO 6: caducado = ahora está estrictamente después de la expiración.
        // TODO 7: devuelve ese booleano.
        return false;
    }

    /**
     * Formatea una fecha en ISO-8601 (yyyy-MM-dd).
     *
     * @param fecha fecha a formatear
     * @return representación ISO
     */
    public static String aIso(LocalDate fecha) {
        // TODO 8: LocalDate ya serializa en ISO-8601 con toString().
        // TODO 9: invoca fecha.toString() de forma explícita.
        // TODO 10: devuelve esa cadena (formato yyyy-MM-dd).
        return "";
    }

    public static void main(String[] args) {
        System.out.println(diasEntre(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 11)));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: usa ChronoUnit.DAYS.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: aplica between(inicio, fin) (el orden importa: define el signo).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: devuelve el resultado (negativo si fin < inicio).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: calcula el instante de expiración = emitido.plusMinutes(minutosVida).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: compara 'ahora' con ese instante usando isAfter.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: caducado = ahora está estrictamente después de la expiración.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: devuelve ese booleano.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: LocalDate ya serializa en ISO-8601 con toString().
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: invoca fecha.toString() de forma explícita.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve esa cadena (formato yyyy-MM-dd).
    }

}
