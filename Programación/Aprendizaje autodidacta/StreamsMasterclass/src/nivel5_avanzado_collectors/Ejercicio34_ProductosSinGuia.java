package nivel5_avanzado_collectors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import modelos.Producto;

/**
 * EJERCICIO 34 — ANÁLISIS COMPLETO DE PRODUCTOS SIN GUÍA
 * 
 * Objetivo: Pipeline complejo sobre catálogo de productos sin pistas.
 */
public class Ejercicio34_ProductosSinGuia {

    public static void demostracion() {
        System.out.println("--- ANÁLISIS AVANZADO DE CATÁLOGO ---");
        System.out.println("Mismo modelo Producto, pero ahora sin pistas.");
        System.out.println("Combina filter, map, groupingBy, reduce, Optional y collectors avanzados.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 34: INFORME COMPLETO DEL CATÁLOGO ---");
        List<Producto> catalogo = new ArrayList<>();
        catalogo.add(new Producto("Laptop Pro", "Electrónica", 1200.0, 15, true));
        catalogo.add(new Producto("Ratón Gamer", "Electrónica", 45.0, 120, true));
        catalogo.add(new Producto("Camiseta Dev", "Ropa", 18.0, 200, true));
        catalogo.add(new Producto("Teclado Mecánico", "Electrónica", 89.0, 0, false));
        catalogo.add(new Producto("Café Premium", "Alimentación", 12.0, 500, true));
        catalogo.add(new Producto("Monitor 4K", "Electrónica", 350.0, 8, true));
        catalogo.add(new Producto("IDE License", "Software", 150.0, 999, true));
        catalogo.add(new Producto("Sudadera Java", "Ropa", 35.0, 80, true));

        // TODO 1: Obtén el producto MÁS CARO disponible. Devuelve su nombre o "Ninguno".
        String masCaro = "Ninguno"; // <- Escribe aquí

        // TODO 2: Agrupa los productos disponibles por categoría y obtén el precio medio por categoría.
        // Map<String, Double> con categoría -> precio medio
        Map<String, Double> precioMedioPorCategoria = null; // <- Escribe aquí

        // TODO 3: Crea un String con las categorías únicas ordenadas A-Z separadas por " | "
        String categoriasTexto = ""; // <- Escribe aquí

        // TODO 4: ¿Cuántos productos tienen stock > 100?
        long stockAlto = 0; // <- Escribe aquí

        System.out.println("Más caro disponible: " + masCaro);
        System.out.println("Categorías: " + categoriasTexto);
        System.out.println("Productos con stock alto: " + stockAlto);

        // --- VALIDACIÓN ---
        boolean v1 = masCaro.equals("Laptop Pro");
        boolean v2 = precioMedioPorCategoria != null && precioMedioPorCategoria.size() >= 4;
        boolean v3 = categoriasTexto.contains("Alimentación") && categoriasTexto.contains("Software");
        boolean v4 = stockAlto == 4;

        if (v1 && v2 && v3 && v4) {
            System.out.println(">> CORRECTO: Informe completo del catálogo generado sin ayuda.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] masCaro=Laptop Pro, 4+ categorías con media, categorías con |, stockAlto=4 (Ratón,Camiseta,Café,IDE License,Sudadera -> 5? Revisa: >100 son Ratón(120),Camiseta(200),Café(500),IDE(999)=4.");
        }
    }
}
