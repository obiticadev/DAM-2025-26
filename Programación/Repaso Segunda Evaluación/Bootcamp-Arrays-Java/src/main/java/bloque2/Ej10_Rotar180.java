package bloque2;

/**
 * EJERCICIO 10 — Rotacion 180 grados
 * Teoria: teoria/02_Rotacion_Transformacion.md (seccion 4)
 *
 * Contexto: Rotar 180° es como leer la matriz al reves. Equivale a dos rotaciones
 * de 90° o a aplicar espejo horizontal + espejo vertical.
 */
public class Ej10_Rotar180 {

    /**
     * Rota la matriz 180 grados.
     * Las dimensiones se mantienen (MxN -> MxN).
     * Formula: resultado[filas-1-i][columnas-1-j] = original[i][j]
     *
     * @param matriz array bidimensional
     * @return nueva matriz rotada 180°
     */
    public static int[][] rotar180(int[][] matriz) {
        // TODO 1: Crea un nuevo array con las MISMAS dimensiones que la original.
        //         Recorre con doble bucle y aplica:
        //         resultado[filas - 1 - i][columnas - 1 - j] = matriz[i][j].
        return null;
    }

    /**
     * Devuelve un String con la representacion visual de la matriz.
     */
    public static String pintar(int[][] matriz) {
        // TODO 2: StringBuilder, doble bucle, espacio entre valores, salto entre filas.
        return "";
    }

    /**
     * Comprueba que rotar 180° equivale a rotar 90° horario dos veces.
     *
     * @param matriz array bidimensional
     * @return true si rotar180 == rotar90 x 2
     */
    public static boolean equivaleADosRotaciones90(int[][] matriz) {
        // TODO 3: Calcula rotar180(matriz). Calcula Ej08_Rotar90.rotar90Horario dos veces.
        //         Compara celda a celda ambos resultados.
        return false;
    }

    /**
     * Comprueba que rotar 180° dos veces devuelve la original.
     *
     * @param matriz array bidimensional
     * @return true si rotar180(rotar180(matriz)) == original
     */
    public static boolean dobleRotacion180EsOriginal(int[][] matriz) {
        // TODO 4: Aplica rotar180 dos veces. Compara con la original.
        return false;
    }

    /**
     * Comprueba que rotar 180° equivale a espejo horizontal + espejo vertical.
     * Aplica ambas operaciones y compara con rotar180.
     *
     * @param matriz array bidimensional
     * @return true si espejoH(espejoV(matriz)) == rotar180(matriz)
     */
    public static boolean equivaleADobleEspejo(int[][] matriz) {
        // TODO 5: Aplica espejo vertical (invertir columnas) a la original.
        //         Luego aplica espejo horizontal (invertir filas) al resultado.
        //         Compara con rotar180(matriz).
        //         (Implementa los espejos aqui mismo o usa metodos auxiliares privados.)
        return false;
    }

    /**
     * Dada una posicion en la original, devuelve donde acaba tras rotar 180°.
     *
     * @param filas    numero de filas
     * @param columnas numero de columnas
     * @param fila     fila en la original (0-indexed)
     * @param columna  columna en la original (0-indexed)
     * @return array {nuevaFila, nuevaColumna}
     */
    public static int[] posicionTraducida(int filas, int columnas, int fila, int columna) {
        // TODO 6: Formula directa: nuevaFila = filas - 1 - fila, nuevaColumna = columnas - 1 - columna.
        return null;
    }

    /**
     * Devuelve true si la matriz es un "palindromo bidimensional":
     * es decir, si al rotarla 180° obtienes la misma matriz.
     *
     * @param matriz array bidimensional
     * @return true si la matriz es igual a su rotacion 180°
     */
    public static boolean esPalindromo2D(int[][] matriz) {
        // TODO 7: Rota 180°. Compara con la original celda a celda.
        //         Optimizacion: puedes comparar solo la primera mitad de la matriz
        //         con la segunda mitad invertida, sin crear la rotacion completa.
        return false;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION MASTER
    // ══════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 10: Rotacion 180° ===\n");

        int[][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Original:");
        System.out.println(pintar(m));

        int[][] r = rotar180(m);
        System.out.println("\nRotada 180°:");
        System.out.println(r != null ? pintar(r) : "null");

        System.out.println("\nEquivale a 2x 90° horario: " + equivaleADosRotaciones90(m));
        System.out.println("Doble 180° = original: " + dobleRotacion180EsOriginal(m));
        System.out.println("Equivale a doble espejo: " + equivaleADobleEspejo(m));

        int[][] palindromo = {{1, 2, 1}, {3, 5, 3}, {1, 2, 1}};
        System.out.println("Palindromo 2D: " + esPalindromo2D(palindromo));
    }
}
