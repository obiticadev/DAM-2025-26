package com.masterclass.arrays.nivel01;

/**
 * EJERCICIO 01 — Declaración e Inicialización de Arrays
 * =====================================================
 * Nivel de Referencia Teórica: teoria/01_Fundamentos_Arrays.md
 *
 * Dominarás las distintas formas de declarar, dimensionar e inicializar
 * arrays en Java, y comprenderás los valores por defecto que la JVM
 * asigna automáticamente según el tipo del array.
 */
public class Ej01_DeclaracionInicializacion {

    // TODO 1: Implementar crearArrayEnteros(int tamanio)
    //   Especificación técnica:
    //   - Instanciar y devolver un int[] con la capacidad especificada por 'tamanio'.
    //   - La JVM debe asignar los valores por defecto automáticamente (no rellenar manualmente).
    //   - Si tamanio es negativo, lanzar IllegalArgumentException con mensaje descriptivo.
    //   - Si tamanio es 0, devolver un array vacío de longitud 0 (es válido en Java).
    public static int[] crearArrayEnteros(int tamanio) {
        return null;
    }

    // TODO 2: Implementar crearArrayDoubles(int tamanio)
    //   Especificación técnica:
    //   - Misma lógica que TODO 1 pero para tipo double.
    //   - Recordar: el valor por defecto de double es 0.0, NO 0.
    //   - Validar tamanio negativo con IllegalArgumentException.
    public static double[] crearArrayDoubles(int tamanio) {
        return null;
    }

    // TODO 3: Implementar crearArrayStrings(int tamanio)
    //   Especificación técnica:
    //   - Instanciar un String[] con la capacidad indicada.
    //   - Cada posición contendrá null (NO cadena vacía ""). Esto es crítico.
    //   - Validar tamanio negativo con IllegalArgumentException.
    public static String[] crearArrayStrings(int tamanio) {
        return null;
    }

    // TODO 4: Implementar crearArrayBooleans(int tamanio)
    //   Especificación técnica:
    //   - Instanciar un boolean[] con la capacidad indicada.
    //   - Cada posición contendrá false por defecto.
    //   - Validar tamanio negativo con IllegalArgumentException.
    public static boolean[] crearArrayBooleans(int tamanio) {
        return null;
    }

    // TODO 5: Implementar crearConLiteral()
    //   Especificación técnica:
    //   - Crear un int[] usando EXCLUSIVAMENTE la sintaxis literal: {10, 20, 30, 40, 50}.
    //   - El tamaño se infiere de la cantidad de elementos proporcionados (5).
    //   - Devolver el array directamente.
    public static int[] crearConLiteral() {
        return null;
    }

    // TODO 6: Implementar verificarTodosValoresPorDefecto(int[] array)
    //   Especificación técnica:
    //   - Recorrer TODAS las posiciones del array recibido.
    //   - Verificar que cada posición contiene exactamente el valor 0.
    //   - Devolver true si TODAS las posiciones son 0; false si al menos una no lo es.
    //   - Si el array es null, lanzar IllegalArgumentException.
    //   - Si el array está vacío (length == 0), devolver true (vacuamente verdadero).
    public static boolean verificarTodosValoresPorDefecto(int[] array) {
        return false;
    }

    // TODO 7: Implementar obtenerLongitud(int[] array)
    //   Especificación técnica:
    //   - Devolver la longitud del array usando la propiedad .length (es un campo, no un método).
    //   - Si el array es null, lanzar IllegalArgumentException con mensaje "El array no puede ser null".
    //   - Recordar: .length es inmutable una vez creado el array.
    public static int obtenerLongitud(int[] array) {
        return -1;
    }

    // TODO 8: Implementar crearYRellenarSecuencial(int tamanio)
    //   Especificación técnica:
    //   - Crear un int[] del tamaño indicado.
    //   - Rellenar cada posición con su propio índice: array[i] = i.
    //   - Resultado esperado para tamanio=5: {0, 1, 2, 3, 4}.
    //   - Validar tamanio negativo con IllegalArgumentException.
    public static int[] crearYRellenarSecuencial(int tamanio) {
        return null;
    }

    // ================================================================
    // ZONA DE EJECUCIÓN MASTER (Playground)
    // Pulsa "Run" para comprobar visualmente tu implementación.
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 01: Declaración e Inicialización ===");
        System.out.println();

        // Descomenta y prueba tus métodos aquí:
        // int[] enteros = crearArrayEnteros(5);
        // System.out.println("Array de enteros creado, tamaño: " + enteros.length);
        // for (int i = 0; i < enteros.length; i++) {
        //     System.out.println("  enteros[" + i + "] = " + enteros[i]);
        // }
        //
        // int[] literal = crearConLiteral();
        // System.out.println("Array literal: " + literal.length + " elementos");
        //
        // boolean todoCeros = verificarTodosValoresPorDefecto(enteros);
        // System.out.println("¿Todos valores por defecto? " + todoCeros);

        System.out.println();
        System.out.println(">> Implementa los TODOs y descomenta las pruebas <<");
    }
}
