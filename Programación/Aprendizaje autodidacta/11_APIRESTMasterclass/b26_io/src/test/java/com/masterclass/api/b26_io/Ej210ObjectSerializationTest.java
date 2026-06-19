package com.masterclass.api.b26_io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej210ObjectSerializationTest {

    @Test
    void serializarPunto() {
        assertEquals(new Ej210ObjectSerialization.Punto(3, 4), Ej210ObjectSerialization.serializarPunto(3, 4));
    }

    @Test
    void serializarLista() {
        assertEquals(List.of("a", "b"), Ej210ObjectSerialization.serializarLista(List.of("a", "b")));
    }

    @Test
    void retoExtra01_transientNoPersiste() {
        assertTrue(Ej210ObjectSerialization.transientNoPersiste());
    }

    @Test
    void retoExtra02_objetoNuloSeSerializa() {
        assertTrue(Ej210ObjectSerialization.objetoNuloSeSerializa());
    }

    @Test
    void retoExtra03_grafoDeObjetosSeSerializa() {
        assertTrue(Ej210ObjectSerialization.grafoDeObjetosSeSerializa());
    }

    @Test
    void retoExtra04_noSerializableLanzaExcepcion() {
        assertTrue(Ej210ObjectSerialization.noSerializableLanzaExcepcion());
    }

    @Test
    void retoExtra05_tamanoSerializadoMayorQueCero() {
        assertTrue(Ej210ObjectSerialization.tamanoSerializadoMayorQueCero());
    }

    @Test
    void retoExtra06_recordRoundTripIgual() {
        assertTrue(Ej210ObjectSerialization.recordRoundTripIgual());
    }

    @Test
    void retoExtra07_clonarPorSerializacion() {
        assertTrue(Ej210ObjectSerialization.clonarPorSerializacion());
    }

    @Test
    void retoExtra08_mapSerializableConservaEntradas() {
        assertEquals(2, Ej210ObjectSerialization.mapSerializableConservaEntradas());
    }

    @Test
    void retoExtra09_enteroSerializable() {
        assertEquals(99, Ej210ObjectSerialization.enteroSerializable());
    }

    @Test
    void retoExtra10_dosObjetosEnElMismoFichero() {
        assertEquals(3, Ej210ObjectSerialization.dosObjetosEnElMismoFichero());
    }

    @Test
    void retoExtra11_transientPresenteAntesAusenteDespues() {
        assertTrue(Ej210ObjectSerialization.transientPresenteAntesAusenteDespues());
    }

    @Test
    void retoExtra12_listaGrandeRoundTrip() {
        assertEquals(1000, Ej210ObjectSerialization.listaGrandeRoundTrip());
    }
}
