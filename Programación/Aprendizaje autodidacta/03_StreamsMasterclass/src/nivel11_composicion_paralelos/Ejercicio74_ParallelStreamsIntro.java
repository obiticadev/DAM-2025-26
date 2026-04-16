package nivel11_composicion_paralelos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import modelos.Empleado;

/**
 * EJERCICIO 74 — PARALLEL STREAMS: INTRODUCCIÓN
 * 
 * Aprende a usar parallelStream() y .parallel() para procesar datos en paralelo.
 * Lee la teoría: teoria/10_Composicion_Funcional_Paralelos.md (secciones 10.3 y 10.5)
 * 
 * CONCEPTO CLAVE:
 *   lista.parallelStream()    → stream paralelo directo
 *   stream.parallel()         → convierte stream secuencial a paralelo
 *   forEachOrdered(...)       → mantiene el orden en parallel
 */
public class Ejercicio74_ParallelStreamsIntro {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 74: PARALLEL STREAMS ===\n");

        // DEMO 1: forEach en parallel NO garantiza orden
        System.out.println("--- parallel + forEach (orden NO garantizado) ---");
        List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).parallelStream()
                .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // DEMO 2: forEachOrdered SÍ garantiza orden
        System.out.println("\n--- parallel + forEachOrdered (orden SÍ garantizado) ---");
        List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).parallelStream()
                .forEachOrdered(n -> System.out.print(n + " "));
        System.out.println();

        // DEMO 3: Medición de rendimiento
        System.out.println("\n--- Comparativa secuencial vs paralelo ---");
        long inicio = System.currentTimeMillis();
        long countSeq = IntStream.rangeClosed(1, 5_000_000)
                .filter(n -> esPrimo(n)).count();
        long finSeq = System.currentTimeMillis();

        long inicio2 = System.currentTimeMillis();
        long countPar = IntStream.rangeClosed(1, 5_000_000)
                .parallel()
                .filter(n -> esPrimo(n)).count();
        long finPar = System.currentTimeMillis();

        System.out.println("Secuencial: " + countSeq + " primos en " + (finSeq - inicio) + "ms");
        System.out.println("Paralelo:   " + countPar + " primos en " + (finPar - inicio2) + "ms");

        System.out.println("\nAhora completa los TODOs.\n");
    }

    private static boolean esPrimo(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 74: PRACTICA PARALLEL STREAMS ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        // TODO 1: Usa parallelStream para filtrar empleados activos y recoger sus nombres
        // en una lista. Usa forEachOrdered para imprimirlos en orden.
        List<String> nombresActivos = null; // <- Escribe aquí con parallelStream
        System.out.println("[Activos (parallel + forEachOrdered)]");
        // <- Imprime con forEachOrdered aquí

        // TODO 2: Usa IntStream.rangeClosed(1, 1_000_000).parallel() para contar
        // cuántos números son primos. Usa el método esPrimo de esta clase.
        long totalPrimos = 0; // <- Escribe aquí
        System.out.println("\nPrimos hasta 1.000.000: " + totalPrimos);

        // TODO 3: Calcula la suma de salarios de empleados activos usando
        // parallelStream + mapToDouble + sum.
        double sumaSalarios = 0; // <- Escribe aquí
        System.out.println("Suma salarios (parallel): " + sumaSalarios + "€");

        // TODO 4: Demuestra la diferencia entre forEach y forEachOrdered en parallel.
        // Imprime los números 1..20 con parallelStream().forEach (verás desorden).
        // Luego imprime 1..20 con parallelStream().forEachOrdered (verás orden).
        System.out.println("\n[forEach parallel — posible desorden]");
        // <- Escribe aquí
        System.out.println("\n[forEachOrdered parallel — orden garantizado]");
        // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = nombresActivos != null && nombresActivos.size() == 7;
        boolean v2 = totalPrimos == 78498; // primos hasta 1M
        boolean v3 = sumaSalarios == 353000.0;

        if (v1 && v2 && v3) {
            System.out.println("\n>> CORRECTO: Parallel streams dominados.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println("\n>> [ERROR] 7 activos, 78498 primos, suma=353000.");
        }
    }
}
