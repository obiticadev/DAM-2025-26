package com.masterclass.nivel6_excepciones_unchecked;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 6 - Ejercicio 41: IllegalArgument")
class Ejercicio41_IllegalArgumentTest {
    @Test @DisplayName("41.1 - validarEdad valida") void ok() { assertThat(Ejercicio41_IllegalArgument.validarEdad(25)).isEqualTo("Edad: 25"); }
    @Test @DisplayName("41.2 - validarEdad negativa lanza") void neg() { assertThatThrownBy(() -> Ejercicio41_IllegalArgument.validarEdad(-1)).isInstanceOf(IllegalArgumentException.class); }
    @Test @DisplayName("41.3 - validarEdad > 150 lanza") void alta() { assertThatThrownBy(() -> Ejercicio41_IllegalArgument.validarEdad(200)).isInstanceOf(IllegalArgumentException.class); }
    @Test @DisplayName("41.4 - longitudSegura null lanza NPE") void npe() { assertThatThrownBy(() -> Ejercicio41_IllegalArgument.longitudSegura(null)).isInstanceOf(NullPointerException.class); }
    @Test @DisplayName("41.5 - longitudSegura vacio lanza IAE") void vacio() { assertThatThrownBy(() -> Ejercicio41_IllegalArgument.longitudSegura("")).isInstanceOf(IllegalArgumentException.class); }
    @Test @DisplayName("41.6 - longitudSegura ok") void longOk() { assertThat(Ejercicio41_IllegalArgument.longitudSegura("Hola")).isEqualTo(4); }
}
