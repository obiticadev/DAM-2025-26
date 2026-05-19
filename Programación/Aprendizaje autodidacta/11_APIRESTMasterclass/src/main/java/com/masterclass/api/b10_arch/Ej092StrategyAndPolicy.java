package com.masterclass.api.b10_arch;

import java.util.Map;
import java.util.function.DoubleUnaryOperator;

/**
 * Ejercicio 092 · Strategy en la capa de negocio.
 *
 * <p>Teoría: {@code teoria/10_Arquitectura_Patrones.md} (sección 10.2).
 *
 * <p>Distintas políticas de descuento intercambiables sin if gigantes.
 */
public final class Ej092StrategyAndPolicy {

    private Ej092StrategyAndPolicy() {
    }

    /**
     * Devuelve el registro de estrategias de descuento.
     *
     * <p>Cada estrategia es una función precio -&gt; precioConDescuento.
     *
     * @return mapa nombre → estrategia ("none", "black-friday" -30%, "vip" -10%)
     */
    public static Map<String, DoubleUnaryOperator> estrategias() {
        // TODO 1: "none" -> identidad (precio sin cambios).
        // TODO 2: "black-friday" -> precio * 0.70 (30% off).
        // TODO 3: "vip" -> precio * 0.90 (10% off).
        // TODO 4: devuelve un Map inmodificable con esas 3 entradas.
        // TODO 5: cada valor es un DoubleUnaryOperator (la "estrategia").
        return Map.of();
    }

    /**
     * Aplica la estrategia indicada a un precio.
     *
     * @param precio    precio base (&gt;= 0)
     * @param estrategia clave de la estrategia
     * @return precio resultante
     * @throws IllegalArgumentException si precio &lt; 0 o la estrategia no existe
     */
    public static double aplicar(double precio, String estrategia) {
        // TODO 6: valida precio >= 0.
        // TODO 7: obtén el mapa de estrategias().
        // TODO 8: si la estrategia no está -> IllegalArgumentException.
        // TODO 9: aplica la función con applyAsDouble(precio).
        // TODO 10: devuelve el resultado (añadir una estrategia nueva NO toca este método).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(aplicar(100, "black-friday"));
    }
}
