package bloque1;

/**
 * EJERCICIO 03 — Busqueda en Array Bidimensional
 * Teoria: teoria/01_Arrays_Bidi_Fundamentos.md (secciones 4, 6)
 *
 * Contexto: En el sistema del cine necesitas localizar butacas concretas,
 * contar cuantas estan ocupadas, y encontrar la primera/ultima disponible.
 */
public class Ej03_Busqueda {

    /**
     * Busca un valor en la matriz y devuelve un array {fila, columna} con la posicion
     * de la PRIMERA ocurrencia (recorriendo por filas). Si no existe, devuelve null.
     *
     * @param matriz array bidimensional
     * @param valor  valor a buscar
     * @return array {fila, columna} o null si no se encuentra
     */
    public static int[] buscarPrimero(int[][] matriz, int valor) {
        // TODO 1: Doble bucle por filas. Cuando encuentres el valor, devuelve
        //         inmediatamente new int[]{i, j}. Si terminas sin encontrarlo, null.
        return null;
    }

    /**
     * Busca un valor en la matriz y devuelve un array {fila, columna} con la posicion
     * de la ULTIMA ocurrencia (recorriendo por filas). Si no existe, devuelve null.
     *
     * @param matriz array bidimensional
     * @param valor  valor a buscar
     * @return array {fila, columna} o null si no se encuentra
     */
    public static int[] buscarUltimo(int[][] matriz, int valor) {
        // TODO 2: Recorre toda la matriz guardando la posicion cada vez que encuentres
        //         el valor. Al final, devuelve la ultima posicion guardada (o null si nunca la guardaste).
        return null;
    }

    /**
     * Cuenta cuantas veces aparece un valor en la matriz.
     *
     * @param matriz array bidimensional
     * @param valor  valor a contar
     * @return numero de ocurrencias
     */
    public static int contarOcurrencias(int[][] matriz, int valor) {
        // TODO 3: Doble bucle, incrementa un contador cada vez que matriz[i][j] == valor.
        return 0;
    }

    /**
     * Devuelve true si el valor existe en la fila indicada.
     *
     * @param matriz array bidimensional
     * @param fila   indice de la fila (0-indexed)
     * @param valor  valor a buscar
     * @return true si el valor esta en esa fila
     */
    public static boolean existeEnFila(int[][] matriz, int fila, int valor) {
        // TODO 4: Valida que la fila esta en rango. Luego recorre solo esa fila
        //         con un unico bucle (j). Devuelve true en cuanto lo encuentres.
        return false;
    }

    /**
     * Devuelve true si el valor existe en la columna indicada.
     *
     * @param matriz  array bidimensional
     * @param columna indice de la columna (0-indexed)
     * @param valor   valor a buscar
     * @return true si el valor esta en esa columna
     */
    public static boolean existeEnColumna(int[][] matriz, int columna, int valor) {
        // TODO 5: Valida que la columna esta en rango. Luego recorre solo esa columna
        //         con un unico bucle (i), accediendo a matriz[i][columna].
        return false;
    }

    /**
     * Devuelve un array bidimensional con las posiciones de TODAS las ocurrencias del valor.
     * Cada fila del resultado es {fila, columna}.
     * Ejemplo: si el valor aparece en [0][1] y [2][3], devuelve {{0,1},{2,3}}.
     * Si no aparece, devuelve un array vacio (new int[0][]).
     *
     * @param matriz array bidimensional
     * @param valor  valor a buscar
     * @return array de posiciones {fila, columna}
     */
    public static int[][] buscarTodas(int[][] matriz, int valor) {
        // TODO 6: Primero cuenta las ocurrencias (puedes reusar contarOcurrencias).
        //         Crea un array de resultado con ese tamano. Recorre de nuevo la matriz
        //         rellenando el resultado con cada posicion encontrada.
        return null;
    }

    /**
     * Devuelve true si la posicion (fila, columna) esta dentro del rango de la matriz.
     *
     * @param matriz  array bidimensional
     * @param fila    indice de fila
     * @param columna indice de columna
     * @return true si esta dentro del rango
     */
    public static boolean enRango(int[][] matriz, int fila, int columna) {
        // TODO 7: Comprueba que fila >= 0, fila < filas, columna >= 0, columna < columnas.
        //         Devuelve el resultado de la condicion compuesta.
        return false;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION MASTER
    // ══════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 03: Busqueda ===\n");

        int[][] m = {
            {1, 2, 3, 2},
            {4, 5, 2, 6},
            {7, 2, 8, 9}
        };

        int[] pos = buscarPrimero(m, 2);
        System.out.println("Primera ocurrencia de 2: " + (pos != null ? "[" + pos[0] + "," + pos[1] + "]" : "null"));

        int[] posU = buscarUltimo(m, 2);
        System.out.println("Ultima ocurrencia de 2: " + (posU != null ? "[" + posU[0] + "," + posU[1] + "]" : "null"));

        System.out.println("Ocurrencias de 2: " + contarOcurrencias(m, 2));
        System.out.println("2 en fila 0: " + existeEnFila(m, 0, 2));
        System.out.println("2 en columna 0: " + existeEnColumna(m, 0, 2));
        System.out.println("En rango [2][3]: " + enRango(m, 2, 3));
        System.out.println("En rango [5][0]: " + enRango(m, 5, 0));
    }
}
