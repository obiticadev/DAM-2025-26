package nivel3_method_references;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 22 — COLLECTORS.JOINING() Y COUNTING() (SIN GUÍA)
 * 
 * Objetivo: Crear strings concatenados y contar elementos por grupo.
 */
public class Ejercicio22_JoiningYCounting {

    public static void demostracion() {
        System.out.println("--- COLLECTORS: JOINING Y COUNTING ---");
        System.out.println("Collectors.joining(\", \") une todos los strings del stream con un separador.");
        System.out.println("Collectors.groupingBy(campo, Collectors.counting()) cuenta elementos por grupo.");
        System.out.println("Son los dos collectors más útiles después de toList().\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 22: RESUMEN TEXTUAL Y CONTEO POR DEPARTAMENTO ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, "carlos@corp.com"));
        empresa.add(new Empleado("Marta", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));

        // TODO 1: Crea un String con los nombres de todos los empleados separados por ", "
        // Resultado esperado: "Ana, Luis, Carlos, Marta, Lucía"
        String nombresUnidos = null; // <- Escribe aquí

        // TODO 2: Crea un Map<String, Long> que cuente cuántos empleados hay por departamento
        Map<String, Long> conteoPorDepto = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean joinOk = nombresUnidos != null && nombresUnidos.equals("Ana, Luis, Carlos, Marta, Lucía");
        boolean countOk = conteoPorDepto != null
                && conteoPorDepto.get("Backend") == 2L
                && conteoPorDepto.get("Frontend") == 2L
                && conteoPorDepto.get("Data") == 1L;

        if (joinOk && countOk) {
            System.out.println(">> CORRECTO: joining() y counting() dominados.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] nombresUnidos='Ana, Luis, Carlos, Marta, Lucía'. conteoPorDepto: Backend=2, Frontend=2, Data=1.");
        }
    }
}
