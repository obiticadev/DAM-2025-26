package com.masterclass.nivel7_excepciones_custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 7 - Ejercicio 50: Excepcion Con Enum")
class Ejercicio50_ExcepcionConEnumTest {
    @Test @DisplayName("50.1 - login ok") void ok() { assertThat(Ejercicio50_ExcepcionConEnum.login("Juan")).isEqualTo("Bienvenido, Juan"); }
    @Test @DisplayName("50.2 - login null lanza INVALID_INPUT") void nulo() {
        assertThatThrownBy(() -> Ejercicio50_ExcepcionConEnum.login(null))
            .isInstanceOfSatisfying(Ejercicio50_ExcepcionConEnum.BusinessException.class, e -> assertThat(e.getCode()).isEqualTo(Ejercicio50_ExcepcionConEnum.ErrorCode.INVALID_INPUT));
    }
    @Test @DisplayName("50.3 - login admin lanza UNAUTHORIZED") void admin() {
        assertThatThrownBy(() -> Ejercicio50_ExcepcionConEnum.login("admin"))
            .isInstanceOfSatisfying(Ejercicio50_ExcepcionConEnum.BusinessException.class, e -> assertThat(e.getCode()).isEqualTo(Ejercicio50_ExcepcionConEnum.ErrorCode.UNAUTHORIZED));
    }
}
