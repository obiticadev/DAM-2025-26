package nivel7_experto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 47 — RETO EXPERTO: REDUCE COMO COLLECTOR MANUAL
 * 
 * SIN PISTAS. Usa reduce para construir resultados que normalmente harías con Collectors.
 */
public class Ejercicio47_ReduceCustomCollector {

    public static void demostracion() {
        System.out.println("=== RETO EXPERTO 47: REDUCE COMO COLLECTOR MANUAL ===");
        System.out.println("A veces reduce te da más control que un Collector predefinido.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 47: CONSTRUIR UN RESUMEN HTML CON REDUCE ---");
        List<Empleado> equipo = new ArrayList<>();
        equipo.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        equipo.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, "carlos@corp.com"));
        equipo.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        equipo.add(new Empleado("Pedro", "QA", "Java", 3, 30000, false, null));

        // TODO 1: Usa reduce para construir un string HTML con una tabla:
        // "<table><tr><td>Ana</td><td>55000.0</td></tr><tr><td>Carlos</td><td>32000.0</td></tr>..."
        // Solo empleados activos. El string debe empezar con "<table>" y terminar con "</table>".
        String tablaHtml = ""; // <- Escribe aquí

        // TODO 2: Usa reduce para encontrar el empleado con el nombre más largo (entre activos).
        String nombreMasLargo = ""; // <- Escribe aquí

        // TODO 3: Usa reduce para calcular el producto de todos los años de experiencia de activos.
        // (7 * 2 * 10 = 140)
        int productoExperiencia = 0; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = tablaHtml.startsWith("<table>") && tablaHtml.endsWith("</table>")
                && tablaHtml.contains("<td>Ana</td>") && !tablaHtml.contains("Pedro");
        boolean v2 = nombreMasLargo.equals("Carlos");
        boolean v3 = productoExperiencia == 140;

        if (v1 && v2 && v3) {
            System.out.println(">> CORRECTO: reduce manual para construir HTML, buscar y multiplicar.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] tabla HTML sin Pedro, nombreMasLargo=Carlos(6 letras), productoExp=140.");
        }
    }
}
