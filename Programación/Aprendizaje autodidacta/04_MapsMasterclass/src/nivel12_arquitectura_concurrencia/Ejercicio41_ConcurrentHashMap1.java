package nivel12_arquitectura_concurrencia;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * EJERCICIO 41 - CONCURRENCIA MÁXIMA I (ConcurrentHashMap)
 * 
 * Objetivo: Desatar a la bestia atómica de Java. Usar operaciones 
 * de sub-bloqueo segmentado en ConcurrentHashMap de manera segura.
 */
public class Ejercicio41_ConcurrentHashMap1 {

    public static void demostracion() {
        System.out.println("--- DEMO: CONCURRENTHASHMAP EN ACCIÓN ---");
        ConcurrentMap<String, String> multiCoreMap = new ConcurrentHashMap<>();
        
        multiCoreMap.put("Core1", "Ok");

        // ConcurrentHashMap no tolera NULOS. (En Java vanilla multihilo si un map.get te da null
        // no sabes si es que no existe o si es que otro hilo lo coló como "null" explícitamente).
        try {
            multiCoreMap.put("Core2", null); // Excepción inmediata
        } catch (NullPointerException e) {
            System.out.println("Protección atómica NULA testeada con éxito. NullPointerException atrapado.");
        }

        // Operacion atómica garantizada libre de condiciones de carrera (Race Condition Lock-Free)
        String r = multiCoreMap.putIfAbsent("Core1", "IntentodeHackeo"); // Si "Core1" ya estaba asignado en la fraccion de milisegundo entre dos hilos, se bloquea.
        System.out.println("Valor protegido de sobreescrituras en multihilo. Conservé: " + multiCoreMap.get("Core1"));
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 41: BLOQUEOS ATÓMICOS EN CAJAS SEGURAS ---");
        
        // TODO 1: Inicializa 'mapaConcurrente' como un ConcurrentHashMap (No un HashMap!).
        ConcurrentMap<Integer, Boolean> mapaConcurrente = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Vamos a simular un Race Condition. 
        // Dos procesos (o tú escribiéndolo estáticamente) intentan reclamar el recurso número '999'.
        // Paso A: Usa putIfAbsent para el Key 999 dándole el value BOOLEAN 'true' (Ganó proceso 1).
        // Paso B: Usa putIfAbsent para el Key 999 dándole el value BOOLEAN 'false' (Llegó proceso 2 e intenta reclamar).
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Utiliza un "boolean final" o comprueba en la validación:
        // Intenta insertar null como Value para causar la NPE protectora interna usando put().
        // Hazlo con try-catch(NullPointerException e). Si te salta, pon a 'true' tu variable 'pruebaNulaAtascada'.
        boolean pruebaNulaAtascada = false;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN DETALLADA ---
        boolean ok1 = mapaConcurrente != null;

        if (!ok1) {
            System.err.println("-> [TODO 1] Falló: Mapa Atómico no creado.");
        } else {
            Boolean ganador = mapaConcurrente.get(999);
            if (ganador == null || !ganador) System.err.println("-> [TODO 2] Falló: Si usabas putIfAbsent, el 'true' original debía resistir. Fue sobreescrito (usaste put() normal).");
            if (!pruebaNulaAtascada) System.err.println("-> [TODO 3] Falló: No forzaste ni comprobaste el NPE defensivo metiendo Null.");

            if (ganador != null && ganador && pruebaNulaAtascada) {
                System.out.println(">> PERFECTO: Tienes las llaves de la concurrencia segura sin monitor global. \033[0;32m [OK]\033[0m");
            }
        }
    }
}
