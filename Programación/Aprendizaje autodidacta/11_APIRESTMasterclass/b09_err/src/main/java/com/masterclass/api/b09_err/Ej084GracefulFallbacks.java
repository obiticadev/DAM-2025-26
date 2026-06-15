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
        // GUÍA: ¿este status obliga a activar el circuito de contingencia?
        // 1. Los 5xx (fallo del servidor/dependencia) son los críticos.
        //    PISTA: return status >= 500 && status <= 599;
        // OJO: el test pasa 503 (Service Unavailable) → true. Un 404 NO es
        // crítico (es el cliente). Reutiliza la idea de esErrorServidor (Ej078).
        // CULTURA: 503 es la señal típica de "dependencia caída" que dispara el
        // fallback a caché (9.8).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esErrorCritico");
    }

    /**
     * RETO EXTRA 02: Genera el payload de contingencia limpia.
     */
    public static String crearRespuestaFallback(String backupData) {
        // GUÍA: envuelve los datos de respaldo en un payload de contingencia.
        // PISTA: return "{\"fallback\":true,\"data\":\"" + backupData + "\"}";
        // OJO: el test solo exige que el resultado .contains("data") cuando
        // backupData="data"; el formato es libre. (Defensa: backupData null → "".)
        // Este es el cuerpo que devuelves en vez de un 500 cuando degradas (9.8).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearRespuestaFallback");
    }

    /**
     * RETO EXTRA 03: Determina si el Circuit Breaker esta en estado OPEN.
     */
    public static boolean esCircuitoAbierto(String state) {
        // GUÍA: una línea — return "OPEN".equals(state);
        // OJO: el test pasa "OPEN" → true. Pon el literal a la IZQUIERDA del
        // equals ("OPEN".equals(state)) para que un state null devuelva false sin
        // NPE, en vez de state.equals("OPEN").
        // CULTURA: un Circuit Breaker (Resilience4j) tiene 3 estados —
        // CLOSED (todo va), OPEN (corta y usa fallback), HALF_OPEN (prueba si se
        // recuperó). OPEN es el que activa la degradación de 9.8.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCircuitoAbierto");
    }

    /**
     * RETO EXTRA 04: Verifica que el retardo de recuperacion sea razonable.
     */
    public static boolean tiempoEsperaSeguro(long ms) {
        // GUÍA: el retardo debe ser positivo y no excesivo (techo razonable).
        // PISTA: return ms > 0 && ms <= 30000;   // entre 1 ms y 30 s
        // OJO: el test pasa 5000L → true. Elige un tope que deje pasar 5000 pero
        // rechace esperas absurdas (un retardo gigante colgaría al cliente).
        // Conecta con calcularBackoffExponencial (reto 9): ese tope evita que el
        // backoff crezca sin límite.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tiempoEsperaSeguro");
    }

    /**
     * RETO EXTRA 05: Determina si proviene del subsistema RestClient/WebClient.
     */
    public static boolean esFalloServicioExterno(Throwable t) {
        // GUÍA: detecta un fallo de servicio externo por su mensaje (timeout).
        // 1. null o sin mensaje → false.
        // 2. return t != null && t.getMessage() != null
        //        && t.getMessage().toLowerCase().contains("timeout");
        // OJO: el test pasa new RuntimeException("timeout") → true. El criterio es
        // por mensaje (no por tipo): un timeout llamando a otra API es el caso
        // canónico que dispara el fallback (9.8).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFalloServicioExterno");
    }

    /**
     * RETO EXTRA 06: Determina si la peticion es de lectura (GET) y segura para reintentar.
     */
    public static boolean esErrorHttpSoportado(String method) {
        // GUÍA: solo es seguro reintentar los métodos idempotentes; el caso del
        // test es GET.
        // PISTA: return "GET".equalsIgnoreCase(method);
        // OJO: el test pasa "GET" → true. Usa equalsIgnoreCase (los métodos HTTP
        // van en mayúsculas, pero tolera "get").
        // CUIDADO conceptual: NO reintentes un POST sin más — podrías crear el
        // recurso dos veces. GET/PUT/DELETE son idempotentes (lo viste en b00);
        // POST no. Por eso el reintento seguro se limita a lecturas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esErrorHttpSoportado");
    }

    /**
     * RETO EXTRA 07: Genera la notificacion de degradacion de servicio.
     */
    public static String crearMensajeDegradado(String originalMsg) {
        // GUÍA: una línea — return "Degradado: " + originalMsg;
        // OJO: el test pasa "original" y espera EXACTAMENTE "Degradado: original":
        // prefijo "Degradado" + ": " (dos puntos + espacio). Mismo patrón que
        // combinarDetalles (Ej078 reto 8).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearMensajeDegradado");
    }

    /**
     * RETO EXTRA 08: Determina si la cache de contingencia tambien cayo.
     */
    public static boolean esFalloPersistenciaContingencia(Throwable t) {
        // GUÍA: detecta que la caché/almacén de contingencia también falló.
        // 1. null → false.
        // 2. La marca de un fallo de E/S es java.io.IOException:
        //       return t instanceof java.io.IOException;
        // OJO: el test manda new java.io.IOException() → true.
        // CULTURA: es el peor caso — falló la dependencia Y falló el fallback.
        // Ahí sí no queda más que un 500 honesto (con su traceId, 9.6).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFalloPersistenciaContingencia");
    }

    /**
     * RETO EXTRA 09: Calcula retraso exponencial con tope maximo.
     */
    public static long calcularBackoffExponencial(int intento, long baseMs) {
        // GUÍA: backoff exponencial = baseMs * 2^intento.
        // PISTA: return baseMs * (long) Math.pow(2, intento);
        // OJO: el test pasa (intento=2, baseMs=100) y espera 400L:
        //    100 * 2^2 = 100 * 4 = 400. Comprueba la cuenta con esos números.
        // Math.pow devuelve double → castea a long. (Mejora real: añadir un tope
        // con Math.min(..., MAX) — el "tope máximo" del enunciado — pero el test
        // no lo fuerza para estos valores.)
        // CULTURA: esperar 2x más en cada reintento evita martillear un servicio
        // ya caído; es el algoritmo estándar de reintentos (con "jitter" aleatorio
        // en producción).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularBackoffExponencial");
    }

    /**
     * RETO EXTRA 10: Resuelve la accion correctiva (RETRY, CACHE, ERROR).
     */
    public static String determinarEstrategiaFallback(int code) {
        // GUÍA: elige la estrategia según el código HTTP.
        // 1. Mapea por familia/código. El test exige 503 → "CACHE".
        //    PISTA (switch de patrones o ifs):
        //       if (code == 503 || code == 500) return "CACHE";  // servidor caído → fallback
        //       if (code == 429 || code == 408) return "RETRY";  // throttle/timeout → reintenta
        //       return "ERROR";                                  // resto → error directo
        // OJO: el test solo fija determinarEstrategiaFallback(503) == "CACHE";
        // las demás ramas son diseño tuyo, pero 503 DEBE devolver "CACHE".
        // CULTURA: une todo el bloque — el código manda la reacción: reintentar,
        // degradar a caché (9.8) o devolver el error estructurado (9.2).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para determinarEstrategiaFallback");
    }

}