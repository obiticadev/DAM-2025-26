package com.masterclass.api.b21_perf;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej185RateLimitingTest {

    @Test
    void consumeHastaAgotar() {
        long[] est = {2, 0};
        assertTrue(Ej185RateLimiting.permitido(est, 5, 1000, 0));
        assertTrue(Ej185RateLimiting.permitido(est, 5, 1000, 0));
        assertFalse(Ej185RateLimiting.permitido(est, 5, 1000, 0));
    }

    @Test
    void recargaConElTiempo() {
        long[] est = {0, 0};
        assertFalse(Ej185RateLimiting.permitido(est, 5, 1000, 0));
        assertTrue(Ej185RateLimiting.permitido(est, 5, 1000, 1000));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej185RateLimiting.permitido(new long[1], 5, 1000, 0));
        assertThrows(IllegalArgumentException.class,
                () -> Ej185RateLimiting.permitido(new long[]{1, 100}, 5, 1000, 0));
    }

    @Test
    void testRetoExtra01_esEstadoValido() {
        // Valida tamaño del estado.
        assertTrue(Ej185RateLimiting.esEstadoValido(new long[]{2, 0}));
    }

    @Test
    void testRetoExtra02_obtenerTokens() {
        // Obtiene tokens disponibles.
        assertEquals(2, Ej185RateLimiting.obtenerTokens(new long[]{2, 0}));
    }

    @Test
    void testRetoExtra03_obtenerUltimoInstante() {
        // Obtiene ultimo instante de recarga.
        assertEquals(100, Ej185RateLimiting.obtenerUltimoInstante(new long[]{2, 100}));
    }

    @Test
    void testRetoExtra04_calcularTranscurrido() {
        // Diferencia de tiempo en ms.
        assertEquals(50, Ej185RateLimiting.calcularTranscurrido(100, 150));
    }

    @Test
    void testRetoExtra05_calcularTokensARecargar() {
        // Calcula tokens teóricos.
        assertEquals(2, Ej185RateLimiting.calcularTokensARecargar(20, 10));
    }

    @Test
    void testRetoExtra06_sumarTokensLimitado() {
        // Suma limitando a capacidad.
        assertEquals(5, Ej185RateLimiting.sumarTokensLimitado(3, 3, 5));
    }

    @Test
    void testRetoExtra07_consumirToken() {
        // Resta un token si es posible.
        assertEquals(1, Ej185RateLimiting.consumirToken(2));
    }

    @Test
    void testRetoExtra08_esPuertoValido() {
        // Valida rango del puerto tcp.
        assertTrue(Ej185RateLimiting.esPuertoValido(8080));
    }

    @Test
    void testRetoExtra09_esRelojValido() {
        // Valida consistencia del reloj.
        assertTrue(Ej185RateLimiting.esRelojValido(100, 150));
    }

    @Test
    void testRetoExtra10_inicializarEstado() {
        // Crea array de estado inicializado.
        long tok = 2, inst = 0;
        assertEquals(tok, Ej185RateLimiting.inicializarEstado(tok, inst)[0]);
    }

}