package com.masterclass.nivel5_excepciones_checked;

/**
 * ============================================================================
 *  EJERCICIO 37 — CREAR EXCEPCION CHECKED CUSTOM (CON GUIA)
 * ============================================================================
 * Lee primero: teoria/05_Excepciones_Checked.md
 */
public class Ejercicio37_ExcepcionCheckedCustom {

    /**
     * TODO: Crea una excepcion checked llamada CuentaBancariaException.
     * Debe tener: mensaje, saldo actual (double), monto solicitado (double).
     * Getters para saldo y monto.
     */
    public static class CuentaBancariaException extends Exception {
        private final double saldo;
        private final double montoSolicitado;

        public CuentaBancariaException(String mensaje, double saldo, double montoSolicitado) {
            super(mensaje);
            this.saldo = saldo;
            this.montoSolicitado = montoSolicitado;
        }

        public double getSaldo() { return saldo; }
        public double getMontoSolicitado() { return montoSolicitado; }
    }

    /**
     * TODO: Simula una retirada. Si monto > saldo, lanza CuentaBancariaException.
     * Si no, devuelve saldo - monto.
     */
    public static double retirar(double saldo, double monto) throws CuentaBancariaException {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Intenta retirar. Si hay excepcion, devuelve un String descriptivo:
     * "Fondos insuficientes: saldo={saldo}, solicitado={monto}"
     * Si no, devuelve "Retiro exitoso. Nuevo saldo: {nuevoSaldo}"
     */
    public static String procesarRetiro(double saldo, double monto) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
