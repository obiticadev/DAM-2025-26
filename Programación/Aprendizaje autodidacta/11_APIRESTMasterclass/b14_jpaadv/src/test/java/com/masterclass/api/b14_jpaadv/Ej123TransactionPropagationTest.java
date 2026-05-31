package com.masterclass.api.b14_jpaadv;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b14_jpaadv.Ej123TransactionPropagation.Propagacion;
import static org.junit.jupiter.api.Assertions.*;

class Ej123TransactionPropagationTest {

    @Test
    void requiredReusaOCrea() {
        assertEquals(7, Ej123TransactionPropagation.txEfectiva(7, Propagacion.REQUIRED, 8));
        assertEquals(8, Ej123TransactionPropagation.txEfectiva(0, Propagacion.REQUIRED, 8));
    }

    @Test
    void requiresNewSiempreCrea() {
        assertEquals(8, Ej123TransactionPropagation.txEfectiva(7, Propagacion.REQUIRES_NEW, 8));
    }

    @Test
    void mandatory() {
        assertEquals(7, Ej123TransactionPropagation.txEfectiva(7, Propagacion.MANDATORY, 8));
        assertThrows(IllegalStateException.class,
                () -> Ej123TransactionPropagation.txEfectiva(0, Propagacion.MANDATORY, 8));
    }

    @Test
    void neverYSupports() {
        assertEquals(0, Ej123TransactionPropagation.txEfectiva(0, Propagacion.NEVER, 8));
        assertThrows(IllegalStateException.class,
                () -> Ej123TransactionPropagation.txEfectiva(7, Propagacion.NEVER, 8));
        assertEquals(7, Ej123TransactionPropagation.txEfectiva(7, Propagacion.SUPPORTS, 8));
        assertEquals(0, Ej123TransactionPropagation.txEfectiva(0, Propagacion.SUPPORTS, 8));
    }

    @Test
    void testRetoExtra01() {
        assertTrue(Ej123TransactionPropagation.requiereNueva(Propagacion.REQUIRES_NEW));
        assertFalse(Ej123TransactionPropagation.requiereNueva(Propagacion.REQUIRED));
    }

    @Test
    void testRetoExtra02() {
        assertTrue(Ej123TransactionPropagation.exigeTransaccion(Propagacion.MANDATORY));
        assertFalse(Ej123TransactionPropagation.exigeTransaccion(Propagacion.NEVER));
    }

    @Test
    void testRetoExtra03() {
        assertTrue(Ej123TransactionPropagation.prohibeTransaccion(Propagacion.NEVER));
        assertFalse(Ej123TransactionPropagation.prohibeTransaccion(Propagacion.REQUIRED));
    }

    @Test
    void testRetoExtra04() {
        assertTrue(Ej123TransactionPropagation.soportaTransaccion(Propagacion.SUPPORTS));
        assertFalse(Ej123TransactionPropagation.soportaTransaccion(Propagacion.REQUIRES_NEW));
    }

    @Test
    void testRetoExtra05() {
        assertTrue(Ej123TransactionPropagation.esRequerida(Propagacion.REQUIRED));
        assertFalse(Ej123TransactionPropagation.esRequerida(Propagacion.SUPPORTS));
    }

    @Test
    void testRetoExtra06() {
        assertTrue(Ej123TransactionPropagation.validarEstado(1, Propagacion.REQUIRED));
        assertFalse(Ej123TransactionPropagation.validarEstado(0, Propagacion.MANDATORY));
    }

    @Test
    void testRetoExtra07() {
        assertEquals("REQUIRED", Ej123TransactionPropagation.obtenerNombre(Propagacion.REQUIRED));
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej123TransactionPropagation.provocaraRollback(Propagacion.REQUIRED));
        assertFalse(Ej123TransactionPropagation.provocaraRollback(Propagacion.NEVER));
    }

    @Test
    void testRetoExtra09() {
        assertTrue(Ej123TransactionPropagation.esSegura(Propagacion.REQUIRED));
    }

    @Test
    void testRetoExtra10() {
        assertEquals("PROP_REQUIRED", Ej123TransactionPropagation.obtenerIdSimulado(Propagacion.REQUIRED));
    }

}
