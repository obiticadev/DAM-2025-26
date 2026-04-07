package nivel6_operaciones_avanzadas_streams;

import modelos.DatosPrueba;
import modelos.Producto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * EJERCICIO 25 - DOWNSTREAM COLLECTORS: FILTERING (Java 9)
 * 
 * Objetivo: Collectors.filtering() descarta elementos en el empaquetado final
 * PERO, a diferencia del filter() principal del Stream, MANTIENE la estructura de la
 * clave creada aunque su lista acabe vacía.
 */
public class Ejercicio25_GroupingAndFiltering {

    public static void demostracion() {
        System.out.println("--- DEMO: GROUPING BY + FILTERING (Java 9+) ---");
        List<Producto> inventario = DatosPrueba.obtenerProductos();

        // Enfoque clásico: Filtramos antes de agrupar.
        System.out.println("1. Filtro en Stream antes de Agrupar (Solo productos baratos < 50):");
        Map<String, List<Producto>> filtroGlobal = inventario.stream()
                .filter(p -> p.getPrecio() < 50.0)
                .collect(Collectors.groupingBy(Producto::getCategoria));
        System.out.println(filtroGlobal); 
        // ¿Qué pasa con "Mobiliario"? COMO Mínimo cuesta 150, DESAPARECIÓ COMPLETAMENTE DEL MAPA porque no pasó el stream.

        // Enfoque Java 9+: Filtro internamente al agrupar.
        System.out.println("2. Filtro Downstream (Mantiene las llaves del mapa aunque den listas vacías):");
        Map<String, List<Producto>> filtroInterno = inventario.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.filtering(p -> p.getPrecio() < 50.0, Collectors.toList())
                ));
        System.out.println(filtroInterno);
        // Resultado: Electrónica=[Ratón], Mobiliario=[] <-- Se mantuvo el Mobiliario porque se evaluó AL empaquetarlo.
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 25: INFORMACIÓN DE STOCK RETENIENDO CATEGORÍAS ---");
        List<Producto> stockGeneral = DatosPrueba.obtenerProductos();

        // TODO 1: Crea un Map<String, List<Producto>> 'productosEnStockPorCategoria'
        // Queremos saber qué productos están EN STOCK (p.isEnStock() == true) clasificados por su Categoría.
        // PERO ES CRÍTICO retener todas las categorías presentes en el catálogo (incluso si tienen 0 en stock).
        // Usa Collectors.filtering() como downstream de groupingBy.
        Map<String, List<Producto>> productosEnStockPorCategoria = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Imprime y verifica cuántos artículos EN STOCK y DISPONIBLES tiene la categoría "Electrónica"
        // y haz lo mismo con la categoría "Mobiliario"
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = productosEnStockPorCategoria != null && productosEnStockPorCategoria.size() == 2; // Electrónica, Mobiliario
        boolean ok2 = productosEnStockPorCategoria != null && productosEnStockPorCategoria.get("Mobiliario").size() == 1; // La Silla está fuera de stock, la mesa sí está en stock. => Size = 1.
        boolean ok3 = productosEnStockPorCategoria != null && productosEnStockPorCategoria.get("Electrónica").size() == 3;

        if (ok1 && ok2 && ok3) {
            System.out.println(">> CORRECTO: Usar filtering() preserva las claves importantes en tu tabla de datos aunque estén a 0.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa la sintaxis de Collectors.filtering(Predicado, Collectors.toList())");
        }
    }
}
