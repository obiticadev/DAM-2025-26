package com.masterclass.nivel7_excepciones_custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 7 - Ejercicio 49: Jerarquia Excepciones")
class Ejercicio49_JerarquiaExcepcionesTest {
    @Test @DisplayName("49.1 - buscarUsuario valido") void ok() throws Exception { assertThat(Ejercicio49_JerarquiaExcepciones.buscarUsuario(50)).isEqualTo("Usuario-50"); }
    @Test @DisplayName("49.2 - id invalido lanza ValidationException") void val() { assertThatThrownBy(() -> Ejercicio49_JerarquiaExcepciones.buscarUsuario(0)).isInstanceOf(Ejercicio49_JerarquiaExcepciones.ValidationException.class); }
    @Test @DisplayName("49.3 - id no encontrado lanza NotFoundException") void nf() { assertThatThrownBy(() -> Ejercicio49_JerarquiaExcepciones.buscarUsuario(200)).isInstanceOf(Ejercicio49_JerarquiaExcepciones.NotFoundException.class); }
    @Test @DisplayName("49.4 - buscarSeguro con error") void segErr() { assertThat(Ejercicio49_JerarquiaExcepciones.buscarSeguro(0)).contains("[VALIDATION]"); }
    @Test @DisplayName("49.5 - buscarSeguro ok") void segOk() { assertThat(Ejercicio49_JerarquiaExcepciones.buscarSeguro(50)).isEqualTo("Usuario-50"); }
}
