package nivel7_experto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import modelos.Producto;

/**
 * EJERCICIO 45 — RETO EXPERTO: TOP-N POR CATEGORÍA
 * 
 * SIN PISTAS. groupingBy con downstream collector complejo.
 */
public class Ejercicio45_TopNPorCategoria {

    public static void demostracion() {
        System.out.println("=== RETO EXPERTO 45: TOP-N POR CATEGORÍA ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 45: TOP 2 PRODUCTOS MÁS CAROS POR CATEGORÍA ---");
        List<Producto> catalogo = new ArrayList<>();
        catalogo.add(new Producto("Laptop Pro", "Electrónica", 1200.0, 15, true));
        catalogo.add(new Producto("Ratón Gamer", "Electrónica", 45.0, 120, true));
        catalogo.add(new Producto("Monitor 4K", "Electrónica", 350.0, 8, true));
        catalogo.add(new Producto("Webcam HD", "Electrónica", 75.0, 60, true));
        catalogo.add(new Producto("Camiseta Dev", "Ropa", 18.0, 200, true));
        catalogo.add(new Producto("Sudadera Java", "Ropa", 35.0, 80, true));
        catalogo.add(new Producto("Gorra Git", "Ropa", 12.0, 150, true));
        catalogo.add(new Producto("Café Premium", "Alimentación", 12.0, 500, true));
        catalogo.add(new Producto("Té Matcha", "Alimentación", 15.0, 300, true));

        // TODO 1: Agrupa por categoría y dentro de cada grupo obtén los 2 nombres
        // de los productos más caros, ordenados de más caro a menos caro.
        // Resultado: Map<String, List<String>>
        // Electrónica -> [Laptop Pro, Monitor 4K]
        // Ropa -> [Sudadera Java, Camiseta Dev]
        // Alimentación -> [Té Matcha, Café Premium]
        Map<String, List<String>> top2PorCategoria = null; // <- Escribe aquí

        // TODO 2: Precio medio de los top-2 de cada categoría
        // Electrónica: (1200+350)/2 = 775, Ropa: (35+18)/2 = 26.5, Alimentación: (15+12)/2 = 13.5
        Map<String, Double> mediaTop2 = null; // <- Escribe aquí (puedes reutilizar el pipeline)

        // --- VALIDACIÓN ---
        boolean v1 = top2PorCategoria != null
                && top2PorCategoria.get("Electrónica").get(0).equals("Laptop Pro")
                && top2PorCategoria.get("Electrónica").get(1).equals("Monitor 4K")
                && top2PorCategoria.get("Ropa").get(0).equals("Sudadera Java");
        boolean v2 = mediaTop2 != null && mediaTop2.get("Electrónica") == 775.0;

        if (v1 && v2) {
            System.out.println(">> CORRECTO: Top-N por categoría con downstream collectors.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Electrónica=[Laptop Pro, Monitor 4K](media 775), Ropa=[Sudadera,Camiseta], Alim=[Té,Café].");
        }
    }
}
