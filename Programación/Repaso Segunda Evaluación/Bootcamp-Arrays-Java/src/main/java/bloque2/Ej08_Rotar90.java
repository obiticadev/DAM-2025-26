package bloque2;

/**
 * EJERCICIO 08 — Rotacion 90 grados en sentido horario
 * Teoria: teoria/02_Rotacion_Transformacion.md (seccion 2)
 *
 * Contexto: Imagina que necesitas rotar el plano de butacas de una sala.
 * La rotacion 90° horario es la mas comun en ejercicios de examen.
 */
public class Ej08_Rotar90 {

    /**
     * Rota la matriz 90 grados en sentido horario.
     * Si la original es MxN, el resultado es NxM.
     * Formula: resultado[j][filas-1-i] = original[i][j]
     *
     * @param matriz array bidimensional
     * @return nueva matriz rotada 90° horario
     */
    public static int[][] rotar90Horario(int[][] matriz) {
        // TODO 1: Las dimensiones del resultado son [columnas][filas] de la original.
        //         Recorre la original con doble bucle.
        //         Aplica: resultado[j][filas - 1 - i] = matriz[i][j].
        return null;
    }

    /**
     * Devuelve un String con la representacion visual de la matriz.
     *
     * @param matriz array bidimensional
     * @return representacion en String
     */
    public static String pintar(int[][] matriz) {
        // TODO 2: Usa StringBuilder. Doble bucle por filas y columnas.
        //         Separa valores con espacio, filas con salto de linea.
        return "";
    }

    /**
     * Rota la matriz 90° horario N veces. N puede ser cualquier numero >= 0.
     * Optimizacion: rotar 4 veces = no rotar. Usa N % 4.
     *
     * @param matriz array bidimensional
     * @param veces  numero de rotaciones a aplicar
     * @return matriz resultante tras N rotaciones
     */
    public static int[][] rotarNVeces(int[][] matriz, int veces) {
        // TODO 3: Calcula veces % 4 (rotaciones efectivas).
        //         Aplica rotar90Horario en un bucle esas veces.
        //         Si veces efectivas es 0, devuelve una copia de la original.
        return null;
    }

    /**
     * Comprueba que rotar 4 veces devuelve la matriz original.
     *
     * @param matriz array bidimensional
     * @return true si rotar 4 veces da el original
     */
    public static boolean cuatroRotacionesEsOriginal(int[][] matriz) {
        // TODO 4: Rota 4 veces usando rotar90Horario.
        //         Compara el resultado con la original celda a celda.
        return false;
    }

    /**
     * Devuelve la primera fila de la matriz rotada 90° horario,
     * SIN crear la matriz rotada completa.
     * La primera fila del resultado es la primera columna de la original leida de abajo a arriba.
     *
     * @param matriz array bidimensional
     * @return array con la primera fila de la rotacion
     */
    public static int[] primeraFilaRotada(int[][] matriz) {
        // TODO 5: Crea un array de tamano igual al numero de filas de la original.
        //         Recorre la primera columna (j=0) de abajo a arriba:
        //         resultado[k] = matriz[filas - 1 - k][0].
        return null;
    }

    /**
     * Devuelve la ultima fila de la matriz rotada 90° horario,
     * SIN crear la matriz rotada completa.
     * La ultima fila del resultado es la ultima columna de la original leida de abajo a arriba.
     *
     * @param matriz array bidimensional
     * @return array con la ultima fila de la rotacion
     */
    public static int[] ultimaFilaRotada(int[][] matriz) {
        // TODO 6: Similar al anterior pero con la ultima columna (j = columnas - 1).
        //         resultado[k] = matriz[filas - 1 - k][columnas - 1].
        return null;
    }

    /**
     * Dada una posicion [fila][columna] en la matriz original, devuelve la posicion
     * equivalente en la matriz rotada 90° horario como un array {nuevaFila, nuevaColumna}.
     *
     * @param filas   numero de filas de la original
     * @param fila    fila en la original (0-indexed)
     * @param columna columna en la original (0-indexed)
     * @return array {nuevaFila, nuevaColumna} en la rotada
     */
    public static int[] posicionTraducida(int filas, int fila, int columna) {
        // TODO 7: Aplica la formula: nuevaFila = columna, nuevaColumna = filas - 1 - fila.
        //         Devuelve new int[]{nuevaFila, nuevaColumna}.
        return null;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION MASTER
    // ══════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 08: Rotacion 90° Horario ===\n");

        int[][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Original:");
        System.out.println(pintar(m));

        int[][] r = rotar90Horario(m);
        System.out.println("\nRotada 90° horario:");
        System.out.println(r != null ? pintar(r) : "null");

        System.out.println("\n4 rotaciones = original: " + cuatroRotacionesEsOriginal(m));

        int[] pf = primeraFilaRotada(m);
        System.out.print("Primera fila rotada: ");
        if (pf != null) for (int v : pf) System.out.print(v + " ");
        System.out.println();

        int[] pos = posicionTraducida(3, 0, 2);
        System.out.println("Posicion [0][2] en rotada: " + (pos != null ? "[" + pos[0] + "][" + pos[1] + "]" : "null"));
    }
}
