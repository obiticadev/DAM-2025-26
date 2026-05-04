package com.masterclass.collections.nivel02_arraylist_intermedio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 04 — Ordenación y Comparator")
class Ejercicio04_OrdenacionYComparatorTest {

    private ArrayList<String> listaDeFrutas() {
        return new ArrayList<>(List.of("Kiwi", "Aguacate", "Uva", "Cereza", "Higo", "Fresa"));
    }

    // ── ordenarAlfabeticamente ──────────────────────────────────────────────

    @Test
    @DisplayName("ordenarAlfabeticamente() ordena A → Z")
    void ordenarAlfabeticamente_ordenAscendente() {
        ArrayList<String> lista = listaDeFrutas();
        Ejercicio04_OrdenacionYComparator.ordenarAlfabeticamente(lista);
        assertThat(lista).isSortedAccordingTo(String::compareTo);
    }

    @Test
    @DisplayName("ordenarAlfabeticamente() conserva todos los elementos")
    void ordenarAlfabeticamente_conservaElementos() {
        ArrayList<String> lista = listaDeFrutas();
        Ejercicio04_OrdenacionYComparator.ordenarAlfabeticamente(lista);
        assertThat(lista).containsExactlyInAnyOrder("Kiwi", "Aguacate", "Uva", "Cereza", "Higo", "Fresa");
    }

    // ── ordenarPorLongitud ──────────────────────────────────────────────────

    @Test
    @DisplayName("ordenarPorLongitud() pone los Strings más cortos primero")
    void ordenarPorLongitud_masCortoPrimero() {
        ArrayList<String> lista = listaDeFrutas();
        Ejercicio04_OrdenacionYComparator.ordenarPorLongitud(lista);
        // Cada elemento debe tener longitud >= al anterior
        for (int i = 1; i < lista.size(); i++) {
            assertThat(lista.get(i).length()).isGreaterThanOrEqualTo(lista.get(i - 1).length());
        }
    }

    // ── ordenarPorLongitudLuegoAlfabetico ───────────────────────────────────

    @Test
    @DisplayName("ordenarPorLongitudLuegoAlfabetico() ordena primero por longitud luego A→Z")
    void ordenarPorLongitudLuegoAlfabetico_ordenCompuesto() {
        ArrayList<String> lista = new ArrayList<>(List.of("Kiwi", "Uva", "Fresa", "Higo", "Mango"));
        // Longitudes: Uva=3, Kiwi=4, Higo=4, Fresa=5, Mango=5
        Ejercicio04_OrdenacionYComparator.ordenarPorLongitudLuegoAlfabetico(lista);

        // Primero los de longitud 3, luego 4 (alfabético), luego 5 (alfabético)
        assertThat(lista.get(0)).isEqualTo("Uva");       // largo=3
        assertThat(lista.get(1)).isEqualTo("Higo");      // largo=4, H < K
        assertThat(lista.get(2)).isEqualTo("Kiwi");      // largo=4, K > H
        assertThat(lista.get(3)).isEqualTo("Fresa");     // largo=5, F < M
        assertThat(lista.get(4)).isEqualTo("Mango");     // largo=5, M > F
    }

    // ── ordenarDescendente ──────────────────────────────────────────────────

    @Test
    @DisplayName("ordenarDescendente() ordena Z → A")
    void ordenarDescendente_ordenInverso() {
        ArrayList<String> lista = listaDeFrutas();
        Ejercicio04_OrdenacionYComparator.ordenarDescendente(lista);
        assertThat(lista).isSortedAccordingTo((a, b) -> b.compareTo(a));
    }

    // ── minimo ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("minimo() retorna el String más corto de la lista")
    void minimo_retornaMasCorto() {
        // "Uva"=3 es la más corta
        ArrayList<String> lista = listaDeFrutas();
        String resultado = Ejercicio04_OrdenacionYComparator.minimo(lista);
        assertThat(resultado.length()).isLessThanOrEqualTo(
                lista.stream().mapToInt(String::length).min().orElse(0));
    }

    @Test
    @DisplayName("minimo() no modifica la lista original")
    void minimo_noModificaLista() {
        ArrayList<String> lista = listaDeFrutas();
        ArrayList<String> copia = new ArrayList<>(lista);
        Ejercicio04_OrdenacionYComparator.minimo(lista);
        assertThat(lista).containsExactlyElementsOf(copia);
    }

    // ── maximo ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("maximo() retorna el String más largo de la lista")
    void maximo_retornaMasLargo() {
        // "Aguacate"=8 es el más largo
        ArrayList<String> lista = listaDeFrutas();
        String resultado = Ejercicio04_OrdenacionYComparator.maximo(lista);
        assertThat(resultado.length()).isGreaterThanOrEqualTo(
                lista.stream().mapToInt(String::length).max().orElse(0));
    }
}
