package com.masterclass.collections.nivel07_hashset;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * EJERCICIO 22 — HashSet: Operaciones de Conjunto (Álgebra de Conjuntos)
 * ========================================================================
 * Teoría de referencia: teoria/05_HashSet_y_Conjuntos.md  (§ 4)
 *
 * Objetivo: Dominar las operaciones matemáticas de conjuntos implementadas
 * en Java: unión (addAll), intersección (retainAll), diferencia (removeAll),
 * subconjunto (containsAll) y disjuntos (Collections.disjoint).
 *
 * Restricción: Todas las operaciones deben producir un NUEVO HashSet sin
 *              modificar los sets de entrada (trabaja sobre copias).
 */
public class Ejercicio22_AlgebraDeConjuntos {

    // TODO 1: Implementa `union`.
    //   - Retorna un NUEVO HashSet<String> con la unión de A y B (A ∪ B).
    //   - No modifica ni A ni B.
    //   - Crea un nuevo HashSet con los elementos de A, luego usa addAll(B).
    public static HashSet<String> union(HashSet<String> a, HashSet<String> b) {
        return null;
    }

    // TODO 2: Implementa `interseccion`.
    //   - Retorna un NUEVO HashSet<String> con la intersección de A y B (A ∩ B).
    //   - No modifica ni A ni B.
    //   - Crea una copia de A, luego usa retainAll(B) sobre la copia.
    public static HashSet<String> interseccion(HashSet<String> a, HashSet<String> b) {
        return null;
    }

    // TODO 3: Implementa `diferencia`.
    //   - Retorna un NUEVO HashSet<String> con la diferencia A − B
    //     (elementos en A que NO están en B).
    //   - No modifica ni A ni B.
    //   - Crea una copia de A, luego usa removeAll(B) sobre la copia.
    public static HashSet<String> diferencia(HashSet<String> a, HashSet<String> b) {
        return null;
    }

    // TODO 4: Implementa `diferenciaSimetrica`.
    //   - Retorna un NUEVO HashSet<String> con la diferencia simétrica:
    //     (A − B) ∪ (B − A) = elementos que están en A o en B pero NO en ambos.
    //   - No modifica ni A ni B.
    //   - Usa los métodos union, interseccion y diferencia ya creados,
    //     o calcula (A ∪ B) − (A ∩ B).
    public static HashSet<String> diferenciaSimetrica(HashSet<String> a, HashSet<String> b) {
        return null;
    }

    // TODO 5: Implementa `esSubconjunto`.
    //   - Retorna true si A es subconjunto de B (A ⊆ B), es decir,
    //     todos los elementos de A están contenidos en B.
    //   - Usa containsAll.
    public static boolean esSubconjunto(HashSet<String> a, HashSet<String> b) {
        return false;
    }

    // TODO 6: Implementa `sonDisjuntos`.
    //   - Retorna true si A y B no tienen elementos en común (A ∩ B = ∅).
    //   - Usa Collections.disjoint(a, b).
    public static boolean sonDisjuntos(HashSet<String> a, HashSet<String> b) {
        return false;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 22 — Álgebra de Conjuntos ===\n");

        HashSet<String> a = new HashSet<>(java.util.List.of("1", "2", "3", "4"));
        HashSet<String> b = new HashSet<>(java.util.List.of("3", "4", "5", "6"));

        System.out.println("A = " + a);
        System.out.println("B = " + b);
        System.out.println("A ∪ B = " + union(a, b));
        System.out.println("A ∩ B = " + interseccion(a, b));
        System.out.println("A − B = " + diferencia(a, b));
        System.out.println("A △ B = " + diferenciaSimetrica(a, b));
        System.out.println("A ⊆ B? = " + esSubconjunto(a, b));

        HashSet<String> c = new HashSet<>(java.util.List.of("3", "4"));
        System.out.println("\nC = " + c);
        System.out.println("C ⊆ B? = " + esSubconjunto(c, b));

        HashSet<String> d = new HashSet<>(java.util.List.of("X", "Y"));
        System.out.println("A ∩ D = ∅? (disjuntos) = " + sonDisjuntos(a, d));
        System.out.println("A ∩ B = ∅? (disjuntos) = " + sonDisjuntos(a, b));
    }
}
