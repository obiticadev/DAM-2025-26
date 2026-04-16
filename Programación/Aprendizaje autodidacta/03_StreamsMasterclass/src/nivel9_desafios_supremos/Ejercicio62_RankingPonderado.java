package nivel9_desafios_supremos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 62 — SISTEMA DE RANKING CON CRITERIOS PONDERADOS
 * 
 * SIN PISTAS. Calcula puntuaciones compuestas y genera rankings complejos.
 */
public class Ejercicio62_RankingPonderado {

    public static void demostracion() {
        System.out.println("=== DESAFÍO SUPREMO 62: RANKING PONDERADO ===\n");
    }

    private static double calcularPuntuacion(Empleado e) {
        // Puntuación = (experiencia * 1000) + (salario * 0.5) + (activo ? 5000 : 0) + (email != null ? 2000 : 0)
        return (e.getExperienciaAnios() * 1000)
                + (e.getSalario() * 0.5)
                + (e.isActivo() ? 5000 : 0)
                + (e.getEmail() != null ? 2000 : 0);
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 62: RANKING PONDERADO DE EMPLEADOS ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        // TODO 1: Calcula la puntuación de cada empleado usando calcularPuntuacion().
        // Genera un ranking ordenado de MAYOR a MENOR puntuación.
        // Resultado: List<String> con formato "#1 Lucía Martín (52000.0 pts)"
        List<String> rankingGlobal = null; // <- Escribe aquí

        // TODO 2: Para cada departamento, obtén el empleado con MAYOR puntuación.
        // Resultado: Map<String, String> depto -> "nombre (X pts)"
        Map<String, String> mejorPorDepto = null; // <- Escribe aquí

        // TODO 3: Calcula la puntuación MEDIA por departamento y ordena de mayor a menor.
        // Resultado: List<String> con formato "DEPTO: media pts"
        List<String> mediaPorDepto = null; // <- Escribe aquí

        // TODO 4: ¿Cuántos empleados están por encima de la puntuación media global?
        long porEncimaMedia = 0; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = rankingGlobal != null && rankingGlobal.size() == 8
                && rankingGlobal.get(0).contains("Lucía Martín");
        boolean v2 = mejorPorDepto != null && mejorPorDepto.size() == 5
                && mejorPorDepto.get("Data").contains("Lucía");
        boolean v3 = mediaPorDepto != null && mediaPorDepto.size() == 5;
        double mediaGlobal = empresa.stream()
                .mapToDouble(e -> calcularPuntuacion(e)).average().orElse(0);
        long expected = empresa.stream()
                .filter(e -> calcularPuntuacion(e) > mediaGlobal).count();
        boolean v4 = porEncimaMedia == expected;

        if (v1 && v2 && v3 && v4) {
            System.out.println(">> CORRECTO: Rankings ponderados calculados.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Ranking global con Lucía #1, mejor por depto, media por depto ordenada.");
        }
    }
}
