package com.masterclass.api.b30_crypto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej243SymmetricAesTest {

    @Test
    void aesGcmRoundTrip() {
        assertEquals("mensaje secreto", Ej243SymmetricAes.aesGcmRoundTrip("mensaje secreto"));
    }

    @Test
    void aesCbcRoundTrip() {
        assertEquals("mensaje secreto", Ej243SymmetricAes.aesCbcRoundTrip("mensaje secreto"));
    }

    @Test
    void retoExtra01_claveAes256TieneTreintaYDosBytes() {
        assertEquals(32, Ej243SymmetricAes.claveAes256TieneTreintaYDosBytes());
    }

    @Test
    void retoExtra02_dosIvAleatoriosDistintos() {
        assertTrue(Ej243SymmetricAes.dosIvAleatoriosDistintos());
    }

    @Test
    void retoExtra03_mismoTextoIvDistintoCifradoDistinto() {
        assertTrue(Ej243SymmetricAes.mismoTextoIvDistintoCifradoDistinto());
    }

    @Test
    void retoExtra04_ecbRevelaPatrones() {
        assertTrue(Ej243SymmetricAes.ecbRevelaPatrones());
    }

    @Test
    void retoExtra05_gcmDetectaManipulacion() {
        assertTrue(Ej243SymmetricAes.gcmDetectaManipulacion());
    }

    @Test
    void retoExtra06_cifrarDescifrarBytes() {
        assertArrayEquals(new byte[]{1, 2, 3, 4, 5}, Ej243SymmetricAes.cifrarDescifrarBytes(new byte[]{1, 2, 3, 4, 5}));
    }

    @Test
    void retoExtra07_claveIncorrectaNoDescifra() {
        assertTrue(Ej243SymmetricAes.claveIncorrectaNoDescifra());
    }

    @Test
    void retoExtra08_claveAes128TieneDieciseisBytes() {
        assertEquals(16, Ej243SymmetricAes.claveAes128TieneDieciseisBytes());
    }

    @Test
    void retoExtra09_cifradoComoBase64RoundTrip() {
        assertTrue(Ej243SymmetricAes.cifradoComoBase64RoundTrip());
    }

    @Test
    void retoExtra10_cbcDeterministaConClaveEIvFijos() {
        assertTrue(Ej243SymmetricAes.cbcDeterministaConClaveEIvFijos());
    }
}
