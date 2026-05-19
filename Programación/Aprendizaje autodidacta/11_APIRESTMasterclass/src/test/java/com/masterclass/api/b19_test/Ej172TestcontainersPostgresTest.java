package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej172TestcontainersPostgresTest {

    @Test
    void construyeUrl() {
        assertEquals("jdbc:postgresql://localhost:54321/test",
                Ej172TestcontainersPostgres.jdbcUrl("localhost", 54321, "test"));
    }

    @Test
    void urlInvalida() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej172TestcontainersPostgres.jdbcUrl("h", 0, "db"));
    }

    @Test
    void imagen() {
        assertTrue(Ej172TestcontainersPostgres.imagenValida("postgres:16-alpine"));
        assertFalse(Ej172TestcontainersPostgres.imagenValida("mysql:8"));
        assertFalse(Ej172TestcontainersPostgres.imagenValida("postgres"));
    }

    @Test
    void imagenNull() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej172TestcontainersPostgres.imagenValida("  "));
    }
}
