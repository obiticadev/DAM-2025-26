package com.masterclass.nivel8_try_with_resources;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 8 - Ejercicio 62: Decorador Closeable")
class Ejercicio62_DecoradorCloseableTest {
    @Test @DisplayName("62.1 - logger basico cierra") void base() throws Exception {
        var l = Ejercicio62_DecoradorCloseable.crearLogger();
        l.log("hola"); l.close();
        assertThat(l.getLogs()).containsExactly("hola","--- LOG CERRADO ---");
    }
    @Test @DisplayName("62.2 - logger mayusculas") void upper() throws Exception {
        var base = Ejercicio62_DecoradorCloseable.crearLogger();
        var l = Ejercicio62_DecoradorCloseable.crearLoggerMayusculas(base);
        l.log("hola"); l.close();
        assertThat(base.getLogs()).contains("HOLA","--- LOG CERRADO ---");
    }
}
