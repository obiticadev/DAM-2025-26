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

    /**
     * TODO extra 1: Retorna la estrategia None (operación identidad).
     */
    public static java.util.function.DoubleUnaryOperator desafioEstrategiaNone() {
        // GUÍA: teoría 10.8 — cada estrategia es un DoubleUnaryOperator (double →
        // double). "none" es la identidad: devuelve el precio sin cambios. El test
        // aplica 100 y espera 100. (Equivale a DoubleUnaryOperator.identity().)
        return p -> p;
    }

    /**
     * TODO extra 2: Retorna la estrategia Black Friday (descuento del 30%).
     */
    public static java.util.function.DoubleUnaryOperator desafioEstrategiaBlackFriday() {
        // GUÍA: descuento del 30% → se paga el 70% (p * 0.70). El test aplica 100
        // y espera 70.0. Cada política encapsulada en su propia lambda, sin if.
        return p -> p * 0.70;
    }

    /**
     * TODO extra 3: Retorna la estrategia VIP (descuento del 10%).
     */
    public static java.util.function.DoubleUnaryOperator desafioEstrategiaVip() {
        // GUÍA: descuento del 10% → se paga el 90% (p * 0.90). El test aplica 100
        // y espera 90.0. CUIDADO: es 0.90 (descuento), no 0.10.
        return p -> p * 0.90;
    }

    /**
     * TODO extra 4: Crea el mapa de estrategias.
     */
    public static java.util.Map<String, java.util.function.DoubleUnaryOperator> desafioCrearMapaEstrategias(
            java.util.function.DoubleUnaryOperator none,
            java.util.function.DoubleUnaryOperator bf,
            java.util.function.DoubleUnaryOperator vip) {
        // GUÍA: el "registro" de estrategias indexadas por su clave (Map.of es
        // inmodificable). CUIDADO con las claves EXACTAS: "none", "black-friday"
        // (con guion), "vip" — son las que aplicar() y los tests buscan. El test
        // solo comprueba size()==3.
        return java.util.Map.of("none", none, "black-friday", bf, "vip", vip);
    }

    /**
     * TODO extra 5: Verifica el resultado de aplicar un operador a un precio.
     */
    public static boolean desafioVerificarOperador(java.util.function.DoubleUnaryOperator op, double precio, double esperado) {
        // GUÍA: comparar doubles con == es peligroso (errores de redondeo IEEE);
        // se usa una tolerancia (epsilon) con Math.abs(diff) < 0.0001. El test:
        // op = p->p*0.5 sobre 100 debe dar ~50 → true.
        return Math.abs(op.applyAsDouble(precio) - esperado) < 0.0001;
    }

    /**
     * TODO extra 6: Valida que el precio no sea negativo.
     */
    public static void desafioValidarPrecioEstrategia(double precio) {
        // GUÍA: precondición de aplicar(). CUIDADO: el corte es < 0 (NO <= 0); el
        // test exige excepción con -1 pero que 0 PASE (un precio de 0 es válido).
        if (precio < 0) {
            throw new IllegalArgumentException("Precio no puede ser negativo");
        }
    }

    /**
     * TODO extra 7: Obtiene una estrategia del mapa por su nombre.
     */
    public static java.util.function.DoubleUnaryOperator desafioObtenerEstrategiaEspecífica(
            java.util.Map<String, java.util.function.DoubleUnaryOperator> mapa, String nombre) {
        // GUÍA: seleccionar la estrategia es un simple map.get(clave). El test
        // busca "vip" en un mapa que la contiene y espera assertNotNull. Elegir
        // algoritmo = buscar en el registro, sin if/switch.
        return mapa.get(nombre);
    }

    /**
     * TODO extra 8: Lanza una excepción si la estrategia no existe en el mapa.
     */
    public static void desafioLanzarEstrategiaInexistente(
            java.util.Map<String, java.util.function.DoubleUnaryOperator> mapa, String nombre) {
        // GUÍA: una estrategia desconocida es un error de cliente →
        // IllegalArgumentException. containsKey distingue "no está" de "está pero
        // su valor es null". El test busca "hack" en un mapa con solo "vip" y
        // espera la excepción.
        if (!mapa.containsKey(nombre)) {
            throw new IllegalArgumentException("Estrategia inexistente");
        }
    }

    /**
     * TODO extra 9: Ejecuta una estrategia aplicando su función sobre un precio.
     */
    public static double desafioEjecutarEstrategia(java.util.function.DoubleUnaryOperator op, double precio) {
        // GUÍA: aplicar la estrategia es invocar su método funcional,
        // applyAsDouble (el de DoubleUnaryOperator; en Function sería apply). El
        // test: op = p->p-20 sobre 100 → 80.
        return op.applyAsDouble(precio);
    }

    /**
     * TODO extra 10: Comprueba que el mapa devuelto por estrategias() es inmodificable.
     */
    public static void desafioEstrategiasInmodificables(java.util.Map<String, java.util.function.DoubleUnaryOperator> mapa) {
        // GUÍA: error nº 4/seguridad — estrategias() devuelve un mapa INMODIFICABLE
        // (Map.of), así nadie inyecta una estrategia "pirata" en caliente. El
        // truco del test: intentar put() DEBE lanzar UnsupportedOperationException;
        // si NO la lanza, se fuerza un AssertionError (el mapa era mutable = fallo).
        // El test base llama esto con estrategias() y espera que NO propague error.
        try {
            mapa.put("hacked", p -> 0);
            throw new AssertionError("El mapa debería ser inmodificable");
        } catch (UnsupportedOperationException e) {
            // Correcto
        }
    }

}
