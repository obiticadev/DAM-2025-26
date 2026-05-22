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

        /**
     * RETO EXTRA 01: Suma dos numeros reales.
     */
    public static double adicionar(double a, double b) {
        // TODO extra: RETO EXTRA 01: Suma dos numeros reales.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para adicionar");
    }

    /**
     * RETO EXTRA 02: Resta dos numeros reales.
     */
    public static double restar(double a, double b) {
        // TODO extra: RETO EXTRA 02: Resta dos numeros reales.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para restar");
    }

    /**
     * RETO EXTRA 03: Multiplica dos numeros reales.
     */
    public static double multiplicar(double a, double b) {
        // TODO extra: RETO EXTRA 03: Multiplica dos numeros reales.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para multiplicar");
    }

    /**
     * RETO EXTRA 04: Divide dos numeros reales.
     */
    public static double dividir(double a, double b) {
        // TODO extra: RETO EXTRA 04: Divide dos numeros reales.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dividir");
    }

    /**
     * RETO EXTRA 05: Determina si un entero es par.
     */
    public static boolean esPar(int n) {
        // TODO extra: RETO EXTRA 05: Determina si un entero es par.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPar");
    }

    /**
     * RETO EXTRA 06: Devuelve una cadena vacia.
     */
    public static String obtenerVacio() {
        // TODO extra: RETO EXTRA 06: Devuelve una cadena vacia.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerVacio");
    }

    /**
     * RETO EXTRA 07: Devuelve un valor nulo.
     */
    public static Object retornarNulo() {
        // TODO extra: RETO EXTRA 07: Devuelve un valor nulo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retornarNulo");
    }

    /**
     * RETO EXTRA 08: Concatena dos cadenas.
     */
    public static String concatenarTextos(String a, String b) {
        // TODO extra: RETO EXTRA 08: Concatena dos cadenas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para concatenarTextos");
    }

    /**
     * RETO EXTRA 09: Devuelve el mayor de dos enteros.
     */
    public static int obtenerMayor(int a, int b) {
        // TODO extra: RETO EXTRA 09: Devuelve el mayor de dos enteros.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerMayor");
    }

    /**
     * RETO EXTRA 10: Devuelve el menor de dos enteros.
     */
    public static int obtenerMenor(int a, int b) {
        // TODO extra: RETO EXTRA 10: Devuelve el menor de dos enteros.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerMenor");
    }

}
