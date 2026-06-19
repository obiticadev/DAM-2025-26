package com.masterclass.api.b30_crypto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej247KeyStoreTest {

    @Test
    void guardarYCargarClaveSecreta() {
        assertTrue(Ej247KeyStore.guardarYCargarClaveSecreta());
    }

    @Test
    void numeroDeAliasDelKeystoreRecurso() {
        assertEquals(1, Ej247KeyStore.numeroDeAliasDelKeystoreRecurso());
    }

    @Test
    void retoExtra01_tipoKeyStorePkcs12() {
        assertEquals("PKCS12", Ej247KeyStore.tipoKeyStorePkcs12());
    }

    @Test
    void retoExtra02_cargaConPasswordCorrecta() {
        assertTrue(Ej247KeyStore.cargaConPasswordCorrecta());
    }

    @Test
    void retoExtra03_cargaConPasswordIncorrectaFalla() {
        assertTrue(Ej247KeyStore.cargaConPasswordIncorrectaFalla());
    }

    @Test
    void retoExtra04_contieneAliasServer() {
        assertTrue(Ej247KeyStore.contieneAliasServer());
    }

    @Test
    void retoExtra05_recuperarCertificado() {
        assertTrue(Ej247KeyStore.recuperarCertificado());
    }

    @Test
    void retoExtra06_recuperarClavePrivada() {
        assertTrue(Ej247KeyStore.recuperarClavePrivada());
    }

    @Test
    void retoExtra07_listarAliasContieneServer() {
        assertTrue(Ej247KeyStore.listarAliasContieneServer());
    }

    @Test
    void retoExtra08_guardarCertificadoEnNuevoKeystore() {
        assertTrue(Ej247KeyStore.guardarCertificadoEnNuevoKeystore());
    }

    @Test
    void retoExtra09_eliminarEntradaReduceTamano() {
        assertTrue(Ej247KeyStore.eliminarEntradaReduceTamano());
    }

    @Test
    void retoExtra10_jceksGuardaClaveSecreta() {
        assertTrue(Ej247KeyStore.jceksGuardaClaveSecreta());
    }
}
