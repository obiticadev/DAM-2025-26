package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej167ServiceUnitTestTest {

    private final SaldoRepo167 repo = c -> "A".equals(c) ? 100.0 : 0.0;

    @Test
    void transferenciaOk() {
        assertEquals(70.0, Ej167ServiceUnitTest.transferir(repo, "A", "B", 30.0));
    }

    @Test
    void fondosInsuficientes() {
        assertThrows(IllegalStateException.class,
                () -> Ej167ServiceUnitTest.transferir(repo, "A", "B", 500.0));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej167ServiceUnitTest.transferir(repo, "A", "A", 10.0));
        assertThrows(IllegalArgumentException.class,
                () -> Ej167ServiceUnitTest.transferir(null, "A", "B", 10.0));
    }
}
