package com.masterclass.nivel7_excepciones_custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 7 - Ejercicio 51: Patron Result")
class Ejercicio51_PatronResultTest {
    @Test @DisplayName("51.1 - dividir ok") void divOk() { var r = Ejercicio51_PatronResult.dividir(10,3); assertThat(r.isSuccess()).isTrue(); assertThat(r.getValue()).isCloseTo(3.33, within(0.01)); }
    @Test @DisplayName("51.2 - dividir por cero") void divCero() { var r = Ejercicio51_PatronResult.dividir(10,0); assertThat(r.isSuccess()).isFalse(); assertThat(r.getError()).contains("cero"); }
    @Test @DisplayName("51.3 - parsear ok") void parseOk() { var r = Ejercicio51_PatronResult.parsear("42"); assertThat(r.isSuccess()).isTrue(); assertThat(r.getValue()).isEqualTo(42); }
    @Test @DisplayName("51.4 - parsear error") void parseFail() { var r = Ejercicio51_PatronResult.parsear("abc"); assertThat(r.isSuccess()).isFalse(); }
}
