package nivel5_map_y_streams;

import modelos.DatosPrueba;
import modelos.Estudiante;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * EJERCICIO 18 - ELEGIR EL TIPO DE MAP: toMap CON MAPFACTORY
 * 
 * Objetivo: Por defecto, toMap() te devuelve un HashMap (desordenado).
 * ¿Qué pasa si necesitas que la salida sea obligatoriamente un LinkedHashMap o un TreeMap?
 * Usamos la versión de 4 parámetros.
 */
public class Ejercicio18_ToMapImplementacion {

    public static void demostracion() {
        System.out.println("--- DEMO: CAMBIANDO A LINKEDHASHMAP EN STREAMS ---");
        List<Estudiante> alumnos = DatosPrueba.obtenerEstudiantes();
        
        // 4 parámetros: keyMapper, valueMapper, mergeFunction, mapFactory
        LinkedHashMap<String, Estudiante> mapaOrdenado = alumnos.stream()
                .collect(Collectors.toMap(
                        Estudiante::getMatricula,
                        e -> e,
                        (v1, v2) -> v1, // Si hay choque, me quedo con el primero
                        LinkedHashMap::new // << MapFactory: ¡Quiero este tipo de map específico!
                ));
        
        System.out.println("El mapa retiene el orden exacto de inserción del stream original.");
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 18: EXPEDIENTE ACADÉMICO ORDENADO ---");
        List<Estudiante> clase = DatosPrueba.obtenerEstudiantes();

        // TODO 1: Crea un Map<String, String> llamado 'expediente' donde:
        // Clave: MATRÍCULA
        // Valor: NOMBRE
        // REQUISITO VITAL: Queremos que sea un TreeMap para que, independientemente del orden de la lista,
        // el mapa se ordene alfabéticamente por número de Matrícula ("MAT-001", "MAT-002"...).
        // Obliga a usar la versión de 4 parámetros de toMap. Para la colisión pon algo como (a,b)->a.
        Map<String, String> expediente = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Como es un TreeMap, queremos extraer las dos primeras matrículas
        // de forma visual imprimiéndolas ordenadamente.
        // Haz un forEach del map. Notarás que el foreach los escupe en orden 1, 2, 3...
        System.out.println("Expedientes ordenados (deben salir 001 hasta 005 en orden):");
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Como demostración en código, castea el 'expediente' a TreeMap
        // o asume que la implementación subyacente lo es. Usaremos una validación de clase.
        
        // --- VALIDACIÓN ---
        boolean ok1 = expediente != null;
        boolean isTreeMap = expediente != null && expediente.getClass().getSimpleName().equals("TreeMap");

        if (ok1 && isTreeMap) {
            System.out.println(">> CORRECTO: Eres capaz de forzar la implementación del recolector usando el patrón ::new.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] El mapa no existe o NO es una instancia de TreeMap. ¿Usaste TreeMap::new?");
        }
    }
}
