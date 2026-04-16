package com.masterclass.nivel1_interfaces_basicas;

/**
 * ============================================================================
 *  EJERCICIO 02 — MULTIPLES IMPLEMENTACIONES (SIN GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Una misma interfaz puede tener MUCHAS implementaciones.
 * Piensa en la interfaz como un "contrato de forma de enchufe" y cada aparato
 * (tostadora, PC, ventilador) es una implementacion diferente.
 *
 * Esto es la base del POLIMORFISMO: tratar objetos diferentes de forma uniforme.
 *
 *   OperacionMatematica suma = (a, b) -> a + b;
 *   OperacionMatematica resta = (a, b) -> a - b;
 *   // Ambas son OperacionMatematica, pero hacen cosas distintas.
 *
 * Lee primero: teoria/01_Interfaces_Basicas.md
 */
public class Ejercicio02_MultiplesImplementaciones {

    /**
     * Interfaz para una operacion matematica con dos operandos.
     */
    public interface OperacionMatematica {
        double calcular(double a, double b);
    }

    /**
     * TODO: Devuelve una OperacionMatematica que SUME a + b.
     */
    public static OperacionMatematica crearSuma() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Devuelve una OperacionMatematica que RESTE a - b.
     */
    public static OperacionMatematica crearResta() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Devuelve una OperacionMatematica que MULTIPLIQUE a * b.
     */
    public static OperacionMatematica crearMultiplicacion() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Devuelve una OperacionMatematica que DIVIDA a / b.
     * Si b es 0, debe devolver Double.NaN (Not a Number).
     */
    public static OperacionMatematica crearDivision() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
