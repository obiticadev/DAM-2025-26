package com.masterclass.nivel6_excepciones_unchecked;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 6 - Ejercicio 43: Unchecked Custom")
class Ejercicio43_UncheckedCustomTest {
    private final String[] pares = {"host=localhost", "port=8080", "debug=true"};
    @Test @DisplayName("43.1 - obtenerConfiguracion existe") void ok() { assertThat(Ejercicio43_UncheckedCustom.obtenerConfiguracion(pares, "host")).isEqualTo("localhost"); }
    @Test @DisplayName("43.2 - obtenerConfiguracion no existe lanza") void fail() { assertThatThrownBy(() -> Ejercicio43_UncheckedCustom.obtenerConfiguracion(pares, "url")).isInstanceOf(Ejercicio43_UncheckedCustom.ConfiguracionException.class); }
    @Test @DisplayName("43.3 - excepcion tiene clave") void clave() {
        assertThatThrownBy(() -> Ejercicio43_UncheckedCustom.obtenerConfiguracion(pares, "url"))
            .isInstanceOfSatisfying(Ejercicio43_UncheckedCustom.ConfiguracionException.class, e -> assertThat(e.getClave()).isEqualTo("url"));
    }
    @Test @DisplayName("43.4 - obtenerODefault con clave existente") void defOk() { assertThat(Ejercicio43_UncheckedCustom.obtenerODefault(pares, "port", "3000")).isEqualTo("8080"); }
    @Test @DisplayName("43.5 - obtenerODefault con clave inexistente") void defFail() { assertThat(Ejercicio43_UncheckedCustom.obtenerODefault(pares, "url", "default")).isEqualTo("default"); }
}
