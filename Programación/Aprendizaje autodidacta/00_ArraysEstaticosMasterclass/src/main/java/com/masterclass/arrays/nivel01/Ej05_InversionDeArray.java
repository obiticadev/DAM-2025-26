package com.masterclass.arrays.nivel01;

/**
 * EJERCICIO 05 — Inversión de Array
 * ==================================
 * Nivel de Referencia Teórica: teoria/01_Fundamentos_Arrays.md
 *
 * Dominarás la técnica de inversión in-place con dos punteros (swap),
 * la inversión con copia, la inversión de rangos parciales, y la
 * detección de palíndromos. Estas operaciones son la base de algoritmos
 * más complejos como la rotación de arrays y la inversión de matrices.
 */
public class Ej05_InversionDeArray {

    // TODO 1: Implementar invertirInPlace(int[] array)
    //   Especificación técnica:
    //   - Invertir el array SOBRE SÍ MISMO sin crear ningún array auxiliar.
    //   - Utilizar dos punteros: uno al inicio (izq=0) y otro al final (der=length-1).
    //   - En cada iteración, intercambiar (swap) array[izq] y array[der], luego
    //     avanzar izq++ y retroceder der--.
    //   - Detenerse cuando izq >= der.
    //   - Si el array es null, lanzar IllegalArgumentException.
    //   - Si el array tiene 0 o 1 elementos, no hacer nada (ya está "invertido").
    public static void invertirInPlace(int[] array) {
        // Sustituir con implementación
    }

    // TODO 2: Implementar invertirConCopia(int[] original)
    //   Especificación técnica:
    //   - Crear un NUEVO array del mismo tamaño que 'original'.
    //   - Copiar los elementos de 'original' en orden inverso al nuevo array.
    //   - El array original NO debe ser modificado.
    //   - Devolver el nuevo array invertido.
    //   - Si original es null, lanzar IllegalArgumentException.
    public static int[] invertirConCopia(int[] original) {
        return null;
    }

    // TODO 3: Implementar invertirRango(int[] array, int desde, int hasta)
    //   Especificación técnica:
    //   - Invertir in-place SOLO el rango [desde, hasta] (ambos inclusivos).
    //   - Los elementos fuera del rango no se tocan.
    //   - Ejemplo: {1, 2, 3, 4, 5} con desde=1, hasta=3 → {1, 4, 3, 2, 5}.
    //   - Si desde < 0 o hasta >= array.length o desde > hasta, lanzar IndexOutOfBoundsException.
    //   - Si array es null, lanzar IllegalArgumentException.
    public static void invertirRango(int[] array, int desde, int hasta) {
        // Sustituir con implementación
    }

    // TODO 4: Implementar esPalindromo(int[] array)
    //   Especificación técnica:
    //   - Verificar si el array se lee igual de izquierda a derecha que de derecha a izquierda.
    //   - Usar dos punteros convergentes (izq y der) comparando en cada paso.
    //   - Devolver true si es palíndromo, false si no lo es.
    //   - Un array vacío o de un solo elemento se considera palíndromo.
    //   - Si el array es null, lanzar IllegalArgumentException.
    //   - Ejemplo: {1, 2, 3, 2, 1} → true; {1, 2, 3} → false.
    public static boolean esPalindromo(int[] array) {
        return false;
    }

    // TODO 5: Implementar invertirStrings(String[] array)
    //   Especificación técnica:
    //   - Misma técnica de inversión in-place con swap pero para String[].
    //   - Intercambia las REFERENCIAS (no es necesario clonar Strings).
    //   - Si el array es null, lanzar IllegalArgumentException.
    //   - Las posiciones con null se intercambian normalmente.
    public static void invertirStrings(String[] array) {
        // Sustituir con implementación
    }

    // TODO 6: Implementar rotarDerecha(int[] array, int posiciones)
    //   Especificación técnica:
    //   - Rotar todos los elementos del array 'posiciones' veces hacia la derecha.
    //   - Ejemplo: {1, 2, 3, 4, 5} rotado 2 → {4, 5, 1, 2, 3}.
    //   - TÉCNICA OBLIGATORIA: "triple inversión":
    //     1. Invertir todo el array.
    //     2. Invertir los primeros 'posiciones' elementos.
    //     3. Invertir los restantes.
    //   - Si posiciones es mayor que la longitud, usar módulo: posiciones % length.
    //   - Si posiciones es 0 o length es 0, no hacer nada.
    //   - Si array es null, lanzar IllegalArgumentException.
    public static void rotarDerecha(int[] array, int posiciones) {
        // Sustituir con implementación
    }

    // TODO 7: Implementar rotarIzquierda(int[] array, int posiciones)
    //   Especificación técnica:
    //   - Rotar todos los elementos del array 'posiciones' veces hacia la izquierda.
    //   - Ejemplo: {1, 2, 3, 4, 5} rotado 2 → {3, 4, 5, 1, 2}.
    //   - Observación: rotar K posiciones a la izquierda equivale a rotar (length - K) a la derecha.
    //   - Si posiciones es mayor que la longitud, usar módulo.
    //   - Si array es null, lanzar IllegalArgumentException.
    public static void rotarIzquierda(int[] array, int posiciones) {
        // Sustituir con implementación
    }

    // ================================================================
    // ZONA DE EJECUCIÓN MASTER (Playground)
    // Pulsa "Run" para comprobar visualmente tu implementación.
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 05: Inversión de Array ===");
        System.out.println();

        // Descomenta y prueba tus métodos aquí:
        // int[] datos = {1, 2, 3, 4, 5};
        // invertirInPlace(datos);
        // System.out.print("Invertido in-place: ");
        // for (int d : datos) System.out.print(d + " ");
        // System.out.println();
        //
        // int[] copia = invertirConCopia(new int[]{10, 20, 30});
        // System.out.print("Invertido con copia: ");
        // for (int c : copia) System.out.print(c + " ");
        // System.out.println();
        //
        // int[] palindromo = {1, 2, 3, 2, 1};
        // System.out.println("¿Es palíndromo? " + esPalindromo(palindromo));

        System.out.println();
        System.out.println(">> Implementa los TODOs y descomenta las pruebas <<");
    }
}
