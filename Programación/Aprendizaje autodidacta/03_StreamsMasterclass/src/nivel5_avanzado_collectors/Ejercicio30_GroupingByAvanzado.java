package nivel5_avanzado_collectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 30 — GROUPINGBY CON DOWNSTREAM COLLECTOR (SIN GUÍA)
 * 
 * Objetivo: Usar groupingBy con un segundo collector para transformar los grupos.
 */
public class Ejercicio30_GroupingByAvanzado {

    public static void demostracion() {
        System.out.println("--- GROUPINGBY + DOWNSTREAM ---");
        System.out.println("groupingBy acepta un segundo argumento: otro Collector que se aplica a cada grupo.");
        System.out.println("Ejemplos:");
        System.out.println("  groupingBy(campo, Collectors.counting())             -> Map<K, Long>");
        System.out.println("  groupingBy(campo, Collectors.averagingDouble(f))     -> Map<K, Double>");
        System.out.println("  groupingBy(campo, Collectors.mapping(f, toList()))   -> Map<K, List<R>>");
        System.out.println("  groupingBy(campo, Collectors.summarizingDouble(f))   -> Map<K, DoubleSummaryStatistics>\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 30: ESTADÍSTICAS POR DEPARTAMENTO ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, "carlos@corp.com"));
        empresa.add(new Empleado("Marta", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));

        // TODO 1: Crea un Map<String, Double> con la media salarial por departamento.
        // Usa groupingBy con Collectors.averagingDouble como downstream.
        Map<String, Double> mediaPorDepto = null; // <- Escribe aquí

        // TODO 2: Crea un Map<String, List<String>> con los nombres de empleados agrupados por depto.
        // Usa groupingBy con Collectors.mapping(Empleado::getNombre, Collectors.toList())
        Map<String, List<String>> nombresPorDepto = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = mediaPorDepto != null
                && mediaPorDepto.get("Backend") == 45000.0
                && mediaPorDepto.get("Frontend") == 40000.0
                && mediaPorDepto.get("Data") == 70000.0;
        boolean v2 = nombresPorDepto != null
                && nombresPorDepto.get("Backend").size() == 2
                && nombresPorDepto.get("Backend").contains("Ana");

        if (v1 && v2) {
            System.out.println(">> CORRECTO: groupingBy con downstream collectors avanzados.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] media: Backend=45000, Frontend=40000, Data=70000. nombres: Backend=[Ana,Luis], etc.");
        }
    }
}
