package com.masterclass.arrays.nivel03;

/**
 * EJERCICIO 16 — Ordenación de Strings
 * =====================================
 * Nivel de Referencia Teórica: teoria/03_Ordenacion_Sobre_Arrays.md
 *
 * Aplicarás los algoritmos de ordenación aprendidos a arrays de Strings,
 * usando compareTo() y compareToIgnoreCase() en lugar de los operadores
 * > y <. Esto consolida la comprensión de que los algoritmos son
 * independientes del tipo, solo cambia la comparación.
 */
public class Ej16_OrdenacionStrings {

    // TODO 1: Implementar sortInsertionAlfabetico(String[] array)
    //   Especificación técnica:
    //   - Ordenar el array de Strings in-place en orden ALFABÉTICO ascendente (A→Z)
    //     usando Insertion Sort.
    //   - Usar str1.compareTo(str2) para las comparaciones:
    //     valor < 0 → str1 va antes, valor > 0 → str1 va después.
    //   - Si array es null, lanzar IllegalArgumentException.
    //   - Los nulls dentro del array deben tratarse como "menores que cualquier String"
    //     (colocarlos al principio).
    public static void sortInsertionAlfabetico(String[] array) {
        // Sustituir con implementación
    }

    // TODO 2: Implementar sortInsertionIgnoreCase(String[] array)
    //   Especificación técnica:
    //   - Misma lógica que TODO 1 pero usando compareToIgnoreCase().
    //   - "banana" y "Banana" se tratan como iguales en orden.
    //   - Ordenar ascendente A→Z ignorando mayúsculas/minúsculas.
    public static void sortInsertionIgnoreCase(String[] array) {
        // Sustituir con implementación
    }

    // TODO 3: Implementar sortQuickAlfabetico(String[] array)
    //   Especificación técnica:
    //   - Ordenar el array de Strings con QuickSort.
    //   - Partición Lomuto con pivot = último elemento.
    //   - Usar compareTo() para comparaciones.
    //   - Si array es null, lanzar IllegalArgumentException.
    public static void sortQuickAlfabetico(String[] array) {
        // Sustituir con implementación
    }

    // TODO 4: Implementar sortPorLongitud(String[] array)
    //   Especificación técnica:
    //   - Ordenar los Strings por su LONGITUD (más cortos primero).
    //   - Si dos Strings tienen la misma longitud, mantener su orden relativo (estabilidad)
    //     → usar Insertion Sort (que es estable) con str.length() como criterio.
    //   - Los nulls se tratan como longitud 0.
    public static void sortPorLongitud(String[] array) {
        // Sustituir con implementación
    }

    // TODO 5: Implementar sortDescendente(String[] array)
    //   Especificación técnica:
    //   - Ordenar de Z→A (orden alfabético inverso).
    //   - Invertir la condición de comparación: str1.compareTo(str2) > 0 → str1 va antes.
    //   - Usar cualquier algoritmo (Insertion Sort o QuickSort).
    public static void sortDescendente(String[] array) {
        // Sustituir con implementación
    }

    // TODO 6: Implementar buscarEnOrdenado(String[] arrayOrdenado, String target)
    //   Especificación técnica:
    //   - El array ya está ordenado alfabéticamente.
    //   - Implementar BÚSQUEDA BINARIA usando compareTo().
    //   - Devolver el índice donde se encuentra 'target', o -1 si no existe.
    //   - Si array es null o target es null, lanzar IllegalArgumentException.
    public static int buscarEnOrdenado(String[] arrayOrdenado, String target) {
        return -1;
    }

    // TODO 7: Implementar estaOrdenadoAlfabeticamente(String[] array)
    //   Especificación técnica:
    //   - Verificar si el array de Strings está ordenado de A→Z.
    //   - Comparar cada par consecutivo con compareTo().
    //   - Devolver true si está ordenado, false si no.
    //   - Array vacío o de un elemento → true.
    //   - Ignorar posiciones con null (tratarlas como válidas siempre que estén al inicio).
    public static boolean estaOrdenadoAlfabeticamente(String[] array) {
        return false;
    }

    // ================================================================
    // ZONA DE EJECUCIÓN MASTER (Playground)
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 16: Ordenación de Strings ===");
        System.out.println();

        // Descomenta y prueba:
        // String[] nombres = {"Carlos", "Ana", "Beatriz", "david", "Eva"};
        // sortInsertionAlfabetico(nombres);
        // for (String n : nombres) System.out.print(n + " ");
        // System.out.println();
        //
        // String[] palabras = {"sol", "luna", "día", "mar", "estrella"};
        // sortPorLongitud(palabras);
        // for (String p : palabras) System.out.print(p + "(" + p.length() + ") ");

        System.out.println(">> Implementa los TODOs y descomenta las pruebas <<");
    }
}
