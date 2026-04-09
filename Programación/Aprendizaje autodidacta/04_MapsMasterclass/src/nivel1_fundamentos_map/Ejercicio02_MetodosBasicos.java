package nivel1_fundamentos_map;

import java.util.HashMap;
import java.util.Map;

/**
 * EJERCICIO 02 - MÉTODOS ANALÍTICOS Y BULK
 * 
 * Objetivo: Usar métodos como containsKey, containsValue, size, isEmpty, clear y putAll.
 */
public class Ejercicio02_MetodosBasicos {

    public static void demostracion() {
        System.out.println("--- DEMO: MÉTODOS ANALÍTICOS ---");
        Map<String, Double> precios = new HashMap<>();
        precios.put("Pan", 1.20);
        
        System.out.println("containsKey(\"Pan\"): " + precios.containsKey("Pan")); // true O(1) en HashMap
        System.out.println("containsValue(1.20): " + precios.containsValue(1.20)); // true O(n)
        System.out.println("size(): " + precios.size()); // 1
        System.out.println("isEmpty(): " + precios.isEmpty()); // false
        
        precios.clear(); // Vacía el mapa
        System.out.println("Tras clear(), isEmpty(): " + precios.isEmpty() + "\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 02: INVENTARIO DE TIENDA ---");
        Map<String, Integer> inventarioPequeno = new HashMap<>();
        inventarioPequeno.put("Manzanas", 50);
        inventarioPequeno.put("Peras", 20);

        Map<String, Integer> inventarioPrincipal = new HashMap<>();
        inventarioPrincipal.put("Naranjas", 100);
        inventarioPrincipal.put("Plátanos", 30);

        // TODO 1: Traspasa todos los elementos de 'inventarioPequeno' a 'inventarioPrincipal'
        // PISTA: No uses un bucle, hay un método para transferir todo de golpe (Bulk operation).
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Verifica si "Manzanas" existe como CLAVE en el inventarioPrincipal.
        // Guarda el resultado (booleano) en una variable llamada 'tieneManzanas'.
        boolean tieneManzanas = false;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Verifica si hay ALGÚN producto que tenga exactamente 100 unidades (como VALOR).
        // Guarda el resultado (booleano) en la variable 'hayStockCien'.
        boolean hayStockCien = false;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 4: Vacía por completo el 'inventarioPequeno' ya que sus datos han sido transferidos.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        if (inventarioPrincipal.size() == 4 && tieneManzanas && hayStockCien && inventarioPequeno.isEmpty()) {
            System.out.println(">> CORRECTO: Manejo excelente de métodos analíticos y volcado masivo.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Falló alguna validación:");
            System.err.println("   Tamaño inventario principal (esperado 4): " + inventarioPrincipal.size());
            System.err.println("   ¿Tiene manzanas? " + tieneManzanas);
            System.err.println("   ¿Hay stock 100? " + hayStockCien);
            System.err.println("   ¿Inventario pequeño vacío? " + inventarioPequeno.isEmpty());
        }
    }
}
