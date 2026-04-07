package nivel12_arquitectura_concurrencia;

import java.util.HashMap;
import java.util.Map;

/**
 * EJERCICIO 40 - CACHÉ MEMOIZATION 
 * 
 * Objetivo: Acortar tiempos de carga mediante un mapa de memoria Caché
 * gestionado limpiamente con computeIfAbsent y lambdas perezosas.
 */
public class Ejercicio40_CacheMemoization {

    // Simula una base de datos pesadísima.
    static Double queryRemotaCarisima(String query) {
        System.out.println("   [ROUTER BDD] ¡Contactando con BDD... Gastando CPU y Dinero para: " + query + "!");
        // try { Thread.sleep(500); } catch (Exception e) {} // (Descomentar para sentir el dolor).
        return query.length() * 3.14; 
    }

    public static void demostracion() {
        System.out.println("--- DEMO: CACHÉ E INTELIGENCIA PEREZOSA ---");
        Map<String, Double> memoria = new HashMap<>();

        System.out.println("- Petición 1 (Juan):");
        memoria.computeIfAbsent("SELECT_SALARIO", Ejercicio40_CacheMemoization::queryRemotaCarisima);
        
        System.out.println("- Petición 2 (Ana pide la misma query):");
        memoria.computeIfAbsent("SELECT_SALARIO", Ejercicio40_CacheMemoization::queryRemotaCarisima);
        
        System.out.println("Fíjate que Ana NO disparó el log de la BDD. Respondió instantáneo de la RAM.");
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 40: MEMOIZACIÓN DEL CÁLCULO GIGANTE ---");

        // Creamos nuestro diccionario que hará de Cache para Cálculos del Factoreal
        Map<Integer, Long> cacheFibonacci = new HashMap<>();
        
        // Arrays auxiliares para comprobar si realmente hiciste trampa o usaste la caché.
        final int[] contadoresDeLlamadaAlMetodoFalso = {0};

        // TODO 1: Completa la estructura de la CACHE
        // Usa un bucle for-loop que itere UNA SOLA VEZ los siguientes inputs: { 10, 15, 10, 20, 15 }.
        // (Es decir, llamadas repetidas).
        // Y por cada iteración vas a hacer un computeIfAbsent() contra el 'cacheFibonacci'.
        // Tu Lambda de cálculo pesada debe ser una que incremente contadoresDeLlamadaAlMetodoFalso[0]++
        // y devuelva un número long hardcodeado como respuesta de prueba (ej: 999L).
        int[] peticionesEntrantes = { 10, 15, 10, 20, 15 };
        
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Ahora quiero que recuperes (Sin simular) el resultado del input (15) guardado
        // en la caché y lo metas a esta variable usando el Map.
        Long respuestaPara15 = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN DETALLADA ---
        boolean ok1 = cacheFibonacci.size() == 3; // Porque procesó 10, 15, 20 eliminando reps al guardarlos.
        
        if (!ok1) {
            System.err.println("-> [TODO 1] Falló: El caché debe acabar con size()=3.");
        } else {
            if (contadoresDeLlamadaAlMetodoFalso[0] > 3) System.err.println("-> [TODO 1] Falló la Memoization! La lambda lenta se ejecutó " + contadoresDeLlamadaAlMetodoFalso[0] + " veces. NO usaste computeIfAbsent debidamente.");
            else if (contadoresDeLlamadaAlMetodoFalso[0] < 3) System.err.println("-> [TODO 1] Falló la Lambda: El contador de red lenta no se incrementó. (Debió ser 3 veces, 1 por cada llave nueva).");
            
            if (respuestaPara15 == null) System.err.println("-> [TODO 2] Falló: No extrajiste la data almacenada de la Caché.");
            
            if (contadoresDeLlamadaAlMetodoFalso[0] == 3 && respuestaPara15 != null) {
                System.out.println(">> PERFECTO: Has ahorrado el 40% de tiempo de servidor implementando una función matemática Memoizada. \033[0;32m [OK]\033[0m");
            }
        }
    }
}
