package com.masterclass.api.b10_arch;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b10_arch.Ej089HexagonalPorts.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej089HexagonalPortsTest {

    @Test
    void dominioUsaElPuerto() {
        var adapter = new NotificacionEnMemoria();
        var svc = new RegistroService(adapter);
        String msg = svc.registrar("a@b.com");
        assertTrue(msg.contains("a@b.com"));
        assertEquals(1, adapter.enviados.size());
        assertTrue(adapter.enviados.get(0).contains("a@b.com"));
    }

    @Test
    void emailInvalido() {
        var svc = new RegistroService(new NotificacionEnMemoria());
        assertThrows(IllegalArgumentException.class, () -> svc.registrar("malo"));
    }

@Test
    void testDesafioValidarDestinoYMensaje() {
        assertThrows(IllegalArgumentException.class, () -> Ej089HexagonalPorts.desafioValidarDestinoYMensaje(null, "Hola"));
        assertThrows(IllegalArgumentException.class, () -> Ej089HexagonalPorts.desafioValidarDestinoYMensaje("a@b.com", " "));
    }

    @Test
    void testDesafioRegistrarEnviado() {
        var list = new java.util.ArrayList<String>();
        Ej089HexagonalPorts.desafioRegistrarEnviado(list, "a@b.com", "Hola");
        assertEquals("a@b.com|Hola", list.get(0));
    }

    @Test
    void testDesafioIntercambiarAdaptador() {
        assertTrue(Ej089HexagonalPorts.desafioIntercambiarAdaptador(new NotificacionEnMemoria()));
        assertFalse(Ej089HexagonalPorts.desafioIntercambiarAdaptador(null));
    }

    @Test
    void testDesafioAsignarPuerto() {
        var p = new NotificacionEnMemoria();
        assertEquals(p, Ej089HexagonalPorts.desafioAsignarPuerto(p));
    }

    @Test
    void testDesafioValidarEmail() {
        assertTrue(Ej089HexagonalPorts.desafioValidarEmail("a@b.com"));
        assertFalse(Ej089HexagonalPorts.desafioValidarEmail("ab.com"));
    }

    @Test
    void testDesafioConstruirBienvenida() {
        assertEquals("Bienvenido u@x.com", Ej089HexagonalPorts.desafioConstruirBienvenida("u@x.com"));
    }

    @Test
    void testDesafioEnviarPorPuerto() {
        var p = new NotificacionEnMemoria();
        Ej089HexagonalPorts.desafioEnviarPorPuerto(p, "u@x.com", "Msg");
        assertEquals("u@x.com|Msg", p.enviados.get(0));
    }

    @Test
    void testDesafioVerificarAislamiento() {
        assertEquals("desacoplado", Ej089HexagonalPorts.desafioVerificarAislamiento());
    }

    @Test
    void testDesafioObtenerMensajeRetorno() {
        assertEquals("Bienvenido u@x.com", Ej089HexagonalPorts.desafioObtenerMensajeRetorno("u@x.com"));
    }

    @Test
    void testDesafioSimularFlujoHexagonal() {
        var p = new NotificacionEnMemoria();
        var msg = Ej089HexagonalPorts.desafioSimularFlujoHexagonal(p, "u@x.com");
        assertEquals("Bienvenido u@x.com", msg);
        assertEquals("u@x.com|Bienvenido u@x.com", p.enviados.get(0));
    }
}
