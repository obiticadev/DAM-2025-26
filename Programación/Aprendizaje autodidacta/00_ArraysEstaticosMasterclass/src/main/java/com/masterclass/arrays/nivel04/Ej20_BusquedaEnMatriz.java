package com.masterclass.arrays.nivel04;

/**
 * EJERCICIO 20 — Búsqueda en Matriz 2D
 * =======================================
 * Nivel de Referencia Teórica: teoria/04_Busqueda_En_Arrays.md
 *
 * Implementarás búsqueda en matrices 2D: desde la fuerza bruta hasta
 * la técnica Staircase (esquina superior derecha) para matrices
 * donde filas y columnas están ordenadas. Complejidad O(m + n).
 */
public class Ej20_BusquedaEnMatriz {

    // TODO 1: Implementar buscarFuerzaBruta(int[][] matriz, int target)
    //   Especificación técnica:
    //   - Recorrer TODA la matriz fila por fila, columna por columna.
    //   - Si encuentra target, devolver un int[2] con {fila, columna}.
    //   - Si no encuentra, devolver null.
    //   - Complejidad: O(filas × columnas).
    //   - Si matriz es null, lanzar IllegalArgumentException.
    public static int[] buscarFuerzaBruta(int[][] matriz, int target) {
        return null;
    }

    // TODO 2: Implementar buscarStaircase(int[][] matriz, int target)
    //   Especificación técnica:
    //   - REQUISITO: cada fila está ordenada de izq→der, cada columna de arriba→abajo.
    //   - Empezar en la ESQUINA SUPERIOR DERECHA: fila=0, col=columnas-1.
    //   - Si el elemento actual == target → devolver {fila, col}.
    //   - Si el elemento actual > target → moverse a la IZQUIERDA (col--).
    //   - Si el elemento actual < target → moverse ABAJO (fila++).
    //   - Si se sale de los límites → devolver null (no encontrado).
    //   - Complejidad: O(filas + columnas).
    //   - Si matriz es null o vacía, lanzar IllegalArgumentException.
    public static int[] buscarStaircase(int[][] matriz, int target) {
        return null;
    }

    // TODO 3: Implementar buscarBinariaPorFilas(int[][] matriz, int target)
    //   Especificación técnica:
    //   - REQUISITO: cada fila está ordenada individualmente.
    //   - Para cada fila, aplicar búsqueda binaria para buscar target.
    //   - Si se encuentra en alguna fila, devolver {fila, col}.
    //   - Si no se encuentra en ninguna fila, devolver null.
    //   - Complejidad: O(filas × log(columnas)).
    public static int[] buscarBinariaPorFilas(int[][] matriz, int target) {
        return null;
    }

    // TODO 4: Implementar contarMenores(int[][] matriz, int target)
    //   Especificación técnica:
    //   - REQUISITO: matriz con filas y columnas ordenadas.
    //   - Contar cuántos elementos de la matriz son ESTRICTAMENTE menores que target.
    //   - Usar la técnica staircase empezando por la esquina inferior izquierda.
    //   - Complejidad: O(filas + columnas).
    //   - Si matriz es null, lanzar IllegalArgumentException.
    public static int contarMenores(int[][] matriz, int target) {
        return -1;
    }

    // TODO 5: Implementar existeEnMatriz(int[][] matriz, int target)
    //   Especificación técnica:
    //   - Devolver true si target existe en la matriz, false si no.
    //   - Usar el método más eficiente disponible (buscarStaircase si la matriz está ordenada).
    //   - Si matriz es null, lanzar IllegalArgumentException.
    public static boolean existeEnMatriz(int[][] matriz, int target) {
        return false;
    }

    // TODO 6: Implementar buscarMinimo(int[][] matriz)
    //   Especificación técnica:
    //   - Encontrar el valor MÍNIMO de toda la matriz.
    //   - Devolver int[3]: {valor, fila, columna}.
    //   - Si la matriz está vacía o es null, lanzar IllegalArgumentException.
    //   - Si la matriz tiene filas y columnas ordenadas, el mínimo siempre está en [0][0].
    //   - Si la matriz NO está ordenada, recorrer todo.
    public static int[] buscarMinimo(int[][] matriz) {
        return null;
    }

    // TODO 7: Implementar buscarMaximo(int[][] matriz)
    //   Especificación técnica:
    //   - Encontrar el valor MÁXIMO de toda la matriz.
    //   - Devolver int[3]: {valor, fila, columna}.
    //   - Si la matriz está ordenada por filas y columnas, el máximo está en la última posición.
    //   - Si no, recorrer todo.
    public static int[] buscarMaximo(int[][] matriz) {
        return null;
    }

    // ================================================================
    // ZONA DE EJECUCIÓN MASTER (Playground)
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 20: Búsqueda en Matriz 2D ===");
        System.out.println();

        // Descomenta y prueba:
        // int[][] matriz = {
        //     { 1,  4,  7, 11},
        //     { 2,  5,  8, 12},
        //     { 3,  6,  9, 16},
        //     {10, 13, 14, 17}
        // };
        //
        // int[] pos = buscarStaircase(matriz, 14);
        // if (pos != null) {
        //     System.out.println("14 encontrado en [" + pos[0] + "][" + pos[1] + "]");
        // }
        //
        // pos = buscarStaircase(matriz, 15);
        // System.out.println("15 encontrado: " + (pos != null));

        System.out.println(">> Implementa los TODOs y descomenta las pruebas <<");
    }
}
