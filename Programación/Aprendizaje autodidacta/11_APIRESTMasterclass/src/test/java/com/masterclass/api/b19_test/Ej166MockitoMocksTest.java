package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej166MockitoMocksTest {

    @Test
    void usuarioExiste() {
        RepositorioStub166 stub = id -> id == 1 ? "Ada" : null;
        assertEquals("Hola, Ada", Ej166MockitoMocks.saludar(stub, 1));
    }

    @Test
    void usuarioNoExiste() {
        RepositorioStub166 stub = id -> null;
        assertEquals("Hola, desconocido", Ej166MockitoMocks.saludar(stub, 99));
    }

    @Test
    void repoNull() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej166MockitoMocks.saludar(null, 1));
    }
}
