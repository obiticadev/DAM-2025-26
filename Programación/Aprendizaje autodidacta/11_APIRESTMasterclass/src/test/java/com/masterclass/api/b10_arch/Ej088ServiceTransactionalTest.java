package com.masterclass.api.b10_arch;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b10_arch.Ej088ServiceTransactional.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej088ServiceTransactionalTest {

    @Test
    void transferenciaAtomica() {
        var a = new Cuenta("A", 100);
        var b = new Cuenta("B", 0);
        Ej088ServiceTransactional.transferir(a, b, 40);
        assertEquals(60, a.saldo, 0.0001);
        assertEquals(40, b.saldo, 0.0001);
    }

    @Test
    void sinFondosNoMutaNada() {
        var a = new Cuenta("A", 10);
        var b = new Cuenta("B", 5);
        assertThrows(SaldoInsuficienteException.class,
                () -> Ej088ServiceTransactional.transferir(a, b, 50));
        assertEquals(10, a.saldo, 0.0001, "rollback: origen intacto");
        assertEquals(5, b.saldo, 0.0001, "rollback: destino intacto");
    }
}
