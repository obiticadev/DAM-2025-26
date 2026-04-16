package nivel9_desafios_supremos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 59 — SIMULADOR DE CONSULTAS SQL CON STREAMS
 * 
 * SIN PISTAS. Traduce consultas SQL mentalmente a pipelines de streams.
 */
public class Ejercicio59_SimuladorSQL {

    public static void demostracion() {
        System.out.println("=== DESAFÍO SUPREMO 59: SQL CON STREAMS ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 59: TRADUCE SQL A STREAMS ---");
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empleados.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empleados.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empleados.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empleados.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empleados.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empleados.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empleados.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));
        empleados.add(new Empleado("Sara Díaz", "Data", "Python", 4, 42000, true, "sara@corp.com"));
        empleados.add(new Empleado("Tomás Ruiz", "QA", "Java", 1, 25000, true, "tomas@corp.com"));

        // CONSULTA 1: SELECT nombre FROM empleados WHERE activo = true AND salario > 45000
        //             ORDER BY salario DESC LIMIT 3
        List<String> consulta1 = null; // <- Escribe aquí

        // CONSULTA 2: SELECT departamento, AVG(salario) FROM empleados
        //             WHERE activo = true GROUP BY departamento HAVING AVG(salario) > 40000
        Map<String, Double> consulta2 = null; // <- Escribe aquí

        // CONSULTA 3: SELECT departamento, COUNT(*) AS total FROM empleados
        //             WHERE activo = true GROUP BY departamento ORDER BY total DESC
        //             (devuelve List<String> con formato "depto: N")
        List<String> consulta3 = null; // <- Escribe aquí

        // CONSULTA 4: SELECT DISTINCT lenguaje_principal FROM empleados
        //             WHERE activo = true ORDER BY lenguaje_principal ASC
        List<String> consulta4 = null; // <- Escribe aquí

        // CONSULTA 5: SELECT nombre, salario FROM empleados WHERE activo = true
        //             AND salario > (SELECT AVG(salario) FROM empleados WHERE activo = true)
        //             ORDER BY nombre ASC
        // Devuelve List<String> con formato "nombre: salario€"
        List<String> consulta5 = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = consulta1 != null && consulta1.size() == 3
                && consulta1.get(0).equals("Lucía Martín");
        boolean v2 = consulta2 != null && !consulta2.containsKey("QA");
        boolean v3 = consulta3 != null && consulta3.get(0).startsWith("Backend");
        boolean v4 = consulta4 != null && consulta4.size() == 5
                && consulta4.get(0).equals("Go");
        double mediaActivos = empleados.stream().filter(Empleado::isActivo)
                .mapToDouble(Empleado::getSalario).average().orElse(0);
        long porEncima = empleados.stream().filter(Empleado::isActivo)
                .filter(e -> e.getSalario() > mediaActivos).count();
        boolean v5 = consulta5 != null && consulta5.size() == porEncima;

        if (v1 && v2 && v3 && v4 && v5) {
            System.out.println(">> CORRECTO: 5 consultas SQL traducidas a Streams.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa cada consulta SQL y tradúcela fielmente a streams.");
        }
    }
}
