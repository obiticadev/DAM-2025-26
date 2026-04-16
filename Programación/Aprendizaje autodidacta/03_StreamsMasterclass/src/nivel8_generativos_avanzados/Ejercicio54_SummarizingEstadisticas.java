package nivel8_generativos_avanzados;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import modelos.Producto;

/**
 * EJERCICIO 54 — SUMMARIZING Y ESTADÍSTICAS COMBINADAS (SIN GUÍA)
 * 
 * Objetivo: Usar summarizingDouble para obtener estadísticas completas en una sola pasada.
 */
public class Ejercicio54_SummarizingEstadisticas {

    public static void demostracion() {
        System.out.println("--- SUMMARIZING: ESTADÍSTICAS EN UNA PASADA ---");
        System.out.println("Collectors.summarizingDouble(fn) devuelve DoubleSummaryStatistics");
        System.out.println("con: getCount(), getSum(), getMin(), getMax(), getAverage().\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 54: ANÁLISIS ESTADÍSTICO DE CATÁLOGO ---");
        List<Producto> catalogo = new ArrayList<>();
        catalogo.add(new Producto("Laptop Pro", "Electrónica", 1200.0, 15, true));
        catalogo.add(new Producto("Ratón Gamer", "Electrónica", 45.0, 120, true));
        catalogo.add(new Producto("Camiseta Dev", "Ropa", 18.0, 200, true));
        catalogo.add(new Producto("Teclado Mecánico", "Electrónica", 89.0, 50, true));
        catalogo.add(new Producto("Café Premium", "Alimentación", 12.0, 500, true));
        catalogo.add(new Producto("Monitor 4K", "Electrónica", 350.0, 8, true));
        catalogo.add(new Producto("Té Matcha", "Alimentación", 15.0, 300, true));
        catalogo.add(new Producto("Sudadera Code", "Ropa", 35.0, 80, true));

        // TODO 1: Obtén las estadísticas completas de precios de todo el catálogo.
        // Extrae: count, sum, min, max y average.
        DoubleSummaryStatistics statsGlobal = null; // <- Escribe aquí

        // TODO 2: Obtén estadísticas de precios POR CATEGORÍA.
        // Resultado: Map<String, DoubleSummaryStatistics>
        Map<String, DoubleSummaryStatistics> statsPorCategoria = null; // <- Escribe aquí

        // TODO 3: Usando statsPorCategoria, obtén el nombre de la categoría con mayor precio medio.
        String categoriaMasCara = ""; // <- Escribe aquí

        // TODO 4: Calcula el valor total del inventario (precio * stock) usando mapToDouble.
        double valorInventario = 0; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = statsGlobal != null && statsGlobal.getCount() == 8
                && statsGlobal.getMin() == 12.0 && statsGlobal.getMax() == 1200.0;
        boolean v2 = statsPorCategoria != null && statsPorCategoria.size() == 3
                && statsPorCategoria.get("Electrónica").getCount() == 4;
        boolean v3 = categoriaMasCara.equals("Electrónica");
        double expectedInv = 1200*15 + 45*120 + 18*200 + 89*50 + 12*500 + 350*8 + 15*300 + 35*80;
        boolean v4 = valorInventario == expectedInv;

        if (v1 && v2 && v3 && v4) {
            System.out.println(">> CORRECTO: Estadísticas avanzadas con summarizing.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] 8 productos, min=12, max=1200, Electrónica=4 items, catMásCara=Electrónica.");
        }
    }
}
