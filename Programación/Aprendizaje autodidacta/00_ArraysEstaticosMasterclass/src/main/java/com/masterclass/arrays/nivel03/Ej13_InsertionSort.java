package com.masterclass.arrays.nivel03;

/**
 * EJERCICIO 13 — Insertion Sort
 * ==============================
 * Nivel de Referencia Teórica: teoria/03_Ordenacion_Sobre_Arrays.md
 *
 * Implementarás Insertion Sort, un algoritmo que simula cómo ordenas
 * una mano de cartas: tomas cada carta y la insertas en su posición
 * correcta entre las ya ordenadas. Es el más eficiente de los O(n²)
 * para arrays casi-ordenados.
 */
public class Ej13_InsertionSort {

    // TODO 1: Implementar sortAscendente(int[] array)
    //   Especificación técnica:
    //   - Ordenar el array IN-PLACE de menor a mayor.
    //   - Para cada posición i (desde 1 hasta length-1):
    //     a) Guardar el valor actual (key = arr[i]).
    //     b) Comparar key con los elementos anteriores (j = i-1 hacia 0).
    //     c) Mientras arr[j] > key, desplazar arr[j] una posición a la derecha.
    //     d) Insertar key en la posición j+1 (donde dejó de desplazar).
    //   - Si array es null, lanzar IllegalArgumentException.
    public static void sortAscendente(int[] array) {
        // Sustituir con implementación
    }

    // TODO 2: Implementar sortDescendente(int[] array)
    //   Especificación técnica:
    //   - Misma lógica pero ordenando de MAYOR a MENOR.
    //   - Condición de desplazamiento invertida: mientras arr[j] < key, desplazar.
    public static void sortDescendente(int[] array) {
        // Sustituir con implementación
    }

    // TODO 3: Implementar sortConContador(int[] array)
    //   Especificación técnica:
    //   - Ordenar ascendentemente y devolver int[2]: [0]=comparaciones, [1]=desplazamientos.
    //   - Contar cada comparación (arr[j] > key) y cada desplazamiento (arr[j+1] = arr[j]).
    //   - En el mejor caso (ya ordenado), comparaciones = n-1, desplazamientos = 0.
    //   - En el peor caso (orden inverso), comparaciones y desplazamientos = n*(n-1)/2.
    public static int[] sortConContador(int[] array) {
        return null;
    }

    // TODO 4: Implementar sortRango(int[] array, int desde, int hasta)
    //   Especificación técnica:
    //   - Aplicar Insertion Sort SOLO al subarray [desde, hasta] (inclusivos).
    //   - Los elementos fuera del rango no se tocan.
    //   - Validar rango.
    public static void sortRango(int[] array, int desde, int hasta) {
        // Sustituir con implementación
    }

    // TODO 5: Implementar insertarEnOrdenado(int[] arrayOrdenado, int tamanioLogico, int valor)
    //   Especificación técnica:
    //   - El array ya está ordenado ascendentemente (primeros 'tamanioLogico' elementos).
    //   - Encontrar la posición correcta de 'valor' desplazando elementos mayores.
    //   - Insertar 'valor' en su posición manteniendo el orden.
    //   - Devolver el nuevo tamaño lógico.
    //   - Si el array está lleno (tamanioLogico == array.length), lanzar IllegalStateException.
    public static int insertarEnOrdenado(int[] arrayOrdenado, int tamanioLogico, int valor) {
        return -1;
    }

    // TODO 6: Implementar ordenarPorInsercionBinaria(int[] array)
    //   Especificación técnica:
    //   - Variante de Insertion Sort que usa BÚSQUEDA BINARIA para encontrar la posición
    //     de inserción en la parte ya ordenada (reduce comparaciones a O(n log n)).
    //   - Sin embargo, los desplazamientos siguen siendo O(n²) porque son físicos.
    //   - Implementar una búsqueda binaria interna que devuelva el índice donde insertar,
    //     y luego desplazar los elementos para hacer hueco.
    public static void ordenarPorInsercionBinaria(int[] array) {
        // Sustituir con implementación
    }

    // TODO 7: Implementar esCasiOrdenado(int[] array, int maxDesplazamiento)
    //   Especificación técnica:
    //   - Verificar si cada elemento del array está como máximo a 'maxDesplazamiento'
    //     posiciones de su posición final ordenada.
    //   - Estrategia: ordenar una copia y comparar la posición original vs final de cada elemento.
    //   - Un array "casi ordenado" con maxDesplazamiento pequeño es ideal para Insertion Sort.
    //   - Devolver true si ningún elemento se desplaza más de 'maxDesplazamiento' posiciones.
    public static boolean esCasiOrdenado(int[] array, int maxDesplazamiento) {
        return false;
    }

    // ================================================================
    // ZONA DE EJECUCIÓN MASTER (Playground)
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 13: Insertion Sort ===");
        System.out.println();

        // Descomenta y prueba:
        // int[] datos = {12, 11, 13, 5, 6};
        // sortAscendente(datos);
        // for (int d : datos) System.out.print(d + " ");
        // System.out.println();
        //
        // int[] stats = sortConContador(new int[]{1, 2, 3, 4, 5}); // Mejor caso
        // System.out.println("Mejor caso → Comparaciones: " + stats[0] + ", Desplazamientos: " + stats[1]);

        System.out.println(">> Implementa los TODOs y descomenta las pruebas <<");
    }
}
