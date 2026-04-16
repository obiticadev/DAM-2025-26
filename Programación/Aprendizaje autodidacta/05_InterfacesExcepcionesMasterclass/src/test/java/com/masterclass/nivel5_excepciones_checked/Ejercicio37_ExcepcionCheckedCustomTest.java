package com.masterclass.nivel5_excepciones_checked;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 5 - Ejercicio 37: Excepcion Checked Custom")
class Ejercicio37_ExcepcionCheckedCustomTest {
    @Test @DisplayName("37.1 - retirar exitoso") void ok() throws Exception { assertThat(Ejercicio37_ExcepcionCheckedCustom.retirar(100, 30)).isEqualTo(70.0); }
    @Test @DisplayName("37.2 - retirar fondos insuficientes lanza excepcion") void fail() { assertThatThrownBy(() -> Ejercicio37_ExcepcionCheckedCustom.retirar(50, 100)).isInstanceOf(Ejercicio37_ExcepcionCheckedCustom.CuentaBancariaException.class); }
    @Test @DisplayName("37.3 - excepcion tiene saldo y monto") void datos() {
        assertThatThrownBy(() -> Ejercicio37_ExcepcionCheckedCustom.retirar(50, 100))
            .isInstanceOfSatisfying(Ejercicio37_ExcepcionCheckedCustom.CuentaBancariaException.class, e -> {
                assertThat(e.getSaldo()).isEqualTo(50.0);
                assertThat(e.getMontoSolicitado()).isEqualTo(100.0);
            });
    }
    @Test @DisplayName("37.4 - procesarRetiro exitoso") void procOk() { assertThat(Ejercicio37_ExcepcionCheckedCustom.procesarRetiro(100, 30)).contains("Retiro exitoso"); }
    @Test @DisplayName("37.5 - procesarRetiro fallido") void procFail() { assertThat(Ejercicio37_ExcepcionCheckedCustom.procesarRetiro(50, 100)).contains("Fondos insuficientes"); }
}
