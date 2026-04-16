package nivel4_metodos_default_java8;

import java.util.HashMap;
import java.util.Map;

/**
 * EJERCICIO 15 - EL REY DE LA ACUMULACIÓN: merge()
 * 
 * Objetivo: Entender cómo merge(key, value, function) condensa la lógica de
 * "Si no existe lo inserto tal cual, si existe decido cómo fusionarlo".
 */
public class Ejercicio15_Merge {

    public static void demostracion() {
        System.out.println("--- DEMO: merge() ---");
        Map<String, String> votos = new HashMap<>(); // País -> Ciudades ganadas
        votos.put("España", "Madrid");

        // Si España NO existiera, asignaría "Barcelona". Como SI existe, ejecuta (old, new) -> old + ", " + new
        votos.merge("España", "Barcelona", (ciudadesViejas, nuevaCiudad) -> ciudadesViejas + ", " + nuevaCiudad);
        
        // Italia no existe, por tanto el valor inicial "Roma" entra directamente, la BiFunction se omite.
        votos.merge("Italia", "Roma", (old, newV) -> old + ", " + newV);

        System.out.println("Resultado de votos combinados: " + votos + "\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 15: CONTEO PARALELO DE INVENTARIO ---");
        // Tú y tu compañero habéis contado el stock de la tienda cada uno por su lado.
        Map<String, Integer> miConteo = new HashMap<>();
        miConteo.put("Pipas", 20);
        miConteo.put("Chicles", 5);

        Map<String, Integer> compañeroConteo = new HashMap<>();
        compañeroConteo.put("Chicles", 3);
        compañeroConteo.put("Caramelos", 50);

        // TODO 1: Vamos a FUSIONAR el conteo de tu compañero en 'miConteo'.
        // Recorre (con forEach) compañeroConteo por entero, recibiendo la 'clave' y el 'valor'.
        // Dentro del forEach, haz un miConteo.merge(...)
        // Si no existen Pipas/Caramelos, se insertarán enteros.
        // Si existe (como los chicles), tendrás que pasarle una lambda (v1, v2) -> v1 + v2 para sumar las existencias.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Alguien nos dona '10' Chicles extra.
        // Escribe la misma instrucción de .merge("Chicles", 10, ... ) directamente sobre miConteo sin estar en un bucle
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Existe una penalización especial. Si merge devuelve NULO por parte de la BiFunction, BOOOOM,
        // la entrada se BORRARÁ.
        // Usa merge("Pipas", 5, (old, newV) -> null) intencionadamente.
        // Pruébalo y observa qué pasa.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = miConteo.get("Caramelos") != null && miConteo.get("Caramelos") == 50;
        boolean ok2 = miConteo.get("Chicles") != null && miConteo.get("Chicles") == 18; // 5 originales + 3 compa + 10 extra
        boolean ok3 = !miConteo.containsKey("Pipas");

        if (ok1 && ok2 && ok3) {
            System.out.println(">> CORRECTO: Dominas la fusión y condensación de diccionarios a un nivel Master.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa las sumas dentro del merge(), no estás uniendo bien los valores.");
        }
    }
}
