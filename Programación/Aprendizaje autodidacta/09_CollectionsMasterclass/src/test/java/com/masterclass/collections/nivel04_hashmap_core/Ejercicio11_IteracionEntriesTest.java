package com.masterclass.collections.nivel04_hashmap_core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 11 — Iteración sobre HashMap")
class Ejercicio11_IteracionEntriesTest {

    private HashMap<String, Integer> puntuaciones;

    @BeforeEach
    void setUp() {
        puntuaciones = new HashMap<>();
        puntuaciones.put("Ana",   95);
        puntuaciones.put("Luis",  72);
        puntuaciones.put("Marta", 88);
        puntuaciones.put("Pedro", 60);
    }

    // ── todasLasClaves ───────────────────────────────────────────────────────

    @Test
    @DisplayName("todasLasClaves() retorna todas las claves del mapa")
    void todasLasClaves_retornaTodasLasClaves() {
        ArrayList<String> claves = Ejercicio11_IteracionEntries.todasLasClaves(puntuaciones);
        assertThat(claves).containsExactlyInAnyOrder("Ana", "Luis", "Marta", "Pedro");
    }

    @Test
    @DisplayName("todasLasClaves() sobre mapa vacío retorna lista vacía")
    void todasLasClaves_mapaVacio() {
        assertThat(Ejercicio11_IteracionEntries.todasLasClaves(new HashMap<>())).isEmpty();
    }

    // ── sumarValores ────────────────────────────────────────────────────────

    @Test
    @DisplayName("sumarValores() suma correctamente todos los valores")
    void sumarValores_calculaCorrectamente() {
        // 95 + 72 + 88 + 60 = 315
        assertThat(Ejercicio11_IteracionEntries.sumarValores(puntuaciones)).isEqualTo(315);
    }

    @Test
    @DisplayName("sumarValores() sobre mapa vacío retorna 0")
    void sumarValores_mapaVacio_retornaCero() {
        assertThat(Ejercicio11_IteracionEntries.sumarValores(new HashMap<>())).isEqualTo(0);
    }

    // ── clavesConValorMayorQue ───────────────────────────────────────────────

    @Test
    @DisplayName("clavesConValorMayorQue() retorna las claves correctas")
    void clavesConValorMayorQue_umbral80() {
        ArrayList<String> resultado =
                Ejercicio11_IteracionEntries.clavesConValorMayorQue(puntuaciones, 80);
        assertThat(resultado).containsExactlyInAnyOrder("Ana", "Marta");
    }

    @Test
    @DisplayName("clavesConValorMayorQue() retorna vacío si ninguno supera el umbral")
    void clavesConValorMayorQue_ningunoCumple() {
        assertThat(Ejercicio11_IteracionEntries.clavesConValorMayorQue(puntuaciones, 100))
                .isEmpty();
    }

    // ── invertirMapa ────────────────────────────────────────────────────────

    @Test
    @DisplayName("invertirMapa() intercambia claves y valores")
    void invertirMapa_intercambia() {
        HashMap<String, String> capitales = new HashMap<>();
        capitales.put("España", "Madrid");
        capitales.put("Francia", "París");

        HashMap<String, String> invertido = Ejercicio11_IteracionEntries.invertirMapa(capitales);
        assertThat(invertido).containsEntry("Madrid", "España")
                             .containsEntry("París", "Francia");
    }

    @Test
    @DisplayName("invertirMapa() no modifica el mapa original")
    void invertirMapa_noModificaOriginal() {
        HashMap<String, String> original = new HashMap<>();
        original.put("A", "1");
        Ejercicio11_IteracionEntries.invertirMapa(original);
        assertThat(original).containsEntry("A", "1");
    }

    // ── formatearEntradas ───────────────────────────────────────────────────

    @Test
    @DisplayName("formatearEntradas() incluye todas las entradas en formato clave=valor")
    void formatearEntradas_incluyeTodasLasEntradas() {
        String resultado = Ejercicio11_IteracionEntries.formatearEntradas(puntuaciones);
        // El orden es indeterminado pero cada par debe aparecer
        assertThat(resultado).contains("Ana=95", "Luis=72", "Marta=88", "Pedro=60");
    }

    @Test
    @DisplayName("formatearEntradas() usa ', ' como separador entre entradas")
    void formatearEntradas_separadorComa() {
        HashMap<String, Integer> uno = new HashMap<>();
        uno.put("X", 1);
        uno.put("Y", 2);
        String resultado = Ejercicio11_IteracionEntries.formatearEntradas(uno);
        // Debe contener exactamente una coma si hay 2 elementos
        long comas = resultado.chars().filter(c -> c == ',').count();
        assertThat(comas).isEqualTo(1);
    }

    // ── clonarMapa ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("clonarMapa() retorna un mapa con los mismos pares")
    void clonarMapa_mismosParess() {
        HashMap<String, Integer> clon = Ejercicio11_IteracionEntries.clonarMapa(puntuaciones);
        assertThat(clon).containsAllEntriesOf(puntuaciones);
    }

    @Test
    @DisplayName("clonarMapa() retorna un objeto diferente al original")
    void clonarMapa_esObjetoDiferente() {
        HashMap<String, Integer> clon = Ejercicio11_IteracionEntries.clonarMapa(puntuaciones);
        assertThat(clon).isNotSameAs(puntuaciones);
    }

    @Test
    @DisplayName("clonarMapa() — modificar el clon no afecta al original")
    void clonarMapa_independiente() {
        HashMap<String, Integer> clon = Ejercicio11_IteracionEntries.clonarMapa(puntuaciones);
        clon.put("EXTRA", 999);
        assertThat(puntuaciones).doesNotContainKey("EXTRA");
    }
}
