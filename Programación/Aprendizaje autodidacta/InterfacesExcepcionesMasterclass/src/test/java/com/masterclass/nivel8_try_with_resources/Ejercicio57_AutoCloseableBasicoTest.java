package com.masterclass.nivel8_try_with_resources;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 8 - Ejercicio 57: AutoCloseable Basico")
class Ejercicio57_AutoCloseableBasicoTest {
    @Test @DisplayName("57.1 - ejecuta y cierra") void ok() {
        var c = new Ejercicio57_AutoCloseableBasico.ConexionSimulada();
        var res = Ejercicio57_AutoCloseableBasico.ejecutarSQL(c, "SELECT 1");
        assertThat(res).isEqualTo("Resultado de: SELECT 1");
        assertThat(c.estaAbierta()).isFalse();
        assertThat(c.getLog()).contains("ABIERTA","CERRADA");
    }
}
