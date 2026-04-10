package com.masterclass.arrays.nivel03;

/**
 * EJERCICIO 11 — Bubble Sort
 * ===========================
 * Nivel de Referencia Teórica: teoria/03_Ordenacion_Sobre_Arrays.md
 *
 * Implementarás el algoritmo Bubble Sort in-place con la optimización
 * del flag de "sin swaps" para detectar que el array ya está ordenado.
 * Aprenderás a contar comparaciones y swaps para análisis de rendimiento.
 */
public class Ej11_BubbleSort {

    // TODO 1: Implementar sortAscendente(int[] array)
    //   Especificación técnica:
    //   - Ordenar el array IN-PLACE de menor a mayor usando Bubble Sort.
    //   - En cada pasada, comparar elementos adyacentes: si arr[j] > arr[j+1], hacer swap.
    //   - Cada pasada "burbujea" el mayor al final → la zona no ordenada se reduce en 1.
    //   - Incluir la OPTIMIZACIÓN del flag 'swapped': si en una pasada completa no se
    //     realiza ningún swap, el array ya está ordenado → salir del bucle inmediatamente.
    //   - Si el array es null, lanzar IllegalArgumentException.
    //   - Si tiene 0 o 1 elementos, no hacer nada (ya está ordenado).
    public static void sortAscendente(int[] array) {
        // Sustituir con implementación
    }

    // TODO 2: Implementar sortDescendente(int[] array)
    //   Especificación técnica:
    //   - Misma lógica que TODO 1 pero ordenando de MAYOR a MENOR.
    //   - Condición de swap invertida: si arr[j] < arr[j+1], hacer swap.
    //   - Mismas validaciones y optimización del flag.
    public static void sortDescendente(int[] array) {
        // Sustituir con implementación
    }

    // TODO 3: Implementar sortConContador(int[] array)
    //   Especificación técnica:
    //   - Ordenar de menor a mayor con Bubble Sort.
    //   - Devolver un int[2] con estadísticas:
    //     resultado[0] = número total de COMPARACIONES realizadas.
    //     resultado[1] = número total de SWAPS realizados.
    //   - Esto permite analizar el rendimiento empírico del algoritmo.
    //   - Si array es null, lanzar IllegalArgumentException.
    public static int[] sortConContador(int[] array) {
        return null;
    }

    // TODO 4: Implementar estaOrdenado(int[] array)
    //   Especificación técnica:
    //   - Verificar si el array ya está ordenado de menor a mayor.
    //   - Recorrer comparando arr[i] <= arr[i+1] para todo i.
    //   - Devolver true si está ordenado, false si no.
    //   - Array vacío o de un elemento → true.
    //   - Si array es null, lanzar IllegalArgumentException.
    public static boolean estaOrdenado(int[] array) {
        return false;
    }

    // TODO 5: Implementar sortRango(int[] array, int desde, int hasta)
    //   Especificación técnica:
    //   - Aplicar Bubble Sort SOLO al rango [desde, hasta] (ambos inclusivos).
    //   - Los elementos fuera del rango no se tocan.
    //   - Validar que desde >= 0, hasta < array.length, desde <= hasta.
    //   - Lanzar IndexOutOfBoundsException si el rango es inválido.
    public static void sortRango(int[] array, int desde, int hasta) {
        // Sustituir con implementación
    }

    // TODO 6: Implementar swap(int[] array, int i, int j)
    //   Especificación técnica:
    //   - Intercambiar los valores de array[i] y array[j] usando una variable temporal.
    //   - Si algún índice es inválido, lanzar IndexOutOfBoundsException.
    //   - Este método auxiliar será usado internamente por los demás.
    public static void swap(int[] array, int i, int j) {
        // Sustituir con implementación
    }

    // TODO 7: Implementar sortBidireccional(int[] array)
    //   Especificación técnica:
    //   - Variante "Cocktail Shaker Sort": alternar pasadas de izquierda→derecha
    //     y derecha→izquierda en cada iteración.
    //   - En la pasada izq→der, el mayor burbujea al final.
    //   - En la pasada der→izq, el menor burbujea al inicio.
    //   - Incluir flag de optimización: si no hay swaps en un ciclo completo, salir.
    //   - Esto mejora el rendimiento en arrays con elementos desplazados en ambos extremos.
    public static void sortBidireccional(int[] array) {
        // Sustituir con implementación
    }

    // ================================================================
    // ZONA DE EJECUCIÓN MASTER (Playground)
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 11: Bubble Sort ===");
        System.out.println();

        // Descomenta y prueba:
        // int[] datos = {64, 34, 25, 12, 22, 11, 90};
        // System.out.print("Original: ");
        // for (int d : datos) System.out.print(d + " ");
        // System.out.println();
        //
        // sortAscendente(datos);
        // System.out.print("Ordenado: ");
        // for (int d : datos) System.out.print(d + " ");
        // System.out.println();
        //
        // int[] stats = sortConContador(new int[]{5, 3, 8, 1, 2});
        // System.out.println("Comparaciones: " + stats[0] + ", Swaps: " + stats[1]);

        System.out.println(">> Implementa los TODOs y descomenta las pruebas <<");
    }
}
