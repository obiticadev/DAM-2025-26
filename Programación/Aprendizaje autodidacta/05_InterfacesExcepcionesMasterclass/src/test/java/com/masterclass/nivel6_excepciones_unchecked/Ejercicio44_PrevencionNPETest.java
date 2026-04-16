package com.masterclass.nivel6_excepciones_unchecked;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 6 - Ejercicio 44: Prevencion NPE")
class Ejercicio44_PrevencionNPETest {
    @Test @DisplayName("44.1 - saludar ok") void ok() { assertThat(Ejercicio44_PrevencionNPE.saludar("Juan")).isEqualTo("Hola, Juan"); }
    @Test @DisplayName("44.2 - saludar null lanza") void npe() { assertThatThrownBy(() -> Ejercicio44_PrevencionNPE.saludar(null)).isInstanceOf(NullPointerException.class); }
    @Test @DisplayName("44.3 - filtrarNulls filtra") void filtrar() { assertThat(Ejercicio44_PrevencionNPE.filtrarNulls(Arrays.asList("A", null, "B", null))).containsExactly("A", "B"); }
    @Test @DisplayName("44.4 - primerNoNull encuentra") void primer() { assertThat(Ejercicio44_PrevencionNPE.primerNoNull(Arrays.asList(null, null, "X"), "def")).isEqualTo("X"); }
    @Test @DisplayName("44.5 - primerNoNull default") void primerDef() { assertThat(Ejercicio44_PrevencionNPE.primerNoNull(Arrays.asList(null, null), "def")).isEqualTo("def"); }
}
