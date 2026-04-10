package com.masterclass.arrays.nivel02;

/**
 * EJERCICIO 08 — Crecimiento Geométrico
 * =======================================
 * Nivel de Referencia Teórica: teoria/02_Redimensionado_Y_Copia.md
 *
 * Implementarás un contenedor de enteros que crece automáticamente
 * cuando se llena, usando la estrategia de duplicación de capacidad
 * (crecimiento geométrico ×2). Es la misma estrategia que usa
 * internamente java.util.ArrayList, pero construida desde cero.
 */
public class Ej08_CrecimientoGeometrico {

    // TODO 1: Declarar los campos internos de la estructura
    //   Especificación técnica:
    //   - Un campo 'int[] datos' que contendrá los elementos.
    //   - Un campo 'int size' que rastreará el tamaño lógico (elementos reales insertados).
    //   - Una constante 'int CAPACIDAD_INICIAL' con valor 4 (capacidad mínima por defecto).
    //   - Todos los campos deben ser privados.

    // TODO 2: Implementar constructor Ej08_CrecimientoGeometrico()
    //   Especificación técnica:
    //   - Inicializar 'datos' como un new int[CAPACIDAD_INICIAL].
    //   - Inicializar 'size' a 0.

    // TODO 3: Implementar add(int valor)
    //   Especificación técnica:
    //   - Si size == datos.length (array lleno), invocar el método grow() primero.
    //   - Asignar datos[size] = valor.
    //   - Incrementar size.
    //   - No devolver nada (void).
    public void add(int valor) {
        // Sustituir con implementación
    }

    // TODO 4: Implementar grow()
    //   Especificación técnica:
    //   - Crear un nuevo array con el DOBLE de capacidad: nuevaCapacidad = datos.length * 2.
    //   - Copiar todos los elementos del array actual al nuevo (puedes usar System.arraycopy o bucle).
    //   - Reasignar la referencia: datos = nuevoArray.
    //   - Este método debe ser PRIVADO (solo lo invoca add() internamente).
    private void grow() {
        // Sustituir con implementación
    }

    // TODO 5: Implementar get(int index)
    //   Especificación técnica:
    //   - Devolver el valor en datos[index].
    //   - Si index < 0 o index >= size, lanzar IndexOutOfBoundsException.
    //   - Atención: validar contra 'size' (tamaño lógico), NO contra datos.length.
    public int get(int index) {
        return -1;
    }

    // TODO 6: Implementar size() y capacity()
    //   Especificación técnica:
    //   - size(): devolver el tamaño lógico (cuántos elementos se han añadido).
    //   - capacity(): devolver datos.length (capacidad física actual del array).
    public int size() {
        return -1;
    }

    public int capacity() {
        return -1;
    }

    // TODO 7: Implementar toArray()
    //   Especificación técnica:
    //   - Crear un NUEVO int[] de tamaño 'size' (solo los elementos reales).
    //   - Copiar los primeros 'size' elementos de 'datos' al nuevo array.
    //   - Devolver el nuevo array (no devolver la referencia interna 'datos').
    //   - Si size es 0, devolver un array vacío.
    public int[] toArray() {
        return null;
    }

    // ================================================================
    // ZONA DE EJECUCIÓN MASTER (Playground)
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 08: Crecimiento Geométrico ===");
        System.out.println();

        // Descomenta y prueba:
        // Ej08_CrecimientoGeometrico contenedor = new Ej08_CrecimientoGeometrico();
        // System.out.println("Capacidad inicial: " + contenedor.capacity());
        //
        // for (int i = 1; i <= 10; i++) {
        //     contenedor.add(i * 10);
        //     System.out.println("add(" + (i*10) + ") → size=" + contenedor.size()
        //                        + ", capacity=" + contenedor.capacity());
        // }
        //
        // System.out.print("Contenido: ");
        // for (int i = 0; i < contenedor.size(); i++) {
        //     System.out.print(contenedor.get(i) + " ");
        // }

        System.out.println();
        System.out.println(">> Implementa los TODOs y descomenta las pruebas <<");
    }
}
