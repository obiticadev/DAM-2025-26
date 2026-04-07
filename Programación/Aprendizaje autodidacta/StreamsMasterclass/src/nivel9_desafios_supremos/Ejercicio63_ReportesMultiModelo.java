package nivel9_desafios_supremos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import modelos.Empleado;
import modelos.Pedido;
import modelos.Producto;

/**
 * EJERCICIO 63 — GENERADOR DE REPORTES MULTI-MODELO
 * 
 * SIN PISTAS. Cruza datos de Empleados, Productos y Pedidos para generar informes.
 */
public class Ejercicio63_ReportesMultiModelo {

    public static void demostracion() {
        System.out.println("=== DESAFÍO SUPREMO 63: REPORTES MULTI-MODELO ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 63: CRUCE DE DATOS ENTRE MODELOS ---");

        List<Producto> productos = new ArrayList<>();
        Producto laptop = new Producto("Laptop Pro", "Electrónica", 1200.0, 15, true);
        Producto raton = new Producto("Ratón Gamer", "Electrónica", 45.0, 120, true);
        Producto camiseta = new Producto("Camiseta Dev", "Ropa", 18.0, 200, true);
        Producto cafe = new Producto("Café Premium", "Alimentación", 12.0, 500, true);
        Producto monitor = new Producto("Monitor 4K", "Electrónica", 350.0, 8, true);
        Producto teclado = new Producto("Teclado Mecánico", "Electrónica", 89.0, 50, true);
        productos.addAll(Arrays.asList(laptop, raton, camiseta, cafe, monitor, teclado));

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("P001", "Ana García", Arrays.asList(laptop, raton), false));
        pedidos.add(new Pedido("P002", "Luis Pérez", Arrays.asList(camiseta, cafe, monitor), true));
        pedidos.add(new Pedido("P003", "Ana García", Arrays.asList(teclado, cafe), false));
        pedidos.add(new Pedido("P004", "Marta López", Arrays.asList(laptop, monitor, raton), false));
        pedidos.add(new Pedido("P005", "Luis Pérez", Arrays.asList(raton, teclado), true));

        List<Empleado> vendedores = new ArrayList<>();
        vendedores.add(new Empleado("Ana García", "Ventas", "Java", 5, 40000, true, "ana@corp.com"));
        vendedores.add(new Empleado("Luis Pérez", "Ventas", "Python", 3, 32000, true, "luis@corp.com"));
        vendedores.add(new Empleado("Marta López", "Ventas", "JavaScript", 7, 50000, true, "marta@corp.com"));

        // TODO 1: Para cada cliente, calcula el total gastado en todos sus pedidos.
        // Resultado: Map<String, Double> cliente -> totalGastado, ordenado por total DESC.
        Map<String, Double> gastoPorCliente = null; // <- Escribe aquí

        // TODO 2: Obtén el producto más vendido (aparece en más pedidos).
        // Si hay empate, el de mayor precio.
        String productoEstrella = ""; // <- Escribe aquí

        // TODO 3: Para cada categoría de producto, calcula los ingresos totales
        // (suma de precios de productos de esa categoría en todos los pedidos).
        Map<String, Double> ingresosPorCategoria = null; // <- Escribe aquí

        // TODO 4: Genera un ranking de clientes con formato:
        // "#1 Marta López: 1595.0€ (1 pedido, 3 artículos)"
        // Ordenados por gasto total DESC.
        List<String> rankingClientes = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = gastoPorCliente != null && gastoPorCliente.size() == 3;
        boolean v2 = productoEstrella.equals("Ratón Gamer");
        boolean v3 = ingresosPorCategoria != null && ingresosPorCategoria.containsKey("Electrónica");
        boolean v4 = rankingClientes != null && rankingClientes.size() == 3
                && rankingClientes.get(0).contains("Marta López");

        if (v1 && v2 && v3 && v4) {
            System.out.println(">> CORRECTO: Reportes multi-modelo generados.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] 3 clientes, productoEstrella=Ratón Gamer, ingresos por cat, ranking por gasto.");
        }
    }
}
