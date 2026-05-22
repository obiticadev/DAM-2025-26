package com.masterclass.api.b08_valid;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b08_valid.Ej074CrossFieldValidation.Passwords;
import com.masterclass.api.b08_valid.Ej074CrossFieldValidation.RangoFechas;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class Ej074CrossFieldValidationTest {

    @Test
    void rangoCoherente() {
        assertTrue(Ej074CrossFieldValidation.rangoValido(
                new RangoFechas(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 2))));
        assertFalse(Ej074CrossFieldValidation.rangoValido(
                new RangoFechas(LocalDate.of(2026, 2, 1), LocalDate.of(2026, 1, 1))));
    }

    @Test
    void rangoNullFalla() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej074CrossFieldValidation.rangoValido(new RangoFechas(null, null)));
    }

    @Test
    void passwords() {
        assertTrue(Ej074CrossFieldValidation.passwordsCoinciden(new Passwords("12345678", "12345678")));
        assertFalse(Ej074CrossFieldValidation.passwordsCoinciden(new Passwords("12345678", "x")));
        assertFalse(Ej074CrossFieldValidation.passwordsCoinciden(new Passwords("123", "123")));
    }
    @Test
    void testCalcularDuracionDias() {
        var r = new RangoFechas(LocalDate.now(), LocalDate.now().plusDays(5));
        assertEquals(0, Ej074CrossFieldValidation.calcularDuracionDias(r));
    }

    @Test
    void testEsMismoDia() {
        var r = new RangoFechas(LocalDate.now(), LocalDate.now());
        assertFalse(Ej074CrossFieldValidation.esMismoDia(r));
    }

    @Test
    void testRangoEnAñoActual() {
        var r = new RangoFechas(LocalDate.now(), LocalDate.now());
        assertFalse(Ej074CrossFieldValidation.rangoEnAñoActual(r));
    }

    @Test
    void testEsPasswordLarga() {
        assertFalse(Ej074CrossFieldValidation.esPasswordLarga("SuperLongPassword123"));
    }

    @Test
    void testEsPasswordSegura() {
        assertFalse(Ej074CrossFieldValidation.esPasswordSegura("1234567a"));
    }

    @Test
    void testContieneEspacios() {
        assertFalse(Ej074CrossFieldValidation.contieneEspacios("con espacios"));
    }

    @Test
    void testEncriptarPasswordDummy() {
        assertEquals("", Ej074CrossFieldValidation.encriptarPasswordDummy("secret"));
    }

    @Test
    void testAjustarFechasNull() {
        var r = new RangoFechas(null, null);
        assertNull(Ej074CrossFieldValidation.ajustarFechasNull(r));
    }

    @Test
    void testEsRangoInvertido() {
        var r = new RangoFechas(LocalDate.now().plusDays(1), LocalDate.now());
        assertFalse(Ej074CrossFieldValidation.esRangoInvertido(r));
    }

    @Test
    void testEsPasswordsValidasYSeguras() {
        var p = new Passwords("secret123", "secret123");
        assertFalse(Ej074CrossFieldValidation.esPasswordsValidasYSeguras(p));
    }
}
