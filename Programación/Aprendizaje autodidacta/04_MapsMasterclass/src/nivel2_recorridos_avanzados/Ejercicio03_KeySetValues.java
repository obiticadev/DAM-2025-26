package nivel2_recorridos_avanzados;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * EJERCICIO 03 - VISTAS PARCIALES: keySet() y values()
 * 
 * Objetivo: Aprender a iterar sobre las llaves o sobre los valores
 * de forma independiente cuando no necesitas todo el conjunto.
 */
public class Ejercicio03_KeySetValues {

    public static void demostracion() {
        System.out.println("--- DEMO: VISTAS PARCIALES DEL MAP ---");
        Map<Integer, String> errores = new HashMap<>();
        errores.put(404, "Not Found");
        errores.put(500, "Internal Server Error");
        errores.put(403, "Forbidden");

        System.out.println("1. Recorriendo solo las LLAVES con keySet():");
        for (Integer codigo : errores.keySet()) {
            System.out.println("   Código de error: " + codigo);
        }

        System.out.println("\n2. Recorriendo solo los VALORES con values():");
        for (String descripcion : errores.values()) {
            System.out.println("   Descripción: " + descripcion);
        }
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 03: EDADES Y NOMBRES ---");
        Map<String, Integer> edades = new HashMap<>();
        edades.put("Ana", 25);
        edades.put("Luis", 30);
        edades.put("Marta", 22);
        edades.put("Pedro", 25);

        // TODO 1: Crea un Set<String> llamado 'nombres' y asígnale la vista de claves del mapa.
        Set<String> nombres = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Calcula la suma TOTAL de todas las edades del mapa utilizando la vista -> .values()
        // PISTA: Haz un bucle for tradicional sobre la colección que devuelve y súmalo.
        int sumaEdades = 0;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Elimina a "Pedro" del mapa USANDO el Set 'nombres'.
        // Magia de las "vistas": si borras del Set devuelto por keySet(), se borra del Map.
        // Usa nombres.remove("Pedro") y verifica qué ocurre.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 4: Recorre de nuevo los VALORES y saca por pantalla cada edad restante para verificarlo tú mismo.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = nombres != null && !nombres.contains("Pedro");
        boolean ok2 = edades.size() == 3;
        boolean ok3 = sumaEdades == 102; // 25 + 30 + 22 + 25

        if (ok1 && ok2 && ok3) {
            System.out.println(">> CORRECTO: Entiendes que keySet() y values() son VISTAS dinámicas.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa tus operaciones.");
            System.err.println("   Suma edades (Esperada: 102) -> " + sumaEdades);
            System.err.println("   Pedro debería haberse borrado del Map principal (tamaño esperado 3) -> " + edades.size());
        }
    }
}
