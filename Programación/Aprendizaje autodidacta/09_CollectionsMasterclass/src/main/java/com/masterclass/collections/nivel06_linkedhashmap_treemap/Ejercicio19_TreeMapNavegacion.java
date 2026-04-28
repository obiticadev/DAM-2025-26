package com.masterclass.collections.nivel06_linkedhashmap_treemap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * EJERCICIO 19 — TreeMap: Orden Natural y NavigableMap
 * =====================================================
 * Teoría de referencia: teoria/04_HashMap_Avanzado_LinkedHashMap_TreeMap.md  (§ 3 — TreeMap)
 *
 * Objetivo: Dominar las operaciones de TreeMap: orden natural, NavigableMap
 * (floor, ceiling, lower, higher), submapas y consultas por rango.
 *
 * Restricción: Todos los mapas de este ejercicio deben ser TreeMap.
 *              NO uses HashMap ni LinkedHashMap.
 */
public class Ejercicio19_TreeMapNavegacion {

    // TODO 1: Implementa `crearTreeMapDesdeArrays`.
    //   - Recibe dos arrays paralelos: claves (String[]) y valores (int[]).
    //   - Construye un TreeMap<String, Integer> con orden NATURAL de String (alfabético).
    //   - Si los arrays tienen distinta longitud, lanza IllegalArgumentException.
    //   - Retorna el TreeMap.
    public static TreeMap<String, Integer> crearTreeMapDesdeArrays(String[] claves, int[] valores) {
        return null;
    }

    // TODO 2: Implementa `obtenerRango`.
    //   - Retorna un ArrayList<String> con las claves del TreeMap que están
    //     en el rango [desde, hasta] (ambos INCLUSIVOS).
    //   - Usa subMap(desde, true, hasta, true) de NavigableMap.
    //   - Si no hay claves en el rango, retorna lista vacía.
    public static ArrayList<String> obtenerRango(TreeMap<String, Integer> mapa,
                                                  String desde, String hasta) {
        return null;
    }

    // TODO 3: Implementa `vecinosMasCercanos`.
    //   - Dado un TreeMap y una clave de referencia, retorna un array de 2 Strings:
    //     [floorKey(referencia), ceilingKey(referencia)].
    //   - floorKey = clave más cercana que sea <= referencia.
    //   - ceilingKey = clave más cercana que sea >= referencia.
    //   - Si alguno es null (no existe), el array lo refleja como null en esa posición.
    public static String[] vecinosMasCercanos(TreeMap<String, Integer> mapa, String referencia) {
        return null;
    }

    // TODO 4: Implementa `clavesEnOrdenInverso`.
    //   - Retorna un ArrayList<String> con TODAS las claves del TreeMap en orden DESCENDENTE.
    //   - Usa descendingMap().keySet() y el constructor de ArrayList que acepta Collection.
    public static ArrayList<String> clavesEnOrdenInverso(TreeMap<String, Integer> mapa) {
        return null;
    }

    // TODO 5: Implementa `sumaPorRango`.
    //   - Retorna la suma de todos los valores cuyas claves estén en el rango
    //     [desde, hasta] (ambos inclusivos).
    //   - Usa subMap(desde, true, hasta, true).values() e itera con un bucle for-each.
    //   - Si no hay claves en el rango, retorna 0.
    public static int sumaPorRango(TreeMap<String, Integer> mapa, String desde, String hasta) {
        return 0;
    }

    // TODO 6: Implementa `primerYUltimaClave`.
    //   - Retorna un String[] de 2 elementos: [firstKey(), lastKey()].
    //   - Si el mapa está vacío, retorna new String[]{null, null}.
    public static String[] primerYUltimaClave(TreeMap<String, Integer> mapa) {
        return null;
    }

    // TODO 7 (desafío): Implementa `crearTreeMapConComparadorPersonalizado`.
    //   - Crea un TreeMap<String, Integer> cuyo comparador ordene las claves
    //     por LONGITUD del String de forma ASCENDENTE; si hay empate en longitud,
    //     se desempata por orden ALFABÉTICO natural.
    //   - Inserta todas las parejas de los arrays recibidos.
    //   - Retorna el TreeMap con su comparador personalizado.
    public static TreeMap<String, Integer> crearTreeMapConComparadorPersonalizado(
            String[] claves, int[] valores) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 19 — TreeMap: Orden Natural y NavigableMap ===\n");

        String[] claves = {"pera", "banana", "mango", "uva", "cereza"};
        int[] valores = {2, 5, 3, 8, 1};

        // -- crearTreeMapDesdeArrays --
        TreeMap<String, Integer> mapa = crearTreeMapDesdeArrays(claves, valores);
        System.out.println("TreeMap (orden natural): " + mapa);

        // -- obtenerRango --
        System.out.println("Rango [cereza, pera]: " + obtenerRango(mapa, "cereza", "pera"));

        // -- vecinosMasCercanos --
        String[] vecinos = vecinosMasCercanos(mapa, "kiwi");
        System.out.println("Vecinos de 'kiwi': floor=" + vecinos[0] + ", ceiling=" + vecinos[1]);

        // -- clavesEnOrdenInverso --
        System.out.println("Orden inverso: " + clavesEnOrdenInverso(mapa));

        // -- sumaPorRango --
        System.out.println("Suma [cereza, pera]: " + sumaPorRango(mapa, "cereza", "pera"));

        // -- primerYUltimaClave --
        String[] extremos = primerYUltimaClave(mapa);
        System.out.println("Primera: " + extremos[0] + ", Última: " + extremos[1]);

        // -- comparador personalizado --
        TreeMap<String, Integer> custom = crearTreeMapConComparadorPersonalizado(claves, valores);
        System.out.println("\nTreeMap con comparador por longitud: " + custom);
    }
}
