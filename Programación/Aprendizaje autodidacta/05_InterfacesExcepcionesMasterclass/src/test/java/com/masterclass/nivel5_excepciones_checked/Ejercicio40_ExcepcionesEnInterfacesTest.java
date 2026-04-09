package com.masterclass.nivel5_excepciones_checked;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 5 - Ejercicio 40: Excepciones en Interfaces")
class Ejercicio40_ExcepcionesEnInterfacesTest {
    @Test @DisplayName("40.1 - parserEntero parsea correctamente") void parseOk() throws Exception { assertThat(Ejercicio40_ExcepcionesEnInterfaces.crearParserEntero().parse("42")).isEqualTo(42); }
    @Test @DisplayName("40.2 - parserEntero lanza ParseException") void parseFail() { assertThatThrownBy(() -> Ejercicio40_ExcepcionesEnInterfaces.crearParserEntero().parse("abc")).isInstanceOf(Ejercicio40_ExcepcionesEnInterfaces.ParseException.class); }
    @Test @DisplayName("40.3 - parserBooleano parsea true") void boolTrue() throws Exception { assertThat(Ejercicio40_ExcepcionesEnInterfaces.crearParserBooleano().parse("TRUE")).isTrue(); }
    @Test @DisplayName("40.4 - parserBooleano parsea false") void boolFalse() throws Exception { assertThat(Ejercicio40_ExcepcionesEnInterfaces.crearParserBooleano().parse("false")).isFalse(); }
    @Test @DisplayName("40.5 - parserBooleano lanza con valor invalido") void boolFail() { assertThatThrownBy(() -> Ejercicio40_ExcepcionesEnInterfaces.crearParserBooleano().parse("yes")).isInstanceOf(Ejercicio40_ExcepcionesEnInterfaces.ParseException.class); }
    @Test @DisplayName("40.6 - parsearTodos con defaults") void parsearTodos() { assertThat(Ejercicio40_ExcepcionesEnInterfaces.parsearTodos(List.of("1","abc","3"), Ejercicio40_ExcepcionesEnInterfaces.crearParserEntero(), 0)).containsExactly(1,0,3); }
}
