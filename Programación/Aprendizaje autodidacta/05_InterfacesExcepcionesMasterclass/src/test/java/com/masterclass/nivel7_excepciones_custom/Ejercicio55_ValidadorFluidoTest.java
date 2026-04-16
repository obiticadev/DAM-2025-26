package com.masterclass.nivel7_excepciones_custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 7 - Ejercicio 55: Validador Fluido")
class Ejercicio55_ValidadorFluidoTest {
    @Test @DisplayName("55.1 - todo valido") void ok() { assertThat(Ejercicio55_ValidadorFluido.validarUsuario("Juan","j@b.com",25).isValid()).isTrue(); }
    @Test @DisplayName("55.2 - todo invalido acumula errores") void allBad() { var r = Ejercicio55_ValidadorFluido.validarUsuario("","nomail",-1); assertThat(r.isValid()).isFalse(); assertThat(r.getErrores()).hasSize(3); }
    @Test @DisplayName("55.3 - solo email invalido") void emailBad() { var r = Ejercicio55_ValidadorFluido.validarUsuario("Juan","nomail",25); assertThat(r.getErrores()).hasSize(1); }
}
