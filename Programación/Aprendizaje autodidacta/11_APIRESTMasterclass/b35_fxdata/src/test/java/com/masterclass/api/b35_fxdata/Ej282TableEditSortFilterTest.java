package com.masterclass.api.b35_fxdata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import static org.junit.jupiter.api.Assertions.*;

class Ej282TableEditSortFilterTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    private static ObservableList<ClienteFx> fuente() {
        return FXCollections.observableArrayList(
                new ClienteFx("Ana", "ana@correo.com", 30),
                new ClienteFx("Bruno", "bruno@correo.com", 40));
    }

    @Test
    void aplicarFiltro() {
        assertEquals(1, Ej282TableEditSortFilter.aplicarFiltro(fuente(), "an").size());
        assertEquals(2, Ej282TableEditSortFilter.aplicarFiltro(fuente(), "").size()); // caso límite: filtro vacío = todos
    }

    @Test
    void commitEdicion() {
        ClienteFx ana = new ClienteFx("Ana", "ana@correo.com", 30);
        assertEquals("Anabel", Ej282TableEditSortFilter.commitEdicion(ana, "nombre", "Anabel"));
        assertNull(Ej282TableEditSortFilter.commitEdicion(ana, "desconocido", "x")); // caso límite: campo no válido
    }

    @Test
    void retoExtra01_contarFiltrados() {
        assertEquals(1, Ej282TableEditSortFilter.contarFiltrados(fuente(), "an"));
    }

    @Test
    void retoExtra02_filtroVacio() {
        assertEquals(2, Ej282TableEditSortFilter.filtroVacio(fuente()));
    }

    @Test
    void retoExtra03_refiltrarPorInicial() {
        assertEquals(1, Ej282TableEditSortFilter.refiltrarPorInicial(fuente(), 'a'));
    }

    @Test
    void retoExtra04_primeroOrdenadoPorNombre() {
        ObservableList<ClienteFx> f = FXCollections.observableArrayList(
                new ClienteFx("Zoe", "z@z.com", 20), new ClienteFx("Ana", "a@a.com", 30));
        assertEquals("Ana", Ej282TableEditSortFilter.primeroOrdenadoPorNombre(f));
    }

    @Test
    void retoExtra05_filtrarYOrdenarPorEdad() {
        // ambos contienen "o" (bruno, ...); añadimos un tercero para que el orden por edad importe
        ObservableList<ClienteFx> f = FXCollections.observableArrayList(
                new ClienteFx("Bruno", "b@b.com", 40), new ClienteFx("Rodo", "r@r.com", 22));
        assertEquals("Rodo", Ej282TableEditSortFilter.filtrarYOrdenarPorEdad(f, "o"));
    }

    @Test
    void retoExtra06_enlazarComparador() {
        assertTrue(Ej282TableEditSortFilter.enlazarComparador(new TableView<>(), fuente()));
    }

    @Test
    void retoExtra07_hacerEditable() {
        assertTrue(Ej282TableEditSortFilter.hacerEditable(new TableView<>(), new TableColumn<>("Nombre")));
    }

    @Test
    void retoExtra08_ponerEditorTexto() {
        assertTrue(Ej282TableEditSortFilter.ponerEditorTexto(new TableColumn<>("Nombre")));
    }

    @Test
    void retoExtra09_commitRefrescaFuente() {
        assertEquals("Anabel", Ej282TableEditSortFilter.commitRefrescaFuente(fuente(), 0, "Anabel"));
    }

    @Test
    void retoExtra10_editarSacaDelFiltro() {
        // parte de [Ana, Bruno]: solo Ana empieza por A; al renombrar Ana a Zoe, el filtro "empieza por A" queda en 0
        assertEquals(0, Ej282TableEditSortFilter.editarSacaDelFiltro(fuente(), "Zoe"));
    }
}
