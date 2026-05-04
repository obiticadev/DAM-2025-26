package com.masterclass.collections.nivel10_instanceof_collections;

import com.masterclass.collections.modelos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 35 — Catálogo Inteligente")
class Ejercicio35_CatalogoInteligenteTest {

    private ArrayList<Object> lista;

    @BeforeEach void setUp() {
        lista = new ArrayList<>(List.of(
                new Empleado("E01", "Ana", "IT", 45000, "admin"),
                new Empleado("E02", "Luis", "RRHH", 38000, "admin"),
                new Producto("P01", "Teclado", 45.0, "periféricos", "input"),
                new Pedido("D01", "C01", 150.0, 7),
                new Pedido("D02", "C02", 200.0, 3),
                new Evento("EV01", "ERROR", "Fallo", 9, "sistema")));
    }

    @Test @DisplayName("construirCatalogoPorPrioridad() ordena desc y agrupa")
    void catalogoPrioridad() {
        TreeMap<Integer, ArrayList<Object>> cat =
                Ejercicio35_CatalogoInteligente.construirCatalogoPorPrioridad(lista);
        ArrayList<Integer> claves = new ArrayList<>(cat.keySet());
        assertThat(claves.get(0)).isEqualTo(9);  // máxima prioridad primero
        assertThat(cat.get(7)).hasSize(1);        // D01
        assertThat(cat.get(3)).hasSize(1);        // D02
    }

    @Test @DisplayName("obtenerTopNPorPrioridad() retorna los N más prioritarios")
    void topN() {
        TreeMap<Integer, ArrayList<Object>> cat =
                Ejercicio35_CatalogoInteligente.construirCatalogoPorPrioridad(lista);
        ArrayList<Object> top2 =
                Ejercicio35_CatalogoInteligente.obtenerTopNPorPrioridad(cat, 2);
        assertThat(top2).hasSize(2);
    }

    @Test @DisplayName("estadisticasPorClase() genera count e interfaces correctos")
    void estadisticas() {
        HashMap<String, HashMap<String, Integer>> r =
                Ejercicio35_CatalogoInteligente.estadisticasPorClase(lista);
        assertThat(r.get("Empleado").get("count")).isEqualTo(2);
        assertThat(r.get("Empleado").get("interfaces")).isEqualTo(2);
        assertThat(r.get("Producto").get("count")).isEqualTo(1);
        assertThat(r.get("Producto").get("interfaces")).isEqualTo(2);
    }

    @Test @DisplayName("buscarPorCriterio() filtra con AND lógico")
    void buscar_and() {
        HashMap<String, String> criterios = new HashMap<>();
        criterios.put("interfaz", "Identificable");
        criterios.put("clase", "Empleado");
        ArrayList<Object> r =
                Ejercicio35_CatalogoInteligente.buscarPorCriterio(lista, criterios);
        assertThat(r).hasSize(2); // 2 empleados
    }

    @Test @DisplayName("buscarPorCriterio() filtra por departamento")
    void buscar_depto() {
        HashMap<String, String> criterios = new HashMap<>();
        criterios.put("departamento", "IT");
        ArrayList<Object> r =
                Ejercicio35_CatalogoInteligente.buscarPorCriterio(lista, criterios);
        assertThat(r).hasSize(1); // solo Ana
    }

    @Test @DisplayName("generarResumenEjecutivo() contiene todas las métricas")
    void resumen() {
        HashMap<String, Object> r =
                Ejercicio35_CatalogoInteligente.generarResumenEjecutivo(lista);
        assertThat((Integer) r.get("total")).isEqualTo(6);
        assertThat((Integer) r.get("procesables_pendientes")).isEqualTo(3); // 2 pedidos + 1 evento
        assertThat((Double) r.get("salario_medio")).isCloseTo(41500.0, within(0.01));

        @SuppressWarnings("unchecked")
        HashSet<String> cats = (HashSet<String>) r.get("categorias");
        assertThat(cats).contains("periféricos");

        @SuppressWarnings("unchecked")
        HashMap<String, Integer> porClase = (HashMap<String, Integer>) r.get("por_clase");
        assertThat(porClase.get("Empleado")).isEqualTo(2);
    }
}
