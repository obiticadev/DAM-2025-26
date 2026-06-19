package com.masterclass.api.b30_crypto;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class Ej241HashingTest {

    private static final String SHA256_ABC =
            "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad";
    private static final String SHA256_VACIO =
            "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

    @Test
    void sha256Hex() {
        assertEquals(SHA256_ABC, Ej241Hashing.sha256Hex("abc"));
        assertEquals(SHA256_VACIO, Ej241Hashing.sha256Hex(""));
    }

    @Test
    void verificarIntegridad() {
        assertTrue(Ej241Hashing.verificarIntegridad("abc".getBytes(StandardCharsets.UTF_8), SHA256_ABC));
        assertFalse(Ej241Hashing.verificarIntegridad("abd".getBytes(StandardCharsets.UTF_8), SHA256_ABC));
    }

    @Test
    void retoExtra01_sha256TieneTreintaYDosBytes() {
        assertEquals(32, Ej241Hashing.sha256TieneTreintaYDosBytes());
    }

    @Test
    void retoExtra02_efectoAvalancha() {
        assertTrue(Ej241Hashing.efectoAvalancha());
    }

    @Test
    void retoExtra03_md5Hex() {
        assertEquals("900150983cd24fb0d6963f7d28e17f72", Ej241Hashing.md5Hex("abc"));
    }

    @Test
    void retoExtra04_sha1Hex() {
        assertEquals("a9993e364706816aba3e25717850c26c9cd0d89d", Ej241Hashing.sha1Hex("abc"));
    }

    @Test
    void retoExtra05_mismaEntradaMismoHash() {
        assertTrue(Ej241Hashing.mismaEntradaMismoHash());
    }

    @Test
    void retoExtra06_ficherosIgualesMismoHash() {
        assertTrue(Ej241Hashing.ficherosIgualesMismoHash());
    }

    @Test
    void retoExtra07_longitudHexSha256() {
        assertEquals(64, Ej241Hashing.longitudHexSha256());
    }

    @Test
    void retoExtra08_hashCadenaVacia() {
        assertEquals(SHA256_VACIO, Ej241Hashing.hashCadenaVacia());
    }

    @Test
    void retoExtra09_digestIncrementalEquivale() {
        assertTrue(Ej241Hashing.digestIncrementalEquivale());
    }

    @Test
    void retoExtra10_compararEnTiempoConstante() {
        assertTrue(Ej241Hashing.compararEnTiempoConstante());
    }
}
