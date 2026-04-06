package nivel1_fundamentos_lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 07 — COMPOSICIÓN DE PREDICADOS (CON GUÍA)
 * 
 * Objetivo: Usar .and(), .or(), .negate() para combinar predicados
 * reutilizables.
 */
public class Ejercicio07_ComposicionPredicados {

    public static void demostracion() {
        System.out.println("--- COMPOSICIÓN: .and(), .or(), .negate() ---");
        System.out.println("Los Predicates no son cajas cerradas. Tienen métodos para combinarse:");
        System.out.println("  Predicate<T> a = ...;  Predicate<T> b = ...;");
        System.out.println("  a.and(b)    -> true solo si AMBOS son true");
        System.out.println("  a.or(b)     -> true si ALGUNO es true");
        System.out.println("  a.negate()  -> invierte el resultado de a");
        System.out.println("Esto te permite construir filtros complejos como piezas de LEGO.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 07: FILTRO COMPUESTO CON PREDICADOS ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Carlos", "Frontend", "JavaScript", 1, 25000, true, "carlos@corp.com"));
        empresa.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Marta", "Backend", "Java", 6, 48000, true, "marta@corp.com"));

        // TODO 1: Crea un Predicate 'usaJava' que compruebe si el lenguaje principal es
        // "Java"
        Predicate<Empleado> usaJava = a -> a.getLenguajePrincipal().equals("Java");

        // TODO 2: Crea un Predicate 'estaActivo' que compruebe si el empleado está
        // activo
        Predicate<Empleado> estaActivo = Empleado::isActivo;

        // TODO 3: Combínalos con .and() para crear 'javaYActivo'
        Predicate<Empleado> javaYActivo = usaJava.and(estaActivo);

        // TODO 4: Crea un Predicate 'noJavaYActivo' usando .negate() sobre usaJava,
        // combinado con estaActivo
        Predicate<Empleado> noJavaYActivo = usaJava.negate().and(estaActivo);

        List<Empleado> resultadoJava = new ArrayList<>();
        List<Empleado> resultadoNoJava = new ArrayList<>();

        if (javaYActivo != null && noJavaYActivo != null) {
            resultadoJava = empresa.stream().filter(javaYActivo).collect(Collectors.toList());
            resultadoNoJava = empresa.stream().filter(noJavaYActivo).collect(Collectors.toList());
        }

        // --- VALIDACIÓN ---
        boolean javaOk = resultadoJava.size() == 2
                && resultadoJava.stream().allMatch(e -> e.getLenguajePrincipal().equals("Java") && e.isActivo());
        boolean noJavaOk = resultadoNoJava.size() == 2
                && resultadoNoJava.stream().noneMatch(e -> e.getLenguajePrincipal().equals("Java"));

        if (javaOk && noJavaOk) {
            System.out.println(
                    ">> CORRECTO: Has construido filtros como piezas de LEGO reutilizables.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(
                    ">> [ERROR] JavaYActivo debería dar 2 (Ana, Marta). NoJavaYActivo debería dar 2 (Carlos, Lucía).");
        }
    }
}
