package bloque1;

/**
 * EJERCICIO 02 — Recorridos en Distintas Direcciones
 * Teoria: teoria/01_Arrays_Bidi_Fundamentos.md (seccion 4)
 *
 * Contexto: Necesitas leer los datos de una sala de cine de diferentes maneras:
 * por filas, por columnas, en diagonal... Cada recorrido tiene su utilidad.
 */
public class Ej02_Recorridos {

    /**
     * Devuelve un array unidimensional con todos los elementos de la matriz
     * leidos por filas (de izquierda a derecha, de arriba a abajo).
     * Ejemplo {{1,2},{3,4}} -> {1,2,3,4}
     *
     * @param matriz array bidimensional
     * @return array unidimensional con los elementos en orden por filas
     */
    public static int[] recorridoPorFilas(int[][] matriz) {
        // TODO 1: Calcula el tamano total, crea un array unidimensional,
        //         y rellena recorriendo fila por fila (for i, for j).
        return null;
    }

    /**
     * Devuelve un array unidimensional con todos los elementos de la matriz
     * leidos por columnas (de arriba a abajo, de izquierda a derecha).
     * Ejemplo {{1,2},{3,4}} -> {1,3,2,4}
     *
     * @param matriz array bidimensional
     * @return array unidimensional con los elementos en orden por columnas
     */
    public static int[] recorridoPorColumnas(int[][] matriz) {
        // TODO 2: Igual que el anterior pero invirtiendo el orden de los bucles:
        //         el bucle exterior recorre columnas (j) y el interior filas (i).
        return null;
    }

    /**
     * Devuelve un array con los elementos de la diagonal principal.
     * Solo funciona con matrices cuadradas (filas == columnas).
     * Ejemplo {{1,2,3},{4,5,6},{7,8,9}} -> {1,5,9}
     *
     * @param matriz array bidimensional cuadrado
     * @return array con la diagonal principal
     */
    public static int[] diagonalPrincipal(int[][] matriz) {
        // TODO 3: La diagonal principal son las posiciones donde i == j.
        //         Un solo bucle: matriz[i][i].
        return null;
    }

    /**
     * Devuelve un array con los elementos de la diagonal inversa (anti-diagonal).
     * Solo funciona con matrices cuadradas.
     * Ejemplo {{1,2,3},{4,5,6},{7,8,9}} -> {3,5,7}
     *
     * @param matriz array bidimensional cuadrado
     * @return array con la diagonal inversa
     */
    public static int[] diagonalInversa(int[][] matriz) {
        // TODO 4: La diagonal inversa son las posiciones [i][n-1-i]
        //         donde n es el numero de filas/columnas.
        return null;
    }

    /**
     * Devuelve un array unidimensional con los elementos del borde de la matriz
     * recorridos en sentido horario (fila superior -> columna derecha -> fila inferior -> columna izquierda).
     * Ejemplo para {{1,2,3},{4,5,6},{7,8,9}} -> {1,2,3,6,9,8,7,4}
     *
     * @param matriz array bidimensional
     * @return array con los elementos del borde en sentido horario
     */
    public static int[] recorridoBorde(int[][] matriz) {
        // TODO 5: Recorre en 4 tramos: fila 0 de izq a der, ultima columna de arriba a abajo
        //         (sin repetir esquina), ultima fila de der a izq (sin repetir esquina),
        //         primera columna de abajo a arriba (sin repetir esquinas).
        //         Cuidado con matrices de 1 fila o 1 columna.
        return null;
    }

    /**
     * Devuelve un array unidimensional con todos los elementos leidos en zigzag:
     * fila 0 de izquierda a derecha, fila 1 de derecha a izquierda, fila 2 de izq a der...
     * Ejemplo {{1,2,3},{4,5,6},{7,8,9}} -> {1,2,3,6,5,4,7,8,9}
     *
     * @param matriz array bidimensional
     * @return array con los elementos en zigzag
     */
    public static int[] recorridoZigzag(int[][] matriz) {
        // TODO 6: En el bucle de filas, comprueba si i es par o impar.
        //         Si par: j va de 0 a columnas. Si impar: j va de columnas-1 a 0.
        return null;
    }

    /**
     * Devuelve true si la matriz es cuadrada (mismo numero de filas que de columnas).
     *
     * @param matriz array bidimensional
     * @return true si es cuadrada
     */
    public static boolean esCuadrada(int[][] matriz) {
        // TODO 7: Compara matriz.length con matriz[0].length.
        return false;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION MASTER
    // ══════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 02: Recorridos ===\n");

        int[][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        System.out.print("Por filas: ");
        imprimirArray(recorridoPorFilas(m));

        System.out.print("Por columnas: ");
        imprimirArray(recorridoPorColumnas(m));

        System.out.print("Diagonal principal: ");
        imprimirArray(diagonalPrincipal(m));

        System.out.print("Diagonal inversa: ");
        imprimirArray(diagonalInversa(m));

        System.out.print("Borde horario: ");
        imprimirArray(recorridoBorde(m));

        System.out.print("Zigzag: ");
        imprimirArray(recorridoZigzag(m));

        System.out.println("Es cuadrada: " + esCuadrada(m));
    }

    private static void imprimirArray(int[] arr) {
        if (arr == null) { System.out.println("null"); return; }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb);
    }
}
