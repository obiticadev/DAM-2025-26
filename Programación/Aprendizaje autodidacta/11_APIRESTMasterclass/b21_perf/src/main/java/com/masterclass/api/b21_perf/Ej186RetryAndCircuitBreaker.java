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

        /**
     * RETO EXTRA 01: Valida estados validos del breaker.
     */
    public static boolean esEstadoValido(String estado) {
        // GUÍA: una línea —
        // return "CLOSED".equals(estado) || "OPEN".equals(estado) || "HALF_OPEN".equals(estado);
        // El test: "CLOSED" → true. Son los 3 estados del breaker (21.4). PISTA:
        // pon el literal primero ("CLOSED".equals(estado)) para que un 'estado'
        // null devuelva false sin NPE. Es el TODO 2 de transicion() como método.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEstadoValido");
    }

    /**
     * RETO EXTRA 02: Valida umbral positivo.
     */
    public static boolean esUmbralValido(int u) {
        // GUÍA: una línea — return u > 0;
        // El test: 3 → true. El umbral de fallos que abre el circuito debe ser
        // estrictamente positivo (con 0 abriría sin un solo fallo). Es el TODO 1
        // de transicion() (21.4).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esUmbralValido");
    }

    /**
     * RETO EXTRA 03: Valida maximo de intentos.
     */
    public static boolean esMaxIntentosValido(int max) {
        // GUÍA: una línea — return max >= 1;
        // El test: 3 → true. CUIDADO con el límite: el mínimo es 1, no 0 (hay que
        // intentar al menos una vez; con 0 intentos no se ejecutaría nada). Es el
        // TODO 2 de conReintentos(), donde maxIntentos < 1 lanza excepción (21.4).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMaxIntentosValido");
    }

    /**
     * RETO EXTRA 04: Crea un contador limpio.
     */
    public static int[] inicializarContador() {
        // GUÍA: una línea — return new int[1];
        // El test exige [0] == 0. Un new int[1] arranca con su único elemento a
        // 0 por defecto (no hace falta inicializarlo a mano). Es el contador de
        // intentos que conReintentos() incrementa; el truco del int[] de un
        // elemento es poder mutarlo "por referencia" dentro de una lambda.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para inicializarContador");
    }

    /**
     * RETO EXTRA 05: Valida tamaño de contador.
     */
    public static boolean esContadorValido(int[] c) {
        // GUÍA: una línea — return c != null && c.length == 1;
        // El test: new int[]{0} → true. El contador SIEMPRE es de un elemento
        // (es una "celda mutable", no un array de datos). Es el TODO 3 de
        // conReintentos(). PISTA: comprueba null ANTES de c.length para no
        // provocar NPE (cortocircuito de &&).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esContadorValido");
    }

    /**
     * RETO EXTRA 06: Incrementa el contador in-place.
     */
    public static int[] incrementarContador(int[] c) {
        // GUÍA: dos pasos — c[0]++; return c;
        // El test: new int[]{0} → [0] == 1. "in-place" significa modificar el
        // MISMO array (no crear otro) y devolverlo. Es justo lo que hace el
        // TODO 5 de conReintentos() en cada vuelta del bucle (21.4).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para incrementarContador");
    }

    /**
     * RETO EXTRA 07: Ejecuta retornando fallback ante error.
     */
    public static String ejecutarAccionSegura(java.util.function.Supplier<String> a) {
        // GUÍA: try/catch —
        //   try { return a.get(); } catch (RuntimeException e) { return "fallback"; }
        // El test pasa un Supplier que lanza y espera EXACTAMENTE "fallback".
        // Es la versión síncrona del exceptionally() de los futures (21.2) y la
        // base del circuit breaker: si la llamada peta, devuelve un valor seguro
        // en vez de propagar el error.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ejecutarAccionSegura");
    }

    /**
     * RETO EXTRA 08: Comprueba CLOSED.
     */
    public static boolean esEstadoCerrado(String estado) {
        // GUÍA: una línea — return "CLOSED".equals(estado);
        // El test: "CLOSED" → true. Literal primero para que null dé false sin
        // NPE. CLOSED = circuito sano, pasan las llamadas (21.4).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEstadoCerrado");
    }

    /**
     * RETO EXTRA 09: Comprueba OPEN.
     */
    public static boolean esEstadoAbierto(String estado) {
        // GUÍA: una línea — return "OPEN".equals(estado);
        // El test: "OPEN" → true. OPEN = circuito cortado, no se llama al
        // servicio caído (21.4). Trío con esEstadoCerrado / esEstadoSemiAbierto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEstadoAbierto");
    }

    /**
     * RETO EXTRA 10: Comprueba HALF_OPEN.
     */
    public static boolean esEstadoSemiAbierto(String estado) {
        // GUÍA: una línea — return "HALF_OPEN".equals(estado);
        // El test: "HALF_OPEN" → true. HALF_OPEN = estado de sonda: deja pasar
        // una llamada de prueba; si va bien → CLOSED, si falla → OPEN (21.4).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEstadoSemiAbierto");
    }

}
