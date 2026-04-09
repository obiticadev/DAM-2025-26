package com.masterclass.nivel6_excepciones_unchecked;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 6 - Ejercicio 47: ClassCast")
class Ejercicio47_ClassCastTest {
    @Test @DisplayName("47.1 - castSeguro correcto") void ok() { assertThat(Ejercicio47_ClassCast.castSeguro("Hola", String.class, "def")).isEqualTo("Hola"); }
    @Test @DisplayName("47.2 - castSeguro incorrecto devuelve default") void fail() { assertThat(Ejercicio47_ClassCast.castSeguro(42, String.class, "def")).isEqualTo("def"); }
    @Test @DisplayName("47.3 - describir Integer") void descInt() { assertThat(Ejercicio47_ClassCast.describir(42)).isEqualTo("Entero: 42"); }
    @Test @DisplayName("47.4 - describir String") void descStr() { assertThat(Ejercicio47_ClassCast.describir("Hola")).isEqualTo("Texto: Hola"); }
    @Test @DisplayName("47.5 - describir Double") void descDbl() { assertThat(Ejercicio47_ClassCast.describir(3.14)).isEqualTo("Decimal: 3.14"); }
}
