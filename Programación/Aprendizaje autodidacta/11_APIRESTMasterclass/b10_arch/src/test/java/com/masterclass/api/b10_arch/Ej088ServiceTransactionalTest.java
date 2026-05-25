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

@Test
    void testDesafioValidarCuentas() {
        assertThrows(IllegalArgumentException.class, () -> Ej088ServiceTransactional.desafioValidarCuentas(null, new Cuenta("B", 10)));
        assertDoesNotThrow(() -> Ej088ServiceTransactional.desafioValidarCuentas(new Cuenta("A", 10), new Cuenta("B", 10)));
    }

    @Test
    void testDesafioValidarImportePositivo() {
        assertThrows(IllegalArgumentException.class, () -> Ej088ServiceTransactional.desafioValidarImportePositivo(0.0));
        assertDoesNotThrow(() -> Ej088ServiceTransactional.desafioValidarImportePositivo(1.0));
    }

    @Test
    void testDesafioComprobarFondosSuficientes() {
        var a = new Cuenta("A", 10.0);
        assertTrue(Ej088ServiceTransactional.desafioComprobarFondosSuficientes(a, 5.0));
        assertFalse(Ej088ServiceTransactional.desafioComprobarFondosSuficientes(a, 15.0));
    }

    @Test
    void testDesafioLanzarSaldoInsuficiente() {
        var a = new Cuenta("A", 10.0);
        assertThrows(SaldoInsuficienteException.class, () -> Ej088ServiceTransactional.desafioLanzarSaldoInsuficiente(a, 15.0));
    }

    @Test
    void testDesafioRestarSaldo() {
        var a = new Cuenta("A", 10.0);
        Ej088ServiceTransactional.desafioRestarSaldo(a, 3.0);
        assertEquals(7.0, a.saldo, 0.0001);
    }

    @Test
    void testDesafioSumarSaldo() {
        var b = new Cuenta("B", 10.0);
        Ej088ServiceTransactional.desafioSumarSaldo(b, 3.0);
        assertEquals(13.0, b.saldo, 0.0001);
    }

    @Test
    void testDesafioSumaTotalConstante() {
        var a = new Cuenta("A", 10.0);
        var b = new Cuenta("B", 20.0);
        assertEquals(30.0, Ej088ServiceTransactional.desafioSumaTotalConstante(a, b), 0.0001);
    }

    @Test
    void testDesafioTransferenciaCompleta() {
        var a = new Cuenta("A", 100.0);
        var b = new Cuenta("B", 50.0);
        Ej088ServiceTransactional.desafioTransferenciaCompleta(a, b, 40.0);
        assertEquals(60.0, a.saldo, 0.0001);
        assertEquals(90.0, b.saldo, 0.0001);
    }

    @Test
    void testDesafioVerificarAuditoria() {
        var a = new Cuenta("A", 10.0);
        var b = new Cuenta("B", 20.0);
        assertEquals("A:10.0|B:20.0", Ej088ServiceTransactional.desafioVerificarAuditoria(a, b));
    }

    @Test
    void testDesafioEsTransaccionValida() {
        var a = new Cuenta("A", 10.0);
        var b = new Cuenta("B", 20.0);
        assertTrue(Ej088ServiceTransactional.desafioEsTransaccionValida(a, b, 5.0));
        assertFalse(Ej088ServiceTransactional.desafioEsTransaccionValida(a, b, 15.0));
    }
}
