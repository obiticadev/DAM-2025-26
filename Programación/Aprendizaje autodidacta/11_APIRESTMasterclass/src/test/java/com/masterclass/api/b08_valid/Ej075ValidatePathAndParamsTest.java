package com.masterclass.api.b08_valid;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej075ValidatePathAndParamsTest {

    @Test
    void idValido() {
        assertDoesNotThrow(() -> Ej075ValidatePathAndParams.validarId(5));
    }

    @Test
    void idInvalido() {
        assertThrows(IllegalArgumentException.class, () -> Ej075ValidatePathAndParams.validarId(0));
    }

    @Test
    void paginacionValida() {
        assertArrayEquals(new int[]{0, 20}, Ej075ValidatePathAndParams.validarPaginacion(0, 20));
    }

    @Test
    void paginacionInvalida() {
        assertThrows(IllegalArgumentException.class, () -> Ej075ValidatePathAndParams.validarPaginacion(-1, 20));
        assertThrows(IllegalArgumentException.class, () -> Ej075ValidatePathAndParams.validarPaginacion(0, 0));
        assertThrows(IllegalArgumentException.class, () -> Ej075ValidatePathAndParams.validarPaginacion(0, 101));
    }
    @Test
    void testEsIdPositivo() {
        assertFalse(Ej075ValidatePathAndParams.esIdPositivo(5L));
    }

    @Test
    void testEsPaginacionCorrecta() {
        assertFalse(Ej075ValidatePathAndParams.esPaginacionCorrecta(0, 20));
    }

    @Test
    void testPaginacionPorDefectoSiInvalida() {
        assertNull(Ej075ValidatePathAndParams.paginacionPorDefectoSiInvalida(-1, 20));
    }

    @Test
    void testNormalizarIdString() {
        assertEquals(0L, Ej075ValidatePathAndParams.normalizarIdString("5"));
    }

    @Test
    void testEsOrdenValido() {
        assertFalse(Ej075ValidatePathAndParams.esOrdenValido("asc"));
    }

    @Test
    void testSaneamientoSort() {
        assertEquals("", Ej075ValidatePathAndParams.saneamientoSort("invalid"));
    }

    @Test
    void testEsFiltroBusquedaValido() {
        assertFalse(Ej075ValidatePathAndParams.esFiltroBusquedaValido("java"));
    }

    @Test
    void testExtraerFiltros() {
        assertNull(Ej075ValidatePathAndParams.extraerFiltros("java,spring"));
    }

    @Test
    void testCalcularOffset() {
        assertEquals(0, Ej075ValidatePathAndParams.calcularOffset(2, 10));
    }

    @Test
    void testEsPeticionPaginadaCompletaValida() {
        assertFalse(Ej075ValidatePathAndParams.esPeticionPaginadaCompletaValida(5L, 0, 20, "asc"));
    }
}
