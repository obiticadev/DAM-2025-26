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

    /**
     * Reto Extra 1: Comprueba si prop requiere una nueva transaccion siempre.
     */
    public static boolean requiereNueva(Propagacion prop) {
        // TODO extra: Reto Extra 1: Comprueba si prop requiere una nueva transaccion siempre.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para requiereNueva");
    }

    /**
     * Reto Extra 2: Comprueba si prop exige una transaccion activa obligatoriamente.
     */
    public static boolean exigeTransaccion(Propagacion prop) {
        // TODO extra: Reto Extra 2: Comprueba si prop exige una transaccion activa obligatoriamente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para exigeTransaccion");
    }

    /**
     * Reto Extra 3: Comprueba si prop prohibe tener transaccion activa.
     */
    public static boolean prohibeTransaccion(Propagacion prop) {
        // TODO extra: Reto Extra 3: Comprueba si prop prohibe tener transaccion activa.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para prohibeTransaccion");
    }

    /**
     * Reto Extra 4: Comprueba si prop soporta transaccion activa si existe.
     */
    public static boolean soportaTransaccion(Propagacion prop) {
        // TODO extra: Reto Extra 4: Comprueba si prop soporta transaccion activa si existe.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para soportaTransaccion");
    }

    /**
     * Reto Extra 5: Comprueba si prop requiere una transaccion (creandola si no existe).
     */
    public static boolean esRequerida(Propagacion prop) {
        // TODO extra: Reto Extra 5: Comprueba si prop requiere una transaccion (creandola si no existe).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRequerida");
    }

    /**
     * Reto Extra 6: Valida el estado transaccional.
     */
    public static boolean validarEstado(int txActiva, Propagacion prop) {
        // TODO extra: Reto Extra 6: Valida el estado transaccional.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarEstado");
    }

    /**
     * Reto Extra 7: Obtiene el nombre del tipo de propagacion.
     */
    public static String obtenerNombre(Propagacion prop) {
        // TODO extra: Reto Extra 7: Obtiene el nombre del tipo de propagacion.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerNombre");
    }

    /**
     * Reto Extra 8: Simula si habra rollback en caso de fallo.
     */
    public static boolean provocaraRollback(Propagacion prop) {
        // TODO extra: Reto Extra 8: Simula si habra rollback en caso de fallo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para provocaraRollback");
    }

    /**
     * Reto Extra 9: Comprueba si es una propagacion segura.
     */
    public static boolean esSegura(Propagacion prop) {
        // TODO extra: Reto Extra 9: Comprueba si es una propagacion segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSegura");
    }

    /**
     * Reto Extra 10: Retorna un identificador simulado.
     */
    public static String obtenerIdSimulado(Propagacion prop) {
        // TODO extra: Reto Extra 10: Retorna un identificador simulado.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerIdSimulado");
    }



}
