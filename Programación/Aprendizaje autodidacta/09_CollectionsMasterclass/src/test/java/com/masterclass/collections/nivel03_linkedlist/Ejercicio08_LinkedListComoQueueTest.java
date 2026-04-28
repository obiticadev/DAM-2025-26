package com.masterclass.collections.nivel03_linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 08 — LinkedList como Queue")
class Ejercicio08_LinkedListComoQueueTest {

    private LinkedList<String> cola;

    @BeforeEach
    void setUp() {
        cola = new LinkedList<>();
        Ejercicio08_LinkedListComoQueue.encolar(cola, "Cliente-A");
        Ejercicio08_LinkedListComoQueue.encolar(cola, "Cliente-B");
        Ejercicio08_LinkedListComoQueue.encolar(cola, "Cliente-C");
    }

    // ── encolar ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("encolar() añade el elemento al final de la cola")
    void encolar_anadeoAlFinal() {
        assertThat(cola.getLast()).isEqualTo("Cliente-C");
    }

    @Test
    @DisplayName("encolar() respeta el orden FIFO (primero en entrar, último por la cola)")
    void encolar_respetaOrdenFIFO() {
        assertThat(cola).containsExactly("Cliente-A", "Cliente-B", "Cliente-C");
    }

    // ── desencolar ────────────────────────────────────────────────────────────

    @Test
    @DisplayName("desencolar() retorna el primer elemento (FIFO)")
    void desencolar_retornaElPrimero() {
        String extraido = Ejercicio08_LinkedListComoQueue.desencolar(cola);
        assertThat(extraido).isEqualTo("Cliente-A");
    }

    @Test
    @DisplayName("desencolar() elimina el elemento del frente")
    void desencolar_eliminaDelFrente() {
        Ejercicio08_LinkedListComoQueue.desencolar(cola);
        assertThat(cola.getFirst()).isEqualTo("Cliente-B");
        assertThat(cola).hasSize(2);
    }

    @Test
    @DisplayName("desencolar() sobre cola vacía retorna null sin excepción")
    void desencolar_colaVacia_retornaNull() {
        assertThat(Ejercicio08_LinkedListComoQueue.desencolar(new LinkedList<>())).isNull();
    }

    // ── consultarFrente ───────────────────────────────────────────────────────

    @Test
    @DisplayName("consultarFrente() retorna el primer elemento sin extraerlo")
    void consultarFrente_noExtrae() {
        String frente = Ejercicio08_LinkedListComoQueue.consultarFrente(cola);
        assertThat(frente).isEqualTo("Cliente-A");
        assertThat(cola).hasSize(3); // tamaño intacto
    }

    @Test
    @DisplayName("consultarFrente() sobre cola vacía retorna null")
    void consultarFrente_vacia_retornaNull() {
        assertThat(Ejercicio08_LinkedListComoQueue.consultarFrente(new LinkedList<>())).isNull();
    }

    // ── procesarTodos ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("procesarTodos() retorna los elementos en orden FIFO")
    void procesarTodos_retornaOrdenFIFO() {
        ArrayList<String> procesados = Ejercicio08_LinkedListComoQueue.procesarTodos(cola);
        assertThat(procesados).containsExactly("Cliente-A", "Cliente-B", "Cliente-C");
    }

    @Test
    @DisplayName("procesarTodos() deja la cola vacía")
    void procesarTodos_colaQuedaVacia() {
        Ejercicio08_LinkedListComoQueue.procesarTodos(cola);
        assertThat(cola).isEmpty();
    }

    // ── transferir ────────────────────────────────────────────────────────────

    @Test
    @DisplayName("transferir() mueve todos los elementos de origen a destino en el mismo orden")
    void transferir_mismoOrden() {
        LinkedList<String> destino = new LinkedList<>();
        Ejercicio08_LinkedListComoQueue.transferir(cola, destino);
        assertThat(destino).containsExactly("Cliente-A", "Cliente-B", "Cliente-C");
    }

    @Test
    @DisplayName("transferir() vacía el origen")
    void transferir_origenQuedaVacio() {
        LinkedList<String> destino = new LinkedList<>();
        Ejercicio08_LinkedListComoQueue.transferir(cola, destino);
        assertThat(cola).isEmpty();
    }

    // ── simularServidor ───────────────────────────────────────────────────────

    @Test
    @DisplayName("simularServidor() procesa las peticiones en parejas y retorna el número de rondas")
    void simularServidor_conteoRondasCorrecto() {
        LinkedList<String> peticiones = new LinkedList<>();
        for (String c : new String[]{"Ana", "Luis", "Marta", "Pedro", "Eva"}) {
            peticiones.offer(c);
        }
        // 5 peticiones: pareja 1 (Ana+Luis), pareja 2 (Marta+Pedro), última (Eva) → 3 rondas
        int rondas = Ejercicio08_LinkedListComoQueue.simularServidor(peticiones);
        assertThat(rondas).isEqualTo(3);
    }

    @Test
    @DisplayName("simularServidor() vacía la cola completamente")
    void simularServidor_colaVaciaAlTerminar() {
        LinkedList<String> peticiones = new LinkedList<>();
        peticiones.offer("X");
        peticiones.offer("Y");
        Ejercicio08_LinkedListComoQueue.simularServidor(peticiones);
        assertThat(peticiones).isEmpty();
    }

    @Test
    @DisplayName("simularServidor() con 4 peticiones (par) realiza exactamente 2 rondas")
    void simularServidor_numeroPar_dosRondas() {
        LinkedList<String> peticiones = new LinkedList<>();
        peticiones.offer("A");
        peticiones.offer("B");
        peticiones.offer("C");
        peticiones.offer("D");
        assertThat(Ejercicio08_LinkedListComoQueue.simularServidor(peticiones)).isEqualTo(2);
    }
}
