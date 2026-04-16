package nivel9_desafios_supremos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import modelos.Empleado;
import modelos.Pedido;
import modelos.Producto;

/**
 * EJERCICIO 65 — DESAFÍO SUPREMO FINAL
 * 
 * SIN PISTAS. El reto definitivo: combina TODOS los conceptos en un mega-ejercicio.
 * Lambdas, Method References, Streams, Optional, Collectors, flatMap, reduce,
 * generate, iterate, Collector.of, downstream collectors, predicados dinámicos.
 * 
 * Si completas este ejercicio correctamente, dominas Java Streams.
 */
public class Ejercicio65_DesafioSupremo {

    public static void demostracion() {
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║        DESAFÍO SUPREMO FINAL — EJERCICIO 65       ║");
        System.out.println("║  Combina TODOS los conceptos. Sin pistas. Suerte. ║");
        System.out.println("╚═══════════════════════════════════════════════════╝\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 65: EL DESAFÍO SUPREMO ---");

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

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("P001", "Ana García", Arrays.asList(laptop, raton), false));
        pedidos.add(new Pedido("P002", "Marta López", Arrays.asList(camiseta, cafe, monitor), true));
        pedidos.add(new Pedido("P003", "Lucía Martín", Arrays.asList(raton, cafe), false));
        pedidos.add(new Pedido("P004", "Marcos Vila", Arrays.asList(laptop, monitor, camiseta), false));
        pedidos.add(new Pedido("P005", "Elena Torres", Arrays.asList(cafe, raton, camiseta), true));

        // ============================================================
        // PARTE A: ANÁLISIS DE PERSONAL (usa empresa)
        // ============================================================

        // TODO A1: Crea un Collector personalizado (Collector.of) que genere un String con formato:
        // "INFORME: N activos de M total | Masa salarial: X€ | Media: Y€"
        // Solo para empleados activos.
        String informePersonal = ""; // <- Escribe aquí

        // TODO A2: Genera un Map<String, Map<Boolean, List<String>>> que agrupe:
        //   nivel 1: por departamento
        //   nivel 2: por si tiene email (true/false)
        //   valor: lista de nombres
        Map<String, Map<Boolean, List<String>>> organigramaProfundo = null; // <- Escribe aquí

        // TODO A3: Usando Stream.iterate, genera los umbrales [25000, 35000, 45000, 55000, 65000].
        // Para cada umbral, calcula cuántos empleados activos ganan ESTRICTAMENTE más.
        // Resultado: Map<Integer, Long> (usa LinkedHashMap para mantener orden)
        Map<Integer, Long> histogramaSalarial = null; // <- Escribe aquí

        // ============================================================
        // PARTE B: ANÁLISIS DE PEDIDOS (usa pedidos + empresa)
        // ============================================================

        // TODO B1: Para cada pedido, genera un String con formato:
        // "P00X -> CLIENTE (DEPTO) | N arts | TOTAL€ | URGENTE/NORMAL"
        // El departamento se obtiene buscando al cliente en la lista de empresa.
        // Si el cliente no existe en empresa -> "SIN DEPTO"
        List<String> fichasPedidos = null; // <- Escribe aquí

        // TODO B2: Obtén todos los productos ÚNICOS de todos los pedidos (por nombre),
        // ordenados por precio descendente.
        List<String> productosUnicos = null; // <- Escribe aquí

        // TODO B3: Calcula el gasto total por departamento:
        // Agrupa pedidos por el departamento del cliente (buscando en empresa),
        // y suma los totales de cada grupo.
        Map<String, Double> gastoPorDepto = null; // <- Escribe aquí

        // ============================================================
        // PARTE C: SÍNTESIS FINAL
        // ============================================================

        // TODO C1: Genera el INFORME FINAL como un solo String multilínea:
        // Línea 1: "=== INFORME CORPORATIVO ==="
        // Línea 2: informePersonal (de A1)
        // Línea 3: "Departamentos: " + lista de deptos ordenados A-Z, separados por ", "
        // Línea 4: "Facturación total: " + suma de todos los pedidos + "€"
        // Línea 5: "Cliente estrella: " + nombre del cliente con mayor gasto total
        // Línea 6: "Producto estrella: " + producto que aparece en más pedidos
        String informeFinal = ""; // <- Escribe aquí

        // --- VALIDACIÓN ---
        int checks = 0;
        if (informePersonal.contains("activos") && informePersonal.contains("Media")) checks++;
        if (organigramaProfundo != null && organigramaProfundo.size() >= 4) checks++;
        if (histogramaSalarial != null && histogramaSalarial.size() == 5) checks++;
        if (fichasPedidos != null && fichasPedidos.size() == 5) checks++;
        if (productosUnicos != null && productosUnicos.size() == 5
                && productosUnicos.get(0).equals("Laptop Pro")) checks++;
        if (gastoPorDepto != null && gastoPorDepto.size() >= 3) checks++;
        if (informeFinal.contains("INFORME CORPORATIVO")
                && informeFinal.contains("Facturación total")
                && informeFinal.contains("Cliente estrella")) checks++;

        System.out.println("\n>>> RESULTADO: " + checks + "/7 secciones correctas <<<");
        if (checks == 7) {
            System.out.println("\033[0;32m╔═══════════════════════════════════════════════════╗");
            System.out.println("║  ¡¡¡FELICIDADES!!! HAS COMPLETADO EL DESAFÍO      ║");
            System.out.println("║  SUPREMO. DOMINAS JAVA STREAMS AL 100%.            ║");
            System.out.println("╚═══════════════════════════════════════════════════╝\033[0m");
        } else {
            System.err.println(">> Faltan " + (7 - checks) + " secciones. ¡Sigue intentando!");
        }
    }
}
