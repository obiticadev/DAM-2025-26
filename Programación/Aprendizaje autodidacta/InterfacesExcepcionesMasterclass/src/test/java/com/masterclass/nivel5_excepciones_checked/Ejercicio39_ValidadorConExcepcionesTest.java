package com.masterclass.nivel5_excepciones_checked;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 5 - Ejercicio 39: Validador con Excepciones")
class Ejercicio39_ValidadorConExcepcionesTest {
    @Test @DisplayName("39.1 - email valido") void emailOk() throws Exception { assertThat(Ejercicio39_ValidadorConExcepciones.validarEmail("a@b.com")).contains("Email valido"); }
    @Test @DisplayName("39.2 - email null lanza") void emailNull() { assertThatThrownBy(() -> Ejercicio39_ValidadorConExcepciones.validarEmail(null)).isInstanceOf(Ejercicio39_ValidadorConExcepciones.ValidacionException.class); }
    @Test @DisplayName("39.3 - email sin @ lanza") void emailSinAt() { assertThatThrownBy(() -> Ejercicio39_ValidadorConExcepciones.validarEmail("abcde")).isInstanceOf(Ejercicio39_ValidadorConExcepciones.ValidacionException.class); }
    @Test @DisplayName("39.4 - password valida") void passOk() throws Exception { assertThat(Ejercicio39_ValidadorConExcepciones.validarPassword("12345678")).isEqualTo("Password valida"); }
    @Test @DisplayName("39.5 - password corta lanza") void passCorta() { assertThatThrownBy(() -> Ejercicio39_ValidadorConExcepciones.validarPassword("123")).isInstanceOf(Ejercicio39_ValidadorConExcepciones.ValidacionException.class); }
    @Test @DisplayName("39.6 - excepcion tiene campo correcto") void campo() {
        assertThatThrownBy(() -> Ejercicio39_ValidadorConExcepciones.validarEmail(null))
            .isInstanceOfSatisfying(Ejercicio39_ValidadorConExcepciones.ValidacionException.class, e -> assertThat(e.getCampo()).isEqualTo("email"));
    }
}
