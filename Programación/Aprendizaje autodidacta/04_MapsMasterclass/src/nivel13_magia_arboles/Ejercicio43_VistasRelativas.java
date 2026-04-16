package nivel13_magia_arboles;

import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * EJERCICIO 43 - VISTAS RELATIVAS BIDIRECCIONALES
 * 
 * Objetivo: Entender que headMap, tailMap y subMap devuelven un puntero MÁGICO 
 * que si se altera, muta el diccionario principal simultáneamente.
 */
public class Ejercicio43_VistasRelativas {

    public static void demostracion() {
        System.out.println("--- DEMO: EL ESPEJO MÁGICO (VISTAS) ---");
        TreeMap<Integer, String> edades = new TreeMap<>();
        edades.put(10, "Niño");
        edades.put(20, "Joven");
        edades.put(40, "Adulto");

        // Creamos una vista para Menores de Edad (< 18)
        SortedMap<Integer, String> menoresMenores = edades.headMap(18); // Exclusivo 18
        
        System.out.println("Mapa antes: " + edades);
        
        // ¡Magia! Inserto en la vista, NO en el map original.
        menoresMenores.put(5, "Bebe");
        
        System.out.println("Mapa original DESPUÉS de alterar la Vista: " + edades);

        // Si intentamos meter un adulto a través del canal de la Vista de menores:
        try {
            menoresMenores.put(50, "Infractor");
        } catch (IllegalArgumentException e) {
            System.out.println("La vista rechazó la petición fuera de rango: " + e.getMessage());
        }
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 43: CALIFICACIONES EN TIEMPO REAL ---");

        TreeMap<Double, String> notas = new TreeMap<>();
        notas.put(4.0, "Juan");
        notas.put(7.5, "Ana");
        notas.put(9.2, "Luis");
        notas.put(4.9, "Pedro");

        // TODO 1: Crea una vista llamada 'aprobados' de tipo NavigableMap (ó SortedMap)
        // que contenga notas exclusivas desde el 5.0 (Inclusivo). Usa `tailMap(5.0, true)`.
        NavigableMap<Double, String> aprobados = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Inserte un nuevo alumno APROBADO "Maria" con un 6.0 pero HACIÉNDOLO 
        // A TRAVÉS DE LA VISTA 'aprobados', no del map madre original.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Elimina a "Luis" con su llave (9.2) TAMBIÉN a través del espejo 'aprobados' (.remove).
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN DETALLADA ---
        boolean ok1 = aprobados != null;
        boolean okLlegada = false;
        boolean okSalida = false;
        
        if (ok1) {
            okLlegada = notas.containsValue("Maria");
            okSalida = !notas.containsKey(9.2);
        }

        if (!ok1) System.err.println("-> [TODO 1] Falló: La vista no fue instanciada.");
        if (ok1 && !okLlegada) System.err.println("-> [TODO 2] Falló: 'Maria' no penetró el mapa base a través de la vista.");
        if (ok1 && !okSalida) System.err.println("-> [TODO 3] Falló: Luis (9.2) debía haber sido removido cruzando el portal del espejo.");

        if (ok1 && okLlegada && okSalida) {
            System.out.println(">> PERFECTO: Cuando devuelvas diccionarios de tus Clases de BDD, valora usar vistas para limitarles los rangos al Cliente. \033[0;32m [OK]\033[0m");
        }
    }
}
