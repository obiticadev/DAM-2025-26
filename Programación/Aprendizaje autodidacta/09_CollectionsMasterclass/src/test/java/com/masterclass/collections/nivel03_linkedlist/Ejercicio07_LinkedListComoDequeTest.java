package com.masterclass.collections.nivel03_linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 07 — LinkedList como Deque")
class Ejercicio07_LinkedListComoDequeTest {

    private LinkedList<String> deque;

    @BeforeEach
    void setUp() {
        deque = Ejercicio07_LinkedListComoDeque.crearDeque("B", "C", "D");
    }

    // ── crearDeque ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("crearDeque() retorna LinkedList con los elementos en el orden dado")
    void crearDeque_ordenCorrecto() {
        assertThat(deque).containsExactly("B", "C", "D");
    }

    @Test
    @DisplayName("crearDeque() con varargs vacío retorna deque vacía")
    void crearDeque_vacia() {
        assertThat(Ejercicio07_LinkedListComoDeque.crearDeque()).isEmpty();
    }

    // ── insertarAlFrente ────────────────────────────────────────────────────

    @Test
    @DisplayName("insertarAlFrente() pone el elemento en la posición 0")
    void insertarAlFrente_posicionCero() {
        Ejercicio07_LinkedListComoDeque.insertarAlFrente(deque, "A");
        assertThat(deque.getFirst()).isEqualTo("A");
    }

    @Test
    @DisplayName("insertarAlFrente() incrementa el tamaño en 1")
    void insertarAlFrente_incrementaTamano() {
        int antes = deque.size();
        Ejercicio07_LinkedListComoDeque.insertarAlFrente(deque, "A");
        assertThat(deque).hasSize(antes + 1);
    }

    // ── insertarAlFinal ─────────────────────────────────────────────────────

    @Test
    @DisplayName("insertarAlFinal() pone el elemento en la última posición")
    void insertarAlFinal_ultimaPosicion() {
        Ejercicio07_LinkedListComoDeque.insertarAlFinal(deque, "E");
        assertThat(deque.getLast()).isEqualTo("E");
    }

    // ── consultarFrente ─────────────────────────────────────────────────────

    @Test
    @DisplayName("consultarFrente() retorna el primer elemento sin modificar la deque")
    void consultarFrente_sinModificar() {
        String frente = Ejercicio07_LinkedListComoDeque.consultarFrente(deque);
        assertThat(frente).isEqualTo("B");
        assertThat(deque).hasSize(3); // tamaño intacto
    }

    @Test
    @DisplayName("consultarFrente() sobre deque vacía retorna null")
    void consultarFrente_vacia_retornaNull() {
        assertThat(Ejercicio07_LinkedListComoDeque.consultarFrente(new LinkedList<>())).isNull();
    }

    // ── consultarFinal ──────────────────────────────────────────────────────

    @Test
    @DisplayName("consultarFinal() retorna el último elemento sin modificar la deque")
    void consultarFinal_sinModificar() {
        String final_ = Ejercicio07_LinkedListComoDeque.consultarFinal(deque);
        assertThat(final_).isEqualTo("D");
        assertThat(deque).hasSize(3);
    }

    // ── extraerDelFrente ────────────────────────────────────────────────────

    @Test
    @DisplayName("extraerDelFrente() retorna el primer elemento y lo elimina")
    void extraerDelFrente_retornaYElimina() {
        String extraido = Ejercicio07_LinkedListComoDeque.extraerDelFrente(deque);
        assertThat(extraido).isEqualTo("B");
        assertThat(deque).hasSize(2).containsExactly("C", "D");
    }

    @Test
    @DisplayName("extraerDelFrente() sobre deque vacía retorna null sin excepción")
    void extraerDelFrente_vacia_retornaNull() {
        assertThat(Ejercicio07_LinkedListComoDeque.extraerDelFrente(new LinkedList<>())).isNull();
    }

    // ── extraerDelFinal ─────────────────────────────────────────────────────

    @Test
    @DisplayName("extraerDelFinal() retorna el último elemento y lo elimina")
    void extraerDelFinal_retornaYElimina() {
        String extraido = Ejercicio07_LinkedListComoDeque.extraerDelFinal(deque);
        assertThat(extraido).isEqualTo("D");
        assertThat(deque).hasSize(2).containsExactly("B", "C");
    }

    // ── invertirConDeque ────────────────────────────────────────────────────

    @Test
    @DisplayName("invertirConDeque() retorna una nueva deque con el orden invertido")
    void invertirConDeque_ordenInvertido() {
        LinkedList<String> original = Ejercicio07_LinkedListComoDeque.crearDeque("1", "2", "3", "4", "5");
        LinkedList<String> invertida = Ejercicio07_LinkedListComoDeque.invertirConDeque(original);
        assertThat(invertida).containsExactly("5", "4", "3", "2", "1");
    }

    @Test
    @DisplayName("invertirConDeque() no modifica la deque original")
    void invertirConDeque_noModificaOriginal() {
        LinkedList<String> original = Ejercicio07_LinkedListComoDeque.crearDeque("X", "Y", "Z");
        Ejercicio07_LinkedListComoDeque.invertirConDeque(original);
        assertThat(original).containsExactly("X", "Y", "Z");
    }
}
