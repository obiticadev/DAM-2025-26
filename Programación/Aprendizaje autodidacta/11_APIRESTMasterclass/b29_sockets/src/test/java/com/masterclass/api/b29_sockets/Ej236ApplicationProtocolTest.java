package com.masterclass.api.b29_sockets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej236ApplicationProtocolTest {

    @Test
    void putYLuegoGet() {
        assertEquals("azul", Ej236ApplicationProtocol.putYLuegoGet("color", "azul"));
    }

    @Test
    void getInexistenteDevuelveNil() {
        assertEquals("NIL", Ej236ApplicationProtocol.getInexistenteDevuelveNil("x"));
    }

    @Test
    void retoExtra01_putRespondeOk() {
        assertEquals("OK", Ej236ApplicationProtocol.putRespondeOk());
    }

    @Test
    void retoExtra02_comandoDesconocidoDevuelveError() {
        assertEquals("ERROR", Ej236ApplicationProtocol.comandoDesconocidoDevuelveError());
    }

    @Test
    void retoExtra03_sobrescribirValor() {
        assertEquals("v2", Ej236ApplicationProtocol.sobrescribirValor());
    }

    @Test
    void retoExtra04_varriasClavesIndependientes() {
        assertEquals("1", Ej236ApplicationProtocol.varriasClavesIndependientes());
    }

    @Test
    void retoExtra05_quitCierraConexion() {
        assertTrue(Ej236ApplicationProtocol.quitCierraConexion());
    }

    @Test
    void retoExtra06_valorConEspacios() {
        assertEquals("hola mundo", Ej236ApplicationProtocol.valorConEspacios());
    }

    @Test
    void retoExtra07_comandoEsCaseSensitive() {
        assertEquals("ERROR", Ej236ApplicationProtocol.comandoEsCaseSensitive());
    }

    @Test
    void retoExtra08_estadoCompartidoEntreClientes() {
        assertEquals("compartido", Ej236ApplicationProtocol.estadoCompartidoEntreClientes());
    }

    @Test
    void retoExtra09_contarComandosProcesados() {
        assertEquals(3, Ej236ApplicationProtocol.contarComandosProcesados());
    }

    @Test
    void retoExtra10_getDeOtraClaveDaNil() {
        assertEquals("NIL", Ej236ApplicationProtocol.getDeOtraClaveDaNil());
    }
}
