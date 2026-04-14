package bloque1;

import java.util.StringJoiner;

/**
 * EJERCICIO 01 — Crear y Pintar un Array Bidimensional
 * Teoria: teoria/01_Arrays_Bidi_Fundamentos.md (secciones 1-5)
 *
 * Contexto: Eres el encargado de generar el plano de butacas de un mini-cine.
 * Debes crear una matriz de enteros y saber representarla visualmente.
 */
public class Ej01_CrearYPintar {

    /**
     * Crea un array bidimensional de enteros con las dimensiones dadas.
     * Todos los valores deben inicializarse a 0.
     *
     * @param filas    numero de filas
     * @param columnas numero de columnas
     * @return array bidimensional de filas x columnas con valores 0
     */
    public static int[][] crearMatriz(int filas, int columnas) {
        // TODO 1: Declara y devuelve un array bidimensional de int con las dimensiones
        // recibidas.
        // Recuerda que Java inicializa los int a 0 por defecto.
        return new int[filas][columnas];
    }

    /**
     * Crea un array bidimensional e inicializa cada celda con un valor consecutivo
     * empezando desde 1. Ejemplo para 2x3: {{1,2,3},{4,5,6}}
     *
     * @param filas    numero de filas
     * @param columnas numero de columnas
     * @return array bidimensional con valores consecutivos desde 1
     */
    public static int[][] crearMatrizConsecutiva(int filas, int columnas) {
        // TODO 2: Crea la matriz y rellena cada posicion con un contador que empiece en
        // 1
        // e incremente en cada celda (recorriendo por filas).
        int[][] matriz = new int[filas][columnas];
        int contador = 0;
        for (int[] matriz1 : matriz) {
            for (int j = 0; j < matriz[0].length; j++) {
                matriz1[j] = ++contador;
            }
        }
        return matriz;
    }

    /**
     * Devuelve el numero total de celdas (filas * columnas) del array recibido.
     *
     * @param matriz array bidimensional
     * @return numero total de elementos
     */
    public static int contarCeldas(int[][] matriz) {
        // TODO 3: Usa .length para obtener filas y columnas. No recorras el array
        // entero,
        // calcula el total con una multiplicacion.
        return matriz.length * matriz[0].length;
    }

    /**
     * Devuelve un String con la representacion visual del array.
     * Cada fila en una linea, valores separados por un espacio, sin espacio al
     * final de linea.
     * Ejemplo para {{1,2},{3,4}}:
     * "1 2\n3 4"
     *
     * @param matriz array bidimensional
     * @return representacion en String
     */
    public static String pintarMatriz(int[][] matriz) {
        // TODO 4: Usa StringBuilder. Recorre por filas y columnas.
        // Anade el valor y un espacio ENTRE valores (no al final de la fila).
        // Anade salto de linea entre filas (no al final de la ultima fila).
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                sb.append(matriz[i][j]);
                if (j != (matriz[i].length - 1)) {
                    sb.append(" ");
                }
            }
            if (i != matriz.length - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Devuelve un String con la representacion visual incluyendo numeros de fila y
     * columna.
     * Primera linea: " 1 2 3" (numeros de columna con formato %3d)
     * Siguientes: " 1 0 0 0" (numero de fila + valores con formato %3d)
     *
     * Ejemplo 2x3:
     * " 1 2 3\n 1 0 0 0\n 2 0 0 0"
     *
     * @param matriz array bidimensional
     * @return representacion con cabeceras
     */
    public static String pintarMatrizConCabecera(int[][] matriz) {
        // TODO 5: Usa StringBuilder y String.format("%3d", valor) para alinear.
        // Primera linea: 3 espacios + numeros de columna.
        // Resto: numero de fila (1-indexed) + valores.
        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        for (int i = 0; i <= matriz[0].length; i++) {
            sb.append(String.format("%3d", (i + 1)));
        }
        sb.append("\n");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                sb.append(String.format("%3d", i + 1)).append(String.format("%3d", matriz[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Devuelve un String representando la matriz pero sustituyendo los 0 por "."
     * y cualquier otro valor por "X".
     * Ejemplo para {{0,1},{2,0}}: ". X\nX ."
     *
     * @param matriz array bidimensional
     * @return representacion con simbolos
     */
    public static String pintarMatrizSimbolos(int[][] matriz) {
        // TODO 6: Recorre la matriz. Si el valor es 0, anade ".". Si no, anade "X".
        // Separa con espacio entre simbolos, salto de linea entre filas.

        StringJoiner resultadoFinal = new StringJoiner("\n");
        for (int i = 0; i < matriz.length; i++) {
            StringJoiner fila = new StringJoiner(" ");
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] == 0) {
                    fila.add(".");
                } else {
                    fila.add("X");
                }
            }
            resultadoFinal.add(fila.toString());
        }
        return resultadoFinal.toString();
    }

    /**
     * Dado un array bidimensional, devuelve true si todas sus celdas valen 0.
     *
     * @param matriz array bidimensional
     * @return true si todo es 0
     */
    public static boolean estaVacia(int[][] matriz) {
        // TODO 7: Recorre toda la matriz. Si encuentras un valor distinto de 0,
        // devuelve false inmediatamente. Si terminas sin encontrar ninguno, devuelve
        // true.
        for (int[] fila : matriz) {
            for (int is : fila) {
                if (is != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // ══════════════════════════════════════════════
    // ZONA DE EJECUCION MASTER — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 01: Crear y Pintar ===\n");

        int[][] m1 = crearMatriz(3, 4);
        System.out.println("Matriz 3x4 vacia:");
        System.out.println(pintarMatriz(m1));
        System.out.println("Esta vacia: " + estaVacia(m1));
        System.out.println("Celdas: " + contarCeldas(m1));
        System.out.println();

        int[][] m2 = crearMatrizConsecutiva(3, 4);
        System.out.println("Matriz 3x4 consecutiva:");
        System.out.println(pintarMatriz(m2));
        System.out.println("Esta vacia: " + estaVacia(m2));
        System.out.println();

        System.out.println("Con cabecera:");
        System.out.println(pintarMatrizConCabecera(m2));
        System.out.println();

        System.out.println("Con simbolos:");
        System.out.println(pintarMatrizSimbolos(m2));
    }
}
