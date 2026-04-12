package bloque1;

/**
 * EJERCICIO 04 — Modificacion con Validacion de Rango
 * Teoria: teoria/01_Arrays_Bidi_Fundamentos.md (seccion 6)
 *
 * Contexto: Antes de modificar cualquier posicion de un array, SIEMPRE debes comprobar
 * que los indices estan dentro del rango valido. Si no, devuelves false o ignoras la operacion.
 */
public class Ej04_Modificacion {

    /**
     * Establece un valor en la posicion [fila][columna] de la matriz.
     * Devuelve true si la operacion fue exitosa, false si la posicion esta fuera de rango.
     *
     * @param matriz  array bidimensional
     * @param fila    indice de fila (0-indexed)
     * @param columna indice de columna (0-indexed)
     * @param valor   valor a establecer
     * @return true si se modifico correctamente
     */
    public static boolean establecerValor(int[][] matriz, int fila, int columna, int valor) {
        // TODO 1: Comprueba si fila y columna estan dentro del rango de la matriz.
        //         Si estan fuera, devuelve false sin modificar nada.
        //         Si estan dentro, asigna el valor y devuelve true.
        return false;
    }

    /**
     * Obtiene el valor en la posicion [fila][columna].
     * Si la posicion esta fuera de rango, devuelve el valorPorDefecto recibido.
     *
     * @param matriz          array bidimensional
     * @param fila            indice de fila (0-indexed)
     * @param columna         indice de columna (0-indexed)
     * @param valorPorDefecto valor a devolver si esta fuera de rango
     * @return el valor en la posicion o el valor por defecto
     */
    public static int obtenerValor(int[][] matriz, int fila, int columna, int valorPorDefecto) {
        // TODO 2: Misma validacion de rango. Si esta fuera, devuelve valorPorDefecto.
        //         Si esta dentro, devuelve matriz[fila][columna].
        return 0;
    }

    /**
     * Rellena toda una fila con el valor dado.
     * Si la fila esta fuera de rango, no hace nada y devuelve false.
     *
     * @param matriz array bidimensional
     * @param fila   indice de fila (0-indexed)
     * @param valor  valor a establecer en toda la fila
     * @return true si se relleno correctamente
     */
    public static boolean rellenarFila(int[][] matriz, int fila, int valor) {
        // TODO 3: Valida la fila. Si es valida, recorre todas las columnas de esa fila
        //         asignando el valor. Devuelve true. Si no es valida, devuelve false.
        return false;
    }

    /**
     * Rellena toda una columna con el valor dado.
     * Si la columna esta fuera de rango, no hace nada y devuelve false.
     *
     * @param matriz  array bidimensional
     * @param columna indice de columna (0-indexed)
     * @param valor   valor a establecer en toda la columna
     * @return true si se relleno correctamente
     */
    public static boolean rellenarColumna(int[][] matriz, int columna, int valor) {
        // TODO 4: Valida la columna. Si es valida, recorre todas las filas asignando
        //         el valor en matriz[i][columna]. Devuelve true o false.
        return false;
    }

    /**
     * Intercambia los valores de dos posiciones en la matriz.
     * Si alguna de las posiciones esta fuera de rango, no hace nada y devuelve false.
     *
     * @param matriz array bidimensional
     * @param f1     fila de la primera posicion
     * @param c1     columna de la primera posicion
     * @param f2     fila de la segunda posicion
     * @param c2     columna de la segunda posicion
     * @return true si el intercambio fue exitoso
     */
    public static boolean intercambiar(int[][] matriz, int f1, int c1, int f2, int c2) {
        // TODO 5: Valida que AMBAS posiciones estan en rango.
        //         Usa una variable temporal para intercambiar: temp = m[f1][c1],
        //         m[f1][c1] = m[f2][c2], m[f2][c2] = temp.
        return false;
    }

    /**
     * Incrementa en 1 el valor de todas las celdas que esten dentro de la subregion
     * definida por [filaInicio..filaFin] y [colInicio..colFin] (ambos inclusive).
     * Los indices que caigan fuera de la matriz simplemente se ignoran (se recorta la region).
     *
     * @param matriz     array bidimensional
     * @param filaInicio fila inicial de la region
     * @param filaFin    fila final de la region (inclusive)
     * @param colInicio  columna inicial de la region
     * @param colFin     columna final de la region (inclusive)
     */
    public static void incrementarRegion(int[][] matriz, int filaInicio, int filaFin, int colInicio, int colFin) {
        // TODO 6: Ajusta los limites para que no se salgan de la matriz:
        //         filaInicio = Math.max(0, filaInicio), filaFin = Math.min(filas-1, filaFin), etc.
        //         Luego recorre la subregion incrementando cada celda en 1.
    }

    /**
     * Pone a 0 todas las celdas de la matriz (la "limpia").
     * Devuelve el numero de celdas que NO eran 0 antes de limpiar.
     *
     * @param matriz array bidimensional
     * @return numero de celdas que fueron modificadas (eran != 0)
     */
    public static int limpiarMatriz(int[][] matriz) {
        // TODO 7: Recorre toda la matriz. Si matriz[i][j] != 0, incrementa un contador
        //         y pon la celda a 0. Devuelve el contador.
        return 0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION MASTER
    // ══════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 04: Modificacion ===\n");

        int[][] m = new int[3][4];

        System.out.println("Establecer [1][2] = 5: " + establecerValor(m, 1, 2, 5));
        System.out.println("Establecer [9][0] = 3: " + establecerValor(m, 9, 0, 3));
        System.out.println("Obtener [1][2]: " + obtenerValor(m, 1, 2, -1));
        System.out.println("Obtener [9][0]: " + obtenerValor(m, 9, 0, -1));

        rellenarFila(m, 0, 7);
        rellenarColumna(m, 3, 9);
        System.out.println("\nTras rellenar fila 0 con 7 y columna 3 con 9:");
        System.out.println(Ej01_CrearYPintar.pintarMatriz(m));

        intercambiar(m, 0, 0, 2, 3);
        System.out.println("\nTras intercambiar [0][0] con [2][3]:");
        System.out.println(Ej01_CrearYPintar.pintarMatriz(m));

        incrementarRegion(m, 1, 2, 1, 2);
        System.out.println("\nTras incrementar region [1..2][1..2]:");
        System.out.println(Ej01_CrearYPintar.pintarMatriz(m));

        int modificadas = limpiarMatriz(m);
        System.out.println("\nCeldas limpiadas: " + modificadas);
    }
}
