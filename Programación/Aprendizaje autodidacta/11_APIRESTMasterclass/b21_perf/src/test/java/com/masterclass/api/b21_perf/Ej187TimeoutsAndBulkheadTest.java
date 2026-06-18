package com.masterclass.api.b21_perf;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej187TimeoutsAndBulkheadTest {

    @Test
    void timeoutCumplidoYExcedido() {
        assertTrue(Ej187TimeoutsAndBulkhead.dentroDeTimeout(0, 50, 100));
        assertTrue(Ej187TimeoutsAndBulkhead.dentroDeTimeout(0, 100, 100));
        assertFalse(Ej187TimeoutsAndBulkhead.dentroDeTimeout(0, 150, 100));
    }

    @Test
    void bulkheadSaturaYRechaza() {
        long[] est = {0};
        assertTrue(Ej187TimeoutsAndBulkhead.adquirir(est, 2));
        assertTrue(Ej187TimeoutsAndBulkhead.adquirir(est, 2));
        assertFalse(Ej187TimeoutsAndBulkhead.adquirir(est, 2));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej187TimeoutsAndBulkhead.dentroDeTimeout(0, 10, 0));
        assertThrows(IllegalArgumentException.class,
                () -> Ej187TimeoutsAndBulkhead.adquirir(new long[1], 0));
    }

    @Test
    void testRetoExtra01_esInicioValido() {
        // Valida inicio.
        assertTrue(Ej187TimeoutsAndBulkhead.esInicioValido(0));
    }

    @Test
    void testRetoExtra02_esDuracionValida() {
        // Valida duracion.
        assertTrue(Ej187TimeoutsAndBulkhead.esDuracionValida(10));
    }

    @Test
    void testRetoExtra03_esTimeoutValido() {
        // Valida timeout.
        assertTrue(Ej187TimeoutsAndBulkhead.esTimeoutValido(100));
    }

    @Test
    void testRetoExtra04_calcularFinMs() {
        // Calcula final de ejecucion.
        assertEquals(150, Ej187TimeoutsAndBulkhead.calcularFinMs(100, 50));
    }

    @Test
    void testRetoExtra05_calcularDeadlineMs() {
        // Calcula instante limite.
        assertEquals(200, Ej187TimeoutsAndBulkhead.calcularDeadlineMs(100, 100));
    }

    @Test
    void testRetoExtra06_esEstadoValido() {
        // Valida bulkhead.
        assertTrue(Ej187TimeoutsAndBulkhead.esEstadoValido(new long[]{0}));
    }

    @Test
    void testRetoExtra07_obtenerPermisosEnUso() {
        // Obtiene en uso.
        assertEquals(2, Ej187TimeoutsAndBulkhead.obtenerPermisosEnUso(new long[]{2}));
    }

    @Test
    void testRetoExtra08_liberarPermiso() {
        // Libera un permiso.
        assertEquals(1, Ej187TimeoutsAndBulkhead.liberarPermiso(new long[]{2})[0]);
    }

    @Test
    void testRetoExtra09_esSaturado() {
        // Comprueba saturacion.
        assertTrue(Ej187TimeoutsAndBulkhead.esSaturado(new long[]{5}, 5));
    }

    @Test
    void testRetoExtra10_inicializarEstado() {
        // Crea estado inicial.
        long perm = 3;
        assertEquals(perm, Ej187TimeoutsAndBulkhead.inicializarEstado(perm)[0]);
    }

}