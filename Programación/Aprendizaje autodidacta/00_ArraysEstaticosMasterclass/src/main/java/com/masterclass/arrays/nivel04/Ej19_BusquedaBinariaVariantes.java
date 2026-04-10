package com.masterclass.arrays.nivel04;

/**
 * EJERCICIO 19 — Búsqueda Binaria: Variantes
 * =============================================
 * Nivel de Referencia Teórica: teoria/04_Busqueda_En_Arrays.md
 *
 * Implementarás las variantes avanzadas de búsqueda binaria para arrays
 * con duplicados: primera/última ocurrencia, lower bound y upper bound.
 * Estas son las variantes que se usan en entrevistas técnicas.
 */
public class Ej19_BusquedaBinariaVariantes {

    // TODO 1: Implementar primeraOcurrencia(int[] array, int target)
    //   Especificación técnica:
    //   - El array está ordenado y puede contener DUPLICADOS.
    //   - Devolver el índice de la PRIMERA ocurrencia de 'target'.
    //   - Cuando array[mid] == target, NO retornar inmediatamente. En su lugar:
    //     guardar mid como candidato y seguir buscando en la mitad IZQUIERDA (high = mid - 1).
    //   - Si target no existe, devolver -1.
    //   - Si array es null, lanzar IllegalArgumentException.
    public static int primeraOcurrencia(int[] array, int target) {
        return -1;
    }

    // TODO 2: Implementar ultimaOcurrencia(int[] array, int target)
    //   Especificación técnica:
    //   - Devolver el índice de la ÚLTIMA ocurrencia de 'target'.
    //   - Cuando array[mid] == target, guardar mid como candidato y seguir
    //     buscando en la mitad DERECHA (low = mid + 1).
    //   - Si target no existe, devolver -1.
    public static int ultimaOcurrencia(int[] array, int target) {
        return -1;
    }

    // TODO 3: Implementar contarOcurrencias(int[] array, int target)
    //   Especificación técnica:
    //   - Contar cuántas veces aparece 'target' en el array ordenado.
    //   - Usar primeraOcurrencia y ultimaOcurrencia: si ambos devuelven >= 0,
    //     la cantidad es ultimaOcurrencia - primeraOcurrencia + 1.
    //   - Si target no existe, devolver 0.
    //   - Complejidad: O(log n), NO O(n).
    public static int contarOcurrencias(int[] array, int target) {
        return 0;
    }

    // TODO 4: Implementar lowerBound(int[] array, int target)
    //   Especificación técnica:
    //   - Devolver el índice del PRIMER elemento que es >= target.
    //   - Si todos los elementos son menores que target, devolver array.length.
    //   - Si el primer elemento ya es >= target, devolver 0.
    //   - Funciona incluso si target no existe en el array.
    //   - Ejemplo: array={1,3,3,5,7}, target=3 → devuelve 1 (primer 3).
    //   - Ejemplo: array={1,3,3,5,7}, target=4 → devuelve 3 (primer >=4 es 5).
    public static int lowerBound(int[] array, int target) {
        return -1;
    }

    // TODO 5: Implementar upperBound(int[] array, int target)
    //   Especificación técnica:
    //   - Devolver el índice del PRIMER elemento que es ESTRICTAMENTE > target.
    //   - Si todos los elementos son <= target, devolver array.length.
    //   - Ejemplo: array={1,3,3,5,7}, target=3 → devuelve 3 (primer >3 es 5).
    //   - Ejemplo: array={1,3,3,5,7}, target=5 → devuelve 4 (primer >5 es 7).
    public static int upperBound(int[] array, int target) {
        return -1;
    }

    // TODO 6: Implementar rangoDeValor(int[] array, int target)
    //   Especificación técnica:
    //   - Devolver un int[2] con: [0]=primera ocurrencia, [1]=última ocurrencia.
    //   - Si target no existe, devolver {-1, -1}.
    //   - Combinar primeraOcurrencia y ultimaOcurrencia.
    //   - Complejidad total: O(log n) (dos búsquedas binarias).
    public static int[] rangoDeValor(int[] array, int target) {
        return null;
    }

    // TODO 7: Implementar buscarMasCercano(int[] array, int target)
    //   Especificación técnica:
    //   - Devolver el VALOR del elemento más cercano a 'target' en el array ordenado.
    //   - Si target existe, devolverlo directamente.
    //   - Si no existe, comparar los dos candidatos adyacentes al punto de inserción
    //     y devolver el que tenga menor diferencia absoluta con target.
    //   - Si hay empate, devolver el menor de los dos.
    //   - Si array es null o vacío, lanzar IllegalArgumentException.
    public static int buscarMasCercano(int[] array, int target) {
        return -1;
    }

    // ================================================================
    // ZONA DE EJECUCIÓN MASTER (Playground)
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 19: Búsqueda Binaria Variantes ===");
        System.out.println();

        // Descomenta y prueba:
        // int[] datos = {1, 3, 3, 3, 3, 5, 7, 9};
        // System.out.println("Primera ocurrencia de 3: " + primeraOcurrencia(datos, 3));
        // System.out.println("Última ocurrencia de 3: " + ultimaOcurrencia(datos, 3));
        // System.out.println("Contar 3s: " + contarOcurrencias(datos, 3));
        // System.out.println("Lower bound de 4: " + lowerBound(datos, 4));
        // System.out.println("Upper bound de 3: " + upperBound(datos, 3));
        // System.out.println("Más cercano a 6: " + buscarMasCercano(datos, 6));

        System.out.println(">> Implementa los TODOs y descomenta las pruebas <<");
    }
}
