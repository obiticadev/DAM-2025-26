package com.masterclass.collections.nivel07_hashset;

import com.masterclass.collections.modelos.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 24 — HashSet con Objetos de Dominio")
class Ejercicio24_HashSetConObjetosTest {

    private Producto p1, p2, p3, p1dup;

    @BeforeEach
    void setUp() {
        p1    = new Producto("P01", "Teclado", 45.0, "periféricos", "input");
        p2    = new Producto("P02", "Ratón",   25.0, "periféricos", "input");
        p3    = new Producto("P03", "Monitor", 299.0, "pantallas",  "output");
        p1dup = new Producto("P01", "Teclado Dup", 50.0, "periféricos", "input");
    }

    // ── crearCatalogoUnico ───────────────────────────────────────────────────

    @Test
    @DisplayName("crearCatalogoUnico() elimina duplicados por id")
    void crearCatalogo_eliminaDuplicados() {
        ArrayList<Producto> lista = new ArrayList<>(List.of(p1, p2, p1dup, p3));
        HashSet<Producto> catalogo = Ejercicio24_HashSetConObjetos.crearCatalogoUnico(lista);
        assertThat(catalogo).hasSize(3);
    }

    // ── existeProducto ───────────────────────────────────────────────────────

    @Test
    @DisplayName("existeProducto() retorna true para id existente")
    void existeProducto_existe() {
        HashSet<Producto> catalogo = new HashSet<>(List.of(p1, p2));
        assertThat(Ejercicio24_HashSetConObjetos.existeProducto(catalogo, "P01")).isTrue();
    }

    @Test
    @DisplayName("existeProducto() retorna false para id inexistente")
    void existeProducto_noExiste() {
        HashSet<Producto> catalogo = new HashSet<>(List.of(p1));
        assertThat(Ejercicio24_HashSetConObjetos.existeProducto(catalogo, "P99")).isFalse();
    }

    // ── productosComunesEntre ────────────────────────────────────────────────

    @Test
    @DisplayName("productosComunesEntre() retorna intersección correcta")
    void comunes_contenido() {
        HashSet<Producto> catA = new HashSet<>(List.of(p1, p2));
        HashSet<Producto> catB = new HashSet<>(List.of(p2, p3));
        HashSet<Producto> comunes =
                Ejercicio24_HashSetConObjetos.productosComunesEntre(catA, catB);
        assertThat(comunes).hasSize(1).contains(p2);
    }

    @Test
    @DisplayName("productosComunesEntre() no modifica los catálogos originales")
    void comunes_noModifica() {
        HashSet<Producto> catA = new HashSet<>(List.of(p1, p2));
        HashSet<Producto> catB = new HashSet<>(List.of(p2, p3));
        Ejercicio24_HashSetConObjetos.productosComunesEntre(catA, catB);
        assertThat(catA).hasSize(2);
        assertThat(catB).hasSize(2);
    }

    // ── productosExclusivos ──────────────────────────────────────────────────

    @Test
    @DisplayName("productosExclusivos() retorna diferencia A − B")
    void exclusivos_contenido() {
        HashSet<Producto> catA = new HashSet<>(List.of(p1, p2, p3));
        HashSet<Producto> catB = new HashSet<>(List.of(p2));
        HashSet<Producto> exclusivos =
                Ejercicio24_HashSetConObjetos.productosExclusivos(catA, catB);
        assertThat(exclusivos).hasSize(2).contains(p1, p3);
    }

    // ── filtrarPorCategoria ──────────────────────────────────────────────────

    @Test
    @DisplayName("filtrarPorCategoria() retorna solo productos de la categoría indicada")
    void filtrar_contenido() {
        HashSet<Producto> catalogo = new HashSet<>(List.of(p1, p2, p3));
        HashSet<Producto> perifericos =
                Ejercicio24_HashSetConObjetos.filtrarPorCategoria(catalogo, "periféricos");
        assertThat(perifericos).hasSize(2).contains(p1, p2);
    }

    @Test
    @DisplayName("filtrarPorCategoria() retorna vacío si no hay coincidencias")
    void filtrar_sinResultados() {
        HashSet<Producto> catalogo = new HashSet<>(List.of(p1));
        assertThat(Ejercicio24_HashSetConObjetos
                .filtrarPorCategoria(catalogo, "audio")).isEmpty();
    }

    // ── detectarProductosDuplicadosEnLista ────────────────────────────────────

    @Test
    @DisplayName("detectarProductosDuplicadosEnLista() retorna IDs de duplicados")
    void detectarDups_contenido() {
        ArrayList<Producto> lista = new ArrayList<>(List.of(p1, p2, p1dup, p3, p2));
        ArrayList<String> dups =
                Ejercicio24_HashSetConObjetos.detectarProductosDuplicadosEnLista(lista);
        assertThat(dups).containsExactlyInAnyOrder("P01", "P02");
    }

    @Test
    @DisplayName("detectarProductosDuplicadosEnLista() retorna vacío si no hay duplicados")
    void detectarDups_sinDuplicados() {
        ArrayList<Producto> lista = new ArrayList<>(List.of(p1, p2, p3));
        assertThat(Ejercicio24_HashSetConObjetos
                .detectarProductosDuplicadosEnLista(lista)).isEmpty();
    }
}
