package nivel8_generativos_avanzados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 53 — DOWNSTREAM COLLECTORS AVANZADOS (CON GUÍA)
 * 
 * Objetivo: Usar collectors dentro de groupingBy para obtener resúmenes potentes.
 * Lee la teoría: teoria/08_Downstream_Collectors.md
 */
public class Ejercicio53_DownstreamCollectors {

    public static void demostracion() {
        System.out.println("--- DOWNSTREAM COLLECTORS ---");
        System.out.println("groupingBy acepta un SEGUNDO argumento: el downstream collector.");
        System.out.println("  groupingBy(clasificador, counting())       -> cuenta por grupo");
        System.out.println("  groupingBy(clasificador, summingDouble(f)) -> suma por grupo");
        System.out.println("  groupingBy(clasificador, maxBy(comp))      -> máximo por grupo");
        System.out.println("  groupingBy(clasificador, mapping(f, toList())) -> transforma y recoge\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 53: ANALÍTICA POR DEPARTAMENTO ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro", "Backend", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena", "Data", "Python", 6, 52000, true, "elena@corp.com"));

        // TODO 1: Cuenta cuántos empleados activos hay en cada departamento.
        // Resultado: Map<String, Long>
        Map<String, Long> conteoPorDepto = null; // <- Escribe aquí

        // TODO 2: Obtén el salario medio de los empleados activos por departamento.
        // Resultado: Map<String, Double>
        Map<String, Double> mediaPorDepto = null; // <- Escribe aquí

        // TODO 3: Obtén el nombre del empleado activo mejor pagado de cada departamento.
        // Resultado: Map<String, Optional<String>> (usa maxBy + mapping o collectingAndThen)
        Map<String, Optional<Empleado>> mejorPorDepto = null; // <- Escribe aquí

        // TODO 4: Obtén los nombres de empleados activos por departamento, unidos por ", ".
        // Resultado: Map<String, String>  Ej: "Backend" -> "Ana, Luis"
        Map<String, String> nombresPorDepto = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = conteoPorDepto != null && conteoPorDepto.get("Backend") == 2L;
        boolean v2 = mediaPorDepto != null && mediaPorDepto.get("Data") == 61000.0;
        boolean v3 = mejorPorDepto != null
                && mejorPorDepto.get("Backend").isPresent()
                && mejorPorDepto.get("Backend").get().getNombre().equals("Ana");
        boolean v4 = nombresPorDepto != null && nombresPorDepto.get("Frontend").contains("Marta");

        if (v1 && v2 && v3 && v4) {
            System.out.println(">> CORRECTO: Downstream collectors dominados.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Backend=2 activos, media Data=61000, mejor Backend=Ana, Frontend contiene Marta.");
        }
    }
}
