package com.masterclass.api.b19_test;

/**
 * Ejercicio 165 · Test unitario con JUnit 5 (lógica pura a testear).
 *
 * <p>Teoría: {@code teoria/19_Testing_APIs.md} (sección 19.2).
 *
 * <p>Esta clase es la "pieza bajo prueba": una calculadora de precio con
 * descuento. El test espejo usa {@code assertEquals}/{@code assertThrows}
 * de JUnit 5 para validarla, sin arrancar Spring.
 */
public final class Ej165UnitTestJUnit5 {

    private Ej165UnitTestJUnit5() {
    }

    /**
     * Aplica un descuento porcentual a un precio base.
     *
     * @param precioBase precio &gt; 0
     * @param porcentaje descuento en rango [0,100]
     * @return precio con descuento, redondeado a 2 decimales
     * @throws IllegalArgumentException si precioBase &lt;= 0 o porcentaje fuera de [0,100]
     */
    public static double aplicarDescuento(double precioBase, double porcentaje) {
        // TODO 1: si precioBase <= 0 -> IllegalArgumentException (un precio no negativo).
        // TODO 2: si porcentaje < 0 -> IllegalArgumentException.
        // TODO 3: si porcentaje > 100 -> IllegalArgumentException (no se regala dinero).
        // TODO 4: calcula el factor de descuento: 1 - porcentaje/100.
        // TODO 5: multiplica precioBase por el factor.
        // TODO 6: redondea a 2 decimales (Math.round(x*100)/100.0).
        // TODO 7: porcentaje 0 -> devuelve el precio base intacto (caso límite).
        // TODO 8: porcentaje 100 -> devuelve 0.0 (caso límite).
        // TODO 9: la función debe ser determinista (mismos inputs -> mismo output).
        // TODO 10: devuelve el resultado redondeado.
        return 0.0;
    }

    public static void main(String[] args) {
        System.out.println(aplicarDescuento(100.0, 20.0));
    }
}
