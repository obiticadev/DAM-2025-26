package com.masterclass.api.b29_sockets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej234TcpClientTest {

    @Test
    void enviarYRecibirConTimeout() {
        assertEquals("ping", Ej234TcpClient.enviarYRecibirConTimeout("ping", 2000));
    }

    @Test
    void enviarVariosYConcatenar() {
        assertEquals("abc", Ej234TcpClient.enviarVariosYConcatenar(List.of("a", "b", "c")));
    }

    @Test
    void retoExtra01_timeoutLanzaSocketTimeout() {
        assertTrue(Ej234TcpClient.timeoutLanzaSocketTimeout());
    }

    @Test
    void retoExtra02_conexionRehusada() {
        assertTrue(Ej234TcpClient.conexionRehusada());
    }

    @Test
    void retoExtra03_ecoNumeroEntero() {
        assertEquals(42, Ej234TcpClient.ecoNumeroEntero(42));
        assertEquals(-7, Ej234TcpClient.ecoNumeroEntero(-7));
    }

    @Test
    void retoExtra04_leerTodasLasLineasHastaEof() {
        assertEquals(3, Ej234TcpClient.leerTodasLasLineasHastaEof());
    }

    @Test
    void retoExtra05_enviarConBufferedWriter() {
        assertEquals("buffer", Ej234TcpClient.enviarConBufferedWriter("buffer"));
    }

    @Test
    void retoExtra06_direccionRemotaEsLoopback() {
        assertTrue(Ej234TcpClient.direccionRemotaEsLoopback());
    }

    @Test
    void retoExtra07_soTimeoutCeroEsperaSiempre() {
        assertEquals("sin-limite", Ej234TcpClient.soTimeoutCeroEsperaSiempre("sin-limite"));
    }

    @Test
    void retoExtra08_enviarBytesYRecibirBytes() {
        assertArrayEquals(new byte[]{1, 2, 3, 4, 5}, Ej234TcpClient.enviarBytesYRecibirBytes(new byte[]{1, 2, 3, 4, 5}));
    }

    @Test
    void retoExtra09_reconectarTrasCierre() {
        assertEquals(2, Ej234TcpClient.reconectarTrasCierre());
    }

    @Test
    void retoExtra10_mensajeLargoLlegaCompleto() {
        assertEquals(10000, Ej234TcpClient.mensajeLargoLlegaCompleto());
    }
}
