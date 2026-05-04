package com.masterclass.collections.nivel06_linkedhashmap_treemap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 19 — TreeMap: Orden Natural y NavigableMap")
class Ejercicio19_TreeMapNavegacionTest {

    private TreeMap<String, Integer> mapa;

    @BeforeEach
    void setUp() {
        mapa = new TreeMap<>();
        mapa.put("banana", 5);
        mapa.put("cereza", 1);
        mapa.put("mango", 3);
        mapa.put("pera", 2);
        mapa.put("uva", 8);
    }

    // ── crearTreeMapDesdeArrays ──────────────────────────────────────────────

    @Test
    @DisplayName("crearTreeMapDesdeArrays() crea TreeMap con todas las entradas")
    void crearTreeMap_contenido() {
        TreeMap<String, Integer> t = Ejercicio19_TreeMapNavegacion
                .crearTreeMapDesdeArrays(new String[]{"B", "A", "C"}, new int[]{2, 1, 3});
        assertThat(t).hasSize(3);
        assertThat(t.get("A")).isEqualTo(1);
    }

    @Test
    @DisplayName("crearTreeMapDesdeArrays() respeta el orden natural de String")
    void crearTreeMap_orden() {
        TreeMap<String, Integer> t = Ejercicio19_TreeMapNavegacion
                .crearTreeMapDesdeArrays(new String[]{"pera", "banana", "mango"}, new int[]{1, 2, 3});
        assertThat(new ArrayList<>(t.keySet())).containsExactly("banana", "mango", "pera");
    }

    @Test
    @DisplayName("crearTreeMapDesdeArrays() lanza excepción con arrays de distinta longitud")
    void crearTreeMap_excepcion() {
        assertThatThrownBy(() ->
                Ejercicio19_TreeMapNavegacion.crearTreeMapDesdeArrays(
                        new String[]{"A"}, new int[]{1, 2}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // ── obtenerRango ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("obtenerRango() retorna claves en el rango inclusivo")
    void obtenerRango_contenido() {
        ArrayList<String> rango = Ejercicio19_TreeMapNavegacion
                .obtenerRango(mapa, "cereza", "pera");
        assertThat(rango).containsExactly("cereza", "mango", "pera");
    }

    @Test
    @DisplayName("obtenerRango() retorna lista vacía si no hay claves en el rango")
    void obtenerRango_vacio() {
        ArrayList<String> rango = Ejercicio19_TreeMapNavegacion
                .obtenerRango(mapa, "xxx", "zzz");
        assertThat(rango).isEmpty();
    }

    // ── vecinosMasCercanos ───────────────────────────────────────────────────

    @Test
    @DisplayName("vecinosMasCercanos() retorna floor y ceiling correctos para clave inexistente")
    void vecinos_claveInexistente() {
        String[] vecinos = Ejercicio19_TreeMapNavegacion.vecinosMasCercanos(mapa, "kiwi");
        assertThat(vecinos[0]).isEqualTo("cereza"); // floor
        assertThat(vecinos[1]).isEqualTo("mango");  // ceiling
    }

    @Test
    @DisplayName("vecinosMasCercanos() retorna la propia clave si existe")
    void vecinos_claveExistente() {
        String[] vecinos = Ejercicio19_TreeMapNavegacion.vecinosMasCercanos(mapa, "mango");
        assertThat(vecinos[0]).isEqualTo("mango");
        assertThat(vecinos[1]).isEqualTo("mango");
    }

    @Test
    @DisplayName("vecinosMasCercanos() retorna null si no hay vecino en esa dirección")
    void vecinos_sinVecino() {
        String[] vecinos = Ejercicio19_TreeMapNavegacion.vecinosMasCercanos(mapa, "aaa");
        assertThat(vecinos[0]).isNull(); // nada <= "aaa"
        assertThat(vecinos[1]).isEqualTo("banana");
    }

    // ── clavesEnOrdenInverso ─────────────────────────────────────────────────

    @Test
    @DisplayName("clavesEnOrdenInverso() retorna claves en orden descendente")
    void clavesInverso() {
        ArrayList<String> inverso = Ejercicio19_TreeMapNavegacion
                .clavesEnOrdenInverso(mapa);
        assertThat(inverso).containsExactly("uva", "pera", "mango", "cereza", "banana");
    }

    // ── sumaPorRango ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("sumaPorRango() suma correctamente los valores del rango")
    void sumaPorRango_correcto() {
        // cereza=1, mango=3, pera=2  → 6
        int suma = Ejercicio19_TreeMapNavegacion.sumaPorRango(mapa, "cereza", "pera");
        assertThat(suma).isEqualTo(6);
    }

    @Test
    @DisplayName("sumaPorRango() retorna 0 si el rango está vacío")
    void sumaPorRango_vacio() {
        int suma = Ejercicio19_TreeMapNavegacion.sumaPorRango(mapa, "xxx", "zzz");
        assertThat(suma).isEqualTo(0);
    }

    // ── primerYUltimaClave ────────────────────────────────────────────────────

    @Test
    @DisplayName("primerYUltimaClave() retorna primera y última clave")
    void primerYUltima_existe() {
        String[] extremos = Ejercicio19_TreeMapNavegacion.primerYUltimaClave(mapa);
        assertThat(extremos[0]).isEqualTo("banana");
        assertThat(extremos[1]).isEqualTo("uva");
    }

    @Test
    @DisplayName("primerYUltimaClave() retorna [null, null] si mapa vacío")
    void primerYUltima_vacio() {
        String[] extremos = Ejercicio19_TreeMapNavegacion
                .primerYUltimaClave(new TreeMap<>());
        assertThat(extremos[0]).isNull();
        assertThat(extremos[1]).isNull();
    }

    // ── crearTreeMapConComparadorPersonalizado ────────────────────────────────

    @Test
    @DisplayName("comparador personalizado ordena por longitud y desempata alfabéticamente")
    void comparadorPersonalizado_orden() {
        TreeMap<String, Integer> t = Ejercicio19_TreeMapNavegacion
                .crearTreeMapConComparadorPersonalizado(
                        new String[]{"pera", "banana", "uva", "mango"},
                        new int[]{2, 5, 8, 3});
        ArrayList<String> claves = new ArrayList<>(t.keySet());
        // longitud: uva(3) < pera(4)=mango(5?)... let's recalc
        // uva=3, pera=4, mango=5, banana=6 → ordenados por longitud asc
        assertThat(claves.get(0)).isEqualTo("uva");     // longitud 3
        assertThat(claves.get(1)).isEqualTo("pera");    // longitud 4
        assertThat(claves.get(2)).isEqualTo("mango");   // longitud 5
        assertThat(claves.get(3)).isEqualTo("banana");  // longitud 6
    }

    @Test
    @DisplayName("comparador personalizado desempata por orden natural si misma longitud")
    void comparadorPersonalizado_desempate() {
        TreeMap<String, Integer> t = Ejercicio19_TreeMapNavegacion
                .crearTreeMapConComparadorPersonalizado(
                        new String[]{"abc", "xyz", "mno"},
                        new int[]{1, 2, 3});
        // Misma longitud 3 → orden alfabético: abc, mno, xyz
        ArrayList<String> claves = new ArrayList<>(t.keySet());
        assertThat(claves).containsExactly("abc", "mno", "xyz");
    }
}
