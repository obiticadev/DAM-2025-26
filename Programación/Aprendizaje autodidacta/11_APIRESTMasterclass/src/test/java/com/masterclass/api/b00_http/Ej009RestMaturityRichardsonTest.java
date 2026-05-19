package com.masterclass.api.b00_http;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej009RestMaturityRichardsonTest {

    @Test
    void nivel0() {
        assertEquals(0, Ej009RestMaturityRichardson.level(false, false, false));
    }

    @Test
    void nivel1() {
        assertEquals(1, Ej009RestMaturityRichardson.level(true, false, false));
    }

    @Test
    void nivel2() {
        assertEquals(2, Ej009RestMaturityRichardson.level(true, true, false));
    }

    @Test
    void nivel3() {
        assertEquals(3, Ej009RestMaturityRichardson.level(true, true, true));
    }
}
