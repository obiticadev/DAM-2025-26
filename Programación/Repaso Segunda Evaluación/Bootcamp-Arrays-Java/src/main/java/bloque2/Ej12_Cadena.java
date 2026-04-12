package bloque2;

/**
 * EJERCICIO 12 — Transformaciones Encadenadas
 * Teoria: teoria/02_Rotacion_Transformacion.md (seccion 6)
 *
 * Contexto: En un examen te pueden pedir aplicar varias transformaciones seguidas.
 * Este ejercicio combina todo lo aprendido en el bloque.
 */
public class Ej12_Cadena {

    /**
     * Aplica una secuencia de transformaciones a la matriz segun un array de comandos.
     * Comandos validos: "T" (transpuesta), "90H" (rotar 90 horario), "90A" (rotar 90 antihorario),
     * "180" (rotar 180), "EH" (espejo horizontal), "EV" (espejo vertical).
     * Cualquier comando no reconocido se ignora.
     *
     * @param matriz   array bidimensional original
     * @param comandos array de Strings con los comandos a aplicar en orden
     * @return matriz resultante tras todas las transformaciones
     */
    public static int[][] aplicarCadena(int[][] matriz, String[] comandos) {
        // TODO 1: Empieza con una copia de la matriz (o la referencia si prefieres).
        //         Recorre el array de comandos. Para cada uno, aplica la transformacion
        //         correspondiente usando los metodos de Ej07, Ej08, Ej09, Ej10, Ej11.
        //         Guarda el resultado y usalo como entrada del siguiente comando.
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
     * Devuelve la secuencia inversa de una cadena de comandos.
     * Cada comando se reemplaza por su inverso:
     * "T" -> "T", "90H" -> "90A", "90A" -> "90H", "180" -> "180", "EH" -> "EH", "EV" -> "EV"
     * Ademas, el orden se invierte.
     *
     * @param comandos array de comandos
     * @return array de comandos inversos en orden inverso
     */
    public static String[] secuenciaInversa(String[] comandos) {
        // TODO 3: Crea un nuevo array del mismo tamano. Recorre el original de atras a adelante.
        //         Para cada comando, pon su inverso en la posicion correspondiente.
        //         "90H" <-> "90A", el resto son su propio inverso.
        return null;
    }

    /**
     * Comprueba que aplicar una cadena y luego su inversa devuelve la original.
     *
     * @param matriz   array bidimensional
     * @param comandos array de comandos
     * @return true si aplicar(cadena) + aplicar(inversa) == original
     */
    public static boolean cadenaYInversaEsOriginal(int[][] matriz, String[] comandos) {
        // TODO 4: Aplica la cadena. Luego aplica la secuencia inversa al resultado.
        //         Compara con la original.
        return false;
    }

    /**
     * Cuenta cuantas transformaciones de la cadena cambian las dimensiones de la matriz.
     * Cambian dimensiones: "T", "90H", "90A" (si la matriz no es cuadrada).
     * No cambian: "180", "EH", "EV".
     * Para este metodo, considera que siempre cambian: "T", "90H", "90A".
     *
     * @param comandos array de comandos
     * @return numero de comandos que cambian dimensiones
     */
    public static int contarCambiosDimension(String[] comandos) {
        // TODO 5: Recorre los comandos. Cuenta los que son "T", "90H" o "90A".
        return 0;
    }

    /**
     * Dada una matriz y una cadena de comandos, devuelve las dimensiones finales
     * como un array {filas, columnas} SIN ejecutar las transformaciones.
     * Pista: "T", "90H", "90A" intercambian filas y columnas. El resto las mantiene.
     *
     * @param filas    filas de la original
     * @param columnas columnas de la original
     * @param comandos array de comandos
     * @return array {filasFinales, columnasFinales}
     */
    public static int[] dimensionesFinales(int filas, int columnas, String[] comandos) {
        // TODO 6: Empieza con las dimensiones dadas. Recorre los comandos.
        //         Si el comando es "T", "90H" o "90A", intercambia filas y columnas.
        //         Devuelve las dimensiones finales.
        return null;
    }

    /**
     * Encuentra la cadena de comandos MAS CORTA que produce el mismo resultado
     * que la cadena dada. Las unicas simplificaciones que debes aplicar:
     * - Dos "T" seguidas se cancelan (quitar ambas).
     * - Dos "EH" seguidas se cancelan.
     * - Dos "EV" seguidas se cancelan.
     * - Dos "180" seguidas se cancelan.
     * Devuelve la cadena simplificada.
     *
     * @param comandos array de comandos
     * @return array simplificado
     */
    public static String[] simplificarCadena(String[] comandos) {
        // TODO 7: Usa una logica de "pila manual" con un array de String.
        //         Recorre los comandos. Si el ultimo anadido es igual al actual, quita ambos
        //         (son operaciones que se anulan). Si no, anade el actual.
        //         Al final, devuelve un array del tamano correcto con los comandos restantes.
        return null;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION MASTER
    // ══════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 12: Transformaciones Encadenadas ===\n");

        int[][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Original:");
        System.out.println(pintar(m));

        String[] cadena = {"90H", "EV", "T"};
        int[][] resultado = aplicarCadena(m, cadena);
        System.out.println("\nTras {90H, EV, T}:");
        System.out.println(resultado != null ? pintar(resultado) : "null");

        String[] inversa = secuenciaInversa(cadena);
        System.out.print("Secuencia inversa: ");
        if (inversa != null) for (String c : inversa) System.out.print(c + " ");
        System.out.println();

        System.out.println("Cadena + Inversa = Original: " + cadenaYInversaEsOriginal(m, cadena));

        int[] dims = dimensionesFinales(3, 3, cadena);
        System.out.println("Dimensiones finales: " + (dims != null ? dims[0] + "x" + dims[1] : "null"));

        String[] larga = {"90H", "90H", "EH", "EH", "T"};
        String[] simplificada = simplificarCadena(larga);
        System.out.print("Simplificada: ");
        if (simplificada != null) for (String c : simplificada) System.out.print(c + " ");
        System.out.println();
    }
}
