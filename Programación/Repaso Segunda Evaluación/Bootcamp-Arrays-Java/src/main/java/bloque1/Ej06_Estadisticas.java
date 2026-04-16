package bloque1;

/**
 * EJERCICIO 06 — Estadisticas por Filas y Columnas
 * Teoria: teoria/01_Arrays_Bidi_Fundamentos.md (seccion 8)
 *
 * Contexto: El gerente del cine quiere informes: recaudacion por fila, la fila
 * con mas/menos ocupacion, totales por columna, etc. Todo se reduce a operar
 * sobre filas y columnas de un array bidimensional.
 */
public class Ej06_Estadisticas {

    /**
     * Calcula la suma de todos los valores de una fila concreta.
     * Si la fila esta fuera de rango, devuelve 0.
     *
     * @param matriz array bidimensional
     * @param fila   indice de fila (0-indexed)
     * @return suma de la fila
     */
    public static int sumaFila(int[][] matriz, int fila) {
        // TODO 1: Valida la fila. Recorre solo esa fila sumando todos los valores.
        return 0;
    }

    /**
     * Calcula la suma de todos los valores de una columna concreta.
     * Si la columna esta fuera de rango, devuelve 0.
     *
     * @param matriz  array bidimensional
     * @param columna indice de columna (0-indexed)
     * @return suma de la columna
     */
    public static int sumaColumna(int[][] matriz, int columna) {
        // TODO 2: Valida la columna. Recorre todas las filas accediendo a matriz[i][columna].
        return 0;
    }

    /**
     * Devuelve un array con la suma de cada fila.
     * El elemento i del resultado es la suma de la fila i.
     *
     * @param matriz array bidimensional
     * @return array de sumas por fila
     */
    public static int[] sumasPorFila(int[][] matriz) {
        // TODO 3: Crea un array de tamano igual al numero de filas.
        //         Para cada fila, calcula su suma (puedes reusar sumaFila o hacerlo directo).
        return null;
    }

    /**
     * Devuelve un array con la suma de cada columna.
     * El elemento j del resultado es la suma de la columna j.
     *
     * @param matriz array bidimensional
     * @return array de sumas por columna
     */
    public static int[] sumasPorColumna(int[][] matriz) {
        // TODO 4: Crea un array de tamano igual al numero de columnas.
        //         Para cada columna, calcula su suma.
        return null;
    }

    /**
     * Devuelve el valor maximo de toda la matriz.
     *
     * @param matriz array bidimensional (se asume no vacia)
     * @return valor maximo
     */
    public static int maximoGlobal(int[][] matriz) {
        // TODO 5: Inicializa el maximo con matriz[0][0].
        //         Recorre toda la matriz, actualizando el maximo si encuentras uno mayor.
        return 0;
    }

    /**
     * Devuelve el indice de la fila cuya suma es la mayor.
     * Si hay empate, devuelve la primera fila con esa suma.
     *
     * @param matriz array bidimensional
     * @return indice de la fila con mayor suma (0-indexed)
     */
    public static int filaMayorSuma(int[][] matriz) {
        // TODO 6: Calcula las sumas de todas las filas (puedes reusar sumasPorFila).
        //         Busca el indice del maximo en ese array de sumas.
        return 0;
    }

    /**
     * Calcula la media (promedio) de todos los valores de la matriz.
     * Devuelve el resultado como double.
     *
     * @param matriz array bidimensional (se asume no vacia)
     * @return media de todos los valores
     */
    public static double mediaGlobal(int[][] matriz) {
        // TODO 7: Suma todos los valores de la matriz.
        //         Divide entre el numero total de celdas (filas * columnas).
        //         Cuidado: la division debe ser decimal, castea a double antes de dividir.
        return 0.0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION MASTER
    // ══════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 06: Estadisticas ===\n");

        int[][] m = {
            {10, 20, 30},
            {5, 15, 25},
            {40, 10, 50}
        };

        System.out.println("Matriz:");
        System.out.println(Ej01_CrearYPintar.pintarMatriz(m));

        System.out.println("Suma fila 0: " + sumaFila(m, 0));
        System.out.println("Suma columna 2: " + sumaColumna(m, 2));

        System.out.print("Sumas por fila: ");
        int[] sf = sumasPorFila(m);
        if (sf != null) for (int v : sf) System.out.print(v + " ");
        System.out.println();

        System.out.print("Sumas por columna: ");
        int[] sc = sumasPorColumna(m);
        if (sc != null) for (int v : sc) System.out.print(v + " ");
        System.out.println();

        System.out.println("Maximo global: " + maximoGlobal(m));
        System.out.println("Fila con mayor suma: " + filaMayorSuma(m));
        System.out.println("Media global: " + mediaGlobal(m));
    }
}
