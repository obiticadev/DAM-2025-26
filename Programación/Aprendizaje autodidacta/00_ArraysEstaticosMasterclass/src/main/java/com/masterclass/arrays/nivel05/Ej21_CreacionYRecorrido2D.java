package com.masterclass.arrays.nivel05;

/**
 * EJERCICIO 21 — Creación y Recorrido de Matrices 2D
 * ==================================================
 * Nivel de Referencia Teórica: teoria/05_Arrays_Bidimensionales_Fundamentos.md
 * 
 * En Java, una matriz 2D es técnicamente un "array de arrays".
 * Este ejercicio asienta las bases de la iteración anidada.
 */
public class Ej21_CreacionYRecorrido2D {

    // TODO 1: Implementar crearMatrizIncremental(int filas, int cols)
    // - Debe devolver una matriz rellena con 1, 2, 3... correlativamente por filas.
    public static int[][] crearMatrizIncremental(int filas, int cols) {
        return null;
    }

    // TODO 2: Implementar sumaTotal(int[][] matriz)
    // - Recorrer toda la matriz y devolver la suma de cada celda.
    public static int sumaTotal(int[][] matriz) {
        return 0;
    }

    // TODO 3: Implementar obtenerFila(int[][] matriz, int index)
    // - Extraer una fila completa como un array de 1D.
    // - Debe validar límites y lanzar IllegalArgumentException si es necesario.
    public static int[] obtenerFila(int[][] matriz, int index) {
        return null;
    }

    // TODO 4: Implementar obtenerColumna(int[][] matriz, int index)
    // - Extraer una columna completa. Requiere iterar sobre las filas.
    public static int[] obtenerColumna(int[][] matriz, int index) {
        return null;
    }

    // TODO 5: Implementar contarOcurrencias(int[][] matriz, int valor)
    // - ¿Cuántas veces aparece el número 'valor' en todo el grid?
    public static int contarOcurrencias(int[][] matriz, int valor) {
        return 0;
    }

    // TODO 6: Implementar rellenarPatronTablero(int[][] matriz)
    // - Rellenar con 1 y 0 alternadamente (como un tablero de ajedrez).
    // - Tip: Usa (i + j) % 2 == 0.
    public static void rellenarPatronTablero(int[][] matriz) {
    }

    // TODO 7: Implementar buscarPosicion(int[][] matriz, int valor)
    // - Retorna un int[] {fila, col} de la primera ocurrencia o {-1, -1} si no existe.
    public static int[] buscarPosicion(int[][] matriz, int valor) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println("=== BIENVENIDO AL NIVEL 05: MATRICES 2D ===");
        // Playground para pruebas rápidas
    }
}
