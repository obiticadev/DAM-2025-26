package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b01_java.Ej018SealedPatternMatching.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej018SealedPatternMatchingTest {

    @Test
    void aHttpStatus() {
        assertEquals(200, Ej018SealedPatternMatching.aHttpStatus(new Ok("v")));
        assertEquals(404, Ej018SealedPatternMatching.aHttpStatus(new NotFound(7)));
        assertEquals(500, Ej018SealedPatternMatching.aHttpStatus(new Fallo("x")));
    }

    @Test
    void describir() {
        assertTrue(Ej018SealedPatternMatching.describir(new Ok("v")).toLowerCase().contains("v"));
        assertNotNull(Ej018SealedPatternMatching.describir(new Fallo("boom")));
    }
}
