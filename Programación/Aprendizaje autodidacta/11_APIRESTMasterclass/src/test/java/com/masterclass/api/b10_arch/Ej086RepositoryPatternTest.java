package com.masterclass.api.b10_arch;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b10_arch.Ej086RepositoryPattern.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej086RepositoryPatternTest {

    @Test
    void escenarioReutilizable() {
        assertEquals(1, Ej086RepositoryPattern.escenario(new LibroRepositoryMem()));
    }

    @Test
    void crudDirecto() {
        var r = new LibroRepositoryMem();
        r.save(new Libro(1L, "DDD"));
        assertEquals("DDD", r.findById(1L).orElseThrow().titulo());
        assertEquals(1, r.count());
        assertTrue(r.deleteById(1L));
        assertEquals(0, r.count());
    }
}
