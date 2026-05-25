package com.masterclass.api.b08_valid;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b08_valid.Ej073CustomConstraint.ArticuloDto;
import static org.junit.jupiter.api.Assertions.*;

class Ej073CustomConstraintTest {

    @Test
    void slugValido() {
        assertTrue(Ej073CustomConstraint.esValido(new ArticuloDto("mi-articulo-1")));
    }

    @Test
    void slugConMayusculas() {
        assertFalse(Ej073CustomConstraint.esValido(new ArticuloDto("Mi-Articulo")));
    }

    @Test
    void slugConGuionAlInicio() {
        assertFalse(Ej073CustomConstraint.esValido(new ArticuloDto("-malo")));
    }

    @Test
    void slugVacio() {
        assertFalse(Ej073CustomConstraint.esValido(new ArticuloDto("")));
    }
    @Test
    void testEsSlugSintacticamenteValido() {
        assertFalse(Ej073CustomConstraint.esSlugSintacticamenteValido("mi-slug"));
    }

    @Test
    void testEmpiezaPorGuion() {
        assertFalse(Ej073CustomConstraint.empiezaPorGuion("-algo"));
    }

    @Test
    void testTerminaPorGuion() {
        assertFalse(Ej073CustomConstraint.terminaPorGuion("algo-"));
    }

    @Test
    void testContieneSoloMinusculasNumerosYGuiones() {
        assertFalse(Ej073CustomConstraint.contieneSoloMinusculasNumerosYGuiones("abc-123"));
    }

    @Test
    void testNormalizarStringASlug() {
        assertEquals("", Ej073CustomConstraint.normalizarStringASlug("Hola Mundo"));
    }

    @Test
    void testEsValidatorActivo() {
        assertFalse(Ej073CustomConstraint.esValidatorActivo());
    }

    @Test
    void testGenerarSlugArticulo() {
        assertEquals("", Ej073CustomConstraint.generarSlugArticulo("Nuevo Post!"));
    }

    @Test
    void testEsNullConsideradoValido() {
        assertFalse(Ej073CustomConstraint.esNullConsideradoValido(null));
    }

    @Test
    void testContarGuiones() {
        assertEquals(0, Ej073CustomConstraint.contarGuiones("a-b-c"));
    }

    @Test
    void testEsSlugPerfecto() {
        var dto = new ArticuloDto("slug");
        assertFalse(Ej073CustomConstraint.esSlugPerfecto(dto));
    }
}
