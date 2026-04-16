package nivel3_method_references;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 21 — GROUPINGBY Y PARTITIONINGBY (CON GUÍA)
 * 
 * Objetivo: Agrupar datos con Collectors.groupingBy y partitioningBy.
 * Lee primero: teoria/06_Collectors_y_Recoleccion_Masiva.md
 */
public class Ejercicio21_GroupingByConGuia {

    public static void demostracion() {
        System.out.println("--- AGRUPACIÓN AUTOMÁTICA CON GROUPINGBY ---");
        System.out.println("Collectors.groupingBy(Empleado::getDepartamento) crea un Map<String, List<Empleado>>");
        System.out.println("donde la clave es el departamento y el valor es la lista de empleados de ese depto.");
        System.out.println("Collectors.partitioningBy(predicate) divide en Map<Boolean, List<T>>.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 21: AGRUPAR POR LENGUAJE Y PARTICIONAR POR SALARIO ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, "carlos@corp.com"));
        empresa.add(new Empleado("Marta", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro", "QA", "Python", 3, 30000, true, "pedro@corp.com"));

        // TODO 1: Agrupa los empleados por lenguaje principal usando groupingBy
        Map<String, List<Empleado>> porLenguaje = null; // <- Escribe aquí

        // TODO 2: Particiona los empleados en dos grupos: los que ganan > 40000 y los que no
        Map<Boolean, List<Empleado>> porSalario = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean agrupOk = porLenguaje != null && porLenguaje.size() == 3
                && porLenguaje.get("Java").size() == 2
                && porLenguaje.get("Python").size() == 2;
        boolean partOk = porSalario != null
                && porSalario.get(true).size() == 3
                && porSalario.get(false).size() == 3;

        if (agrupOk && partOk) {
            System.out.println(">> CORRECTO: Agrupación y partición ejecutadas correctamente.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] porLenguaje: Java=2, JavaScript=2, Python=2. porSalario: true=3(Ana,Marta,Lucía), false=3.");
        }
    }
}
