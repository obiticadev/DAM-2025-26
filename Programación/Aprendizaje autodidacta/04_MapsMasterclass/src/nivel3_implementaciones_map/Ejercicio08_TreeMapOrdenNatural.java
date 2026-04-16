package nivel3_implementaciones_map;

import java.util.Map;
import java.util.TreeMap;

/**
 * EJERCICIO 08 - TREEMAP Y EL ORDEN NATURAL
 * 
 * Objetivo: Entender que TreeMap ordena automáticamente por la CLAVE.
 * Si las llaves son Strings, orden alfabético. Si son Integers, numérico.
 */
public class Ejercicio08_TreeMapOrdenNatural {

    public static void demostracion() {
        System.out.println("--- DEMO: EL ORDEN ALFABÉTICO DE TREEMAP ---");
        // Forzamos orden inverso usando un Comparator en el constructor: (a,b) -> b.compareTo(a)
        Map<String, Integer> puntuaciones = new TreeMap<>((a, b) -> b.compareTo(a));
        puntuaciones.put("Zoe", 10);
        puntuaciones.put("Ana", 15);
        puntuaciones.put("Mario", 20);

        System.out.println("Iteramos el TreeMap Custom (Z -> A):");
        puntuaciones.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 08: CLASIFICACIÓN DEL TORNEO ---");
        // LLAVE: Puntuación (Integer), VALOR: Nombre del equipo (String)
        // Ojo a la llave: Queremos TreeMap para que se ordene por puntuación de menor a mayor.
        
        // TODO 1: Instancia 'clasificacion' como TreeMap sin argumentos (orden natural: 1, 2, 3...)
        Map<Integer, String> clasificacion = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Inserta los siguientes datos EN ESTE ORDEN:
        // 45 puntos -> "Equipo Beta"
        // 12 puntos -> "Equipo Alpha"
        // 89 puntos -> "Equipo Gamma"
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Intenta insertar una llave null con valor "Equipo Descalificado".
        // ¿Qué ocurre? TreeMap NO PERMITE claves null porque el motor de ordenación (compareTo) peta.
        // Envuelve 'clasificacion.put(null, "...")' en un bloque try/catch y atrapa NullPointerException.
        boolean arrojoExcepcion = false;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 4: Recorre e imprime la clasificación final.
        // La salida debería ser primero Alpha(12), luego Beta(45), luego Gamma(89).
        // Concatena solo los nombres en orden a 'nombresOrdenados' para la validación.
        StringBuilder nombresOrdenados = new StringBuilder();
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        if (clasificacion != null && arrojoExcepcion && nombresOrdenados.toString().equals("Equipo AlphaEquipo BetaEquipo Gamma")) {
            System.out.println(">> CORRECTO: TreeMap ordena tus llaves Integer ascendentemente y odia los null.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa el try-catch de nulos o tu bucle de recorrido.");
        }
    }
}
