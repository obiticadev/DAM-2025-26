package nivel1_fundamentos_map;

import java.util.HashMap;
import java.util.Map;

/**
 * EJERCICIO 01 - OPERACIONES CRUD BÁSICAS (Create, Read, Update, Delete)
 * 
 * Objetivo: Entender cómo insertar, leer, actualizar y eliminar elementos en un Map.
 * Un Map asocia una Clave (Key) a un Valor (Value). Las claves son ÚNICAS.
 * 
 * Lee primero: teoria/01_Interfaz_Map_y_Jerarquia.md
 */
public class Ejercicio01_PutGetRemove {

    public static void demostracion() {
        System.out.println("--- DEMO: OPERACIONES BÁSICAS EN MAP ---");
        Map<String, String> capitales = new HashMap<>();
        
        System.out.println("1. put(K, V) inserta un par. Si la clave no existe, devuelve null.");
        String anterior = capitales.put("España", "Madrid");
        System.out.println("   Insertado España -> Madrid. Retorno de put: " + anterior);
        
        System.out.println("2. Si la clave YA existe, put ACTUALIZA el valor y devuelve el valor ANTERIOR.");
        String antiguo = capitales.put("España", "Barcelona");
        System.out.println("   Actualizado España -> Barcelona. Retorno de put: " + antiguo);
        capitales.put("España", "Madrid"); // Lo dejamos bien
        
        System.out.println("3. get(K) obtiene el valor. Si no existe, devuelve null.");
        System.out.println("   Capital de España: " + capitales.get("España"));
        System.out.println("   Capital de Francia: " + capitales.get("Francia"));
        
        System.out.println("4. remove(K) elimina el par y devuelve el valor asociado (o null si no existía).");
        capitales.remove("España");
        System.out.println("   Mapa tras remove: " + capitales + "\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 01: EL REGISTRO DE PUNTUACIONES ---");
        Map<String, Integer> puntuaciones = new HashMap<>();
        puntuaciones.put("Jugador1", 100);
        puntuaciones.put("Jugador2", 150);

        // TODO 1: Añade al "Jugador3" con una puntuación de 200.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: El "Jugador1" ha ganado 50 puntos extra. Actualiza su puntuación a 150.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Obtén la puntuación del "Jugador2" y guárdala en una variable llamada 'puntosJ2'.
        // Si lo haces bien, no debería ser null. Si no existe usar 0 por defecto (usa la lógica normal o getOrDefault).
        Integer puntosJ2 = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 4: Elimina del mapa al "Jugador3" porque ha sido baneado por hacer trampas.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = puntuaciones.size() == 2;
        boolean ok2 = puntuaciones.get("Jugador1") != null && puntuaciones.get("Jugador1") == 150;
        boolean ok3 = puntuaciones.get("Jugador3") == null;
        boolean ok4 = puntosJ2 != null && puntosJ2 == 150;

        if (ok1 && ok2 && ok3 && ok4) {
            System.out.println(">> CORRECTO: Dominas las operaciones CRUD del Map.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa las operaciones. Estado actual: " + puntuaciones);
            System.err.println("   Esperado: Tamaño=2, Jugador1=150, Jugador3=null, puntosJ2=150");
        }
    }
}
