package nivel4_optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 29 — OPTIONAL INTEGRADO CON STREAMS (CON GUÍA)
 * 
 * Objetivo: Combinar Optional con operaciones de Stream en escenarios reales.
 */
public class Ejercicio29_OptionalConStreams {

    public static void demostracion() {
        System.out.println("--- OPTIONAL + STREAMS: EL DÚO PERFECTO ---");
        System.out.println("En la vida real, muchos métodos devuelven Optional.");
        System.out.println("El patrón típico es:");
        System.out.println("  lista.stream()");
        System.out.println("      .map(obj -> Optional.ofNullable(obj.getCampoNullable()))");
        System.out.println("      .filter(Optional::isPresent)");
        System.out.println("      .map(Optional::get)");
        System.out.println("O más elegante en Java 9+: .flatMap(Optional::stream)\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 29: EXTRAER EMAILS NO NULOS CON OPTIONAL ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena", "DevOps", "Go", 4, 45000, true, "elena@corp.com"));

        // TODO: Extrae los emails de todos los empleados, descartando los null de forma segura
        // usando Optional.ofNullable() dentro de un Stream.

        List<String> emailsValidos = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        if (emailsValidos != null && emailsValidos.size() == 3
                && emailsValidos.contains("ana@corp.com")
                && emailsValidos.contains("lucia@corp.com")
                && emailsValidos.contains("elena@corp.com")) {
            System.out.println(">> CORRECTO: Optional + Stream para filtrar nulls de forma elegante.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Esperado 3 emails válidos: ana@corp.com, lucia@corp.com, elena@corp.com.");
        }
    }
}
