package com.masterclass.collections.nivel05_hashmap_avanzado;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 14 — Frecuencias y Conteos")
class Ejercicio14_FrecuenciasYConteosTest {

    private ArrayList<String> listaDePrueba() {
        return new ArrayList<>(List.of(
                "Java", "Python", "Java", "Go", "Python", "Java", "Rust", "Go", "Java"));
        // Java=4, Python=2, Go=2, Rust=1
    }

    // ── contarFrecuencias ────────────────────────────────────────────────────

    @Test
    @DisplayName("contarFrecuencias() cuenta correctamente las apariciones de cada elemento")
    void contarFrecuencias_correcto() {
        HashMap<String, Integer> freq = Ejercicio14_FrecuenciasYConteos.contarFrecuencias(listaDePrueba());
        assertThat(freq.get("Java")).isEqualTo(4);
        assertThat(freq.get("Python")).isEqualTo(2);
        assertThat(freq.get("Go")).isEqualTo(2);
        assertThat(freq.get("Rust")).isEqualTo(1);
    }

    @Test
    @DisplayName("contarFrecuencias() con lista vacía retorna mapa vacío")
    void contarFrecuencias_listaVacia() {
        assertThat(Ejercicio14_FrecuenciasYConteos.contarFrecuencias(new ArrayList<>())).isEmpty();
    }

    @Test
    @DisplayName("contarFrecuencias() con todos elementos únicos da frecuencia 1 a cada uno")
    void contarFrecuencias_todosUnicos() {
        ArrayList<String> unicos = new ArrayList<>(List.of("A", "B", "C"));
        HashMap<String, Integer> freq = Ejercicio14_FrecuenciasYConteos.contarFrecuencias(unicos);
        assertThat(freq.values()).containsOnly(1);
    }

    // ── palabraMasFrecuente ──────────────────────────────────────────────────

    @Test
    @DisplayName("palabraMasFrecuente() retorna el elemento que más aparece")
    void palabraMasFrecuente_correcto() {
        String resultado = Ejercicio14_FrecuenciasYConteos.palabraMasFrecuente(listaDePrueba());
        assertThat(resultado).isEqualTo("Java");
    }

    @Test
    @DisplayName("palabraMasFrecuente() con un solo elemento lo retorna")
    void palabraMasFrecuente_unElemento() {
        ArrayList<String> uno = new ArrayList<>(List.of("Solo"));
        assertThat(Ejercicio14_FrecuenciasYConteos.palabraMasFrecuente(uno)).isEqualTo("Solo");
    }

    // ── elementosUnicos ──────────────────────────────────────────────────────

    @Test
    @DisplayName("elementosUnicos() retorna solo los elementos con frecuencia 1")
    void elementosUnicos_correcto() {
        ArrayList<String> unicos = Ejercicio14_FrecuenciasYConteos.elementosUnicos(listaDePrueba());
        assertThat(unicos).containsExactlyInAnyOrder("Rust");
    }

    @Test
    @DisplayName("elementosUnicos() retorna vacío si no hay elementos con frecuencia 1")
    void elementosUnicos_sinUnicos() {
        ArrayList<String> lista = new ArrayList<>(List.of("A", "A", "B", "B"));
        assertThat(Ejercicio14_FrecuenciasYConteos.elementosUnicos(lista)).isEmpty();
    }

    // ── combinarFrecuencias ──────────────────────────────────────────────────

    @Test
    @DisplayName("combinarFrecuencias() suma las frecuencias de ambos mapas")
    void combinarFrecuencias_suma() {
        HashMap<String, Integer> f1 = new HashMap<>();
        f1.put("A", 2); f1.put("B", 3);
        HashMap<String, Integer> f2 = new HashMap<>();
        f2.put("B", 1); f2.put("C", 4);

        HashMap<String, Integer> resultado =
                Ejercicio14_FrecuenciasYConteos.combinarFrecuencias(f1, f2);

        assertThat(resultado.get("A")).isEqualTo(2);
        assertThat(resultado.get("B")).isEqualTo(4); // 3+1
        assertThat(resultado.get("C")).isEqualTo(4);
    }

    @Test
    @DisplayName("combinarFrecuencias() no modifica los mapas originales")
    void combinarFrecuencias_noModificaOriginales() {
        HashMap<String, Integer> f1 = new HashMap<>();
        f1.put("X", 5);
        HashMap<String, Integer> f2 = new HashMap<>();
        f2.put("Y", 3);
        Ejercicio14_FrecuenciasYConteos.combinarFrecuencias(f1, f2);
        assertThat(f1).doesNotContainKey("Y");
        assertThat(f2).doesNotContainKey("X");
    }

    // ── histograma ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("histograma() contiene cada elemento con tantos asteriscos como su frecuencia")
    void histograma_contieneAsteriscos() {
        String resultado = Ejercicio14_FrecuenciasYConteos.histograma(listaDePrueba());
        // Java tiene 4 apariciones → "****"
        assertThat(resultado).contains("Java").contains("****");
        // Rust tiene 1 aparición → "*"
        assertThat(resultado).contains("Rust").contains("*");
    }

    @Test
    @DisplayName("histograma() pone el elemento más frecuente primero")
    void histograma_ordenDescendente() {
        String resultado = Ejercicio14_FrecuenciasYConteos.histograma(listaDePrueba());
        int posJava = resultado.indexOf("Java");
        int posRust = resultado.indexOf("Rust");
        // Java (4) debe aparecer antes que Rust (1)
        assertThat(posJava).isLessThan(posRust);
    }
}
