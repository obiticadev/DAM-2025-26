package nivel14_master_streams;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * EJERCICIO 47 - GROUPING BY CONCURRENT (Multicore Processing)
 * 
 * Objetivo: Entender la abismal diferencia entre un .groupingBy (Mono hilo, merge lento)
 * y un .groupingByConcurrent para paralelismo agresivo.
 */
public class Ejercicio47_GroupingByConcurrent {

    public static void demostracion() {
        System.out.println("--- DEMO: STREAM PARALELO E HILOS CONCURRENTES ---");
        // Lanzamos un millon de numeros simulando carga pesada en paralelo.
        // Si usaramos "groupingBy" normal, Java tendría que bloquear el mapa en cada iteración y sería lento.
        // groupingByConcurrent inyecta en un ConcurrentHashMap segmentado, permitiendo que 
        // 8 Hilos escriban en el mapa a la vez a velocidades de vértigo.
        ConcurrentMap<Integer, List<Integer>> mapaVeloz = IntStream.range(1, 100000)
            .parallel() // Activamos la magia Multicore
            .boxed()
            .collect(Collectors.groupingByConcurrent(n -> n % 10)); // Agrupamos por el último dígito

        System.out.println("Tardó milisegundos en clasificar 100k elementos multicore. Llaves generadas: " + mapaVeloz.keySet().size());
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 47: CLASIFICACIÓN PARALELA ---");
        
        List<String> palabras = List.of(
            "Java", "React", "Rust", "Ruby", "C", "Go", "Git"
        );

        // TODO 1: Crea un Stream desde la lista de palabras. HAZLO PARALELO llamando a `.parallelStream()` en vez de `.stream()`.
        // TODO 2: Al recolectarlo, usa agrupamiento concurrente `Collectors.groupingByConcurrent(...)`.
        // TODO 3: Argúpalo según LA LONGITUD DE LA PALABRA (String::length).
        // El tipo devuelto debe ser un ConcurrentMap<Integer, List<String>>.
        ConcurrentMap<Integer, List<String>> clasificado = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN DETALLADA ---
        boolean ok1 = clasificado != null && clasificado.getClass().getName().contains("Concurrent");
        
        if (!ok1) {
            System.err.println("-> [TODO] Falló: Debías usar groupingByConcurrent. O usaste uno normal o no asignaste el mapa.");
        } else {
            boolean d4 = clasificado.getOrDefault(4, List.of()).contains("Java") && clasificado.get(4).contains("Rust");
            boolean d3 = clasificado.getOrDefault(3, List.of()).contains("Git");
            
            if (!maximo2(d4, d3)) System.err.println("-> [TODO 3] Falló: No agrupaste correctamente por longitud. 4 letras(Java, Rust), 3 letras(Git).");
            else {
                System.out.println(">> PERFECTO: Cuando la recolección de un Stream pesado cuello de botella -> parallelStream() + groupingByConcurrent(). \033[0;32m [OK]\033[0m");
            }
        }
    }
    
    static boolean maximo2(boolean b1, boolean b2) { return b1 && b2; }
}
