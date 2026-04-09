package modulo2_algoritmos_rendimiento;

/**
 * ============================================================================
 * EJERCICIO 23: Counting Sort (Ordenamiento No Comparativo)
 * ============================================================================
 * 📚 Teoría: teoria/02_Algoritmos_Rendimiento_BigO.md - Sección 2.3
 *
 * CONTEXTO:
 * Counting Sort rompe la barrera de O(n log n) porque NO compara elementos.
 * En su lugar, cuenta las ocurrencias de cada valor y reconstruye el array
 * a partir de los conteos. Es extremadamente rápido cuando el rango de
 * valores (k) es pequeño respecto a n.
 *
 * Implementa:
 * - countingSort(int[] arr): ordena el array usando la técnica de conteo.
 * - countingSortEstable(int[] arr): versión estable que preserva el orden
 *   relativo de elementos iguales (usa la técnica de suma acumulativa).
 *
 * RESTRICCIONES:
 * - Solo funciona con enteros no negativos (o adapta desplazando el rango).
 * - Necesita un array auxiliar de conteo de tamaño (max + 1).
 * - Necesita un array auxiliar de salida de tamaño n (para versión estable).
 * - Sin usar Arrays.sort() ni Collections.
 *
 * COMPLEJIDAD:
 * - Tiempo: O(n + k) donde k = rango de valores.
 * - Espacio: O(k) para el array de conteo.
 * - Estable: Sí (versión acumulativa).
 * ============================================================================
 */
public class Ejercicio23_CountingSort {

    public static void countingSort(int[] arr) {
        // TODO 1: Encontrar el valor máximo del array para dimensionar el array de conteo.
        //         Crear un array de conteo de tamaño (max + 1), inicializado a 0.
        //         Recorrer arr y por cada valor, incrementar conteo[valor]++.

        // TODO 2: Reconstruir el array original recorriendo el array de conteo:
        //         Para cada índice i del conteo, si conteo[i] > 0, escribir
        //         el valor i en arr tantas veces como indique conteo[i].
        //         Mantener un puntero 'pos' que avanza en arr.
    }

    public static int[] countingSortEstable(int[] arr) {
        // TODO 3: Versión estable (importante para ordenar objetos por una clave):
        //         1. Encontrar el máximo.
        //         2. Crear array de conteo y contar ocurrencias.
        //         3. Hacer SUMA ACUMULATIVA en el array de conteo:
        //            conteo[i] += conteo[i-1]
        //            Ahora conteo[i] indica la POSICIÓN FINAL del valor i.
        //         4. Crear array de salida de tamaño arr.length.
        //         5. Recorrer arr DESDE EL FINAL (para estabilidad):
        //            salida[conteo[arr[i]] - 1] = arr[i]
        //            conteo[arr[i]]--
        //         6. Retornar el array de salida.
        return new int[0];
    }

    public static int encontrarMaximo(int[] arr) {
        // TODO 4: Recorrer el array para encontrar el valor máximo.
        //         Validar que no esté vacío.
        return 0;
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
        System.out.println("=== EJERCICIO 23: Counting Sort ===\n");

        // Versión básica
        int[] datos = {4, 2, 2, 8, 3, 3, 1, 7, 5, 4};
        System.out.println("Original:      " + arrayToString(datos));
        countingSort(datos);
        System.out.println("CountingSort:  " + arrayToString(datos));
        // Esperado: [1, 2, 2, 3, 3, 4, 4, 5, 7, 8]

        // Versión estable
        int[] datos2 = {4, 2, 2, 8, 3, 3, 1, 7, 5, 4};
        int[] resultado = countingSortEstable(datos2);
        System.out.println("Estable:       " + arrayToString(resultado));

        // Datos con ceros
        int[] conCeros = {0, 3, 0, 1, 0, 2, 3};
        countingSort(conCeros);
        System.out.println("Con ceros:     " + arrayToString(conCeros));

        // Todos iguales
        int[] iguales = {5, 5, 5, 5, 5};
        countingSort(iguales);
        System.out.println("Todos iguales: " + arrayToString(iguales));

        // Benchmark vs QuickSort
        int[] copia1 = new int[500_000];
        int[] copia2 = new int[500_000];
        for (int i = 0; i < copia1.length; i++) {
            int val = (int)(Math.random() * 1000); // rango pequeño
            copia1[i] = val;
            copia2[i] = val;
        }

        long t1 = System.nanoTime();
        countingSort(copia1);
        long t2 = System.nanoTime();
        System.out.printf("\n500K elementos (rango 0-999): CountingSort = %.2f ms%n", (t2 - t1) / 1e6);

        System.out.println("\n=== FIN EJERCICIO 23 ===");
    }
}
