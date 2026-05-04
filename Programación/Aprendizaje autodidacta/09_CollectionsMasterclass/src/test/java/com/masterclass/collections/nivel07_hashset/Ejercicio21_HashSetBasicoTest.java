package com.masterclass.collections.nivel07_hashset;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 21 — HashSet: Operaciones Básicas y Unicidad")
class Ejercicio21_HashSetBasicoTest {

    // ── crearSetDesdeArray ───────────────────────────────────────────────────

    @Test
    @DisplayName("crearSetDesdeArray() elimina duplicados del array")
    void crearSet_eliminaDuplicados() {
        String[] entrada = {"A", "B", "A", "C", "B"};
        HashSet<String> set = Ejercicio21_HashSetBasico.crearSetDesdeArray(entrada);
        assertThat(set).hasSize(3).containsExactlyInAnyOrder("A", "B", "C");
    }

    @Test
    @DisplayName("crearSetDesdeArray() con array sin duplicados retorna set completo")
    void crearSet_sinDuplicados() {
        String[] entrada = {"X", "Y", "Z"};
        HashSet<String> set = Ejercicio21_HashSetBasico.crearSetDesdeArray(entrada);
        assertThat(set).hasSize(3);
    }

    // ── agregarElemento ──────────────────────────────────────────────────────

    @Test
    @DisplayName("agregarElemento() retorna true para elemento nuevo")
    void agregar_nuevo() {
        HashSet<String> set = new HashSet<>();
        assertThat(Ejercicio21_HashSetBasico.agregarElemento(set, "A")).isTrue();
        assertThat(set).contains("A");
    }

    @Test
    @DisplayName("agregarElemento() retorna false para elemento duplicado")
    void agregar_duplicado() {
        HashSet<String> set = new HashSet<>(List.of("A"));
        assertThat(Ejercicio21_HashSetBasico.agregarElemento(set, "A")).isFalse();
        assertThat(set).hasSize(1);
    }

    // ── eliminarSiExiste ─────────────────────────────────────────────────────

    @Test
    @DisplayName("eliminarSiExiste() retorna true y elimina si existe")
    void eliminar_existe() {
        HashSet<String> set = new HashSet<>(List.of("A", "B"));
        assertThat(Ejercicio21_HashSetBasico.eliminarSiExiste(set, "A")).isTrue();
        assertThat(set).doesNotContain("A");
    }

    @Test
    @DisplayName("eliminarSiExiste() retorna false si no existe")
    void eliminar_noExiste() {
        HashSet<String> set = new HashSet<>(List.of("A"));
        assertThat(Ejercicio21_HashSetBasico.eliminarSiExiste(set, "Z")).isFalse();
    }

    // ── eliminarDuplicadosDeLista ────────────────────────────────────────────

    @Test
    @DisplayName("eliminarDuplicadosDeLista() retorna lista sin duplicados")
    void eliminarDups_contenido() {
        ArrayList<String> lista = new ArrayList<>(List.of("A", "B", "A", "C", "B"));
        ArrayList<String> resultado = Ejercicio21_HashSetBasico.eliminarDuplicadosDeLista(lista);
        assertThat(resultado).hasSize(3)
                .containsExactlyInAnyOrder("A", "B", "C");
    }

    @Test
    @DisplayName("eliminarDuplicadosDeLista() no modifica la lista original")
    void eliminarDups_noModificaOriginal() {
        ArrayList<String> lista = new ArrayList<>(List.of("A", "A", "A"));
        Ejercicio21_HashSetBasico.eliminarDuplicadosDeLista(lista);
        assertThat(lista).hasSize(3);
    }

    // ── contarElementosUnicos ────────────────────────────────────────────────

    @Test
    @DisplayName("contarElementosUnicos() cuenta correctamente")
    void contarUnicos() {
        assertThat(Ejercicio21_HashSetBasico
                .contarElementosUnicos(new String[]{"A", "B", "A", "C", "B"}))
                .isEqualTo(3);
    }

    @Test
    @DisplayName("contarElementosUnicos() retorna 0 para array vacío")
    void contarUnicos_vacio() {
        assertThat(Ejercicio21_HashSetBasico.contarElementosUnicos(new String[]{}))
                .isEqualTo(0);
    }

    // ── todosUnicos ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("todosUnicos() retorna true si no hay duplicados")
    void todosUnicos_sinDuplicados() {
        assertThat(Ejercicio21_HashSetBasico
                .todosUnicos(new ArrayList<>(List.of("A", "B", "C")))).isTrue();
    }

    @Test
    @DisplayName("todosUnicos() retorna false si hay duplicados")
    void todosUnicos_conDuplicados() {
        assertThat(Ejercicio21_HashSetBasico
                .todosUnicos(new ArrayList<>(List.of("A", "B", "A")))).isFalse();
    }
}
