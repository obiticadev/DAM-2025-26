package nivel4_avanzado_estructuras_inyectadas;

import java.util.Map;
import java.util.TreeMap;
import modelos.Aventurero;

public class Ejercicio11_InyeccionLambdaTreeMap {

    public static void demostracion() {
        System.out.println("--- MODIFICANDO MAPAS ORDENADOS (TREEMAP) ---");
        System.out.println("Igual que con el TreeSet, un TreeMap ordena internamente... ¡SUS LLAVES (KEYS)!");
        System.out.println("Map<Clave, Valor> = new TreeMap<>( (c1, c2) -> ... );");
        System.out.println("La lambda siempre actúa sobre la CLAVE, no sobre el valor asociado.");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 11: DICCIONARIOS REBELDES ---");
        
        // TODO: En lugar de un TreeMap tradicional donde las Claves String ("Escudo", "Espada") irían de A-Z...
        // Introdúcele una Lambda al constructor que fuerce el orden REVERSO (De Z-A).
        // PISTA: b.compareTo(a) en vez de a.compareTo(b).
        Map<String, Aventurero> armeria = null; // <- Modifica esto
        
        if (armeria != null) {
            armeria.put("Arco", new Aventurero("Legolas", "Arquero", 70, 50, true));
            armeria.put("Maza", new Aventurero("Clerigo", "Sanador", 20, 10, true));
            armeria.put("Espada", new Aventurero("Guts", "Guerrero", 99, 0, true));
        }

        // --- VALIDACIÓN ---
        if (armeria != null && armeria.size() == 3 && armeria.keySet().iterator().next().equals("Maza")) {
            System.out.println(">> ¡CORRECTO! Has ordenado el mapa léxicamente a la inversa según sus Strings Clave.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] El diccionario debería tener a 'Maza' como primer elemento si usas un orden String de la Z a la A.");
        }
    }
}
