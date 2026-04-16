package nivel5_avanzado_collectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 31 — TOMAP CON FUNCIÓN DE MERGE (CON GUÍA)
 * 
 * Objetivo: Resolver conflictos de claves duplicadas en Collectors.toMap().
 */
public class Ejercicio31_ToMapConMerge {

    public static void demostracion() {
        System.out.println("--- TOMAP: ¿QUÉ PASA CUANDO HAY CLAVES DUPLICADAS? ---");
        System.out.println("Si dos empleados comparten departamento, toMap(getDepartamento, getSalario) falla.");
        System.out.println("Solución: tercer argumento -> función de merge.");
        System.out.println("  Collectors.toMap(keyMapper, valueMapper, (v1, v2) -> v1 + v2)");
        System.out.println("  (v1, v2) -> v1         -> Se queda con el primero");
        System.out.println("  (v1, v2) -> v1 + v2    -> Suma ambos");
        System.out.println("  (v1, v2) -> Math.max(v1, v2) -> Se queda con el mayor\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 31: SALARIO MÁXIMO POR DEPARTAMENTO CON TOMAP ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, "carlos@corp.com"));
        empresa.add(new Empleado("Marta", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));

        // TODO 1: Crea un Map<String, Double> donde la clave sea el departamento
        // y el valor sea el SALARIO MÁXIMO de ese departamento.
        Map<String, Double> maxSalarioPorDepto = null; // <- Escribe aquí

        // TODO 2: Crea un Map<String, Double> con la SUMA de salarios por departamento.
        Map<String, Double> sumaSalarioPorDepto = null; // <- Escribe aquí usando (v1,v2) -> v1+v2

        // --- VALIDACIÓN ---
        boolean v1 = maxSalarioPorDepto != null
                && maxSalarioPorDepto.get("Backend") == 55000.0
                && maxSalarioPorDepto.get("Frontend") == 48000.0;
        boolean v2 = sumaSalarioPorDepto != null
                && sumaSalarioPorDepto.get("Backend") == 90000.0
                && sumaSalarioPorDepto.get("Data") == 70000.0;

        if (v1 && v2) {
            System.out.println(">> CORRECTO: toMap con merge function dominado.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] maxSalario: Backend=55000, Frontend=48000. sumaSalario: Backend=90000, Data=70000.");
        }
    }
}
