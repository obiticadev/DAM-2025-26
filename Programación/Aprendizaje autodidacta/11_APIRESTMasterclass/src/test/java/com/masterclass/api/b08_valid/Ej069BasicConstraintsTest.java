package com.masterclass.api.b08_valid;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b08_valid.Ej069BasicConstraints.RegistroDto;
import static org.junit.jupiter.api.Assertions.*;

class Ej069BasicConstraintsTest {

    @Test
    void todoInvalido() {
        var inv = Ej069BasicConstraints.camposInvalidos(new RegistroDto("", "malo", 10, "abc"));
        assertTrue(inv.contains("nombre"));
        assertTrue(inv.contains("email"));
        assertTrue(inv.contains("edad"));
        assertTrue(inv.contains("telefono"));
    }

    @Test
    void todoValido() {
        var inv = Ej069BasicConstraints.camposInvalidos(
                new RegistroDto("Ana", "ana@b.com", 30, "600123456"));
        assertTrue(inv.isEmpty(), "no debería haber inválidos: " + inv);
    }
}
