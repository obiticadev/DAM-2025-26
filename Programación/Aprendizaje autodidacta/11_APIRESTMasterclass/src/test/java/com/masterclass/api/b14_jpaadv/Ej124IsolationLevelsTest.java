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
}
