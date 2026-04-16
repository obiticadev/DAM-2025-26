package com.masterclass.nivel7_excepciones_custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 7 - Ejercicio 52: Excepciones y Genericos")
class Ejercicio52_ExcepcionesYGenericosTest {
    @Test @DisplayName("52.1 - mapearSeguro con exitos") void ok() { assertThat(Ejercicio52_ExcepcionesYGenericos.mapearSeguro(List.of("1","2","3"), Integer::parseInt, 0)).containsExactly(1,2,3); }
    @Test @DisplayName("52.2 - mapearSeguro con fallos usa default") void def() { assertThat(Ejercicio52_ExcepcionesYGenericos.mapearSeguro(List.of("1","abc","3"), Integer::parseInt, 0)).containsExactly(1,0,3); }
    @Test @DisplayName("52.3 - envolver funciona") void envOk() { var fn = Ejercicio52_ExcepcionesYGenericos.envolver((String s) -> Integer.parseInt(s)); assertThat(fn.apply("42")).isEqualTo(42); }
    @Test @DisplayName("52.4 - envolver lanza RuntimeException") void envFail() { var fn = Ejercicio52_ExcepcionesYGenericos.envolver((String s) -> Integer.parseInt(s)); assertThatThrownBy(() -> fn.apply("abc")).isInstanceOf(RuntimeException.class); }
}
