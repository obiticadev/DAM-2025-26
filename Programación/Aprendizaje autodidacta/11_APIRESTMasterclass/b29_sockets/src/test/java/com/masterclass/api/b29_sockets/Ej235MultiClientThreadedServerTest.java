package com.masterclass.api.b29_sockets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 15, unit = TimeUnit.SECONDS)
class Ej235MultiClientThreadedServerTest {

    @Test
    void atenderVariosClientes() {
        assertEquals(3, Ej235MultiClientThreadedServer.atenderVariosClientes(3));
    }

    @Test
    void sumarNumerosDeClientes() {
        assertEquals(10L, Ej235MultiClientThreadedServer.sumarNumerosDeClientes(List.of(1, 2, 3, 4)));
    }

    @Test
    void retoExtra01_dosClientesSimultaneos() {
        assertTrue(Ej235MultiClientThreadedServer.dosClientesSimultaneos());
    }

    @Test
    void retoExtra02_cadaClienteEnHiloDistinto() {
        assertEquals(4, Ej235MultiClientThreadedServer.cadaClienteEnHiloDistinto(4));
    }

    @Test
    void retoExtra03_saludoPersonalizado() {
        assertEquals("Hola cliente 3", Ej235MultiClientThreadedServer.saludoPersonalizado(3));
    }

    @Test
    void retoExtra04_contadorCompartidoSeguro() {
        assertEquals(200, Ej235MultiClientThreadedServer.contadorCompartidoSeguro(200));
    }

    @Test
    void retoExtra05_errorEnUnHandlerNoTumbaServidor() {
        assertEquals(3, Ej235MultiClientThreadedServer.errorEnUnHandlerNoTumbaServidor(3));
    }

    @Test
    void retoExtra06_aceptadorEsDaemon() {
        assertTrue(Ej235MultiClientThreadedServer.aceptadorEsDaemon());
    }

    @Test
    void retoExtra07_limiteDeConexiones() {
        assertEquals(2, Ej235MultiClientThreadedServer.limiteDeConexiones(2));
    }

    @Test
    void retoExtra08_nombreHiloHandlerIdentifica() {
        assertTrue(Ej235MultiClientThreadedServer.nombreHiloHandlerIdentifica());
    }

    @Test
    void retoExtra09_cerrarDesbloqueaAccept() {
        assertTrue(Ej235MultiClientThreadedServer.cerrarDesbloqueaAccept());
    }

    @Test
    void retoExtra10_cadaEcoCorrespondeASuCliente() {
        assertEquals(4, Ej235MultiClientThreadedServer.cadaEcoCorrespondeASuCliente(4));
    }
}
