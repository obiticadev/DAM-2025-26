package com.masterclass.nivel5_excepciones_checked;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 5 - Ejercicio 34: Multiple Catch")
class Ejercicio34_MultipleCatchTest {
    @Test @DisplayName("34.1 - operacionMultiple exito") void ok() { assertThat(Ejercicio34_MultipleCatch.operacionMultiple("0", new String[]{"Hola"})).isEqualTo("Hola"); }
    @Test @DisplayName("34.2 - operacionMultiple error formato") void formato() { assertThat(Ejercicio34_MultipleCatch.operacionMultiple("abc", new String[]{"Hola"})).isEqualTo("ERROR_FORMATO"); }
    @Test @DisplayName("34.3 - operacionMultiple error indice") void indice() { assertThat(Ejercicio34_MultipleCatch.operacionMultiple("5", new String[]{"Hola"})).isEqualTo("ERROR_INDICE"); }
    @Test @DisplayName("34.4 - operacionMultiCatch exito") void multiOk() { assertThat(Ejercicio34_MultipleCatch.operacionMultiCatch("10", 2)).isEqualTo("OK:5"); }
    @Test @DisplayName("34.5 - operacionMultiCatch error formato") void multiFmt() { assertThat(Ejercicio34_MultipleCatch.operacionMultiCatch("abc", 2)).isNotNull(); }
    @Test @DisplayName("34.6 - operacionMultiCatch error division") void multiDiv() { assertThat(Ejercicio34_MultipleCatch.operacionMultiCatch("10", 0)).isNotNull(); }
}
