package com.masterclass.nivel5_excepciones_checked;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 5 - Ejercicio 38: Exception Chaining")
class Ejercicio38_ExceptionChainingTest {
    @Test @DisplayName("38.1 - procesarDato exitoso") void ok() throws Exception { assertThat(Ejercicio38_ExceptionChaining.procesarDato("42")).isEqualTo(42); }
    @Test @DisplayName("38.2 - procesarDato lanza ServicioException con causa") void fail() {
        assertThatThrownBy(() -> Ejercicio38_ExceptionChaining.procesarDato("abc"))
            .isInstanceOf(Ejercicio38_ExceptionChaining.ServicioException.class)
            .hasCauseInstanceOf(NumberFormatException.class);
    }
    @Test @DisplayName("38.3 - procesarConInfo exitoso") void infoOk() { assertThat(Ejercicio38_ExceptionChaining.procesarConInfo("42")).isEqualTo("Resultado: 42"); }
    @Test @DisplayName("38.4 - procesarConInfo con error incluye causa") void infoFail() { assertThat(Ejercicio38_ExceptionChaining.procesarConInfo("abc")).contains("Error:").contains("Causa:"); }
}
