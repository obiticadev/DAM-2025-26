package modulo2_algoritmos_rendimiento;

/**
 * ============================================================================
 * EJERCICIO 20: Insertion Sort
 * ============================================================================
 * 📚 Teoría: teoria/02_Algoritmos_Rendimiento_BigO.md - Sección 2.3
 *
 * CONTEXTO:
 * Insertion Sort funciona como ordenar cartas en la mano: toma cada elemento
 * y lo inserta en su posición correcta dentro de la parte ya ordenada,
 * desplazando los mayores a la derecha.
 *
 * Es el mejor algoritmo cuadrático para datos casi ordenados (O(n) en mejor caso).
 *
 * Implementa:
 * - insertionSort(int[] arr): versión clásica.
 * - insertionSortConContador(int[] arr): devuelve el número de desplazamientos
 *   realizados (mide cuánto "trabajo" hizo el algoritmo).
 *
 * RESTRICCIONES:
 * - In-place.
 * - El "hueco" se abre desplazando con un bucle while descendente, NO swap.
 *   (Guardar el elemento clave → desplazar mayores → colocar la clave).
 * - Sin usar Arrays.sort().
 *
 * COMPLEJIDAD:
 * - Mejor caso (ya ordenado): O(n)
 * - Peor caso (invertido): O(n²)
 * - Espacio: O(1)
 * - Estable: Sí
 * ============================================================================
 */
public class Ejercicio20_InsertionSort {

    public static void insertionSort(int[] arr) {
        // TODO 1: Bucle externo: i desde 1 hasta arr.length (el pos 0 ya está "ordenado").
        //         Guardar arr[i] en una variable 'clave'.
        //         Inicializar j = i - 1.
        //         Bucle interno (while): mientras j >= 0 Y arr[j] > clave:
        //           Desplazar arr[j] a arr[j+1] (abrir hueco).
        //           j--.
        //         Colocar clave en arr[j+1] (su posición correcta).
    }

    public static int insertionSortConContador(int[] arr) {
        // TODO 2: Mismo algoritmo que insertionSort pero contando cada
        //         desplazamiento (cada vez que mueves arr[j] a arr[j+1]).
        //         Retornar el total de desplazamientos.
        //         Datos ya ordenados → 0 desplazamientos.
        //         Datos invertidos → máximo desplazamientos (n*(n-1)/2).
        return 0;
    }

    public static void insertionSortVisualizando(int[] arr) {
        // TODO 3: Versión que imprime el array después de insertar cada elemento:
        //         "Insertar 12: [12, 34, 25, ...] (clave=12 colocada en pos 0)"
        //         Esto muestra cómo crece la parte ordenada de izquierda a derecha.
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
        System.out.println("=== EJERCICIO 20: Insertion Sort ===\n");

        int[] datos = {64, 34, 25, 12, 22, 11, 90};

        int[] copia1 = datos.clone();
        System.out.println("Original:  " + arrayToString(copia1));
        insertionSort(copia1);
        System.out.println("Ordenado:  " + arrayToString(copia1));

        // Contar desplazamientos en datos desordenados
        int[] copia2 = datos.clone();
        int shifts1 = insertionSortConContador(copia2);
        System.out.println("Desplazamientos (desordenado): " + shifts1);

        // Contar desplazamientos en datos ya ordenados
        int[] yaOrdenado = {1, 2, 3, 4, 5, 6, 7};
        int shifts2 = insertionSortConContador(yaOrdenado);
        System.out.println("Desplazamientos (ya ordenado): " + shifts2); // 0

        // Contar desplazamientos en datos invertidos (peor caso)
        int[] invertido = {7, 6, 5, 4, 3, 2, 1};
        int shifts3 = insertionSortConContador(invertido);
        System.out.println("Desplazamientos (invertido):   " + shifts3); // 21

        // Visualización
        System.out.println("\n--- Paso a paso ---");
        insertionSortVisualizando(datos.clone());

        System.out.println("\n=== FIN EJERCICIO 20 ===");
    }
}
