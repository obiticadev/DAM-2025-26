package com.masterclass.api.b14_jpaadv;

/**
 * Ejercicio 123 · Propagación de transacciones (modelo conceptual).
 *
 * <p>Teoría: {@code teoria/14_JPA_Avanzado.md} (sección 14.1).
 *
 * <p>Simula qué transacción usa un método según su propagación.
 */
public final class Ej123TransactionPropagation {

    public enum Propagacion {
        REQUIRED, REQUIRES_NEW, MANDATORY, NEVER, SUPPORTS
    }

    private Ej123TransactionPropagation() {
    }

    /**
     * Devuelve el id de transacción efectivo de un método llamado desde otro.
     *
     * @param txActiva   id de la tx del llamante (0 = no hay tx activa)
     * @param prop       propagación del método llamado
     * @param siguienteId id que se asignaría si hubiera que CREAR una tx nueva
     * @return id de tx en que se ejecuta el método (0 = sin transacción)
     * @throws IllegalStateException si MANDATORY sin tx, o NEVER con tx
     */
    public static int txEfectiva(int txActiva, Propagacion prop, int siguienteId) {
        // TODO 1: REQUIRED -> si hay tx activa, úsala (txActiva); si no, crea (siguienteId).
        // TODO 2: REQUIRES_NEW -> SIEMPRE crea una nueva (siguienteId), aunque haya activa.
        // TODO 3: MANDATORY -> exige tx activa; si txActiva==0 -> IllegalStateException.
        // TODO 4: MANDATORY con tx -> usa la activa.
        // TODO 5: NEVER -> exige NO tx; si txActiva!=0 -> IllegalStateException.
        // TODO 6: NEVER sin tx -> 0 (se ejecuta sin transacción).
        // TODO 7: SUPPORTS -> usa la activa si existe; si no, 0 (sin tx, sin crear).
        // TODO 8: usa un switch sobre 'prop'.
        // TODO 9: cubre TODOS los valores del enum (exhaustivo).
        // TODO 10: devuelve el id resultante.
        return -999;
    }

    public static void main(String[] args) {
        System.out.println(txEfectiva(7, Propagacion.REQUIRES_NEW, 8));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: REQUIRED -> si hay tx activa, úsala (txActiva); si no, crea (siguienteId).
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: REQUIRES_NEW -> SIEMPRE crea una nueva (siguienteId), aunque haya activa.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: MANDATORY -> exige tx activa; si txActiva==0 -> IllegalStateException.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: MANDATORY con tx -> usa la activa.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: NEVER -> exige NO tx; si txActiva!=0 -> IllegalStateException.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: NEVER sin tx -> 0 (se ejecuta sin transacción).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: SUPPORTS -> usa la activa si existe; si no, 0 (sin tx, sin crear).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: usa un switch sobre 'prop'.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: cubre TODOS los valores del enum (exhaustivo).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve el id resultante.
    }

}
