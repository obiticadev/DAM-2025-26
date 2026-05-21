package com.masterclass.api.b00_http;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej004HttpMethodsSemanticsTest {

    @Test
    void seguridad() {
        assertTrue(Ej004HttpMethodsSemantics.isSafe("GET"));
        assertTrue(Ej004HttpMethodsSemantics.isSafe("get"));
        assertFalse(Ej004HttpMethodsSemantics.isSafe("POST"));
        assertFalse(Ej004HttpMethodsSemantics.isSafe("DELETE"));
    }

    @Test
    void idempotencia() {
        assertTrue(Ej004HttpMethodsSemantics.isIdempotent("PUT"));
        assertTrue(Ej004HttpMethodsSemantics.isIdempotent("DELETE"));
        assertFalse(Ej004HttpMethodsSemantics.isIdempotent("POST"));
        assertFalse(Ej004HttpMethodsSemantics.isIdempotent("PATCH"));
    }

    @Test
    void verboInvalido() {
        assertThrows(IllegalArgumentException.class, () -> Ej004HttpMethodsSemantics.isSafe("FOO"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_esMetodoSeguroWeb() {
        assertTrue(Ej004HttpMethodsSemantics.esMetodoSeguroWeb("GET"));
        assertTrue(Ej004HttpMethodsSemantics.esMetodoSeguroWeb("HEAD"));
        assertTrue(Ej004HttpMethodsSemantics.esMetodoSeguroWeb("OPTIONS"));
        assertTrue(Ej004HttpMethodsSemantics.esMetodoSeguroWeb("TRACE"));
        assertFalse(Ej004HttpMethodsSemantics.esMetodoSeguroWeb("POST"));
        assertThrows(IllegalArgumentException.class, () -> Ej004HttpMethodsSemantics.esMetodoSeguroWeb(null));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_esMetodoIdempotenteWeb() {
        assertTrue(Ej004HttpMethodsSemantics.esMetodoIdempotenteWeb("GET"));
        assertTrue(Ej004HttpMethodsSemantics.esMetodoIdempotenteWeb("PUT"));
        assertTrue(Ej004HttpMethodsSemantics.esMetodoIdempotenteWeb("OPTIONS"));
        assertFalse(Ej004HttpMethodsSemantics.esMetodoIdempotenteWeb("POST"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_admiteCuerpoDePeticion() {
        assertTrue(Ej004HttpMethodsSemantics.admiteCuerpoDePeticion("POST"));
        assertTrue(Ej004HttpMethodsSemantics.admiteCuerpoDePeticion("PUT"));
        assertFalse(Ej004HttpMethodsSemantics.admiteCuerpoDePeticion("GET"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_esCacheablePorDefecto() {
        assertTrue(Ej004HttpMethodsSemantics.esCacheablePorDefecto("GET"));
        assertFalse(Ej004HttpMethodsSemantics.esCacheablePorDefecto("POST"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_validarCambioEstadoPermitido() {
        assertTrue(Ej004HttpMethodsSemantics.validarCambioEstadoPermitido("GET", true));
        assertFalse(Ej004HttpMethodsSemantics.validarCambioEstadoPermitido("POST", true));
        assertTrue(Ej004HttpMethodsSemantics.validarCambioEstadoPermitido("POST", false));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_normalizarMetodoExtremo() {
        assertEquals("GET", Ej004HttpMethodsSemantics.normalizarMetodoExtremo("  get  "));
        assertThrows(IllegalArgumentException.class, () -> Ej004HttpMethodsSemantics.normalizarMetodoExtremo(null));
        assertThrows(IllegalArgumentException.class, () -> Ej004HttpMethodsSemantics.normalizarMetodoExtremo("GET1"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_esMetodoWebDav() {
        assertTrue(Ej004HttpMethodsSemantics.esMetodoWebDav("PROPFIND"));
        assertFalse(Ej004HttpMethodsSemantics.esMetodoWebDav("GET"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_determinarCodigoExitoPorDefecto() {
        assertEquals(201, Ej004HttpMethodsSemantics.determinarCodigoExitoPorDefecto("POST"));
        assertEquals(204, Ej004HttpMethodsSemantics.determinarCodigoExitoPorDefecto("DELETE"));
        assertEquals(200, Ej004HttpMethodsSemantics.determinarCodigoExitoPorDefecto("GET"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_permiteReintentoAutomatico() {
        assertTrue(Ej004HttpMethodsSemantics.permiteReintentoAutomatico("GET"));
        assertTrue(Ej004HttpMethodsSemantics.permiteReintentoAutomatico("PUT"));
        assertFalse(Ej004HttpMethodsSemantics.permiteReintentoAutomatico("POST"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_requiereUriEspecificaRecurso() {
        assertTrue(Ej004HttpMethodsSemantics.requiereUriEspecificaRecurso("DELETE"));
        assertFalse(Ej004HttpMethodsSemantics.requiereUriEspecificaRecurso("GET"));
        assertFalse(Ej004HttpMethodsSemantics.requiereUriEspecificaRecurso("POST"));
    }
}
