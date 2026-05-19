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
}
