package com.masterclass.collections.nivel07_hashset;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * EJERCICIO 23 — HashSet: Detección de Duplicados y Patrones
 * ============================================================
 * Teoría de referencia: teoria/05_HashSet_y_Conjuntos.md  (§ 7 — Patrones útiles)
 *
 * Objetivo: Usar HashSet como herramienta de detección: encontrar duplicados,
 * elementos únicos, primera repetición, etc.
 *
 * Restricción: NO uses HashMap para estos ejercicios. La solución debe basarse en la
 *              semántica de add() de HashSet que retorna false para duplicados.
 */
public class Ejercicio23_DeteccionDuplicados {

    // TODO 1: Implementa `encontrarDuplicados`.
    //   - Recibe un ArrayList<String>.
    //   - Retorna un ArrayList<String> con los elementos que aparecen MÁS DE UNA VEZ.
    //   - Cada duplicado debe aparecer una sola vez en el resultado.
    //   - Usa un HashSet<String> como "vistos": si add() retorna false, el elemento
    //     es un duplicado. Usa un segundo HashSet para evitar duplicados en el resultado.
    public static ArrayList<String> encontrarDuplicados(ArrayList<String> lista) {
        return null;
    }

    // TODO 2: Implementa `primeraRepeticion`.
    //   - Recibe un String[] y retorna el PRIMER elemento que se repite
    //     (el primero cuyo add() al HashSet retorna false).
    //   - Si no hay repeticiones, retorna null.
    public static String primeraRepeticion(String[] elementos) {
        return null;
    }

    // TODO 3: Implementa `elementosExclusivos`.
    //   - Recibe dos ArrayList<String>: listaA y listaB.
    //   - Retorna un ArrayList<String> con los elementos que están en listaA
    //     pero NO en listaB.
    //   - Crea un HashSet con listaB para búsqueda O(1), luego itera listaA
    //     comprobando si cada elemento NO está en el set de B.
    //   - El resultado no debe tener duplicados.
    public static ArrayList<String> elementosExclusivos(ArrayList<String> listaA,
                                                         ArrayList<String> listaB) {
        return null;
    }

    // TODO 4: Implementa `contarRepetidosUnicos`.
    //   - Recibe un ArrayList<String>.
    //   - Retorna cuántos elementos DISTINTOS aparecen más de una vez.
    //   - Ejemplo: ["A", "B", "A", "C", "B", "A"] → 2 (A y B se repiten).
    //   - Usa la estrategia de doble HashSet: "vistos" y "repetidos".
    public static int contarRepetidosUnicos(ArrayList<String> lista) {
        return -1;
    }

    // TODO 5: Implementa `eliminarDuplicadosPreservandoOrden`.
    //   - Recibe un ArrayList<String> con posibles duplicados.
    //   - Retorna un NUEVO ArrayList<String> sin duplicados, PRESERVANDO el orden
    //     de primera aparición.
    //   - Usa un HashSet como tracker: itera la lista, si add() retorna true
    //     (es nuevo), añade al resultado.
    public static ArrayList<String> eliminarDuplicadosPreservandoOrden(ArrayList<String> lista) {
        return null;
    }

    // TODO 6 (desafío): Implementa `encontrarParConSuma`.
    //   - Recibe un int[] y un int objetivo.
    //   - Retorna true si existe algún par de elementos distintos cuya suma sea
    //     igual a `objetivo`.
    //   - Usa un HashSet<Integer>: para cada número, comprueba si (objetivo - numero)
    //     ya está en el set. Si sí, retorna true. Si no, añade el número al set.
    //   - Complejidad esperada: O(n).
    public static boolean encontrarParConSuma(int[] numeros, int objetivo) {
        return false;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 23 — Detección de Duplicados ===\n");

        // -- encontrarDuplicados --
        ArrayList<String> lista = new ArrayList<>(java.util.List.of("A", "B", "A", "C", "B", "D", "A"));
        System.out.println("Duplicados: " + encontrarDuplicados(lista));

        // -- primeraRepeticion --
        String[] arr = {"X", "Y", "Z", "Y", "X"};
        System.out.println("Primera repetición: " + primeraRepeticion(arr));

        // -- elementosExclusivos --
        ArrayList<String> a = new ArrayList<>(java.util.List.of("A", "B", "C", "D"));
        ArrayList<String> b = new ArrayList<>(java.util.List.of("B", "D", "E"));
        System.out.println("Exclusivos de A: " + elementosExclusivos(a, b));

        // -- contarRepetidosUnicos --
        System.out.println("Repetidos únicos: " + contarRepetidosUnicos(lista));

        // -- eliminarDuplicadosPreservandoOrden --
        ArrayList<String> conDups = new ArrayList<>(java.util.List.of("C", "A", "B", "A", "C", "D"));
        System.out.println("Sin dups (orden preservado): " + eliminarDuplicadosPreservandoOrden(conDups));

        // -- encontrarParConSuma --
        int[] nums = {2, 7, 11, 15};
        System.out.println("¿Par con suma 9? " + encontrarParConSuma(nums, 9));
        System.out.println("¿Par con suma 100? " + encontrarParConSuma(nums, 100));
    }
}
