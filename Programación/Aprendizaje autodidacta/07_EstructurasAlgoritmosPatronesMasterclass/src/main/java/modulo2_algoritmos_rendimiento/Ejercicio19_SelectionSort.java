package modulo2_algoritmos_rendimiento;

/**
 * ============================================================================
 * EJERCICIO 19: Selection Sort
 * ============================================================================
 * 📚 Teoría: teoria/02_Algoritmos_Rendimiento_BigO.md - Sección 2.3
 *
 * CONTEXTO:
 * Selection Sort divide conceptualmente el array en dos partes:
 * la parte ordenada (izquierda) y la desordenada (derecha).
 * En cada iteración, encuentra el MÍNIMO de la parte desordenada
 * y lo coloca al final de la parte ordenada mediante un swap.
 *
 * Implementa:
 * - selectionSort(int[] arr): versión clásica (buscar mínimo, swap).
 * - selectionSortDescendente(int[] arr): ordena de mayor a menor
 *   (buscar máximo en la parte desordenada).
 *
 * RESTRICCIONES:
 * - In-place (sin arrays auxiliares).
 * - El bucle interno busca el ÍNDICE del mínimo, no el valor directamente.
 * - Sin usar Arrays.sort(), Collections, ni streams.
 *
 * COMPLEJIDAD:
 * - Siempre O(n²) (no se beneficia de datos parcialmente ordenados).
 * - Espacio: O(1)
 * - Estable: No (los swaps a distancia rompen el orden relativo).
 * ============================================================================
 */
public class Ejercicio19_SelectionSort {

    public static void selectionSort(int[] arr) {
        // TODO 1: Bucle externo: i desde 0 hasta arr.length - 1.
        //         En cada iteración, inicializar indiceMenor = i.
        //         Bucle interno: j desde i+1 hasta arr.length.
        //           Si arr[j] < arr[indiceMenor] → actualizar indiceMenor = j.
        //         Después del bucle interno, intercambiar arr[i] con arr[indiceMenor]
        //         (solo si indiceMenor != i, para evitar swap innecesario).
    }

    public static void selectionSortDescendente(int[] arr) {
        // TODO 2: Misma lógica pero buscando el MÁXIMO en la parte desordenada.
        //         Bucle externo: i desde 0.
        //         Inicializar indiceMayor = i.
        //         Bucle interno: buscar arr[j] > arr[indiceMayor].
        //         Intercambiar arr[i] con arr[indiceMayor].
    }

    public static void imprimirPasos(int[] arr) {
        // TODO 3: Implementar selectionSort que imprima el estado del array
        //         después de cada swap:
        //         "Paso 1: mínimo=11 en pos 5, swap con pos 0 → [11, 34, 25, 12, 22, 64, 90]"
        //         "Paso 2: mínimo=12 en pos 3, swap con pos 1 → [11, 12, 25, 34, 22, 64, 90]"
        //         Esto ayuda a visualizar cómo crece la parte ordenada.
    }

    public static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 19: Selection Sort ===\n");

        int[] datos = {64, 34, 25, 12, 22, 11, 90};

        // Ascendente
        int[] copia1 = datos.clone();
        System.out.println("Original:      " + arrayToString(copia1));
        selectionSort(copia1);
        System.out.println("Ascendente:    " + arrayToString(copia1));
        // Esperado: [11, 12, 22, 25, 34, 64, 90]

        // Descendente
        int[] copia2 = datos.clone();
        selectionSortDescendente(copia2);
        System.out.println("Descendente:   " + arrayToString(copia2));
        // Esperado: [90, 64, 34, 25, 22, 12, 11]

        // Pasos detallados
        System.out.println("\n--- Ejecución paso a paso ---");
        int[] copia3 = datos.clone();
        imprimirPasos(copia3);

        System.out.println("\n=== FIN EJERCICIO 19 ===");
    }
}
