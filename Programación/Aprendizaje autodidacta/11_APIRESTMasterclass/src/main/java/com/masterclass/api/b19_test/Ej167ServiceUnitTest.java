package com.masterclass.api.b19_test;

/**
 * Ejercicio 167 · Test unitario de servicio (orquestación con colaboradores).
 *
 * <p>Teoría: {@code teoria/19_Testing_APIs.md} (sección 19.4).
 *
 * <p>Un servicio de transferencia que orquesta un repositorio de saldos
 * (stub). El test verifica la lógica de negocio sin BD ni Spring.
 */
public final class Ej167ServiceUnitTest {

    private Ej167ServiceUnitTest() {
    }

    /**
     * Transfiere un importe entre dos cuentas usando un repositorio de saldos.
     *
     * @param saldos repositorio de saldos (stub en test) no null
     * @param origen  id cuenta origen
     * @param destino id cuenta destino (distinto de origen)
     * @param importe importe &gt; 0
     * @return nuevo saldo de la cuenta origen tras la transferencia
     * @throws IllegalArgumentException entradas inválidas
     * @throws IllegalStateException si el saldo de origen es insuficiente
     */
    public static double transferir(SaldoRepo167 saldos, String origen, String destino, double importe) {
        // TODO 1: si saldos es null -> IllegalArgumentException.
        // TODO 2: si origen o destino son null/blank -> IllegalArgumentException.
        // TODO 3: si origen.equals(destino) -> IllegalArgumentException (no a sí mismo).
        // TODO 4: si importe <= 0 -> IllegalArgumentException.
        // TODO 5: lee saldoOrigen = saldos.saldoDe(origen).
        // TODO 6: si saldoOrigen < importe -> IllegalStateException (fondos insuficientes).
        // TODO 7: calcula nuevoSaldoOrigen = saldoOrigen - importe.
        // TODO 8: la regla de negocio no escribe BD aquí: el repo es un doble.
        // TODO 9: no debe mutar el repo de entrada (lógica pura verificable).
        // TODO 10: devuelve nuevoSaldoOrigen.
        return 0.0;
    }

    public static void main(String[] args) {
        SaldoRepo167 repo = c -> "A".equals(c) ? 100.0 : 0.0;
        System.out.println(transferir(repo, "A", "B", 30.0));
    }
}

/** Colaborador del servicio: repositorio de saldos (doble de prueba). */
@FunctionalInterface
interface SaldoRepo167 {
    /** @param cuenta id @return saldo actual de la cuenta */
    double saldoDe(String cuenta);
}
