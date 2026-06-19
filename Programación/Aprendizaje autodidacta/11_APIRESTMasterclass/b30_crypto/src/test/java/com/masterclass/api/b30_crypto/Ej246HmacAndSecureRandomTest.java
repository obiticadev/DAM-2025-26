package com.masterclass.api.b30_crypto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej246HmacAndSecureRandomTest {

    private static final String HMAC_CONOCIDO =
            "fd7e5bf1373edd1a44fdd38bba0e4bcc6eb5ca7931dbe8c3e4abcfa71aa0d00f";

    @Test
    void hmacSha256Hex() {
        assertEquals(HMAC_CONOCIDO, Ej246HmacAndSecureRandom.hmacSha256Hex("mensaje", "clave-secreta"));
    }

    @Test
    void hmacVerifica() {
        assertTrue(Ej246HmacAndSecureRandom.hmacVerifica("mensaje", "clave-secreta", HMAC_CONOCIDO));
        assertFalse(Ej246HmacAndSecureRandom.hmacVerifica("otro", "clave-secreta", HMAC_CONOCIDO));
    }

    @Test
    void retoExtra01_mismaClaveMismoHmac() {
        assertTrue(Ej246HmacAndSecureRandom.mismaClaveMismoHmac());
    }

    @Test
    void retoExtra02_distintaClaveDistintoHmac() {
        assertTrue(Ej246HmacAndSecureRandom.distintaClaveDistintoHmac());
    }

    @Test
    void retoExtra03_secureRandomGenera16Bytes() {
        assertEquals(16, Ej246HmacAndSecureRandom.secureRandomGenera16Bytes());
    }

    @Test
    void retoExtra04_dosTokensDistintos() {
        assertTrue(Ej246HmacAndSecureRandom.dosTokensDistintos());
    }

    @Test
    void retoExtra05_tokenBase64UrlSinPadding() {
        assertTrue(Ej246HmacAndSecureRandom.tokenBase64UrlSinPadding());
    }

    @Test
    void retoExtra06_hmacDetectaManipulacion() {
        assertTrue(Ej246HmacAndSecureRandom.hmacDetectaManipulacion());
    }

    @Test
    void retoExtra07_comparacionEnTiempoConstante() {
        assertTrue(Ej246HmacAndSecureRandom.comparacionEnTiempoConstante());
    }

    @Test
    void retoExtra08_hmacSha256Tiene32Bytes() {
        assertEquals(32, Ej246HmacAndSecureRandom.hmacSha256Tiene32Bytes());
    }

    @Test
    void retoExtra09_noncesUnicosEnLote() {
        assertTrue(Ej246HmacAndSecureRandom.noncesUnicosEnLote());
    }

    @Test
    void retoExtra10_secureRandomStrongDisponible() {
        assertTrue(Ej246HmacAndSecureRandom.secureRandomStrongDisponible());
    }
}
