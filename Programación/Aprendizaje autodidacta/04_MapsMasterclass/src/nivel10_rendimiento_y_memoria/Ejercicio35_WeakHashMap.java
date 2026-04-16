package nivel10_rendimiento_y_memoria;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * EJERCICIO 35 - WeakHashMap (EL SEÑOR DE LA BASURA)
 * 
 * Objetivo: Integrar un mapa donde las Llaves son "referencias débiles".
 * Si la llave deja de ser usada en el programa, el Garbage Collector barrerá la llave 
 * y automáticamente el WeakHashMap eliminará también el VALOR asociado para liberar memoria.
 */
public class Ejercicio35_WeakHashMap {

    // Clase auxiliar pesada
    static class ImagenPesada {
        String data = "10 MEGA BYTES DE FOTO";
    }

    public static void demostracion() {
        System.out.println("--- DEMO: LA MAGIA DEL GARBAGE COLLECTOR ---");
        Map<Object, String> cacheVolatil = new WeakHashMap<>();

        Object id = new Object(); // Referencia fuerte
        cacheVolatil.put(id, "Datos en caché");

        System.out.println("Caché antes de la purga: " + cacheVolatil.size()); // 1

        id = null; // Cortamos la única cuerda fuerte que sostenía el objeto ID en RAM.
        // Ahora mismo ese objeto flota en la memoria y su única atadura es una cuerda "Débil" (Weak) dentro del WeakHashMap.
        
        System.gc();     // Le SUPLICAMOS al camión de basura que pase (Sugerencia al GC).
        
        try { Thread.sleep(200); } catch (Exception e) {} // Le damos tiempo al GC...

        System.out.println("Caché tras la poda del GC: " + cacheVolatil.size() + " (Desapareció solito!)");
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 35: CACHÉ DE MEMORIA AUTOLIMPIABLE ---");
        
        // TODO 1: Instancia un WeakHashMap<ImagenPesada, String> llamado 'gestorCache'.
        WeakHashMap<ImagenPesada, String> gestorCache = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        ImagenPesada fotoApp = new ImagenPesada();

        // TODO 2: Registra la fotoApp en el gestor y asígnale el valor "Foto cargada".
        // Imprime el size() del gestorCache. (Debe ser 1).
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Elimina la variable, llama al Recolector y espera.
        // fotoApp = null;
        // System.gc();
        // Haz un pequeño try-catch con Thread.sleep(300);
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN DETALLADA ---
        if (gestorCache == null) {
            System.err.println("-> [TODO 1] Falló: El WeakHashMap no fue creado.");
        } else {
            // Evaluamos si el Garbage Collector hizo el trabajo sucio.
            // Nota: En algunas Máquinas Virtuales (JVM), el GC ignora System.gc(). 
            // Si el test te salta como error, es por la heurística de tu JVM, pero la teoría aplica igual.
            if (gestorCache.isEmpty()) {
                System.out.println(">> PERFECTO: Cache limpia gracias a cordones débiles de hilo fantasma. [OK]\033[0;32m [OK]\033[0m");
            } else {
                System.err.println("-> [TODO 3] Falló o el GC decidió no hacer caso. (Asegúrate de hacer fotoApp = null y llamar a System.gc()). El tamaño fue: " + gestorCache.size() + ". Si está bien codificado, puedes darlo por válido dadas las reglas de tu JVM.");
            }
        }
    }
}
