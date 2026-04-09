package nivel10_foreach_consumer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 68 — FORMATEO DE TABLAS CON forEach Y METHOD REFERENCES
 * 
 * Aprende a pintar tablas bonitas usando streams + forEach + printf.
 * Lee la teoría: teoria/09_ForEach_Consumer_Avanzado.md (sección 9.4)
 * 
 * CONCEPTO CLAVE:
 *   System.out.printf("| %-15s | %,8.0f€ |%n", nombre, salario);
 *     %-15s  → String alineado a la izquierda, 15 caracteres
 *     %,8.0f → número con separador de miles, 8 caracteres, 0 decimales
 *     %n     → salto de línea
 */
public class Ejercicio68_TablaFormateada {

    public static void imprimirFila(Empleado e) {
        System.out.printf("│ %-18s │ %-10s │ %-10s │ %3d años │ %,9.0f€ │%n",
                e.getNombre(), e.getDepartamento(), e.getLenguajePrincipal(),
                e.getExperienciaAnios(), e.getSalario());
    }

    private static void imprimirSeparador() {
        System.out.println("├" + "─".repeat(20) + "┼" + "─".repeat(12) + "┼"
                + "─".repeat(12) + "┼" + "─".repeat(10) + "┼" + "─".repeat(11) + "┤");
    }

    public static void demostracion() {
        System.out.println("=== EJERCICIO 68: TABLAS FORMATEADAS ===\n");

        List<Empleado> demo = new ArrayList<>();
        demo.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        demo.add(new Empleado("Luis Pérez", "QA", "Java", 3, 35000, true, "luis@corp.com"));

        // DEMO: Tabla completa con método estático como method reference
        System.out.println("┌" + "─".repeat(20) + "┬" + "─".repeat(12) + "┬"
                + "─".repeat(12) + "┬" + "─".repeat(10) + "┬" + "─".repeat(11) + "┐");
        System.out.printf("│ %-18s │ %-10s │ %-10s │ %8s │ %9s │%n",
                "NOMBRE", "DEPTO", "LENGUAJE", "EXP", "SALARIO");
        imprimirSeparador();
        demo.stream().forEach(Ejercicio68_TablaFormateada::imprimirFila);
        System.out.println("└" + "─".repeat(20) + "┴" + "─".repeat(12) + "┴"
                + "─".repeat(12) + "┴" + "─".repeat(10) + "┴" + "─".repeat(11) + "┘");

        System.out.println("\nAhora completa los TODOs.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 68: PINTA TABLAS PROFESIONALES ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        List<String> nombresImpresos = new ArrayList<>();

        // TODO 1: Pinta una tabla con TODOS los empleados ACTIVOS ordenados por salario DESC.
        // Usa el método imprimirFila como method reference en forEach.
        // Incluye cabecera y pie de tabla (copia el formato de la demo).
        // Además, usa peek antes del forEach para añadir los nombres a nombresImpresos.
        System.out.println("\n[TABLA: Empleados activos por salario]");
        // <- Escribe aquí (cabecera + stream con forEach + pie)

        // TODO 2: Debajo de la tabla, imprime una línea de resumen usando streams:
        // "Total: N empleados | Masa salarial: X€ | Media: Y€"
        // Calcula N, X, Y usando streams sobre los empleados activos.
        // <- Escribe aquí

        // TODO 3: Pinta una SEGUNDA tabla solo con empleados de "Backend",
        // esta vez usando un lambda en forEach que imprima con formato:
        // "  [N] NOMBRE ─── salario€ (lenguaje)"
        // donde N es un contador que empieza en 1 (usa un int[] para mutarlo en el lambda).
        System.out.println("\n[LISTA: Equipo Backend]");
        int[] idx = {0};
        // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = nombresImpresos.size() == 7 && nombresImpresos.get(0).equals("Lucía Martín");
        boolean v2 = idx[0] == 3; // 3 empleados de Backend (Ana, Luis, Marcos)

        if (v1 && v2) {
            System.out.println("\n>> CORRECTO: Tablas formateadas con forEach.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println("\n>> [ERROR] 7 activos impresos (Lucía primero), 3 en Backend.");
        }
    }
}
