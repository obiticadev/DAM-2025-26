package nivel2_recorridos_avanzados;

import java.util.HashMap;
import java.util.Map;

/**
 * EJERCICIO 05 - ITERACIÓN MODERNA: forEach y Lambdas
 * 
 * Objetivo: Sustituir el bucle de nivel 3 (EntrySet) por la elegancia de Java 8
 * usando el método forEach que acepta un BiConsumer<K, V>.
 */
public class Ejercicio05_ForEachLambda {

    public static void demostracion() {
        System.out.println("--- DEMO: forEach() Y LAMBDAS ---");
        Map<String, String> configuracion = new HashMap<>();
        configuracion.put("Modo", "Oscuro");
        configuracion.put("Sonido", "Activado");
        configuracion.put("Notificaciones", "Desactivado");

        System.out.println("A través de forEach, recibimos clave y valor directamente:");
        configuracion.forEach((llave, valor) -> {
            System.out.println("   Ajuste de " + llave + ": " + valor);
        });
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 05: CONTROL DE INVENTARIO CON LAMBDAS ---");
        Map<String, Integer> stock = new HashMap<>();
        stock.put("Termo", 15);
        stock.put("Mochila", 3);
        stock.put("Libreta", 0);
        stock.put("Bolígrafo", 50);

        // TODO 1: Usa forEach para imprimir los productos con CERO stock de la siguiente manera:
        // "¡ALERTA! El producto {llave} se ha agotado."
        System.out.println("Sección de alertas:");
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Cuidado con las modificaciones en forEach.
        // A diferencia de entrySet().iterator(), NO PUEDES añadir o borrar elementos
        // estructurales del Map directamente dentro del forEach() porque lanzaría un ConcurrentModificationException.
        // Para practicar, en lugar de borrar, vamos a crear una variable auxiliar.
        int[] productosEnPeligro = {0}; // Usamos un array porque la variable en lambda debe ser effectively final
        
        // TODO 3: Usa forEach para recorrer nuevamente y contar cuántos productos tienen MENOS de 5 unidades.
        // Incrementa la posición [0] del array productosEnPeligro cada vez que ocurra.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 4: Usa forEach SIN bloque de llaves {} (versión acortada) para imprimir todos los artículos 
        // de la forma: "{llave} - {valor} uds."
        System.out.println("\nResumen general de inventario:");
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        if (productosEnPeligro[0] == 2) { // Mochila (3) y Libreta (0)
            System.out.println("\n>> CORRECTO: Controlas la iteración lambda y entiendes sus limitaciones estructurales.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println("\n>> [ERROR] Has calculado mal los productos en peligro. Había 2 (Mochila y Libreta). Encontraste: " + productosEnPeligro[0]);
        }
    }
}
