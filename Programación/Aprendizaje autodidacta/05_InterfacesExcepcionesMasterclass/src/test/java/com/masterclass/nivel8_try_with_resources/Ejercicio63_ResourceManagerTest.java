package com.masterclass.nivel8_try_with_resources;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicBoolean;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 8 - Ejercicio 63: Resource Manager")
class Ejercicio63_ResourceManagerTest {
    @Test @DisplayName("63.1 - cierra todos los recursos") void ok() throws Exception {
        var closed1 = new AtomicBoolean(false); var closed2 = new AtomicBoolean(false);
        var rm = new Ejercicio63_ResourceManager.ResourceManager();
        rm.registrar(() -> closed1.set(true));
        rm.registrar(() -> closed2.set(true));
        rm.close();
        assertThat(closed1.get()).isTrue(); assertThat(closed2.get()).isTrue();
    }
    @Test @DisplayName("63.2 - captura errores al cerrar") void errores() throws Exception {
        var rm = new Ejercicio63_ResourceManager.ResourceManager();
        rm.registrar(() -> { throw new Exception("fail1"); });
        rm.registrar(() -> {});
        rm.close();
        assertThat(rm.getErrores()).hasSize(1);
    }
}
