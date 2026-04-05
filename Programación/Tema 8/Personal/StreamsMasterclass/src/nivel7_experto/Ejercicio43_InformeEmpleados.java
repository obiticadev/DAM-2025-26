package nivel7_experto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 43 — RETO EXPERTO: INFORME COMPLETO DE RRHH
 * 
 * SIN PISTAS. Combina todo lo aprendido.
 */
public class Ejercicio43_InformeEmpleados {

    public static void demostracion() {
        System.out.println("=== RETO EXPERTO 43: INFORME COMPLETO DE RRHH ===");
        System.out.println("A partir de aquí, no hay pistas. Solo la descripción y la validación.");
        System.out.println("Demuestra todo lo que has aprendido.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 43: GENERAR UN INFORME COMPLETO ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        // TODO 1: Nombre del empleado activo mejor pagado del departamento "Backend"
        String mejorBackend = ""; // <- Escribe aquí

        // TODO 2: Media salarial de empleados activos
        double mediaActivos = 0; // <- Escribe aquí

        // TODO 3: Mapa departamento -> lista de nombres, solo empleados activos, ordenados A-Z dentro de cada grupo
        Map<String, List<String>> directorio = null; // <- Escribe aquí

        // TODO 4: String con todos los emails válidos (no null) separados por "; "
        String listaEmails = ""; // <- Escribe aquí

        // TODO 5: Total de años de experiencia combinados de todos los empleados activos
        int experienciaTotal = 0; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = mejorBackend.equals("Marcos Vila");
        boolean v2 = Math.abs(mediaActivos - 50428.57) < 1;
        boolean v3 = directorio != null && directorio.get("Backend").size() == 3
                && directorio.get("Backend").get(0).equals("Ana García");
        boolean v4 = listaEmails.contains("ana@corp.com") && listaEmails.contains("marcos@corp.com")
                && !listaEmails.contains("null");
        boolean v5 = experienciaTotal == 41;

        if (v1 && v2 && v3 && v4 && v5) {
            System.out.println(">> CORRECTO: Informe RRHH completo generado con streams.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] mejorBackend=Marcos Vila, media~50428.57, directorio con listas ordenadas, emails sin null, exp=41.");
        }
    }
}
