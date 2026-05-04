package com.masterclass.collections.nivel01_arraylist_basico;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 03 — Búsqueda y Filtrado")
class Ejercicio03_BusquedaYFiltradoTest {

    private ArrayList<String> ciudades;

    @BeforeEach
    void setUp() {
        ciudades = new ArrayList<>(
                List.of("Madrid", "Barcelona", "Madrid", "Valencia", "Bilbao", "Madrid"));
    }

    // ── primerIndice ────────────────────────────────────────────────────────

    @Test
    @DisplayName("primerIndice() retorna la posición de la primera ocurrencia")
    void primerIndice_retornaPrimera() {
        assertThat(Ejercicio03_BusquedaYFiltrado.primerIndice(ciudades, "Madrid")).isEqualTo(0);
    }

    @Test
    @DisplayName("primerIndice() retorna -1 si el elemento no existe")
    void primerIndice_noExiste_retornaMinusUno() {
        assertThat(Ejercicio03_BusquedaYFiltrado.primerIndice(ciudades, "Sevilla")).isEqualTo(-1);
    }

    // ── ultimoIndice ────────────────────────────────────────────────────────

    @Test
    @DisplayName("ultimoIndice() retorna la posición de la última ocurrencia")
    void ultimoIndice_retornaUltima() {
        // Madrid aparece en 0, 2, 5 → último es 5
        assertThat(Ejercicio03_BusquedaYFiltrado.ultimoIndice(ciudades, "Madrid")).isEqualTo(5);
    }

    @Test
    @DisplayName("ultimoIndice() es igual a primerIndice() para elementos únicos")
    void ultimoIndice_elementoUnico_igualAPrimero() {
        int primero = Ejercicio03_BusquedaYFiltrado.primerIndice(ciudades, "Valencia");
        int ultimo = Ejercicio03_BusquedaYFiltrado.ultimoIndice(ciudades, "Valencia");
        assertThat(primero).isEqualTo(ultimo);
    }

    // ── extraerSubLista ─────────────────────────────────────────────────────

    @Test
    @DisplayName("extraerSubLista() retorna los elementos correctos en [desde, hasta)")
    void extraerSubLista_rangoValido() {
        ArrayList<String> sub = Ejercicio03_BusquedaYFiltrado.extraerSubLista(ciudades, 1, 4);
        assertThat(sub).containsExactly("Barcelona", "Madrid", "Valencia");
    }

    @Test
    @DisplayName("extraerSubLista() retorna una copia independiente (no una vista)")
    void extraerSubLista_esCopiaNdependiente() {
        ArrayList<String> sub = Ejercicio03_BusquedaYFiltrado.extraerSubLista(ciudades, 0, 2);
        int tamanoOriginal = ciudades.size();
        sub.clear();
        assertThat(ciudades).hasSize(tamanoOriginal); // original intacto
    }

    @Test
    @DisplayName("extraerSubLista() con desde >= hasta retorna lista vacía")
    void extraerSubLista_rangoVacio_retornaVacia() {
        ArrayList<String> sub = Ejercicio03_BusquedaYFiltrado.extraerSubLista(ciudades, 3, 3);
        assertThat(sub).isEmpty();
    }

    // ── eliminarSiEmpiezaPor ────────────────────────────────────────────────

    @Test
    @DisplayName("eliminarSiEmpiezaPor() elimina los elementos con el prefijo dado")
    void eliminarSiEmpiezaPor_eliminaCorrecto() {
        ArrayList<String> copia = new ArrayList<>(ciudades);
        int eliminados = Ejercicio03_BusquedaYFiltrado.eliminarSiEmpiezaPor(copia, "B");
        // "Barcelona" y "Bilbao" comienzan con B
        assertThat(eliminados).isEqualTo(2);
        assertThat(copia).doesNotContain("Barcelona", "Bilbao");
    }

    @Test
    @DisplayName("eliminarSiEmpiezaPor() retorna 0 si ningún elemento comienza con el prefijo")
    void eliminarSiEmpiezaPor_sinCoincidencias_retornaCero() {
        ArrayList<String> copia = new ArrayList<>(ciudades);
        int eliminados = Ejercicio03_BusquedaYFiltrado.eliminarSiEmpiezaPor(copia, "Z");
        assertThat(eliminados).isEqualTo(0);
        assertThat(copia).hasSize(ciudades.size());
    }

    // ── filtrarPorLongitudMinima ────────────────────────────────────────────

    @Test
    @DisplayName("filtrarPorLongitudMinima() retorna solo los elementos con longitud suficiente")
    void filtrarPorLongitudMinima_retornaCorrectamente() {
        // Madrid=6, Barcelona=9, Valencia=8, Bilbao=6
        ArrayList<String> resultado =
                Ejercicio03_BusquedaYFiltrado.filtrarPorLongitudMinima(ciudades, 7);
        assertThat(resultado).contains("Barcelona", "Valencia")
                             .doesNotContain("Madrid", "Bilbao");
    }

    @Test
    @DisplayName("filtrarPorLongitudMinima() no modifica la lista original")
    void filtrarPorLongitudMinima_noModificaOriginal() {
        int tamanoAntes = ciudades.size();
        Ejercicio03_BusquedaYFiltrado.filtrarPorLongitudMinima(ciudades, 7);
        assertThat(ciudades).hasSize(tamanoAntes);
    }

    // ── contieneTodos ───────────────────────────────────────────────────────

    @Test
    @DisplayName("contieneTodos() retorna true cuando la lista contiene todos los requeridos")
    void contieneTodos_todosPresentes_retornaTrue() {
        List<String> requeridos = List.of("Madrid", "Bilbao");
        assertThat(Ejercicio03_BusquedaYFiltrado.contieneTodos(ciudades, requeridos)).isTrue();
    }

    @Test
    @DisplayName("contieneTodos() retorna false cuando algún requerido falta")
    void contieneTodos_algunoFalta_retornaFalse() {
        List<String> requeridos = List.of("Madrid", "Sevilla");
        assertThat(Ejercicio03_BusquedaYFiltrado.contieneTodos(ciudades, requeridos)).isFalse();
    }
}
