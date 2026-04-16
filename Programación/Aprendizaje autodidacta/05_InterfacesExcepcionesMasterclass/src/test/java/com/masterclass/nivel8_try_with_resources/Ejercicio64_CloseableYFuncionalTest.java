package com.masterclass.nivel8_try_with_resources;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicBoolean;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 8 - Ejercicio 64: Closeable y Funcional")
class Ejercicio64_CloseableYFuncionalTest {
    @Test @DisplayName("64.1 - ejecuta y cierra recurso") void ok() throws Exception {
        var cerrado = new AtomicBoolean(false);
        AutoCloseable recurso = () -> cerrado.set(true);
        var res = Ejercicio64_CloseableYFuncional.usarRecurso(recurso, r -> "OK");
        assertThat(res).isEqualTo("OK");
        assertThat(cerrado.get()).isTrue();
    }
}
