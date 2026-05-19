package com.masterclass.api.b21_perf;

import java.util.function.Supplier;

/**
 * Ejercicio 186 · Reintentos y circuit breaker.
 *
 * <p>Teoria: {@code teoria/21_Rendimiento_Resiliencia.md} (seccion 21.4).
 *
 * <p>Los reintentos con backoff absorben fallos transitorios; el circuit
 * breaker (CLOSED &rarr; OPEN &rarr; HALF_OPEN) corta llamadas a un servicio caido.
 * Aqui ambos se modelan como logica pura y determinista.
 */
public final class Ej186RetryAndCircuitBreaker {

    private Ej186RetryAndCircuitBreaker() {
    }

    /**
     * Ejecuta {@code accion} reintentando ante excepcion hasta {@code maxIntentos} veces.
     *
     * @param accion       operacion que puede lanzar excepcion (no null)
     * @param maxIntentos  numero total de intentos permitidos (&gt;= 1)
     * @param contador     array de 1 elemento donde se cuenta cada intento real
     * @return el resultado del primer intento exitoso
     * @throws IllegalArgumentException si accion null, maxIntentos&lt;1 o contador invalido
     * @throws RuntimeException         si se agotan todos los intentos (propaga el ultimo fallo)
     */
    public static String conReintentos(Supplier<String> accion, int maxIntentos, int[] contador) {
        // TODO 1: si accion es null -> IllegalArgumentException.
        // TODO 2: si maxIntentos < 1 -> IllegalArgumentException.
        // TODO 3: si contador es null o contador.length != 1 -> IllegalArgumentException.
        // TODO 4: bucle desde intento 1 hasta maxIntentos.
        // TODO 5: en cada vuelta incrementa contador[0] (intento realmente ejecutado).
        // TODO 6: intenta accion.get(); si no lanza, devuelve su resultado de inmediato.
        // TODO 7: si lanza, captura la excepcion y guardala como "ultimoFallo".
        // TODO 8: si aun quedan intentos, continua el bucle (aqui iria el backoff).
        // TODO 9: si era el ultimo intento, sal del bucle sin reintentar mas.
        // TODO 10: agotados los intentos, relanza el ultimo fallo como RuntimeException.
        return null;
    }

    /**
     * Transicion de estado de un circuit breaker tras una llamada.
     *
     * <p>Estados validos: "CLOSED", "OPEN", "HALF_OPEN".
     *
     * @param estadoActual estado actual ("CLOSED"/"OPEN"/"HALF_OPEN")
     * @param exito        true si la ultima llamada tuvo exito; false si fallo
     * @param fallos       numero de fallos consecutivos acumulados en CLOSED
     * @param umbral       fallos consecutivos que abren el circuito (&gt; 0)
     * @return el nuevo estado tras aplicar la transicion
     * @throws IllegalArgumentException si estadoActual no es valido o umbral &lt;= 0
     */
    public static String transicion(String estadoActual, boolean exito, int fallos, int umbral) {
        // TODO 1: si umbral <= 0 -> IllegalArgumentException.
        // TODO 2: si estadoActual no es CLOSED/OPEN/HALF_OPEN -> IllegalArgumentException.
        // TODO 3: en CLOSED con exito: el circuito sigue CLOSED.
        // TODO 4: en CLOSED con fallo y fallos >= umbral: pasa a OPEN.
        // TODO 5: en CLOSED con fallo pero fallos < umbral: sigue CLOSED.
        // TODO 6: en OPEN: se asume que toco timeout y pasa a HALF_OPEN (sonda).
        // TODO 7: en HALF_OPEN con exito: el servicio se recupero -> CLOSED.
        // TODO 8: en HALF_OPEN con fallo: el servicio sigue mal -> OPEN.
        // TODO 9: ningun camino debe devolver un estado fuera del conjunto valido.
        // TODO 10: devuelve el nuevo estado calculado.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(transicion("CLOSED", false, 5, 3));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si accion es null -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si maxIntentos < 1 -> IllegalArgumentException.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si contador es null o contador.length != 1 -> IllegalArgumentException.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: bucle desde intento 1 hasta maxIntentos.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: en cada vuelta incrementa contador[0] (intento realmente ejecutado).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: intenta accion.get(); si no lanza, devuelve su resultado de inmediato.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: si lanza, captura la excepcion y guardala como "ultimoFallo".
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: si aun quedan intentos, continua el bucle (aqui iria el backoff).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: si era el ultimo intento, sal del bucle sin reintentar mas.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: agotados los intentos, relanza el ultimo fallo como RuntimeException.
    }

}
