package com.masterclass.api.b29_sockets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej233TcpEchoServerTest {

    @Test
    void ecoDeUnMensaje() {
        assertEquals("hola", Ej233TcpEchoServer.ecoDeUnMensaje("hola"));
        assertEquals("PSP RA3", Ej233TcpEchoServer.ecoDeUnMensaje("PSP RA3"));
    }

    @Test
    void ecoDeVariasLineas() {
        assertEquals(List.of("a", "b", "c"), Ej233TcpEchoServer.ecoDeVariasLineas(List.of("a", "b", "c")));
    }

    @Test
    void retoExtra01_ecoEnMayusculas() {
        assertEquals("ABC", Ej233TcpEchoServer.ecoEnMayusculas("abc"));
    }

    @Test
    void retoExtra02_longitudDelMensaje() {
        assertEquals(4, Ej233TcpEchoServer.longitudDelMensaje("hola"));
        assertEquals(0, Ej233TcpEchoServer.longitudDelMensaje(""));
    }

    @Test
    void retoExtra03_ecoConPrefijo() {
        assertEquals("ECO:hola", Ej233TcpEchoServer.ecoConPrefijo("hola", "ECO:"));
    }

    @Test
    void retoExtra04_cuentaConexionesAtendidas() {
        assertEquals(5, Ej233TcpEchoServer.cuentaConexionesAtendidas(5));
    }

    @Test
    void retoExtra05_clienteDetectaFinDeStream() {
        assertTrue(Ej233TcpEchoServer.clienteDetectaFinDeStream());
    }

    @Test
    void retoExtra06_ecoInvirtiendo() {
        assertEquals("cba", Ej233TcpEchoServer.ecoInvirtiendo("abc"));
    }

    @Test
    void retoExtra07_puertoEfimeroEsValido() {
        assertTrue(Ej233TcpEchoServer.puertoEfimeroEsValido());
    }

    @Test
    void retoExtra08_ecoConUtf8() {
        assertEquals("ñandú café", Ej233TcpEchoServer.ecoConUtf8("ñandú café"));
    }

    @Test
    void retoExtra09_contarLineasRecibidas() {
        assertEquals(3, Ej233TcpEchoServer.contarLineasRecibidas(List.of("a", "b", "c")));
    }

    @Test
    void retoExtra10_ecoMensajeVacio() {
        assertEquals("", Ej233TcpEchoServer.ecoMensajeVacio());
    }
}
