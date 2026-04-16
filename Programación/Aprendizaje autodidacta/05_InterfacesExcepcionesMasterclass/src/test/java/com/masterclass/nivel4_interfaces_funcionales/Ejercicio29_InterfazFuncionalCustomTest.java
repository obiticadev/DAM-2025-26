package com.masterclass.nivel4_interfaces_funcionales;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 4 - Ejercicio 29: Interfaz Funcional Custom")
class Ejercicio29_InterfazFuncionalCustomTest {
    @Test @DisplayName("29.1 - noNuloNiVacio rechaza null") void rechazaNull() { assertThat(Ejercicio29_InterfazFuncionalCustom.noNuloNiVacio().validar(null)).isFalse(); }
    @Test @DisplayName("29.2 - noNuloNiVacio rechaza vacio") void rechazaVacio() { assertThat(Ejercicio29_InterfazFuncionalCustom.noNuloNiVacio().validar("")).isFalse(); }
    @Test @DisplayName("29.3 - noNuloNiVacio acepta texto") void aceptaTexto() { assertThat(Ejercicio29_InterfazFuncionalCustom.noNuloNiVacio().validar("hola")).isTrue(); }
    @Test @DisplayName("29.4 - esPositivo funciona") void positivo() { assertThat(Ejercicio29_InterfazFuncionalCustom.esPositivo().validar(5)).isTrue(); assertThat(Ejercicio29_InterfazFuncionalCustom.esPositivo().validar(-1)).isFalse(); assertThat(Ejercicio29_InterfazFuncionalCustom.esPositivo().validar(0)).isFalse(); }
    @Test @DisplayName("29.5 - validadorCompleto combina and") void completo() { assertThat(Ejercicio29_InterfazFuncionalCustom.validadorCompleto().validar("abc")).isTrue(); assertThat(Ejercicio29_InterfazFuncionalCustom.validadorCompleto().validar("ab")).isFalse(); assertThat(Ejercicio29_InterfazFuncionalCustom.validadorCompleto().validar(null)).isFalse(); }
}
