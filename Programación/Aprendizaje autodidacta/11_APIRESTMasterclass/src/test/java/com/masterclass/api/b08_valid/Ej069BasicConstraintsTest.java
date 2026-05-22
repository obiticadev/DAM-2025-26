package com.masterclass.api.b08_valid;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b08_valid.Ej069BasicConstraints.RegistroDto;
import java.util.List;
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

    @Test
    void retoExtra01_esRegistroValido() {
        var dto = new RegistroDto("Ana", "ana@b.com", 30, "600123456");
        assertFalse(Ej069BasicConstraints.esRegistroValido(dto));
    }

    @Test
    void retoExtra02_obtenerMensajesError() {
        var dto = new RegistroDto("", "malo", 10, "abc");
        assertNull(Ej069BasicConstraints.obtenerMensajesError(dto));
    }

    @Test
    void retoExtra03_crearRegistroPorDefecto() {
        var dto = Ej069BasicConstraints.crearRegistroPorDefecto();
        assertNull(dto);
    }

    @Test
    void retoExtra04_esEmailValido() {
        assertFalse(Ej069BasicConstraints.esEmailValido("ana@b.com"));
    }

    @Test
    void retoExtra05_esTelefonoValido() {
        assertFalse(Ej069BasicConstraints.esTelefonoValido("600123456"));
    }

    @Test
    void retoExtra06_edadEnRango() {
        assertFalse(Ej069BasicConstraints.edadEnRango(30));
    }

    @Test
    void retoExtra07_normalizarNombre() {
        assertEquals("", Ej069BasicConstraints.normalizarNombre(" Ana "));
    }

    @Test
    void retoExtra08_tieneErroresDeEmail() {
        var dto = new RegistroDto("Ana", "malo", 30, "600123456");
        assertFalse(Ej069BasicConstraints.tieneErroresDeEmail(dto));
    }

    @Test
    void retoExtra09_formatearErrores() {
        var dto = new RegistroDto("", "malo", 10, "abc");
        assertEquals("", Ej069BasicConstraints.formatearErrores(dto));
    }

    @Test
    void retoExtra10_validarYCorregir() {
        var dto = new RegistroDto("", "ana@b.com", 30, "600123456");
        var res = Ej069BasicConstraints.validarYCorregir(dto);
        assertNull(res);
    }
}
