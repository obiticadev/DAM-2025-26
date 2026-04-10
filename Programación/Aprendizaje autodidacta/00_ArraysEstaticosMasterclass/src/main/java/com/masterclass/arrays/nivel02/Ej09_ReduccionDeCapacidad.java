package com.masterclass.arrays.nivel02;

/**
 * EJERCICIO 09 — Reducción de Capacidad (Shrink)
 * ================================================
 * Nivel de Referencia Teórica: teoria/02_Redimensionado_Y_Copia.md
 *
 * Implementarás un contenedor que no solo crece sino que también
 * REDUCE su capacidad automáticamente cuando la ocupación es baja,
 * evitando desperdiciar memoria innecesariamente.
 */
public class Ej09_ReduccionDeCapacidad {

    // TODO 1: Declarar los campos internos
    //   Especificación técnica:
    //   - int[] datos: almacenamiento interno.
    //   - int size: tamaño lógico.
    //   - Constante CAPACIDAD_INICIAL = 8 (mínimo absoluto al que nunca se reduce por debajo).

    // TODO 2: Implementar constructor Ej09_ReduccionDeCapacidad()
    //   Especificación técnica:
    //   - Inicializar datos con CAPACIDAD_INICIAL.
    //   - Inicializar size a 0.

    // TODO 3: Implementar add(int valor)
    //   Especificación técnica:
    //   - Si el array está lleno (size == datos.length), duplicar la capacidad (grow).
    //   - Asignar datos[size] = valor y size++.
    public void add(int valor) {
        // Sustituir con implementación
    }

    // TODO 4: Implementar remove(int index)
    //   Especificación técnica:
    //   - Validar que index >= 0 y index < size, si no, lanzar IndexOutOfBoundsException.
    //   - Guardar el valor en datos[index] para devolverlo.
    //   - Desplazar todos los elementos desde index+1 hasta size-1 una posición a la izquierda.
    //   - Decrementar size y limpiar datos[size] = 0.
    //   - Tras la eliminación, evaluar si se debe reducir: si size <= datos.length / 4
    //     Y datos.length > CAPACIDAD_INICIAL → invocar shrink().
    //   - Devolver el valor eliminado.
    public int remove(int index) {
        return -1;
    }

    // TODO 5: Implementar shrink()
    //   Especificación técnica:
    //   - Calcular nueva capacidad = datos.length / 2.
    //   - Si nueva capacidad < CAPACIDAD_INICIAL, establecerla a CAPACIDAD_INICIAL.
    //   - Crear un nuevo array con la nueva capacidad.
    //   - Copiar los primeros 'size' elementos al nuevo array.
    //   - Reasignar datos = nuevoArray.
    //   - Este método debe ser PRIVADO.
    private void shrink() {
        // Sustituir con implementación
    }

    // TODO 6: Implementar get(int index), size() y capacity()
    //   Especificación técnica:
    //   - get(int index): devolver datos[index], validar contra size.
    //   - size(): devolver tamaño lógico.
    //   - capacity(): devolver datos.length.
    public int get(int index) {
        return -1;
    }

    public int size() {
        return -1;
    }

    public int capacity() {
        return -1;
    }

    // TODO 7: Implementar toArray()
    //   Especificación técnica:
    //   - Crear un nuevo int[size] con solo los elementos lógicos.
    //   - Devolver la copia (nunca la referencia interna).
    public int[] toArray() {
        return null;
    }

    // ================================================================
    // ZONA DE EJECUCIÓN MASTER (Playground)
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 09: Reducción de Capacidad ===");
        System.out.println();

        // Descomenta y prueba:
        // Ej09_ReduccionDeCapacidad contenedor = new Ej09_ReduccionDeCapacidad();
        // for (int i = 0; i < 20; i++) contenedor.add(i);
        // System.out.println("Tras 20 adds → size=" + contenedor.size() + " cap=" + contenedor.capacity());
        //
        // while (contenedor.size() > 2) {
        //     contenedor.remove(0);
        //     System.out.println("remove → size=" + contenedor.size() + " cap=" + contenedor.capacity());
        // }

        System.out.println(">> Implementa los TODOs y descomenta las pruebas <<");
    }
}
