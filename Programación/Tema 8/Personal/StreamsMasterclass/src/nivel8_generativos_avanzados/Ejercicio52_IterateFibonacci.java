package nivel8_generativos_avanzados;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * EJERCICIO 52 — ITERATE AVANZADO: FIBONACCI Y SECUENCIAS (SIN GUÍA)
 * 
 * Objetivo: Dominar Stream.iterate con estructuras de estado (arrays).
 */
public class Ejercicio52_IterateFibonacci {

    public static void demostracion() {
        System.out.println("--- ITERATE AVANZADO ---");
        System.out.println("Stream.iterate puede usar arrays como 'estado' para generar secuencias complejas.");
        System.out.println("Ejemplo: Fibonacci usa un par [a, b] que avanza a [b, a+b] en cada paso.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 52: FIBONACCI Y SECUENCIAS COMPLEJAS ---");

        // TODO 1: Genera los primeros 12 números de Fibonacci: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89
        // Usa Stream.iterate con un array long[] como semilla.
        List<Long> fibonacci = null; // <- Escribe aquí

        // TODO 2: Genera la secuencia de factoriales del 1! al 10!
        // 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800
        // Usa Stream.iterate con un long[] = {1, 1} donde [índice, factorial]
        List<Long> factoriales = null; // <- Escribe aquí

        // TODO 3: Genera los primeros 8 números triangulares: 1, 3, 6, 10, 15, 21, 28, 36
        // Un número triangular T(n) = n*(n+1)/2 o equivalente: T(n) = T(n-1) + n
        List<Long> triangulares = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = fibonacci != null && fibonacci.size() == 12
                && fibonacci.get(0) == 0 && fibonacci.get(11) == 89;
        boolean v2 = factoriales != null && factoriales.size() == 10
                && factoriales.get(0) == 1 && factoriales.get(9) == 3628800L;
        boolean v3 = triangulares != null && triangulares.size() == 8
                && triangulares.get(0) == 1 && triangulares.get(7) == 36;

        if (v1 && v2 && v3) {
            System.out.println(">> CORRECTO: Secuencias matemáticas generadas con iterate.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] fib=[0..89](12), fact=[1..3628800](10), tri=[1..36](8).");
        }
    }
}
