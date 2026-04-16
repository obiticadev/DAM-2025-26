package nivel5_map_y_streams;

import modelos.DatosPrueba;
import modelos.Producto;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * EJERCICIO 16 - CONVERSIÓN DIRECTA CON toMap()
 * 
 * Objetivo: Coger un List<Objeto> y transformarlo en un Map<Clave, Objeto>
 * indexando por un identificador único que garantice rápidez de búsqueda O(1) a futuro.
 */
public class Ejercicio16_ToMapBasico {

    public static void demostracion() {
        System.out.println("--- DEMO: Collectors.toMap() ---");
        List<Producto> productos = DatosPrueba.obtenerProductos();
        
        // Vamos a crear un Diccionario (Map) donde la clave es el ID del producto
        // y el valor es EL MISMO producto instanciado.
        Map<String, Producto> mapaPorId = productos.stream()
                .collect(Collectors.toMap(
                        Producto::getId,         // Key Mapper (Extrae de P1 -> "P1")
                        Function.identity()      // Value Mapper (Usa el propio Producto como valor. Es lo mismo que p -> p)
                ));

        System.out.println("Producto P3 extraído del mapa al instante: ");
        System.out.println(mapaPorId.get("P3"));
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 16: MAPA DE NOMBRES Y PRECIOS ---");
        List<Producto> inventario = DatosPrueba.obtenerProductos();

        // TODO 1: Queremos un Map<String, Double> donde la clave sea el NOMBRE del producto
        // y el valor sea su PRECIO.
        // Utiliza inventario.stream().collect(...)
        // PISTA: Para el valueMapper usa Producto::getPrecio en vez de Function.identity()
        Map<String, Double> catalogoPrecios = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Una vez generado el mapa, obtén el precio del "Teclado Mecánico"
        // y guárdalo en la variable. Usa el mapa, NO el stream.
        Double precioTeclado = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Elimina la "Silla Ergonómica" del mapa (operación CRUD básica)
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = catalogoPrecios != null && catalogoPrecios.size() == 4; // Eran 5, borramos 1
        boolean ok2 = precioTeclado != null && precioTeclado == 85.00;
        boolean ok3 = catalogoPrecios != null && !catalogoPrecios.containsKey("Silla Ergonómica");

        if (ok1 && ok2 && ok3) {
            System.out.println(">> CORRECTO: Indexar listas a Maps usando Streams es el pan de cada día.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa las lambdas de toMap o la eliminación.");
        }
    }
}
