package com.masterclass.api.b34_fxfxml;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import static org.junit.jupiter.api.Assertions.*;

class Ej273EventHandlersTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    @Test
    void contarDisparos() {
        assertEquals(3, Ej273EventHandlers.contarDisparos(new Button(), 3));
        assertEquals(0, Ej273EventHandlers.contarDisparos(new Button(), 0)); // caso límite: 0 disparos
    }

    @Test
    void textoDeLaFuente() {
        assertEquals("Hola", Ej273EventHandlers.textoDeLaFuente(new ActionEvent(new Button("Hola"), null)));
        assertEquals("", Ej273EventHandlers.textoDeLaFuente(new ActionEvent("no-es-boton", null))); // límite
    }

    @Test
    void retoExtra01_manejadorContador() {
        int[] c = {0};
        var h = Ej273EventHandlers.manejadorContador(c);
        h.handle(null);
        h.handle(null);
        assertEquals(2, c[0]);
    }

    @Test
    void retoExtra02_tieneAccion() {
        Button sin = new Button();
        Button con = new Button();
        con.setOnAction(e -> {
        });
        assertFalse(Ej273EventHandlers.tieneAccion(sin));
        assertTrue(Ej273EventHandlers.tieneAccion(con));
    }

    @Test
    void retoExtra03_fuenteAlDisparar() {
        Button b = new Button("x");
        assertSame(b, Ej273EventHandlers.fuenteAlDisparar(b));
    }

    @Test
    void retoExtra04_dispararSiHabilitado() {
        Button activo = new Button();
        Button desactivado = new Button();
        desactivado.setDisable(true);
        assertTrue(Ej273EventHandlers.dispararSiHabilitado(activo));
        assertFalse(Ej273EventHandlers.dispararSiHabilitado(desactivado));
    }

    @Test
    void retoExtra05_consumir() {
        assertTrue(Ej273EventHandlers.consumir(new ActionEvent()));
    }

    @Test
    void retoExtra06_variosManejadores() {
        assertEquals(3, Ej273EventHandlers.variosManejadores(new Button(), 3));
    }

    @Test
    void retoExtra07_tipoDeEvento() {
        assertEquals("ACTION", Ej273EventHandlers.tipoDeEvento(new ActionEvent()));
    }

    @Test
    void retoExtra08_textosEnOrden() {
        List<Button> botones = Ej273EventHandlers.botonesCon("a", "b");
        assertEquals(List.of("a", "b"), Ej273EventHandlers.textosEnOrden(botones));
    }

    @Test
    void retoExtra09_capturaFalloDeManejador() {
        assertTrue(Ej273EventHandlers.capturaFalloDeManejador(new Button()));
    }

    @Test
    void retoExtra10_contadorPorClics() {
        assertEquals(5, Ej273EventHandlers.contadorPorClics(new Button(), 5));
    }
}
