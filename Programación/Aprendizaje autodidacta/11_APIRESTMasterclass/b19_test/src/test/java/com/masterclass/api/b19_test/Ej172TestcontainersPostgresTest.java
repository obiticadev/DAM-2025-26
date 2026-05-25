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

    @Test
    void testRetoExtra01_obtenerHostJdbc() {
        // Extrae el host de la URL JDBC.
        assertEquals("localhost", Ej172TestcontainersPostgres.obtenerHostJdbc("jdbc:postgresql://localhost:5432/db"));
    }

    @Test
    void testRetoExtra02_obtenerPuertoJdbc() {
        // Extrae el puerto de la URL JDBC.
        assertEquals(5432, Ej172TestcontainersPostgres.obtenerPuertoJdbc("jdbc:postgresql://localhost:5432/db"));
    }

    @Test
    void testRetoExtra03_obtenerBaseDatosJdbc() {
        // Extrae la BD de la URL JDBC.
        assertEquals("db", Ej172TestcontainersPostgres.obtenerBaseDatosJdbc("jdbc:postgresql://localhost:5432/db"));
    }

    @Test
    void testRetoExtra04_esHostLocal() {
        // Comprueba si es host local.
        assertTrue(Ej172TestcontainersPostgres.esHostLocal("localhost"));
    }

    @Test
    void testRetoExtra05_esPuertoValido() {
        // Valida el rango del puerto.
        assertTrue(Ej172TestcontainersPostgres.esPuertoValido(8080));
    }

    @Test
    void testRetoExtra06_extraerTagImagen() {
        // Extrae el tag de una imagen.
        assertEquals("16", Ej172TestcontainersPostgres.extraerTagImagen("postgres:16"));
    }

    @Test
    void testRetoExtra07_esImagenPostgres() {
        // Valida si es imagen Postgres.
        assertTrue(Ej172TestcontainersPostgres.esImagenPostgres("postgres:16"));
    }

    @Test
    void testRetoExtra08_esImagenAlpine() {
        // Comprueba si es variante Alpine.
        assertTrue(Ej172TestcontainersPostgres.esImagenAlpine("postgres:16-alpine"));
    }

    @Test
    void testRetoExtra09_construirUrlMinima() {
        // Construye URL minima sin BD.
        assertEquals("jdbc:postgresql://localhost:5432", Ej172TestcontainersPostgres.construirUrlMinima("localhost", 5432));
    }

    @Test
    void testRetoExtra10_formatearDockerCommand() {
        // Formatea comando de ejecucion docker.
        assertEquals("docker run -d postgres:16", Ej172TestcontainersPostgres.formatearDockerCommand("postgres:16"));
    }

}