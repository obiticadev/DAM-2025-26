package com.masterclass.api.b34_fxfxml;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;

import static org.junit.jupiter.api.Assertions.*;

class Ej274MouseKeyboardEventsTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    private VBox padreCon(Button hijo) {
        VBox padre = new VBox();
        padre.getChildren().add(hijo);
        return padre;
    }

    @Test
    void ordenDePropagacion() {
        Button hijo = new Button("hijo");
        VBox padre = padreCon(hijo);
        // Disparado en el hijo: filtro del padre (captura) -> handler del hijo (objetivo) -> handler del padre (burbuja).
        assertEquals(List.of("filtro-padre", "handler-hijo", "handler-padre"),
                Ej274MouseKeyboardEvents.ordenDePropagacion(padre, hijo, true));

        Button hijo2 = new Button("hijo");
        VBox padre2 = padreCon(hijo2);
        // Caso límite: disparado en el padre -> el handler del hijo NO entra (no está en la cadena).
        assertEquals(List.of("filtro-padre", "handler-padre"),
                Ej274MouseKeyboardEvents.ordenDePropagacion(padre2, hijo2, false));
    }

    @Test
    void descripcionRaton() {
        assertEquals("PRIMARY:2",
                Ej274MouseKeyboardEvents.descripcionRaton(Ej274MouseKeyboardEvents.clic(MouseButton.PRIMARY, 2, 0, 0, false)));
        assertEquals("", Ej274MouseKeyboardEvents.descripcionRaton(null)); // caso límite: evento null
    }

    @Test
    void retoExtra01_esClicPrimario() {
        assertTrue(Ej274MouseKeyboardEvents.esClicPrimario(Ej274MouseKeyboardEvents.clic(MouseButton.PRIMARY, 1, 0, 0, false)));
        assertFalse(Ej274MouseKeyboardEvents.esClicPrimario(Ej274MouseKeyboardEvents.clic(MouseButton.SECONDARY, 1, 0, 0, false)));
    }

    @Test
    void retoExtra02_esDobleClic() {
        assertTrue(Ej274MouseKeyboardEvents.esDobleClic(Ej274MouseKeyboardEvents.clic(MouseButton.PRIMARY, 2, 0, 0, false)));
        assertFalse(Ej274MouseKeyboardEvents.esDobleClic(Ej274MouseKeyboardEvents.clic(MouseButton.PRIMARY, 1, 0, 0, false)));
    }

    @Test
    void retoExtra03_teclaPulsada() {
        assertEquals("Enter", Ej274MouseKeyboardEvents.teclaPulsada(Ej274MouseKeyboardEvents.tecla(KeyCode.ENTER, false)));
    }

    @Test
    void retoExtra04_conControl() {
        assertTrue(Ej274MouseKeyboardEvents.conControl(Ej274MouseKeyboardEvents.clic(MouseButton.PRIMARY, 1, 0, 0, true)));
        assertFalse(Ej274MouseKeyboardEvents.conControl(Ej274MouseKeyboardEvents.clic(MouseButton.PRIMARY, 1, 0, 0, false)));
    }

    @Test
    void retoExtra05_llegaAlHijoTrasConsumir() {
        Button hijo = new Button("hijo");
        VBox padre = padreCon(hijo);
        assertFalse(Ej274MouseKeyboardEvents.llegaAlHijoTrasConsumir(padre, hijo));
    }

    @Test
    void retoExtra06_ordenFiltroHandlerMismoNodo() {
        assertEquals(List.of("filtro", "handler"),
                Ej274MouseKeyboardEvents.ordenFiltroHandlerMismoNodo(new Button("x")));
    }

    @Test
    void retoExtra07_esAtajoGuardar() {
        assertTrue(Ej274MouseKeyboardEvents.esAtajoGuardar(Ej274MouseKeyboardEvents.tecla(KeyCode.S, true)));
        assertFalse(Ej274MouseKeyboardEvents.esAtajoGuardar(Ej274MouseKeyboardEvents.tecla(KeyCode.S, false)));
        assertFalse(Ej274MouseKeyboardEvents.esAtajoGuardar(Ej274MouseKeyboardEvents.tecla(KeyCode.A, true)));
    }

    @Test
    void retoExtra08_posicionRaton() {
        assertEquals("10,20", Ej274MouseKeyboardEvents.posicionRaton(Ej274MouseKeyboardEvents.clic(MouseButton.PRIMARY, 1, 10, 20, false)));
    }

    @Test
    void retoExtra09_burbujaFrenadaEnObjetivo() {
        Button hijo = new Button("hijo");
        VBox padre = padreCon(hijo);
        assertEquals(List.of("handler-hijo"),
                Ej274MouseKeyboardEvents.burbujaFrenadaEnObjetivo(padre, hijo));
    }

    @Test
    void retoExtra10_accionPorTecla() {
        assertEquals("aceptar", Ej274MouseKeyboardEvents.accionPorTecla(Ej274MouseKeyboardEvents.tecla(KeyCode.ENTER, false)));
        assertEquals("cancelar", Ej274MouseKeyboardEvents.accionPorTecla(Ej274MouseKeyboardEvents.tecla(KeyCode.ESCAPE, false)));
        assertEquals("", Ej274MouseKeyboardEvents.accionPorTecla(Ej274MouseKeyboardEvents.tecla(KeyCode.A, false)));
    }
}
