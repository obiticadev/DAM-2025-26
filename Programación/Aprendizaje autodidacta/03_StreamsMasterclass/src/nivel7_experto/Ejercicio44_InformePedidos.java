package nivel7_experto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import modelos.Pedido;
import modelos.Producto;

/**
 * EJERCICIO 44 — RETO EXPERTO: INFORME DE VENTAS
 * 
 * SIN PISTAS. Trabaja con Pedidos y Productos.
 */
public class Ejercicio44_InformePedidos {

    public static void demostracion() {
        System.out.println("=== RETO EXPERTO 44: INFORME DE VENTAS ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 44: DASHBOARD DE VENTAS ---");
        Producto laptop = new Producto("Laptop Pro", "Electrónica", 1200.0, 15, true);
        Producto raton = new Producto("Ratón Gamer", "Electrónica", 45.0, 120, true);
        Producto camiseta = new Producto("Camiseta Dev", "Ropa", 18.0, 200, true);
        Producto cafe = new Producto("Café Premium", "Alimentación", 12.0, 500, true);
        Producto monitor = new Producto("Monitor 4K", "Electrónica", 350.0, 8, true);
        Producto sudadera = new Producto("Sudadera Java", "Ropa", 35.0, 80, true);

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("P001", "Ana", Arrays.asList(laptop, raton), false));
        pedidos.add(new Pedido("P002", "Carlos", Arrays.asList(camiseta, cafe, monitor), true));
        pedidos.add(new Pedido("P003", "Ana", Arrays.asList(sudadera, cafe), false));
        pedidos.add(new Pedido("P004", "Lucía", Arrays.asList(laptop, monitor, raton), true));

        // TODO 1: Cliente que ha gastado más en total (suma de todos sus pedidos)
        String mejorCliente = ""; // <- Escribe aquí

        // TODO 2: Producto que aparece en más pedidos distintos (nombre)
        String productoPopular = ""; // <- Escribe aquí

        // TODO 3: Ingreso total de pedidos entregados
        double ingresoEntregados = 0; // <- Escribe aquí

        // TODO 4: Map<String, Long> de cliente -> número de pedidos
        Map<String, Long> pedidosPorCliente = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = mejorCliente.equals("Lucía"); // 1200+350+45 = 1595
        boolean v2 = productoPopular.equals("Café Premium") || productoPopular.equals("Ratón Gamer"); // ambos en 2 pedidos
        boolean v3 = ingresoEntregados == (18 + 12 + 350 + 1200 + 350 + 45); // P002 + P004
        boolean v4 = pedidosPorCliente != null && pedidosPorCliente.get("Ana") == 2L;

        if (v1 && v3 && v4) {
            System.out.println(">> CORRECTO: Dashboard de ventas generado.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] mejorCliente=Lucía(1595), ingresoEntregados=1975, Ana tiene 2 pedidos.");
        }
    }
}
