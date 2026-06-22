package com.masterclass.api.b34_fxfxml;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URL;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import static org.junit.jupiter.api.Assertions.*;

class Ej271FxmlLoaderBasicsTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    @Test
    void cargarVista() throws Exception {
        Parent raiz = Ej271FxmlLoaderBasics.cargarVista(Ej271FxmlLoaderBasics.VISTA_271);
        assertNotNull(raiz);
        assertInstanceOf(VBox.class, raiz);
        assertNull(Ej271FxmlLoaderBasics.cargarVista(null)); // caso límite: ruta null
    }

    @Test
    void crearLoaderDe() throws Exception {
        var loader = Ej271FxmlLoaderBasics.crearLoaderDe(Ej271FxmlLoaderBasics.VISTA_271);
        assertNotNull(loader);
        loader.load();
        assertInstanceOf(Controlador271.class, loader.getController());
        assertNull(Ej271FxmlLoaderBasics.crearLoaderDe("/com/masterclass/api/b34_fxfxml/noexiste.fxml")); // límite
    }

    @Test
    void retoExtra01_existeRecurso() {
        assertTrue(Ej271FxmlLoaderBasics.existeRecurso(Ej271FxmlLoaderBasics.VISTA_271));
        assertFalse(Ej271FxmlLoaderBasics.existeRecurso("/com/masterclass/api/b34_fxfxml/noexiste.fxml"));
    }

    @Test
    void retoExtra02_rutaDeVista() {
        assertEquals(Ej271FxmlLoaderBasics.VISTA_271, Ej271FxmlLoaderBasics.rutaDeVista("vista271"));
    }

    @Test
    void retoExtra03_cargarYContarHijos() throws Exception {
        assertEquals(2, Ej271FxmlLoaderBasics.cargarYContarHijos(Ej271FxmlLoaderBasics.VISTA_271));
    }

    @Test
    void retoExtra04_cargarComo() throws Exception {
        VBox raiz = Ej271FxmlLoaderBasics.cargarComo(Ej271FxmlLoaderBasics.VISTA_271, VBox.class);
        assertNotNull(raiz);
    }

    @Test
    void retoExtra05_controladorDe() throws Exception {
        assertInstanceOf(Controlador271.class, Ej271FxmlLoaderBasics.controladorDe(Ej271FxmlLoaderBasics.VISTA_271));
    }

    @Test
    void retoExtra06_cargarDesdeTexto() throws Exception {
        Parent raiz = Ej271FxmlLoaderBasics.cargarDesdeTexto(Ej271FxmlLoaderBasics.fxmlDeUnLabel());
        assertInstanceOf(Label.class, raiz);
    }

    @Test
    void retoExtra07_recargarDistintas() throws Exception {
        assertTrue(Ej271FxmlLoaderBasics.recargarDistintas(Ej271FxmlLoaderBasics.VISTA_271));
    }

    @Test
    void retoExtra08_ubicacionDe() {
        URL url = Ej271FxmlLoaderBasics.ubicacionDe(Ej271FxmlLoaderBasics.VISTA_271);
        assertNotNull(url);
        assertTrue(url.toString().endsWith("vista271.fxml"));
    }

    @Test
    void retoExtra09_cargarConRaiz() throws Exception {
        VBox raizExterna = new VBox();
        Parent r = Ej271FxmlLoaderBasics.cargarConRaiz("/com/masterclass/api/b34_fxfxml/vistaRoot271.fxml", raizExterna);
        assertSame(raizExterna, r);
        assertEquals(1, raizExterna.getChildren().size());
    }

    @Test
    void retoExtra10_cargarConControladorFijo() throws Exception {
        Controlador271 fijo = new Controlador271();
        Object ctrl = Ej271FxmlLoaderBasics.cargarConControladorFijo(Ej271FxmlLoaderBasics.VISTA_271, fijo);
        assertSame(fijo, ctrl);
    }
}
