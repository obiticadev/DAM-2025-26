package com.masterclass.api.b30_crypto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej245DigitalSignatureTest {

    @Test
    void firmarYVerificar() {
        assertTrue(Ej245DigitalSignature.firmarYVerificar("documento importante"));
    }

    @Test
    void verificaFallaSiCambiaMensaje() {
        assertTrue(Ej245DigitalSignature.verificaFallaSiCambiaMensaje("original", "alterado"));
    }

    @Test
    void retoExtra01_firmaConPrivadaVerificaConPublica() {
        assertTrue(Ej245DigitalSignature.firmaConPrivadaVerificaConPublica());
    }

    @Test
    void retoExtra02_verificarConOtraClaveFalla() {
        assertTrue(Ej245DigitalSignature.verificarConOtraClaveFalla());
    }

    @Test
    void retoExtra03_longitudFirmaRsa2048() {
        assertEquals(256, Ej245DigitalSignature.longitudFirmaRsa2048());
    }

    @Test
    void retoExtra04_algoritmoDeFirma() {
        assertEquals("SHA256withRSA", Ej245DigitalSignature.algoritmoDeFirma());
    }

    @Test
    void retoExtra05_firmarYVerificarBytes() {
        assertTrue(Ej245DigitalSignature.firmarYVerificarBytes(new byte[]{1, 2, 3}));
    }

    @Test
    void retoExtra06_manipularFirmaInvalida() {
        assertTrue(Ej245DigitalSignature.manipularFirmaInvalida());
    }

    @Test
    void retoExtra07_firmaBase64NoVacia() {
        assertTrue(Ej245DigitalSignature.firmaBase64NoVacia());
    }

    @Test
    void retoExtra08_ecdsaFirmaYVerifica() {
        assertTrue(Ej245DigitalSignature.ecdsaFirmaYVerifica());
    }

    @Test
    void retoExtra09_firmaRsaEsDeterminista() {
        assertTrue(Ej245DigitalSignature.firmaRsaEsDeterminista());
    }

    @Test
    void retoExtra10_noRepudioSoloDuenoFirma() {
        assertTrue(Ej245DigitalSignature.noRepudioSoloDuenoFirma());
    }
}
