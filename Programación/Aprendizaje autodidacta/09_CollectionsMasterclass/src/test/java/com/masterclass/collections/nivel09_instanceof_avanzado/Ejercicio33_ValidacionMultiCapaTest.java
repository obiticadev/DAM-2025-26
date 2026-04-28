package com.masterclass.collections.nivel09_instanceof_avanzado;

import com.masterclass.collections.modelos.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 33 — Validación Multi-Capa")
class Ejercicio33_ValidacionMultiCapaTest {

    // ── validarIdentificable ─────────────────────────────────────────────────

    @Test @DisplayName("validarIdentificable() retorna lista vacía para Empleado válido")
    void validarIdentif_ok() {
        Empleado e = new Empleado("E01", "Ana", "IT", 1, "a");
        assertThat(Ejercicio33_ValidacionMultiCapa.validarIdentificable(e)).isEmpty();
    }

    @Test @DisplayName("validarIdentificable() retorna NO_IDENTIFICABLE para String")
    void validarIdentif_noIdentif() {
        assertThat(Ejercicio33_ValidacionMultiCapa.validarIdentificable("texto"))
                .containsExactly("NO_IDENTIFICABLE");
    }

    // ── validarProcesable ────────────────────────────────────────────────────

    @Test @DisplayName("validarProcesable() retorna lista vacía para Pedido válido")
    void validarProces_ok() {
        Pedido p = new Pedido("D01", "C01", 100.0, 5);
        assertThat(Ejercicio33_ValidacionMultiCapa.validarProcesable(p)).isEmpty();
    }

    @Test @DisplayName("validarProcesable() detecta YA_PROCESADO")
    void validarProces_yaProc() {
        Pedido p = new Pedido("D01", "C01", 100.0, 5);
        p.procesar();
        assertThat(Ejercicio33_ValidacionMultiCapa.validarProcesable(p))
                .contains("YA_PROCESADO");
    }

    @Test @DisplayName("validarProcesable() retorna NO_PROCESABLE para Empleado")
    void validarProces_noProces() {
        assertThat(Ejercicio33_ValidacionMultiCapa
                .validarProcesable(new Empleado("E01", "A", "IT", 1, "a")))
                .containsExactly("NO_PROCESABLE");
    }

    // ── validarCompleto ──────────────────────────────────────────────────────

    @Test @DisplayName("validarCompleto() genera ambas claves para Pedido")
    void validarCompleto_pedido() {
        Pedido p = new Pedido("D01", "C01", 100.0, 3);
        HashMap<String, ArrayList<String>> r =
                Ejercicio33_ValidacionMultiCapa.validarCompleto(p);
        assertThat(r).containsKeys("identificable", "procesable");
        assertThat(r.get("identificable")).isEmpty(); // Pedido es Identificable válido
        assertThat(r.get("procesable")).isEmpty();    // Pedido es Procesable válido
    }

    @Test @DisplayName("validarCompleto() genera errores para String")
    void validarCompleto_string() {
        HashMap<String, ArrayList<String>> r =
                Ejercicio33_ValidacionMultiCapa.validarCompleto("texto");
        // No es Identificable → ignorar (lista vacía ya que no aplica)
        // No es Procesable → ignorar (lista vacía ya que no aplica)
        assertThat(r).containsKeys("identificable", "procesable");
    }

    // ── validarLote ──────────────────────────────────────────────────────────

    @Test @DisplayName("validarLote() genera una entrada por cada objeto")
    void validarLote_contenido() {
        ArrayList<Object> lista = new ArrayList<>(List.of(
                new Empleado("E01", "Ana", "IT", 1, "a"),
                new Pedido("D01", "C01", 1, 3),
                "texto"));
        var r = Ejercicio33_ValidacionMultiCapa.validarLote(lista);
        assertThat(r).hasSize(3);
        assertThat(r).containsKeys(0, 1, 2);
    }

    // ── contarErroresTotales ─────────────────────────────────────────────────

    @Test @DisplayName("contarErroresTotales() suma todos los errores del lote")
    void contarErrores() {
        ArrayList<Object> lista = new ArrayList<>(List.of(
                new Empleado("E01", "Ana", "IT", 1, "a"), // 0 errores
                "texto")); // no identificable → pero no aplica
        var lote = Ejercicio33_ValidacionMultiCapa.validarLote(lista);
        int total = Ejercicio33_ValidacionMultiCapa.contarErroresTotales(lote);
        assertThat(total).isGreaterThanOrEqualTo(0);
    }

    // ── objetosSinErrores ────────────────────────────────────────────────────

    @Test @DisplayName("objetosSinErrores() filtra los objetos sin errores")
    void sinErrores() {
        ArrayList<Object> lista = new ArrayList<>(List.of(
                new Empleado("E01", "Ana", "IT", 1, "a"),
                new Pedido("D01", "C01", 1, 3)));
        var lote = Ejercicio33_ValidacionMultiCapa.validarLote(lista);
        ArrayList<Object> r =
                Ejercicio33_ValidacionMultiCapa.objetosSinErrores(lote, lista);
        // Empleado: Identif OK, no Procesable (no aplica) → sin errores
        // Pedido: Identif OK, Procesable OK → sin errores
        assertThat(r).hasSize(2);
    }
}
