package nivel5_avanzado_collectors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import modelos.Producto;

/**
 * EJERCICIO 33 — CAMBIO DE MODELO: PRODUCTOS CON STREAMS (CON GUÍA)
 * 
 * Objetivo: Aplicar todo lo aprendido con un modelo diferente (Producto).
 */
public class Ejercicio33_ProductosConStreams {

    public static void demostracion() {
        System.out.println("--- CAMBIO DE CONTEXTO: CATÁLOGO DE PRODUCTOS ---");
        System.out.println("Hasta ahora has trabajado con Empleado. Ahora cambiamos a Producto.");
        System.out.println("Mismas herramientas, distinto dominio. Esto refuerza la abstracción.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 33: ANÁLISIS DE CATÁLOGO ---");
        List<Producto> catalogo = new ArrayList<>();
        catalogo.add(new Producto("Laptop Pro", "Electrónica", 1200.0, 15, true));
        catalogo.add(new Producto("Ratón Gamer", "Electrónica", 45.0, 120, true));
        catalogo.add(new Producto("Camiseta Dev", "Ropa", 18.0, 200, true));
        catalogo.add(new Producto("Teclado Mecánico", "Electrónica", 89.0, 0, false));
        catalogo.add(new Producto("Café Premium", "Alimentación", 12.0, 500, true));
        catalogo.add(new Producto("Monitor 4K", "Electrónica", 350.0, 8, true));
        catalogo.add(new Producto("IDE License", "Software", 150.0, 999, true));

        // TODO 1: Filtra productos disponibles, ordénalos por precio descendente
        // y recoge los nombres en una List<String>.
        List<String> disponiblesCaros = null; // <- Escribe aquí

        // TODO 2: Agrupa los productos por categoría y cuenta cuántos hay en cada una.
        Map<String, Long> conteoPorCategoria = null; // <- Escribe aquí

        // TODO 3: Calcula el valor total del inventario (precio * stock) de productos disponibles.
        double valorInventario = 0; // <- Escribe aquí usando mapToDouble y sum

        // --- VALIDACIÓN ---
        boolean v1 = disponiblesCaros != null && disponiblesCaros.size() == 6
                && disponiblesCaros.get(0).equals("Laptop Pro");
        boolean v2 = conteoPorCategoria != null && conteoPorCategoria.get("Electrónica") == 4L;
        boolean v3 = valorInventario == (1200*15 + 45*120 + 18*200 + 12*500 + 350*8 + 150*999);

        if (v1 && v2 && v3) {
            System.out.println(">> CORRECTO: Catálogo analizado con streams sobre modelo Producto.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] v1: 6 disponibles, primero=Laptop Pro. v2: Electrónica=4. v3: valorInventario=" + (1200*15 + 45*120 + 18*200 + 12*500 + 350*8 + 150*999));
        }
    }
}
