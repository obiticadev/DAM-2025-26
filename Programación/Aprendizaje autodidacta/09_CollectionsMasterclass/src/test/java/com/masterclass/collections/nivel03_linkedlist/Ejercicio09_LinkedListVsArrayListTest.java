package com.masterclass.collections.nivel03_linkedlist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 09 — LinkedList vs ArrayList: Aplicación Práctica")
class Ejercicio09_LinkedListVsArrayListTest {

    // ── insertarEnPosicionCero ───────────────────────────────────────────────

    @Test
    @DisplayName("insertarEnPosicionCero() sobre ArrayList pone el elemento al inicio")
    void insertarEnPosicionCero_arrayList() {
        ArrayList<String> lista = new ArrayList<>(List.of("B", "C", "D"));
        Ejercicio09_LinkedListVsArrayList.insertarEnPosicionCero(lista, "A");
        assertThat(lista.get(0)).isEqualTo("A");
        assertThat(lista).containsExactly("A", "B", "C", "D");
    }

    @Test
    @DisplayName("insertarEnPosicionCero() sobre LinkedList pone el elemento al inicio")
    void insertarEnPosicionCero_linkedList() {
        LinkedList<String> lista = new LinkedList<>(List.of("B", "C", "D"));
        Ejercicio09_LinkedListVsArrayList.insertarEnPosicionCero(lista, "A");
        assertThat(lista.getFirst()).isEqualTo("A");
    }

    // ── extraerPrimero ───────────────────────────────────────────────────────

    @Test
    @DisplayName("extraerPrimero() retorna y elimina el primer elemento")
    void extraerPrimero_retornaYElimina() {
        ArrayList<String> lista = new ArrayList<>(List.of("X", "Y", "Z"));
        String extraido = Ejercicio09_LinkedListVsArrayList.extraerPrimero(lista);
        assertThat(extraido).isEqualTo("X");
        assertThat(lista).containsExactly("Y", "Z");
    }

    @Test
    @DisplayName("extraerPrimero() sobre lista vacía retorna null")
    void extraerPrimero_listaVacia_retornaNull() {
        assertThat(Ejercicio09_LinkedListVsArrayList.extraerPrimero(new ArrayList<>())).isNull();
    }

    // ── estadisticasNumericas ────────────────────────────────────────────────

    @Test
    @DisplayName("estadisticasNumericas() calcula min, max, suma y promedio correctamente")
    void estadisticasNumericas_calculaCorrecto() {
        // min=1, max=9, suma=45, promedio=5.00
        List<Integer> numeros = new ArrayList<>(List.of(5, 2, 8, 1, 9, 3, 7, 4, 6));
        String resultado = Ejercicio09_LinkedListVsArrayList.estadisticasNumericas(numeros);
        assertThat(resultado).contains("min=1", "max=9", "suma=45", "promedio=5.00");
    }

    @Test
    @DisplayName("estadisticasNumericas() para lista vacía retorna 'lista vacía'")
    void estadisticasNumericas_listaVacia() {
        String resultado = Ejercicio09_LinkedListVsArrayList.estadisticasNumericas(new ArrayList<>());
        assertThat(resultado).isEqualTo("lista vacía");
    }

    @Test
    @DisplayName("estadisticasNumericas() con un elemento retorna ese mismo como min y max")
    void estadisticasNumericas_unElemento() {
        List<Integer> lista = new ArrayList<>(List.of(7));
        String resultado = Ejercicio09_LinkedListVsArrayList.estadisticasNumericas(lista);
        assertThat(resultado).contains("min=7", "max=7", "suma=7");
    }

    // ── rotarIzquierda ───────────────────────────────────────────────────────

    @Test
    @DisplayName("rotarIzquierda() mueve el primer elemento al final")
    void rotarIzquierda_primerElementoVaAlFinal() {
        ArrayList<String> lista = new ArrayList<>(List.of("A", "B", "C", "D"));
        Ejercicio09_LinkedListVsArrayList.rotarIzquierda(lista);
        assertThat(lista).containsExactly("B", "C", "D", "A");
    }

    @Test
    @DisplayName("rotarIzquierda() conserva el mismo número de elementos")
    void rotarIzquierda_conservaTamano() {
        ArrayList<String> lista = new ArrayList<>(List.of("X", "Y", "Z"));
        Ejercicio09_LinkedListVsArrayList.rotarIzquierda(lista);
        assertThat(lista).hasSize(3);
    }

    // ── intercalar ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("intercalar() alterna elementos de a y b")
    void intercalar_alternaCorrecto() {
        ArrayList<String> a = new ArrayList<>(List.of("A", "B", "C"));
        ArrayList<String> b = new ArrayList<>(List.of("1", "2", "3"));
        ArrayList<String> resultado = Ejercicio09_LinkedListVsArrayList.intercalar(a, b);
        assertThat(resultado).containsExactly("A", "1", "B", "2", "C", "3");
    }

    @Test
    @DisplayName("intercalar() añade los sobrantes al final si las listas tienen distinto tamaño")
    void intercalar_diferenteTamano_sobrantes() {
        ArrayList<String> a = new ArrayList<>(List.of("X", "Y", "Z"));
        ArrayList<String> b = new ArrayList<>(List.of("1", "2"));
        ArrayList<String> resultado = Ejercicio09_LinkedListVsArrayList.intercalar(a, b);
        assertThat(resultado).containsExactly("X", "1", "Y", "2", "Z");
    }

    // ── copiarInverso ────────────────────────────────────────────────────────

    @Test
    @DisplayName("copiarInverso() retorna una nueva lista con el orden invertido")
    void copiarInverso_ordenInvertido() {
        ArrayList<String> lista = new ArrayList<>(List.of("uno", "dos", "tres", "cuatro"));
        ArrayList<String> resultado = Ejercicio09_LinkedListVsArrayList.copiarInverso(lista);
        assertThat(resultado).containsExactly("cuatro", "tres", "dos", "uno");
    }

    @Test
    @DisplayName("copiarInverso() no modifica la lista original")
    void copiarInverso_noModificaOriginal() {
        ArrayList<String> lista = new ArrayList<>(List.of("A", "B", "C"));
        Ejercicio09_LinkedListVsArrayList.copiarInverso(lista);
        assertThat(lista).containsExactly("A", "B", "C");
    }

    @Test
    @DisplayName("copiarInverso() retorna un objeto diferente (nueva lista)")
    void copiarInverso_retornaListaNueva() {
        ArrayList<String> lista = new ArrayList<>(List.of("A", "B"));
        ArrayList<String> resultado = Ejercicio09_LinkedListVsArrayList.copiarInverso(lista);
        assertThat(resultado).isNotSameAs(lista);
    }
}
