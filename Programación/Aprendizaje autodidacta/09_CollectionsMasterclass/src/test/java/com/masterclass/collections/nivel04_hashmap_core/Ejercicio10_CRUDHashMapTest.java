package com.masterclass.collections.nivel04_hashmap_core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 10 — CRUD Básico HashMap")
class Ejercicio10_CRUDHashMapTest {

    private HashMap<String, String> mapa;

    @BeforeEach
    void setUp() {
        mapa = Ejercicio10_CRUDHashMap.crearMapa("nombre", "Ana", "ciudad", "Madrid");
    }

    // ── crearMapa ────────────────────────────────────────────────────────────

    @Test
    @DisplayName("crearMapa() retorna los pares clave-valor correctos")
    void crearMapa_retornaParesConcorrecto() {
        assertThat(mapa).containsEntry("nombre", "Ana").containsEntry("ciudad", "Madrid");
    }

    @Test
    @DisplayName("crearMapa() con varargs vacío retorna mapa vacío")
    void crearMapa_sinArgs_retornaVacio() {
        assertThat(Ejercicio10_CRUDHashMap.crearMapa()).isEmpty();
    }

    @Test
    @DisplayName("crearMapa() con número impar de argumentos ignora el último")
    void crearMapa_impar_ignoraUltimo() {
        HashMap<String, String> m = Ejercicio10_CRUDHashMap.crearMapa("a", "1", "b");
        assertThat(m).hasSize(1).containsEntry("a", "1").doesNotContainKey("b");
    }

    // ── insertar ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("insertar() retorna null para una clave nueva")
    void insertar_claveNueva_retornaNull() {
        assertThat(Ejercicio10_CRUDHashMap.insertar(mapa, "pais", "España")).isNull();
    }

    @Test
    @DisplayName("insertar() retorna el valor anterior al sobrescribir")
    void insertar_claveExistente_retornaAnterior() {
        String anterior = Ejercicio10_CRUDHashMap.insertar(mapa, "ciudad", "Barcelona");
        assertThat(anterior).isEqualTo("Madrid");
        assertThat(mapa.get("ciudad")).isEqualTo("Barcelona");
    }

    // ── obtener ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("obtener() retorna el valor asociado a la clave")
    void obtener_claveExistente() {
        assertThat(Ejercicio10_CRUDHashMap.obtener(mapa, "nombre")).isEqualTo("Ana");
    }

    @Test
    @DisplayName("obtener() retorna null para clave inexistente")
    void obtener_claveInexistente_retornaNull() {
        assertThat(Ejercicio10_CRUDHashMap.obtener(mapa, "edad")).isNull();
    }

    // ── eliminar ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("eliminar() retorna el valor eliminado y lo quita del mapa")
    void eliminar_claveExistente() {
        String eliminado = Ejercicio10_CRUDHashMap.eliminar(mapa, "ciudad");
        assertThat(eliminado).isEqualTo("Madrid");
        assertThat(mapa).doesNotContainKey("ciudad");
    }

    @Test
    @DisplayName("eliminar() retorna null para clave inexistente")
    void eliminar_claveInexistente_retornaNull() {
        assertThat(Ejercicio10_CRUDHashMap.eliminar(mapa, "noExiste")).isNull();
    }

    // ── contieneClave ────────────────────────────────────────────────────────

    @Test
    @DisplayName("contieneClave() retorna true para clave existente")
    void contieneClave_existente() {
        assertThat(Ejercicio10_CRUDHashMap.contieneClave(mapa, "nombre")).isTrue();
    }

    @Test
    @DisplayName("contieneClave() retorna false para clave inexistente")
    void contieneClave_inexistente() {
        assertThat(Ejercicio10_CRUDHashMap.contieneClave(mapa, "pais")).isFalse();
    }

    // ── contieneValor ────────────────────────────────────────────────────────

    @Test
    @DisplayName("contieneValor() retorna true para valor existente")
    void contieneValor_existente() {
        assertThat(Ejercicio10_CRUDHashMap.contieneValor(mapa, "Ana")).isTrue();
    }

    @Test
    @DisplayName("contieneValor() retorna false para valor inexistente")
    void contieneValor_inexistente() {
        assertThat(Ejercicio10_CRUDHashMap.contieneValor(mapa, "Barcelona")).isFalse();
    }

    // ── vaciar ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("vaciar() deja el mapa con 0 entradas")
    void vaciar_mapaVacio() {
        Ejercicio10_CRUDHashMap.vaciar(mapa);
        assertThat(mapa).isEmpty();
    }

    @Test
    @DisplayName("vaciar() sobre mapa ya vacío no lanza excepción")
    void vaciar_mapaYaVacio() {
        assertThatNoException().isThrownBy(() ->
                Ejercicio10_CRUDHashMap.vaciar(new HashMap<>()));
    }
}
