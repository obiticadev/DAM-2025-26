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

    /**
     * TODO extra 1: Valida que las cuentas de origen y destino no sean nulas.
     */
    public static void desafioValidarCuentas(Cuenta origen, Cuenta destino) {
        if (origen == null || destino == null) {
            throw new IllegalArgumentException("Las cuentas no pueden ser nulas");
        }
    }

    /**
     * TODO extra 2: Valida que el importe a transferir sea positivo.
     */
    public static void desafioValidarImportePositivo(double importe) {
        if (importe <= 0) {
            throw new IllegalArgumentException("El importe debe ser mayor que cero");
        }
    }

    /**
     * TODO extra 3: Comprueba si la cuenta tiene fondos suficientes.
     */
    public static boolean desafioComprobarFondosSuficientes(Cuenta origen, double importe) {
        return origen.saldo >= importe;
    }

    /**
     * TODO extra 4: Lanza SaldoInsuficienteException si la cuenta no tiene fondos.
     */
    public static void desafioLanzarSaldoInsuficiente(Cuenta origen, double importe) {
        if (!desafioComprobarFondosSuficientes(origen, importe)) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
    }

    /**
     * TODO extra 5: Resta el importe del saldo de la cuenta origen.
     */
    public static void desafioRestarSaldo(Cuenta origen, double importe) {
        origen.saldo -= importe;
    }

    /**
     * TODO extra 6: Suma el importe al saldo de la cuenta destino.
     */
    public static void desafioSumarSaldo(Cuenta destino, double importe) {
        destino.saldo += importe;
    }

    /**
     * TODO extra 7: Verifica que la suma total de saldos permanezca constante.
     */
    public static double desafioSumaTotalConstante(Cuenta origen, Cuenta destino) {
        return origen.saldo + destino.saldo;
    }

    /**
     * TODO extra 8: Realiza la transferencia de forma completa.
     */
    public static void desafioTransferenciaCompleta(Cuenta origen, Cuenta destino, double importe) {
        desafioValidarCuentas(origen, destino);
        desafioValidarImportePositivo(importe);
        desafioLanzarSaldoInsuficiente(origen, importe);
        desafioRestarSaldo(origen, importe);
        desafioSumarSaldo(destino, importe);
    }

    /**
     * TODO extra 9: Devuelve un log representativo del estado de las cuentas.
     */
    public static String desafioVerificarAuditoria(Cuenta origen, Cuenta destino) {
        return origen.id + ":" + origen.saldo + "|" + destino.id + ":" + destino.saldo;
    }

    /**
     * TODO extra 10: Verifica si es posible transferir con fondos hipotéticos sin alterar nada.
     */
    public static boolean desafioEsTransaccionValida(Cuenta origen, Cuenta destino, double importe) {
        try {
            desafioValidarCuentas(origen, destino);
            desafioValidarImportePositivo(importe);
            return desafioComprobarFondosSuficientes(origen, importe);
        } catch (Exception e) {
            return false;
        }
    }

}
