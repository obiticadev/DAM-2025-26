package com.masterclass.arrays.nivel03;

/**
 * EJERCICIO 12 — Selection Sort
 * ==============================
 * Nivel de Referencia Teórica: teoria/03_Ordenacion_Sobre_Arrays.md
 *
 * Implementarás el algoritmo Selection Sort que en cada iteración
 * busca el mínimo del subarray no ordenado y lo coloca en su posición
 * definitiva mediante un único swap.
 */
public class Ej12_SelectionSort {

    // TODO 1: Implementar sortAscendente(int[] array)
    //   Especificación técnica:
    //   - Ordenar el array IN-PLACE de menor a mayor.
    //   - Para cada posición i (0 a length-2):
    //     a) Buscar el ÍNDICE del elemento mínimo en el subarray [i, length-1].
    //     b) Si el mínimo no está en la posición i, hacer swap(arr, i, indiceMin).
    //   - Tras la iteración i, los primeros i+1 elementos están en su posición final.
    //   - Si array es null, lanzar IllegalArgumentException.
    public static void sortAscendente(int[] array) {
        // Sustituir con implementación
    }

    // TODO 2: Implementar sortDescendente(int[] array)
    //   Especificación técnica:
    //   - Ordenar de MAYOR a MENOR. En cada iteración buscar el MÁXIMO
    //     del subarray no ordenado y colocarlo al principio.
    public static void sortDescendente(int[] array) {
        // Sustituir con implementación
    }

    // TODO 3: Implementar encontrarIndiceMinimo(int[] array, int desde)
    //   Especificación técnica:
    //   - Buscar el índice del elemento más pequeño en el rango [desde, array.length-1].
    //   - Inicializar el candidato con 'desde', no con 0.
    //   - Devolver el índice (no el valor).
    //   - Si desde < 0 o desde >= array.length, lanzar IndexOutOfBoundsException.
    public static int encontrarIndiceMinimo(int[] array, int desde) {
        return -1;
    }

    // TODO 4: Implementar encontrarIndiceMaximo(int[] array, int desde)
    //   Especificación técnica:
    //   - Misma lógica que TODO 3 pero buscando el elemento más GRANDE.
    //   - Devolver el índice del máximo en [desde, length-1].
    public static int encontrarIndiceMaximo(int[] array, int desde) {
        return -1;
    }

    // TODO 5: Implementar sortConContador(int[] array)
    //   Especificación técnica:
    //   - Ordenar ascendentemente con Selection Sort.
    //   - Devolver int[2]: [0]=comparaciones, [1]=swaps.
    //   - Selection Sort siempre hace exactamente n-1 swaps (como máximo).
    //   - Las comparaciones siempre son n*(n-1)/2 (no hay optimización posible).
    public static int[] sortConContador(int[] array) {
        return null;
    }

    // TODO 6: Implementar sortDoble(int[] array)
    //   Especificación técnica:
    //   - Variante "Double Selection Sort": en cada iteración, encontrar SIMULTÁNEAMENTE
    //     el mínimo y el máximo del subarray no procesado.
    //   - Colocar el mínimo al principio y el máximo al final en la misma iteración.
    //   - El rango no procesado se reduce por ambos extremos.
    //   - Cuidado con el caso especial: si el máximo estaba en la posición donde pusimos
    //     el mínimo, ajustar el índice del máximo tras el primer swap.
    public static void sortDoble(int[] array) {
        // Sustituir con implementación
    }

    // TODO 7: Implementar obtenerKMenores(int[] array, int k)
    //   Especificación técnica:
    //   - Usando la idea de Selection Sort (selección parcial), encontrar los K
    //     elementos más pequeños del array y devolverlos en un NUEVO int[k] ordenado.
    //   - NO ordenar todo el array: solo hacer K iteraciones de selección.
    //   - El array original NO debe ser modificado (trabajar sobre una copia).
    //   - Si k > array.length, lanzar IllegalArgumentException.
    //   - Si k <= 0, devolver un array vacío.
    public static int[] obtenerKMenores(int[] array, int k) {
        return null;
    }

    // ================================================================
    // ZONA DE EJECUCIÓN MASTER (Playground)
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 12: Selection Sort ===");
        System.out.println();

        // Descomenta y prueba:
        // int[] datos = {29, 10, 14, 37, 13};
        // sortAscendente(datos);
        // for (int d : datos) System.out.print(d + " ");
        // System.out.println();
        //
        // int[] menores = obtenerKMenores(new int[]{50, 10, 30, 20, 40}, 3);
        // System.out.print("3 menores: ");
        // for (int m : menores) System.out.print(m + " ");

        System.out.println(">> Implementa los TODOs y descomenta las pruebas <<");
    }
}
