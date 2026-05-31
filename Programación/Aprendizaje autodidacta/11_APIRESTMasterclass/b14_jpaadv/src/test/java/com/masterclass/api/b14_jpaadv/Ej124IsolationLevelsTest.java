package com.masterclass.api.b14_jpaadv;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b14_jpaadv.Ej124IsolationLevels.Fenomeno;
import com.masterclass.api.b14_jpaadv.Ej124IsolationLevels.Nivel;
import static org.junit.jupiter.api.Assertions.*;

class Ej124IsolationLevelsTest {

    @Test
    void niveles() {
        assertTrue(Ej124IsolationLevels.previene(Nivel.READ_UNCOMMITTED).isEmpty());
        assertEquals(java.util.Set.of(Fenomeno.DIRTY_READ),
                Ej124IsolationLevels.previene(Nivel.READ_COMMITTED));
        assertEquals(java.util.Set.of(Fenomeno.DIRTY_READ, Fenomeno.NON_REPEATABLE_READ),
                Ej124IsolationLevels.previene(Nivel.REPEATABLE_READ));
        assertEquals(3, Ej124IsolationLevels.previene(Nivel.SERIALIZABLE).size());
    }

    @Test
    void testRetoExtra01() {
        assertTrue(Ej124IsolationLevels.previeneDirtyRead(Nivel.READ_COMMITTED));
        assertFalse(Ej124IsolationLevels.previeneDirtyRead(Nivel.READ_UNCOMMITTED));
    }

    @Test
    void testRetoExtra02() {
        assertTrue(Ej124IsolationLevels.previeneNonRepeatableRead(Nivel.REPEATABLE_READ));
        assertFalse(Ej124IsolationLevels.previeneNonRepeatableRead(Nivel.READ_COMMITTED));
    }

    @Test
    void testRetoExtra03() {
        assertTrue(Ej124IsolationLevels.previenePhantomRead(Nivel.SERIALIZABLE));
        assertFalse(Ej124IsolationLevels.previenePhantomRead(Nivel.REPEATABLE_READ));
    }

    @Test
    void testRetoExtra04() {
        assertTrue(Ej124IsolationLevels.previeneTodo(Nivel.SERIALIZABLE));
        assertFalse(Ej124IsolationLevels.previeneTodo(Nivel.REPEATABLE_READ));
    }

    @Test
    void testRetoExtra05() {
        assertTrue(Ej124IsolationLevels.esMasEstricto(Nivel.SERIALIZABLE, Nivel.REPEATABLE_READ));
        assertFalse(Ej124IsolationLevels.esMasEstricto(Nivel.READ_COMMITTED, Nivel.SERIALIZABLE));
    }

    @Test
    void testRetoExtra06() {
        assertEquals("READ_COMMITTED", Ej124IsolationLevels.obtenerNombreNivel(Nivel.READ_COMMITTED));
    }

    @Test
    void testRetoExtra07() {
        assertEquals("DIRTY_READ", Ej124IsolationLevels.obtenerNombreFenomeno(Fenomeno.DIRTY_READ));
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej124IsolationLevels.esElMasBajo(Nivel.READ_UNCOMMITTED));
        assertFalse(Ej124IsolationLevels.esElMasBajo(Nivel.READ_COMMITTED));
    }

    @Test
    void testRetoExtra09() {
        assertTrue(Ej124IsolationLevels.esElMasAlto(Nivel.SERIALIZABLE));
        assertFalse(Ej124IsolationLevels.esElMasAlto(Nivel.REPEATABLE_READ));
    }

    @Test
    void testRetoExtra10() {
        assertEquals("ISOLATION_SERIALIZABLE", Ej124IsolationLevels.obtenerIdNivel(Nivel.SERIALIZABLE));
    }

}
