package com.masterclass.nivel9_proyecto_integrador;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.function.Function;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 9 - Ejercicio 67: Pipeline con Errores")
class Ejercicio67_PipelineConErroresTest {
    @Test @DisplayName("67.1 - pipeline sin errores") void ok() {
        List<Function<String, String>> pasos = List.of(String::toUpperCase, s -> s + "!");
        var res = Ejercicio67_PipelineConErrores.procesarPipeline(List.of("hola","mundo"), pasos);
        assertThat(res.getValue()).containsExactly("HOLA!","MUNDO!");
        assertThat(res.hasErrors()).isFalse();
    }
    @Test @DisplayName("67.2 - pipeline con error parcial") void err() {
        List<Function<String, String>> pasos = List.of(
            s -> { if (s.equals("malo")) throw new RuntimeException("fallo"); return s.toUpperCase(); }
        );
        var res = Ejercicio67_PipelineConErrores.procesarPipeline(List.of("bueno","malo"), pasos);
        assertThat(res.hasErrors()).isTrue();
        assertThat(res.getValue()).contains("BUENO");
    }
}
