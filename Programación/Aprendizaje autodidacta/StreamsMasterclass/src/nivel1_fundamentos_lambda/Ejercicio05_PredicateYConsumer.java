package nivel1_fundamentos_lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import modelos.Empleado;

/**
 * EJERCICIO 05 — PREDICATE Y CONSUMER COMO VARIABLES (CON GUÍA)
 * 
 * Objetivo: Almacenar Lambdas en variables tipadas con interfaces funcionales.
 * Lee primero: teoria/01_Interfaces_Funcionales_y_SAM.md
 */
public class Ejercicio05_PredicateYConsumer {

    public static void demostracion() {
        System.out.println("--- LAMBDAS ALMACENADAS EN VARIABLES ---");
        System.out.println("Una Lambda no solo se escribe inline. Puedes guardarla en una variable:");
        System.out.println("  Predicate<Empleado> esSenior = e -> e.esSenior();");
        System.out.println("  Consumer<Empleado> imprimir = e -> System.out.println(e);");
        System.out.println("Después la usas donde quieras: lista.stream().filter(esSenior).forEach(imprimir);");
        System.out.println("Predicate = devuelve boolean. Consumer = no devuelve nada (void).\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 05: CREAR UN PREDICATE Y UN CONSUMER ---");
        List<Empleado> equipo = new ArrayList<>();
        equipo.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        equipo.add(new Empleado("Carlos", "Frontend", "JavaScript", 1, 25000, true, "carlos@corp.com"));
        equipo.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        equipo.add(new Empleado("Pedro", "QA", "Java", 3, 30000, false, "pedro@corp.com"));

        // TODO 1: Crea un Predicate<Empleado> llamado 'activoYSenior' que devuelva true
        // si el empleado está activo Y es senior (usa los métodos isActivo() y esSenior()).

        Predicate<Empleado> activoYSenior = null; // <- Escribe aquí

        // TODO 2: Crea un Consumer<Empleado> llamado 'mostrar' que imprima el empleado por consola.

        Consumer<Empleado> mostrar = null; // <- Escribe aquí

        // TODO 3: Usa ambos para filtrar e imprimir con un stream
        List<Empleado> resultado = new ArrayList<>();
        if (activoYSenior != null) {
            equipo.stream().filter(activoYSenior).forEach(e -> {
                resultado.add(e);
                if (mostrar != null) mostrar.accept(e);
            });
        }

        // --- VALIDACIÓN ---
        if (resultado.size() == 2
                && resultado.stream().allMatch(e -> e.isActivo() && e.esSenior())) {
            System.out.println(">> CORRECTO: Has almacenado Lambdas en variables funcionales y las has reutilizado.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Deberían quedar 2 empleados (Ana y Lucía): activos Y seniors.");
        }
    }
}
