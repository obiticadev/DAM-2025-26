package nivel9_inmutabilidad;

import java.util.Map;

/**
 * EJERCICIO 31 - MAPAS INMUTABLES (Map.of y Map.ofEntries)
 * 
 * Objetivo: Acostumbrarse a la sintaxis moderna de creación de diccionarios
 * de tamaño fijo y entender sus letales restricciones de seguridad (Nulls prohibidos).
 */
public class Ejercicio31_MapOf {

    public static void demostracion() {
        System.out.println("--- DEMO: Map.of() ---");
        // Una sola línea para crear un diccionario sellado de fábrica.
        Map<String, String> dbConfig = Map.of(
            "url", "jdbc:mysql://localhost",
            "user", "admin",
            "pass", "1234"
        );

        System.out.println("Configuración Base: " + dbConfig);
        try {
            dbConfig.put("timeout", "300"); // PUM! Excepción.
        } catch (UnsupportedOperationException e) {
            System.out.println("¡Intento de modificación interceptado! Es INMUTABLE.");
        }
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 31: ESTADOS DE APLICACIÓN ---");
        
        // TODO 1: Crea un mapa constante llamado 'estadosHTTP' usando Map.ofEntries()
        // y Map.entry(). Incluye los siguientes códigos:
        // 200 -> "OK"
        // 400 -> "Bad Request"
        // 403 -> "Forbidden"
        // 404 -> "Not Found"
        Map<Integer, String> estadosHTTP = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Intenta capturar un UnsupportedOperationException.
        // Escribe un try/catch. Dentro del try, intenta eliminar el estado 200 haciendo estadosHTTP.remove(200).
        // En el catch (UnsupportedOperationException), asigna 'true' a una variable boolean llamada 'intentoBloqueado'.
        boolean intentoBloqueado = false;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Map.of() no tolera NULOS.
        // Haz un try/catch(NullPointerException e). Dentro del try, haz:
        // Map.of("Config", null);
        // Si salta la excepción, pon la variable 'nuloBloqueado' a true.
        boolean nuloBloqueado = false;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN DETALLADA ---
        boolean ok1 = estadosHTTP != null && estadosHTTP.size() == 4;
        
        if (!ok1) System.err.println("-> [TODO 1] Falló: El mapa 'estadosHTTP' no se creó con Map.ofEntries() correctamente.");
        if (!intentoBloqueado) System.err.println("-> [TODO 2] Falló: No se atrapó UnsupportedOperationException al llamar a remove().");
        if (!nuloBloqueado) System.err.println("-> [TODO 3] Falló: No se demostró que Map.of() prohíba nulos atrapando NPE.");

        if (ok1 && intentoBloqueado && nuloBloqueado) {
            System.out.println(">> PERFECTO: Conoces la dureza blindada de las factorías modernas de Java. [OK]\033[0;32m [OK]\033[0m");
        }
    }
}
