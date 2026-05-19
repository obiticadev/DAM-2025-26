package com.masterclass.api.b07_dto;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b07_dto.Ej065MapStructIntro.Origen;
import static org.junit.jupiter.api.Assertions.*;

class Ej065MapStructIntroTest {

    @Test
    void mapeoBase() {
        var d = Ej065MapStructIntro.mapper().apply(new Origen("x", 5));
        assertEquals("x", d.a());
        assertEquals(10, d.bDoble());
    }

    @Test
    void mapeoConPostProceso() {
        var d = Ej065MapStructIntro.mapperMayus().apply(new Origen("ab", 3));
        assertEquals("AB", d.a());
        assertEquals(6, d.bDoble());
    }
}
