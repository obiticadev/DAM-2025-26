package com.masterclass.nivel8_try_with_resources;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 8 - Ejercicio 61: Transaccion Simulada")
class Ejercicio61_TransaccionSimuladaTest {
    @Test @DisplayName("61.1 - commit exitoso") void ok() {
        var t = Ejercicio61_TransaccionSimulada.ejecutarTransaccion(List.of("INSERT","UPDATE"), true);
        assertThat(t.isCommitted()).isTrue();
        assertThat(t.getOperaciones()).contains("INSERT","UPDATE","COMMIT","CLOSED");
    }
    @Test @DisplayName("61.2 - rollback si falla") void fail() {
        var t = Ejercicio61_TransaccionSimulada.ejecutarTransaccion(List.of("INSERT"), false);
        assertThat(t.isCommitted()).isFalse();
        assertThat(t.getOperaciones()).contains("INSERT","ROLLBACK","CLOSED");
    }
}
