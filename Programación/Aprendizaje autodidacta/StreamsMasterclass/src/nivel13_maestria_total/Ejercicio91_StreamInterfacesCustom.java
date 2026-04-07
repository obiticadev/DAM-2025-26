package nivel13_maestria_total;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 91 - STREAMS CON INTERFACES FUNCIONALES CUSTOM
 * 
 * SIN GUIA. Combina streams con interfaces funcionales personalizadas
 * para crear operaciones de alto nivel.
 */
public class Ejercicio91_StreamInterfacesCustom {

    @FunctionalInterface
    public interface Procesador<T> {
        List<String> procesar(List<T> datos);
    }

    @FunctionalInterface
    public interface Formateador<T> {
        String formatear(T obj);
    }

    public static void demostracion() {
        System.out.println("=== EJERCICIO 91: STREAM + INTERFACES CUSTOM ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 91: PROCESADORES CUSTOM ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Perez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta Lopez", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucia Martin", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        // TODO 1: Implementa un Procesador<Empleado> que filtre activos,
        // ordene por salario DESC y devuelva una lista de "NOMBRE - salarioEUR".
        Procesador<Empleado> procesadorSalarial = null; // <- Escribe aqui

        System.out.println("[Procesador salarial]");
        if (procesadorSalarial != null) procesadorSalarial.procesar(empresa).forEach(System.out::println);

        // TODO 2: Implementa un Formateador<Empleado> que genere:
        // "  [nombre] depto | lenguaje | experiencia anios"
        Formateador<Empleado> fichaEmpleado = null; // <- Escribe aqui

        System.out.println("\n[Fichas formateadas]");
        if (fichaEmpleado != null) {
            empresa.stream()
                    .filter(Empleado::isActivo)
                    .map(fichaEmpleado::formatear)
                    .forEach(System.out::println);
        }

        // TODO 3: Crea un metodo generico usando Procesador:
        //   procesadorPorDepto: agrupa por departamento, y para cada grupo
        //   devuelve "DEPTO: nombre1, nombre2 (N personas)"
        Procesador<Empleado> procesadorPorDepto = null; // <- Escribe aqui

        System.out.println("\n[Procesador por departamento]");
        if (procesadorPorDepto != null) procesadorPorDepto.procesar(empresa).forEach(System.out::println);

        // TODO 4: Combina Procesador y Formateador: crea un Procesador que use
        // internamente un Formateador para transformar cada empleado.
        Procesador<Empleado> procesadorConFormato = null; // <- Escribe aqui

        System.out.println("\n[Procesador con formato custom]");
        if (procesadorConFormato != null) procesadorConFormato.procesar(empresa).forEach(System.out::println);

        // --- VALIDACION ---
        boolean v1 = procesadorSalarial != null && procesadorSalarial.procesar(empresa).size() == 7;
        boolean v2 = fichaEmpleado != null;
        boolean v3 = procesadorPorDepto != null;

        if (v1 && v2 && v3) {
            System.out.println("\n>> CORRECTO: Interfaces custom con streams dominadas. [OK]");
        } else {
            System.err.println("\n>> [ERROR] procesadorSalarial=7 activos, fichaEmpleado y procesadorPorDepto.");
        }
    }
}
