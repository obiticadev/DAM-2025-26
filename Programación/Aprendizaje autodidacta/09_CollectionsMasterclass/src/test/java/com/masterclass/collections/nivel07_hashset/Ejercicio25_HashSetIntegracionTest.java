package com.masterclass.collections.nivel07_hashset;

import com.masterclass.collections.modelos.Producto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 25 — HashSet + HashMap: Integración")
class Ejercicio25_HashSetIntegracionTest {

    // ── categoriasDisponibles ────────────────────────────────────────────────

    @Test
    @DisplayName("categoriasDisponibles() retorna categorías únicas")
    void categorias_contenido() {
        ArrayList<Producto> prods = new ArrayList<>(List.of(
                new Producto("P1", "A", 10, "peri", "in"),
                new Producto("P2", "B", 20, "peri", "in"),
                new Producto("P3", "C", 30, "audio", "out")));
        HashSet<String> cats = Ejercicio25_HashSetIntegracion.categoriasDisponibles(prods);
        assertThat(cats).containsExactlyInAnyOrder("peri", "audio");
    }

    // ── productosPorCategoriaConUnicidad ──────────────────────────────────────

    @Test
    @DisplayName("productosPorCategoria() agrupa correctamente sin duplicados")
    void porCategoria_agrupacion() {
        Producto p1 = new Producto("P01", "Teclado", 45, "peri", "in");
        Producto p2 = new Producto("P02", "Ratón",   25, "peri", "in");
        Producto p3 = new Producto("P03", "Monitor", 299, "pant", "out");
        Producto p1dup = new Producto("P01", "Teclado2", 50, "peri", "in");

        ArrayList<Producto> prods = new ArrayList<>(List.of(p1, p2, p3, p1dup));
        HashMap<String, HashSet<Producto>> resultado =
                Ejercicio25_HashSetIntegracion.productosPorCategoriaConUnicidad(prods);
        assertThat(resultado.get("peri")).hasSize(2); // P01 y P02, no P01dup
        assertThat(resultado.get("pant")).hasSize(1);
    }

    // ── clientesConPedidosMultiples ───────────────────────────────────────────

    @Test
    @DisplayName("clientesConPedidosMultiples() detecta clientes con productos repetidos")
    void clientesMultiples_contenido() {
        HashMap<String, ArrayList<String>> pedidos = new HashMap<>();
        pedidos.put("C1", new ArrayList<>(List.of("P01", "P02", "P01")));
        pedidos.put("C2", new ArrayList<>(List.of("P02", "P03")));
        pedidos.put("C3", new ArrayList<>(List.of("P01", "P01", "P01")));

        HashSet<String> resultado =
                Ejercicio25_HashSetIntegracion.clientesConPedidosMultiples(pedidos);
        assertThat(resultado).containsExactlyInAnyOrder("C1", "C3");
    }

    // ── productosEnComun ─────────────────────────────────────────────────────

    @Test
    @DisplayName("productosEnComun() retorna productos pedidos por TODOS los clientes")
    void enComun_contenido() {
        HashMap<String, ArrayList<String>> pedidos = new HashMap<>();
        pedidos.put("C1", new ArrayList<>(List.of("P01", "P02", "P03")));
        pedidos.put("C2", new ArrayList<>(List.of("P02", "P03")));
        pedidos.put("C3", new ArrayList<>(List.of("P02", "P03", "P04")));

        HashSet<String> comun =
                Ejercicio25_HashSetIntegracion.productosEnComun(pedidos);
        assertThat(comun).containsExactlyInAnyOrder("P02", "P03");
    }

    @Test
    @DisplayName("productosEnComun() retorna vacío si mapa vacío")
    void enComun_vacio() {
        assertThat(Ejercicio25_HashSetIntegracion
                .productosEnComun(new HashMap<>())).isEmpty();
    }

    // ── historialUnicoOrdenado ────────────────────────────────────────────────

    @Test
    @DisplayName("historialUnicoOrdenado() preserva orden de primera aparición")
    void historial_orden() {
        ArrayList<String> acciones = new ArrayList<>(
                List.of("login", "view", "login", "purchase", "view", "logout"));
        ArrayList<String> resultado =
                Ejercicio25_HashSetIntegracion.historialUnicoOrdenado(acciones);
        assertThat(resultado).containsExactly("login", "view", "purchase", "logout");
    }

    // ── analisisCompletoCatalogo ──────────────────────────────────────────────

    @Test
    @DisplayName("analisisCompletoCatalogo() calcula correctamente las 4 métricas")
    void analisis_correcto() {
        Producto p1 = new Producto("P01", "A", 10, "x", "y");
        Producto p2 = new Producto("P02", "B", 20, "x", "y");
        Producto p3 = new Producto("P03", "C", 30, "x", "y");

        ArrayList<Producto> catA = new ArrayList<>(List.of(p1, p2));
        ArrayList<Producto> catB = new ArrayList<>(List.of(p2, p3));

        HashMap<String, Object> resultado =
                Ejercicio25_HashSetIntegracion.analisisCompletoCatalogo(catA, catB);

        assertThat((HashSet<?>) resultado.get("soloA")).hasSize(1);
        assertThat((HashSet<?>) resultado.get("soloB")).hasSize(1);
        assertThat((HashSet<?>) resultado.get("ambos")).hasSize(1);
        assertThat((Integer) resultado.get("totalUnicos")).isEqualTo(3);
    }
}
