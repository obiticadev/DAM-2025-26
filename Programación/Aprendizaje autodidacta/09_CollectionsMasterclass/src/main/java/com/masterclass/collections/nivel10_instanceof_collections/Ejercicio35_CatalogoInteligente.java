package com.masterclass.collections.nivel10_instanceof_collections;

import com.masterclass.collections.modelos.*;
import com.masterclass.collections.modelos.interfaces.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * EJERCICIO 35 — instanceof + Collections: Catálogo Inteligente
 * ===============================================================
 * Teoría de referencia: teoria/06_instanceof_Interfaces_Polimorfismo.md
 *                       teoria/04_HashMap_Avanzado_LinkedHashMap_TreeMap.md
 *
 * Objetivo: Construir un catálogo que combina las colecciones avanzadas
 * (TreeMap, HashSet) con instanceof para crear vistas inteligentes
 * sobre datos heterogéneos.
 *
 * Restricción: Usa el tipo de colección indicado en cada TODO.
 */
public class Ejercicio35_CatalogoInteligente {

    // TODO 1: Implementa `construirCatalogoPorPrioridad`.
    //   - Recibe un ArrayList<Object>.
    //   - Para los que son Procesable, indexa en un TreeMap<Integer, ArrayList<Object>>
    //     donde la clave es la prioridad (orden DESCENDENTE → usa Comparator.reverseOrder()).
    //   - Si varios tienen la misma prioridad, van a la misma lista.
    //   - Los no-Procesable se ignoran.
    //   - Usa computeIfAbsent.
    public static TreeMap<Integer, ArrayList<Object>> construirCatalogoPorPrioridad(
            ArrayList<Object> lista) {
        return null;
    }

    // TODO 2: Implementa `obtenerTopNPorPrioridad`.
    //   - Dado el catálogo del TODO 1 y un int N,
    //     retorna un ArrayList<Object> con los N objetos de mayor prioridad.
    //   - Si hay más de N en las prioridades más altas, incluye hasta N.
    //   - Itera el TreeMap (ya en orden desc) y va acumulando.
    public static ArrayList<Object> obtenerTopNPorPrioridad(
            TreeMap<Integer, ArrayList<Object>> catalogo, int n) {
        return null;
    }

    // TODO 3: Implementa `estadisticasPorClase`.
    //   - Recibe un ArrayList<Object>.
    //   - Retorna un HashMap<String, HashMap<String, Integer>> donde:
    //     clave externa = nombre simple de clase (getClass().getSimpleName())
    //     clave interna = "count" → cuántos hay de esa clase
    //                     "interfaces" → cuántas interfaces (de las 4) implementa
    //   - Ejemplo: {"Empleado" → {"count": 2, "interfaces": 2}}
    public static HashMap<String, HashMap<String, Integer>> estadisticasPorClase(
            ArrayList<Object> lista) {
        return null;
    }

    // TODO 4: Implementa `buscarPorCriterio`.
    //   - Recibe un ArrayList<Object> y un HashMap<String, String> con criterios:
    //     Posibles claves del criterio: "interfaz", "clase", "departamento"
    //   - "interfaz" → filtra por los que implementan esa interfaz
    //   - "clase" → filtra por getClass().getSimpleName()
    //   - "departamento" → filtra por getDepartamento() solo para Empleados
    //   - Aplica TODOS los criterios presentes (AND lógico).
    //   - Retorna un ArrayList<Object> con los que cumplen todos.
    public static ArrayList<Object> buscarPorCriterio(ArrayList<Object> lista,
                                                       HashMap<String, String> criterios) {
        return null;
    }

    // TODO 5: Implementa `generarResumenEjecutivo`.
    //   - Recibe un ArrayList<Object>.
    //   - Retorna un HashMap<String, Object> con:
    //     "total"         → Integer (total de objetos)
    //     "por_clase"     → HashMap<String, Integer> (nombre_clase → count)
    //     "procesables_pendientes" → Integer (cuántos Procesable no están procesados)
    //     "salario_medio" → Double (media de salarios de Empleados, 0.0 si no hay)
    //     "categorias"    → HashSet<String> (categorías únicas de Clasificables)
    public static HashMap<String, Object> generarResumenEjecutivo(ArrayList<Object> lista) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 35 — Catálogo Inteligente ===\n");

        ArrayList<Object> lista = new ArrayList<>();
        lista.add(new Empleado("E01", "Ana", "IT", 45000, "admin"));
        lista.add(new Empleado("E02", "Luis", "RRHH", 38000, "admin"));
        lista.add(new Producto("P01", "Teclado", 45.0, "periféricos", "input"));
        lista.add(new Pedido("D01", "C01", 150.0, 7));
        lista.add(new Pedido("D02", "C02", 200.0, 3));
        lista.add(new Evento("EV01", "ERROR", "Fallo", 9, "sistema"));

        var catalogo = construirCatalogoPorPrioridad(lista);
        System.out.println("Catálogo por prioridad: " + catalogo.keySet());
        System.out.println("Top 2: " + obtenerTopNPorPrioridad(catalogo, 2).size());

        System.out.println("\nEstadísticas: " + estadisticasPorClase(lista));

        HashMap<String, String> criterios = new HashMap<>();
        criterios.put("interfaz", "Identificable");
        criterios.put("clase", "Empleado");
        System.out.println("Búsqueda [Identif+Empleado]: " +
                buscarPorCriterio(lista, criterios).size());

        System.out.println("\nResumen ejecutivo: " + generarResumenEjecutivo(lista));
    }
}
