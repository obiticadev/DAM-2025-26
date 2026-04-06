package nivel10_foreach_consumer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 66 — forEach CON METHOD REFERENCES
 * 
 * Aprende a usar forEach como operación terminal con method references.
 * Lee la teoría: teoria/09_ForEach_Consumer_Avanzado.md (secciones 9.1 y 9.2)
 * 
 * CONCEPTO CLAVE:
 *   .forEach(System.out::println)   equivale a   .forEach(x -> System.out.println(x))
 *   .forEach(Empleado::presentarse) equivale a   .forEach(e -> e.presentarse())
 */
public class Ejercicio66_ForEachMethodReference {

    // Método estático que imprime un empleado formateado
    public static void imprimirEmpleado(Empleado e) {
        System.out.printf("  → %-18s | %-10s | %,8.0f€%n",
                e.getNombre(), e.getDepartamento(), e.getSalario());
    }

    public static void demostracion() {
        System.out.println("=== EJERCICIO 66: forEach CON METHOD REFERENCES ===\n");

        List<Empleado> demo = new ArrayList<>();
        demo.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        demo.add(new Empleado("Luis Pérez", "QA", "Java", 3, 35000, true, "luis@corp.com"));

        // DEMO 1: forEach con System.out::println (referencia a instancia concreta)
        System.out.println("--- forEach con System.out::println ---");
        demo.stream()
                .map(Empleado::getNombre)
                .forEach(System.out::println);

        // DEMO 2: forEach con method reference a método estático propio
        System.out.println("\n--- forEach con método estático propio ---");
        demo.stream()
                .forEach(Ejercicio66_ForEachMethodReference::imprimirEmpleado);

        System.out.println("\nAhora completa los TODOs del ejercicio.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 66: PRACTICA forEach + METHOD REFERENCES ---");
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empleados.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empleados.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empleados.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empleados.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empleados.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));

        List<String> resultados = new ArrayList<>();

        // TODO 1: Usa un stream para filtrar los activos, ordenarlos por salario DESC,
        // obtener sus nombres con map(Empleado::getNombre),
        // y pintarlos con .forEach(System.out::println).
        // Además, recoge los nombres en la lista "resultados" ANTES del forEach
        // (pista: puedes usar peek para añadir a resultados).
        System.out.println("\n[Activos ordenados por salario DESC]");
        // <- Escribe aquí

        // TODO 2: Usa forEach con el método estático imprimirEmpleado de esta clase
        // para pintar una tabla de empleados activos senior (experiencia >= 5).
        // Usa method reference: Ejercicio66_ForEachMethodReference::imprimirEmpleado
        System.out.println("\n[Tabla de seniors activos]");
        System.out.printf("  %-20s | %-10s | %9s%n", "NOMBRE", "DEPTO", "SALARIO");
        System.out.println("  " + "-".repeat(20) + "-+-" + "-".repeat(10) + "-+-" + "-".repeat(9));
        // <- Escribe aquí

        // TODO 3: Usa map para transformar cada empleado activo a un String con formato
        // "NOMBRE (DEPTO)" donde NOMBRE está en mayúsculas, luego píntalos con forEach.
        // Usa String::toUpperCase como parte de la transformación si puedes.
        System.out.println("\n[Empleados en MAYÚSCULAS (DEPTO)]");
        // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = resultados.size() == 5 && resultados.get(0).equals("Lucía Martín");
        if (v1) {
            System.out.println("\n>> CORRECTO: forEach con method references dominado.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println("\n>> [ERROR] resultados debe tener 5 activos, primero Lucía Martín.");
        }
    }
}
