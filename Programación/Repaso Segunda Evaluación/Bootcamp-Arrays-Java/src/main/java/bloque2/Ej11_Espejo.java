package bloque2;

/**
 * EJERCICIO 11 — Espejo Horizontal y Vertical
 * Teoria: teoria/02_Rotacion_Transformacion.md (seccion 5)
 *
 * Contexto: Los espejos son transformaciones mas simples que las rotaciones.
 * Horizontal invierte las filas, vertical invierte las columnas.
 * Combinados, producen una rotacion 180°.
 */
public class Ej11_Espejo {

    /**
     * Aplica espejo horizontal: invierte el orden de las filas.
     * La primera fila pasa a ser la ultima y viceversa.
     * Las dimensiones no cambian.
     * Formula: resultado[filas-1-i][j] = original[i][j]
     *
     * @param matriz array bidimensional
     * @return nueva matriz con espejo horizontal
     */
    public static int[][] espejoHorizontal(int[][] matriz) {
        // TODO 1: Crea un nuevo array con las mismas dimensiones.
        //         Recorre con doble bucle: resultado[filas - 1 - i][j] = matriz[i][j].
        return null;
    }

    /**
     * Aplica espejo vertical: invierte el orden de las columnas.
     * La primera columna pasa a ser la ultima y viceversa.
     * Formula: resultado[i][columnas-1-j] = original[i][j]
     *
     * @param matriz array bidimensional
     * @return nueva matriz con espejo vertical
     */
    public static int[][] espejoVertical(int[][] matriz) {
        // TODO 2: Crea un nuevo array con las mismas dimensiones.
        //         Recorre con doble bucle: resultado[i][columnas - 1 - j] = matriz[i][j].
        return null;
    }

    /**
     * Devuelve un String con la representacion visual de la matriz.
     */
    public static String pintar(int[][] matriz) {
        // TODO 3: StringBuilder, doble bucle, espacio entre valores, salto entre filas.
        return "";
    }

    /**
     * Comprueba que aplicar espejo horizontal dos veces devuelve la original.
     *
     * @param matriz array bidimensional
     * @return true si espejoH(espejoH(matriz)) == original
     */
    public static boolean dobleEspejoHEsOriginal(int[][] matriz) {
        // TODO 4: Aplica espejoHorizontal dos veces. Compara con la original.
        return false;
    }

    /**
     * Comprueba que aplicar espejo vertical dos veces devuelve la original.
     *
     * @param matriz array bidimensional
     * @return true si espejoV(espejoV(matriz)) == original
     */
    public static boolean dobleEspejoVEsOriginal(int[][] matriz) {
        // TODO 5: Aplica espejoVertical dos veces. Compara con la original.
        return false;
    }

    /**
     * Comprueba que espejo horizontal + espejo vertical equivale a rotacion 180°.
     *
     * @param matriz array bidimensional
     * @return true si espejoH(espejoV(matriz)) == rotar180(matriz)
     */
    public static boolean espejoHVEquivaleA180(int[][] matriz) {
        // TODO 6: Aplica espejoVertical, luego espejoHorizontal al resultado.
        //         Compara con Ej10_Rotar180.rotar180(matriz) celda a celda.
        return false;
    }

    /**
     * Devuelve true si la matriz tiene simetria horizontal:
     * es decir, si es igual a su espejo horizontal.
     * Ejemplo: {{1,2},{3,4},{1,2}} tiene simetria horizontal.
     *
     * @param matriz array bidimensional
     * @return true si la matriz tiene simetria horizontal
     */
    public static boolean tieneSimetriaHorizontal(int[][] matriz) {
        // TODO 7: Compara la fila i con la fila filas-1-i. Solo necesitas comprobar
        //         la primera mitad de las filas. Si todas coinciden, es simetrica.
        return false;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION MASTER
    // ══════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 11: Espejos ===\n");

        int[][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Original:");
        System.out.println(pintar(m));

        int[][] eh = espejoHorizontal(m);
        System.out.println("\nEspejo horizontal:");
        System.out.println(eh != null ? pintar(eh) : "null");

        int[][] ev = espejoVertical(m);
        System.out.println("\nEspejo vertical:");
        System.out.println(ev != null ? pintar(ev) : "null");

        System.out.println("\nDoble espejoH = original: " + dobleEspejoHEsOriginal(m));
        System.out.println("Doble espejoV = original: " + dobleEspejoVEsOriginal(m));
        System.out.println("EspejoH+V = 180°: " + espejoHVEquivaleA180(m));

        int[][] sim = {{1, 2, 3}, {4, 5, 6}, {1, 2, 3}};
        System.out.println("Simetria horizontal: " + tieneSimetriaHorizontal(sim));
    }
}
