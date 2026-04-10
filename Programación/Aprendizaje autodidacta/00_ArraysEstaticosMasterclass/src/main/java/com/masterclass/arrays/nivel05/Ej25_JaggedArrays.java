package com.masterclass.arrays.nivel05;

/**
 * EJERCICIO 25 — Jagged Arrays (Triángulo de Pascal)
 * ===================================================
 * Nivel de Referencia Teórica: teoria/05_Arrays_Bidimensionales_Fundamentos.md
 * 
 * Un "Jagged Array" es un array de arrays donde cada fila tiene un tamaño distinto.
 */
public class Ej25_JaggedArrays {

    // TODO 1: Implementar crearEscalera(int niveles)
    // - Retorna un int[][] donde la fila 0 tiene tamaño 1, la fila 1 tamaño 2... etc.
    public static int[][] crearEscalera(int niveles) {
        return null;
    }

    // TODO 2: Implementar generarPascal(int niveles)
    // - Construye la famosa pirámide de Pascal usando jagged arrays.
    // - Tip: pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j].
    public static int[][] generarPascal(int niveles) {
        return null;
    }

    // TODO 3: Implementar aplanarJagged(int[][] jagged)
    // - Convierte el jagged array en un solo int[] lineal con todos los datos.
    public static int[] aplanarJagged(int[][] jagged) {
        return null;
    }

    // TODO 4: Implementar reconstruirJagged(int[] lineal, int[] formas)
    // - La inversa del anterior. Recibe los datos y el tamaño de cada fila.
    public static int[][] reconstruirJagged(int[] lineal, int[] formas) {
        return null;
    }

    // TODO 5: Implementar sumaFilaMasLarga(int[][] jagged)
    // - Identifica cuál es la fila con más elementos y devuelve su suma.
    public static int sumaFilaMasLarga(int[][] jagged) {
        return 0;
    }

    // TODO 6: Implementar copiarJagged(int[][] m)
    // - Deep copy manual. Recuerda que cada fila debe ser un nuevo array independiente.
    public static int[][] copiarJagged(int[][] m) {
        return null;
    }

    // TODO 7: Implementar buscarEnJagged(int[][] m, int valor)
    // - Retorna primera posición {fila, col} donde aparezca.
    public static int[] buscarEnJagged(int[][] m, int valor) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println("=== ARRAYS IRREGULARES ===");
    }
}
