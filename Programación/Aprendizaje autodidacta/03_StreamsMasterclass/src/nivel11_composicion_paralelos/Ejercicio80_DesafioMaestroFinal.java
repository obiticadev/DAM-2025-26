package nivel11_composicion_paralelos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import modelos.Empleado;
import modelos.Pedido;
import modelos.Producto;

/**
 * EJERCICIO 80 — DESAFÍO MAESTRO FINAL
 * 
 * SIN PISTAS. El reto definitivo del nivel 11.
 * Combina TODAS las técnicas aprendidas en niveles 1-11:
 * Lambdas, Method References, Streams, Optional, Collectors, flatMap, reduce,
 * generate, iterate, Collector.of, downstream collectors, forEach avanzado,
 * Consumer.andThen, Function.compose/andThen, Predicate.and/or/negate,
 * Stream.concat, collectingAndThen, Supplier&lt;Stream&gt;, parallel streams.
 * 
 * Si completas este ejercicio, eres un MAESTRO de Java Streams.
 */
public class Ejercicio80_DesafioMaestroFinal {

    public static void demostracion() {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║          DESAFÍO MAESTRO FINAL — EJERCICIO 80          ║");
        System.out.println("║   Combina TODAS las técnicas. Sin pistas. A por ello.  ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 80: EL DESAFÍO MAESTRO ---");

        // === DATOS ===
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));
        empresa.add(new Empleado("Sara Díaz", "Data", "Python", 4, 42000, true, "sara@corp.com"));
        empresa.add(new Empleado("Tomás Ruiz", "QA", "Java", 1, 25000, true, "tomas@corp.com"));

        Producto laptop = new Producto("Laptop Pro", "Electrónica", 1200.0, 15, true);
        Producto raton = new Producto("Ratón Gamer", "Electrónica", 45.0, 120, true);
        Producto camiseta = new Producto("Camiseta Dev", "Ropa", 18.0, 200, true);
        Producto cafe = new Producto("Café Premium", "Alimentación", 12.0, 500, true);
        Producto monitor = new Producto("Monitor 4K", "Electrónica", 350.0, 8, true);
        Producto teclado = new Producto("Teclado Mecánico", "Electrónica", 89.0, 50, true);

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("P001", "Ana García", Arrays.asList(laptop, raton), false));
        pedidos.add(new Pedido("P002", "Marta López", Arrays.asList(camiseta, cafe, monitor), true));
        pedidos.add(new Pedido("P003", "Lucía Martín", Arrays.asList(raton, cafe, teclado), false));
        pedidos.add(new Pedido("P004", "Marcos Vila", Arrays.asList(laptop, monitor, camiseta), false));
        pedidos.add(new Pedido("P005", "Elena Torres", Arrays.asList(cafe, raton, camiseta), true));
        pedidos.add(new Pedido("P006", "Ana García", Arrays.asList(teclado, monitor), false));

        // ============================================================
        // PARTE A: COMPOSICIÓN FUNCIONAL
        // ============================================================

        // TODO A1: Crea un Supplier<Stream<Empleado>> para activos.
        // Usa el Supplier para calcular: totalActivos, masaSalarial, salarioMedio.
        Supplier<Stream<Empleado>> sActivos = null; // <- Escribe aquí
        long totalActivos = 0;
        double masaSalarial = 0;
        double salarioMedio = 0;

        // TODO A2: Crea un Predicate compuesto:
        //   (activo AND experiencia >= 5 AND email != null) OR (activo AND salario >= 65000)
        // Filtra la empresa con él y recoge los nombres en una lista.
        Predicate<Empleado> eliteFilter = null; // <- Escribe aquí
        List<String> elite = null; // <- Escribe aquí

        // TODO A3: Crea un pipeline de Function con andThen:
        //   Empleado → nombre → toUpperCase → "【nombre】"
        // Aplícalo con map + forEach(System.out::println) sobre los elite.
        Function<Empleado, String> badgeFn = null; // <- Escribe aquí

        // ============================================================
        // PARTE B: forEach AVANZADO + VISUAL
        // ============================================================

        // TODO B1: Crea 2 Consumer<Empleado> encadenados con andThen:
        //   a) imprime "► nombre (depto)"
        //   b) imprime "    salario€ | experiencia años | lenguaje"
        // Aplícalo con forEach a los 5 mejor pagados activos, ordenados por salario DESC.
        Consumer<Empleado> fichaDual = null; // <- Escribe aquí
        System.out.println("\n[Top 5 con Consumer dual]");
        // <- Escribe aquí

        // TODO B2: Agrupa activos por departamento. Usa entrySet().stream()
        // .sorted(Map.Entry.comparingByKey()) y forEach para imprimir cada grupo:
        //   "DEPTO: nombre1, nombre2 — Media: X€"
        System.out.println("\n[Departamentos]");
        // <- Escribe aquí

        // ============================================================
        // PARTE C: PARALLEL + CONCAT + COLLECTING
        // ============================================================

        // TODO C1: Usa parallelStream + reduce(3 args) para sumar salarios activos.
        double sumaParallel = 0; // <- Escribe aquí

        // TODO C2: Concatena los nombres de clientes (de pedidos) con los nombres
        // de empleados activos usando Stream.concat. Obtén los ÚNICOS, ordénalos.
        List<String> todosNombresUnicos = null; // <- Escribe aquí

        // TODO C3: Usa collectingAndThen(groupingBy(...), TreeMap::new) para obtener
        // un mapa ordenado de departamento → lista de nombres de activos.
        // Luego usa forEach para imprimirlo.
        System.out.println("\n[Mapa ordenado depto → nombres]");
        // <- Escribe aquí

        // ============================================================
        // PARTE D: INFORME FINAL (una sola línea por métrica)
        // ============================================================

        // TODO D1: Genera una List<String> de métricas usando Stream.of():
        //   "Empleados activos: N"
        //   "Masa salarial: X€"
        //   "Salario medio: X€"
        //   "Elite: nombre1, nombre2, ..."
        //   "Clientes únicos: N"
        //   "Facturación total pedidos: X€"
        //   "Producto más popular: NOMBRE (en N pedidos)"
        // Imprime con forEach(System.out::println).
        System.out.println("\n╔═════════════════════════════════╗");
        System.out.println("║       INFORME MAESTRO FINAL     ║");
        System.out.println("╚═════════════════════════════════╝");
        // <- Escribe aquí

        // --- VALIDACIÓN ---
        int checks = 0;
        if (totalActivos == 9 && Math.abs(masaSalarial - 380000) < 1) checks++;
        if (elite != null && elite.size() >= 4 && elite.contains("Lucía Martín")) checks++;
        if (badgeFn != null && badgeFn.apply(empresa.get(0)).contains("ANA")) checks++;
        if (fichaDual != null) checks++;
        if (Math.abs(sumaParallel - 380000) < 1) checks++;
        if (todosNombresUnicos != null && todosNombresUnicos.size() >= 9) checks++;

        System.out.println("\n>>> RESULTADO: " + checks + "/6 secciones correctas <<<");
        if (checks == 6) {
            System.out.println("\033[0;32m╔════════════════════════════════════════════════════════╗");
            System.out.println("║  ¡¡¡FELICIDADES!!!                                     ║");
            System.out.println("║  HAS COMPLETADO EL DESAFÍO MAESTRO FINAL.              ║");
            System.out.println("║  ERES UN MAESTRO DE JAVA STREAMS, LAMBDAS Y            ║");
            System.out.println("║  PROGRAMACIÓN FUNCIONAL.                                ║");
            System.out.println("╚════════════════════════════════════════════════════════╝\033[0m");
        } else {
            System.err.println(">> Faltan " + (6 - checks) + " secciones. ¡Sigue intentando!");
        }
    }
}
