package nivel3_implementaciones_map;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * EJERCICIO 09 - NAVIGABLE MAP Y CONSULTAS DE RANGO
 * 
 * Objetivo: Aprovechar que TreeMap implementa NavigableMap
 * para extraer rangos (¿Quién es el mayor? ¿Dame los 3 últimos?).
 */
public class Ejercicio09_TreeMapNavegacion {

    public static void demostracion() {
        System.out.println("--- DEMO: MAGIA DE NAVIGABLE MAP ---");
        // Usamos la interfaz NavigableMap (padre de TreeMap) en lugar de Map general
        NavigableMap<Integer, String> rangos = new TreeMap<>();
        rangos.put(10, "Diez");
        rangos.put(50, "Cincuenta");
        rangos.put(100, "Cien");

        System.out.println("Primer elemento (llave menor): " + rangos.firstEntry()); // 10
        System.out.println("Último elemento (llave mayor): " + rangos.lastEntry()); // 100
        System.out.println("Inmediato inferior a 45: " + rangos.lowerEntry(45)); // 10
        System.out.println("Inmediato superior a 50: " + rangos.higherEntry(50)); // 100
        System.out.println(String.format("Mapa Inverso: %s\n", rangos.descendingMap()));
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 09: FECHAS HISTÓRICAS ---");
        // LLAVE: Año (Integer) VALOR: Evento (String)
        NavigableMap<Integer, String> historia = new TreeMap<>();
        historia.put(1492, "Descubrimiento de América");
        historia.put(1969, "Llegada a la Luna");
        historia.put(1789, "Revolución Francesa");
        historia.put(1914, "Inicio Primera Guerra Mundial");
        historia.put(1945, "Fin Segunda Guerra Mundial");
        historia.put(2001, "Caída Torres Gemelas");

        // TODO 1: Queremos saber cuál fue el PRIMER evento histórico registrado (la llave más pequeña).
        // Extrae el VALOR (el evento, no el año) usando history.firstEntry() y asígnalo a:
        String eventoMasAntiguo = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Ocurre un evento en el año 1950. Queremos saber cuál fue el evento INMEDIATAMENTE ANTERIOR a esa fecha.
        // Usa history.lowerEntry(1950) para obtenerlo y asígnalo a:
        String eventoInmediatoAnterior1950 = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Queremos un sub-mapa solo con los eventos del siglo XX (desde 1900 inclusivo hasta 1999 inclusivo).
        // Usa history.subMap(fromKey, fromInclusive, toKey, toInclusive).
        NavigableMap<Integer, String> sigloXX = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 4: Recorre el mapa 'sigloXX' en ORDEN INVERSO (del más nuevo al más antiguo)
        // Puedes usar sigloXX.descendingMap().forEach()
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = "Descubrimiento de América".equals(eventoMasAntiguo);
        boolean ok2 = "Fin Segunda Guerra Mundial".equals(eventoInmediatoAnterior1950);
        boolean ok3 = sigloXX != null && sigloXX.size() == 3;

        if (ok1 && ok2 && ok3) {
            System.out.println("\n>> CORRECTO: NavigableMap te da un control increíble sobre consultas por rangos!\033[0;32m [OK]\033[0m");
        } else {
            System.err.println("\n>> [ERROR] Revisa las llamadas de la API a firstEntry, lowerEntry o subMap.");
        }
    }
}
