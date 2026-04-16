package nivel15_google_guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * EJERCICIO 52 - EL BIMAP (El Diccionario de Doble Sentido)
 * 
 * Objetivo: Acceder con velocidad extrema O(1) partiendo desde los Values 
 * hasta sus Keys mediante la vista .inverse() garantizada y única de Guava.
 */
public class Ejercicio52_BiMap {

    public static void demostracion() {
        System.out.println("--- DEMO: BIMAP (BIDIRECCIONAL) ---");
        BiMap<Integer, String> empleados = HashBiMap.create();
        
        empleados.put(1, "ana@test.com");
        empleados.put(2, "bob@test.com");
        
        // Cuidado: BiMap ESTÁ OBLIGADO A ESTAR SEGURO DE QUE NO SE DUPLICAN LOS VALORES TAMPOCO!
        try {
            empleados.put(3, "ana@test.com"); // Va a petar porque "ana@test.com" YA EXISTE como VALUE
        } catch (IllegalArgumentException e) {
            System.out.println("BiMap me defendió: No puedes tener dos Value idénticos: " + e.getMessage());
        }

        System.out.println("Sacar a Bob sabiendo su ID: " + empleados.get(2));
        System.out.println("Adivinando el ID sabiendo el Email (Inverse Mágico): " + empleados.inverse().get("ana@test.com"));
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 52: BUSCADOR INVERSO ---");

        // Inicializamos el BiMap de IPs a Hostnames
        BiMap<String, String> redSistemas = HashBiMap.create();
        redSistemas.put("192.168.1.10", "WEB-SERVER");
        redSistemas.put("192.168.1.20", "DB-SERVER");
        redSistemas.put("192.168.1.50", "FIREWALL");

        // TODO 1: Queremos forzar el renombrado. El 'forcePut(Key, Value)' de un BiMap 
        // permite sobrescribir los datos de Values existentes de forma silenciosa e inteligente 
        // desconectando la vieja llave.
        // Utiliza redSistemas.forcePut(...) metiendo: key = "192.168.1.30", value = "WEB-SERVER".
        // (Esto quitará automáticamente la vieja IP del WEB-SERVER original para mantener la Unicidad).
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        redSistemas.forcePut("192.168.1.30", "WEB-SERVER");

        // TODO 2: Busquemos por Nombre. Obtén la IP del "WEB-SERVER" invocando a `.inverse().get(...)` sobre redSistemas.
        // Guárdala en una variable String 'ipActualWeb'.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        String ipActualWeb = redSistemas.inverse().get("WEB-SERVER");

        // --- VALIDACIÓN DETALLADA ---
        boolean okForce = redSistemas.size() == 3 && !redSistemas.containsKey("192.168.1.10");
        boolean okBusqueda = ipActualWeb != null && ipActualWeb.equals("192.168.1.30");

        if (!okForce) System.err.println("-> [TODO 1] Falló: HashBiMap debía desconectar a la antigua IP al hacer el forcePut del Value repetido. Usa forcePut en lugar de put.");
        if (okForce && !okBusqueda) System.err.println("-> [TODO 2] Falló: La búsqueda invertida no recogió la IP actualizada (" + ipActualWeb + ").");

        if (okForce && okBusqueda) {
            System.out.println(">> PERFECTO: Buscar por Valor ya no equivale a un For iterativo estúpido. Es un salto O(1). \033[0;32m [OK]\033[0m");
        }
    }
}
