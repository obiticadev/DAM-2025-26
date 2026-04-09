package nivel6_flatmap_reduce;

import java.util.ArrayList;
import java.util.List;
import modelos.Empleado;

/**
 * EJERCICIO 37 — REDUCE: ACUMULAR VALORES EN UNO SOLO (CON GUÍA)
 * 
 * Objetivo: Entender .reduce() como operación terminal de acumulación.
 */
public class Ejercicio37_ReduceBasico {

    public static void demostracion() {
        System.out.println("--- .REDUCE(): LA OPERACIÓN DE ACUMULACIÓN ---");
        System.out.println("reduce(valorInicial, (acumulador, elemento) -> nuevaAcumulacion)");
        System.out.println("Ejemplo: sumar todos los salarios:");
        System.out.println("  double total = lista.stream()");
        System.out.println("      .map(Empleado::getSalario)");
        System.out.println("      .reduce(0.0, (acc, sal) -> acc + sal);");
        System.out.println("También existe reduce sin valor inicial -> devuelve Optional.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 37: REDUCE PARA SUMAR Y CONCATENAR ---");
        List<Empleado> equipo = new ArrayList<>();
        equipo.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        equipo.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, "carlos@corp.com"));
        equipo.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        equipo.add(new Empleado("Pedro", "QA", "Java", 3, 30000, true, "pedro@corp.com"));

        // TODO 1: Usa reduce con valor inicial 0.0 para sumar todos los salarios.
        double totalSalarios = 0; // <- Escribe aquí

        // TODO 2: Usa reduce para concatenar todos los nombres separados por " + ".
        String nombresConcatenados = ""; // <- Escribe aquí

        // TODO 3: Usa reduce SIN valor inicial para encontrar el empleado con mayor experiencia.
        // Recuerda que reduce sin valor inicial devuelve Optional.
        String masExperto = "Nadie"; // <- Escribe aquí

        System.out.println("Total salarios: " + totalSalarios);
        System.out.println("Nombres: " + nombresConcatenados);
        System.out.println("Más experto: " + masExperto);

        // --- VALIDACIÓN ---
        if (totalSalarios == 187000.0
                && nombresConcatenados.equals("Ana + Carlos + Lucía + Pedro")
                && masExperto.equals("Lucía")) {
            System.out.println(">> CORRECTO: reduce para sumar, concatenar y seleccionar dominado.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] total=187000, nombres='Ana + Carlos + Lucía + Pedro', masExperto=Lucía.");
        }
    }
}
