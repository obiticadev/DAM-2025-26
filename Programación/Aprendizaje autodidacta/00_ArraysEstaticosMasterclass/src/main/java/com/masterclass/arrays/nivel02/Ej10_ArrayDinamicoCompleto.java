package com.masterclass.arrays.nivel02;

/**
 * EJERCICIO 10 — Array Dinámico Completo (MiArrayList)
 * ====================================================
 * Nivel de Referencia Teórica: teoria/02_Redimensionado_Y_Copia.md
 *
 * Este es el ejercicio culminante del Nivel 02. Construirás desde cero
 * un contenedor dinámico completo equivalente a un ArrayList<Integer>,
 * sin usar absolutamente nada de java.util. Integra auto-grow,
 * auto-shrink, y todas las operaciones fundamentales.
 */
public class Ej10_ArrayDinamicoCompleto {

    // TODO 1: Declarar campos y constantes internas
    //   Especificación técnica:
    //   - int[] datos: array interno de almacenamiento.
    //   - int size: cantidad de elementos lógicos (inicialmente 0).
    //   - static final int CAPACIDAD_MINIMA = 4: nunca reducir por debajo de este valor.
    //   - Todos los campos deben ser privados.

    // TODO 2: Implementar constructores
    //   Especificación técnica:
    //   - Constructor sin argumentos: inicializar datos con CAPACIDAD_MINIMA, size = 0.
    //   - Constructor con capacidad inicial: Ej10_ArrayDinamicoCompleto(int capacidadInicial).
    //     Si capacidadInicial < CAPACIDAD_MINIMA, usar CAPACIDAD_MINIMA.
    //     Si capacidadInicial < 0, lanzar IllegalArgumentException.
    public Ej10_ArrayDinamicoCompleto() {
        // Sustituir con implementación
    }

    public Ej10_ArrayDinamicoCompleto(int capacidadInicial) {
        // Sustituir con implementación
    }

    // TODO 3: Implementar add(int valor) y add(int index, int valor)
    //   Especificación técnica:
    //   - add(int valor): añadir al final. Grow si size == capacity.
    //   - add(int index, int valor): insertar en posición, desplazando hacia la derecha.
    //     Validar 0 <= index <= size. Grow si necesario. Lanzar IndexOutOfBoundsException si inválido.
    public void add(int valor) {
        // Sustituir con implementación
    }

    public void add(int index, int valor) {
        // Sustituir con implementación
    }

    // TODO 4: Implementar get(int index) y set(int index, int valor)
    //   Especificación técnica:
    //   - get(int index): devolver datos[index]. Validar 0 <= index < size.
    //   - set(int index, int valor): reemplazar el valor en la posición dada.
    //     Devolver el valor anterior que había en esa posición.
    //     Validar 0 <= index < size.
    public int get(int index) {
        return -1;
    }

    public int set(int index, int valor) {
        return -1;
    }

    // TODO 5: Implementar remove(int index) con auto-shrink
    //   Especificación técnica:
    //   - Guardar el valor en datos[index] para devolverlo.
    //   - Desplazar elementos a la izquierda.
    //   - Decrementar size, limpiar última posición.
    //   - Evaluar shrink: si size <= capacity/4 Y capacity > CAPACIDAD_MINIMA → shrink a capacity/2
    //     (nunca por debajo de CAPACIDAD_MINIMA).
    //   - Devolver el valor eliminado.
    //   - Validar index.
    public int remove(int index) {
        return -1;
    }

    // TODO 6: Implementar contains(int valor), indexOf(int valor), size(), isEmpty()
    //   Especificación técnica:
    //   - contains(int valor): recorrer datos[0..size-1] buscando 'valor'. Devolver true/false.
    //   - indexOf(int valor): devolver el primer índice donde aparece 'valor', o -1 si no existe.
    //   - size(): devolver el tamaño lógico.
    //   - isEmpty(): devolver true si size == 0.
    public boolean contains(int valor) {
        return false;
    }

    public int indexOf(int valor) {
        return -1;
    }

    public int size() {
        return -1;
    }

    public boolean isEmpty() {
        return true;
    }

    // TODO 7: Implementar toArray() y toString()
    //   Especificación técnica:
    //   - toArray(): devolver un NUEVO int[size] con copia de los elementos lógicos.
    //   - toString(): representación textual en formato "[10, 20, 30]".
    //     Si está vacío: "[]". Usar StringBuilder para eficiencia.
    public int[] toArray() {
        return null;
    }

    @Override
    public String toString() {
        return "[]";
    }

    // TODO 8: Implementar capacity() (para tests)
    //   Especificación técnica:
    //   - Devolver datos.length (capacidad física actual).
    public int capacity() {
        return -1;
    }

    // ================================================================
    // ZONA DE EJECUCIÓN MASTER (Playground)
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 10: Array Dinámico Completo (MiArrayList) ===");
        System.out.println();

        // Descomenta y prueba:
        // Ej10_ArrayDinamicoCompleto lista = new Ej10_ArrayDinamicoCompleto();
        //
        // // Inserciones
        // for (int i = 1; i <= 12; i++) {
        //     lista.add(i * 10);
        //     System.out.println("add(" + (i*10) + ") → " + lista + " (size=" + lista.size() + ", cap=" + lista.capacity() + ")");
        // }
        //
        // // Acceso
        // System.out.println("get(0) = " + lista.get(0));
        // System.out.println("contains(50) = " + lista.contains(50));
        // System.out.println("indexOf(80) = " + lista.indexOf(80));
        //
        // // Eliminaciones (observar shrink)
        // while (lista.size() > 2) {
        //     int removed = lista.remove(0);
        //     System.out.println("remove → " + removed + " | " + lista + " (cap=" + lista.capacity() + ")");
        // }

        System.out.println();
        System.out.println(">> Implementa los TODOs y descomenta las pruebas <<");
    }
}
