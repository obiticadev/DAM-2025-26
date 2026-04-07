package nivel2_streams_basicos;

import java.util.ArrayList;
import java.util.List;
import modelos.Empleado;

/**
 * EJERCICIO 13 — TERMINALES: .COUNT() Y .FOREACH() (CON GUÍA)
 * 
 * Objetivo: Usar operaciones terminales que no recolectan en lista.
 */
public class Ejercicio13_CountYForEach {

    public static void demostracion() {
        System.out.println("--- OPERACIONES TERMINALES ALTERNATIVAS ---");
        System.out.println("No todo termina en .collect(). Existen otros terminales:");
        System.out.println("  .count()   -> Devuelve un long con el número de elementos que llegan al final.");
        System.out.println("  .forEach() -> Ejecuta una acción (Consumer) sobre cada elemento. No devuelve nada.");
        System.out.println("  RECUERDA: Un stream solo se consume UNA VEZ. No puedes reutilizarlo.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 13: CONTAR Y MOSTRAR ---");
        List<Empleado> equipo = new ArrayList<>();
        equipo.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        equipo.add(new Empleado("Carlos", "Frontend", "JavaScript", 1, 25000, true, "carlos@corp.com"));
        equipo.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        equipo.add(new Empleado("Pedro", "QA", "Java", 3, 30000, false, null));
        equipo.add(new Empleado("Elena", "DevOps", "Go", 4, 45000, true, "elena@corp.com"));

        // TODO 1: Usa .stream().filter().count() para contar cuántos empleados ganan más de 40000.
        long bienPagados = 0; // <- Escribe aquí

        // TODO 2: Usa .stream().filter().forEach() para imprimir por consola SOLO los empleados
        // que usan Java. Usa System.out::println como Consumer.
        System.out.println("Empleados Java:");
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<

        // --- VALIDACIÓN ---
        if (bienPagados == 3) {
            System.out.println(">> CORRECTO: Has contado 3 empleados con salario > 40000 y mostrado los de Java.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Deberían ser 3 los que ganan >40k: Ana(55k), Lucía(70k), Elena(45k).");
        }
    }
}
