package com.masterclass.arrays.nivel01;

/**
 * EJERCICIO 02 — Recorrido y Operaciones Básicas
 * ================================================
 * Nivel de Referencia Teórica: teoria/01_Fundamentos_Arrays.md
 *
 * Dominarás los patrones de recorrido (for, for-each, inverso) y
 * realizarás operaciones aritméticas fundamentales sobre los elementos
 * de un array sin recurrir a Streams ni a la API de Collections.
 */
public class Ej02_RecorridoYOperaciones {

    // TODO 1: Implementar calcularSuma(int[] array)
    //   Especificación técnica:
    //   - Recorrer el array completo acumulando la suma de todos sus elementos.
    //   - Devolver el resultado como int.
    //   - Si el array es null o está vacío, devolver 0.
    public static int calcularSuma(int[] array) {
        return 0;
    }

    // TODO 2: Implementar calcularMedia(int[] array)
    //   Especificación técnica:
    //   - Calcular la media aritmética de los elementos del array.
    //   - Devolver el resultado como double (división real, no entera).
    //   - Si el array es null o está vacío, devolver 0.0.
    //   - Atención al casting: asegurar que la división produce decimales.
    public static double calcularMedia(int[] array) {
        return 0.0;
    }

    // TODO 3: Implementar encontrarMaximo(int[] array)
    //   Especificación técnica:
    //   - Recorrer el array buscando el valor más grande.
    //   - Inicializar el candidato con el primer elemento (no con 0 ni Integer.MIN_VALUE).
    //   - Si el array es null o está vacío, lanzar IllegalArgumentException.
    //   - El array puede contener valores negativos.
    public static int encontrarMaximo(int[] array) {
        return 0;
    }

    // TODO 4: Implementar encontrarMinimo(int[] array)
    //   Especificación técnica:
    //   - Misma lógica que TODO 3 pero buscando el valor más pequeño.
    //   - Inicializar el candidato con el primer elemento.
    //   - Si el array es null o está vacío, lanzar IllegalArgumentException.
    public static int encontrarMinimo(int[] array) {
        return 0;
    }

    // TODO 5: Implementar contarPares(int[] array)
    //   Especificación técnica:
    //   - Contar cuántos elementos del array son pares (divisibles entre 2 sin resto).
    //   - Usar el operador módulo (%) para la comprobación.
    //   - El cero (0) se considera par.
    //   - Números negativos también pueden ser pares (ej. -4 es par).
    //   - Si el array es null o vacío, devolver 0.
    public static int contarPares(int[] array) {
        return 0;
    }

    // TODO 6: Implementar generarArraySecuencial(int inicio, int cantidad)
    //   Especificación técnica:
    //   - Crear un int[] de tamaño 'cantidad'.
    //   - Rellenarlo con valores consecutivos comenzando desde 'inicio'.
    //   - Ejemplo: generarArraySecuencial(5, 4) → {5, 6, 7, 8}.
    //   - Si cantidad es negativa, lanzar IllegalArgumentException.
    //   - Si cantidad es 0, devolver un array vacío.
    public static int[] generarArraySecuencial(int inicio, int cantidad) {
        return null;
    }

    // TODO 7: Implementar encontrarIndice(int[] array, int valor)
    //   Especificación técnica:
    //   - Recorrer el array de izquierda a derecha buscando la PRIMERA ocurrencia de 'valor'.
    //   - Si lo encuentra, devolver el índice (posición 0-based).
    //   - Si NO lo encuentra, devolver -1.
    //   - Si el array es null, lanzar IllegalArgumentException.
    public static int encontrarIndice(int[] array, int valor) {
        return -1;
    }

    // TODO 8: Implementar contarOcurrencias(int[] array, int valor)
    //   Especificación técnica:
    //   - Contar cuántas veces aparece 'valor' en el array completo.
    //   - Devolver el recuento total.
    //   - Si el array es null o vacío, devolver 0.
    public static int contarOcurrencias(int[] array, int valor) {
        return 0;
    }

    // ================================================================
    // ZONA DE EJECUCIÓN MASTER (Playground)
    // Pulsa "Run" para comprobar visualmente tu implementación.
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 02: Recorrido y Operaciones ===");
        System.out.println();

        // Descomenta y prueba tus métodos aquí:
        // int[] datos = {10, 25, 3, 47, 8, 12, 36};
        // System.out.println("Suma: " + calcularSuma(datos));
        // System.out.println("Media: " + calcularMedia(datos));
        // System.out.println("Máximo: " + encontrarMaximo(datos));
        // System.out.println("Mínimo: " + encontrarMinimo(datos));
        // System.out.println("Pares: " + contarPares(datos));
        // System.out.println("Índice de 47: " + encontrarIndice(datos, 47));
        // System.out.println("Ocurrencias de 10: " + contarOcurrencias(datos, 10));

        System.out.println();
        System.out.println(">> Implementa los TODOs y descomenta las pruebas <<");
    }
}
