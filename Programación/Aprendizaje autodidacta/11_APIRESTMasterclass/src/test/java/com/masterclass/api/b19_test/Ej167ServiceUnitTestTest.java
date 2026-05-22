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

    @Test
    void testRetoExtra01_formatearSaldo() {
        // Formatea el saldo de la cuenta.
        assertEquals("$100.0", Ej167ServiceUnitTest.formatearSaldo(c -> 100.0, "A"));
    }

    @Test
    void testRetoExtra02_esCuentaPremium() {
        // Comprueba si el saldo califica como Premium.
        assertTrue(Ej167ServiceUnitTest.esCuentaPremium(c -> 1500.0, "A"));
    }

    @Test
    void testRetoExtra03_esSaldoPositivo() {
        // Comprueba si el saldo es positivo.
        assertTrue(Ej167ServiceUnitTest.esSaldoPositivo(c -> 5.0, "A"));
    }

    @Test
    void testRetoExtra04_transferenciaPosible() {
        // Comprueba si una transferencia es viable.
        assertTrue(Ej167ServiceUnitTest.transferenciaPosible(c -> 100.0, "A", 50.0));
    }

    @Test
    void testRetoExtra05_calcularInteresAnual() {
        // Calcula el interes anual estimado.
        assertEquals(5.0, Ej167ServiceUnitTest.calcularInteresAnual(c -> 100.0, "A", 0.05));
    }

    @Test
    void testRetoExtra06_saldoDuplicado() {
        // Devuelve el saldo duplicado.
        assertEquals(20.0, Ej167ServiceUnitTest.saldoDuplicado(c -> 10.0, "A"));
    }

    @Test
    void testRetoExtra07_obtenerDiferenciaSaldos() {
        // Calcula la diferencia de saldo entre dos cuentas.
        assertEquals(5.0, Ej167ServiceUnitTest.obtenerDiferenciaSaldos(c -> "A".equals(c) ? 10.0 : 15.0, "A", "B"));
    }

    @Test
    void testRetoExtra08_sumarSaldos() {
        // Suma los saldos de dos cuentas.
        assertEquals(25.0, Ej167ServiceUnitTest.sumarSaldos(c -> "A".equals(c) ? 10.0 : 15.0, "A", "B"));
    }

    @Test
    void testRetoExtra09_saldoValido() {
        // Comprueba si el saldo no es negativo.
        assertTrue(Ej167ServiceUnitTest.saldoValido(c -> 0.0, "A"));
    }

    @Test
    void testRetoExtra10_saldoOExcepcion() {
        // Retorna el saldo o lanza si es negativo.
        assertThrows(IllegalArgumentException.class, () -> Ej167ServiceUnitTest.saldoOExcepcion(c -> -10.0, "A"));
    }

}