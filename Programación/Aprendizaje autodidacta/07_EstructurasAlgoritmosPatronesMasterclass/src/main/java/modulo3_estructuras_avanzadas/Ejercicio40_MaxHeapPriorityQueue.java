package modulo3_estructuras_avanzadas;

/**
 * ============================================================================
 * EJERCICIO 40: Max-Heap y Priority Queue
 * ============================================================================
 * 📚 Teoría: teoria/03_Estructuras_Avanzadas_Grafos.md - Sección 3.3
 *
 * CONTEXTO:
 * Un Max-Heap es el opuesto del Min-Heap: el padre siempre es MAYOR
 * o igual que sus hijos. El máximo está en la raíz. Es la base de las
 * Priority Queues (colas de prioridad).
 *
 * Implementa un MaxHeap que funcione como Priority Queue:
 * - insert(int prioridad, String tarea): insertar con prioridad.
 * - extractMax(): extraer la tarea con mayor prioridad.
 * - peek(): ver la tarea con mayor prioridad.
 * - changePriority(int indice, int nuevaPrioridad): cambiar prioridad
 *   y restaurar la propiedad del heap.
 *
 * RESTRICCIONES:
 * - Almacenar pares (prioridad, tarea) como clase interna.
 * - siftUp y siftDown basados en la prioridad (no la tarea).
 * - Si dos tareas tienen la misma prioridad, el orden es arbitrario.
 * - Sin java.util.PriorityQueue.
 *
 * COMPLEJIDAD: insert/extractMax: O(log n), peek: O(1).
 * ============================================================================
 */
public class Ejercicio40_MaxHeapPriorityQueue {

    static class Entrada {
        int prioridad;
        String tarea;
        Entrada(int prioridad, String tarea) {
            this.prioridad = prioridad;
            this.tarea = tarea;
        }
        @Override
        public String toString() { return "[P" + prioridad + ": " + tarea + "]"; }
    }

    private Entrada[] datos;
    private int size;

    public Ejercicio40_MaxHeapPriorityQueue() {
        this.datos = new Entrada[16];
        this.size = 0;
    }

    public void insert(int prioridad, String tarea) {
        // TODO 1: Crear nueva Entrada(prioridad, tarea).
        //         Redimensionar si es necesario.
        //         Colocar en datos[size], incrementar size.
        //         siftUp(size - 1) comparando PRIORIDADES (mayor sube).
    }

    public Entrada extractMax() {
        // TODO 2: Validar no vacío. Guardar datos[0] (mayor prioridad).
        //         Mover datos[size-1] a datos[0]. Decrementar size.
        //         siftDown(0) (el mayor de los hijos sube).
        //         Retornar la entrada guardada.
        return null;
    }

    private void siftUp(int indice) {
        // TODO 3: Mientras indice > 0:
        //           padre = (indice - 1) / 2.
        //           Si datos[indice].prioridad > datos[padre].prioridad → swap.
        //           (En Max-Heap, el MAYOR sube).
        //           indice = padre.
    }

    private void siftDown(int indice) {
        // TODO 4: Mientras hijo izquierdo exista:
        //           Encontrar el hijo con MAYOR prioridad.
        //           Si datos[indice].prioridad < datos[hijoMayor].prioridad → swap.
        //           indice = hijoMayor.
    }

    public Entrada peek() {
        if (size == 0) throw new IllegalStateException("PQ vacía");
        return datos[0];
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    private void swap(int a, int b) {
        Entrada temp = datos[a];
        datos[a] = datos[b];
        datos[b] = temp;
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 40: Max-Heap Priority Queue ===\n");

        Ejercicio40_MaxHeapPriorityQueue pq = new Ejercicio40_MaxHeapPriorityQueue();

        pq.insert(3, "Enviar email");
        pq.insert(10, "Apagar incendio en produccion");
        pq.insert(1, "Actualizar README");
        pq.insert(7, "Fix bug critico");
        pq.insert(5, "Code review");
        pq.insert(9, "Deploy hotfix");

        System.out.println("Tareas por prioridad (mayor primero):");
        while (!pq.isEmpty()) {
            Entrada e = pq.extractMax();
            System.out.println("  " + e);
        }
        // Esperado: P10, P9, P7, P5, P3, P1

        System.out.println("\n=== FIN EJERCICIO 40 ===");
    }
}
