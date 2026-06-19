package com.masterclass.api.b29_sockets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej237UdpDatagramsTest {

    @Test
    void ecoUdp() {
        assertEquals("udp-hola", Ej237UdpDatagrams.ecoUdp("udp-hola"));
    }

    @Test
    void enviarYRecibirNumeroUdp() {
        assertEquals(123, Ej237UdpDatagrams.enviarYRecibirNumeroUdp(123));
    }

    @Test
    void retoExtra01_puertoServidorUdpAsignado() {
        assertTrue(Ej237UdpDatagrams.puertoServidorUdpAsignado());
    }

    @Test
    void retoExtra02_mensajeUdpEnMayusculas() {
        assertEquals("ABC", Ej237UdpDatagrams.mensajeUdpEnMayusculas("abc"));
    }

    @Test
    void retoExtra03_timeoutUdpSiNoLlegaNada() {
        assertTrue(Ej237UdpDatagrams.timeoutUdpSiNoLlegaNada());
    }

    @Test
    void retoExtra04_longitudDatagramaRecibido() {
        assertEquals(4, Ej237UdpDatagrams.longitudDatagramaRecibido("hola"));
    }

    @Test
    void retoExtra05_remitenteEsLoopback() {
        assertTrue(Ej237UdpDatagrams.remitenteEsLoopback());
    }

    @Test
    void retoExtra06_variosDatagramasMismoSocket() {
        assertEquals(3, Ej237UdpDatagrams.variosDatagramasMismoSocket());
    }

    @Test
    void retoExtra07_datagramaGrande() {
        assertEquals(1000, Ej237UdpDatagrams.datagramaGrande());
    }

    @Test
    void retoExtra08_bufferPequenoTrunca() {
        assertEquals(4, Ej237UdpDatagrams.bufferPequenoTrunca());
    }

    @Test
    void retoExtra09_ecoUdpUtf8() {
        assertEquals("ñandú café", Ej237UdpDatagrams.ecoUdpUtf8("ñandú café"));
    }

    @Test
    void retoExtra10_udpNoNecesitaConexion() {
        assertTrue(Ej237UdpDatagrams.udpNoNecesitaConexion());
    }
}
