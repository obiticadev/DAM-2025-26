package bloque2;

/**
 * EJERCICIO 09 — Rotacion 90 grados en sentido antihorario
 * Teoria: teoria/02_Rotacion_Transformacion.md (seccion 3)
 *
 * Contexto: La rotacion antihoraria es la operacion inversa de la horaria.
 * Si aplicas una horaria y luego una antihoraria, vuelves al original.
 */
public class Ej09_Rotar90Anti {

    /**
     * Rota la matriz 90 grados en sentido antihorario.
     * Si la original es MxN, el resultado es NxM.
     * Formula: resultado[columnas-1-j][i] = original[i][j]
     *
     * @param matriz array bidimensional
     * @return nueva matriz rotada 90° antihorario
     */
    public static int[][] rotar90Antihorario(int[][] matriz) {
        // TODO 1: Las dimensiones del resultado son [columnas][filas] de la original.
        //         Recorre la original con doble bucle.
        //         Aplica: resultado[columnas - 1 - j][i] = matriz[i][j].
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
     * Comprueba que rotar 90° horario seguido de 90° antihorario devuelve el original.
     *
     * @param matriz array bidimensional
     * @return true si horario + antihorario = original
     */
    public static boolean horarioAntihorarioEsOriginal(int[][] matriz) {
        // TODO 3: Rota la matriz 90° horario (usa Ej08_Rotar90.rotar90Horario).
        //         Luego rota el resultado 90° antihorario.
        //         Compara con el original celda a celda.
        return false;
    }

    /**
     * Comprueba que rotar 90° antihorario es equivalente a rotar 90° horario 3 veces.
     *
     * @param matriz array bidimensional
     * @return true si antihorario == 3x horario
     */
    public static boolean antihorarioEsTresHorario(int[][] matriz) {
        // TODO 4: Rota antihorario una vez. Rota horario 3 veces.
        //         Compara ambos resultados celda a celda.
        return false;
    }

    /**
     * Rota la matriz antihorario N veces. Usa N % 4 para optimizar.
     *
     * @param matriz array bidimensional
     * @param veces  numero de rotaciones antihorario
     * @return matriz resultante
     */
    public static int[][] rotarAntihorarioNVeces(int[][] matriz, int veces) {
        // TODO 5: Calcula veces % 4. Aplica rotar90Antihorario ese numero de veces.
        //         Si 0, devuelve copia de la original.
        return null;
    }

    /**
     * Dada una posicion en la original, devuelve donde acaba tras rotar 90° antihorario.
     *
     * @param columnas numero de columnas de la original
     * @param fila     fila en la original (0-indexed)
     * @param columna  columna en la original (0-indexed)
     * @return array {nuevaFila, nuevaColumna}
     */
    public static int[] posicionTraducida(int columnas, int fila, int columna) {
        // TODO 6: Formula: nuevaFila = columnas - 1 - columna, nuevaColumna = fila.
        //         Devuelve new int[]{nuevaFila, nuevaColumna}.
        return null;
    }

    /**
     * Devuelve la primera columna de la matriz rotada 90° antihorario,
     * SIN crear la matriz rotada completa.
     * La primera columna del resultado antihorario es la primera fila de la original
     * leida de derecha a izquierda.
     *
     * @param matriz array bidimensional
     * @return array con la primera columna de la rotacion antihoraria
     */
    public static int[] primeraColumnaRotada(int[][] matriz) {
        // TODO 7: La primera columna del resultado tiene tantos elementos como columnas
        //         tiene la original. resultado[k] = matriz[0][columnas - 1 - k].
        return null;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION MASTER
    // ══════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 09: Rotacion 90° Antihorario ===\n");

        int[][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Original:");
        System.out.println(pintar(m));

        int[][] r = rotar90Antihorario(m);
        System.out.println("\nRotada 90° antihorario:");
        System.out.println(r != null ? pintar(r) : "null");

        System.out.println("\nHorario + Antihorario = Original: " + horarioAntihorarioEsOriginal(m));
        System.out.println("Antihorario == 3x Horario: " + antihorarioEsTresHorario(m));
    }
}
