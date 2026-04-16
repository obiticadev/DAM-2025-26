package nivel10_foreach_consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import modelos.Empleado;
import modelos.Pedido;
import modelos.Producto;

/**
 * EJERCICIO 72 — DASHBOARD COMPLETO: STREAMS + forEach + FORMATO
 * 
 * SIN GUÍA. Construye un dashboard corporativo completo combinando
 * forEach, method references, Consumer.andThen, Stream.concat,
 * collectingAndThen y formato visual.
 */
public class Ejercicio72_DashboardCompleto {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 72: DASHBOARD COMPLETO ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 72: DASHBOARD CORPORATIVO ---");

        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
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
        pedidos.add(new Pedido("P004", "Elena Torres", Arrays.asList(monitor, raton), false));

        int[] paneles = {0};

        // TODO 1: PANEL "PLANTILLA" — Pinta tabla de empleados activos con forEach.
        // Usa method reference a un método estático que imprima cada fila.
        // Debajo, imprime: "Activos: N | Inactivos: M | Masa salarial: X€"
        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.println("│              PANEL: PLANTILLA               │");
        System.out.println("└─────────────────────────────────────────────┘");
        // <- Escribe aquí
        // Al terminar: paneles[0]++;

        // TODO 2: PANEL "PEDIDOS" — Para cada pedido, imprime con forEach:
        // "  pedido.getIdPedido() | cliente | N arts | total€ | ENTREGADO/PENDIENTE"
        // Debajo: "Facturación total: X€ | Pendientes: N | Entregados: M"
        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.println("│               PANEL: PEDIDOS                │");
        System.out.println("└─────────────────────────────────────────────┘");
        // <- Escribe aquí
        // Al terminar: paneles[0]++;

        // TODO 3: PANEL "CRUCE" — Usa Stream.concat para unir los nombres
        // de empleados activos con los clientes de pedidos.
        // Cuenta cuántos empleados activos también son clientes.
        // Imprime: "Empleados que también son clientes: nombre1, nombre2, ..."
        // Usa streams + forEach.
        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.println("│           PANEL: CRUCE EMP/CLIENTES         │");
        System.out.println("└─────────────────────────────────────────────┘");
        // <- Escribe aquí
        // Al terminar: paneles[0]++;

        // TODO 4: PANEL "KPIs" — Calcula e imprime con forEach sobre una lista de strings:
        //   "Salario medio activos: X€"
        //   "Ticket medio pedidos: X€"
        //   "Depto más grande: DEPTO (N personas)"
        //   "Producto más vendido: NOMBRE (aparece en N pedidos)"
        // Construye la lista de KPIs con streams, luego imprímela con forEach(System.out::println).
        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.println("│               PANEL: KPIs                   │");
        System.out.println("└─────────────────────────────────────────────┘");
        // <- Escribe aquí
        // Al terminar: paneles[0]++;

        // --- VALIDACIÓN ---
        if (paneles[0] == 4) {
            System.out.println("\n>> CORRECTO: Dashboard completo con 4 paneles.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println("\n>> [ERROR] Completa los 4 paneles (incrementa paneles[0] en cada uno).");
        }
    }
}
