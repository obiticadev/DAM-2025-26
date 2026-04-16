package modulo3_estructuras_avanzadas;

/**
 * ============================================================================
 * EJERCICIO 41: Heap Sort
 * ============================================================================
 * 📚 Teoría: teoria/03_Estructuras_Avanzadas_Grafos.md - Sección 3.3
 *
 * CONTEXTO:
 * Heap Sort combina lo mejor de los heaps con el sorting. El algoritmo:
 * 1. Construir un MAX-HEAP in-place sobre el array (fase de heapify).
 * 2. Extraer repetidamente el máximo intercambiándolo con el último
 *    elemento no ordenado y reduciendo el tamaño del heap.
 *
 * Implementa:
 * - heapSort(int[] arr): ordena el array in-place ascendentemente.
 * - heapify(int[] arr, int n, int i): sift-down sobre el nodo i.
 * - buildMaxHeap(int[] arr): construir max-heap desde un array desordenado.
 *
 * RESTRICCIONES:
 * - In-place (espacio O(1)) — no usar array auxiliar.
 * - buildMaxHeap recorre los nodos internos de abajo arriba: (n/2 - 1) a 0.
 * - Sin usar PriorityQueue ni ninguna colección.
 *
 * COMPLEJIDAD:
 * - Tiempo: O(n log n) SIEMPRE (mejor, promedio y peor).
 * - Espacio: O(1) — in-place.
 * - Estable: No.
 * ============================================================================
 */
public class Ejercicio41_HeapSort {

    public static void heapSort(int[] arr) {
        // TODO 1: Fase 1 — Construir Max-Heap:
        //         Para i desde (arr.length / 2 - 1) hasta 0 (descendente):
        //           heapify(arr, arr.length, i)
        //         Tras esto, arr[0] contiene el MÁXIMO.

        // TODO 2: Fase 2 — Extraer máximos:
        //         Para i desde (arr.length - 1) hasta 1 (descendente):
        //           swap(arr[0], arr[i]) — el máximo va al final.
        //           heapify(arr, i, 0) — restaurar heap en el subarray [0, i).
    }

    private static void heapify(int[] arr, int n, int i) {
        // TODO 3: n = tamaño del heap (no del array completo).
        //         i = nodo a "bajar" (sift-down).
        //         Calcular hijoIzq = 2*i + 1, hijoDer = 2*i + 2.
        //         Encontrar el MAYOR entre padre, hijoIzq y hijoDer.
        //         Si el mayor no es el padre → swap y recursión en el hijo afectado.
    }

    private static void swap(int[] arr, int a, int b) {
        // TODO 4: Intercambiar arr[a] y arr[b] con variable temporal.
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
        System.out.println("=== EJERCICIO 41: Heap Sort ===\n");

        int[] datos = {12, 11, 13, 5, 6, 7, 3, 1};
        System.out.println("Original: " + arrayToString(datos));
        heapSort(datos);
        System.out.println("Ordenado: " + arrayToString(datos));
        // Esperado: [1, 3, 5, 6, 7, 11, 12, 13]

        // Benchmark
        int[] grande = new int[500_000];
        for (int i = 0; i < grande.length; i++) grande[i] = (int)(Math.random() * 1_000_000);
        long t1 = System.nanoTime();
        heapSort(grande);
        long t2 = System.nanoTime();
        System.out.printf("\n500K elementos: %.2f ms%n", (t2 - t1) / 1e6);

        // Verificar
        boolean ordenado = true;
        for (int i = 1; i < grande.length; i++) {
            if (grande[i] < grande[i - 1]) { ordenado = false; break; }
        }
        System.out.println("Correctamente ordenado: " + ordenado);

        System.out.println("\n=== FIN EJERCICIO 41 ===");
    }
}
