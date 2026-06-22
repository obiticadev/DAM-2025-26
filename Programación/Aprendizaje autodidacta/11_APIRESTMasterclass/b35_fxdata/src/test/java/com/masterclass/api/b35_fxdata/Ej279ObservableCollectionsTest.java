package com.masterclass.api.b35_fxdata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import static org.junit.jupiter.api.Assertions.*;

class Ej279ObservableCollectionsTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    @Test
    void agregadosNotificados() {
        ObservableList<String> lista = FXCollections.observableArrayList("Ana");
        assertEquals(List.of("Berta", "Caro"),
                Ej279ObservableCollections.agregadosNotificados(lista, List.of("Berta", "Caro")));
        assertEquals(List.of(),
                Ej279ObservableCollections.agregadosNotificados(FXCollections.observableArrayList(), List.of())); // caso límite
    }

    @Test
    void filtrarObservable() {
        ObservableList<String> fuente = FXCollections.observableArrayList("a", "bb", "ccc");
        FilteredList<String> filtrada = Ej279ObservableCollections.filtrarObservable(fuente, s -> s.length() > 1);
        assertEquals(2, filtrada.size());
        fuente.add("dddd"); // la vista es viva: crece sola
        assertEquals(3, filtrada.size());
        assertEquals(0, Ej279ObservableCollections.filtrarObservable(fuente, s -> false).size()); // caso límite
    }

    @Test
    void retoExtra01_crearObservable() {
        assertEquals(List.of("x", "y"), Ej279ObservableCollections.crearObservable(List.of("x", "y")));
    }

    @Test
    void retoExtra02_tamanoTrasAgregar() {
        assertEquals(2, Ej279ObservableCollections.tamanoTrasAgregar(FXCollections.observableArrayList("a"), "b"));
    }

    @Test
    void retoExtra03_soloLectura() {
        ObservableList<String> ro = Ej279ObservableCollections.soloLectura(FXCollections.observableArrayList("a"));
        assertThrows(UnsupportedOperationException.class, () -> ro.add("b"));
    }

    @Test
    void retoExtra04_ordenarEnSitio() {
        assertEquals(List.of("a", "b", "c"),
                Ej279ObservableCollections.ordenarEnSitio(FXCollections.observableArrayList("c", "a", "b")));
    }

    @Test
    void retoExtra05_invertir() {
        assertEquals(List.of("c", "b", "a"),
                Ej279ObservableCollections.invertir(FXCollections.observableArrayList("a", "b", "c")));
    }

    @Test
    void retoExtra06_contarEliminaciones() {
        ObservableList<String> lista = FXCollections.observableArrayList("a", "b", "c");
        assertEquals(2, Ej279ObservableCollections.contarEliminaciones(lista, List.of("a", "c")));
    }

    @Test
    void retoExtra07_contarFiltrados() {
        assertEquals(1, Ej279ObservableCollections.contarFiltrados(
                FXCollections.observableArrayList("a", "bb", "ccc"), s -> s.length() > 2));
    }

    @Test
    void retoExtra08_recontarTrasPredicado() {
        assertEquals(2, Ej279ObservableCollections.recontarTrasPredicado(
                FXCollections.observableArrayList("a", "bb", "ccc"), s -> s.length() <= 2));
    }

    @Test
    void retoExtra09_ordenarLigado() {
        ObservableList<String> fuente = FXCollections.observableArrayList("c", "a", "b");
        SortedList<String> ordenada = Ej279ObservableCollections.ordenarLigado(fuente);
        assertEquals("a", ordenada.get(0));     // la vista está ordenada
        assertEquals("c", fuente.get(0));        // la fuente NO se tocó
    }

    @Test
    void retoExtra10_notificacionesConExtractor() {
        List<Ej279ObservableCollections.Fila> filas = List.of(
                new Ej279ObservableCollections.Fila("uno"),
                new Ej279ObservableCollections.Fila("dos"));
        assertEquals(1, Ej279ObservableCollections.notificacionesConExtractor(filas, 0, "UNO"));
    }
}
