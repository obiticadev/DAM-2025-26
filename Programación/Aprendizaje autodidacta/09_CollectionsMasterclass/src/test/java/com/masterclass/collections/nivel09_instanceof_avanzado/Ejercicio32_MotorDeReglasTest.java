package com.masterclass.collections.nivel09_instanceof_avanzado;

import com.masterclass.collections.modelos.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 32 — Motor de Reglas por Interfaz")
class Ejercicio32_MotorDeReglasTest {

    // ── aplicarReglasSalario ─────────────────────────────────────────────────

    @Test @DisplayName("aplicarReglasSalario() aplica bonus IT del 10%")
    void salario_bonusIT() {
        Empleado e = new Empleado("E01", "Ana", "IT", 40000, "admin");
        ArrayList<Object> lista = new ArrayList<>(List.of(e));
        Ejercicio32_MotorDeReglas.aplicarReglasSalario(lista);
        assertThat(e.getSalario()).isCloseTo(44000.0, within(0.01));
    }

    @Test @DisplayName("aplicarReglasSalario() aplica bonus RRHH del 5%")
    void salario_bonusRRHH() {
        Empleado e = new Empleado("E02", "Luis", "RRHH", 40000, "admin");
        ArrayList<Object> lista = new ArrayList<>(List.of(e));
        Ejercicio32_MotorDeReglas.aplicarReglasSalario(lista);
        assertThat(e.getSalario()).isCloseTo(42000.0, within(0.01));
    }

    @Test @DisplayName("aplicarReglasSalario() no modifica otros departamentos")
    void salario_otroDepto() {
        Empleado e = new Empleado("E03", "Mar", "Ventas", 40000, "admin");
        ArrayList<Object> lista = new ArrayList<>(List.of(e));
        int mod = Ejercicio32_MotorDeReglas.aplicarReglasSalario(lista);
        assertThat(e.getSalario()).isEqualTo(40000.0);
        assertThat(mod).isEqualTo(0);
    }

    @Test @DisplayName("aplicarReglasSalario() retorna conteo correcto de modificados")
    void salario_conteo() {
        ArrayList<Object> lista = new ArrayList<>(List.of(
                new Empleado("E01", "Ana", "IT", 40000, "admin"),
                new Empleado("E02", "Luis", "RRHH", 35000, "admin"),
                new Producto("P01", "X", 1, "x", "y")));
        assertThat(Ejercicio32_MotorDeReglas.aplicarReglasSalario(lista)).isEqualTo(2);
    }

    // ── generarEtiqueta ──────────────────────────────────────────────────────

    @Test @DisplayName("generarEtiqueta() genera etiqueta completa para Empleado")
    void etiqueta_empleado() {
        Empleado e = new Empleado("E01", "Ana", "IT", 45000, "admin");
        String etq = Ejercicio32_MotorDeReglas.generarEtiqueta(e);
        assertThat(etq).contains("[Empleado]").contains("id=E01").contains("by=admin");
    }

    @Test @DisplayName("generarEtiqueta() genera etiqueta para Producto (Identif + Clasif)")
    void etiqueta_producto() {
        Producto p = new Producto("P01", "Teclado", 45.0, "periféricos", "input");
        String etq = Ejercicio32_MotorDeReglas.generarEtiqueta(p);
        assertThat(etq).contains("[Producto]").contains("id=P01").contains("cat=periféricos");
        assertThat(etq).doesNotContain("prio=").doesNotContain("by=");
    }

    @Test @DisplayName("generarEtiqueta() genera SIN_CONTRATOS para String")
    void etiqueta_sinContratos() {
        assertThat(Ejercicio32_MotorDeReglas.generarEtiqueta("texto"))
                .contains("[String]").contains("SIN_CONTRATOS");
    }

    // ── generarEtiquetasMasivas ──────────────────────────────────────────────

    @Test @DisplayName("generarEtiquetasMasivas() genera una etiqueta por objeto")
    void etiquetasMasivas() {
        ArrayList<Object> lista = new ArrayList<>(List.of(
                new Empleado("E01", "Ana", "IT", 1, "a"),
                new Producto("P01", "X", 1, "c", "t")));
        ArrayList<String> r = Ejercicio32_MotorDeReglas.generarEtiquetasMasivas(lista);
        assertThat(r).hasSize(2);
        assertThat(r.get(0)).contains("[Empleado]");
        assertThat(r.get(1)).contains("[Producto]");
    }

    // ── crearRegistroDeCapacidades ───────────────────────────────────────────

    @Test @DisplayName("crearRegistroDeCapacidades() registra interfaces de cada clase")
    void registro_contenido() {
        ArrayList<Object> lista = new ArrayList<>(List.of(
                new Empleado("E01", "A", "IT", 1, "a"),
                new Producto("P01", "B", 1, "c", "t")));
        HashMap<String, ArrayList<String>> r =
                Ejercicio32_MotorDeReglas.crearRegistroDeCapacidades(lista);
        assertThat(r.get("Empleado")).containsExactlyInAnyOrder("Identificable", "Auditable");
        assertThat(r.get("Producto")).containsExactlyInAnyOrder("Identificable", "Clasificable");
    }

    // ── procesarYRecopilarResultados ──────────────────────────────────────────

    @Test @DisplayName("procesarYRecopilarResultados() marca PROCESADO y YA_PROCESADO")
    void procesarRecopilar() {
        Pedido p1 = new Pedido("D01", "C01", 100.0, 3);
        Pedido p2 = new Pedido("D02", "C02", 200.0, 5);
        p2.procesar(); // ya procesado
        ArrayList<Object> lista = new ArrayList<>(List.of(p1, p2, "texto"));
        ArrayList<String> r =
                Ejercicio32_MotorDeReglas.procesarYRecopilarResultados(lista);
        assertThat(r).hasSize(2);
        assertThat(r.get(0)).startsWith("PROCESADO:");
        assertThat(r.get(1)).startsWith("YA_PROCESADO:");
    }

    // ── simularPipelineDeTransformaciones ─────────────────────────────────────

    @Test @DisplayName("simularPipeline() produce resultados, procesados y descartados")
    void pipeline() {
        ArrayList<Object> lista = new ArrayList<>(List.of(
                new Empleado("E01", "Ana", "IT", 1, "admin"),   // Identif+Audit
                new Producto("P01", "X", 1, "c", "t"),          // Identif (no Audit)
                new Pedido("D01", "C01", 1, 1),                  // Identif+Proces
                new Evento("EV01", "E", "F", 1, "s"),            // no Identif → descartado
                "texto"));                                        // descartado

        HashMap<String, Object> r =
                Ejercicio32_MotorDeReglas.simularPipelineDeTransformaciones(lista);

        @SuppressWarnings("unchecked")
        ArrayList<String> resultados = (ArrayList<String>) r.get("resultados");
        assertThat(resultados).hasSize(3);
        assertThat(resultados).anyMatch(s -> s.startsWith("ENRIQUECIDO:E01:admin"));
        assertThat(resultados).anyMatch(s -> s.startsWith("BASICO:P01"));
        assertThat((Integer) r.get("procesados")).isEqualTo(1); // Pedido
        assertThat((Integer) r.get("descartados")).isEqualTo(2); // Evento + texto
    }
}
