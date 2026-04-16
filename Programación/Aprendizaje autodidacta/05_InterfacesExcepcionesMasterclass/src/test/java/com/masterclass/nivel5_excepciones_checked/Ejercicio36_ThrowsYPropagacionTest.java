package com.masterclass.nivel5_excepciones_checked;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 5 - Ejercicio 36: Throws y Propagacion")
class Ejercicio36_ThrowsYPropagacionTest {
    @Test @DisplayName("36.1 - validarEdad con edad valida") void edadOk() throws Exception { assertThat(Ejercicio36_ThrowsYPropagacion.validarEdad(25)).isEqualTo("Edad valida: 25"); }
    @Test @DisplayName("36.2 - validarEdad con edad negativa lanza excepcion") void edadNeg() { assertThatThrownBy(() -> Ejercicio36_ThrowsYPropagacion.validarEdad(-1)).isInstanceOf(Ejercicio36_ThrowsYPropagacion.EdadInvalidaException.class); }
    @Test @DisplayName("36.3 - validarEdad con edad > 150 lanza excepcion") void edadAlta() { assertThatThrownBy(() -> Ejercicio36_ThrowsYPropagacion.validarEdad(200)).isInstanceOf(Ejercicio36_ThrowsYPropagacion.EdadInvalidaException.class); }
    @Test @DisplayName("36.4 - procesarEdad captura error") void procesarError() { assertThat(Ejercicio36_ThrowsYPropagacion.procesarEdad(-5)).startsWith("Error:"); }
    @Test @DisplayName("36.5 - procesarEdad exito") void procesarOk() { assertThat(Ejercicio36_ThrowsYPropagacion.procesarEdad(30)).isEqualTo("Edad valida: 30"); }
}
