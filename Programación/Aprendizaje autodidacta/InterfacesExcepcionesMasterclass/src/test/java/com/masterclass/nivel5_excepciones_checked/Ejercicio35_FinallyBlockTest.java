package com.masterclass.nivel5_excepciones_checked;

import com.masterclass.nivel5_excepciones_checked.Ejercicio35_FinallyBlock.RecursoSimulado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 5 - Ejercicio 35: Finally Block")
class Ejercicio35_FinallyBlockTest {
    @Test @DisplayName("35.1 - recurso se cierra en caso exitoso") void cierraOk() {
        var r = new RecursoSimulado();
        assertThat(Ejercicio35_FinallyBlock.usarRecurso(r, "test")).isEqualTo("TEST");
        assertThat(r.estaAbierto()).isFalse();
        assertThat(r.getLog()).contains("ABIERTO", "CERRADO");
    }
    @Test @DisplayName("35.2 - recurso se cierra en caso de error") void cierraError() {
        var r = new RecursoSimulado();
        assertThat(Ejercicio35_FinallyBlock.usarRecurso(r, null)).isEqualTo("ERROR");
        assertThat(r.estaAbierto()).isFalse();
        assertThat(r.getLog()).contains("ABIERTO", "CERRADO");
    }
}
