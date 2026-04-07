package nivel2_streams_basicos;

import java.util.ArrayList;
import java.util.List;
import modelos.Empleado;

/**
 * EJERCICIO 09 — PRIMER STREAM: FILTER + COLLECT (CON GUÍA)
 * 
 * Objetivo: Crear tu primera tubería Stream con .stream(), .filter(),
 * .collect()
 * Lee primero: teoria/04_Streams_Profundizacion.md
 */
public class Ejercicio09_PrimerStream {

    public static void demostracion() {
        System.out.println("--- TU PRIMERA TUBERÍA STREAM ---");
        System.out.println("Un Stream tiene 3 fases: ORIGEN -> INTERMEDIAS -> TERMINAL");
        System.out.println("  1. Origen:     lista.stream()");
        System.out.println("  2. Intermedia: .filter(e -> condicion)");
        System.out.println("  3. Terminal:   .collect(Collectors.toList())");
        System.out.println("El Stream NO modifica la lista original. Crea una nueva.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 09: FILTRAR EMPLEADOS ACTIVOS ---");
        List<Empleado> plantilla = new ArrayList<>();
        plantilla.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        plantilla.add(new Empleado("Carlos", "Frontend", "JavaScript", 1, 25000, false, null));
        plantilla.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        plantilla.add(new Empleado("Pedro", "QA", "Java", 3, 30000, false, "pedro@corp.com"));
        plantilla.add(new Empleado("Elena", "DevOps", "Go", 4, 45000, true, "elena@corp.com"));

        // TODO: Crea un Stream desde 'plantilla', filtra SOLO los empleados activos,
        // y recógelos en una nueva List<Empleado>.
        // Recuerda la estructura: origen.stream().intermedia(...).terminal()

        List<Empleado> activos = plantilla.stream()
                .filter(Empleado::isActivo)
                .peek(System.out::println)
                .toList();

        // --- VALIDACIÓN ---
        if (activos != null && activos.size() == 3
                && activos.stream().allMatch(Empleado::isActivo)) {
            System.out.println(">> CORRECTO: Has creado tu primera tubería Stream con filtrado.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Deberían quedar 3 empleados activos: Ana, Lucía, Elena.");
        }
    }
}
