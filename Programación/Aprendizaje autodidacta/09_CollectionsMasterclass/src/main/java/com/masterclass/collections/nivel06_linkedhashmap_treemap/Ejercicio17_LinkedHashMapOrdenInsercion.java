package com.masterclass.collections.nivel06_linkedhashmap_treemap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * EJERCICIO 17 — LinkedHashMap: Orden de Inserción
 * ==================================================
 * Teoría de referencia: teoria/04_HashMap_Avanzado_LinkedHashMap_TreeMap.md  (§ 2 — LinkedHashMap)
 *
 * Objetivo: Comprender la diferencia entre HashMap (sin orden) y LinkedHashMap
 * (orden de inserción garantizado), y aprovechar esa garantía para construir
 * estructuras predecibles.
 *
 * Restricción: Todos los mapas de este ejercicio deben ser LinkedHashMap.
 *              NO se permite usar HashMap ni TreeMap.
 */
public class Ejercicio17_LinkedHashMapOrdenInsercion {

    // TODO 1: Implementa `crearLinkedHashMap`.
    //   - Recibe dos arrays paralelos: claves[] y valores[].
    //   - Construye un LinkedHashMap<String, Integer> insertando en orden de los arrays.
    //   - Si los arrays tienen distinta longitud, lanza IllegalArgumentException.
    //   - Retorna el LinkedHashMap construido.
    public static LinkedHashMap<String, Integer> crearLinkedHashMap(String[] claves, int[] valores) {
        return null;
    }

    // TODO 2: Implementa `obtenerClavesEnOrden`.
    //   - Recibe un LinkedHashMap<String, Integer>.
    //   - Retorna un ArrayList<String> con las claves del mapa en orden de inserción.
    //   - Usa keySet() y el constructor de ArrayList que acepta una Collection.
    public static ArrayList<String> obtenerClavesEnOrden(LinkedHashMap<String, Integer> mapa) {
        return null;
    }

    // TODO 3: Implementa `obtenerPrimeraEntrada`.
    //   - Retorna la primera entrada (Map.Entry<String, Integer>) del LinkedHashMap.
    //   - Si el mapa está vacío, retorna null.
    //   - Pista: la primera entrada del entrySet() de un LinkedHashMap es siempre
    //     la primera que se insertó. Usa iterator().next().
    public static Map.Entry<String, Integer> obtenerPrimeraEntrada(LinkedHashMap<String, Integer> mapa) {
        return null;
    }

    // TODO 4: Implementa `obtenerUltimaEntrada`.
    //   - Retorna la última entrada (Map.Entry<String, Integer>) del LinkedHashMap.
    //   - Si el mapa está vacío, retorna null.
    //   - Pista: itera todo el entrySet() y quédate con la última. No hay
    //     método directo "getLast()" en LinkedHashMap clásico.
    public static Map.Entry<String, Integer> obtenerUltimaEntrada(LinkedHashMap<String, Integer> mapa) {
        return null;
    }

    // TODO 5: Implementa `reinsertarClave`.
    //   - Si la clave existe en el mapa, la elimina y la vuelve a insertar
    //     con el MISMO valor, moviéndola así al final del orden de inserción.
    //   - Si la clave no existe, no hace nada.
    //   - El mapa se modifica en sitio.
    public static void reinsertarClave(LinkedHashMap<String, Integer> mapa, String clave) {
        // implementa aquí
    }

    // TODO 6: Implementa `filtrarPorValorMinimoConservandoOrden`.
    //   - Retorna un NUEVO LinkedHashMap<String, Integer> que contenga solo las
    //     entradas cuyo valor sea >= minimo.
    //   - El orden del nuevo mapa debe respetar el orden de inserción del original.
    //   - Itera el entrySet() del original y añade selectivamente al nuevo mapa.
    public static LinkedHashMap<String, Integer> filtrarPorValorMinimoConservandoOrden(
            LinkedHashMap<String, Integer> mapa, int minimo) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 17 — LinkedHashMap: Orden de Inserción ===\n");

        // -- crearLinkedHashMap --
        String[] claves = {"banana", "manzana", "pera", "uva"};
        int[] valores = {3, 1, 7, 2};
        LinkedHashMap<String, Integer> mapa = crearLinkedHashMap(claves, valores);
        System.out.println("Mapa creado: " + mapa);

        // -- obtenerClavesEnOrden --
        System.out.println("Claves en orden: " + obtenerClavesEnOrden(mapa));

        // -- obtenerPrimeraEntrada --
        System.out.println("Primera entrada: " + obtenerPrimeraEntrada(mapa));

        // -- obtenerUltimaEntrada --
        System.out.println("Última entrada: " + obtenerUltimaEntrada(mapa));

        // -- reinsertarClave --
        reinsertarClave(mapa, "banana");
        System.out.println("\nTras reinsertar 'banana': " + mapa);
        System.out.println("Nueva última entrada: " + obtenerUltimaEntrada(mapa));

        // -- filtrarPorValorMinimoConservandoOrden --
        LinkedHashMap<String, Integer> filtrado = filtrarPorValorMinimoConservandoOrden(mapa, 3);
        System.out.println("\nFiltrado (>= 3): " + filtrado);
    }
}
