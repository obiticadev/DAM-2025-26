package nivel9_inmutabilidad;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * EJERCICIO 32 - COPIA FÍSICA vs VISTA LÓGICA
 * 
 * Objetivo: Entender uno de los bugs de seguridad (Memory Leaks o mutaciones indeseadas)
 * más comunes en las APIs grandes: Devolver un Collections.unmodifiableMap y que el
 * creador de la clase mute el estado base en segundo plano sin que el cliente lo sepa.
 */
public class Ejercicio32_CopyOfVsUnmodifiable {

    public static void demostracion() {
        System.out.println("--- DEMO: VISTA VS COPIA ---");
        Map<String, String> interno = new HashMap<>(); // Base de datos
        interno.put("claveSec", "xyz");

        // Approach 1 (Agujero):
        Map<String, String> expuestoUnmodifiable = Collections.unmodifiableMap(interno);
        // Approach 2 (Blindaje):
        Map<String, String> expuestoCopyOf = Map.copyOf(interno);

        // ¡El Hacker muta la estructura interna (o un proceso asíncrono lo hace)!
        interno.put("claveSec", "robada!");

        System.out.println("Estado Unmodifiable (Es transparente): " + expuestoUnmodifiable); // Se cuela el cambio!
        System.out.println("Estado CopyOf (Es un clon sellado): " + expuestoCopyOf);  // Está protegido del mutador
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 32: SELLAR LA CONFIGURACIÓN ---");
        
        Map<String, Integer> inventarioBase = new HashMap<>();
        inventarioBase.put("Poción", 5);
        inventarioBase.put("Espada", 1);

        // TODO 1: Crea una vista NO modificable basada en inventarioBase llamada 'vistaInventario'
        // usando Collections.unmodifiableMap.
        Map<String, Integer> vistaInventario = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Crea una COPIA ABSOLUTA e independiente basada en inventarioBase llamada 'copiaInventario'
        // usando Map.copyOf() (Añadido en Java 10).
        Map<String, Integer> copiaInventario = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Modifica el 'inventarioBase' sumando +5 a la "Poción". (Debería tener 10 ahora).
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN DETALLADA ---
        boolean pasoCreacion = vistaInventario != null && copiaInventario != null;
        boolean vistaAfectada = false;
        boolean copiaProtegida = false;
        
        if (pasoCreacion) {
            vistaAfectada = vistaInventario.get("Poción") == 10;
            copiaProtegida = copiaInventario.get("Poción") == 5;
        }

        if (!pasoCreacion) System.err.println("-> [TODO 1 o 2] Falló: No creaste los mapas 'vistaInventario' o 'copiaInventario'.");
        else if (inventarioBase.get("Poción") != 10) System.err.println("-> [TODO 3] Falló: No subiste a 10 la Poción en inventarioBase.");
        else if (!vistaAfectada) System.err.println("-> [TODO 1 / 3] Falló: La vista no refleja el cambio en la base, algo hiciste mal.");
        else if (!copiaProtegida) System.err.println("-> [TODO 2] Falló: La copia no fue un clon duro y se vio afectada.");

        if (pasoCreacion && vistaAfectada && copiaProtegida) {
            System.out.println(">> PERFECTO: Tu API ya es robusta contra mutaciones por referencia trasera. [OK]\033[0;32m [OK]\033[0m");
        }
    }
}
