package modulo2_algoritmos_rendimiento;

/**
 * ============================================================================
 * EJERCICIO 25: Two Pointers — Par con Suma Objetivo
 * ============================================================================
 * 📚 Teoría: teoria/02_Algoritmos_Rendimiento_BigO.md - Sección 2.5
 *
 * CONTEXTO:
 * Dado un array ORDENADO de enteros, encontrar dos elementos cuya suma
 * sea igual a un valor objetivo (target). La solución por fuerza bruta
 * sería O(n²) con dos bucles. Con Two Pointers logramos O(n).
 *
 * La idea: un puntero 'left' empieza al inicio, otro 'right' al final.
 * - Si la suma es menor que target → mover left a la derecha (necesitamos más).
 * - Si la suma es mayor que target → mover right a la izquierda (necesitamos menos).
 * - Si la suma es igual → encontrado.
 *
 * Implementa:
 * - encontrarPar(int[] arr, int target): retorna los ÍNDICES del par como int[2].
 * - encontrarTodosLosPares(int[] arr, int target): retorna todos los pares.
 * - tresSuma(int[] arr, int target): encontrar TRES números que sumen target.
 *
 * RESTRICCIONES:
 * - El array DEBE estar ordenado previamente.
 * - Sin usar HashSet, HashMap ni estructuras de búsqueda auxiliares.
 * - Solo dos punteros moviéndose en direcciones opuestas.
 *
 * COMPLEJIDAD OBJETIVO:
 * - encontrarPar(): O(n)
 * - encontrarTodosLosPares(): O(n)
 * - tresSuma(): O(n²) — un bucle externo + two pointers interno
 * ============================================================================
 */
public class Ejercicio25_TwoPointersSuma {

    public static int[] encontrarPar(int[] arr, int target) {
        // TODO 1: Inicializar left = 0, right = arr.length - 1.
        //         Mientras left < right:
        //           int suma = arr[left] + arr[right].
        //           Si suma == target → retornar new int[]{left, right}.
        //           Si suma < target → left++ (necesitamos un valor mayor).
        //           Si suma > target → right-- (necesitamos un valor menor).
        //         Si no se encuentra, retornar null.
        return null;
    }

    public static void encontrarTodosLosPares(int[] arr, int target) {
        // TODO 2: Similar a encontrarPar pero sin detenerse al primer resultado.
        //         Al encontrar un par (suma == target):
        //           Imprimir el par: "Par encontrado: arr[left] + arr[right] = target"
        //           Mover AMBOS punteros: left++, right-- (para buscar más pares).
        //           Además, saltar duplicados:
        //             while left < right y arr[left] == arr[left-1] → left++
        //             while left < right y arr[right] == arr[right+1] → right--
    }

    public static int[] tresSuma(int[] arr, int target) {
        // TODO 3: Para cada elemento arr[i] (bucle i=0 hasta n-2):
        //           Usar Two Pointers en el sub-array [i+1, n-1]
        //           buscando dos números que sumen (target - arr[i]).
        //           Si se encuentra: retornar new int[]{i, left, right}.
        //         Optimización: si arr[i] == arr[i-1], skip (evitar duplicados).
        //         Si no se encuentra ningún triplete, retornar null.
        return null;
    }

    public static String parToString(int[] par) {
        if (par == null) return "No encontrado";
        return "[" + par[0] + ", " + par[1] + (par.length > 2 ? ", " + par[2] : "") + "]";
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 25: Two Pointers — Suma ===\n");

        int[] ordenado = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Encontrar un par que sume 11
        int[] par = encontrarPar(ordenado, 11);
        System.out.println("Par que sume 11: " + parToString(par));
        // Posible: [0, 9] → 1+10=11

        // Par que no existe
        int[] par2 = encontrarPar(ordenado, 25);
        System.out.println("Par que sume 25: " + parToString(par2)); // null

        // Encontrar TODOS los pares que sumen 11
        System.out.println("\nTodos los pares que sumen 11:");
        encontrarTodosLosPares(ordenado, 11);
        // 1+10, 2+9, 3+8, 4+7, 5+6

        // Tres suma
        int[] arr3 = {-1, 0, 1, 2, -1, -4};
        // Nota: hay que ordenar primero para que funcione
        // Ordénalo manualmente o reutiliza tu sort
        int[] arr3ord = {-4, -1, -1, 0, 1, 2};
        int[] triplete = tresSuma(arr3ord, 0);
        System.out.println("\nTres números que sumen 0: " + parToString(triplete));
        // Esperado: [-1, -1, 2] o [-1, 0, 1]

        System.out.println("\n=== FIN EJERCICIO 25 ===");
    }
}
