package com.masterclass.api.b35_fxdata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import static org.junit.jupiter.api.Assertions.*;

class Ej281TableViewColumnsTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    private static ClienteFx ana() {
        return new ClienteFx("Ana", "ana@correo.com", 30);
    }

    @Test
    void columnaDe() {
        TableColumn<ClienteFx, String> col = Ej281TableViewColumns.columnaDe("nombre");
        assertEquals("Ana", col.getCellObservableValue(ana()).getValue());
        TableColumn<ClienteFx, String> colEmail = Ej281TableViewColumns.columnaDe("email"); // caso límite: otra propiedad
        assertEquals("ana@correo.com", colEmail.getCellObservableValue(ana()).getValue());
    }

    @Test
    void valorDeFila() {
        TableColumn<ClienteFx, String> col = new TableColumn<>();
        col.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        assertEquals("Ana", Ej281TableViewColumns.valorDeFila(col, ana()));
        assertNull(Ej281TableViewColumns.valorDeFila(new TableColumn<ClienteFx, String>(), ana())); // caso límite: sin factory
    }

    @Test
    void retoExtra01_crearTabla() {
        TableView<ClienteFx> tabla = Ej281TableViewColumns.crearTabla(List.of(ana()));
        assertEquals(1, tabla.getItems().size());
    }

    @Test
    void retoExtra02_agregarColumna() {
        assertEquals(1, Ej281TableViewColumns.agregarColumna(new TableView<>(), new TableColumn<>("Nombre")));
    }

    @Test
    void retoExtra03_columnaConTitulo() {
        assertEquals("Correo", Ej281TableViewColumns.columnaConTitulo("Correo", "email"));
    }

    @Test
    void retoExtra04_valorPorPropiedad() {
        assertEquals("ana@correo.com", Ej281TableViewColumns.valorPorPropiedad(ana(), "email"));
    }

    @Test
    void retoExtra05_valorConLambda() {
        assertEquals("Ana", Ej281TableViewColumns.valorConLambda(ana(), ClienteFx::getNombre));
    }

    @Test
    void retoExtra06_valorCalculado() {
        assertEquals("Ana (30)", Ej281TableViewColumns.valorCalculado(ana()));
    }

    @Test
    void retoExtra07_filaSeleccionada() {
        TableView<ClienteFx> tabla = new TableView<>(javafx.collections.FXCollections.observableArrayList(
                ana(), new ClienteFx("Berta", "b@b.com", 25)));
        assertEquals("Berta", Ej281TableViewColumns.filaSeleccionada(tabla, 1).getNombre());
    }

    @Test
    void retoExtra08_ajustarColumnas() {
        assertTrue(Ej281TableViewColumns.ajustarColumnas(new TableView<>()));
    }

    @Test
    void retoExtra09_ordenarPor() {
        TableView<ClienteFx> tabla = new TableView<>(javafx.collections.FXCollections.observableArrayList(
                new ClienteFx("Zoe", "z@z.com", 20), ana()));
        TableColumn<ClienteFx, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tabla.getColumns().add(colNombre);
        assertEquals("Ana", Ej281TableViewColumns.ordenarPor(tabla, colNombre));
    }

    @Test
    void retoExtra10_celdaReactiva() {
        assertEquals("Cambiado", Ej281TableViewColumns.celdaReactiva(ana(), "Cambiado"));
    }
}
