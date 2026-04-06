package nivel10_foreach_consumer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 70 — COLLECTORS.collectingAndThen()
 * 
 * Aprende a post-procesar el resultado de un Collector con una transformación final.
 * Lee la teoría: teoria/09_ForEach_Consumer_Avanzado.md (sección 9.6)
 * 
 * CONCEPTO CLAVE:
 *   Collectors.collectingAndThen(collector, transformacionFinal)
 *   Ejemplo: collectingAndThen(toList(), Collections::unmodifiableList)
 */
public class Ejercicio70_CollectingAndThen {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 70: collectingAndThen ===\n");

        List<Empleado> demo = new ArrayList<>();
        demo.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        demo.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        demo.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));

        // DEMO: Obtener lista inmutable
        List<String> inmutable = demo.stream()
                .map(Empleado::getNombre)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Collections::unmodifiableList));

        System.out.println("Lista inmutable: " + inmutable);
        try {
            inmutable.add("Test");
        } catch (UnsupportedOperationException e) {
            System.out.println(">> No se puede modificar: " + e.getClass().getSimpleName());
        }

        // DEMO: groupingBy + collectingAndThen para extraer el mejor de cada grupo
        Map<String, Empleado> mejorPorDepto = demo.stream()
                .collect(Collectors.groupingBy(
                        Empleado::getDepartamento,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Empleado::getSalario)),
                                Optional::get)));

        mejorPorDepto.forEach((d, e) ->
                System.out.println("Mejor de " + d + ": " + e.getNombre()));

        System.out.println("\nAhora completa los TODOs.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 70: PRACTICA collectingAndThen ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        // TODO 1: Recoge los nombres de todos los empleados en una lista INMUTABLE
        // usando collectingAndThen(toList(), Collections::unmodifiableList).
        List<String> nombresInmutables = null; // <- Escribe aquí

        // TODO 2: Agrupa por departamento y obtén el empleado con MAYOR salario de cada uno.
        // Usa groupingBy + collectingAndThen(maxBy(...), Optional::get).
        // Resultado: Map<String, Empleado>
        Map<String, Empleado> mejorPorDepto = null; // <- Escribe aquí

        // TODO 3: Agrupa por departamento y obtén el resultado como TreeMap (ordenado por clave).
        // Usa collectingAndThen(groupingBy(...), TreeMap::new).
        // Muestra cada entrada con forEach: depto -> [nombres separados por ", "]
        TreeMap<String, List<Empleado>> porDeptoOrdenado = null; // <- Escribe aquí

        // TODO 4: Recoge los salarios en una lista y usa collectingAndThen para
        // devolver directamente el salario MÁXIMO como Double (no Optional).
        // Pista: collectingAndThen(maxBy(...), Optional::get)
        double salarioMax = 0; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = nombresInmutables != null && nombresInmutables.size() == 7;
        boolean inmutable = false;
        try {
            if (nombresInmutables != null) nombresInmutables.add("test");
        } catch (UnsupportedOperationException e) {
            inmutable = true;
        }
        boolean v2 = mejorPorDepto != null && mejorPorDepto.get("Backend").getNombre().equals("Marcos Vila");
        boolean v3 = porDeptoOrdenado != null && porDeptoOrdenado.firstKey().equals("Backend");
        boolean v4 = salarioMax == 70000.0;

        if (v1 && inmutable && v2 && v3 && v4) {
            System.out.println("\n>> CORRECTO: collectingAndThen dominado.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println("\n>> [ERROR] lista inmutable=7, mejorBackend=Marcos, TreeMap, maxSalario=70000.");
        }
    }
}
