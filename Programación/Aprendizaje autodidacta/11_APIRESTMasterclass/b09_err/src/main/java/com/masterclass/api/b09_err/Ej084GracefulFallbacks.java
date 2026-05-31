package com.masterclass.api.b09_err;

import java.util.function.Supplier;

/**
 * Ejercicio 084 · Degradación controlada (graceful fallback).
 *
 * <p>Teoría: {@code teoria/09_Manejo_de_Errores.md} (sección 9.3).
 *
 * <p>Si una dependencia falla, a veces es mejor devolver un valor degradado
 * que un 500. Implementa esa política con registro de fallos.
 */
public class Ej084GracefulFallbacks {

    private int fallos = 0;

    /**
     * Intenta la operación principal; si lanza, devuelve el fallback y registra el fallo.
     *
     * @param principal operación que puede fallar
     * @param fallback  valor seguro a devolver si la principal falla
     * @param <T>       tipo de resultado
     * @return resultado de la principal, o el fallback
     */
    public <T> T conFallback(Supplier<T> principal, T fallback) {
        // TODO 1: valida que 'principal' no sea null.
        // TODO 2: abre un try alrededor de principal.get().
        // TODO 3: si tiene éxito, devuelve su resultado directamente.
        // TODO 4: NO incrementes el contador de fallos si fue bien.
        // TODO 5: captura cualquier RuntimeException.
        // TODO 6: en el catch, incrementa 'fallos'.
        // TODO 7: registra (conceptual) que se degradó (aquí basta el contador).
        // TODO 8: devuelve 'fallback' como valor degradado (no relances).
        // TODO 9: el fallback puede ser null si así se decidió (no fuerces no-null).
        // TODO 10: el método nunca debe propagar la excepción original.
        return null;
    }

    /**
     * @return número de veces que se activó el fallback
     */
    public int fallos() {
        return fallos;
    }

    public static void main(String[] args) {
        var g = new Ej084GracefulFallbacks();
        System.out.println(g.conFallback(() -> {
            throw new RuntimeException("down");
        }, "cache"));
    }

        /**
     * RETO EXTRA 01: Determina si el error requiere desvio al circuito de contingencia.
     */
    public static boolean esErrorCritico(int status) {
        // TODO extra: RETO EXTRA 01: Determina si el error requiere desvio al circuito de contingencia.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esErrorCritico");
    }

    /**
     * RETO EXTRA 02: Genera el payload de contingencia limpia.
     */
    public static String crearRespuestaFallback(String backupData) {
        // TODO extra: RETO EXTRA 02: Genera el payload de contingencia limpia.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearRespuestaFallback");
    }

    /**
     * RETO EXTRA 03: Determina si el Circuit Breaker esta en estado OPEN.
     */
    public static boolean esCircuitoAbierto(String state) {
        // TODO extra: RETO EXTRA 03: Determina si el Circuit Breaker esta en estado OPEN.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCircuitoAbierto");
    }

    /**
     * RETO EXTRA 04: Verifica que el retardo de recuperacion sea razonable.
     */
    public static boolean tiempoEsperaSeguro(long ms) {
        // TODO extra: RETO EXTRA 04: Verifica que el retardo de recuperacion sea razonable.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tiempoEsperaSeguro");
    }

    /**
     * RETO EXTRA 05: Determina si proviene del subsistema RestClient/WebClient.
     */
    public static boolean esFalloServicioExterno(Throwable t) {
        // TODO extra: RETO EXTRA 05: Determina si proviene del subsistema RestClient/WebClient.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFalloServicioExterno");
    }

    /**
     * RETO EXTRA 06: Determina si la peticion es de lectura (GET) y segura para reintentar.
     */
    public static boolean esErrorHttpSoportado(String method) {
        // TODO extra: RETO EXTRA 06: Determina si la peticion es de lectura (GET) y segura para reintentar.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esErrorHttpSoportado");
    }

    /**
     * RETO EXTRA 07: Genera la notificacion de degradacion de servicio.
     */
    public static String crearMensajeDegradado(String originalMsg) {
        // TODO extra: RETO EXTRA 07: Genera la notificacion de degradacion de servicio.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearMensajeDegradado");
    }

    /**
     * RETO EXTRA 08: Determina si la cache de contingencia tambien cayo.
     */
    public static boolean esFalloPersistenciaContingencia(Throwable t) {
        // TODO extra: RETO EXTRA 08: Determina si la cache de contingencia tambien cayo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFalloPersistenciaContingencia");
    }

    /**
     * RETO EXTRA 09: Calcula retraso exponencial con tope maximo.
     */
    public static long calcularBackoffExponencial(int intento, long baseMs) {
        // TODO extra: RETO EXTRA 09: Calcula retraso exponencial con tope maximo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularBackoffExponencial");
    }

    /**
     * RETO EXTRA 10: Resuelve la accion correctiva (RETRY, CACHE, ERROR).
     */
    public static String determinarEstrategiaFallback(int code) {
        // TODO extra: RETO EXTRA 10: Resuelve la accion correctiva (RETRY, CACHE, ERROR).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para determinarEstrategiaFallback");
    }

}