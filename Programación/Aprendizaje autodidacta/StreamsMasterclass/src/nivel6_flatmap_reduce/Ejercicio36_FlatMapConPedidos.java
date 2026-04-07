package nivel6_flatmap_reduce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import modelos.Pedido;
import modelos.Producto;

/**
 * EJERCICIO 36 — FLATMAP CON PEDIDOS: APLANAR PRODUCTOS DE MÚLTIPLES PEDIDOS (SIN GUÍA)
 * 
 * Objetivo: Usar flatMap sobre el modelo Pedido para acceder a los productos internos.
 */
public class Ejercicio36_FlatMapConPedidos {

    public static void demostracion() {
        System.out.println("--- FLATMAP CON OBJETOS REALES ---");
        System.out.println("Cada Pedido tiene una List<Producto>. Si quieres TODOS los productos");
        System.out.println("de TODOS los pedidos en un solo stream:");
        System.out.println("  pedidos.stream().flatMap(p -> p.getProductos().stream())");
        System.out.println("Esto es la clave para trabajar con datos jerárquicos.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 36: TODOS LOS PRODUCTOS DE TODOS LOS PEDIDOS ---");
        Producto laptop = new Producto("Laptop Pro", "Electrónica", 1200.0, 15, true);
        Producto raton = new Producto("Ratón Gamer", "Electrónica", 45.0, 120, true);
        Producto camiseta = new Producto("Camiseta Dev", "Ropa", 18.0, 200, true);
        Producto cafe = new Producto("Café Premium", "Alimentación", 12.0, 500, true);
        Producto monitor = new Producto("Monitor 4K", "Electrónica", 350.0, 8, true);

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("P001", "Cliente A", Arrays.asList(laptop, raton), false));
        pedidos.add(new Pedido("P002", "Cliente B", Arrays.asList(camiseta, cafe, monitor), true));
        pedidos.add(new Pedido("P003", "Cliente C", Arrays.asList(raton, cafe), false));

        // TODO 1: Obtén TODOS los productos de TODOS los pedidos en una sola lista plana.
        List<Producto> todosProductos = null; // <- Escribe aquí con flatMap

        // TODO 2: Obtén los nombres ÚNICOS de productos de pedidos NO entregados.
        List<String> nombresNoEntregados = null; // <- Escribe aquí: filtra pedidos, flatMap productos, distinct

        // TODO 3: Calcula el precio total de TODOS los productos de TODOS los pedidos.
        double precioTotal = 0; // <- Escribe aquí con flatMap + mapToDouble + sum

        // --- VALIDACIÓN ---
        boolean v1 = todosProductos != null && todosProductos.size() == 7;
        boolean v2 = nombresNoEntregados != null && nombresNoEntregados.size() == 3;
        boolean v3 = precioTotal == (1200 + 45 + 18 + 12 + 350 + 45 + 12);

        if (v1 && v2 && v3) {
            System.out.println(">> CORRECTO: flatMap sobre Pedido->Producto dominado.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] todosProductos=7, nombresNoEntregados=3 (Laptop,Ratón,Café), precioTotal=" + (1200+45+18+12+350+45+12));
        }
    }
}
