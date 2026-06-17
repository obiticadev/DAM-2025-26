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
        // GUÍA: teoría 19.2 — la pieza más simple que un assertEquals puede probar.
        // Una línea: return a + b;
        // OJO: el test es assertEquals(5.0, adicionar(2.0, 3.0)) — comparación de
        // double exacta. Con sumas pequeñas no hay problema de coma flotante.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para adicionar");
    }

    /**
     * RETO EXTRA 02: Resta dos numeros reales.
     */
    public static double restar(double a, double b) {
        // GUÍA: teoría 19.2 — una línea: return a - b;
        // OJO: el test espera -1.0 para restar(2.0, 3.0), no 1.0. El orden
        // importa: es minuendo - sustraendo, a - b (no b - a).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para restar");
    }

    /**
     * RETO EXTRA 03: Multiplica dos numeros reales.
     */
    public static double multiplicar(double a, double b) {
        // GUÍA: teoría 19.2 — una línea: return a * b;
        // El test: assertEquals(6.0, multiplicar(2.0, 3.0)).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para multiplicar");
    }

    /**
     * RETO EXTRA 04: Divide dos numeros reales.
     */
    public static double dividir(double a, double b) {
        // GUÍA: teoría 19.2 — EL RETO TRAMPA del bloque (error común nº 2).
        // 1. El test es assertThrows(ArithmeticException.class, () -> dividir(1.0, 0.0)).
        // 2. CUIDADO: en Java, 1.0 / 0.0 con double NO lanza: da Double.INFINITY.
        //    Si haces solo "return a / b;", el test FALLA (no se lanza nada).
        // 3. Tienes que detectar el divisor cero TÚ y lanzar:
        //       if (b == 0) throw new ArithmeticException("división por cero");
        //       return a / b;
        // CULTURA: la división ENTERA (int/int) sí lanza ArithmeticException sola;
        // la de coma flotante no. Esa asimetría es justo lo que el test verifica.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dividir");
    }

    /**
     * RETO EXTRA 05: Determina si un entero es par.
     */
    public static boolean esPar(int n) {
        // GUÍA: teoría 19.2 — una línea: return n % 2 == 0;
        // El test: assertTrue(esPar(4)). El operador módulo (%) da el resto;
        // resto 0 al dividir entre 2 = par. (Funciona también para negativos:
        // -4 % 2 == 0.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPar");
    }

    /**
     * RETO EXTRA 06: Devuelve una cadena vacia.
     */
    public static String obtenerVacio() {
        // GUÍA: teoría 19.2 — una línea: return "";
        // OJO: cadena VACÍA (""), no null. El test usa assertEquals("", ...),
        // y "".equals(null) sería false. Vacío ≠ null (lo verás en 19.3).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerVacio");
    }

    /**
     * RETO EXTRA 07: Devuelve un valor nulo.
     */
    public static Object retornarNulo() {
        // GUÍA: teoría 19.2 — una línea: return null;
        // El test usa assertNull(retornarNulo()). assertNull pasa solo si el
        // valor es exactamente null (contrapartida de assertNotNull).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retornarNulo");
    }

    /**
     * RETO EXTRA 08: Concatena dos cadenas.
     */
    public static String concatenarTextos(String a, String b) {
        // GUÍA: teoría 19.2 — una línea: return a + b;
        // El test: assertEquals("ab", concatenarTextos("a", "b")). El "+" sobre
        // String concatena; sin separador, "a"+"b" = "ab".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para concatenarTextos");
    }

    /**
     * RETO EXTRA 09: Devuelve el mayor de dos enteros.
     */
    public static int obtenerMayor(int a, int b) {
        // GUÍA: teoría 19.2 — una línea: return Math.max(a, b);
        // El test: assertEquals(5, obtenerMayor(3, 5)). Math.max evita el
        // if/else manual y deja la intención clarísima.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerMayor");
    }

    /**
     * RETO EXTRA 10: Devuelve el menor de dos enteros.
     */
    public static int obtenerMenor(int a, int b) {
        // GUÍA: teoría 19.2 — una línea: return Math.min(a, b);
        // El test: assertEquals(3, obtenerMenor(3, 5)). Simétrico al reto 9.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerMenor");
    }

}
