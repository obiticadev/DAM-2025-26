package nivel14_master_streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * EJERCICIO 46 - COLLECTORS.TEEING (Java 12)
 * 
 * Objetivo: Entender cómo "bifurcar" un Stream de datos en dos colectores
 * independientes (como Mapas) y fusionarlos al vuelo en un solo paso.
 */
public class Ejercicio46_Teeing {

    // Record auxiliar
    record Empleado(String nombre, int salario) {}

    public static void demostracion() {
        System.out.println("--- DEMO: COLLECTORS TEEING (BIFURCACIÓN) ---");
        List<Empleado> emps = List.of(
            new Empleado("Ana", 1000), new Empleado("Bob", 2000), new Empleado("Zac", 1500)
        );

        // Queremos saber el salario MAX y el MIN en 1 sola iteración, sin recorrer la lista 2 veces.
        // Teeing (Como una tuberia en 'T') tiene 3 argumentos: 
        // 1. Colector Izquierdo, 2. Colector Derecho, 3. Función de Fusión.
        Map<String, Integer> reporte = emps.stream().collect(
            Collectors.teeing(
                Collectors.maxBy((e1, e2) -> e1.salario() - e2.salario()),
                Collectors.minBy((e1, e2) -> e1.salario() - e2.salario()),
                (optMax, optMin) -> Map.of(
                    "MAX", optMax.get().salario(), 
                    "MIN", optMin.get().salario()
                )
            )
        );

        System.out.println("Reporte instantáneo unificado: " + reporte);
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 46: AUDITORÍA DE APROBADOS Y SUSPENSOS ---");

        record Alumno(String nombre, double nota) {}
        Stream<Alumno> notas = Stream.of(
            new Alumno("Luis", 4.0), new Alumno("Rosa", 9.0),
            new Alumno("Pepe", 2.0), new Alumno("Marta", 6.0)
        );

        // TODO: Usa Collectors.teeing para procesar el Stream UNA VEZ.
        // Colector 1 (Izquierdo): Cuenta cuántos alumnos aprobaron (nota >= 5). Usa Collectors.filtering y Collectors.counting.
        // Colector 2 (Derecho): Cuenta cuántos suspendieron (nota < 5).
        // Fusionador: Devuelve un Map.of("Aprobados", cantAprobados, "Suspensos", cantSuspensos).
        // TIP: filtering() devuelve un colector, no te olvides de encadenarle counting() como downstream.
        
        Map<String, Long> estadisticas = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN DETALLADA ---
        boolean ok1 = estadisticas != null;
        boolean okAprob = ok1 && estadisticas.getOrDefault("Aprobados", 0L) == 2L;
        boolean okSusp = ok1 && estadisticas.getOrDefault("Suspensos", 0L) == 2L;

        if (!ok1) System.err.println("-> [TODO] Falló: No creaste el recolector teeing.");
        if (ok1 && (!okAprob || !okSusp)) System.err.println("-> [TODO] Falló: La fusión no procesó bien: Aprobados(" + estadisticas.get("Aprobados") + "), Suspensos(" + estadisticas.get("Suspensos") + ")");

        if (ok1 && okAprob && okSusp) {
            System.out.println(">> PERFECTO: Teeing es brutal para recorrer Gigabytes de logs una sola vez sacando métricas divididas. \033[0;32m [OK]\033[0m");
        }
    }
}
