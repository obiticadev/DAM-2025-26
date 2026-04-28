package com.masterclass.collections.nivel07_hashset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 22 — Álgebra de Conjuntos")
class Ejercicio22_AlgebraDeConjuntosTest {

    private HashSet<String> a;
    private HashSet<String> b;

    @BeforeEach
    void setUp() {
        a = new HashSet<>(List.of("1", "2", "3", "4"));
        b = new HashSet<>(List.of("3", "4", "5", "6"));
    }

    // ── union ────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("union() contiene todos los elementos de ambos sets")
    void union_contenido() {
        HashSet<String> resultado = Ejercicio22_AlgebraDeConjuntos.union(a, b);
        assertThat(resultado).containsExactlyInAnyOrder("1", "2", "3", "4", "5", "6");
    }

    @Test
    @DisplayName("union() no modifica los sets originales")
    void union_noModifica() {
        Ejercicio22_AlgebraDeConjuntos.union(a, b);
        assertThat(a).hasSize(4);
        assertThat(b).hasSize(4);
    }

    // ── interseccion ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("interseccion() contiene solo los elementos comunes")
    void interseccion_contenido() {
        HashSet<String> resultado = Ejercicio22_AlgebraDeConjuntos.interseccion(a, b);
        assertThat(resultado).containsExactlyInAnyOrder("3", "4");
    }

    @Test
    @DisplayName("interseccion() retorna vacío si no hay elementos comunes")
    void interseccion_vacia() {
        HashSet<String> x = new HashSet<>(List.of("A", "B"));
        HashSet<String> y = new HashSet<>(List.of("C", "D"));
        assertThat(Ejercicio22_AlgebraDeConjuntos.interseccion(x, y)).isEmpty();
    }

    @Test
    @DisplayName("interseccion() no modifica los sets originales")
    void interseccion_noModifica() {
        Ejercicio22_AlgebraDeConjuntos.interseccion(a, b);
        assertThat(a).hasSize(4);
        assertThat(b).hasSize(4);
    }

    // ── diferencia ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("diferencia() A − B contiene solo elementos exclusivos de A")
    void diferencia_contenido() {
        HashSet<String> resultado = Ejercicio22_AlgebraDeConjuntos.diferencia(a, b);
        assertThat(resultado).containsExactlyInAnyOrder("1", "2");
    }

    @Test
    @DisplayName("diferencia() no modifica los sets originales")
    void diferencia_noModifica() {
        Ejercicio22_AlgebraDeConjuntos.diferencia(a, b);
        assertThat(a).hasSize(4);
    }

    // ── diferenciaSimetrica ──────────────────────────────────────────────────

    @Test
    @DisplayName("diferenciaSimetrica() contiene elementos exclusivos de cada set")
    void difSimetrica_contenido() {
        HashSet<String> resultado = Ejercicio22_AlgebraDeConjuntos.diferenciaSimetrica(a, b);
        assertThat(resultado).containsExactlyInAnyOrder("1", "2", "5", "6");
    }

    @Test
    @DisplayName("diferenciaSimetrica() de dos sets iguales es vacío")
    void difSimetrica_iguales() {
        HashSet<String> copia = new HashSet<>(a);
        assertThat(Ejercicio22_AlgebraDeConjuntos.diferenciaSimetrica(a, copia)).isEmpty();
    }

    // ── esSubconjunto ────────────────────────────────────────────────────────

    @Test
    @DisplayName("esSubconjunto() retorna true si A ⊆ B")
    void esSubconjunto_true() {
        HashSet<String> sub = new HashSet<>(List.of("3", "4"));
        assertThat(Ejercicio22_AlgebraDeConjuntos.esSubconjunto(sub, b)).isTrue();
    }

    @Test
    @DisplayName("esSubconjunto() retorna false si A ⊄ B")
    void esSubconjunto_false() {
        assertThat(Ejercicio22_AlgebraDeConjuntos.esSubconjunto(a, b)).isFalse();
    }

    @Test
    @DisplayName("esSubconjunto() set vacío es subconjunto de cualquiera")
    void esSubconjunto_vacio() {
        assertThat(Ejercicio22_AlgebraDeConjuntos
                .esSubconjunto(new HashSet<>(), a)).isTrue();
    }

    // ── sonDisjuntos ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("sonDisjuntos() retorna true si no hay elementos comunes")
    void sonDisjuntos_true() {
        HashSet<String> x = new HashSet<>(List.of("X", "Y"));
        assertThat(Ejercicio22_AlgebraDeConjuntos.sonDisjuntos(a, x)).isTrue();
    }

    @Test
    @DisplayName("sonDisjuntos() retorna false si hay elementos comunes")
    void sonDisjuntos_false() {
        assertThat(Ejercicio22_AlgebraDeConjuntos.sonDisjuntos(a, b)).isFalse();
    }
}
