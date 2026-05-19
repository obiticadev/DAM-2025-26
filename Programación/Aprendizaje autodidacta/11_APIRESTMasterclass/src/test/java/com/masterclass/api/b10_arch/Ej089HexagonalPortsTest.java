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
}
