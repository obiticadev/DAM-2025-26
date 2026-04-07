package nivel1_fundamentos_lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 08 — PASAR LAMBDAS COMO PARÁMETROS A MÉTODOS PROPIOS (SIN GUÍA)
 * 
 * Objetivo: Diseñar un método que acepte un Predicate como parámetro y filtrar
 * con él.
 */
public class Ejercicio08_LambdaComoParametro {

    public static void demostracion() {
        System.out.println("--- LAMBDAS COMO PARÁMETROS DE TUS PROPIOS MÉTODOS ---");
        System.out.println("No solo los métodos de Java aceptan Lambdas. Tú puedes crear");
        System.out.println("métodos que reciban un Predicate<T>, Function<T,R>, Consumer<T>...");
        System.out.println("y usarlos internamente. Esto es la base del patrón Strategy.\n");
    }

    /**
     * Este método ya está creado. Recibe una lista y un Predicate y devuelve los
     * filtrados.
     * El alumno debe usarlo pasándole diferentes Lambdas.
     */
    public static List<Empleado> filtrarCon(List<Empleado> lista, Predicate<Empleado> criterio) {
        return lista.stream().filter(criterio).collect(Collectors.toList());
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 08: REUTILIZAR UN MÉTODO CON DISTINTAS LAMBDAS ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Carlos", "Frontend", "JavaScript", 1, 25000, true, "carlos@corp.com"));
        empresa.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena", "DevOps", "Go", 4, 45000, true, "elena@corp.com"));

        // El método filtrarCon(lista, predicate) ya existe arriba.
        // TODO 1: Usa filtrarCon para obtener solo los empleados con salario > 40000
        List<Empleado> bienPagados = filtrarCon(empresa, a -> a.getSalario() > 40000);

        // TODO 2: Usa filtrarCon para obtener solo los empleados cuyo nombre empieza
        // por vocal (A, E, I, O, U)
        /*
         * List<Empleado> empiezanVocal = filtrarCon(empresa, a ->
         * a.getNombre().toLowerCase().startsWith("a") ||
         * a.getNombre().toLowerCase().startsWith("e") ||
         * a.getNombre().toLowerCase().startsWith("i") ||
         * a.getNombre().toLowerCase().startsWith("o") ||
         * a.getNombre().toLowerCase().startsWith("u"));
         */
        List<Empleado> empiezanVocal = filtrarCon(empresa, e -> e.getNombre().matches("(?i)^[aeiouáéíóú].*"));

        // TODO 3: Usa filtrarCon para obtener empleados activos con más de 3 años de
        // experiencia
        List<Empleado> activosExpertos = filtrarCon(empresa, a -> a.isActivo() && a.getExperienciaAnios() > 3);

        // --- VALIDACIÓN ---
        boolean v1 = bienPagados != null && bienPagados.size() == 3;
        boolean v2 = empiezanVocal != null && empiezanVocal.size() == 2;
        boolean v3 = activosExpertos != null && activosExpertos.size() == 3;

        if (v1 && v2 && v3) {
            System.out.println(
                    ">> CORRECTO: Has reutilizado un mismo método con 3 Lambdas diferentes. Patrón Strategy en acción.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(
                    ">> [ERROR] bienPagados=3 (Ana,Lucía,Elena), empiezanVocal=2 (Ana,Elena), activosExpertos=3 (Ana,Lucía,Elena)");
        }
    }
}
