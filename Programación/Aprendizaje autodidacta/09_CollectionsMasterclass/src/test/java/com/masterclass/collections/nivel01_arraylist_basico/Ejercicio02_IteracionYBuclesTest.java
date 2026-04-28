package com.masterclass.collections.nivel01_arraylist_basico;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 02 — Iteración y Bucles")
class Ejercicio02_IteracionYBuclesTest {

    // ── sumarLongitudes ─────────────────────────────────────────────────────

    @Test
    @DisplayName("sumarLongitudes() suma correctamente las longitudes de los Strings")
    void sumarLongitudes_calculaCorrectamente() {
        ArrayList<String> lista = new ArrayList<>(List.of("Java", "es", "genial"));
        // "Java"=4, "es"=2, "genial"=6 → 12
        assertThat(Ejercicio02_IteracionYBucles.sumarLongitudes(lista)).isEqualTo(12);
    }

    @Test
    @DisplayName("sumarLongitudes() sobre lista vacía retorna 0")
    void sumarLongitudes_listaVacia_retornaCero() {
        assertThat(Ejercicio02_IteracionYBucles.sumarLongitudes(new ArrayList<>())).isEqualTo(0);
    }

    @Test
    @DisplayName("sumarLongitudes() con un solo elemento retorna su longitud")
    void sumarLongitudes_unElemento() {
        ArrayList<String> lista = new ArrayList<>(List.of("Hola"));
        assertThat(Ejercicio02_IteracionYBucles.sumarLongitudes(lista)).isEqualTo(4);
    }

    // ── convertirAMayusculas ────────────────────────────────────────────────

    @Test
    @DisplayName("convertirAMayusculas() retorna nueva lista con todos los elementos en mayúsculas")
    void convertirAMayusculas_retornaEnMayusculas() {
        ArrayList<String> original = new ArrayList<>(List.of("hola", "mundo", "java"));
        ArrayList<String> resultado = Ejercicio02_IteracionYBucles.convertirAMayusculas(original);
        assertThat(resultado).containsExactly("HOLA", "MUNDO", "JAVA");
    }

    @Test
    @DisplayName("convertirAMayusculas() no modifica la lista original")
    void convertirAMayusculas_noModificaOriginal() {
        ArrayList<String> original = new ArrayList<>(List.of("hola", "mundo"));
        Ejercicio02_IteracionYBucles.convertirAMayusculas(original);
        assertThat(original).containsExactly("hola", "mundo");
    }

    @Test
    @DisplayName("convertirAMayusculas() retorna un objeto diferente (nueva lista)")
    void convertirAMayusculas_retornaListaNueva() {
        ArrayList<String> original = new ArrayList<>(List.of("a", "b"));
        ArrayList<String> resultado = Ejercicio02_IteracionYBucles.convertirAMayusculas(original);
        assertThat(resultado).isNotSameAs(original);
    }

    // ── eliminarMenoresQue ──────────────────────────────────────────────────

    @Test
    @DisplayName("eliminarMenoresQue() elimina los elementos correctos y retorna el conteo")
    void eliminarMenoresQue_eliminaYRetornaConteo() {
        ArrayList<Integer> lista = new ArrayList<>(List.of(10, 3, 7, 1, 15, 4));
        int eliminados = Ejercicio02_IteracionYBucles.eliminarMenoresQue(lista, 5);
        assertThat(eliminados).isEqualTo(3); // 3, 1, 4 son < 5
        assertThat(lista).containsExactlyInAnyOrder(10, 7, 15);
    }

    @Test
    @DisplayName("eliminarMenoresQue() con todos mayores no elimina nada")
    void eliminarMenoresQue_todosValidos_eliminaCero() {
        ArrayList<Integer> lista = new ArrayList<>(List.of(10, 20, 30));
        int eliminados = Ejercicio02_IteracionYBucles.eliminarMenoresQue(lista, 5);
        assertThat(eliminados).isEqualTo(0);
        assertThat(lista).hasSize(3);
    }

    // ── invertirEnSitio ─────────────────────────────────────────────────────

    @Test
    @DisplayName("invertirEnSitio() invierte el orden de la lista")
    void invertirEnSitio_invierteOrden() {
        ArrayList<String> lista = new ArrayList<>(List.of("uno", "dos", "tres", "cuatro"));
        Ejercicio02_IteracionYBucles.invertirEnSitio(lista);
        assertThat(lista).containsExactly("cuatro", "tres", "dos", "uno");
    }

    @Test
    @DisplayName("invertirEnSitio() sobre lista de un elemento no cambia nada")
    void invertirEnSitio_unElemento_sinCambio() {
        ArrayList<String> lista = new ArrayList<>(List.of("solo"));
        Ejercicio02_IteracionYBucles.invertirEnSitio(lista);
        assertThat(lista).containsExactly("solo");
    }

    @Test
    @DisplayName("invertirEnSitio() conserva todos los elementos")
    void invertirEnSitio_conservaTodosLosElementos() {
        ArrayList<String> lista = new ArrayList<>(List.of("A", "B", "C", "D", "E"));
        Ejercicio02_IteracionYBucles.invertirEnSitio(lista);
        assertThat(lista).containsExactlyInAnyOrder("A", "B", "C", "D", "E");
    }

    // ── contarOcurrencias ───────────────────────────────────────────────────

    @Test
    @DisplayName("contarOcurrencias() retorna el número exacto de veces que aparece el objetivo")
    void contarOcurrencias_cuentaCorrectamente() {
        ArrayList<String> lista = new ArrayList<>(List.of("Java", "Python", "Java", "Go", "Java"));
        assertThat(Ejercicio02_IteracionYBucles.contarOcurrencias(lista, "Java")).isEqualTo(3);
    }

    @Test
    @DisplayName("contarOcurrencias() retorna 0 si el objetivo no está en la lista")
    void contarOcurrencias_noExiste_retornaCero() {
        ArrayList<String> lista = new ArrayList<>(List.of("Java", "Go"));
        assertThat(Ejercicio02_IteracionYBucles.contarOcurrencias(lista, "Rust")).isEqualTo(0);
    }
}
