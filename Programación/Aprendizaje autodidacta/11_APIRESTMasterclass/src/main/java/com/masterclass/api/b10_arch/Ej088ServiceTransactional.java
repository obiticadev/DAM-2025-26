package com.masterclass.api.b10_arch;

/**
 * Ejercicio 088 · Servicio transaccional (atomicidad, rollback).
 *
 * <p>Teoría: {@code teoria/10_Arquitectura_Patrones.md} (sección 10.1).
 *
 * <p>Una transferencia debe ser atómica: o se mueven ambos saldos o ninguno.
 */
public final class Ej088ServiceTransactional {

    /** Cuenta mutable para simular estado transaccional. */
    public static class Cuenta {
        public final String id;
        public double saldo;

        public Cuenta(String id, double saldo) {
            this.id = id;
            this.saldo = saldo;
        }
    }

    public static class SaldoInsuficienteException extends RuntimeException {
        public SaldoInsuficienteException(String m) {
            super(m);
        }
    }

    private Ej088ServiceTransactional() {
    }

    /**
     * Transfiere de 'origen' a 'destino' de forma atómica.
     *
     * @param origen  cuenta que paga
     * @param destino cuenta que recibe
     * @param importe cantidad a mover (&gt; 0)
     * @throws IllegalArgumentException si importe &lt;= 0 o alguna cuenta es null
     * @throws SaldoInsuficienteException si el origen no tiene fondos (no debe alterar saldos)
     */
    public static void transferir(Cuenta origen, Cuenta destino, double importe) {
        // TODO 1: valida origen y destino no null.
        // TODO 2: valida importe > 0.
        // TODO 3: comprueba ANTES de tocar nada si origen.saldo >= importe.
        // TODO 4: si no hay fondos, lanza SaldoInsuficienteException SIN modificar saldos
        //         (esto simula el rollback: no se aplica nada).
        // TODO 5: resta importe a origen.saldo.
        // TODO 6: suma importe a destino.saldo.
        // TODO 7: ambos pasos forman UNA unidad atómica (todo o nada).
        // TODO 8: el orden importa: valida primero, muta después (consistencia).
        // TODO 9: no debe quedar dinero "en el aire" (suma total constante).
        // TODO 10: método void: el efecto es la mutación atómica de ambas cuentas.
    }

    public static void main(String[] args) {
        var a = new Cuenta("A", 100);
        var b = new Cuenta("B", 0);
        transferir(a, b, 40);
        System.out.println(a.saldo + " / " + b.saldo);
    }
}
