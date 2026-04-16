package nivel6_operaciones_avanzadas_streams;

import modelos.DatosPrueba;
import modelos.Estudiante;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * EJERCICIO 24 - DOWNSTREAM COLLECTORS: MAPPING
 * 
 * Objetivo: Por defecto groupingBy empaqueta en List<V> el objeto COMPLETO.
 * Collectors.mapping() actúa como el .map() de un Stream, transformando el objeto
 * antes de que sea empaquetado en la lista interna.
 */
public class Ejercicio24_GroupingAndMapping {

    public static void demostracion() {
        System.out.println("--- DEMO: GROUPING BY + MAPPING ---");
        List<Estudiante> inscritos = DatosPrueba.obtenerEstudiantes();

        // Queremos saber por cada EDAD, los NOMBRES de los alumnos (no el objeto Estudiante completo)
        Map<Integer, List<String>> nombresPorEdad = inscritos.stream()
                .collect(Collectors.groupingBy(
                        Estudiante::getEdad, // Agrupamos por la propiedad Edad (K)
                        Collectors.mapping(
                                Estudiante::getNombre,    // Transformamos cada Estudiante a un String(su nombre)
                                Collectors.toList()       // Y lo coleccionamos en una lista de Strings
                        )
                ));

        System.out.println("Grupos de nombres por Edad: " + nombresPorEdad);
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 24: RECOPILADOR DE MATRÍCULAS VIP ---");
        List<Estudiante> alumnos = DatosPrueba.obtenerEstudiantes();

        // TODO 1: Vamos a clasificar a los alumnos académicamente en TRES NIVELES basándonos en su nota,
        // Y SOLO vamos a guardar su MATRICULA en las listas resultantes.
        // Criterios: 
        // Nota >= 9.0 -> "SOBRESALIENTE"
        // Nota >= 7.0 y < 9.0 -> "NOTABLE"
        // Nota < 7.0 -> "APROBADO/SUSPENSO"
        // Crea este `Map<String, List<String>> matriculasPorNivel` agrupando con una función lógica (Operador condicional/if)
        // y usa Collectors.mapping() para transformarlo en su matrícula y meterlo en una lista.
        Map<String, List<String>> matriculasPorNivel = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Imprime con un forEach() los alumnos "SOBRESALIENTE"
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = matriculasPorNivel != null && matriculasPorNivel.size() == 3;
        boolean ok2 = matriculasPorNivel != null && matriculasPorNivel.get("SOBRESALIENTE").size() == 1; // Beatriz López -> MAT-003(9.1)
        boolean ok3 = matriculasPorNivel != null && matriculasPorNivel.get("SOBRESALIENTE").contains("MAT-003");

        if (ok1 && ok2 && ok3) {
            System.out.println(">> CORRECTO: mapping() te permite proyectar campos puros ahorrando mucha memoria.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa tu lógica ternaria o if-else dentro del groupingBy y la extracción getMatricula.");
        }
    }
}
