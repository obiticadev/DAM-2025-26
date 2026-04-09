package nivel13_magia_arboles;

import java.util.HashMap;
import java.util.Map;

/**
 * EJERCICIO 45 - SIMULANDO UN BIMAP (Dos Direcciones)
 * 
 * Objetivo: Prepararnos para el Nivel 15 de Guava construyendo 
 * artesanalmente en Java Vanilla un "Bi-Map" bidireccional puro.
 * Útil para evitar el espantoso antipatrón de iterar TODO el mapa para buscar por Valor.
 */
public class Ejercicio45_SimularBiMap {

    public static void demostracion() {
        System.out.println("--- DEMO: CÓDIGO SPAGHETTI PARA BUSCAR POR VALOR ---");
        Map<String, String> traductor = new HashMap<>();
        traductor.put("Dog", "Perro");
        
        // Normalmente, buscar la Key "Dog" dado el Valor "Perro" requiere un for feísimo:
        String encontrada = null;
        for(Map.Entry<String, String> ee : traductor.entrySet()) {
            if(ee.getValue().equals("Perro")) encontrada = ee.getKey();
        }
        System.out.println("Coste de búsqueda por Valor para Perro: O(N). Encontrada: " + encontrada);
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 45: CONSTRUIR EL BIMAP (DOBLE MAPA) ---");
        
        // El truco arquitectónico para O(1) recíproco es MANTENER 2 MAPAS FÍSICOS SINCRONIZADOS.
        Map<Integer, String> dbUsuariosById = new HashMap<>();
        Map<String, Integer> dbUsuariosByEmail = new HashMap<>(); // El BiMap

        // TODO 1: Registra 2 usuarios. PEro tienes que registrarlos de forma ESPEJO en ambos mapas.
        // Usuario A: Id 100, Email "juan@test.com"
        // Usuario B: Id 200, Email "ana@test.com"
        // (Debe haber 4 puts en total en tu código).
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Alguien intenta logearse con el correo "ana@test.com".
        // Utiliza el segundo map (dbUsuariosByEmail) para recuperar rápidamente el ID de Ana (que es O(1) puro)
        // y guárdalo en Integer 'idAna'.
        Integer idAna = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Ana borra su cuenta. 
        // Elimínala LLAMANDO A LOS DOS MAPS (remove(200) del primero, remove(ana) del segundo).
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN DETALLADA ---
        boolean ok1 = dbUsuariosById.containsKey(100) && dbUsuariosByEmail.containsKey("juan@test.com");
        boolean okAna = idAna != null && idAna == 200;
        boolean anaBorradaCorrectamente = !dbUsuariosById.containsKey(200) && !dbUsuariosByEmail.containsKey("ana@test.com");
        boolean huerfanos = dbUsuariosById.size() != dbUsuariosByEmail.size();

        if (!ok1) System.err.println("-> [TODO 1] Falló: No insertaste los datos correctamente en los dos mapas espejo.");
        if (ok1 && huerfanos) System.err.println("-> [TODO 1/3] Falló: Uno de los mapas está desincronizado (Tamanos diferentes). Esto es un Bug Severo crítico del Vanilla.");
        if (ok1 && !okAna) System.err.println("-> [TODO 2] Falló: No extrariste el ID de 'ana@test.com' adecuadamente.");
        if (okAna && !anaBorradaCorrectamente) System.err.println("-> [TODO 3] Falló: Ana fue borrada sólo de un mapa y no del otro, dejando estado corrupto.");

        if (ok1 && !huerfanos && okAna && anaBorradaCorrectamente) {
            System.out.println(">> PERFECTO: Esto funciona genial, pero el mantenimiento de Sincronicidad a mano asusta. En la Fase 3, Google Guava nos salvará de teclear doble. \033[0;32m [OK]\033[0m");
        }
    }
}
