package nivel7_experto;

import modelos.DatosPrueba;
import modelos.Estudiante;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * EJERCICIO 27 - APLANAR DATOS: FlatMap
 * 
 * Objetivo: Qué sucede si tengo un Map<String, List<String>> y quiero extraer
 * un set o lista ÚNICA plana de todas las asignaturas que existen en el sistema.
 */
public class Ejercicio27_FlatMapValues {

    public static void demostracion() {
        System.out.println("--- DEMO: FLATMAP ---");
        List<Estudiante> inscritos = DatosPrueba.obtenerEstudiantes();

        // Creamos un mapa de {Nombre -> Lista de Asignaturas}
        Map<String, List<String>> alumnoAsignaturas = inscritos.stream()
                .collect(Collectors.toMap(Estudiante::getNombre, Estudiante::getAsignaturas));

        // Queremos saber cuáles son TODAS las asignaturas que se imparten en la escuela sin duplicados.
        // Necesitamos aplastar las List<String> del getValue().
        Set<String> todasLasAsignaturas = alumnoAsignaturas.values().stream() // Stream<List<String>>
                .flatMap(List::stream)                                        // Aplasta a Stream<String>
                .collect(Collectors.toSet());

        System.out.println("Catálogo único de asignaturas impartidas: " + todasLasAsignaturas);
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 27: TODOS LOS NOMBRES COMBINADOS ---");
        List<Estudiante> estudiantes = DatosPrueba.obtenerEstudiantes();
        
        // Aquí agrupamos estudiantes por su Edad. Obtenemos Map<Integer, List<Estudiante>>
        Map<Integer, List<Estudiante>> agrupados = estudiantes.stream()
                .collect(Collectors.groupingBy(Estudiante::getEdad));

        // TODO 1: Queremos un simple String aglutinando los NOMBRES de TODOS los estudiantes, separados por ", ".
        // 1. Llama a agrupados.values().stream() para obtener Stream<List<Estudiante>>
        // 2. Aplánalo usando .flatMap(List::stream) para obtener Stream<Estudiante>
        // 3. Conviértelo usando .map(Estudiante::getNombre) a Stream<String>
        // 4. Recólectalo usando Collectors.joining(", ")
        String listadoNombres = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Imprime el listado plano de nombres resultante.
        System.out.println("Lista plana consolidada: " + listadoNombres);
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = listadoNombres != null && listadoNombres.contains("Ana García") && listadoNombres.contains("Luis Pérez");

        if (ok1 && listadoNombres.split(",").length >= 5) {
            System.out.println(">> CORRECTO: Has dominado el despliegue de estructuras complejas (Flattening).\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] El listado no se ha generado correctamente usando .flatMap().");
        }
    }
}
