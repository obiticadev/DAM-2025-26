package com.masterclass.nivel4_interfaces_funcionales;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 4 - Ejercicio 30: Method References")
class Ejercicio30_MethodReferencesTest {
    @Test @DisplayName("30.1 - aMayusculas convierte") void mayus() { assertThat(Ejercicio30_MethodReferences.aMayusculas().apply("hola")).isEqualTo("HOLA"); }
    @Test @DisplayName("30.2 - esVacio detecta vacio") void vacio() { assertThat(Ejercicio30_MethodReferences.esVacio().test("")).isTrue(); assertThat(Ejercicio30_MethodReferences.esVacio().test("x")).isFalse(); }
    @Test @DisplayName("30.3 - aEntero parsea") void entero() { assertThat(Ejercicio30_MethodReferences.aEntero().apply("42")).isEqualTo(42); }
    @Test @DisplayName("30.4 - filtrarYMayusculas combina todo") void filtrarYMayus() { assertThat(Ejercicio30_MethodReferences.filtrarYMayusculas(List.of("hola", "", "mundo", ""))).containsExactly("HOLA", "MUNDO"); }
}
