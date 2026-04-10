package com.masterclass.arrays.nivel05;

/**
 * EJERCICIO 23 — Diagonales Principal y Secundaria
 * =================================================
 * Nivel de Referencia Teórica: teoria/05_Arrays_Bidimensionales_Fundamentos.md
 * 
 * Las diagonales solo tienen sentido matemático pleno en matrices CUADRADAS.
 */
public class Ej23_DiagonalesPrincipalSecundaria {

    // TODO 1: Implementar esCuadrada(int[][] m)
    // - Retorna true si filas == columnas.
    public static boolean esCuadrada(int[][] m) {
        return false;
    }

    // TODO 2: Implementar diagonalPrincipal(int[][] m)
    // - Retorna array con elementos [i][i]. Lanzar exception si no es cuadrada.
    public static int[] diagonalPrincipal(int[][] m) {
        return null;
    }

    // TODO 3: Implementar diagonalSecundaria(int[][] m)
    // - Los índices para n=3 son: [0][2], [1][1], [2][0]. 
    // - Fórmula: [i][n - 1 - i].
    public static int[] diagonalSecundaria(int[][] m) {
        return null;
    }

    // TODO 4: Implementar sumaDiagonalPrincipal(int[][] m)
    public static int sumaDiagonalPrincipal(int[][] m) {
        return 0;
    }

    // TODO 5: Implementar sumaDiagonalSecundaria(int[][] m)
    public static int sumaDiagonalSecundaria(int[][] m) {
        return 0;
    }

    // TODO 6: Implementar obtenerDiagonalesParalelasSuperiores(int[][] m)
    // - Extraer todas las diagonales que hay por ENCIMA de la principal.
    public static int[][] obtenerDiagonalesParalelasSuperiores(int[][] m) {
        return null;
    }

    // TODO 7: Implementar esMatrizTriangularSuperior(int[][] m)
    // - Todos los elementos debajo de la diagonal principal deben ser 0.
    public static boolean esMatrizTriangularSuperior(int[][] m) {
        return false;
    }

    public static void main(String[] args) {
        System.out.println("=== MATEMÁTICA DE DIAGONALES ===");
    }
}
