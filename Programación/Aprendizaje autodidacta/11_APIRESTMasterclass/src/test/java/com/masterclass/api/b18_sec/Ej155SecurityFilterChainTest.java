package com.masterclass.api.b18_sec;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej155SecurityFilterChainTest {

    @Test
    void rutasPublicas() {
        assertTrue(Ej155SecurityFilterChain.esPublica("GET", "/public/health"));
        assertTrue(Ej155SecurityFilterChain.esPublica("POST", "/auth/login"));
        assertTrue(Ej155SecurityFilterChain.esPublica("POST", "/auth/refresh"));
    }

    @Test
    void rutasPrivadas() {
        assertFalse(Ej155SecurityFilterChain.esPublica("GET", "/api/users"));
        assertFalse(Ej155SecurityFilterChain.esPublica("POST", "/public/health"));
    }

    @Test
    void status401() {
        assertTrue(Ej155SecurityFilterChain.requiere401("GET", "/api/users", false));
        assertFalse(Ej155SecurityFilterChain.requiere401("GET", "/api/users", true));
        assertFalse(Ej155SecurityFilterChain.requiere401("GET", "/public/x", false));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej155SecurityFilterChain.esPublica(null, "/x"));
        assertThrows(IllegalArgumentException.class,
                () -> Ej155SecurityFilterChain.esPublica("GET", " "));
    }
}
