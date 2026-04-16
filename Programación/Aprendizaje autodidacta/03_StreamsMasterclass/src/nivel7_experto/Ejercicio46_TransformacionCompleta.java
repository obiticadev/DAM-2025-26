package nivel7_experto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 46 — RETO EXPERTO: TRANSFORMACIÓN COMPLETA DE DATOS
 * 
 * SIN PISTAS. Construye un resumen ejecutivo a partir de datos crudos.
 */
public class Ejercicio46_TransformacionCompleta {

    public static void demostracion() {
        System.out.println("=== RETO EXPERTO 46: TRANSFORMACIÓN COMPLETA ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 46: RESUMEN EJECUTIVO DE LA EMPRESA ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        // TODO 1: Crea un Map<String, String> donde:
        //   clave = departamento
        //   valor = "depto: nombre1, nombre2 (N empleados, media: X€)"
        //   Solo empleados activos.
        //   Ejemplo: "Backend: Ana García, Luis Pérez, Marcos Vila (3 empleados, media: 51666.67€)"
        Map<String, String> resumenPorDepto = null; // <- Escribe aquí

        // TODO 2: Lista de departamentos ordenados por salario medio DESCENDENTE
        List<String> deptosPorSalarioDesc = null; // <- Escribe aquí

        // TODO 3: String con formato "EMPRESA: N activos, N inactivos, presupuesto total: X€"
        String resumenGlobal = ""; // <- Escribe aquí

        System.out.println(resumenGlobal);

        // --- VALIDACIÓN ---
        boolean v1 = resumenPorDepto != null && resumenPorDepto.size() >= 4;
        boolean v2 = deptosPorSalarioDesc != null && deptosPorSalarioDesc.get(0).equals("Data");
        boolean v3 = resumenGlobal.contains("7 activos") && resumenGlobal.contains("1 inactivos");

        if (v1 && v2 && v3) {
            System.out.println(">> CORRECTO: Resumen ejecutivo generado con streams avanzados.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] resumen por depto con 4+ entradas, Data primero por salario, 7 activos 1 inactivo.");
        }
    }
}
