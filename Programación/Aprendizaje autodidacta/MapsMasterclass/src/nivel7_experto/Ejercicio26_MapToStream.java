package nivel7_experto;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * EJERCICIO 26 - DE MAP A STREAM Y VICEVERSA
 * 
 * Objetivo: A diferencia de List, la interfaz Map no tiene el método .stream().
 * Para usar el poder de los streams sobre un diccionario ya existente, 
 * debes invocar .entrySet().stream() y luego usar collect para volver a armarlo.
 */
public class Ejercicio26_MapToStream {

    public static void demostracion() {
        System.out.println("--- DEMO: DE MAP A STREAM ---");
        Map<String, String> capitales = new HashMap<>();
        capitales.put("España", "Madrid");
        capitales.put("Francia", "París");
        capitales.put("Alemania", "Berlín");

        // Queremos generar un NUEVO map que solo contenga los países que empiezan por 'E' o 'F'
        // y transformar sus ciudades a MAYÚSCULAS.
        Map<String, String> resultado = capitales.entrySet().stream()
                .filter(entrada -> entrada.getKey().startsWith("E") || entrada.getKey().startsWith("F"))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,                       // K
                        entrada -> entrada.getValue().toUpperCase()// V
                ));

        System.out.println("Capitales filtradas y en mayúsculas: " + resultado);
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 26: DEPURACIÓN DE DATOS CORRUPTOS ---");
        Map<Integer, String> censos = new HashMap<>(); // ID -> Nombre
        censos.put(1, "Ana");
        censos.put(2, "NULL"); // Dato corrupto 1
        censos.put(3, "Luis");
        censos.put(4, "");     // Dato corrupto 2

        // TODO 1: Genera un NUEVO MAPA llamado 'censoLimpio'.
        // Iteralo usando censos.entrySet().stream()
        // Aplica un .filter() para quitar las entradas cuyo getValue() sea "NULL" o esté vacío (.isEmpty()).
        // Vuelve a recolectarlo usando Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue).
        Map<Integer, String> censoLimpio = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Imprime el mapa limpio para verificar.
        System.out.println("Censo tras la limpieza:");
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = censoLimpio != null && censoLimpio.size() == 2;
        boolean ok2 = censoLimpio != null && censoLimpio.containsKey(1) && censoLimpio.containsKey(3);

        if (ok1 && ok2) {
            System.out.println(">> CORRECTO: Convertir un Map a Stream y volver a ensamblarlo es magia pura.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa los filtros. ¿Comprobaste isEmpty() y equals(\"NULL\") en el getValue()?");
        }
    }
}
