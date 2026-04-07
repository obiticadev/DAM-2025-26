package com.masterclass.nivel9_proyecto_integrador;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 9 - Ejercicio 68: Mini Framework")
class Ejercicio68_MiniFrameworkTest {
    @Test @DisplayName("68.1 - cadena exitosa") void ok() {
        var p1 = Ejercicio68_MiniFramework.crearProcesador("upper", (String s) -> s.toUpperCase());
        var p2 = Ejercicio68_MiniFramework.crearProcesador("exclaim", (String s) -> s + "!");
        Map<String, Object> res = Ejercicio68_MiniFramework.ejecutarCadena("hola", List.of(p1, p2));
        assertThat(res.get("resultado")).isEqualTo("HOLA!");
        var report = (Ejercicio68_MiniFramework.ProcessingReport) res.get("report");
        assertThat(report.isSuccess()).isTrue();
        assertThat(report.getStepsExecuted()).containsExactly("upper","exclaim");
    }
    @Test @DisplayName("68.2 - cadena con fallo parcial") void fail() {
        var p1 = Ejercicio68_MiniFramework.crearProcesador("boom", (String s) -> { throw new RuntimeException("!"); });
        var p2 = Ejercicio68_MiniFramework.crearProcesador("upper", (String s) -> s.toUpperCase());
        Map<String, Object> res = Ejercicio68_MiniFramework.ejecutarCadena("hola", List.of(p1, p2));
        var report = (Ejercicio68_MiniFramework.ProcessingReport) res.get("report");
        assertThat(report.getErrors()).hasSize(1);
        assertThat(res.get("resultado")).isEqualTo("HOLA");
    }
}
