package modulo2_algoritmos_rendimiento;

/**
 * ============================================================================
 * EJERCICIO 27: Sliding Window — Suma Máxima (Tamaño Fijo)
 * ============================================================================
 * 📚 Teoría: teoria/02_Algoritmos_Rendimiento_BigO.md - Sección 2.6
 *
 * CONTEXTO:
 * Dado un array de enteros y un tamaño de ventana k, encontrar el sub-array
 * contiguo de tamaño k con la suma máxima.
 *
 * Sin Sliding Window: calcular la suma de cada sub-array = O(n × k).
 * Con Sliding Window: deslizar la ventana restando el que sale y sumando
 * el que entra = O(n).
 *
 * Implementa:
 * - sumaMaximaFuerzaBruta(int[] arr, int k): O(n × k) para comparar.
 * - sumaMaximaVentana(int[] arr, int k): O(n) con sliding window.
 * - promedioMaximoVentana(int[] arr, int k): promedio máximo de sub-array de tamaño k.
 * - sumasTodasVentanas(int[] arr, int k): retorna las sumas de TODAS las ventanas.
 *
 * RESTRICCIONES:
 * - Sin java.util.Arrays ni streams.
 * - La ventana deslizante NO debe recalcular la suma completa en cada paso.
 * - Fórmula: sumaVentana = sumaVentana - arr[i - k] + arr[i]
 *
 * COMPLEJIDAD OBJETIVO:
 * - Fuerza bruta: O(n × k)
 * - Sliding window: O(n)
 * ============================================================================
 */
public class Ejercicio27_SlidingWindowFijo {

    public static int sumaMaximaFuerzaBruta(int[] arr, int k) {
        // TODO 1: Para cada posición i desde 0 hasta arr.length - k:
        //         Calcular la suma del sub-array [i, i+k-1] con un bucle interno.
        //         Si esa suma es mayor que el máximo actual, actualizar.
        //         Retornar la suma máxima encontrada.
        //         NOTA: Este es el enfoque LENTO para comparar con la ventana.
        return 0;
    }

    public static int sumaMaximaVentana(int[] arr, int k) {
        // TODO 2: Calcular la suma de la primera ventana (posiciones 0 a k-1).
        //         Establecerla como sumaActual y maxSuma.
        //         Deslizar la ventana desde i = k hasta arr.length - 1:
        //           sumaActual = sumaActual + arr[i] - arr[i - k]
        //           (entra arr[i], sale arr[i-k])
        //           Si sumaActual > maxSuma → actualizar maxSuma.
        //         Retornar maxSuma.
        return 0;
    }

    public static double promedioMaximoVentana(int[] arr, int k) {
        // TODO 3: Misma lógica que sumaMaximaVentana pero retornando
        //         maxSuma / (double) k para obtener el promedio.
        return 0.0;
    }

    public static int[] sumasTodasVentanas(int[] arr, int k) {
        // TODO 4: Crear un array de resultado de tamaño (arr.length - k + 1).
        //         Calcular la suma de la primera ventana.
        //         Almacenarla en resultado[0].
        //         Deslizar y almacenar cada suma en resultado[i].
        //         Retornar el array de sumas.
        return new int[0];
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
        System.out.println("=== EJERCICIO 27: Sliding Window (Fijo) ===\n");

        int[] datos = {2, 1, 5, 1, 3, 2};
        int k = 3;

        System.out.println("Array: " + arrayToString(datos) + ", k=" + k);

        int maxBruta = sumaMaximaFuerzaBruta(datos, k);
        System.out.println("Suma máxima (fuerza bruta): " + maxBruta);   // 9 ([5,1,3])

        int maxVentana = sumaMaximaVentana(datos, k);
        System.out.println("Suma máxima (ventana):      " + maxVentana); // 9

        double promedio = promedioMaximoVentana(datos, k);
        System.out.printf("Promedio máximo (k=%d):      %.2f%n", k, promedio); // 3.00

        int[] sumas = sumasTodasVentanas(datos, k);
        System.out.println("Sumas de todas las ventanas: " + arrayToString(sumas));
        // Esperado: [8, 7, 9, 6]

        // Benchmark comparativo
        System.out.println("\n--- Benchmark ---");
        int[] grande = new int[1_000_000];
        for (int i = 0; i < grande.length; i++) grande[i] = (int)(Math.random() * 100);

        long t1 = System.nanoTime();
        sumaMaximaFuerzaBruta(grande, 1000);
        long t2 = System.nanoTime();
        System.out.printf("Fuerza bruta (1M, k=1000): %.2f ms%n", (t2 - t1) / 1e6);

        t1 = System.nanoTime();
        sumaMaximaVentana(grande, 1000);
        t2 = System.nanoTime();
        System.out.printf("Sliding Window (1M, k=1000): %.2f ms%n", (t2 - t1) / 1e6);

        System.out.println("\n=== FIN EJERCICIO 27 ===");
    }
}
