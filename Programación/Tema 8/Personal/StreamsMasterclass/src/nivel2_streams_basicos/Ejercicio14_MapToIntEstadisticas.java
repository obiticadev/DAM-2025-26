package nivel2_streams_basicos;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import modelos.Empleado;

/**
 * EJERCICIO 14 — MAPTOINT Y ESTADÍSTICAS (SIN GUÍA)
 * 
 * Objetivo: Usar mapToInt/mapToDouble para obtener estadísticas numéricas.
 */
public class Ejercicio14_MapToIntEstadisticas {

    public static void demostracion() {
        System.out.println("--- STREAMS PRIMITIVOS: mapToInt(), mapToDouble() ---");
        System.out.println("Cuando necesitas operar con números (suma, media, max, min),");
        System.out.println("usa .mapToInt() o .mapToDouble() para convertir a un IntStream/DoubleStream.");
        System.out.println("Estos streams especiales tienen: .sum(), .average(), .max(), .min(), .summaryStatistics()");
        System.out.println("summaryStatistics() te da todo a la vez en un solo objeto.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 14: ESTADÍSTICAS SALARIALES ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Carlos", "Frontend", "JavaScript", 1, 25000, true, "carlos@corp.com"));
        empresa.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro", "QA", "Java", 3, 30000, true, "pedro@corp.com"));
        empresa.add(new Empleado("Elena", "DevOps", "Go", 4, 45000, true, "elena@corp.com"));

        // TODO 1: Usa .stream().mapToInt(e -> (int)e.getSalario()).summaryStatistics()
        // para obtener un IntSummaryStatistics de los salarios.
        IntSummaryStatistics stats = empresa.stream()
                .mapToInt(e -> (int) e.getSalario()).summaryStatistics();

        // TODO 2: Extrae la suma total, la media, el máximo y el mínimo del objeto
        // stats.
        long suma = stats.getSum();
        double media = stats.getAverage();
        int maximo = stats.getMax();
        int minimo = stats.getMin();

        if (stats != null) {
            suma = stats.getSum();
            media = stats.getAverage();
            maximo = stats.getMax();
            minimo = stats.getMin();
        }

        System.out.println("Suma salarios: " + suma);
        System.out.println("Media: " + media);
        System.out.println("Max: " + maximo + " | Min: " + minimo);

        // --- VALIDACIÓN ---
        if (suma == 225000 && maximo == 70000 && minimo == 25000 && media == 45000.0) {
            System.out.println(">> CORRECTO: Estadísticas salariales calculadas con IntStream.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Suma=225000, Media=45000.0, Max=70000, Min=25000.");
        }
    }
}
