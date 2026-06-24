package com.masterclass.api.b44_nui;

import org.junit.jupiter.api.Test;

import java.util.List;

import com.masterclass.api.b44_nui.Ej340GestureStateMachine.EstadoGesto;
import com.masterclass.api.b44_nui.Ej340GestureStateMachine.EventoGesto;
import com.masterclass.api.b44_nui.Ej340GestureStateMachine.Gesto;

import static org.junit.jupiter.api.Assertions.*;

class Ej340GestureStateMachineTest {

    @Test
    void clasificarGesto() {
        assertEquals(Gesto.SWIPE_DER,
                Ej340GestureStateMachine.clasificarGesto(List.of(new Punto(0, 0), new Punto(100, 2))));
        assertEquals(Gesto.TAP,
                Ej340GestureStateMachine.clasificarGesto(List.of(new Punto(0, 0), new Punto(2, 2))));
        assertEquals(Gesto.DESCONOCIDO,
                Ej340GestureStateMachine.clasificarGesto(List.of())); // caso límite
    }

    @Test
    void estadoSiguiente() {
        assertEquals(EstadoGesto.PRESSED,
                Ej340GestureStateMachine.estadoSiguiente(EstadoGesto.IDLE, EventoGesto.DOWN));
        assertEquals(EstadoGesto.RELEASED,
                Ej340GestureStateMachine.estadoSiguiente(EstadoGesto.PRESSED, EventoGesto.UP));
        assertEquals(EstadoGesto.IDLE,
                Ej340GestureStateMachine.estadoSiguiente(EstadoGesto.IDLE, EventoGesto.UP)); // transición inválida
    }

    @Test
    void retoExtra01_esFlick() {
        assertTrue(Ej340GestureStateMachine.esFlick(new Punto(0, 0), new Punto(100, 0), 0.1, 500));
        assertFalse(Ej340GestureStateMachine.esFlick(new Punto(0, 0), new Punto(100, 0), 1.0, 500));
    }

    @Test
    void retoExtra02_distanciaTrayectoria() {
        assertEquals(7.0,
                Ej340GestureStateMachine.distanciaTrayectoria(List.of(new Punto(0, 0), new Punto(3, 0), new Punto(3, 4))),
                1e-9);
        assertEquals(0.0,
                Ej340GestureStateMachine.distanciaTrayectoria(List.of(new Punto(1, 1))), 1e-9); // caso límite
    }

    @Test
    void retoExtra03_esHold() {
        assertTrue(Ej340GestureStateMachine.esHold(1.0, 0.5));
        assertFalse(Ej340GestureStateMachine.esHold(0.2, 0.5));
    }

    @Test
    void retoExtra04_esDobleTap() {
        assertTrue(Ej340GestureStateMachine.esDobleTap(0.2, 0.3));
        assertFalse(Ej340GestureStateMachine.esDobleTap(0.5, 0.3));
    }

    @Test
    void retoExtra05_anguloMovimiento() {
        assertEquals(90.0, Ej340GestureStateMachine.anguloMovimiento(new Punto(0, 0), new Punto(0, 5)), 1e-9);
        assertEquals(0.0, Ej340GestureStateMachine.anguloMovimiento(new Punto(0, 0), new Punto(5, 0)), 1e-9);
    }

    @Test
    void retoExtra06_dentroDeZonaMuerta() {
        assertTrue(Ej340GestureStateMachine.dentroDeZonaMuerta(new Punto(0, 0), new Punto(2, 0), 3));
        assertFalse(Ej340GestureStateMachine.dentroDeZonaMuerta(new Punto(0, 0), new Punto(2, 0), 1));
    }

    @Test
    void retoExtra07_cancelarGesto() {
        assertEquals(EstadoGesto.IDLE, Ej340GestureStateMachine.cancelarGesto(EstadoGesto.DRAG));
    }

    @Test
    void retoExtra08_cuentaPunteros() {
        assertEquals("simple", Ej340GestureStateMachine.cuentaPunteros(1));
        assertEquals("multiple", Ej340GestureStateMachine.cuentaPunteros(2));
    }

    @Test
    void retoExtra09_suavizarTrayectoria() {
        assertEquals(List.of(new Punto(0, 0), new Punto(2, 2)),
                Ej340GestureStateMachine.suavizarTrayectoria(List.of(new Punto(0, 0), new Punto(2, 2)), 1));
        assertEquals(List.of(), Ej340GestureStateMachine.suavizarTrayectoria(List.of(), 1)); // caso límite
    }

    @Test
    void retoExtra10_velocidadMedia() {
        assertEquals(50.0, Ej340GestureStateMachine.velocidadMedia(100, 2), 1e-9);
        assertEquals(0.0, Ej340GestureStateMachine.velocidadMedia(100, 0), 1e-9); // caso límite
    }
}
