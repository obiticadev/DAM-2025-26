package com.masterclass.nivel7_excepciones_custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 7 - Ejercicio 56: Excepciones y Optional")
class Ejercicio56_ExcepcionesYOptionalTest {
    @Test @DisplayName("56.1 - parsearOptional ok") void ok() { assertThat(Ejercicio56_ExcepcionesYOptional.parsearOptional("42")).contains(42); }
    @Test @DisplayName("56.2 - parsearOptional fallo") void fail() { assertThat(Ejercicio56_ExcepcionesYOptional.parsearOptional("abc")).isEmpty(); }
    @Test @DisplayName("56.3 - parsearOLanzar ok") void lanzarOk() { assertThat(Ejercicio56_ExcepcionesYOptional.parsearOLanzar("42")).isEqualTo(42); }
    @Test @DisplayName("56.4 - parsearOLanzar fallo") void lanzarFail() { assertThatThrownBy(() -> Ejercicio56_ExcepcionesYOptional.parsearOLanzar("abc")).isInstanceOf(IllegalArgumentException.class); }
    @Test @DisplayName("56.5 - buscarSeguro ok") void busOk() { assertThat(Ejercicio56_ExcepcionesYOptional.buscarSeguro(new String[]{"A","B"}, 1)).contains("B"); }
    @Test @DisplayName("56.6 - buscarSeguro fuera de rango") void busFail() { assertThat(Ejercicio56_ExcepcionesYOptional.buscarSeguro(new String[]{"A"}, 5)).isEmpty(); }
}
