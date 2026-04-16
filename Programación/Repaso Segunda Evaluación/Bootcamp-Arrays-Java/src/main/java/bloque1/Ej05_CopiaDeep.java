package bloque1;

/**
 * EJERCICIO 05 — Copia Profunda de Arrays Bidimensionales
 * Teoria: teoria/01_Arrays_Bidi_Fundamentos.md (seccion 7)
 *
 * Contexto: Cuando necesitas guardar una "foto" del estado de la sala antes de hacer
 * cambios, debes hacer una copia profunda. Si copias la referencia, ambas variables
 * apuntan a los mismos datos y los cambios afectan a ambas.
 */
public class Ej05_CopiaDeep {

    /**
     * Realiza una copia profunda (deep copy) de un array bidimensional de enteros.
     * Modificar la copia NO debe afectar al original.
     *
     * @param original array bidimensional a copiar
     * @return nueva instancia con los mismos valores
     */
    public static int[][] copiarMatriz(int[][] original) {
        // TODO 1: Crea un nuevo array con las mismas dimensiones que el original.
        //         Usa un doble bucle para copiar cada valor individualmente.
        //         No uses original.clone() ni System.arraycopy (hazlo manual).
        return null;
    }

    /**
     * Compara dos matrices y devuelve true si tienen las mismas dimensiones
     * y los mismos valores en todas las posiciones.
     *
     * @param a primera matriz
     * @param b segunda matriz
     * @return true si son iguales en contenido
     */
    public static boolean sonIguales(int[][] a, int[][] b) {
        // TODO 2: Comprueba primero que ambas tienen el mismo numero de filas.
        //         Luego, para cada fila, comprueba que tienen el mismo numero de columnas.
        //         Finalmente, compara celda a celda. Si encuentras una diferencia, devuelve false.
        return false;
    }

    /**
     * Comprueba que la copia es independiente del original.
     * Modifica la posicion [0][0] de la copia y verifica que el original NO cambio.
     *
     * @param original array bidimensional original
     * @param copia    supuesta copia profunda
     * @return true si la copia es independiente (modificar la copia no afecta al original)
     */
    public static boolean esIndependiente(int[][] original, int[][] copia) {
        // TODO 3: Guarda el valor original[0][0]. Modifica copia[0][0] a un valor diferente.
        //         Comprueba si original[0][0] cambio. Restaura copia[0][0] al valor guardado.
        //         Devuelve true si el original NO fue afectado.
        return false;
    }

    /**
     * Copia la matriz original pero anadiendo un borde de ceros alrededor.
     * Ejemplo: {{1,2},{3,4}} -> {{0,0,0,0},{0,1,2,0},{0,3,4,0},{0,0,0,0}}
     *
     * @param original array bidimensional
     * @return nueva matriz con una fila/columna extra en cada lado (rellena de 0)
     */
    public static int[][] copiarConBorde(int[][] original) {
        // TODO 4: La nueva matriz tiene original.length+2 filas y original[0].length+2 columnas.
        //         Los bordes ya son 0 por defecto. Copia los valores del original
        //         desplazados una posicion (resultado[i+1][j+1] = original[i][j]).
        return null;
    }

    /**
     * Extrae una submatriz de la matriz original, definida por las posiciones de inicio y fin.
     * Los indices son inclusivos. Si alguno se sale del rango, se recorta al limite valido.
     *
     * @param original  array bidimensional
     * @param filaIni   fila inicial (0-indexed, inclusive)
     * @param filaFin   fila final (0-indexed, inclusive)
     * @param colIni    columna inicial (0-indexed, inclusive)
     * @param colFin    columna final (0-indexed, inclusive)
     * @return submatriz extraida
     */
    public static int[][] extraerSubmatriz(int[][] original, int filaIni, int filaFin, int colIni, int colFin) {
        // TODO 5: Ajusta los limites con Math.max/Math.min para no salirte del rango.
        //         Calcula las dimensiones de la submatriz.
        //         Crea el nuevo array y copia los valores correspondientes.
        return null;
    }

    /**
     * Une dos matrices horizontalmente (lado a lado). Ambas deben tener el mismo numero de filas.
     * Si no tienen el mismo numero de filas, devuelve null.
     *
     * @param izquierda primera matriz
     * @param derecha   segunda matriz
     * @return matriz resultante de unir ambas o null si las filas no coinciden
     */
    public static int[][] unirHorizontal(int[][] izquierda, int[][] derecha) {
        // TODO 6: Comprueba que izquierda.length == derecha.length. Si no, devuelve null.
        //         La matriz resultado tiene las mismas filas y la suma de las columnas.
        //         Copia primero los valores de la izquierda, luego los de la derecha desplazados.
        return null;
    }

    /**
     * Une dos matrices verticalmente (una encima de la otra). Ambas deben tener el mismo
     * numero de columnas. Si no, devuelve null.
     *
     * @param arriba primera matriz (va arriba)
     * @param abajo  segunda matriz (va abajo)
     * @return matriz resultante de unir ambas o null si las columnas no coinciden
     */
    public static int[][] unirVertical(int[][] arriba, int[][] abajo) {
        // TODO 7: Comprueba que arriba[0].length == abajo[0].length. Si no, devuelve null.
        //         La matriz resultado tiene la suma de filas y las mismas columnas.
        //         Copia primero los valores de arriba, luego los de abajo desplazados.
        return null;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION MASTER
    // ══════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 05: Copia Profunda ===\n");

        int[][] original = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] copia = copiarMatriz(original);

        System.out.println("Original:");
        System.out.println(Ej01_CrearYPintar.pintarMatriz(original));
        System.out.println("Copia:");
        System.out.println(copia != null ? Ej01_CrearYPintar.pintarMatriz(copia) : "null");
        System.out.println("Son iguales: " + sonIguales(original, copia));
        System.out.println("Es independiente: " + (copia != null ? esIndependiente(original, copia) : "null"));

        System.out.println("\nCon borde:");
        int[][] conBorde = copiarConBorde(original);
        System.out.println(conBorde != null ? Ej01_CrearYPintar.pintarMatriz(conBorde) : "null");

        System.out.println("\nSubmatriz [0..1][1..2]:");
        int[][] sub = extraerSubmatriz(original, 0, 1, 1, 2);
        System.out.println(sub != null ? Ej01_CrearYPintar.pintarMatriz(sub) : "null");
    }
}
