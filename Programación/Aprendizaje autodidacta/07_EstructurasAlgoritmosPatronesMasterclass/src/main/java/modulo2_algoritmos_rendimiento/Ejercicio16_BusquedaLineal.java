package modulo2_algoritmos_rendimiento;

/**
 * ============================================================================
 * EJERCICIO 16: Búsqueda Lineal (Linear Search)
 * ============================================================================
 * 📚 Teoría: teoria/02_Algoritmos_Rendimiento_BigO.md - Sección 2.2
 *
 * CONTEXTO:
 * La búsqueda lineal es el algoritmo más primitivo: recorre el array
 * elemento por elemento hasta encontrar el objetivo. No requiere que
 * el array esté ordenado, pero es O(n) en el peor caso.
 *
 * Implementa las siguientes variantes:
 * - buscarPrimero(int[] arr, int objetivo): índice de la primera ocurrencia.
 * - buscarUltimo(int[] arr, int objetivo): índice de la última ocurrencia.
 * - contarOcurrencias(int[] arr, int objetivo): cuántas veces aparece.
 * - buscarMinimo(int[] arr): encontrar el valor mínimo.
 * - buscarMaximo(int[] arr): encontrar el valor máximo.
 *
 * RESTRICCIONES:
 * - No usar Arrays.sort(), Collections, ni streams.
 * - Una sola pasada por variante (no recorrer dos veces).
 * - Retornar -1 cuando no se encuentre el elemento.
 *
 * COMPLEJIDAD OBJETIVO: O(n) para todas las variantes.
 * ============================================================================
 */
public class Ejercicio16_BusquedaLineal {

    public static int buscarPrimero(int[] arr, int objetivo) {
        // TODO 1: Recorrer el array desde el inicio (i=0) hasta el final.
        //         Al encontrar arr[i] == objetivo, retornar i inmediatamente.
        //         Si el bucle termina sin encontrarlo, retornar -1.
        return -1;
    }

    public static int buscarUltimo(int[] arr, int objetivo) {
        // TODO 2: Recorrer el array desde el FINAL (i=arr.length-1) hacia el inicio.
        //         Al encontrar arr[i] == objetivo, retornar i inmediatamente.
        //         Si el bucle termina sin encontrarlo, retornar -1.
        return -1;
    }

    public static int contarOcurrencias(int[] arr, int objetivo) {
        // TODO 3: Recorrer el array completo contando cuántas veces
        //         arr[i] == objetivo. Retornar el contador.
        return 0;
    }

    public static int buscarMinimo(int[] arr) {
        // TODO 4: Inicializar min = arr[0]. Recorrer desde i=1 comparando.
        //         Si arr[i] < min, actualizar min.
        //         Validar que el array no esté vacío (lanzar excepción).
        //         Retornar el valor mínimo encontrado.
        return 0;
    }

    public static int buscarMaximo(int[] arr) {
        // TODO 5: Mismo patrón que buscarMinimo pero buscando el mayor.
        //         Inicializar max = arr[0]. Si arr[i] > max, actualizar.
        return 0;
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 16: Búsqueda Lineal ===\n");

        int[] datos = {4, 7, 2, 7, 9, 1, 7, 3, 8, 7};

        System.out.println("Array: [4, 7, 2, 7, 9, 1, 7, 3, 8, 7]");
        System.out.println("buscarPrimero(7)   = " + buscarPrimero(datos, 7));    // 1
        System.out.println("buscarUltimo(7)    = " + buscarUltimo(datos, 7));     // 9
        System.out.println("contarOcurrencias(7)= " + contarOcurrencias(datos, 7)); // 4
        System.out.println("buscarPrimero(99)  = " + buscarPrimero(datos, 99));   // -1
        System.out.println("buscarMinimo()     = " + buscarMinimo(datos));        // 1
        System.out.println("buscarMaximo()     = " + buscarMaximo(datos));        // 9

        System.out.println("\n=== FIN EJERCICIO 16 ===");
    }
}
