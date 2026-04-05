package nivel1_fundamentos_lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import modelos.Empleado;

/**
 * EJERCICIO 03 — COMPARATOR.COMPARING() CON LAMBDA (CON GUÍA)
 * 
 * Objetivo: Descubrir Comparator.comparing() como alternativa elegante a la Lambda (a,b)->...
 */
public class Ejercicio03_ComparatorComparing {

    public static void demostracion() {
        System.out.println("--- COMPARATOR.COMPARING: EL ATAJO INTELIGENTE ---");
        System.out.println("En lugar de escribir: lista.sort((a, b) -> a.getNombre().compareTo(b.getNombre()))");
        System.out.println("Java ofrece un atajo más limpio: lista.sort(Comparator.comparing(e -> e.getNombre()))");
        System.out.println("Y todavía más limpio con Method Reference: lista.sort(Comparator.comparing(Empleado::getNombre))");
        System.out.println("Además, puedes encadenar .reversed() para invertir el orden.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 03: ORDENAR EMPLEADOS POR EXPERIENCIA ASCENDENTE ---");
        List<Empleado> equipo = new ArrayList<>();
        equipo.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        equipo.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, "carlos@corp.com"));
        equipo.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        equipo.add(new Empleado("Pedro", "DevOps", "Go", 4, 45000, true, "pedro@corp.com"));

        // TODO: Ordena 'equipo' por años de experiencia ASCENDENTE usando Comparator.comparing()

        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<

        // --- VALIDACIÓN ---
        if (equipo.get(0).getNombre().equals("Carlos") && equipo.get(3).getNombre().equals("Lucía")) {
            System.out.println(">> CORRECTO: Comparator.comparing() simplifica enormemente las ordenaciones.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Carlos (2a) debería ir primero, Lucía (10a) al final.");
        }
    }
}
