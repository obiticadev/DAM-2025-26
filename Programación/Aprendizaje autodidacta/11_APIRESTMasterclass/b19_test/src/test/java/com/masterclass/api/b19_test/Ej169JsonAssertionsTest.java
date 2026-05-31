package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej169JsonAssertionsTest {

    @Test
    void igualesAunqueOrdenDistinto() {
        assertTrue(Ej169JsonAssertions.jsonIguales("{\"a\":1,\"b\":2}", "{\"b\":2,\"a\":1}"));
    }

    @Test
    void distintos() {
        assertFalse(Ej169JsonAssertions.jsonIguales("{\"a\":1}", "{\"a\":2}"));
    }

    @Test
    void jsonInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej169JsonAssertions.jsonIguales("{no-json", "{}"));
    }

    @Test
    void valorDeCampo() {
        assertEquals("Ada", Ej169JsonAssertions.valorCampo("{\"nombre\":\"Ada\"}", "nombre"));
        assertNull(Ej169JsonAssertions.valorCampo("{\"nombre\":\"Ada\"}", "edad"));
    }

    @Test
    void testRetoExtra01_crearJsonNodo() {
        // Crea un nodo JSON simple.
        assertNotNull(Ej169JsonAssertions.crearJsonNodo("k", "v"));
    }

    @Test
    void testRetoExtra02_contieneCampo() {
        // Comprueba si contiene el campo.
        assertTrue(Ej169JsonAssertions.contieneCampo("{\"a\":1}", "a"));
    }

    @Test
    void testRetoExtra03_obtenerCampoEntero() {
        // Extrae valor entero del campo.
        assertEquals(10, Ej169JsonAssertions.obtenerCampoEntero("{\"a\":10}", "a"));
    }

    @Test
    void testRetoExtra04_esArrayJson() {
        // Determina si el JSON es un array.
        assertTrue(Ej169JsonAssertions.esArrayJson("[1,2]"));
    }

    @Test
    void testRetoExtra05_tamanioArrayJson() {
        // Obtiene el tamaño del array JSON.
        assertEquals(2, Ej169JsonAssertions.tamanioArrayJson("[1,2]"));
    }

    @Test
    void testRetoExtra06_esObjetoJson() {
        // Determina si el JSON es un objeto.
        assertTrue(Ej169JsonAssertions.esObjetoJson("{\"a\":1}"));
    }

    @Test
    void testRetoExtra07_obtenerCampoBooleano() {
        // Extrae valor booleano del campo.
        assertTrue(Ej169JsonAssertions.obtenerCampoBooleano("{\"a\":true}", "a"));
    }

    @Test
    void testRetoExtra08_formatoJsonLimpio() {
        // Formatea el JSON de forma compacta.
        assertFalse(Ej169JsonAssertions.formatoJsonLimpio("{\"a\": 1}").isEmpty());
    }

    @Test
    void testRetoExtra09_obtenerNombresCampos() {
        // Obtiene nombres de todos los campos.
        assertEquals(1, Ej169JsonAssertions.obtenerNombresCampos("{\"a\":1}").size());
    }

    @Test
    void testRetoExtra10_esJsonValido() {
        // Valida si el JSON es sintacticamente correcto.
        assertTrue(Ej169JsonAssertions.esJsonValido("{}"));
    }

}