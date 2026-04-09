package modulo2_algoritmos_rendimiento;

/**
 * ============================================================================
 * EJERCICIO 21: Merge Sort (Divide and Conquer)
 * ============================================================================
 * 📚 Teoría: teoria/02_Algoritmos_Rendimiento_BigO.md - Sección 2.3
 *
 * CONTEXTO:
 * Merge Sort es el algoritmo de ordenamiento "divide y vencerás" más
 * elegante. Divide el array en mitades recursivamente hasta tener sub-arrays
 * de 1 elemento (que ya están "ordenados"), y luego los fusiona (merge)
 * en orden ascendente.
 *
 * El corazón del algoritmo está en la función MERGE: toma dos sub-arrays
 * ya ordenados y los combina en uno solo manteniendo el orden.
 *
 * Implementa:
 * - mergeSort(int[] arr, int izq, int der): divide recursivamente.
 * - merge(int[] arr, int izq, int mid, int der): fusiona dos mitades.
 * - mergeSort(int[] arr): método envolvente para comodidad.
 *
 * RESTRICCIONES:
 * - La función merge requiere un array temporal auxiliar (O(n) espacio extra).
 * - Sin usar Arrays.sort() ni System.arraycopy().
 * - Copiar manualmente los elementos al array temporal y de vuelta.
 *
 * COMPLEJIDAD:
 * - Tiempo: O(n log n) SIEMPRE (mejor, promedio y peor).
 * - Espacio: O(n) — necesita array auxiliar para el merge.
 * - Estable: Sí.
 * ============================================================================
 */
public class Ejercicio21_MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int izq, int der) {
        // TODO 1: Caso base: si izq >= der, retornar (sub-array de 0 o 1 elemento).
        //         Calcular el punto medio: mid = izq + (der - izq) / 2.
        //         Llamada recursiva para la mitad izquierda: mergeSort(arr, izq, mid).
        //         Llamada recursiva para la mitad derecha: mergeSort(arr, mid+1, der).
        //         Fusionar ambas mitades: merge(arr, izq, mid, der).
    }

    private static void merge(int[] arr, int izq, int mid, int der) {
        // TODO 2: Calcular los tamaños de los dos sub-arrays:
        //         n1 = mid - izq + 1 (tamaño izquierda)
        //         n2 = der - mid      (tamaño derecha)
        //         Crear dos arrays temporales: tempIzq[n1] y tempDer[n2].
        //         Copiar los elementos correspondientes de arr a cada temporal.

        // TODO 3: Fusionar los dos temporales de vuelta en arr[izq..der]:
        //         Mantener tres índices: i (tempIzq), j (tempDer), k (arr).
        //         Mientras haya elementos en ambos temporales:
        //           Si tempIzq[i] <= tempDer[j] → arr[k] = tempIzq[i], i++.
        //           Si no → arr[k] = tempDer[j], j++.
        //           k++.
        //         Copiar los elementos restantes de tempIzq (si quedan).
        //         Copiar los elementos restantes de tempDer (si quedan).
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
        System.out.println("=== EJERCICIO 21: Merge Sort ===\n");

        int[] datos = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Original:  " + arrayToString(datos));
        mergeSort(datos);
        System.out.println("Ordenado:  " + arrayToString(datos));
        // Esperado: [3, 9, 10, 27, 38, 43, 82]

        // Array ya ordenado
        int[] ordenado = {1, 2, 3, 4, 5};
        mergeSort(ordenado);
        System.out.println("\nYa ordenado: " + arrayToString(ordenado));

        // Array invertido
        int[] invertido = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        mergeSort(invertido);
        System.out.println("Invertido:   " + arrayToString(invertido));

        // Array con duplicados
        int[] duplicados = {5, 3, 5, 1, 3, 5, 2};
        mergeSort(duplicados);
        System.out.println("Duplicados:  " + arrayToString(duplicados));
        // Esperado: [1, 2, 3, 3, 5, 5, 5]

        // Array de 1 elemento
        int[] uno = {42};
        mergeSort(uno);
        System.out.println("Un elemento: " + arrayToString(uno));

        // Benchmark con 100,000 elementos
        int[] grande = new int[100_000];
        for (int i = 0; i < grande.length; i++) grande[i] = (int)(Math.random() * 1_000_000);
        long inicio = System.nanoTime();
        mergeSort(grande);
        long fin = System.nanoTime();
        System.out.printf("\n100,000 elementos ordenados en %.2f ms%n", (fin - inicio) / 1_000_000.0);

        System.out.println("\n=== FIN EJERCICIO 21 ===");
    }
}
