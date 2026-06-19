package com.masterclass.api.b29_sockets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej238ObjectOverSocketTest {

    @Test
    void enviarPunto() {
        assertEquals(new Ej238ObjectOverSocket.Punto(3, 4), Ej238ObjectOverSocket.enviarPunto(3, 4));
    }

    @Test
    void enviarLista() {
        assertEquals(List.of("a", "b"), Ej238ObjectOverSocket.enviarLista(List.of("a", "b")));
    }

    @Test
    void retoExtra01_transientNoSeSerializa() {
        assertTrue(Ej238ObjectOverSocket.transientNoSeSerializa());
    }

    @Test
    void retoExtra02_objetoNuloViaja() {
        assertTrue(Ej238ObjectOverSocket.objetoNuloViaja());
    }

    @Test
    void retoExtra03_grafoDeObjetosViaja() {
        assertTrue(Ej238ObjectOverSocket.grafoDeObjetosViaja());
    }

    @Test
    void retoExtra04_referenciaCompartidaSePreserva() {
        assertTrue(Ej238ObjectOverSocket.referenciaCompartidaSePreserva());
    }

    @Test
    void retoExtra05_resetRompeLaComparticion() {
        assertTrue(Ej238ObjectOverSocket.resetRompeLaComparticion());
    }

    @Test
    void retoExtra06_claseNoSerializableLanzaExcepcion() {
        assertTrue(Ej238ObjectOverSocket.claseNoSerializableLanzaExcepcion());
    }

    @Test
    void retoExtra07_enteroComoObjeto() {
        assertEquals(99, Ej238ObjectOverSocket.enteroComoObjeto());
    }

    @Test
    void retoExtra08_arrayDeBytesComoObjeto() {
        assertArrayEquals(new byte[]{10, 20, 30}, Ej238ObjectOverSocket.arrayDeBytesComoObjeto());
    }

    @Test
    void retoExtra09_tamanoSerializadoMayorQueCero() {
        assertTrue(Ej238ObjectOverSocket.tamanoSerializadoMayorQueCero());
    }

    @Test
    void retoExtra10_recordRoundTripIgual() {
        assertTrue(Ej238ObjectOverSocket.recordRoundTripIgual());
    }
}
