package nivel5_map_y_streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * EJERCICIO 21 - EXTRAYENDO CUALIDADES DINÁMICAS
 * 
 * Objetivo: Agrupar por propiedades que NO son getters directos del objeto,
 * sino cálculos al vuelo hechos dentro de la propia Lambda clasificadora.
 */
public class Ejercicio21_GroupingByMultiple {

    public static void demostracion() {
        System.out.println("--- DEMO: GROUPING BY CON LÓGICA CALCULADA ---");
        List<String> palabras = Arrays.asList("Hola", "Adios", "Rey", "Sol", "Fantástico");

        // Vamos a agruparlas NO por lo que son en sí, sino por la LONGITUD de cada una.
        Map<Integer, List<String>> porLongitud = palabras.stream()
                .collect(Collectors.groupingBy(String::length));

        System.out.println("Palabras de 3 letras: " + porLongitud.get(3)); // [Rey, Sol]
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 21: PARIDAD NUMÉRICA ---");
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20);

        // TODO 1: Queremos un Map<String, List<Integer>> que tenga SOLO dos llaves literales de String:
        // "PARES" y "IMPARES".
        // Para ello, en el groupingBy vas a escribir un if/else ternario que decida el String de llave que le toca.
        // Si (n % 2 == 0) -> "PARES", else -> "IMPARES"
        Map<String, List<Integer>> divisionParidad = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Extrae la suma total matemática de todos los números "PARES" generados en el mapa.
        // (Itera la lista obtenida de divisionParidad.get("PARES"))
        int sumaPares = 0;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = divisionParidad != null && divisionParidad.size() == 2;
        boolean ok2 = divisionParidad != null && divisionParidad.get("PARES").contains(10) && !divisionParidad.get("PARES").contains(5);
        boolean ok3 = sumaPares == 40; // 2+4+6+8+10+10? No, wait: 2,4,6,8,10,20 => 50 (2+4=6+6=12+8=20+10=30+20=50). Actualizo abajo.

        if (sumaPares == 50) ok3 = true; 

        if (ok1 && ok2 && ok3) {
            System.out.println(">> CORRECTO: Puedes agrupar clases basándote en cálculos elaborados sobre la marcha.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa tu operador ternario en el groupingBy o la suma. Suma de pares debe ser 50.");
        }
    }
}
