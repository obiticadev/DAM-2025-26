package com.masterclass.api.b00_http;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej009RestMaturityRichardsonTest {

    @Test
    void nivel0() {
        assertEquals(0, Ej009RestMaturityRichardson.level(false, false, false));
    }

    @Test
    void nivel1() {
        assertEquals(1, Ej009RestMaturityRichardson.level(true, false, false));
    }

    @Test
    void nivel2() {
        assertEquals(2, Ej009RestMaturityRichardson.level(true, true, false));
    }

    @Test
    void nivel3() {
        assertEquals(3, Ej009RestMaturityRichardson.level(true, true, true));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_evaluarNivelPorEjemplo() {
        assertEquals(0, Ej009RestMaturityRichardson.evaluarNivelPorEjemplo("/soap/customerService", "POST", "<xml>...</xml>"));
        assertEquals(3, Ej009RestMaturityRichardson.evaluarNivelPorEjemplo("/usuarios/12", "GET", "{\"_links\": {}}"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_esDisenoRpc() {
        assertTrue(Ej009RestMaturityRichardson.esDisenoRpc("/crearUsuario", "POST"));
        assertFalse(Ej009RestMaturityRichardson.esDisenoRpc("/usuarios/42", "GET"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_contieneHipermediaHateoas() {
        assertTrue(Ej009RestMaturityRichardson.contieneHipermediaHateoas("{\"_links\": {\"self\": {\"href\": \"/x\"}}}"));
        assertFalse(Ej009RestMaturityRichardson.contieneHipermediaHateoas("{\"id\": 42}"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_generarEnlaceHal() {
        String json = Ej009RestMaturityRichardson.generarEnlaceHal("/pedidos/42", "self");
        assertTrue(json.contains("\"self\""));
        assertTrue(json.contains("\"/pedidos/42\""));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_clasificarCodigoEstadoMaturity() {
        assertEquals("Created", Ej009RestMaturityRichardson.clasificarCodigoEstadoMaturity(201));
        assertEquals("Conflict", Ej009RestMaturityRichardson.clasificarCodigoEstadoMaturity(409));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_esIdempotenteParaMaturity() {
        assertTrue(Ej009RestMaturityRichardson.esIdempotenteParaMaturity("GET"));
        assertFalse(Ej009RestMaturityRichardson.esIdempotenteParaMaturity("POST"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_sugerirVerboYCodigo() {
        assertTrue(Ej009RestMaturityRichardson.sugerirVerboYCodigo("crearUsuario").contains("201"));
        assertTrue(Ej009RestMaturityRichardson.sugerirVerboYCodigo("eliminarFila").contains("DELETE"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_extraerEnlacesDeCabeceraLink() {
        var res = Ej009RestMaturityRichardson.extraerEnlacesDeCabeceraLink("<https://api.example.com/items?page=2>; rel=\"next\"");
        assertEquals(1, res.length);
        assertEquals("https://api.example.com/items?page=2", res[0]);
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_esColeccionPaginadaSegura() {
        assertTrue(Ej009RestMaturityRichardson.esColeccionPaginadaSegura("{\"_links\": {\"next\": {\"href\": \"/x\"}}}"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_generarRespuestaHalPaginada() {
        String hal = Ej009RestMaturityRichardson.generarRespuestaHalPaginada("pedidos", 1, 3);
        assertTrue(hal.contains("\"next\""));
        assertTrue(hal.contains("\"prev\""));
    }
}
