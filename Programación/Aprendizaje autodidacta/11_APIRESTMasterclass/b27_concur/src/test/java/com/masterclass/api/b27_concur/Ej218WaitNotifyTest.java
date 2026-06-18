package com.masterclass.api.b27_concur;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej218WaitNotifyTest {

    @Test
    void produceConsume() {
        assertEquals(1000, Ej218WaitNotify.produceConsume(1000, 16));
    }

    @Test
    void retoExtra01_variosConsumidores() {
        assertEquals(900, Ej218WaitNotify.variosConsumidores(900, 16, 3));
    }

    @Test
    void retoExtra02_usarWhileNoIf() {
        assertTrue(Ej218WaitNotify.usarWhileNoIf());
    }

    @Test
    void retoExtra03_variosProductoresYConsumidores() {
        assertEquals(1000, Ej218WaitNotify.variosProductoresYConsumidores(4, 4, 250));
    }

    @Test
    void retoExtra04_bufferNuncaExcedeCapacidad() {
        assertTrue(Ej218WaitNotify.bufferNuncaExcedeCapacidad(1000, 8));
    }

    @Test
    void retoExtra05_ordenFIFO() {
        assertTrue(Ej218WaitNotify.ordenFIFO(500, 8));
    }

    @Test
    void retoExtra06_sumaTransferida() {
        assertEquals(500L * 501L / 2L, Ej218WaitNotify.sumaTransferida(500, 8));
    }

    @Test
    void retoExtra07_consumidorLento() {
        assertEquals(300, Ej218WaitNotify.consumidorLento(300, 4));
    }

    @Test
    void retoExtra08_productorLento() {
        assertEquals(300, Ej218WaitNotify.productorLento(300, 4));
    }

    @Test
    void retoExtra09_capacidadUno() {
        assertEquals(200, Ej218WaitNotify.capacidadUno(200));
    }

    @Test
    void retoExtra10_notifyAllDespiertaATodos() {
        assertTrue(Ej218WaitNotify.notifyAllDespiertaATodos());
    }
}
