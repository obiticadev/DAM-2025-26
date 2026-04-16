package com.masterclass.nivel4_interfaces_funcionales;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 4 - Ejercicio 28: Unary y Binary Operator")
class Ejercicio28_UnaryBinaryOperatorTest {
    @Test @DisplayName("28.1 - mayusculas convierte") void mayus() { assertThat(Ejercicio28_UnaryBinaryOperator.mayusculas().apply("hola")).isEqualTo("HOLA"); }
    @Test @DisplayName("28.2 - duplicar duplica") void dup() { assertThat(Ejercicio28_UnaryBinaryOperator.duplicar().apply(5)).isEqualTo(10); }
    @Test @DisplayName("28.3 - sumar suma") void sum() { assertThat(Ejercicio28_UnaryBinaryOperator.sumar().apply(3,4)).isEqualTo(7); }
    @Test @DisplayName("28.4 - reducir con suma") void red() { assertThat(Ejercicio28_UnaryBinaryOperator.reducir(List.of(1,2,3,4), Ejercicio28_UnaryBinaryOperator.sumar(), 0)).isEqualTo(10); }
    @Test @DisplayName("28.5 - aplicarATodos con duplicar") void aplic() { assertThat(Ejercicio28_UnaryBinaryOperator.aplicarATodos(List.of(1,2,3), Ejercicio28_UnaryBinaryOperator.duplicar())).containsExactly(2,4,6); }
}
