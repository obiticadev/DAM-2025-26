package modulo3_estructuras_avanzadas;

/**
 * ============================================================================
 * EJERCICIO 39: Min-Heap Manual
 * ============================================================================
 * 📚 Teoría: teoria/03_Estructuras_Avanzadas_Grafos.md - Sección 3.3
 *
 * CONTEXTO:
 * Un Min-Heap es un árbol binario completo donde el padre es SIEMPRE
 * menor o igual que sus hijos. El mínimo SIEMPRE está en la raíz.
 * Se implementa eficientemente sobre un array usando aritmética de índices.
 *
 * Fórmulas de navegación:
 * - Padre de i:          (i - 1) / 2
 * - Hijo izquierdo de i: 2 * i + 1
 * - Hijo derecho de i:   2 * i + 2
 *
 * Implementa:
 * - insert(int valor): insertar y hacer sift-up.
 * - extractMin(): extraer el mínimo (raíz) y hacer sift-down.
 * - peek(): ver el mínimo sin extraerlo.
 * - size(), isEmpty().
 *
 * RESTRICCIONES:
 * - Array interno con capacidad inicial 16.
 * - siftUp: comparar con el padre e intercambiar si es menor.
 * - siftDown: comparar con el hijo menor e intercambiar si es mayor.
 * - Sin java.util.PriorityQueue ni ninguna colección.
 *
 * COMPLEJIDAD OBJETIVO:
 * - insert(): O(log n) — sube como máximo la altura del heap.
 * - extractMin(): O(log n) — baja como máximo la altura.
 * - peek(): O(1).
 * ============================================================================
 */
public class Ejercicio39_MinHeap {

    private int[] datos;
    private int size;

    public Ejercicio39_MinHeap() {
        this.datos = new int[16];
        this.size = 0;
    }

    public void insert(int valor) {
        // TODO 1: Si size == datos.length → redimensionar al doble.
        //         Colocar el valor en datos[size].
        //         Incrementar size.
        //         Llamar a siftUp(size - 1) para restaurar la propiedad del heap.
    }

    public int extractMin() {
        // TODO 2: Si está vacío, lanzar IllegalStateException.
        //         Guardar datos[0] (el mínimo) en una variable.
        //         Mover el último elemento a la raíz: datos[0] = datos[size - 1].
        //         Decrementar size.
        //         Llamar a siftDown(0) para restaurar la propiedad.
        //         Retornar el mínimo guardado.
        return -1;
    }

    private void siftUp(int indice) {
        // TODO 3: Mientras indice > 0:
        //           Calcular padre = (indice - 1) / 2.
        //           Si datos[indice] < datos[padre] → swap y subir (indice = padre).
        //           Si no → break (propiedad restaurada).
    }

    private void siftDown(int indice) {
        // TODO 4: Mientras el hijo izquierdo exista (2*indice+1 < size):
        //           Calcular hijoIzq = 2*indice + 1, hijoDer = 2*indice + 2.
        //           Encontrar el MENOR de los dos hijos.
        //           Si datos[indice] > datos[hijoMenor] → swap y bajar.
        //           Si no → break.
    }

    public int peek() {
        if (size == 0) throw new IllegalStateException("Heap vacío");
        return datos[0];
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    private void swap(int a, int b) {
        int temp = datos[a];
        datos[a] = datos[b];
        datos[b] = temp;
    }

    @Override
    public String toString() {
        // TODO 5: Representación del array interno (solo los primeros 'size' elementos):
        //         "MinHeap[1, 3, 5, 7, 4] (size=5)"
        return "MinHeap[]";
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 39: Min-Heap Manual ===\n");

        Ejercicio39_MinHeap heap = new Ejercicio39_MinHeap();

        int[] valores = {15, 10, 20, 5, 8, 25, 3, 12};
        for (int v : valores) {
            heap.insert(v);
            System.out.printf("  insert(%d) → peek()=%d, %s%n", v, heap.peek(), heap);
        }

        System.out.println("\nExtrayendo en orden (debería ser ascendente):");
        while (!heap.isEmpty()) {
            System.out.println("  extractMin() = " + heap.extractMin());
        }
        // Esperado: 3, 5, 8, 10, 12, 15, 20, 25

        System.out.println("\n=== FIN EJERCICIO 39 ===");
    }
}
