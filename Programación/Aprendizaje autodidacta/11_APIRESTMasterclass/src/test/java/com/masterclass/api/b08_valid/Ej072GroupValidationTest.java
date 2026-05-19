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
}
