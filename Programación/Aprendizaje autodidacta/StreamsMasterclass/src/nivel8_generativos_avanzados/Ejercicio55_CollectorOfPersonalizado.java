package nivel8_generativos_avanzados;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 55 — COLLECTOR.OF: CREA TU PROPIO COLLECTOR (CON GUÍA)
 * 
 * Objetivo: Construir un Collector personalizado con Collector.of().
 */
public class Ejercicio55_CollectorOfPersonalizado {

    public static void demostracion() {
        System.out.println("--- COLLECTOR.OF(): CONSTRUCTOR DE COLLECTORS ---");
        System.out.println("Collector.of(supplier, accumulator, combiner, finisher)");
        System.out.println("  supplier:    () -> crea el contenedor mutable");
        System.out.println("  accumulator: (contenedor, elemento) -> añade al contenedor");
        System.out.println("  combiner:    (c1, c2) -> fusiona dos contenedores");
        System.out.println("  finisher:    contenedor -> resultado final\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 55: COLLECTORS PERSONALIZADOS ---");
        List<Empleado> equipo = new ArrayList<>();
        equipo.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        equipo.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        equipo.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        equipo.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        equipo.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));

        // TODO 1: Crea un Collector personalizado que genere un String con formato:
        // "EQUIPO[Ana García | Carlos Ruiz | Lucía Martín | Elena Torres]"
        // Solo empleados activos, separados por " | ", envueltos en "EQUIPO[...]"
        // Usa Collector.of() con StringJoiner como contenedor mutable.
        String equipoStr = ""; // <- Escribe aquí

        // TODO 2: Crea un Collector que calcule la diferencia entre el salario máximo y mínimo
        // de los empleados activos. Usa un double[] de tamaño 2 como contenedor [min, max].
        double rangoSalarial = 0; // <- Escribe aquí

        // TODO 3: Crea un Collector que cuente cuántos empleados tienen email no nulo
        // y devuelva el porcentaje sobre el total de activos.
        // Usa un int[] de tamaño 2 como contenedor [conEmail, total].
        double porcentajeConEmail = 0; // <- Escribe aquí

        System.out.println("Equipo: " + equipoStr);
        System.out.println("Rango salarial: " + rangoSalarial);
        System.out.printf("Con email: %.1f%%\n", porcentajeConEmail);

        // --- VALIDACIÓN ---
        boolean v1 = equipoStr.startsWith("EQUIPO[") && equipoStr.endsWith("]")
                && equipoStr.contains("Ana García") && equipoStr.contains("Elena Torres")
                && !equipoStr.contains("Pedro");
        boolean v2 = rangoSalarial == 38000.0; // 70000 - 32000
        boolean v3 = porcentajeConEmail == 75.0; // 3 de 4 activos tienen email

        if (v1 && v2 && v3) {
            System.out.println(">> CORRECTO: Collectors personalizados creados con Collector.of().\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] EQUIPO[4 activos separados por |], rango=38000, %email=75.0.");
        }
    }
}
