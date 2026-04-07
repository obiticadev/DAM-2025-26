package nivel5_map_y_streams;

import modelos.DatosPrueba;
import modelos.Producto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * EJERCICIO 20 - LA BIFURCACIÓN DEL CAMINO: partitioningBy
 * 
 * Objetivo: Separar datos en dos cubos exclusivos: Los que cumplen la condición (true) 
 * y los que no la cumplen (false). Genera SIEMPRE un Map<Boolean, List<V>>.
 */
public class Ejercicio20_PartitioningBy {

    public static void demostracion() {
        System.out.println("--- DEMO: Collectors.partitioningBy() ---");
        List<Producto> almacen = DatosPrueba.obtenerProductos();

        // Queremos separar los productos que tienen stock de los que no.
        Map<Boolean, List<Producto>> productosPorStock = almacen.stream()
                .collect(Collectors.partitioningBy(Producto::isEnStock));

        System.out.println("Hay " + productosPorStock.get(true).size() + " productos con stock disponible.");
        System.out.println("Hay " + productosPorStock.get(false).size() + " productos AGOTADOS.");
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 20: FILTRO DE PRODUCTOS PREMIUM ---");
        List<Producto> inventario = DatosPrueba.obtenerProductos();

        // Definimos 'Producto Premium' como aquel cuyo precio sea ESTRÍCTAMENTE MAYOR a 100.
        
        // TODO 1: Usa Collectors.partitioningBy con una Lambda que evalúe si el precio > 100.
        Map<Boolean, List<Producto>> separacionPremium = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Imprime el nombre de todos los productos Premium recorriendo la lista asocidada a 'true'.
        System.out.println("Listado VIP:");
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Imprime cuántos productos baratos (no premium) tenemos. (la lista asociada a 'false').
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = separacionPremium != null && separacionPremium.containsKey(true) && separacionPremium.containsKey(false);
        boolean ok2 = separacionPremium != null && separacionPremium.get(true).size() == 2; // Portátil y Mesa (Silla 150 > 100? ah wait, Silla es 150, Portatil 1200, Mesa 200 => Son 3) ... Wait.
                                                                                           // Portátil: 1200.5, Silla: 150, Mesa: 200. => 3 premiums!
        boolean valCorrecta = separacionPremium != null && separacionPremium.get(true).size() == 3;
        
        if (ok1 && valCorrecta) {
            System.out.println(">> CORRECTO: partitioningBy es tu mejor aliado para filtros de 'aprueba/suspende', 'caro/barato'.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa la condición de p -> p.getPrecio() > 100. Esperamos 3 true y 2 false.");
        }
    }
}
