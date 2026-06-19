package com.masterclass.api.b29_sockets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 15, unit = TimeUnit.SECONDS)
class Ej240GracefulShutdownAndTimeoutsTest {

    @Test
    void atenderHastaApagado() {
        assertEquals(3, Ej240GracefulShutdownAndTimeouts.atenderHastaApagado(3));
    }

    @Test
    void clienteDetectaCierreServidor() {
        assertTrue(Ej240GracefulShutdownAndTimeouts.clienteDetectaCierreServidor());
    }

    @Test
    void retoExtra01_soTimeoutEnAccept() {
        assertTrue(Ej240GracefulShutdownAndTimeouts.soTimeoutEnAccept());
    }

    @Test
    void retoExtra02_cerrarDesbloqueaAccept() {
        assertTrue(Ej240GracefulShutdownAndTimeouts.cerrarDesbloqueaAccept());
    }

    @Test
    void retoExtra03_tryWithResourcesCierraSocket() {
        assertTrue(Ej240GracefulShutdownAndTimeouts.tryWithResourcesCierraSocket());
    }

    @Test
    void retoExtra04_clienteConTimeoutDeLectura() {
        assertTrue(Ej240GracefulShutdownAndTimeouts.clienteConTimeoutDeLectura());
    }

    @Test
    void retoExtra05_banderaVolatileDetieneBucle() {
        assertEquals(3, Ej240GracefulShutdownAndTimeouts.banderaVolatileDetieneBucle(3));
    }

    @Test
    void retoExtra06_mensajeDeDespedidaAntesDeCerrar() {
        assertEquals("BYE", Ej240GracefulShutdownAndTimeouts.mensajeDeDespedidaAntesDeCerrar());
    }

    @Test
    void retoExtra07_desconexionAbruptaNoTumbaServidor() {
        assertEquals(3, Ej240GracefulShutdownAndTimeouts.desconexionAbruptaNoTumbaServidor(3));
    }

    @Test
    void retoExtra08_reuseAddressPermiteRebind() {
        assertTrue(Ej240GracefulShutdownAndTimeouts.reuseAddressPermiteRebind());
    }

    @Test
    void retoExtra09_awaitTerminationDelPool() {
        assertTrue(Ej240GracefulShutdownAndTimeouts.awaitTerminationDelPool());
    }

    @Test
    void retoExtra10_dobleCierreEsIdempotente() {
        assertTrue(Ej240GracefulShutdownAndTimeouts.dobleCierreEsIdempotente());
    }
}
