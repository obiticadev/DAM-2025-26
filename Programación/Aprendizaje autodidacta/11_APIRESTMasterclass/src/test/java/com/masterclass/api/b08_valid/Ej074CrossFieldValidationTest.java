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
}
