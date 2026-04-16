package nivel5_map_y_streams;

import modelos.DatosPrueba;
import modelos.Producto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * EJERCICIO 17 - CHOQUE DE TITANES: toMap CON COLISIONES
 * 
 * Objetivo: Qué pasa cuando la función KeyMapper genera una clave
 * que ya estaba en el mapa. Cómo usar la función de combinación (MergeFunction).
 */
public class Ejercicio17_ToMapColisiones {

    public static void demostracion() {
        System.out.println("--- DEMO: RESOLVIENDO COLISIONES EN toMap() ---");
        List<Producto> todoElStock = DatosPrueba.obtenerProductos();
        
        // Si intentamos hacer un Map donde la clave sea la "Categoría",
        // P1 y P2 tienen "Electrónica". ¡Es una colisión! Si lo hacemos con dos parámetros lanzará IllegalStateException.
        
        // Usamos la variante de 3 parámetros. El tercero te dice: 
        // "Oye, la llave 'Electrónica' ya existe (viejo). Y ahora viene otro con la misma llave (nuevo). ¿Cual meto?"
        Map<String, Producto> unProductoPorCat = todoElStock.stream()
                .collect(Collectors.toMap(
                        Producto::getCategoria,
                        p -> p,
                        (productoViejo, productoNuevo) -> productoNuevo  // << Merge Function: Nos quedamos con el último que llegue
                ));

        System.out.println("Resultado: Un representante por categoría: \n" + unProductoPorCat);
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 17: MAPA DE PRECIOS EXTREMOS ---");
        List<Producto> inventario = DatosPrueba.obtenerProductos();

        // TODO 1: Queremos un Map<String, Double> donde la clave sea la CATEGORIA
        // y el valor sea el PRECIO MÁS CARO registrado en esa categoría.
        // PISTA: Tu valueMapper es extraer el precio. Tu MergeFunction debe comparar (precioA, precioB) 
        // y devolver el Math.max(precioA, precioB).
        Map<String, Double> preciosMaximos = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Imprime el mapa usando forEach para ver cuál es el tope de precio en Electrónica y en Mobiliario.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Haz OTRO diccionario llamado 'preciosAcumulados' donde la llave sea la CATEGORÍA
        // y el valor sea la SUMA TOTAL de todo el precio de esa categoría.
        // Usa la misma lógica pero en lugar de Math.max, lógicamente los sumas.
        Map<String, Double> preciosAcumulados = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = preciosMaximos != null && preciosMaximos.get("Electrónica") == 1200.50;
        boolean ok2 = preciosAcumulados != null && preciosAcumulados.get("Mobiliario") == 350.0;

        if (ok1 && ok2) {
            System.out.println("\n>> CORRECTO: Sabes lidiar con colisiones lógicas como un pro.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println("\n>> [ERROR] Los cálculos de max e int de acumulación fallaron.");
        }
    }
}
