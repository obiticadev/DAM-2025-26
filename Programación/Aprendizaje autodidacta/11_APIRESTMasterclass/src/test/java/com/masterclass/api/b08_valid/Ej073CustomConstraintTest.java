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
}
