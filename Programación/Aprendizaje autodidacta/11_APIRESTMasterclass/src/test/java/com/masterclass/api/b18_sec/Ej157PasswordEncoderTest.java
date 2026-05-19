package com.masterclass.api.b18_sec;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej157PasswordEncoderTest {

    @Test
    void hashYVerificaOk() {
        String h = Ej157PasswordEncoder.hash("supersecreta1");
        assertTrue(h.startsWith("$2"));
        assertTrue(Ej157PasswordEncoder.verifica("supersecreta1", h));
    }

    @Test
    void verificaFallaConOtra() {
        String h = Ej157PasswordEncoder.hash("supersecreta1");
        assertFalse(Ej157PasswordEncoder.verifica("otraClave1", h));
    }

    @Test
    void hashesDistintosPorSalt() {
        assertNotEquals(Ej157PasswordEncoder.hash("supersecreta1"),
                Ej157PasswordEncoder.hash("supersecreta1"));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej157PasswordEncoder.hash("corta"));
        assertThrows(IllegalArgumentException.class,
                () -> Ej157PasswordEncoder.verifica("x", null));
    }
}
