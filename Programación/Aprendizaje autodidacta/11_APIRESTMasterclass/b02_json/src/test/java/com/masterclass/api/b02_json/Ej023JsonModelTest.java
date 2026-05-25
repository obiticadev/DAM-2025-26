package com.masterclass.api.b02_json;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej023JsonModelTest {

    @Test
    void tipos() {
        assertEquals("objeto", Ej023JsonModel.tipo("{}"));
        assertEquals("array", Ej023JsonModel.tipo("[1,2]"));
        assertEquals("string", Ej023JsonModel.tipo("\"hola\""));
        assertEquals("numero", Ej023JsonModel.tipo("42"));
        assertEquals("numero", Ej023JsonModel.tipo("3.14"));
        assertEquals("booleano", Ej023JsonModel.tipo("true"));
        assertEquals("null", Ej023JsonModel.tipo("null"));
    }

    @Test
    void desconocido() {
        assertEquals("desconocido", Ej023JsonModel.tipo("??"));
        assertEquals("desconocido", Ej023JsonModel.tipo(""));
    }

    @Test
    void retoExtra01_esObjetoVacio() {
        assertTrue(Ej023JsonModel.esObjetoVacio("{}"));
        assertTrue(Ej023JsonModel.esObjetoVacio(" {   } "));
        assertFalse(Ej023JsonModel.esObjetoVacio("{\"id\":1}"));
    }

    @Test
    void retoExtra02_esArrayVacio() {
        assertTrue(Ej023JsonModel.esArrayVacio("[]"));
        assertTrue(Ej023JsonModel.esArrayVacio(" [  ] "));
        assertFalse(Ej023JsonModel.esArrayVacio("[1,2]"));
    }

    @Test
    void retoExtra03_esStringValido() {
        assertTrue(Ej023JsonModel.esStringValido("\"hola\""));
        assertFalse(Ej023JsonModel.esStringValido("hola"));
        assertFalse(Ej023JsonModel.esStringValido("\""));
    }

    @Test
    void retoExtra04_esNumeroEntero() {
        assertTrue(Ej023JsonModel.esNumeroEntero("42"));
        assertTrue(Ej023JsonModel.esNumeroEntero("-5"));
        assertFalse(Ej023JsonModel.esNumeroEntero("3.14"));
        assertFalse(Ej023JsonModel.esNumeroEntero("abc"));
    }

    @Test
    void retoExtra05_extraerTextoString() {
        assertEquals("hola", Ej023JsonModel.extraerTextoString("\"hola\""));
        assertEquals("dijo \"si\"", Ej023JsonModel.extraerTextoString("\"dijo \\\"si\\\"\""));
        assertNull(Ej023JsonModel.extraerTextoString("sincomillas"));
    }

    @Test
    void retoExtra06_contarElementosArraySimple() {
        assertEquals(3, Ej023JsonModel.contarElementosArraySimple("[1, \"dos\", null]"));
        assertEquals(0, Ej023JsonModel.contarElementosArraySimple("[]"));
    }

    @Test
    void retoExtra07_limpiarEspaciosJson() {
        assertEquals("{\"a\":1}", Ej023JsonModel.limpiarEspaciosJson(" { \"a\" : 1 } "));
        assertEquals("\"con espacios\"", Ej023JsonModel.limpiarEspaciosJson(" \"con espacios\" "));
    }

    @Test
    void retoExtra08_esBooleanoFalso() {
        assertTrue(Ej023JsonModel.esBooleanoFalso("false"));
        assertTrue(Ej023JsonModel.esBooleanoFalso("  false  "));
        assertFalse(Ej023JsonModel.esBooleanoFalso("true"));
    }

    @Test
    void retoExtra09_esNullJson() {
        assertTrue(Ej023JsonModel.esNullJson("null"));
        assertTrue(Ej023JsonModel.esNullJson("  null  "));
        assertFalse(Ej023JsonModel.esNullJson("undefined"));
    }

    @Test
    void retoExtra10_obtenerLiteralDefecto() {
        assertEquals("{}", Ej023JsonModel.obtenerLiteralDefecto("{}", "\"defecto\""));
        assertEquals("\"defecto\"", Ej023JsonModel.obtenerLiteralDefecto("??", "\"defecto\""));
    }
}
