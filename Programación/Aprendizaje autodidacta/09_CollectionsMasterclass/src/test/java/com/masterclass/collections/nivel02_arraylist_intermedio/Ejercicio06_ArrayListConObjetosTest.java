package com.masterclass.collections.nivel02_arraylist_intermedio;

import com.masterclass.collections.modelos.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 06 — ArrayList con Objetos Propios")
class Ejercicio06_ArrayListConObjetosTest {

    private ArrayList<Producto> catalogo;

    @BeforeEach
    void setUp() {
        catalogo = new ArrayList<>();
        catalogo.add(new Producto("P001", "Teclado Mecánico", 89.99, "Periféricos", "Hardware"));
        catalogo.add(new Producto("P002", "Ratón Ergonómico", 45.50, "Periféricos", "Hardware"));
        catalogo.add(new Producto("P003", "Monitor 27\"", 320.00, "Pantallas", "Hardware"));
        catalogo.add(new Producto("P004", "Auriculares BT", 79.99, "Audio", "Hardware"));
        catalogo.add(new Producto("P005", "Antivirus Pro", 29.99, "Software", "Licencia"));
        catalogo.add(new Producto("P006", "Silla Gaming", 199.00, "Mobiliario", "Accesorio"));
    }

    // ── filtrarPorPrecioMaximo ──────────────────────────────────────────────

    @Test
    @DisplayName("filtrarPorPrecioMaximo() retorna solo productos con precio <= umbral")
    void filtrarPorPrecioMaximo_retornaCorrectamente() {
        ArrayList<Producto> resultado =
                Ejercicio06_ArrayListConObjetos.filtrarPorPrecioMaximo(catalogo, 80.0);
        // P002=45.50, P004=79.99, P005=29.99 son <= 80
        assertThat(resultado).hasSize(3);
        assertThat(resultado).extracting(Producto::getId)
                .containsExactlyInAnyOrder("P002", "P004", "P005");
    }

    @Test
    @DisplayName("filtrarPorPrecioMaximo() no modifica el catálogo original")
    void filtrarPorPrecioMaximo_noModificaOriginal() {
        int tamano = catalogo.size();
        Ejercicio06_ArrayListConObjetos.filtrarPorPrecioMaximo(catalogo, 50.0);
        assertThat(catalogo).hasSize(tamano);
    }

    @Test
    @DisplayName("filtrarPorPrecioMaximo() retorna lista vacía si ninguno cumple")
    void filtrarPorPrecioMaximo_ninguno_retornaVacia() {
        ArrayList<Producto> resultado =
                Ejercicio06_ArrayListConObjetos.filtrarPorPrecioMaximo(catalogo, 5.0);
        assertThat(resultado).isEmpty();
    }

    // ── buscarPorNombre ─────────────────────────────────────────────────────

    @Test
    @DisplayName("buscarPorNombre() retorna Optional con el producto si existe")
    void buscarPorNombre_encuentraProducto() {
        Optional<Producto> resultado =
                Ejercicio06_ArrayListConObjetos.buscarPorNombre(catalogo, "Monitor 27\"");
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getId()).isEqualTo("P003");
    }

    @Test
    @DisplayName("buscarPorNombre() retorna Optional.empty() si no existe")
    void buscarPorNombre_noExiste_retornaVacio() {
        Optional<Producto> resultado =
                Ejercicio06_ArrayListConObjetos.buscarPorNombre(catalogo, "Tablet Inexistente");
        assertThat(resultado).isEmpty();
    }

    // ── ordenarPorPrecioAscendente ──────────────────────────────────────────

    @Test
    @DisplayName("ordenarPorPrecioAscendente() ordena de menor a mayor precio")
    void ordenarPorPrecioAscendente_ordenCorrecto() {
        ArrayList<Producto> copia = new ArrayList<>(catalogo);
        Ejercicio06_ArrayListConObjetos.ordenarPorPrecioAscendente(copia);
        for (int i = 1; i < copia.size(); i++) {
            assertThat(copia.get(i).getPrecio())
                    .isGreaterThanOrEqualTo(copia.get(i - 1).getPrecio());
        }
    }

    // ── encontrarMasBarato ──────────────────────────────────────────────────

    @Test
    @DisplayName("encontrarMasBarato() retorna el producto con menor precio")
    void encontrarMasBarato_retornaElMinimo() {
        Producto resultado = Ejercicio06_ArrayListConObjetos.encontrarMasBarato(catalogo);
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo("P005"); // 29.99
    }

    @Test
    @DisplayName("encontrarMasBarato() retorna null para catálogo vacío")
    void encontrarMasBarato_catalgoVacio_retornaNull() {
        assertThat(Ejercicio06_ArrayListConObjetos.encontrarMasBarato(new ArrayList<>())).isNull();
    }

    // ── resumenPorCategoria ─────────────────────────────────────────────────

    @Test
    @DisplayName("resumenPorCategoria() incluye todas las categorías presentes")
    void resumenPorCategoria_incluyeTodasLasCategorias() {
        String resumen = Ejercicio06_ArrayListConObjetos.resumenPorCategoria(catalogo);
        assertThat(resumen).contains("Periféricos", "Pantallas", "Audio", "Software", "Mobiliario");
    }

    @Test
    @DisplayName("resumenPorCategoria() menciona el conteo correcto de productos por categoría")
    void resumenPorCategoria_conteoCorrecto() {
        String resumen = Ejercicio06_ArrayListConObjetos.resumenPorCategoria(catalogo);
        // Periféricos tiene 2 productos (P001 y P002)
        assertThat(resumen).contains("Periféricos").contains("2");
    }

    // ── contienePorId ────────────────────────────────────────────────────────

    @Test
    @DisplayName("contienePorId() retorna true si el id existe en el catálogo")
    void contienePorId_existeId_retornaTrue() {
        assertThat(Ejercicio06_ArrayListConObjetos.contienePorId(catalogo, "P003")).isTrue();
    }

    @Test
    @DisplayName("contienePorId() retorna false si el id no existe")
    void contienePorId_noExisteId_retornaFalse() {
        assertThat(Ejercicio06_ArrayListConObjetos.contienePorId(catalogo, "P999")).isFalse();
    }
}
