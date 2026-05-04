package com.masterclass.collections.nivel06_linkedhashmap_treemap;

import com.masterclass.collections.modelos.Producto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 20 — Integración LinkedHashMap + TreeMap")
class Ejercicio20_IntegracionMapasOrdenadosTest {

    // ── historialConsultas ───────────────────────────────────────────────────

    @Test
    @DisplayName("historialConsultas() cuenta correctamente las repeticiones")
    void historialConsultas_conteos() {
        String[] ids = {"P01", "P03", "P01", "P02", "P03", "P01"};
        LinkedHashMap<String, Integer> h =
                Ejercicio20_IntegracionMapasOrdenados.historialConsultas(ids);
        assertThat(h.get("P01")).isEqualTo(3);
        assertThat(h.get("P03")).isEqualTo(2);
        assertThat(h.get("P02")).isEqualTo(1);
    }

    @Test
    @DisplayName("historialConsultas() respeta el orden de primera aparición")
    void historialConsultas_ordenInsercion() {
        String[] ids = {"P03", "P01", "P02", "P01"};
        LinkedHashMap<String, Integer> h =
                Ejercicio20_IntegracionMapasOrdenados.historialConsultas(ids);
        assertThat(new ArrayList<>(h.keySet())).containsExactly("P03", "P01", "P02");
    }

    // ── catalogoOrdenadoPorPrecio ────────────────────────────────────────────

    @Test
    @DisplayName("catalogoOrdenadoPorPrecio() agrupa productos con el mismo precio")
    void catalogo_agrupacion() {
        ArrayList<Producto> prods = new ArrayList<>();
        prods.add(new Producto("P1", "Teclado", 45.0, "peri", "input"));
        prods.add(new Producto("P2", "Webcam",  45.0, "peri", "input"));
        prods.add(new Producto("P3", "Ratón",   25.0, "peri", "input"));

        TreeMap<Double, ArrayList<String>> cat =
                Ejercicio20_IntegracionMapasOrdenados.catalogoOrdenadoPorPrecio(prods);
        assertThat(cat.get(45.0)).containsExactlyInAnyOrder("Teclado", "Webcam");
        assertThat(cat.get(25.0)).containsExactly("Ratón");
    }

    @Test
    @DisplayName("catalogoOrdenadoPorPrecio() ordena por precio ascendente")
    void catalogo_orden() {
        ArrayList<Producto> prods = new ArrayList<>();
        prods.add(new Producto("P1", "Monitor", 299.0, "pant", "output"));
        prods.add(new Producto("P2", "Altavoz",  15.0, "audio", "output"));
        prods.add(new Producto("P3", "Teclado",  45.0, "peri", "input"));

        TreeMap<Double, ArrayList<String>> cat =
                Ejercicio20_IntegracionMapasOrdenados.catalogoOrdenadoPorPrecio(prods);
        assertThat(new ArrayList<>(cat.keySet())).containsExactly(15.0, 45.0, 299.0);
    }

    // ── productosMasBaratosQue ────────────────────────────────────────────────

    @Test
    @DisplayName("productosMasBaratosQue() retorna solo los de precio < máximo")
    void masBaratos_contenido() {
        TreeMap<Double, ArrayList<String>> cat = new TreeMap<>();
        cat.put(15.0, new ArrayList<>(java.util.List.of("Altavoz")));
        cat.put(45.0, new ArrayList<>(java.util.List.of("Teclado", "Webcam")));
        cat.put(299.0, new ArrayList<>(java.util.List.of("Monitor")));

        ArrayList<String> resultado =
                Ejercicio20_IntegracionMapasOrdenados.productosMasBaratosQue(cat, 46.0);
        assertThat(resultado).containsExactlyInAnyOrder("Altavoz", "Teclado", "Webcam");
    }

    @Test
    @DisplayName("productosMasBaratosQue() exclusión estricta del precio límite")
    void masBaratos_exclusionEstricta() {
        TreeMap<Double, ArrayList<String>> cat = new TreeMap<>();
        cat.put(45.0, new ArrayList<>(java.util.List.of("Teclado")));
        cat.put(45.01, new ArrayList<>(java.util.List.of("Webcam")));

        ArrayList<String> resultado =
                Ejercicio20_IntegracionMapasOrdenados.productosMasBaratosQue(cat, 45.0);
        assertThat(resultado).isEmpty();
    }

    // ── productoMasCaro ──────────────────────────────────────────────────────

    @Test
    @DisplayName("productoMasCaro() retorna el nombre del primer producto del precio más alto")
    void masCaro_resultado() {
        TreeMap<Double, ArrayList<String>> cat = new TreeMap<>();
        cat.put(15.0, new ArrayList<>(java.util.List.of("Altavoz")));
        cat.put(299.0, new ArrayList<>(java.util.List.of("Monitor", "TV")));

        assertThat(Ejercicio20_IntegracionMapasOrdenados.productoMasCaro(cat))
                .isEqualTo("Monitor");
    }

    @Test
    @DisplayName("productoMasCaro() retorna null si el catálogo está vacío")
    void masCaro_vacio() {
        assertThat(Ejercicio20_IntegracionMapasOrdenados
                .productoMasCaro(new TreeMap<>())).isNull();
    }

    // ── convertirHistorialATreeMap ───────────────────────────────────────────

    @Test
    @DisplayName("convertirHistorialATreeMap() agrupa IDs por número de consultas")
    void convertir_agrupacion() {
        LinkedHashMap<String, Integer> h = new LinkedHashMap<>();
        h.put("P01", 3);
        h.put("P02", 1);
        h.put("P03", 3);

        TreeMap<Integer, ArrayList<String>> ranking =
                Ejercicio20_IntegracionMapasOrdenados.convertirHistorialATreeMap(h);
        assertThat(ranking.get(3)).containsExactlyInAnyOrder("P01", "P03");
        assertThat(ranking.get(1)).containsExactly("P02");
    }

    @Test
    @DisplayName("convertirHistorialATreeMap() ordena de mayor a menor")
    void convertir_ordenDesc() {
        LinkedHashMap<String, Integer> h = new LinkedHashMap<>();
        h.put("P01", 5);
        h.put("P02", 1);
        h.put("P03", 10);

        TreeMap<Integer, ArrayList<String>> ranking =
                Ejercicio20_IntegracionMapasOrdenados.convertirHistorialATreeMap(h);
        assertThat(new ArrayList<>(ranking.keySet())).containsExactly(10, 5, 1);
    }

    // ── topNProductosMasConsultados ───────────────────────────────────────────

    @Test
    @DisplayName("topNProductosMasConsultados() retorna los N primeros por consultas desc")
    void topN_resultado() {
        TreeMap<Integer, ArrayList<String>> ranking = new TreeMap<>(java.util.Comparator.reverseOrder());
        ranking.put(10, new ArrayList<>(java.util.List.of("P03")));
        ranking.put(5, new ArrayList<>(java.util.List.of("P01")));
        ranking.put(1, new ArrayList<>(java.util.List.of("P02", "P04")));

        ArrayList<String> top2 =
                Ejercicio20_IntegracionMapasOrdenados.topNProductosMasConsultados(ranking, 2);
        assertThat(top2).containsExactly("P03", "P01");
    }

    @Test
    @DisplayName("topNProductosMasConsultados() retorna todos si N > total de productos")
    void topN_menosQueN() {
        TreeMap<Integer, ArrayList<String>> ranking = new TreeMap<>(java.util.Comparator.reverseOrder());
        ranking.put(5, new ArrayList<>(java.util.List.of("P01")));

        ArrayList<String> top5 =
                Ejercicio20_IntegracionMapasOrdenados.topNProductosMasConsultados(ranking, 5);
        assertThat(top5).containsExactly("P01");
    }
}
