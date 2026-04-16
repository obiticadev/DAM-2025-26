package nivel3_method_references;

import java.util.ArrayList;
import java.util.List;
import modelos.Empleado;

/**
 * EJERCICIO 23 — ANYMATCH, ALLMATCH, NONEMATCH (CON GUÍA)
 * 
 * Objetivo: Usar operaciones terminales booleanas para verificar condiciones.
 */
public class Ejercicio23_AnyMatchAllMatchNoneMatch {

    public static void demostracion() {
        System.out.println("--- MATCHERS: PREGUNTAS BOOLEANAS AL STREAM ---");
        System.out.println(".anyMatch(predicate)  -> ¿ALGÚN elemento cumple?");
        System.out.println(".allMatch(predicate)  -> ¿TODOS los elementos cumplen?");
        System.out.println(".noneMatch(predicate) -> ¿NINGÚN elemento cumple?");
        System.out.println("Devuelven boolean y son TERMINALES (cierran el stream).\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 23: AUDITORÍA DE LA PLANTILLA ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, "carlos@corp.com"));
        empresa.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro", "QA", "Java", 3, 30000, true, "pedro@corp.com"));

        // TODO 1: ¿Hay ALGÚN empleado que gane más de 60000?
        boolean hayRico = false; // <- Escribe aquí

        // TODO 2: ¿TODOS los empleados están activos?
        boolean todosActivos = false; // <- Escribe aquí

        // TODO 3: ¿NINGÚN empleado usa "Ruby" como lenguaje principal?
        boolean nadieUsaRuby = false; // <- Escribe aquí

        System.out.println("¿Hay alguien que gane >60k? " + hayRico);
        System.out.println("¿Todos activos? " + todosActivos);
        System.out.println("¿Nadie usa Ruby? " + nadieUsaRuby);

        // --- VALIDACIÓN ---
        if (hayRico && todosActivos && nadieUsaRuby) {
            System.out.println(">> CORRECTO: Las 3 auditorías booleanas son correctas.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] hayRico=true (Lucía 70k), todosActivos=true, nadieUsaRuby=true.");
        }
    }
}
