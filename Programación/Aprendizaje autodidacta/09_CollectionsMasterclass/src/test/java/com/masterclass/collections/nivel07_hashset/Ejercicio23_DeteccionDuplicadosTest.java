package com.masterclass.collections.nivel07_hashset;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 23 — Detección de Duplicados")
class Ejercicio23_DeteccionDuplicadosTest {

    // ── encontrarDuplicados ──────────────────────────────────────────────────

    @Test
    @DisplayName("encontrarDuplicados() retorna los elementos repetidos sin duplicar")
    void encontrarDuplicados_contenido() {
        ArrayList<String> lista = new ArrayList<>(List.of("A", "B", "A", "C", "B", "D", "A"));
        ArrayList<String> dups = Ejercicio23_DeteccionDuplicados.encontrarDuplicados(lista);
        assertThat(dups).containsExactlyInAnyOrder("A", "B");
    }

    @Test
    @DisplayName("encontrarDuplicados() retorna lista vacía si no hay duplicados")
    void encontrarDuplicados_sinDuplicados() {
        ArrayList<String> lista = new ArrayList<>(List.of("A", "B", "C"));
        assertThat(Ejercicio23_DeteccionDuplicados.encontrarDuplicados(lista)).isEmpty();
    }

    // ── primeraRepeticion ────────────────────────────────────────────────────

    @Test
    @DisplayName("primeraRepeticion() retorna el primer elemento que se repite")
    void primeraRepeticion_existe() {
        String[] arr = {"X", "Y", "Z", "Y", "X"};
        assertThat(Ejercicio23_DeteccionDuplicados.primeraRepeticion(arr)).isEqualTo("Y");
    }

    @Test
    @DisplayName("primeraRepeticion() retorna null si no hay repeticiones")
    void primeraRepeticion_sinRepeticiones() {
        String[] arr = {"A", "B", "C"};
        assertThat(Ejercicio23_DeteccionDuplicados.primeraRepeticion(arr)).isNull();
    }

    // ── elementosExclusivos ──────────────────────────────────────────────────

    @Test
    @DisplayName("elementosExclusivos() retorna elementos de A que no están en B")
    void elementosExclusivos_contenido() {
        ArrayList<String> a = new ArrayList<>(List.of("A", "B", "C", "D"));
        ArrayList<String> b = new ArrayList<>(List.of("B", "D", "E"));
        ArrayList<String> exclusivos = Ejercicio23_DeteccionDuplicados.elementosExclusivos(a, b);
        assertThat(exclusivos).containsExactlyInAnyOrder("A", "C");
    }

    @Test
    @DisplayName("elementosExclusivos() no incluye duplicados en el resultado")
    void elementosExclusivos_sinDuplicados() {
        ArrayList<String> a = new ArrayList<>(List.of("A", "A", "B"));
        ArrayList<String> b = new ArrayList<>(List.of("B"));
        ArrayList<String> exclusivos = Ejercicio23_DeteccionDuplicados.elementosExclusivos(a, b);
        assertThat(exclusivos).containsExactly("A");
    }

    // ── contarRepetidosUnicos ────────────────────────────────────────────────

    @Test
    @DisplayName("contarRepetidosUnicos() cuenta correctamente los distintos repetidos")
    void contarRepetidos_contenido() {
        ArrayList<String> lista = new ArrayList<>(List.of("A", "B", "A", "C", "B", "A"));
        assertThat(Ejercicio23_DeteccionDuplicados.contarRepetidosUnicos(lista)).isEqualTo(2);
    }

    @Test
    @DisplayName("contarRepetidosUnicos() retorna 0 si no hay repetidos")
    void contarRepetidos_cero() {
        ArrayList<String> lista = new ArrayList<>(List.of("A", "B", "C"));
        assertThat(Ejercicio23_DeteccionDuplicados.contarRepetidosUnicos(lista)).isEqualTo(0);
    }

    // ── eliminarDuplicadosPreservandoOrden ────────────────────────────────────

    @Test
    @DisplayName("eliminarDuplicadosPreservandoOrden() preserva el orden de primera aparición")
    void eliminarDupsOrden_contenido() {
        ArrayList<String> lista = new ArrayList<>(List.of("C", "A", "B", "A", "C", "D"));
        ArrayList<String> resultado =
                Ejercicio23_DeteccionDuplicados.eliminarDuplicadosPreservandoOrden(lista);
        assertThat(resultado).containsExactly("C", "A", "B", "D");
    }

    @Test
    @DisplayName("eliminarDuplicadosPreservandoOrden() no modifica la lista original")
    void eliminarDupsOrden_noModifica() {
        ArrayList<String> lista = new ArrayList<>(List.of("A", "A"));
        Ejercicio23_DeteccionDuplicados.eliminarDuplicadosPreservandoOrden(lista);
        assertThat(lista).hasSize(2);
    }

    // ── encontrarParConSuma ──────────────────────────────────────────────────

    @Test
    @DisplayName("encontrarParConSuma() retorna true si existe un par válido")
    void parConSuma_existe() {
        assertThat(Ejercicio23_DeteccionDuplicados
                .encontrarParConSuma(new int[]{2, 7, 11, 15}, 9)).isTrue();
    }

    @Test
    @DisplayName("encontrarParConSuma() retorna false si no existe par")
    void parConSuma_noExiste() {
        assertThat(Ejercicio23_DeteccionDuplicados
                .encontrarParConSuma(new int[]{2, 7, 11, 15}, 100)).isFalse();
    }

    @Test
    @DisplayName("encontrarParConSuma() funciona con array vacío")
    void parConSuma_vacio() {
        assertThat(Ejercicio23_DeteccionDuplicados
                .encontrarParConSuma(new int[]{}, 5)).isFalse();
    }
}
