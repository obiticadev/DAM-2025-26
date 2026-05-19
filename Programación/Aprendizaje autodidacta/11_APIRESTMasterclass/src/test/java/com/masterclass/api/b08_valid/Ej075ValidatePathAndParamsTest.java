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
}
