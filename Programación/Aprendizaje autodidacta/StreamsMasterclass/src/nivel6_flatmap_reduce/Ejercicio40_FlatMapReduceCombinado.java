package nivel6_flatmap_reduce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import modelos.Pedido;
import modelos.Producto;

/**
 * EJERCICIO 40 — FLATMAP + REDUCE + GROUPINGBY COMBINADOS (SIN GUÍA)
 * 
 * Objetivo: Pipeline complejo que combina flatMap, reduce y collectors avanzados.
 */
public class Ejercicio40_FlatMapReduceCombinado {

    public static void demostracion() {
        System.out.println("--- COMBINANDO TODO: FLATMAP + REDUCE + COLLECTORS ---");
        System.out.println("Los pipelines más potentes combinan varias herramientas.");
        System.out.println("flatMap para aplanar, filter para seleccionar, groupingBy para agrupar,");
        System.out.println("reduce para acumular. Sin pistas.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 40: INFORME DE VENTAS POR CATEGORÍA ---");
        Producto laptop = new Producto("Laptop Pro", "Electrónica", 1200.0, 15, true);
        Producto raton = new Producto("Ratón Gamer", "Electrónica", 45.0, 120, true);
        Producto camiseta = new Producto("Camiseta Dev", "Ropa", 18.0, 200, true);
        Producto cafe = new Producto("Café Premium", "Alimentación", 12.0, 500, true);
        Producto monitor = new Producto("Monitor 4K", "Electrónica", 350.0, 8, true);
        Producto sudadera = new Producto("Sudadera Java", "Ropa", 35.0, 80, true);

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("P001", "Cliente A", Arrays.asList(laptop, raton, camiseta), false));
        pedidos.add(new Pedido("P002", "Cliente B", Arrays.asList(cafe, monitor, sudadera), true));
        pedidos.add(new Pedido("P003", "Cliente C", Arrays.asList(raton, cafe, camiseta), false));

        // TODO 1: Usa flatMap para obtener TODOS los productos de todos los pedidos,
        // agrúpalos por categoría y suma el precio total por categoría.
        // Resultado: Map<String, Double> categoría -> suma de precios
        Map<String, Double> ventasPorCategoria = null; // <- Escribe aquí

        // TODO 2: Encuentra el producto MÁS BARATO de todos los pedidos (usando flatMap + min).
        String productoMasBarato = "Ninguno"; // <- Escribe aquí

        // TODO 3: ¿Cuántas categorías DISTINTAS aparecen en todos los pedidos?
        long categoriasDistintas = 0; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = ventasPorCategoria != null
                && ventasPorCategoria.get("Electrónica") == (1200 + 45 + 350 + 45)
                && ventasPorCategoria.get("Ropa") == (18 + 35 + 18);
        boolean v2 = productoMasBarato.equals("Café Premium");
        boolean v3 = categoriasDistintas == 3;

        if (v1 && v2 && v3) {
            System.out.println(">> CORRECTO: Pipeline combinado flatMap + groupingBy + min.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Electrónica=1640, Ropa=71, Alimentación=24. MásBarato=Café Premium. Categorías=3.");
        }
    }
}
