package com.masterclass.arrays.nivel03;

/**
 * EJERCICIO 15 — MergeSort
 * =========================
 * Nivel de Referencia Teórica: teoria/03_Ordenacion_Sobre_Arrays.md
 *
 * Implementarás MergeSort, el algoritmo divide-y-vencerás que garantiza
 * O(n log n) en TODOS los casos, a costa de usar un array auxiliar O(n).
 * Dominarás la operación de merge: fusionar dos mitades ordenadas.
 */
public class Ej15_MergeSort {

    // TODO 1: Implementar sort(int[] array)
    //   Especificación técnica:
    //   - Método público de entrada.
    //   - Crear un array auxiliar del mismo tamaño que 'array' (int[] aux = new int[array.length]).
    //   - Llamar al método recursivo sort(array, aux, 0, array.length - 1).
    //   - Si array es null, lanzar IllegalArgumentException.
    //   - Si tiene 0 o 1 elementos, no hacer nada.
    public static void sort(int[] array) {
        // Sustituir con implementación
    }

    // TODO 2: Implementar sort(int[] array, int[] aux, int left, int right)
    //   Especificación técnica:
    //   - Caso base: si left >= right, retornar.
    //   - Calcular mid = left + (right - left) / 2.
    //   - Recursión sobre la mitad izquierda: sort(array, aux, left, mid).
    //   - Recursión sobre la mitad derecha: sort(array, aux, mid + 1, right).
    //   - Fusionar ambas mitades: merge(array, aux, left, mid, right).
    private static void sort(int[] array, int[] aux, int left, int right) {
        // Sustituir con implementación
    }

    // TODO 3: Implementar merge(int[] array, int[] aux, int left, int mid, int right)
    //   Especificación técnica:
    //   - Copiar el rango [left, right] de 'array' al array auxiliar 'aux'.
    //   - Usar tres punteros: i=left (mitad izq), j=mid+1 (mitad der), k=left (posición de escritura).
    //   - Comparar aux[i] y aux[j]: el menor va a array[k]; avanzar el puntero correspondiente y k.
    //   - Cuando una mitad se agota, copiar el resto de la otra.
    //   - Al terminar, array[left..right] está ordenado.
    public static void merge(int[] array, int[] aux, int left, int mid, int right) {
        // Sustituir con implementación
    }

    // TODO 4: Implementar mergeArrays(int[] a, int[] b)
    //   Especificación técnica:
    //   - Dados dos arrays YA ORDENADOS, fusionarlos en un NUEVO array ordenado.
    //   - NO es necesario ordenar internamente. Solo recorrido con dos punteros.
    //   - Devolver el nuevo array de tamaño a.length + b.length.
    //   - Si alguno es null, lanzar IllegalArgumentException.
    //   - Si alguno está vacío, devolver una copia del otro.
    public static int[] mergeArrays(int[] a, int[] b) {
        return null;
    }

    // TODO 5: Implementar sortBottomUp(int[] array)
    //   Especificación técnica:
    //   - Variante ITERATIVA de MergeSort (sin recursión).
    //   - Empezar con subarrays de tamaño 1, luego 2, 4, 8, ... hasta cubrir todo el array.
    //   - Para cada tamaño, recorrer el array fusionando pares de subarrays consecutivos.
    //   - Usar un array auxiliar para las fusiones.
    //   - Complejidad: O(n log n) igual que la versión recursiva.
    public static void sortBottomUp(int[] array) {
        // Sustituir con implementación
    }

    // TODO 6: Implementar contarInversiones(int[] array)
    //   Especificación técnica:
    //   - Una "inversión" es un par (i, j) donde i < j pero array[i] > array[j].
    //   - Contar TODAS las inversiones usando una variante de MergeSort.
    //   - Durante el merge, cuando se toma un elemento de la mitad derecha, la cantidad
    //     de inversiones aumenta en (mid - i + 1) (los elementos restantes de la izquierda).
    //   - Devolver el total de inversiones.
    //   - No modificar el array original (trabajar sobre copia).
    public static long contarInversiones(int[] array) {
        return -1;
    }

    // TODO 7: Implementar estaOrdenado(int[] array)
    //   Especificación técnica:
    //   - Verificar si el array está ordenado ascendentemente.
    //   - Comprobar que arr[i] <= arr[i+1] para todo i.
    //   - Devolver true si está ordenado, false si no.
    //   - Array vacío o de uno → true. Null → excepción.
    public static boolean estaOrdenado(int[] array) {
        return false;
    }

    // ================================================================
    // ZONA DE EJECUCIÓN MASTER (Playground)
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 15: MergeSort ===");
        System.out.println();

        // Descomenta y prueba:
        // int[] datos = {38, 27, 43, 3, 9, 82, 10};
        // sort(datos);
        // System.out.print("MergeSort: ");
        // for (int d : datos) System.out.print(d + " ");
        // System.out.println();
        //
        // int[] merged = mergeArrays(new int[]{1, 5, 8}, new int[]{2, 4, 9});
        // System.out.print("Merged: ");
        // for (int m : merged) System.out.print(m + " ");

        System.out.println(">> Implementa los TODOs y descomenta las pruebas <<");
    }
}
