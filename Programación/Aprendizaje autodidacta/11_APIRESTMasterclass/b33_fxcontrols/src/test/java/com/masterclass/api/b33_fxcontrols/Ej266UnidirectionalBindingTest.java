package com.masterclass.api.b33_fxcontrols;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import static org.junit.jupiter.api.Assertions.*;

class Ej266UnidirectionalBindingTest {

    @Test
    void totalLineaPedido() {
        assertEquals(30, Ej266UnidirectionalBinding.totalLineaPedido(10, 3));
        assertEquals(0, Ej266UnidirectionalBinding.totalLineaPedido(10, 0)); // caso límite: cantidad 0
    }

    @Test
    void recalculoAutomatico() {
        // total definido con precio 10 y cantidad 2 (=20); cambia precio a 15 -> 30
        assertEquals(30, Ej266UnidirectionalBinding.recalculoAutomatico(10, 2, 15));
    }

    @Test
    void retoExtra01_enlazarDestino() {
        assertEquals("fuente", Ej266UnidirectionalBinding.enlazarDestino(
                new SimpleStringProperty("vacio"), new SimpleStringProperty("fuente")));
    }

    @Test
    void retoExtra02_dobleDe() {
        assertEquals(14, Ej266UnidirectionalBinding.dobleDe(new SimpleIntegerProperty(7)));
    }

    @Test
    void retoExtra03_suma() {
        assertEquals(12, Ej266UnidirectionalBinding.suma(new SimpleIntegerProperty(5), new SimpleIntegerProperty(7)));
    }

    @Test
    void retoExtra04_estaEnlazada() {
        StringProperty enlazada = new SimpleStringProperty();
        enlazada.bind(new SimpleStringProperty("x"));
        assertTrue(Ej266UnidirectionalBinding.estaEnlazada(enlazada));
        assertFalse(Ej266UnidirectionalBinding.estaEnlazada(new SimpleStringProperty("libre")));
    }

    @Test
    void retoExtra05_nombreCompleto() {
        assertEquals("Ada Lovelace", Ej266UnidirectionalBinding.nombreCompleto(
                new SimpleStringProperty("Ada"), new SimpleStringProperty("Lovelace")));
    }

    @Test
    void retoExtra06_mayorQue() {
        assertTrue(Ej266UnidirectionalBinding.mayorQue(new SimpleIntegerProperty(10), 5));
        assertFalse(Ej266UnidirectionalBinding.mayorQue(new SimpleIntegerProperty(5), 5)); // estricto
    }

    @Test
    void retoExtra07_longitud() {
        assertEquals(4, Ej266UnidirectionalBinding.longitud(new SimpleStringProperty("hola")));
    }

    @Test
    void retoExtra08_intentarAsignarEnlazada() {
        assertTrue(Ej266UnidirectionalBinding.intentarAsignarEnlazada(new SimpleIntegerProperty(1)));
    }

    @Test
    void retoExtra09_negar() {
        assertTrue(Ej266UnidirectionalBinding.negar(new SimpleBooleanProperty(false)));
    }

    @Test
    void retoExtra10_cadenaDependencias() {
        assertEquals(45, Ej266UnidirectionalBinding.cadenaDependencias(10, 20));
    }
}
