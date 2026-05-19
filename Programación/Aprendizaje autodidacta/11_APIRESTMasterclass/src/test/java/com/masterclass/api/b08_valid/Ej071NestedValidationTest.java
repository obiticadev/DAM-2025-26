package com.masterclass.api.b08_valid;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b08_valid.Ej071NestedValidation.Cliente;
import com.masterclass.api.b08_valid.Ej071NestedValidation.Direccion;
import static org.junit.jupiter.api.Assertions.*;

class Ej071NestedValidationTest {

    @Test
    void cascadaDetectaErroresInternos() {
        var inv = Ej071NestedValidation.rutasInvalidas(
                new Cliente("Ana", new Direccion("", "abc")));
        assertTrue(inv.contains("direccion.calle"), "la cascada @Valid debe propagar: " + inv);
        assertTrue(inv.contains("direccion.cp"));
    }

    @Test
    void direccionNull() {
        var inv = Ej071NestedValidation.rutasInvalidas(new Cliente("Ana", null));
        assertTrue(inv.contains("direccion"));
    }

    @Test
    void todoValido() {
        var inv = Ej071NestedValidation.rutasInvalidas(
                new Cliente("Ana", new Direccion("C/ Mayor", "28001")));
        assertTrue(inv.isEmpty(), "no debería haber inválidos: " + inv);
    }
}
