package com.masterclass.api.b34_fxfxml;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import javafx.scene.Parent;
import javafx.scene.control.Button;

import static org.junit.jupiter.api.Assertions.*;

class Ej272ControllerInjectionTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    @Test
    void cargarControlador() throws Exception {
        Controlador272 c = Ej272ControllerInjection.cargarControlador(Ej272ControllerInjection.VISTA_272);
        assertNotNull(c);
        assertNotNull(c.getUsuario()); // el @FXML se inyectó tras load()
        assertNull(Ej272ControllerInjection.cargarControlador(null)); // caso límite: ruta null
    }

    @Test
    void inyeccionCorrecta() throws Exception {
        Controlador272 cargado = Ej272ControllerInjection.cargarControlador(Ej272ControllerInjection.VISTA_272);
        assertTrue(Ej272ControllerInjection.inyeccionCorrecta(cargado));
        assertFalse(Ej272ControllerInjection.inyeccionCorrecta(new Controlador272())); // límite: sin cargar, todo null
    }

    @Test
    void retoExtra01_dispararAccion() throws Exception {
        Controlador272 c = Ej272ControllerInjection.cargarControlador(Ej272ControllerInjection.VISTA_272);
        assertEquals(1, Ej272ControllerInjection.dispararAccion(c));
    }

    @Test
    void retoExtra02_saludoTras() throws Exception {
        Controlador272 c = Ej272ControllerInjection.cargarControlador(Ej272ControllerInjection.VISTA_272);
        assertEquals("Hola, Ana", Ej272ControllerInjection.saludoTras(c, "Ana"));
    }

    @Test
    void retoExtra03_buscarPorId() throws Exception {
        Parent raiz = Ej271FxmlLoaderBasics.cargarVista(Ej272ControllerInjection.VISTA_272);
        assertNotNull(Ej272ControllerInjection.buscarPorId(raiz, "usuario"));
    }

    @Test
    void retoExtra04_tipoDeNodo() throws Exception {
        Parent raiz = Ej271FxmlLoaderBasics.cargarVista(Ej272ControllerInjection.VISTA_272);
        assertEquals("TextField", Ej272ControllerInjection.tipoDeNodo(raiz, "usuario"));
    }

    @Test
    void retoExtra05_saludoInicialVacio() throws Exception {
        Controlador272 c = Ej272ControllerInjection.cargarControlador(Ej272ControllerInjection.VISTA_272);
        assertTrue(Ej272ControllerInjection.saludoInicialVacio(c)); // initialize() puso el saludo a ""
    }

    @Test
    void retoExtra06_camposNulosSinCargar() {
        assertTrue(Ej272ControllerInjection.camposNulosSinCargar());
    }

    @Test
    void retoExtra07_idsDelArbol() throws Exception {
        Parent raiz = Ej271FxmlLoaderBasics.cargarVista(Ej272ControllerInjection.VISTA_272);
        assertEquals(List.of("aceptar", "saludo", "usuario"), Ej272ControllerInjection.idsDelArbol(raiz));
    }

    @Test
    void retoExtra08_contarDisparos() {
        Button b = new Button("X");
        assertEquals(3, Ej272ControllerInjection.contarDisparos(b, 3));
    }

    @Test
    void retoExtra09_textoDeBoton() throws Exception {
        Parent raiz = Ej271FxmlLoaderBasics.cargarVista(Ej272ControllerInjection.VISTA_272);
        assertEquals("Saludar", Ej272ControllerInjection.textoDeBoton(raiz, "aceptar"));
    }

    @Test
    void retoExtra10_cargarYActuar() throws Exception {
        assertEquals("Hola, Mundo", Ej272ControllerInjection.cargarYActuar(Ej272ControllerInjection.VISTA_272, "Mundo"));
    }
}
