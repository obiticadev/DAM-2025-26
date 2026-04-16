package nivel4_metodos_default_java8;

import java.util.HashMap;
import java.util.Map;

/**
 * EJERCICIO 12 - LA MUTACIÓN ABSOLUTA: compute()
 * 
 * Objetivo: compute() acepta una llave y una BiFunction. La BiFunction 
 * recibe la Llave y el Valor Actual. Tú devuelves en qué quieres que se convierta.
 * ¡OJO! Si devuelves null, la llave se BORRA del mapa.
 */
public class Ejercicio12_Compute {

    public static void demostracion() {
        System.out.println("--- DEMO: compute() ---");
        Map<String, Integer> contador = new HashMap<>();
        contador.put("A", 1);
        
        // El valor a la derecha del -> es lo que se guardará en la llave "A"
        contador.compute("A", (k, v) -> v + 1);
        System.out.println("Después de compute 'A': " + contador); // {A=2}

        // ¿Qué pasa si "B" no existe? 'v' será null.
        contador.compute("B", (k, v) -> (v == null) ? 1 : v + 1);
        System.out.println("Después de buscar B que no existía: " + contador); // {A=2, B=1}
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 12: CONTADOR DE PALABRAS ---");
        // Típica entrevista técnica: "Dado un diccionario, cuenta la ocurrencia de cada letra"
        Map<String, Integer> frecuencias = new HashMap<>();
        frecuencias.put("C", 3);
        frecuencias.put("A", 1);

        // TODO 1: Usa OBLIGATORIAMENTE compute() para sumarle 1 a la frecuencia de "C".
        // Recuerda que la BiFunction recibe (llave, valorAntiguo).
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Intenta sumarle 1 a la frecuencia de "B" usando compute().
        // ¡Cuidado! Como "B" no existe, el valorAntiguo que recibe la lambda será null.
        // Si haces `valorAntiguo + 1` directamente, lanzará NullPointerException.
        // Usa un operador ternario o if/else en la lambda para insertar un 1 si era null, o sumarle 1 si no.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Existe una regla brutal en compute(): si la lambda retorna NULL, el Map BORRA esa entrada.
        // Usa compute() para obligar a que la clave "A" termine eliminada devolviendo un null.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = frecuencias.get("C") != null && frecuencias.get("C") == 4;
        boolean ok2 = frecuencias.get("B") != null && frecuencias.get("B") == 1;
        boolean ok3 = !frecuencias.containsKey("A");

        if (ok1 && ok2 && ok3) {
            System.out.println(">> CORRECTO: Eres un maestro del compute(), controlas los null y previenes las NullPointerException.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa las matemáticas de tus compute() o la eliminación por null.");
        }
    }
}
