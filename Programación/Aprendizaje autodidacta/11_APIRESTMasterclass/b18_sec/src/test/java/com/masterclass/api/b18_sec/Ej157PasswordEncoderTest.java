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

    @Test
    void testRetoExtra01_esFirmaBcryptValida() {
        // Comprueba si un hash tiene la estructura clasica de BCrypt ($2a$...).
        assertTrue(Ej157PasswordEncoder.esFirmaBcryptValida("$2a$10$abcdef"));
    }

    @Test
    void testRetoExtra02_esPasswordFuerte() {
        // Valida requisitos de contrasena fuerte (minimo 8 chars, letras, numeros, especiales).
        assertTrue(Ej157PasswordEncoder.esPasswordFuerte("Pass123!"));
    }

    @Test
    void testRetoExtra03_esFirmaAlgoritmoCorrecto() {
        // Determina si el algoritmo de hash es aceptado.
        assertTrue(Ej157PasswordEncoder.esFirmaAlgoritmoCorrecto("{bcrypt}"));
    }

    @Test
    void testRetoExtra04_compararEnSegundoPlano() {
        // Simula comparacion simulando un pequeno delay para mitigar timing attacks.
        assertTrue(Ej157PasswordEncoder.compararEnSegundoPlano("1", "1"));
    }

    @Test
    void testRetoExtra05_esExcepcionEncoding() {
        // Determina si la excepcion fue por algoritmo de hash corrupto o inexistente.
        assertTrue(Ej157PasswordEncoder.esExcepcionEncoding(new IllegalArgumentException("encoder")));
    }

    @Test
    void testRetoExtra06_crearPrefijoEstandar() {
        // Genera el prefijo Spring Security para el codificador.
        assertEquals("{bcrypt}", Ej157PasswordEncoder.crearPrefijoEstandar("bcrypt"));
    }

    @Test
    void testRetoExtra07_esHashDuplicado() {
        // Verifica si dos hashes corresponden al mismo valor (no deberia por el salt).
        assertFalse(Ej157PasswordEncoder.esHashDuplicado("h1", "h2"));
    }

    @Test
    void testRetoExtra08_determinarFuerzaEncoder() {
        // Extrae la fuerza del hash BCrypt (el costo logaritmico).
        assertEquals(10, Ej157PasswordEncoder.determinarFuerzaEncoder("$2a$10$abcdef"));
    }

    @Test
    void testRetoExtra09_generarSaltManual() {
        // Crea una cadena salt de uso unico.
        assertNotNull(Ej157PasswordEncoder.generarSaltManual());
    }

    @Test
    void testRetoExtra10_esPasswordVacia() {
        // Determina si la contrasena es nula o vacia.
        assertTrue(Ej157PasswordEncoder.esPasswordVacia(null));
    }

}