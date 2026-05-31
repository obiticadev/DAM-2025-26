package com.masterclass.api.b21_perf;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej186RetryAndCircuitBreakerTest {

    @Test
    void reintentaHastaExito() {
        int[] c = {0};
        String r = Ej186RetryAndCircuitBreaker.conReintentos(() -> {
            if (c[0] < 2) {
                throw new RuntimeException("fallo");
            }
            return "ok";
        }, 5, c);
        assertEquals("ok", r);
        assertEquals(3, c[0]);
    }

    @Test
    void agotaIntentosYRelanza() {
        assertThrows(RuntimeException.class,
                () -> Ej186RetryAndCircuitBreaker.conReintentos(() -> {
                    throw new RuntimeException("siempre");
                }, 2, new int[1]));
    }

    @Test
    void transicionesCircuitBreaker() {
        assertEquals("OPEN", Ej186RetryAndCircuitBreaker.transicion("CLOSED", false, 3, 3));
        assertEquals("HALF_OPEN", Ej186RetryAndCircuitBreaker.transicion("OPEN", false, 0, 3));
        assertEquals("CLOSED", Ej186RetryAndCircuitBreaker.transicion("HALF_OPEN", true, 0, 3));
        assertEquals("OPEN", Ej186RetryAndCircuitBreaker.transicion("HALF_OPEN", false, 0, 3));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej186RetryAndCircuitBreaker.transicion("RARO", true, 0, 3));
        assertThrows(IllegalArgumentException.class,
                () -> Ej186RetryAndCircuitBreaker.conReintentos(() -> "x", 0, new int[1]));
    }

    @Test
    void testRetoExtra01_esEstadoValido() {
        // Valida estados validos del breaker.
        assertTrue(Ej186RetryAndCircuitBreaker.esEstadoValido("CLOSED"));
    }

    @Test
    void testRetoExtra02_esUmbralValido() {
        // Valida umbral positivo.
        assertTrue(Ej186RetryAndCircuitBreaker.esUmbralValido(3));
    }

    @Test
    void testRetoExtra03_esMaxIntentosValido() {
        // Valida maximo de intentos.
        assertTrue(Ej186RetryAndCircuitBreaker.esMaxIntentosValido(3));
    }

    @Test
    void testRetoExtra04_inicializarContador() {
        // Crea un contador limpio.
        assertEquals(0, Ej186RetryAndCircuitBreaker.inicializarContador()[0]);
    }

    @Test
    void testRetoExtra05_esContadorValido() {
        // Valida tamaño de contador.
        assertTrue(Ej186RetryAndCircuitBreaker.esContadorValido(new int[]{0}));
    }

    @Test
    void testRetoExtra06_incrementarContador() {
        // Incrementa el contador in-place.
        assertEquals(1, Ej186RetryAndCircuitBreaker.incrementarContador(new int[]{0})[0]);
    }

    @Test
    void testRetoExtra07_ejecutarAccionSegura() {
        // Ejecuta retornando fallback ante error.
        assertEquals("fallback", Ej186RetryAndCircuitBreaker.ejecutarAccionSegura(() -> { throw new RuntimeException(); }));
    }

    @Test
    void testRetoExtra08_esEstadoCerrado() {
        // Comprueba CLOSED.
        assertTrue(Ej186RetryAndCircuitBreaker.esEstadoCerrado("CLOSED"));
    }

    @Test
    void testRetoExtra09_esEstadoAbierto() {
        // Comprueba OPEN.
        assertTrue(Ej186RetryAndCircuitBreaker.esEstadoAbierto("OPEN"));
    }

    @Test
    void testRetoExtra10_esEstadoSemiAbierto() {
        // Comprueba HALF_OPEN.
        assertTrue(Ej186RetryAndCircuitBreaker.esEstadoSemiAbierto("HALF_OPEN"));
    }

}