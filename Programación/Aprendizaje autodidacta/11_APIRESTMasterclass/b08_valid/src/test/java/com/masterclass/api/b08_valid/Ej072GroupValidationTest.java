package com.masterclass.api.b08_valid;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b08_valid.Ej072GroupValidation.RecursoDto;
import static org.junit.jupiter.api.Assertions.*;

class Ej072GroupValidationTest {

    @Test
    void createConIdEsInvalido() {
        assertTrue(Ej072GroupValidation.validarCreate(new RecursoDto(5L, "x")).contains("id"));
    }

    @Test
    void createSinIdEsValido() {
        assertTrue(Ej072GroupValidation.validarCreate(new RecursoDto(null, "x")).isEmpty());
    }

    @Test
    void updateSinIdEsInvalido() {
        assertTrue(Ej072GroupValidation.validarUpdate(new RecursoDto(null, "x")).contains("id"));
    }

    @Test
    void updateConIdEsValido() {
        assertTrue(Ej072GroupValidation.validarUpdate(new RecursoDto(7L, "x")).isEmpty());
    }
    @Test
    void testEsValidoParaCrear() {
        var dto = new RecursoDto(null, "x");
        assertFalse(Ej072GroupValidation.esValidoParaCrear(dto));
    }

    @Test
    void testEsValidoParaActualizar() {
        var dto = new RecursoDto(1L, "x");
        assertFalse(Ej072GroupValidation.esValidoParaActualizar(dto));
    }

    @Test
    void testEsNombreCorrecto() {
        assertFalse(Ej072GroupValidation.esNombreCorrecto("x"));
    }

    @Test
    void testTieneIdSiendoUpdate() {
        var dto = new RecursoDto(1L, "x");
        assertFalse(Ej072GroupValidation.tieneIdSiendoUpdate(dto));
    }

    @Test
    void testCareceDeIdSiendoCreate() {
        var dto = new RecursoDto(null, "x");
        assertFalse(Ej072GroupValidation.careceDeIdSiendoCreate(dto));
    }

    @Test
    void testObtenerCamposInvalidosGlobal() {
        var dto = new RecursoDto(null, "x");
        assertNull(Ej072GroupValidation.obtenerCamposInvalidosGlobal(dto));
    }

    @Test
    void testPrepararParaCrear() {
        var dto = new RecursoDto(5L, "x");
        assertNull(Ej072GroupValidation.prepararParaCrear(dto));
    }

    @Test
    void testPrepararParaActualizar() {
        var dto = new RecursoDto(null, "x");
        assertNull(Ej072GroupValidation.prepararParaActualizar(dto, 9L));
    }

    @Test
    void testEsIgualGrupo() {
        assertFalse(Ej072GroupValidation.esIgualGrupo(Ej072GroupValidation.OnCreate.class, Ej072GroupValidation.OnUpdate.class));
    }

    @Test
    void testValidarConGruposMultiples() {
        var dto = new RecursoDto(null, "x");
        assertNull(Ej072GroupValidation.validarConGruposMultiples(dto, Ej072GroupValidation.OnCreate.class));
    }
}
