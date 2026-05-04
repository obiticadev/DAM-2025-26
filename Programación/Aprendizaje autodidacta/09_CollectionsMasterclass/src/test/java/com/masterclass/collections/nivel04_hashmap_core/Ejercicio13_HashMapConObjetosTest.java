package com.masterclass.collections.nivel04_hashmap_core;

import com.masterclass.collections.modelos.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 13 — HashMap con Objetos Propios")
class Ejercicio13_HashMapConObjetosTest {

    private ArrayList<Producto> lista;
    private HashMap<String, Producto> indice;

    @BeforeEach
    void setUp() {
        lista = new ArrayList<>();
        lista.add(new Producto("P001", "Teclado",     89.99, "Periféricos", "Hardware"));
        lista.add(new Producto("P002", "Ratón",       45.50, "Periféricos", "Hardware"));
        lista.add(new Producto("P003", "Monitor",    320.00, "Pantallas",   "Hardware"));
        lista.add(new Producto("P004", "Auriculares", 79.99, "Audio",       "Hardware"));
        lista.add(new Producto("P005", "Antivirus",   29.99, "Software",    "Licencia"));
        indice = Ejercicio13_HashMapConObjetos.indexarPorId(lista);
    }

    // ── indexarPorId ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("indexarPorId() crea un mapa con tantas entradas como productos")
    void indexarPorId_tamano() {
        assertThat(indice).hasSize(lista.size());
    }

    @Test
    @DisplayName("indexarPorId() usa el id del producto como clave")
    void indexarPorId_clavesCorrectas() {
        assertThat(indice).containsKeys("P001", "P002", "P003", "P004", "P005");
    }

    @Test
    @DisplayName("indexarPorId() asocia el objeto correcto a cada id")
    void indexarPorId_valoresCorrectos() {
        assertThat(indice.get("P003").getNombre()).isEqualTo("Monitor");
    }

    // ── buscarPorId ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("buscarPorId() retorna Optional con el producto si el id existe")
    void buscarPorId_existe() {
        Optional<Producto> resultado = Ejercicio13_HashMapConObjetos.buscarPorId(indice, "P001");
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNombre()).isEqualTo("Teclado");
    }

    @Test
    @DisplayName("buscarPorId() retorna Optional.empty() si el id no existe")
    void buscarPorId_noExiste() {
        assertThat(Ejercicio13_HashMapConObjetos.buscarPorId(indice, "P999")).isEmpty();
    }

    // ── actualizarPrecio ─────────────────────────────────────────────────────

    @Test
    @DisplayName("actualizarPrecio() retorna true y actualiza el precio si el id existe")
    void actualizarPrecio_existe() {
        boolean resultado = Ejercicio13_HashMapConObjetos.actualizarPrecio(indice, "P001", 99.99);
        assertThat(resultado).isTrue();
        assertThat(indice.get("P001").getPrecio()).isEqualTo(99.99);
    }

    @Test
    @DisplayName("actualizarPrecio() retorna false si el id no existe")
    void actualizarPrecio_noExiste() {
        assertThat(Ejercicio13_HashMapConObjetos.actualizarPrecio(indice, "P999", 10.0)).isFalse();
    }

    // ── filtrarPorCategoria ──────────────────────────────────────────────────

    @Test
    @DisplayName("filtrarPorCategoria() retorna solo los productos de esa categoría")
    void filtrarPorCategoria_retornaCorrectos() {
        HashMap<String, Producto> perifericos =
                Ejercicio13_HashMapConObjetos.filtrarPorCategoria(indice, "Periféricos");
        assertThat(perifericos).hasSize(2).containsKeys("P001", "P002");
    }

    @Test
    @DisplayName("filtrarPorCategoria() no modifica el índice original")
    void filtrarPorCategoria_noModificaOriginal() {
        int tamano = indice.size();
        Ejercicio13_HashMapConObjetos.filtrarPorCategoria(indice, "Periféricos");
        assertThat(indice).hasSize(tamano);
    }

    @Test
    @DisplayName("filtrarPorCategoria() retorna mapa vacío si no hay coincidencias")
    void filtrarPorCategoria_sinCoincidencias() {
        assertThat(Ejercicio13_HashMapConObjetos.filtrarPorCategoria(indice, "Juguetes")).isEmpty();
    }

    // ── eliminarPorCategoria ─────────────────────────────────────────────────

    @Test
    @DisplayName("eliminarPorCategoria() retorna el número de entradas eliminadas")
    void eliminarPorCategoria_conteoCorecto() {
        int eliminados = Ejercicio13_HashMapConObjetos.eliminarPorCategoria(indice, "Periféricos");
        assertThat(eliminados).isEqualTo(2);
    }

    @Test
    @DisplayName("eliminarPorCategoria() modifica el mapa eliminando las entradas")
    void eliminarPorCategoria_mapaModificado() {
        Ejercicio13_HashMapConObjetos.eliminarPorCategoria(indice, "Periféricos");
        assertThat(indice).doesNotContainKey("P001").doesNotContainKey("P002");
    }

    // ── productoMasCaro ──────────────────────────────────────────────────────

    @Test
    @DisplayName("productoMasCaro() retorna el producto con el precio más alto")
    void productoMasCaro_correcto() {
        Producto resultado = Ejercicio13_HashMapConObjetos.productoMasCaro(indice);
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo("P003"); // Monitor 320€
    }

    @Test
    @DisplayName("productoMasCaro() retorna null para mapa vacío")
    void productoMasCaro_mapaVacio() {
        assertThat(Ejercicio13_HashMapConObjetos.productoMasCaro(new HashMap<>())).isNull();
    }
}
