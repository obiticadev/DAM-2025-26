package modulo2_algoritmos_rendimiento;

/**
 * ============================================================================
 * EJERCICIO 26: Two Pointers — Palíndromos e Inversiones
 * ============================================================================
 * 📚 Teoría: teoria/02_Algoritmos_Rendimiento_BigO.md - Sección 2.5
 *
 * CONTEXTO:
 * La técnica Two Pointers también aplica cuando ambos punteros parten
 * de extremos opuestos y se acercan al centro. Es la técnica ideal para
 * detectar palíndromos e invertir arrays/strings in-place.
 *
 * Implementa:
 * - esPalindromo(String texto): ¿es palíndromo? (ignorando espacios/mayúsculas).
 * - invertirArray(int[] arr): invertir in-place con dos punteros.
 * - invertirString(char[] chars): invertir un array de caracteres in-place.
 * - eliminarDuplicados(int[] ordenado): eliminar duplicados in-place
 *   en un array ORDENADO, retornando el nuevo tamaño lógico.
 *
 * RESTRICCIONES:
 * - Todo in-place, sin crear arrays/strings auxiliares.
 * - Para palíndromo, ignorar caracteres no alfanuméricos y mayúsculas.
 * - Sin usar StringBuilder.reverse(), Collections.reverse(), etc.
 *
 * COMPLEJIDAD OBJETIVO: O(n) para todas las funciones, O(1) espacio.
 * ============================================================================
 */
public class Ejercicio26_TwoPointersInversiones {

    public static boolean esPalindromo(String texto) {
        // TODO 1: Convertir a minúsculas. Inicializar left = 0, right = length - 1.
        //         Mientras left < right:
        //           Si el carácter en left NO es alfanumérico → left++ (saltar).
        //           Si el carácter en right NO es alfanumérico → right-- (saltar).
        //           Si ambos son alfanuméricos: comparar. Si no son iguales → false.
        //           Mover ambos: left++, right--.
        //         Si termina el bucle, retornar true.
        //         Usar Character.isLetterOrDigit() y Character.toLowerCase().
        return false;
    }

    public static void invertirArray(int[] arr) {
        // TODO 2: Inicializar left = 0, right = arr.length - 1.
        //         Mientras left < right:
        //           swap(arr[left], arr[right]) con variable temporal.
        //           left++, right--.
    }

    public static void invertirString(char[] chars) {
        // TODO 3: Misma lógica que invertirArray pero con char[].
        //         left = 0, right = chars.length - 1.
        //         Swap e incrementar/decrementar.
    }

    public static int eliminarDuplicados(int[] ordenado) {
        // TODO 4: En un array ORDENADO, los duplicados están contiguos.
        //         Usar un puntero 'escritura' (posición donde escribir el siguiente único).
        //         Recorrer con puntero 'lectura' desde 1:
        //           Si ordenado[lectura] != ordenado[escritura]:
        //             escritura++
        //             ordenado[escritura] = ordenado[lectura]
        //         Retornar escritura + 1 (nuevo tamaño sin duplicados).
        return 0;
    }

    public static String arrayToString(int[] arr, int longitud) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < longitud; i++) {
            sb.append(arr[i]);
            if (i < longitud - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 26: Two Pointers — Palíndromos ===\n");

        // Palíndromos
        System.out.println("--- Palíndromos ---");
        System.out.println("'Ana' → " + esPalindromo("Ana"));                       // true
        System.out.println("'race a car' → " + esPalindromo("race a car"));          // false
        System.out.println("'A man a plan a canal Panama' → " +
                esPalindromo("A man a plan a canal Panama"));                         // true
        System.out.println("'' → " + esPalindromo(""));                              // true
        System.out.println("'Anita lava la tina' → " +
                esPalindromo("Anita lava la tina"));                                  // true

        // Invertir array
        System.out.println("\n--- Invertir Array ---");
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Original: " + arrayToString(arr, arr.length));
        invertirArray(arr);
        System.out.println("Invertido: " + arrayToString(arr, arr.length)); // [5, 4, 3, 2, 1]

        // Invertir string
        System.out.println("\n--- Invertir String ---");
        char[] chars = "Hola Mundo".toCharArray();
        System.out.println("Original: " + new String(chars));
        invertirString(chars);
        System.out.println("Invertido: " + new String(chars)); // odnuM aloH

        // Eliminar duplicados
        System.out.println("\n--- Eliminar Duplicados (array ordenado) ---");
        int[] conDupes = {1, 1, 2, 2, 2, 3, 4, 4, 5};
        System.out.println("Original: " + arrayToString(conDupes, conDupes.length));
        int nuevoSize = eliminarDuplicados(conDupes);
        System.out.println("Sin dupes: " + arrayToString(conDupes, nuevoSize)); // [1, 2, 3, 4, 5]
        System.out.println("Nuevo tamaño: " + nuevoSize); // 5

        System.out.println("\n=== FIN EJERCICIO 26 ===");
    }
}
