package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej168WebMvcTestTest {

    @Test
    void ok() {
        Respuesta168 r = Ej168WebMvcTest.handle("GET", "/saludo", "Ada");
        assertEquals(200, r.status);
        assertTrue(r.body.contains("Hola, Ada"));
    }

    @Test
    void faltaNombre() {
        assertEquals(400, Ej168WebMvcTest.handle("GET", "/saludo", "  ").status);
    }

    @Test
    void rutaDesconocida() {
        assertEquals(404, Ej168WebMvcTest.handle("GET", "/otra", "Ada").status);
    }

    @Test
    void metodoNoPermitido() {
        assertEquals(405, Ej168WebMvcTest.handle("POST", "/saludo", "Ada").status);
    }

    @Test
    void nulls() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej168WebMvcTest.handle(null, "/saludo", "Ada"));
    }
}
