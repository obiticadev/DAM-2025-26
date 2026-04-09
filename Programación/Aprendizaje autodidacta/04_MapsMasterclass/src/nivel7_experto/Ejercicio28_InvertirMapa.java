package nivel7_experto;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * EJERCICIO 28 - INVERTIR EL MAPA
 * 
 * Objetivo: Un clásico de las entrevistas. Voltear un Map<K,V> a Map<V,K>.
 * Cuidado con las colisiones al voltear.
 */
public class Ejercicio28_InvertirMapa {

    public static void demostracion() {
        System.out.println("--- DEMO: INVERSIÓN DE CLAVES Y VALORES ---");
        Map<String, Integer> original = new HashMap<>();
        original.put("Uno", 1);
        original.put("Dos", 2);

        Map<Integer, String> invertido = original.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getValue, // La antigua V ahora es K
                        Map.Entry::getKey    // La antigua K ahora es V
                ));

        System.out.println("Original: " + original);
        System.out.println("Invertido: " + invertido);
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 28: DICCIONARIO INVERSO INGLÉS-ESPAÑOL ---");
        Map<String, String> espanolAIngles = new HashMap<>(); // K: Español -> V: Inglés
        espanolAIngles.put("Perro", "Dog");
        espanolAIngles.put("Gato", "Cat");
        espanolAIngles.put("Pájaro", "Bird");
        
        // ¡Cuidado! En inglés, "Can" puede ser "Lata" o "Poder". 
        // Si tienes dos llaves españolas que van a la misma en inglés, el invertido chocará.
        espanolAIngles.put("Lata", "Can");
        espanolAIngles.put("Poder", "Can");

        // TODO 1: Invierte el mapa para que sea Inglés -> Español.
        // Itera usando espanolAIngles.entrySet().stream()
        // Usa Collectors.toMap y pon como K al String inglés, como V al String español.
        // CRÍTICO: Al encontrar la colisión ("Can"), resuelve con una MergeFunction que combine ambas traducciones españolas,
        // (espViejo, espNuevo) -> espViejo + " o " + espNuevo
        Map<String, String> inglesAEspanol = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Imprime el resultado llamando a forEach() en el mapa invertido.
        // Observa la traducción de "Can".
        System.out.println("Traductor invertido:");
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = inglesAEspanol != null && inglesAEspanol.containsKey("Cat");
        boolean ok2 = inglesAEspanol != null && (inglesAEspanol.get("Can").equals("Lata o Poder") || inglesAEspanol.get("Can").equals("Poder o Lata"));

        if (ok1 && ok2) {
            System.out.println(">> CORRECTO: Inversión de diccionarios con resolución de colisiones dominada.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa tu función combinadora en el toMap. Esperábamos 'Lata o Poder' para la llave 'Can'.");
        }
    }
}
