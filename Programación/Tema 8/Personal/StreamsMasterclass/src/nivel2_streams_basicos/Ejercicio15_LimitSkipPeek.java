package nivel2_streams_basicos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import modelos.Empleado;

/**
 * EJERCICIO 15 — .LIMIT(), .SKIP() Y .PEEK() (CON GUÍA)
 * 
 * Objetivo: Paginar resultados y depurar con peek.
 */
public class Ejercicio15_LimitSkipPeek {

    public static void demostracion() {
        System.out.println("--- PAGINACIÓN Y DEPURACIÓN EN STREAMS ---");
        System.out.println(".limit(n) -> Solo deja pasar los primeros N elementos.");
        System.out.println(".skip(n)  -> Salta los primeros N elementos.");
        System.out.println(".peek(Consumer) -> Espía cada elemento sin modificar el stream (para debug).\n");
        System.out.println("Ejemplo paginación: stream.skip(pagina * tamano).limit(tamano)");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 15: TOP 3 MEJORES PAGADOS ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Carlos", "Frontend", "JavaScript", 1, 25000, true, "carlos@corp.com"));
        empresa.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro", "QA", "Java", 3, 30000, true, "pedro@corp.com"));
        empresa.add(new Empleado("Elena", "DevOps", "Go", 4, 45000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        // TODO: Ordena por salario descendente, usa .peek() para debug,
        // toma los 3 primeros con .limit() y recoge en lista.

        List<Empleado> top3 = empresa.stream()
                .sorted(Comparator.comparing(Empleado::getSalario).reversed())
                .peek(System.out::println)
                .limit(3)
                .toList();

        // --- VALIDACIÓN ---
        if (top3 != null && top3.size() == 3
                && top3.get(0).getNombre().equals("Lucía")
                && top3.get(1).getNombre().equals("Marcos")
                && top3.get(2).getNombre().equals("Ana")) {
            System.out.println(">> CORRECTO: Top 3 con peek de debug funcionando.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Top 3 esperado: Lucía(70k), Marcos(65k), Ana(55k).");
        }
    }
}
