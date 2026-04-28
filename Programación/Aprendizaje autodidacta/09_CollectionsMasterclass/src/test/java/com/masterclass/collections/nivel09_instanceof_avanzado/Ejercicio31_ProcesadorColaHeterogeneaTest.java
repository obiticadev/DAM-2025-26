package com.masterclass.collections.nivel09_instanceof_avanzado;

import com.masterclass.collections.modelos.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 31 — Procesador de Cola Heterogénea")
class Ejercicio31_ProcesadorColaHeterogeneaTest {

    @Test @DisplayName("procesarColaConPrioridad() procesa alta prioridad primero")
    void procesarCola() {
        ArrayList<Object> cola = new ArrayList<>(List.of(
                new Pedido("D01", "C01", 100.0, 7),
                new Pedido("D02", "C02", 200.0, 2),
                new Evento("EV01", "WARN", "Aviso", 8, "sistema")));
        ArrayList<String> r = Ejercicio31_ProcesadorColaHeterogenea.procesarColaConPrioridad(cola);
        // D01(7) y EV01(8) son prio>=5, D02(2) es prio<5
        // Orden: primero los de alta prio, luego baja
        // EV01 no es Identificable → ANONIMO
        assertThat(r.subList(0, 2)).containsExactlyInAnyOrder("D01", "ANONIMO");
        assertThat(r.get(2)).isEqualTo("D02");
    }

    @Test @DisplayName("generarInformeAuditoria() genera claves AUDIT-0, AUDIT-1...")
    void auditoria() {
        ArrayList<Object> lista = new ArrayList<>(List.of(
                new Empleado("E01", "Ana", "IT", 45000, "admin"),
                new Evento("EV01", "ERR", "Fallo", 1, "sistema"),
                new Producto("P01", "Teclado", 45.0, "peri", "input")));
        HashMap<String, String> r = Ejercicio31_ProcesadorColaHeterogenea.generarInformeAuditoria(lista);
        assertThat(r).hasSize(2).containsKeys("AUDIT-0", "AUDIT-1");
        assertThat(r.get("AUDIT-0")).contains("admin");
    }

    @Test @DisplayName("encontrarDuplicadosPorClase() detecta clases repetidas")
    void duplicados() {
        ArrayList<Object> lista = new ArrayList<>(List.of(
                new Pedido("D01", "C01", 100.0, 1),
                new Pedido("D02", "C02", 200.0, 2),
                new Producto("P01", "A", 10, "x", "y")));
        HashSet<String> r = Ejercicio31_ProcesadorColaHeterogenea.encontrarDuplicadosPorClase(lista);
        assertThat(r).contains("Pedido");
        assertThat(r).doesNotContain("Producto");
    }

    @Test @DisplayName("contarInterfacesPromedio() calcula correctamente")
    void promedio() {
        // Empleado=2, Producto=2, Pedido=2, Evento=2, String=0 → 8/5=1.6
        ArrayList<Object> lista = new ArrayList<>(List.of(
                new Empleado("E01", "A", "IT", 1, "a"),
                new Producto("P01", "B", 1, "x", "y"),
                new Pedido("D01", "C01", 1, 1),
                new Evento("EV01", "E", "F", 1, "s"),
                "texto"));
        assertThat(Ejercicio31_ProcesadorColaHeterogenea.contarInterfacesPromedio(lista))
                .isCloseTo(1.6, within(0.01));
    }

    @Test @DisplayName("extraerNombresOrdenados() retorna nombres alfabéticamente")
    void nombresOrdenados() {
        ArrayList<Object> lista = new ArrayList<>(List.of(
                new Empleado("E01", "Zara", "IT", 1, "a"),
                new Producto("P01", "Alfombra", 1, "x", "y"),
                new Pedido("D01", "C01", 1, 1)));
        ArrayList<String> r = Ejercicio31_ProcesadorColaHeterogenea.extraerNombresOrdenados(lista);
        assertThat(r).containsExactly("Alfombra", "Pedido-D01", "Zara");
    }
}
