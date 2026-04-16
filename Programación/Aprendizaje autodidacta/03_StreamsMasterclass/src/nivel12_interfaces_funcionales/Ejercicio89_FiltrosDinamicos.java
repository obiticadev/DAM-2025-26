package nivel12_interfaces_funcionales;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 89 - SISTEMA DE FILTROS DINAMICOS
 * 
 * SIN GUIA. Crea un sistema donde el usuario pueda combinar filtros
 * dinamicamente usando Predicate.and/or/negate.
 */
public class Ejercicio89_FiltrosDinamicos {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 89: FILTROS DINAMICOS ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 89: COMBINA FILTROS ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Perez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta Lopez", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucia Martin", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        // TODO 1: Crea un "banco de filtros" como List<Predicate<Empleado>> con:
        //   - esActivo
        //   - salario > 40000
        //   - experiencia >= 5
        //   - tiene email
        List<Predicate<Empleado>> banco = new ArrayList<>();
        // <- Anade filtros al banco

        // TODO 2: Crea un metodo que combine TODOS los filtros del banco con AND.
        // Usa reduce(p -> true, Predicate::and) sobre la lista de predicados.
        // Filtra la empresa con el predicado combinado y muestra resultados.
        Predicate<Empleado> todosAnd = null; // <- Usa banco.stream().reduce(...)
        System.out.println("[AND de todos los filtros]");
        List<String> resultadoAnd = null;
        // <- Filtra y recoge nombres

        // TODO 3: Combina los filtros con OR en vez de AND.
        // Usa reduce(p -> false, Predicate::or).
        Predicate<Empleado> todosOr = null;
        System.out.println("\n[OR de todos los filtros]");
        // <- Filtra y muestra

        // TODO 4: Crea filtros compuestos mas complejos:
        //   a) (Backend AND senior) OR (Data)
        //   b) NOT(QA) AND (salario > 35000) AND activo
        // Muestra resultados de cada uno con forEach.
        System.out.println("\n[Backend senior OR Data]");
        // <- Escribe aqui
        System.out.println("\n[NOT QA AND salario>35k AND activo]");
        // <- Escribe aqui

        // --- VALIDACION ---
        boolean v1 = resultadoAnd != null && resultadoAnd.size() == 3; // Ana, Lucia, Marcos (>=5exp, >40k, email, activo) -- wait Marcos has 65k, 8exp, email, activo. Marta: 48k, 5exp, email, activo. Elena: 48k, 6exp, email, activo.
        // Actually: activo AND >40k AND >=5 AND email: Ana(55k,7,email), Marta(48k,5,email), Lucia(70k,10,email), Elena(48k,6,email), Marcos(65k,8,email) = 5
        boolean v2 = todosAnd != null && !todosAnd.test(empresa.get(1)); // Luis: 35k < 40k

        if (v1 && v2) {
            System.out.println("\n>> CORRECTO: Filtros dinamicos dominados. [OK]");
        } else {
            System.err.println("\n>> [ERROR] AND=5 resultados, Luis no pasa (35k<40k).");
        }
    }
}
