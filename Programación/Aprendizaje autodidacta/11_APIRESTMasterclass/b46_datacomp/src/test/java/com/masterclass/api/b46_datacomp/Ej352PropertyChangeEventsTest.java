package com.masterclass.api.b46_datacomp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej352PropertyChangeEventsTest {

    @Test
    void notificarCambio() {
        assertEquals(List.of("temperatura:20->25"),
                Ej352PropertyChangeEvents.notificarCambio("temperatura", 20, 25));
        assertEquals(List.of(),
                Ej352PropertyChangeEvents.notificarCambio("temperatura", 20, 20)); // no cambia
    }

    @Test
    void contarNotificaciones() {
        assertEquals(2, Ej352PropertyChangeEvents.contarNotificaciones(pcs -> {
            pcs.firePropertyChange("x", 1, 2);
            pcs.firePropertyChange("x", 2, 3);
        }));
        assertEquals(-1, Ej352PropertyChangeEvents.contarNotificaciones(null)); // caso límite
    }

    @Test
    void testRetoExtra01_dispararManual() {
        assertEquals(1, Ej352PropertyChangeEvents.dispararManual("p"));
    }

    @Test
    void testRetoExtra02_notificarAVariosListeners() {
        assertEquals(3, Ej352PropertyChangeEvents.notificarAVariosListeners(3));
    }

    @Test
    void testRetoExtra03_ordenDeNotificacion() {
        assertEquals("A,B,C", Ej352PropertyChangeEvents.ordenDeNotificacion());
    }

    @Test
    void testRetoExtra04_vetarCambio() {
        assertTrue(Ej352PropertyChangeEvents.vetarCambio(10, 200, 100));
        assertFalse(Ej352PropertyChangeEvents.vetarCambio(10, 50, 100));
    }

    @Test
    void testRetoExtra05_escucharSoloPropiedad() {
        assertEquals(1, Ej352PropertyChangeEvents.escucharSoloPropiedad("x"));
    }

    @Test
    void testRetoExtra06_quitarListenerYContar() {
        assertEquals(1, Ej352PropertyChangeEvents.quitarListenerYContar());
    }

    @Test
    void testRetoExtra07_notificacionIndexada() {
        assertEquals(2, Ej352PropertyChangeEvents.notificacionIndexada(2));
    }

    @Test
    void testRetoExtra08_debounce() {
        assertEquals(3, Ej352PropertyChangeEvents.debounce(List.of(1, 1, 2, 2, 3)));
    }

    @Test
    @Timeout(5)
    void testRetoExtra09_notificarEntreHilos() throws InterruptedException {
        assertEquals(42, Ej352PropertyChangeEvents.notificarEntreHilos(42));
    }

    @Test
    void testRetoExtra10_valorFinalTrasCambios() {
        assertEquals(3, Ej352PropertyChangeEvents.valorFinalTrasCambios(1, 2, 3));
    }
}
