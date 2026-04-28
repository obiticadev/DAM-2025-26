package com.masterclass.collections.nivel02_arraylist_intermedio;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * EJERCICIO 04 — Ordenación y Comparator
 * ========================================
 * Teoría de referencia: teoria/01_ArrayList_Fundamentos.md  (§ 6 — Ordenación con Comparator)
 *
 * Objetivo: Dominar Collections.sort, lista.sort() y la construcción de
 * Comparators (naturalOrder, reversed, comparing, thenComparing).
 *
 * Restricción: Solo se permite Streams para recopilar resultados, no para ordenar.
 *              La ordenación debe hacerse con sort/Comparator.
 */
public class Ejercicio04_OrdenacionYComparator {

    // TODO 1: Implementa `ordenarAlfabeticamente`.
    //   - Ordena la lista EN SU LUGAR en orden alfabético natural (A → Z).
    //   - Usa lista.sort() con el Comparator adecuado (o null para orden natural).
    //   - La lista original se modifica; no hay valor de retorno.
    public static void ordenarAlfabeticamente(ArrayList<String> lista) {
        // implementa aquí
    }

    // TODO 2: Implementa `ordenarPorLongitud`.
    //   - Ordena la lista EN SU LUGAR según la longitud de cada String (más corto primero).
    //   - Si dos elementos tienen la misma longitud, el orden entre ellos es indiferente.
    //   - Usa Comparator.comparingInt con una referencia de método.
    public static void ordenarPorLongitud(ArrayList<String> lista) {
        // implementa aquí
    }

    // TODO 3: Implementa `ordenarPorLongitudLuegoAlfabetico`.
    //   - Ordena EN SU LUGAR primero por longitud (ascendente).
    //   - En caso de empate de longitud, ordena alfabéticamente (A → Z).
    //   - Usa Comparator.comparingInt(...).thenComparing(...).
    public static void ordenarPorLongitudLuegoAlfabetico(ArrayList<String> lista) {
        // implementa aquí
    }

    // TODO 4: Implementa `ordenarDescendente`.
    //   - Ordena la lista EN SU LUGAR en orden alfabético inverso (Z → A).
    //   - Usa Comparator.reverseOrder() o naturalOrder().reversed().
    public static void ordenarDescendente(ArrayList<String> lista) {
        // implementa aquí
    }

    // TODO 5: Implementa `minimo`.
    //   - Retorna el String más corto (por longitud) de la lista.
    //   - Si hay empate de longitud, retorna cualquiera de los empatados.
    //   - Usa Collections.min() con el Comparator apropiado.
    //   - Lanza NoSuchElementException si la lista está vacía (la API lo hace sola).
    public static String minimo(ArrayList<String> lista) {
        return null;
    }

    // TODO 6: Implementa `maximo`.
    //   - Retorna el String más largo (por longitud).
    //   - Usa Collections.max() con el Comparator apropiado.
    public static String maximo(ArrayList<String> lista) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run para comprobar visualmente
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 04 — Ordenación y Comparator ===\n");

        ArrayList<String> frutas = new ArrayList<>();
        frutas.add("Kiwi");
        frutas.add("Aguacate");
        frutas.add("Uva");
        frutas.add("Cereza");
        frutas.add("Higo");
        frutas.add("Fresa");
        frutas.add("Albaricoque");
        frutas.add("Uva"); // duplicado intencional

        System.out.println("Lista original: " + frutas);

        // Trabaja siempre sobre una copia para no afectar las siguientes pruebas
        ArrayList<String> copia1 = new ArrayList<>(frutas);
        ordenarAlfabeticamente(copia1);
        System.out.println("Ordenado alfabético: " + copia1);

        ArrayList<String> copia2 = new ArrayList<>(frutas);
        ordenarPorLongitud(copia2);
        System.out.println("Ordenado por longitud: " + copia2);

        ArrayList<String> copia3 = new ArrayList<>(frutas);
        ordenarPorLongitudLuegoAlfabetico(copia3);
        System.out.println("Ordenado por longitud luego alfabético: " + copia3);

        ArrayList<String> copia4 = new ArrayList<>(frutas);
        ordenarDescendente(copia4);
        System.out.println("Ordenado descendente: " + copia4);

        System.out.println("\nMínimo (más corto): " + minimo(frutas));
        System.out.println("Máximo (más largo): " + maximo(frutas));
    }
}
