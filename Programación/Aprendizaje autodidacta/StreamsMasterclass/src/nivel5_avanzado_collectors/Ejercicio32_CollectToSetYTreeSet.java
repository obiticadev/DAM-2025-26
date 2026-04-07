package nivel5_avanzado_collectors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 32 — COLLECT A SET Y TREESET (SIN GUÍA)
 * 
 * Objetivo: Recolectar en Set (sin duplicados) y TreeSet (ordenado automáticamente).
 */
public class Ejercicio32_CollectToSetYTreeSet {

    public static void demostracion() {
        System.out.println("--- COLLECTORS: TOSET Y TOCOLLECTION ---");
        System.out.println("Collectors.toSet()                          -> HashSet (sin orden, sin duplicados).");
        System.out.println("Collectors.toCollection(TreeSet::new)       -> TreeSet (ordenado por Comparable).");
        System.out.println("Collectors.toCollection(() -> new TreeSet<>(comparator)) -> TreeSet con orden custom.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 32: DEPARTAMENTOS ÚNICOS Y EMPLEADOS ORDENADOS EN TREESET ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, "carlos@corp.com"));
        empresa.add(new Empleado("Marta", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));

        // TODO 1: Extrae los departamentos únicos en un Set<String>.
        Set<String> departamentos = null; // <- Escribe aquí

        // TODO 2: Recolecta los empleados en un TreeSet que los ordene por nombre A-Z.
        // Necesitas Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Empleado::getNombre)))
        TreeSet<Empleado> ordenados = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = departamentos != null && departamentos.size() == 3
                && departamentos.contains("Backend") && departamentos.contains("Frontend");
        boolean v2 = ordenados != null && ordenados.size() == 5
                && ordenados.first().getNombre().equals("Ana")
                && ordenados.last().getNombre().equals("Marta");

        if (v1 && v2) {
            System.out.println(">> CORRECTO: Set sin duplicados y TreeSet con orden personalizado.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] departamentos={Backend,Frontend,Data}. TreeSet ordenado: Ana...Marta.");
        }
    }
}
