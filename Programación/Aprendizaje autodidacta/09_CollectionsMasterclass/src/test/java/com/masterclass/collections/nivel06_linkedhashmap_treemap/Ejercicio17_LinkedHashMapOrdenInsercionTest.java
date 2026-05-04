package com.masterclass.collections.nivel06_linkedhashmap_treemap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 17 — LinkedHashMap: Orden de Inserción")
class Ejercicio17_LinkedHashMapOrdenInsercionTest {

    // ── crearLinkedHashMap ───────────────────────────────────────────────────

    @Test
    @DisplayName("crearLinkedHashMap() crea el mapa con todas las claves y valores")
    void crearLinkedHashMap_contenido() {
        String[] claves = {"A", "B", "C"};
        int[] valores = {10, 20, 30};
        LinkedHashMap<String, Integer> mapa =
                Ejercicio17_LinkedHashMapOrdenInsercion.crearLinkedHashMap(claves, valores);
        assertThat(mapa).hasSize(3);
        assertThat(mapa.get("A")).isEqualTo(10);
        assertThat(mapa.get("B")).isEqualTo(20);
        assertThat(mapa.get("C")).isEqualTo(30);
    }

    @Test
    @DisplayName("crearLinkedHashMap() respeta el orden de inserción")
    void crearLinkedHashMap_orden() {
        String[] claves = {"Z", "A", "M"};
        int[] valores = {1, 2, 3};
        LinkedHashMap<String, Integer> mapa =
                Ejercicio17_LinkedHashMapOrdenInsercion.crearLinkedHashMap(claves, valores);
        assertThat(new ArrayList<>(mapa.keySet())).containsExactly("Z", "A", "M");
    }

    @Test
    @DisplayName("crearLinkedHashMap() lanza IllegalArgumentException si arrays desiguales")
    void crearLinkedHashMap_arraysDesiguales() {
        assertThatThrownBy(() ->
                Ejercicio17_LinkedHashMapOrdenInsercion.crearLinkedHashMap(
                        new String[]{"A"}, new int[]{1, 2}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // ── obtenerClavesEnOrden ─────────────────────────────────────────────────

    @Test
    @DisplayName("obtenerClavesEnOrden() retorna las claves en orden de inserción")
    void obtenerClavesEnOrden_orden() {
        LinkedHashMap<String, Integer> mapa = new LinkedHashMap<>();
        mapa.put("pera", 7);
        mapa.put("banana", 3);
        mapa.put("manzana", 1);
        ArrayList<String> claves =
                Ejercicio17_LinkedHashMapOrdenInsercion.obtenerClavesEnOrden(mapa);
        assertThat(claves).containsExactly("pera", "banana", "manzana");
    }

    @Test
    @DisplayName("obtenerClavesEnOrden() retorna lista vacía si mapa vacío")
    void obtenerClavesEnOrden_vacio() {
        assertThat(Ejercicio17_LinkedHashMapOrdenInsercion
                .obtenerClavesEnOrden(new LinkedHashMap<>())).isEmpty();
    }

    // ── obtenerPrimeraEntrada ────────────────────────────────────────────────

    @Test
    @DisplayName("obtenerPrimeraEntrada() retorna la primera insertada")
    void obtenerPrimeraEntrada_existe() {
        LinkedHashMap<String, Integer> mapa = new LinkedHashMap<>();
        mapa.put("X", 100);
        mapa.put("Y", 200);
        Map.Entry<String, Integer> primera =
                Ejercicio17_LinkedHashMapOrdenInsercion.obtenerPrimeraEntrada(mapa);
        assertThat(primera).isNotNull();
        assertThat(primera.getKey()).isEqualTo("X");
        assertThat(primera.getValue()).isEqualTo(100);
    }

    @Test
    @DisplayName("obtenerPrimeraEntrada() retorna null si mapa vacío")
    void obtenerPrimeraEntrada_vacio() {
        assertThat(Ejercicio17_LinkedHashMapOrdenInsercion
                .obtenerPrimeraEntrada(new LinkedHashMap<>())).isNull();
    }

    // ── obtenerUltimaEntrada ─────────────────────────────────────────────────

    @Test
    @DisplayName("obtenerUltimaEntrada() retorna la última insertada")
    void obtenerUltimaEntrada_existe() {
        LinkedHashMap<String, Integer> mapa = new LinkedHashMap<>();
        mapa.put("A", 1);
        mapa.put("B", 2);
        mapa.put("C", 3);
        Map.Entry<String, Integer> ultima =
                Ejercicio17_LinkedHashMapOrdenInsercion.obtenerUltimaEntrada(mapa);
        assertThat(ultima).isNotNull();
        assertThat(ultima.getKey()).isEqualTo("C");
        assertThat(ultima.getValue()).isEqualTo(3);
    }

    @Test
    @DisplayName("obtenerUltimaEntrada() retorna null si mapa vacío")
    void obtenerUltimaEntrada_vacio() {
        assertThat(Ejercicio17_LinkedHashMapOrdenInsercion
                .obtenerUltimaEntrada(new LinkedHashMap<>())).isNull();
    }

    // ── reinsertarClave ──────────────────────────────────────────────────────

    @Test
    @DisplayName("reinsertarClave() mueve la clave al final del orden")
    void reinsertarClave_mueveAlFinal() {
        LinkedHashMap<String, Integer> mapa = new LinkedHashMap<>();
        mapa.put("A", 10);
        mapa.put("B", 20);
        mapa.put("C", 30);
        Ejercicio17_LinkedHashMapOrdenInsercion.reinsertarClave(mapa, "A");
        assertThat(new ArrayList<>(mapa.keySet())).containsExactly("B", "C", "A");
        assertThat(mapa.get("A")).isEqualTo(10); // valor preservado
    }

    @Test
    @DisplayName("reinsertarClave() no modifica el mapa si la clave no existe")
    void reinsertarClave_noExiste() {
        LinkedHashMap<String, Integer> mapa = new LinkedHashMap<>();
        mapa.put("A", 10);
        Ejercicio17_LinkedHashMapOrdenInsercion.reinsertarClave(mapa, "Z");
        assertThat(mapa).hasSize(1);
        assertThat(new ArrayList<>(mapa.keySet())).containsExactly("A");
    }

    // ── filtrarPorValorMinimoConservandoOrden ─────────────────────────────────

    @Test
    @DisplayName("filtrarPorValorMinimo() retorna solo entradas con valor >= mínimo")
    void filtrar_contenido() {
        LinkedHashMap<String, Integer> mapa = new LinkedHashMap<>();
        mapa.put("A", 5);
        mapa.put("B", 2);
        mapa.put("C", 8);
        mapa.put("D", 1);
        LinkedHashMap<String, Integer> filtrado =
                Ejercicio17_LinkedHashMapOrdenInsercion
                        .filtrarPorValorMinimoConservandoOrden(mapa, 5);
        assertThat(filtrado).hasSize(2);
        assertThat(filtrado).containsKeys("A", "C");
    }

    @Test
    @DisplayName("filtrarPorValorMinimo() conserva el orden de inserción original")
    void filtrar_orden() {
        LinkedHashMap<String, Integer> mapa = new LinkedHashMap<>();
        mapa.put("Z", 10);
        mapa.put("A", 1);
        mapa.put("M", 10);
        LinkedHashMap<String, Integer> filtrado =
                Ejercicio17_LinkedHashMapOrdenInsercion
                        .filtrarPorValorMinimoConservandoOrden(mapa, 5);
        assertThat(new ArrayList<>(filtrado.keySet())).containsExactly("Z", "M");
    }

    @Test
    @DisplayName("filtrarPorValorMinimo() retorna nuevo mapa sin modificar el original")
    void filtrar_noModificaOriginal() {
        LinkedHashMap<String, Integer> mapa = new LinkedHashMap<>();
        mapa.put("A", 10);
        mapa.put("B", 1);
        LinkedHashMap<String, Integer> filtrado =
                Ejercicio17_LinkedHashMapOrdenInsercion
                        .filtrarPorValorMinimoConservandoOrden(mapa, 5);
        assertThat(mapa).hasSize(2);
        assertThat(filtrado).hasSize(1);
        assertThat(filtrado).isNotSameAs(mapa);
    }
}
