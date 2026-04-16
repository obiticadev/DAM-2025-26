package modulo2_algoritmos_rendimiento;

/**
 * ============================================================================
 * EJERCICIO 29: Búsqueda Binaria — Variantes Avanzadas
 * ============================================================================
 * 📚 Teoría: teoria/02_Algoritmos_Rendimiento_BigO.md - Sección 2.2
 *
 * CONTEXTO:
 * La búsqueda binaria no solo sirve para encontrar un elemento exacto.
 * Sus variantes son extremadamente útiles en problemas reales:
 * - Lower Bound: primera posición donde se podría insertar un valor.
 * - Upper Bound: última posición donde se podría insertar un valor.
 * - Encontrar la primera/última ocurrencia de un valor duplicado.
 * - Buscar en un array rotado.
 *
 * Implementa:
 * - lowerBound(int[] arr, int target): índice de la primera ocurrencia
 *   o posición donde debería insertarse.
 * - upperBound(int[] arr, int target): índice después de la última ocurrencia.
 * - primeraOcurrencia(int[] arr, int target): primera aparición exacta.
 * - ultimaOcurrencia(int[] arr, int target): última aparición exacta.
 * - buscarEnRotado(int[] arr, int target): búsqueda en array rotado ordenado.
 *
 * RESTRICCIONES:
 * - Todos deben ser O(log n).
 * - Sin usar Arrays.binarySearch() ni ningún método de java.util.
 * - Manejar correctamente duplicados.
 *
 * COMPLEJIDAD OBJETIVO: O(log n) para todas las variantes.
 * ============================================================================
 */
public class Ejercicio29_BusquedaBinariaAvanzada {

    public static int lowerBound(int[] arr, int target) {
        // TODO 1: Encontrar el PRIMER índice donde arr[i] >= target.
        //         Inicializar low = 0, high = arr.length, resultado = arr.length.
        //         Mientras low < high:
        //           mid = low + (high - low) / 2
        //           Si arr[mid] >= target → high = mid (buscar más a la izquierda).
        //           Si no → low = mid + 1.
        //         Retornar low.
        return -1;
    }

    public static int upperBound(int[] arr, int target) {
        // TODO 2: Encontrar el PRIMER índice donde arr[i] > target.
        //         Similar a lowerBound pero con > en lugar de >=.
        //         Inicializar low = 0, high = arr.length.
        //         Mientras low < high:
        //           mid = low + (high - low) / 2
        //           Si arr[mid] > target → high = mid.
        //           Si no → low = mid + 1.
        //         Retornar low.
        return -1;
    }

    public static int primeraOcurrencia(int[] arr, int target) {
        // TODO 3: Usar lowerBound y verificar que el elemento en esa posición
        //         sea realmente igual a target.
        //         int pos = lowerBound(arr, target);
        //         Si pos < arr.length Y arr[pos] == target → retornar pos.
        //         Si no → retornar -1 (no existe).
        return -1;
    }

    public static int ultimaOcurrencia(int[] arr, int target) {
        // TODO 4: Usar upperBound y retroceder una posición.
        //         int pos = upperBound(arr, target) - 1;
        //         Si pos >= 0 Y arr[pos] == target → retornar pos.
        //         Si no → retornar -1.
        return -1;
    }

    public static int buscarEnRotado(int[] arr, int target) {
        // TODO 5: Búsqueda binaria en un array ordenado que fue ROTADO.
        //         Ejemplo: [4,5,6,7,0,1,2] (rotación de [0,1,2,4,5,6,7])
        //         Clave: al menos una mitad siempre está ordenada.
        //         low=0, high=arr.length-1.
        //         Mientras low <= high:
        //           mid = low + (high - low) / 2
        //           Si arr[mid] == target → retornar mid.
        //           Si la mitad izquierda está ordenada (arr[low] <= arr[mid]):
        //             Si target >= arr[low] Y target < arr[mid] → high = mid - 1
        //             Si no → low = mid + 1
        //           Si no (la mitad derecha está ordenada):
        //             Si target > arr[mid] Y target <= arr[high] → low = mid + 1
        //             Si no → high = mid - 1
        //         Retornar -1 si no se encuentra.
        return -1;
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 29: Búsqueda Binaria Avanzada ===\n");

        int[] conDupes = {1, 2, 2, 2, 3, 4, 4, 5};

        System.out.println("Array: [1, 2, 2, 2, 3, 4, 4, 5]");
        System.out.println("lowerBound(2) = " + lowerBound(conDupes, 2));     // 1
        System.out.println("upperBound(2) = " + upperBound(conDupes, 2));     // 4
        System.out.println("primera(2)    = " + primeraOcurrencia(conDupes, 2)); // 1
        System.out.println("ultima(2)     = " + ultimaOcurrencia(conDupes, 2));  // 3
        System.out.println("primera(4)    = " + primeraOcurrencia(conDupes, 4)); // 5
        System.out.println("ultima(4)     = " + ultimaOcurrencia(conDupes, 4));  // 6
        System.out.println("primera(6)    = " + primeraOcurrencia(conDupes, 6)); // -1

        // Número de ocurrencias usando upper - lower
        int count = upperBound(conDupes, 2) - lowerBound(conDupes, 2);
        System.out.println("Ocurrencias de 2: " + count); // 3

        // Array rotado
        System.out.println("\n--- Búsqueda en Array Rotado ---");
        int[] rotado = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Array rotado: [4, 5, 6, 7, 0, 1, 2]");
        System.out.println("buscar(0) = " + buscarEnRotado(rotado, 0));  // 4
        System.out.println("buscar(4) = " + buscarEnRotado(rotado, 4));  // 0
        System.out.println("buscar(7) = " + buscarEnRotado(rotado, 7));  // 3
        System.out.println("buscar(3) = " + buscarEnRotado(rotado, 3));  // -1

        System.out.println("\n=== FIN EJERCICIO 29 ===");
    }
}
