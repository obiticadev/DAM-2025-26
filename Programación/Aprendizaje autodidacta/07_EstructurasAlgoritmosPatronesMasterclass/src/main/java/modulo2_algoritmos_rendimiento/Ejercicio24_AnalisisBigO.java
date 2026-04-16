package modulo2_algoritmos_rendimiento;

/**
 * ============================================================================
 * EJERCICIO 24: Análisis Big O Práctico
 * ============================================================================
 * 📚 Teoría: teoria/02_Algoritmos_Rendimiento_BigO.md - Sección 2.1
 *
 * CONTEXTO:
 * Este ejercicio no es de implementar un algoritmo nuevo, sino de
 * ANALIZAR y CLASIFICAR la complejidad de diferentes fragmentos de código.
 * Cada método contiene un patrón algorítmico y tú debes:
 * 1. Ejecutarlo con diferentes tamaños de entrada.
 * 2. Medir el tiempo real.
 * 3. Determinar su complejidad Big O observando el crecimiento.
 *
 * Implementa los métodos que faltan y completa el análisis.
 *
 * RESTRICCIONES:
 * - No se busca optimizar los métodos, sino ENTENDER su complejidad.
 * - Cada patrón debe ejecutarse con n = 1000, 2000, 4000, 8000, 16000.
 * - Observar si el tiempo se duplica (lineal), cuadruplica (cuadrático),
 *   o crece mínimamente (logarítmico).
 * ============================================================================
 */
public class Ejercicio24_AnalisisBigO {

    // ---- PATRÓN A: ¿Cuál es su Big O? ----
    public static long patronA(int n) {
        long ops = 0;
        for (int i = 0; i < n; i++) {
            ops++;
        }
        return ops;
    }

    // ---- PATRÓN B: ¿Cuál es su Big O? ----
    public static long patronB(int n) {
        long ops = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ops++;
            }
        }
        return ops;
    }

    // ---- PATRÓN C: ¿Cuál es su Big O? ----
    public static long patronC(int n) {
        long ops = 0;
        int i = n;
        while (i > 0) {
            ops++;
            i = i / 2;
        }
        return ops;
    }

    // ---- PATRÓN D: ¿Cuál es su Big O? ----
    public static long patronD(int n) {
        long ops = 0;
        for (int i = 0; i < n; i++) {
            int j = n;
            while (j > 0) {
                ops++;
                j = j / 2;
            }
        }
        return ops;
    }

    // ---- PATRÓN E: ¿Cuál es su Big O? ----
    public static long patronE(int n) {
        // TODO 1: Implementar un patrón O(n³):
        //         Tres bucles anidados, cada uno recorriendo de 0 a n.
        //         Contar e incrementar 'ops' en el bucle más interno.
        //         Retornar ops.
        return 0;
    }

    public static void medirPatron(String nombre, int n) {
        // TODO 2: Medir el tiempo de ejecución del patrón indicado por 'nombre'.
        //         Usar System.nanoTime() antes y después de la llamada.
        //         Imprimir: "PatrónA  n=1000  ops=1000  tiempo=0.05ms"
        //         Según el nombre ("A", "B", "C", "D", "E"), invocar el método correcto.
    }

    public static void ejecutarBenchmark() {
        // TODO 3: Para cada patrón (A, B, C, D, E), ejecutar con
        //         n = 1000, 2000, 4000, 8000, 16000 (o ajustar para los
        //         cuadráticos/cúbicos que serían muy lentos con n grande).
        //         Imprimir una tabla formateada con los resultados.
        //         PISTA: Si al duplicar n el tiempo se duplica → O(n).
        //                Si al duplicar n el tiempo se cuadruplica → O(n²).
        //                Si al duplicar n el tiempo crece poco → O(log n).
    }

    public static void responderPreguntas() {
        // TODO 4: Imprimir tus conclusiones sobre la complejidad de cada patrón:
        //         System.out.println("Patrón A: O(???) porque ...");
        //         System.out.println("Patrón B: O(???) porque ...");
        //         System.out.println("Patrón C: O(???) porque ...");
        //         System.out.println("Patrón D: O(???) porque ...");
        //         System.out.println("Patrón E: O(???) porque ...");
        //         Justificar cada respuesta con la estructura de bucles.
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 24: Análisis Big O Práctico ===\n");

        // Mediciones rápidas para verificar
        System.out.println("--- Operaciones por patrón (n=1000) ---");
        System.out.println("Patrón A: " + patronA(1000) + " ops");
        System.out.println("Patrón B: " + patronB(1000) + " ops");
        System.out.println("Patrón C: " + patronC(1000) + " ops");
        System.out.println("Patrón D: " + patronD(1000) + " ops");
        System.out.println("Patrón E: " + patronE(1000) + " ops");

        System.out.println("\n--- Benchmark Completo ---");
        ejecutarBenchmark();

        System.out.println("\n--- Conclusiones ---");
        responderPreguntas();

        System.out.println("\n=== FIN EJERCICIO 24 ===");
    }
}
