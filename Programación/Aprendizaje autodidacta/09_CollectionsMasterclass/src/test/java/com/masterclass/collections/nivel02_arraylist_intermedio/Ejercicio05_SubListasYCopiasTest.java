package com.masterclass.collections.nivel02_arraylist_intermedio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 05 — SubListas, Copias y Operaciones Bulk")
class Ejercicio05_SubListasYCopiasTest {

    // ── copiaIndependiente ──────────────────────────────────────────────────

    @Test
    @DisplayName("copiaIndependiente() retorna un ArrayList con los mismos elementos")
    void copiaIndependiente_mismosElementos() {
        ArrayList<String> original = new ArrayList<>(List.of("A", "B", "C"));
        ArrayList<String> copia = Ejercicio05_SubListasYCopias.copiaIndependiente(original);
        assertThat(copia).containsExactly("A", "B", "C");
    }

    @Test
    @DisplayName("copiaIndependiente() retorna un objeto diferente (no la misma referencia)")
    void copiaIndependiente_esObjetoDiferente() {
        ArrayList<String> original = new ArrayList<>(List.of("A", "B", "C"));
        ArrayList<String> copia = Ejercicio05_SubListasYCopias.copiaIndependiente(original);
        assertThat(copia).isNotSameAs(original);
    }

    @Test
    @DisplayName("copiaIndependiente() — modificar la copia no afecta al original")
    void copiaIndependiente_modificarCopiaNoAfectaOriginal() {
        ArrayList<String> original = new ArrayList<>(List.of("A", "B", "C"));
        ArrayList<String> copia = Ejercicio05_SubListasYCopias.copiaIndependiente(original);
        copia.add("D");
        copia.set(0, "CAMBIADO");
        assertThat(original).containsExactly("A", "B", "C");
    }

    // ── fusionar ────────────────────────────────────────────────────────────

    @Test
    @DisplayName("fusionar() retorna todos los elementos de a seguidos de b")
    void fusionar_retornaEnOrdenAB() {
        ArrayList<String> a = new ArrayList<>(List.of("1", "2", "3"));
        ArrayList<String> b = new ArrayList<>(List.of("4", "5"));
        ArrayList<String> resultado = Ejercicio05_SubListasYCopias.fusionar(a, b);
        assertThat(resultado).containsExactly("1", "2", "3", "4", "5");
    }

    @Test
    @DisplayName("fusionar() no modifica las listas originales")
    void fusionar_noModificaOriginales() {
        ArrayList<String> a = new ArrayList<>(List.of("X"));
        ArrayList<String> b = new ArrayList<>(List.of("Y"));
        Ejercicio05_SubListasYCopias.fusionar(a, b);
        assertThat(a).containsExactly("X");
        assertThat(b).containsExactly("Y");
    }

    // ── interseccion ────────────────────────────────────────────────────────

    @Test
    @DisplayName("interseccion() retorna los elementos comunes a ambas listas")
    void interseccion_elementosComunes() {
        ArrayList<String> a = new ArrayList<>(List.of("A", "B", "C", "D"));
        ArrayList<String> b = new ArrayList<>(List.of("C", "D", "E", "F"));
        ArrayList<String> resultado = Ejercicio05_SubListasYCopias.interseccion(a, b);
        assertThat(resultado).containsExactlyInAnyOrder("C", "D");
    }

    @Test
    @DisplayName("interseccion() sin elementos comunes retorna lista vacía")
    void interseccion_sinComunes_retornaVacia() {
        ArrayList<String> a = new ArrayList<>(List.of("A", "B"));
        ArrayList<String> b = new ArrayList<>(List.of("X", "Y"));
        assertThat(Ejercicio05_SubListasYCopias.interseccion(a, b)).isEmpty();
    }

    @Test
    @DisplayName("interseccion() no modifica las listas originales")
    void interseccion_noModificaOriginales() {
        ArrayList<String> a = new ArrayList<>(List.of("A", "B", "C"));
        ArrayList<String> b = new ArrayList<>(List.of("B", "C", "D"));
        Ejercicio05_SubListasYCopias.interseccion(a, b);
        assertThat(a).containsExactly("A", "B", "C");
        assertThat(b).containsExactly("B", "C", "D");
    }

    // ── diferencia ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("diferencia() retorna elementos de a que no están en b")
    void diferencia_elementosSoloEnA() {
        ArrayList<String> a = new ArrayList<>(List.of("A", "B", "C", "D"));
        ArrayList<String> b = new ArrayList<>(List.of("C", "D", "E"));
        ArrayList<String> resultado = Ejercicio05_SubListasYCopias.diferencia(a, b);
        assertThat(resultado).containsExactlyInAnyOrder("A", "B");
    }

    @Test
    @DisplayName("diferencia() no modifica las listas originales")
    void diferencia_noModificaOriginales() {
        ArrayList<String> a = new ArrayList<>(List.of("A", "B", "C"));
        ArrayList<String> b = new ArrayList<>(List.of("B"));
        Ejercicio05_SubListasYCopias.diferencia(a, b);
        assertThat(a).containsExactly("A", "B", "C");
    }

    // ── llenarConValor ──────────────────────────────────────────────────────

    @Test
    @DisplayName("llenarConValor() crea una lista del tamaño indicado con el valor repetido")
    void llenarConValor_creaTamanoCorrecto() {
        ArrayList<String> resultado = Ejercicio05_SubListasYCopias.llenarConValor("X", 5);
        assertThat(resultado).hasSize(5).containsOnly("X");
    }

    @Test
    @DisplayName("llenarConValor() con cantidad 0 retorna lista vacía")
    void llenarConValor_cantidadCero_retornaVacia() {
        assertThat(Ejercicio05_SubListasYCopias.llenarConValor("Y", 0)).isEmpty();
    }

    // ── listaInmutable ──────────────────────────────────────────────────────

    @Test
    @DisplayName("listaInmutable() lanza UnsupportedOperationException al intentar add()")
    void listaInmutable_addLanzaExcepcion() {
        ArrayList<String> lista = new ArrayList<>(List.of("A", "B"));
        var inmutable = Ejercicio05_SubListasYCopias.listaInmutable(lista);
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> inmutable.add("C"));
    }

    @Test
    @DisplayName("listaInmutable() lanza UnsupportedOperationException al intentar remove()")
    void listaInmutable_removeLanzaExcepcion() {
        ArrayList<String> lista = new ArrayList<>(List.of("A", "B"));
        var inmutable = Ejercicio05_SubListasYCopias.listaInmutable(lista);
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> inmutable.remove(0));
    }

    @Test
    @DisplayName("listaInmutable() permite lectura sin excepciones")
    void listaInmutable_lecturaFunciona() {
        ArrayList<String> lista = new ArrayList<>(List.of("A", "B", "C"));
        var inmutable = Ejercicio05_SubListasYCopias.listaInmutable(lista);
        assertThat(inmutable).containsExactly("A", "B", "C");
    }
}
