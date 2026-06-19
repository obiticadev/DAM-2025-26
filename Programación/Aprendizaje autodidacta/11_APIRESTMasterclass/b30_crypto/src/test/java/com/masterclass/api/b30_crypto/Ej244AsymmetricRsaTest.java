package com.masterclass.api.b30_crypto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej244AsymmetricRsaTest {

    @Test
    void rsaRoundTrip() {
        assertEquals("hola RSA", Ej244AsymmetricRsa.rsaRoundTrip("hola RSA"));
    }

    @Test
    void tamanoModuloEnBits() {
        assertEquals(2048, Ej244AsymmetricRsa.tamanoModuloEnBits());
    }

    @Test
    void retoExtra01_cifrarPublicaDescifrarPrivada() {
        assertTrue(Ej244AsymmetricRsa.cifrarPublicaDescifrarPrivada());
    }

    @Test
    void retoExtra02_clavePublicaYPrivadaDistintas() {
        assertTrue(Ej244AsymmetricRsa.clavePublicaYPrivadaDistintas());
    }

    @Test
    void retoExtra03_formatoClavePublica() {
        assertEquals("X.509", Ej244AsymmetricRsa.formatoClavePublica());
    }

    @Test
    void retoExtra04_formatoClavePrivada() {
        assertEquals("PKCS#8", Ej244AsymmetricRsa.formatoClavePrivada());
    }

    @Test
    void retoExtra05_algoritmoDeLaClave() {
        assertEquals("RSA", Ej244AsymmetricRsa.algoritmoDeLaClave());
    }

    @Test
    void retoExtra06_rsaNoCifraDatosGrandes() {
        assertTrue(Ej244AsymmetricRsa.rsaNoCifraDatosGrandes());
    }

    @Test
    void retoExtra07_cifradoNoDeterministaPorPadding() {
        assertTrue(Ej244AsymmetricRsa.cifradoNoDeterministaPorPadding());
    }

    @Test
    void retoExtra08_reconstruirPublicaDesdeBytes() {
        assertTrue(Ej244AsymmetricRsa.reconstruirPublicaDesdeBytes());
    }

    @Test
    void retoExtra09_descifrarConOtraPrivadaFalla() {
        assertTrue(Ej244AsymmetricRsa.descifrarConOtraPrivadaFalla());
    }

    @Test
    void retoExtra10_roundTripBytes() {
        assertArrayEquals(new byte[]{7, 7, 7}, Ej244AsymmetricRsa.roundTripBytes(new byte[]{7, 7, 7}));
    }
}
