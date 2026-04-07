package nivel10_rendimiento_y_memoria;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * EJERCICIO 34 - IdentityHashMap (DESTRUYENDO .EQUALS)
 * 
 * Objetivo: Entender cómo forzar a Java a comparar las llaves por Identidad de Memoria (==)
 * en lugar de usar la Equivalencia Semántica (.equals()).
 */
public class Ejercicio34_IdentityHashMap {

    public static void demostracion() {
        System.out.println("--- DEMO: Equals vs ==" + " ---");
        // Tenemos dos Integers diferentes en memoria mediante el constructor explícito y obsoleto (para eludir la cache de boxing).
        Integer key1 = new Integer(1000); 
        Integer key2 = new Integer(1000); 

        System.out.println("Hash de key1: " + System.identityHashCode(key1) + " | Hash de key2: " + System.identityHashCode(key2));

        Map<Integer, String> hashClasico = new HashMap<>();
        hashClasico.put(key1, "Primero");
        hashClasico.put(key2, "Segundo"); // key1.equals(key2) es TRUE. ¡Se SOBRESCRIBE!

        Map<Integer, String> identityMap = new IdentityHashMap<>();
        identityMap.put(key1, "Primero");
        identityMap.put(key2, "Segundo"); // key1 == key2 es FALSE. ¡Sobreviven AMBOS!

        System.out.println("Tamaño Clasico: " + hashClasico.size() + " (Destruido por equals)");
        System.out.println("Tamaño Identity: " + identityMap.size() + " (Sobreviven porque son celdas RAM distintas)");
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 34: CLONANDO SISTEMAS ESTRICTOS ---");
        
        // Creamos dos Strings en espacios de memoria separados (el constructor 'new String()' fuerza esto)
        String jugador1 = new String("Player");
        String jugador2 = new String("Player");

        // TODO 1: Crea un IdentityHashMap vacio llamado 'marcador' cuya clave sea String (El Objeto Jugador) 
        // y el valor Integer (La Pila de puntos).
        IdentityHashMap<String, Integer> marcador = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Otorga 50 puntos al jugador1 y 30 puntos al jugador2 metiéndolos en el identityMap.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Haz lo mismo pero en un HashMap normal llamado 'marcadorRoto'.
        Map<String, Integer> marcadorRoto = new HashMap<>();
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN DETALLADA ---
        boolean pasoCreacion = marcador != null;
        
        if (!pasoCreacion) {
            System.err.println("-> [TODO 1] Falló: El mapa IdentityHashMap no se inicializó.");
        } else {
            if (marcador.size() != 2) System.err.println("-> [TODO 2] Falló: IdentityHashMap debió guardar ambos punteros físicos (Size = 2).");
            if (marcadorRoto.size() != 1) System.err.println("-> [TODO 3] Falló: HashMap normal debió sobrescribir al evaluar Player.equals(Player).");
            
            if (marcador.size() == 2 && marcadorRoto.size() == 1) {
                System.out.println(">> PERFECTO: Esto es oro puro al recorrer grafos y clones para evitar bucles infinitos. [OK]\033[0;32m [OK]\033[0m");
            }
        }
    }
}
