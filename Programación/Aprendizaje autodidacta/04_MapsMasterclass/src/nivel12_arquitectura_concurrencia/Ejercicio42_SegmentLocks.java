package nivel12_arquitectura_concurrencia;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * EJERCICIO 42 - SEGMENT LOCKS vs SINCRONIZACIÓN GLOBAL
 * 
 * Objetivo: Contrastar a nivel de compilador por qué Collections.synchronizedMap
 * se considera casi "Legacy" frente a ConcurrentHashMap usando métodos BULK.
 */
public class Ejercicio42_SegmentLocks {

    public static void demostracion() {
        System.out.println("--- DEMO: SYNCHRONIZED VS CONCURRENT BULK ---");
        
        // Método Antiguo: Envuelve el mapa en un "synchronized(mutex)". Bloquea el MAPA ENTERO si se accede.
        Map<String, String> legacySeguro = Collections.synchronizedMap(new HashMap<>());
        legacySeguro.put("UserA", "Conectado"); // LOCK GLOBAL
        
        // Método Moderno: 
        ConcurrentHashMap<String, String> chm = new ConcurrentHashMap<>();
        chm.put("Rojo", "Red");
        chm.put("Azul", "Blue");
        chm.put("Verde", "Green");

        // ConcurrentHashMap permite operaciones MAsivas (Bulk).
        // Por ej: search() paralelizado. Le pasas un "Parallelism Threshold". Si le pasas 1, lo hará lo más paralelo posible usando N hilos.
        String encontradoEnHilos = chm.search(1, (k, v) -> {
            if (v.equals("Blue")) return k;
            return null; // El search concurrente ignora los null. Si un hilo devuelve algo real, se corta la búsqueda (Winner).
        });

        System.out.println("Bulk Search Multi-core encontró: " + encontradoEnHilos);
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 42: EL MAPPING PARALELO ---");
        
        // Vamos a instanciar directamente a la clase concurrente para tener acceso a sus métodos Bulk exclusivos.
        ConcurrentHashMap<String, Long> transaccionesTotales = new ConcurrentHashMap<>();
        for (int i = 0; i < 50; i++) {
            transaccionesTotales.put("TRX-" + i, (long)(i * 10)); // 0, 10, 20...
        }

        // TODO 1: Vamos a usar el método Bulk exclusivo ".reduceValues()" de ConcurrentHashMap.
        // Recibe 2 parámetros: (parallelismThreshold, reducerFunction).
        // - Usa 2 como Threshold (Para que si tiene más de 2 elementos de cola se divida en el ForkJoinPool).
        // - Reducer: (acumulador, valorActual) -> acumulador + valorActual (Para sumar todo a lo bestia).
        // Guárdalo en la variable.
        Long sumaBestial = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Oye, si eres conservador, podrías haber convertido esto al antiguo Legacy usando Collections.synchronizedMap().
        // Crea un Map 'mapaViejo' instanciado como HashMap, pero envuelto con el Collections estandar.
        Map<String, Long> mapaViejo = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Imprime el resultado total de 'sumaBestial'
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN DETALLADA ---
        boolean ok1 = mapaViejo != null && mapaViejo.getClass().getName().contains("SynchronizedMap");
        boolean ok2 = sumaBestial != null && sumaBestial == 12250L; // La suma de 0 a 49 * 10 = 12250

        if (!ok2) System.err.println("-> [TODO 1] Falló: La reducción Bulk Concurrente falló. Verifica: reduceValues(2, Long::sum). Acuérdate de recoger el sum() y no el size.");
        if (!ok1) System.err.println("-> [TODO 2] Falló: Debías usar Collections.synchronizedMap(new HashMap<>()).");

        if (ok1 && ok2) {
            System.out.println(">> PERFECTO: Conocimiento absoluto de la rama de sincronización alcanzado. \033[0;32m [OK]\033[0m");
        }
    }
}
