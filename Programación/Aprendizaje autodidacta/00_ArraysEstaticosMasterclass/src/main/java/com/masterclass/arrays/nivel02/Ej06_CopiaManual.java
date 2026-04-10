package com.masterclass.arrays.nivel02;

/**
 * EJERCICIO 06 — Copia Manual de Arrays
 * ======================================
 * Nivel de Referencia Teórica: teoria/02_Redimensionado_Y_Copia.md
 *
 * Aprenderás a copiar arrays elemento a elemento, entendiendo la
 * diferencia entre copiar una referencia (alias) y copiar los datos
 * reales. Verificarás que las copias son independientes del original.
 */
public class Ej06_CopiaManual {

    // TODO 1: Implementar copiarCompleto(int[] original)
    //   Especificación técnica:
    //   - Crear un NUEVO int[] con la misma longitud que 'original'.
    //   - Copiar cada elemento del original al nuevo array usando un bucle for.
    //   - Devolver el nuevo array.
    //   - Si original es null, lanzar IllegalArgumentException.
    //   - Si original está vacío (length == 0), devolver un array vacío nuevo.
    public static int[] copiarCompleto(int[] original) {
        return null;
    }

    // TODO 2: Implementar copiarParcial(int[] original, int desde, int hasta)
    //   Especificación técnica:
    //   - Copiar SOLO los elementos del rango [desde, hasta) — 'desde' inclusivo, 'hasta' exclusivo.
    //   - El nuevo array tendrá tamaño (hasta - desde).
    //   - Si desde < 0 o hasta > original.length o desde > hasta, lanzar IndexOutOfBoundsException.
    //   - Si original es null, lanzar IllegalArgumentException.
    //   - Ejemplo: original={10,20,30,40,50}, desde=1, hasta=4 → {20, 30, 40}.
    public static int[] copiarParcial(int[] original, int desde, int hasta) {
        return null;
    }

    // TODO 3: Implementar sonIndependientes(int[] original, int[] copia)
    //   Especificación técnica:
    //   - Verificar que 'original' y 'copia' tienen los mismos valores pero son objetos DISTINTOS.
    //   - Comprobar que NO son la misma referencia (original != copia en identidad de objeto).
    //   - Comprobar que tienen la misma longitud.
    //   - Comprobar que cada posición contiene el mismo valor.
    //   - Devolver true si son copias independientes con mismo contenido; false en caso contrario.
    //   - Si alguno es null, devolver false.
    public static boolean sonIndependientes(int[] original, int[] copia) {
        return false;
    }

    // TODO 4: Implementar copiarConDesplazamiento(int[] original, int offset)
    //   Especificación técnica:
    //   - Crear un nuevo array de tamaño original.length + offset.
    //   - Copiar los elementos de 'original' a partir de la posición 'offset' en el nuevo array.
    //   - Las primeras 'offset' posiciones del nuevo array quedan en 0.
    //   - Si original es null, lanzar IllegalArgumentException.
    //   - Si offset < 0, lanzar IllegalArgumentException.
    //   - Ejemplo: original={10,20,30}, offset=2 → {0, 0, 10, 20, 30}.
    public static int[] copiarConDesplazamiento(int[] original, int offset) {
        return null;
    }

    // TODO 5: Implementar concatenar(int[] a, int[] b)
    //   Especificación técnica:
    //   - Crear un nuevo array de tamaño a.length + b.length.
    //   - Copiar todos los elementos de 'a' al inicio y los de 'b' a continuación.
    //   - Devolver el nuevo array concatenado.
    //   - Si alguno es null, lanzar IllegalArgumentException.
    //   - Si alguno está vacío, devolver una copia del otro.
    public static int[] concatenar(int[] a, int[] b) {
        return null;
    }

    // TODO 6: Implementar clonarBidimensional(int[][] original)
    //   Especificación técnica:
    //   - Crear un NUEVO int[][] copiando cada fila como un array independiente.
    //   - Cada fila del clon debe ser un array nuevo (no la misma referencia que en el original).
    //   - Si original es null, lanzar IllegalArgumentException.
    //   - Soportar jagged arrays (filas de distintas longitudes).
    public static int[][] clonarBidimensional(int[][] original) {
        return null;
    }

    // TODO 7: Implementar verificarIndependencia2D(int[][] original, int[][] copia)
    //   Especificación técnica:
    //   - Verificar que el clon es independiente: ninguna fila del clon es la misma referencia
    //     que la fila correspondiente del original (original[i] != copia[i]).
    //   - Verificar que todos los valores coinciden.
    //   - Devolver true si todas las filas son independientes con mismo contenido.
    //   - Si alguno es null, devolver false.
    public static boolean verificarIndependencia2D(int[][] original, int[][] copia) {
        return false;
    }

    // ================================================================
    // ZONA DE EJECUCIÓN MASTER (Playground)
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 06: Copia Manual ===");
        System.out.println();

        // Descomenta y prueba:
        // int[] original = {10, 20, 30, 40, 50};
        // int[] copia = copiarCompleto(original);
        // original[0] = 999;
        // System.out.println("Original[0] = " + original[0]);
        // System.out.println("Copia[0] = " + copia[0]);
        // System.out.println("¿Independientes? " + sonIndependientes(original, copia));

        System.out.println(">> Implementa los TODOs y descomenta las pruebas <<");
    }
}
