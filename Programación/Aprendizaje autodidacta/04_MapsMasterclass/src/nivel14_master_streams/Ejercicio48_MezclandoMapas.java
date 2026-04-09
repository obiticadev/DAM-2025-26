package nivel14_master_streams;

import java.util.HashMap;
import java.util.Map;

/**
 * EJERCICIO 48 - MERGING AVANZADO Y REDUCCIÓN DE COLISIONES
 * 
 * Objetivo: Fusionar "al vuelo" los valores de una tabla sobre otra
 * con reglas personalizadas de negocio.
 */
public class Ejercicio48_MezclandoMapas {

    public static void demostracion() {
        System.out.println("--- DEMO: Map.merge() MANUAL ---");
        Map<String, Integer> baseDatos = new HashMap<>(Map.of("Luis", 100));
        
        System.out.println("Estado BD Inicial: " + baseDatos);

        // Caso 1: Ana NO existe. El merge la CREA insertando el valor (50).
        baseDatos.merge("Ana", 50, (viejo, nuevo) -> viejo + nuevo);
        
        // Caso 2: Luis SÍ existe (con 100). Llegan nuevos 20 puntos para él.
        // La Lambda captura y dice: Ok, suma viejo + nuevo = 120. (Sobrescribe con 120).
        baseDatos.merge("Luis", 20, (viejo, nuevo) -> viejo + nuevo);
        
        System.out.println("Estado BD Post-Merge: " + baseDatos);
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 48: INTEGRACIÓN DE SERVIDORES ---");

        Map<String, Integer> serverUS = new HashMap<>(Map.of("JuegoA", 500, "JuegoB", 200));
        Map<String, Integer> serverEU = Map.of("JuegoB", 300, "JuegoC", 100);

        // TODO: Queremos FUSIONAR los datos de 'serverEU' DENTRO de 'serverUS' iterando serverEU.
        // Usarás un forEach sobre entrySet para iterar a EU.
        // Y por cada iteración, vas a llamar a `serverUS.merge(k, v, lambda...)`.
        // Regla de Negocio: Si hay colisión (JuegoB), se debe quedar con el NÚMERO MÁS GRANDE (Math.max).
        
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN DETALLADA ---
        boolean okJuegoA = serverUS.getOrDefault("JuegoA", 0) == 500;
        boolean okJuegoB = serverUS.getOrDefault("JuegoB", 0) == 300; // Colisión. Era 200 vs 300. Max es 300.
        boolean okJuegoC = serverUS.getOrDefault("JuegoC", 0) == 100;

        if (!okJuegoB) System.err.println("-> [TODO] Falló: JuegoB debió quedar como 300. Te quedó como " + serverUS.get("JuegoB") + ". Revisa Math.max.");
        if (!okJuegoC) System.err.println("-> [TODO] Falló: JuegoC no fue migrado.");
        
        if (okJuegoA && okJuegoB && okJuegoC) {
            System.out.println(">> PERFECTO: Dominas la fusión sin tener que escribir if(contains){put}else{put}. O(1) elegansísima. \033[0;32m [OK]\033[0m");
        }
    }
}
