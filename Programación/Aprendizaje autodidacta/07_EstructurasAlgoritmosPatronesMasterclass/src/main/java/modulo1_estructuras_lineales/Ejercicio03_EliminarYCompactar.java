package modulo1_estructuras_lineales;

/**
 * ============================================================================
 * EJERCICIO 03: Eliminar y Compactar Array
 * ============================================================================
 * 📚 Teoría: teoria/01_Fundamentos_Estructuras_Lineales.md - Sección 1.3
 *
 * CONTEXTO:
 * Eliminar un elemento de un array no es tan sencillo como poner un null.
 * Hay que desplazar todos los elementos posteriores una posición a la
 * izquierda para "cerrar" el hueco, manteniendo la integridad del array.
 *
 * Implementa un array dinámico completo con operaciones de eliminación:
 * - removeAt(int indice): elimina el elemento en la posición dada.
 * - removeFirst(): elimina el primer elemento.
 * - removeLast(): elimina el último elemento.
 * - contains(int elemento): busca si existe un valor.
 * - indexOf(int elemento): retorna el índice de la primera ocurrencia.
 *
 * RESTRICCIONES:
 * - Al eliminar, desplazar hacia la izquierda con bucle ascendente.
 * - Limpiar la última posición lógica (datos[size] = 0) tras compactar.
 * - Considerar reducir capacidad a la mitad si size < capacity/4 (shrink).
 *
 * COMPLEJIDAD OBJETIVO:
 * - removeAt():  O(n)
 * - removeLast(): O(1)
 * - contains():  O(n)
 * - indexOf():   O(n)
 * ============================================================================
 */
public class Ejercicio03_EliminarYCompactar {

    private int[] datos;
    private int size;

    public Ejercicio03_EliminarYCompactar() {
        this.datos = new int[4];
        this.size = 0;
    }

    public void add(int elemento) {
        if (size == datos.length) {
            redimensionar(datos.length * 2);
        }
        datos[size++] = elemento;
    }

    public int removeAt(int indice) {
        // TODO 1: Validar que el índice esté en rango [0, size). Lanzar excepción si no.
        //         Guardar el valor a eliminar en una variable temporal.
        //         Desplazar todos los elementos desde 'indice+1' hasta 'size-1'
        //         una posición a la izquierda (bucle ascendente: i = indice; i < size-1; i++).
        //         Decrementar size y limpiar datos[size] = 0.
        //         Retornar el valor eliminado.
        return -1;
    }

    public int removeFirst() {
        // TODO 2: Delegar en removeAt(0). Pero antes, verificar que el array
        //         no esté vacío. Si lo está, lanzar una IllegalStateException
        //         con el mensaje "El array está vacío".
        return -1;
    }

    public int removeLast() {
        // TODO 3: Delegar en removeAt(size - 1). Aplicar la misma validación
        //         de array vacío que en removeFirst().
        return -1;
    }

    public boolean contains(int elemento) {
        // TODO 4: Recorrer el array desde 0 hasta size y retornar true
        //         si encuentras una coincidencia. Retornar false si no.
        //         Reutilizar indexOf() para simplificar.
        return false;
    }

    public int indexOf(int elemento) {
        // TODO 5: Recorrer el array desde 0 hasta size buscando el elemento.
        //         Retornar el índice de la primera ocurrencia encontrada.
        //         Si no existe, retornar -1.
        return -1;
    }

    public int get(int indice) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("Índice " + indice + " fuera de rango. Size=" + size);
        }
        return datos[indice];
    }

    public int size() { return size; }

    private void redimensionar(int nuevaCapacidad) {
        int[] nuevo = new int[nuevaCapacidad];
        for (int i = 0; i < size; i++) {
            nuevo[i] = datos[i];
        }
        datos = nuevo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(datos[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 03: Eliminar y Compactar ===\n");

        Ejercicio03_EliminarYCompactar arr = new Ejercicio03_EliminarYCompactar();
        for (int i = 1; i <= 8; i++) arr.add(i * 10);

        System.out.println("Array original: " + arr);            // [10, 20, 30, 40, 50, 60, 70, 80]

        int eliminado = arr.removeAt(3);
        System.out.println("removeAt(3) → " + eliminado);        // 40
        System.out.println("Después:     " + arr);               // [10, 20, 30, 50, 60, 70, 80]

        arr.removeFirst();
        System.out.println("removeFirst: " + arr);               // [20, 30, 50, 60, 70, 80]

        arr.removeLast();
        System.out.println("removeLast:  " + arr);               // [20, 30, 50, 60, 70]

        System.out.println("contains(50): " + arr.contains(50)); // true
        System.out.println("contains(99): " + arr.contains(99)); // false
        System.out.println("indexOf(60):  " + arr.indexOf(60));  // 3

        System.out.println("\n=== FIN EJERCICIO 03 ===");
    }
}
