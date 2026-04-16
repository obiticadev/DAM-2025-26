package modulo2_algoritmos_rendimiento;

/**
 * ============================================================================
 * EJERCICIO 28: Sliding Window — Ventana de Tamaño Variable
 * ============================================================================
 * 📚 Teoría: teoria/02_Algoritmos_Rendimiento_BigO.md - Sección 2.6
 *
 * CONTEXTO:
 * La ventana de tamaño variable expande y contrae dinámicamente según
 * una condición. La ventana crece (expandiendo right) hasta que la condición
 * se cumple, y luego se contrae (moviendo left) para encontrar el óptimo.
 *
 * Problemas clásicos:
 * 1. Encontrar el sub-array más corto cuya suma sea >= target.
 * 2. Encontrar la subcadena más larga sin caracteres repetidos.
 *
 * Implementa:
 * - subarrayMinimoConSuma(int[] arr, int target): longitud mínima del
 *   sub-array cuya suma sea >= target.
 * - subcadenaSinRepetidos(String s): longitud de la subcadena más larga
 *   sin caracteres repetidos.
 *
 * RESTRICCIONES:
 * - Para la subcadena, usar un array booleano de 128 posiciones (ASCII)
 *   como "set" de caracteres, NO usar HashSet.
 * - Sin usar java.util.* (excepto en la zona de pruebas para verificar).
 *
 * COMPLEJIDAD OBJETIVO:
 * - subarrayMinimoConSuma(): O(n) — cada elemento entra y sale 1 vez.
 * - subcadenaSinRepetidos(): O(n)
 * ============================================================================
 */
public class Ejercicio28_SlidingWindowVariable {

    public static int subarrayMinimoConSuma(int[] arr, int target) {
        // TODO 1: Inicializar left = 0, sumaVentana = 0, minLongitud = Integer.MAX_VALUE.
        //         Expandir con right (bucle for desde 0 hasta arr.length):
        //           sumaVentana += arr[right]
        //           Mientras sumaVentana >= target (condición cumplida):
        //             Actualizar minLongitud = Math.min(minLongitud, right - left + 1)
        //             Contraer: sumaVentana -= arr[left], left++
        //         Si minLongitud sigue siendo MAX_VALUE, retornar 0 (no encontrado).
        //         Si no, retornar minLongitud.
        return 0;
    }

    public static int subcadenaSinRepetidos(String s) {
        // TODO 2: Crear un array boolean[] visto = new boolean[128] (tabla ASCII).
        //         Inicializar left = 0, maxLongitud = 0.
        //         Expandir con right (bucle for desde 0):
        //           char c = s.charAt(right)
        //           Mientras visto[c] sea true (carácter repetido en ventana):
        //             Marcar visto[s.charAt(left)] = false
        //             left++
        //           Marcar visto[c] = true
        //           Actualizar maxLongitud = Math.max(maxLongitud, right - left + 1)
        //         Retornar maxLongitud.
        return 0;
    }

    public static String obtenerSubcadenaSinRepetidos(String s) {
        // TODO 3: Misma lógica que subcadenaSinRepetidos pero además de la
        //         longitud, registrar las posiciones (inicioMax, finMax) de la
        //         subcadena más larga encontrada.
        //         Al final, retornar s.substring(inicioMax, finMax + 1).
        return "";
    }

    public static int contarSubarraysConSuma(int[] arr, int target) {
        // TODO 4: Contar cuántos sub-arrays contiguos suman EXACTAMENTE target.
        //         Usar sliding window variable:
        //         Expandir right, contraer left cuando sumaVentana > target.
        //         Si sumaVentana == target → incrementar contador y contraer.
        //         NOTA: Esto es más complejo que los anteriores. Si los elementos
        //         son todos positivos, la ventana deslizante funciona perfectamente.
        return 0;
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 28: Sliding Window Variable ===\n");

        // Sub-array mínimo con suma >= target
        System.out.println("--- Sub-array mínimo con suma >= target ---");
        int[] arr1 = {2, 3, 1, 2, 4, 3};
        int min1 = subarrayMinimoConSuma(arr1, 7);
        System.out.println("arr=[2,3,1,2,4,3] target=7 → longitud mínima: " + min1); // 2 ([4,3])

        int[] arr2 = {1, 4, 4};
        int min2 = subarrayMinimoConSuma(arr2, 4);
        System.out.println("arr=[1,4,4] target=4 → longitud mínima: " + min2); // 1 ([4])

        int[] arr3 = {1, 1, 1};
        int min3 = subarrayMinimoConSuma(arr3, 10);
        System.out.println("arr=[1,1,1] target=10 → " + min3); // 0 (imposible)

        // Subcadena sin repetidos
        System.out.println("\n--- Subcadena más larga sin repetidos ---");
        System.out.println("'abcabcbb' → " + subcadenaSinRepetidos("abcabcbb")); // 3 (abc)
        System.out.println("'bbbbb'    → " + subcadenaSinRepetidos("bbbbb"));     // 1 (b)
        System.out.println("'pwwkew'   → " + subcadenaSinRepetidos("pwwkew"));    // 3 (wke)
        System.out.println("''         → " + subcadenaSinRepetidos(""));           // 0

        String sub = obtenerSubcadenaSinRepetidos("abcabcbb");
        System.out.println("Subcadena real de 'abcabcbb': '" + sub + "'"); // "abc"

        // Contar sub-arrays con suma exacta
        System.out.println("\n--- Sub-arrays con suma exacta ---");
        int[] arr4 = {1, 2, 3, 4, 5};
        int count = contarSubarraysConSuma(arr4, 5);
        System.out.println("arr=[1,2,3,4,5] target=5 → " + count + " sub-arrays");
        // Esperado: 2 → [2,3] y [5]

        System.out.println("\n=== FIN EJERCICIO 28 ===");
    }
}
