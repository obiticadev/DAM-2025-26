package nivel11_composicion_paralelos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import modelos.Empleado;
import modelos.Pedido;
import modelos.Producto;

/**
 * EJERCICIO 78 — PATRONES AVANZADOS DE forEach CON METHOD REFERENCES
 * 
 * SIN GUÍA. Combina forEach con method references en escenarios reales:
 * imprimir mapas, procesar entrySet, encadenar Consumers sobre colecciones agrupadas.
 */
public class Ejercicio78_ForEachAvanzadoPatrones {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 78: PATRONES AVANZADOS forEach ===\n");
    }

    // Método estático para imprimir una entrada de mapa
    public static void imprimirEntrada(Map.Entry<String, List<Empleado>> entry) {
        String nombres = entry.getValue().stream()
                .map(Empleado::getNombre)
                .collect(Collectors.joining(", "));
        System.out.printf("  %-12s → %s%n", entry.getKey(), nombres);
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 78: forEach SOBRE MAPAS Y GRUPOS ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        Producto laptop = new Producto("Laptop Pro", "Electrónica", 1200.0, 15, true);
        Producto raton = new Producto("Ratón Gamer", "Electrónica", 45.0, 120, true);
        Producto camiseta = new Producto("Camiseta Dev", "Ropa", 18.0, 200, true);
        Producto monitor = new Producto("Monitor 4K", "Electrónica", 350.0, 8, true);

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("P001", "Ana García", Arrays.asList(laptop, raton), false));
        pedidos.add(new Pedido("P002", "Marta López", Arrays.asList(camiseta, monitor), true));
        pedidos.add(new Pedido("P003", "Marcos Vila", Arrays.asList(laptop, raton, camiseta), false));

        int[] checks = {0};

        // TODO 1: Agrupa empleados por departamento con groupingBy.
        // Luego usa .entrySet().stream().sorted(Map.Entry.comparingByKey())
        // y forEach con el método estático imprimirEntrada de esta clase (method reference).
        System.out.println("[Empleados agrupados por departamento]");
        // <- Escribe aquí
        // checks[0]++;

        // TODO 2: Para cada pedido, usa forEach con un Consumer que imprima:
        //   "pedidoId: producto1, producto2, ..." (nombres de productos separados por ", ")
        // Usa Pedido::getProductos + stream + joining dentro del Consumer.
        System.out.println("\n[Contenido de cada pedido]");
        // <- Escribe aquí
        // checks[0]++;

        // TODO 3: Agrupa pedidos por cliente. Para cada entrada del mapa, imprime:
        //   "CLIENTE: N pedidos, total gastado = X€"
        // Usa .forEach((cliente, listaPedidos) -> ...) sobre el mapa directamente.
        System.out.println("\n[Resumen de pedidos por cliente]");
        Map<String, List<Pedido>> pedidosPorCliente = null; // <- Escribe aquí
        // <- Imprime con forEach sobre el mapa
        // checks[0]++;

        // TODO 4: Crea una Function<Empleado, String> que genere una "tarjeta":
        //   "┌──────────────────┐"
        //   "│ NOMBRE           │"
        //   "│ Depto: DEPTO     │"
        //   "│ Salario: X€      │"
        //   "└──────────────────┘"
        // Usa .map(function).forEach(System.out::println) para pintar las tarjetas
        // de los 3 empleados mejor pagados.
        System.out.println("\n[Tarjetas top 3]");
        // <- Escribe aquí
        // checks[0]++;

        // --- VALIDACIÓN ---
        if (checks[0] == 4) {
            System.out.println("\n>> CORRECTO: Patrones avanzados de forEach dominados.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println("\n>> [ERROR] Completa los 4 bloques (incrementa checks[0]).");
        }
    }
}
