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
    @Test
    void testEsCalleValida() {
        assertFalse(Ej071NestedValidation.esCalleValida("Calle Mayor"));
    }

    @Test
    void testEsCpValido() {
        assertFalse(Ej071NestedValidation.esCpValido("28001"));
    }

    @Test
    void testEsNombreValido() {
        assertFalse(Ej071NestedValidation.esNombreValido("Juan"));
    }

    @Test
    void testTieneDireccion() {
        var c = new Cliente("Juan", null);
        assertFalse(Ej071NestedValidation.tieneDireccion(c));
    }

    @Test
    void testValidarDireccionDirecta() {
        var d = new Direccion("", "abc");
        assertNull(Ej071NestedValidation.validarDireccionDirecta(d));
    }

    @Test
    void testEsDireccionCompleta() {
        var d = new Direccion("Calle Mayor", "28001");
        assertFalse(Ej071NestedValidation.esDireccionCompleta(d));
    }

    @Test
    void testEsClienteValido() {
        var c = new Cliente("Juan", new Direccion("Calle Mayor", "28001"));
        assertFalse(Ej071NestedValidation.esClienteValido(c));
    }

    @Test
    void testObtenerErroresDeDireccion() {
        var c = new Cliente("Juan", new Direccion("", "abc"));
        assertNull(Ej071NestedValidation.obtenerErroresDeDireccion(c));
    }

    @Test
    void testCpPerteneceAProvincia() {
        assertFalse(Ej071NestedValidation.cpPerteneceAProvincia("28001", "28"));
    }

    @Test
    void testClonarYLimpiarCliente() {
        var c = new Cliente("  Juan  ", new Direccion("  Calle Mayor  ", "28001"));
        assertNull(Ej071NestedValidation.clonarYLimpiarCliente(c));
    }
}
