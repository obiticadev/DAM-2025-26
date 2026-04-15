package bloque2;

/**
 * EJERCICIO 07 — Transpuesta de una Matriz
 * Teoria: teoria/02_Rotacion_Transformacion.md (seccion 1)
 *
 * Contexto: La transpuesta intercambia filas por columnas. Es la transformacion
 * base sobre la que se construyen las rotaciones. Dominarla es esencial.
 */
public class Ej07_Transpuesta {

    /**
     * Devuelve la transpuesta de la matriz recibida.
     * Si la original es MxN, la transpuesta es NxM.
     * Formula: transpuesta[j][i] = original[i][j]
     *
     * @param matriz array bidimensional
     * @return nueva matriz transpuesta
     */
    public static int[][] transponer(int[][] matriz) {
        // TODO 1: Calcula las dimensiones de la transpuesta (filas y columnas
        // invertidas).
        // Crea un nuevo array con esas dimensiones.
        // Recorre la original con doble bucle y aplica: resultado[j][i] = matriz[i][j].
        int[][] resultado = new int[matriz[0].length][matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                resultado[j][i] = matriz[i][j];
            }
        }
        return resultado;
    }

    /**
     * Devuelve true si la matriz es simetrica respecto a su diagonal principal.
     * Una matriz es simetrica si es igual a su transpuesta.
     * Solo puede ser simetrica si es cuadrada.
     *
     * @param matriz array bidimensional
     * @return true si es simetrica
     */
    public static boolean esSimetrica(int[][] matriz) {
        // TODO 2: Primero comprueba que es cuadrada (filas == columnas). Si no, false.
        // Recorre solo la mitad superior (j > i). Si matriz[i][j] != matriz[j][i],
        // false.
        // Si llegas al final sin diferencias, true.
        return false;
    }

    /**
     * Aplica la transpuesta dos veces y comprueba que el resultado es igual al
     * original.
     * Devuelve true si transponer(transponer(matriz)) == matriz.
     *
     * @param matriz array bidimensional
     * @return true si la doble transposicion devuelve la original
     */
    public static boolean dobleTransposicionEsOriginal(int[][] matriz) {
        // TODO 3: Llama a transponer dos veces. Compara el resultado con la original
        // celda a celda. Devuelve true si son identicas.
        return false;
    }

    /**
     * Devuelve un String con la representacion visual de la matriz.
     * Valores separados por espacio, filas separadas por salto de linea.
     * Sin espacio al final de la fila ni salto al final.
     *
     * @param matriz array bidimensional
     * @return representacion en String
     */
    public static String pintar(int[][] matriz) {
        // TODO 4: Usa StringBuilder. Doble bucle. Anade valor + espacio entre valores.
        // Salto de linea entre filas. Sin espacio ni salto extra al final.
        return "";
    }

    /**
     * Dada una matriz MxN, devuelve una nueva matriz NxM donde cada celda contiene
     * la suma de la fila original a la que pertenecia ese elemento en la
     * transpuesta.
     * Es decir: resultado[j][i] = suma de la fila i de la original.
     *
     * @param matriz array bidimensional
     * @return matriz transpuesta con sumas de fila
     */
    public static int[][] transponerConSumaFila(int[][] matriz) {
        // TODO 5: Primero calcula la suma de cada fila de la original (array auxiliar).
        // Luego crea la transpuesta, pero en vez de copiar el valor,
        // pon la suma de la fila original: resultado[j][i] = sumaFila[i].
        return null;
    }

    /**
     * Devuelve la diagonal principal de la transpuesta de la matriz.
     * Solo funciona si la original tiene al menos tantas columnas como filas.
     * La diagonal de la transpuesta son los elementos [i][i] de la transpuesta,
     * que equivalen a [i][i] de la original (solo si es cuadrada).
     *
     * @param matriz array bidimensional
     * @return array con la diagonal de la transpuesta, o null si no es posible
     */
    public static int[] diagonalTranspuesta(int[][] matriz) {
        // TODO 6: Transponer la matriz. Luego extraer la diagonal principal
        // (los elementos donde i == j). El tamano de la diagonal es
        // Math.min(transpuesta.length, transpuesta[0].length).
        return null;
    }

    /**
     * Comprueba que la transpuesta conserva la suma total de la matriz.
     * Devuelve true si la suma de todos los elementos de la original es igual
     * a la suma de todos los elementos de la transpuesta.
     *
     * @param matriz array bidimensional
     * @return true si las sumas totales coinciden
     */
    public static boolean sumaConservada(int[][] matriz) {
        // TODO 7: Calcula la suma total de la original (doble bucle).
        // Transponer. Calcula la suma total de la transpuesta.
        // Compara ambas sumas.
        return false;
    }

    // ══════════════════════════════════════════════
    // ZONA DE EJECUCION MASTER
    // ══════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 07: Transpuesta ===\n");

        int[][] m = { { 1, 2, 3 }, { 4, 5, 6 } };
        System.out.println("Original (2x3):");
        System.out.println(pintar(m));

        int[][] t = transponer(m);
        System.out.println("\nTranspuesta (3x2):");
        System.out.println(t != null ? pintar(t) : "null");

        int[][] cuad = { { 1, 2, 3 }, { 2, 5, 6 }, { 3, 6, 9 } };
        System.out.println("\nMatriz simetrica: " + esSimetrica(cuad));
        System.out.println("Doble transposicion = original: " + dobleTransposicionEsOriginal(m));
        System.out.println("Suma conservada: " + sumaConservada(m));

        int[][] matrizPersonalizada = {
                { 1, 2 },
                { 3, 4 },
                { 5, 6 },
                { 7, 8 }
        };
        int[][] traspuesta = transponer(matrizPersonalizada);
        int[] suma = new int[traspuesta.length];
        int contador = 0;
        for (int[] fila : traspuesta) {

            for (int is : fila) {
                System.out.print(String.format("%3d", is).toString());
                suma[contador] += is;
            }
            System.out.println(" = " + suma[contador++]);
        }
    }
}
