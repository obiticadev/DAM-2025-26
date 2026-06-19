package com.masterclass.api.b30_crypto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 15, unit = TimeUnit.SECONDS)
class Ej248TlsSecureChannelTest {

    @Test
    void ecoTlsRoundTrip() {
        assertEquals("hola TLS", Ej248TlsSecureChannel.ecoTlsRoundTrip("hola TLS"));
    }

    @Test
    void soportaTls13() {
        assertTrue(Ej248TlsSecureChannel.soportaTls13());
    }

    @Test
    void retoExtra01_handshakeNegociaTls() {
        assertTrue(Ej248TlsSecureChannel.handshakeNegociaTls());
    }

    @Test
    void retoExtra02_servidorPresentaCertificado() {
        assertTrue(Ej248TlsSecureChannel.servidorPresentaCertificado());
    }

    @Test
    void retoExtra03_cipherSuiteNegociadaNoVacia() {
        assertTrue(Ej248TlsSecureChannel.cipherSuiteNegociadaNoVacia());
    }

    @Test
    void retoExtra04_clienteSinTruststoreRechaza() {
        assertTrue(Ej248TlsSecureChannel.clienteSinTruststoreRechaza());
    }

    @Test
    void retoExtra05_ecoTlsUtf8() {
        assertEquals("ñandú café", Ej248TlsSecureChannel.ecoTlsUtf8("ñandú café"));
    }

    @Test
    void retoExtra06_suitesSoportadasNoVacias() {
        assertTrue(Ej248TlsSecureChannel.suitesSoportadasNoVacias());
    }

    @Test
    void retoExtra07_sslContextTlsDisponible() {
        assertTrue(Ej248TlsSecureChannel.sslContextTlsDisponible());
    }

    @Test
    void retoExtra08_keyManagerDesdeKeystore() {
        assertTrue(Ej248TlsSecureChannel.keyManagerDesdeKeystore());
    }

    @Test
    void retoExtra09_trustManagerDesdeTruststore() {
        assertTrue(Ej248TlsSecureChannel.trustManagerDesdeTruststore());
    }

    @Test
    void retoExtra10_tlsRoundTripBytes() {
        assertArrayEquals(new byte[]{9, 8, 7}, Ej248TlsSecureChannel.tlsRoundTripBytes(new byte[]{9, 8, 7}));
    }
}
