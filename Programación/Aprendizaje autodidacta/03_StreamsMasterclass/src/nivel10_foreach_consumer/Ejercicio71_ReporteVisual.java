package nivel10_foreach_consumer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 71 — REPORTE VISUAL CON forEach + FORMATO AVANZADO
 * 
 * SIN GUÍA. Construye un informe visual completo usando streams + forEach.
 * Combina todo: filter, map, sorted, groupingBy, peek, forEach con method references.
 */
public class Ejercicio71_ReporteVisual {

    public static void imprimirBarra(double valor, double max) {
        int bloques = (int) ((valor / max) * 30);
        System.out.print("  ");
        for (int i = 0; i < bloques; i++) System.out.print("█");
        for (int i = bloques; i < 30; i++) System.out.print("░");
        System.out.printf(" %,.0f€%n", valor);
    }

    public static void demostracion() {
        System.out.println("=== EJERCICIO 71: REPORTE VISUAL ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 71: GENERA UN INFORME VISUAL ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));
        empresa.add(new Empleado("Sara Díaz", "Data", "Python", 4, 42000, true, "sara@corp.com"));
        empresa.add(new Empleado("Tomás Ruiz", "QA", "Java", 1, 25000, true, "tomas@corp.com"));

        int[] seccionesCompletadas = {0};

        // TODO 1: SECCIÓN "GRÁFICO DE BARRAS SALARIAL"
        // Para cada empleado activo (ordenado por salario DESC), imprime:
        //   "NOMBRE          " seguido de una barra visual usando imprimirBarra(salario, maxSalario).
        // Primero calcula el salario máximo de activos. Luego usa forEach con un lambda
        // que llame a imprimirBarra.
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║          GRÁFICO DE BARRAS SALARIAL             ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        // <- Escribe aquí
        // Al terminar: seccionesCompletadas[0]++;

        // TODO 2: SECCIÓN "RESUMEN POR DEPARTAMENTO"
        // Agrupa empleados activos por departamento.
        // Para cada grupo, usa forEach (sobre el entrySet) para imprimir:
        //   "DEPTO (N personas): nombre1, nombre2, ... — Media: X€"
        // Usa Collectors.joining para los nombres dentro de cada grupo.
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║          RESUMEN POR DEPARTAMENTO               ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        // <- Escribe aquí
        // Al terminar: seccionesCompletadas[0]++;

        // TODO 3: SECCIÓN "TOP 3 MEJOR PAGADOS"
        // Filtra activos, ordena por salario DESC, toma los 3 primeros.
        // Usa forEach con un Consumer que imprima medallas:
        //   🥇 para el 1º, 🥈 para el 2º, 🥉 para el 3º
        //   Formato: "MEDALLA nombre — salario€"
        // Usa un int[] contador para saber qué posición es.
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║              TOP 3 MEJOR PAGADOS                ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        int[] pos = {0};
        String[] medallas = {"\uD83E\uDD47", "\uD83E\uDD48", "\uD83E\uDD49"};
        // <- Escribe aquí
        // Al terminar: seccionesCompletadas[0]++;

        // --- VALIDACIÓN ---
        if (seccionesCompletadas[0] == 3) {
            System.out.println("\n>> CORRECTO: Reporte visual completo generado.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println("\n>> [ERROR] Completa las 3 secciones (incrementa seccionesCompletadas).");
        }
    }
}
