package modulo1_estructuras_lineales;

/**
 * ============================================================================
 * EJERCICIO 06: Singly LinkedList desde Cero
 * ============================================================================
 * 📚 Teoría: teoria/01_Fundamentos_Estructuras_Lineales.md - Sección 1.4
 *
 * CONTEXTO:
 * Ahora que comprendes los nodos (Ejercicio 05), es hora de construir una
 * LinkedList simple (singly linked) completa con todas las operaciones
 * estándar. Esta estructura no usa arrays internos; cada elemento es un
 * nodo en el heap enlazado al siguiente.
 *
 * Tu MiLinkedList debe soportar:
 * - addFirst(int valor): insertar al inicio O(1).
 * - addLast(int valor): insertar al final O(n) sin tail, O(1) con tail.
 * - removeFirst(): eliminar el primer nodo.
 * - get(int indice): acceso posicional recorriendo la cadena.
 * - size(): número de elementos.
 * - toString(): representación visual.
 *
 * RESTRICCIONES:
 * - Clase interna estática 'Nodo' con campos 'valor' y 'siguiente'.
 * - Mantener un campo 'head' (referencia al primer nodo).
 * - Mantener un campo 'tail' (referencia al último nodo) para addLast O(1).
 * - Mantener un campo 'size' (contador de elementos).
 * - Sin usar java.util (ni LinkedList ni nada).
 *
 * COMPLEJIDAD OBJETIVO:
 * - addFirst():    O(1)
 * - addLast():     O(1) con tail
 * - removeFirst(): O(1)
 * - get(i):        O(n)
 * ============================================================================
 */
public class Ejercicio06_LinkedListSimple {

    private static class Nodo {
        int valor;
        Nodo siguiente;

        Nodo(int valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    private Nodo head;
    private Nodo tail;
    private int size;

    public Ejercicio06_LinkedListSimple() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addFirst(int valor) {
        // TODO 1: Crear un nuevo Nodo con el valor recibido.
        //         Si la lista está vacía (head == null), este nodo será head Y tail.
        //         Si no está vacía, el nuevo nodo apunta al actual head, y luego
        //         head se reasigna al nuevo nodo.
        //         Incrementar size.
    }

    public void addLast(int valor) {
        // TODO 2: Crear un nuevo Nodo. Si la lista está vacía, delegar en addFirst().
        //         Si no está vacía, tail.siguiente apunta al nuevo nodo, y luego
        //         tail se reasigna al nuevo nodo.
        //         Incrementar size.
    }

    public int removeFirst() {
        // TODO 3: Validar que la lista no esté vacía (lanzar IllegalStateException).
        //         Guardar head.valor en una variable temporal.
        //         Mover head al siguiente nodo (head = head.siguiente).
        //         Si head queda null, tail también debe ser null (lista quedó vacía).
        //         Decrementar size. Retornar el valor eliminado.
        return -1;
    }

    public int get(int indice) {
        // TODO 4: Validar que el índice esté en rango [0, size).
        //         Recorrer la cadena desde head avanzando 'indice' veces.
        //         Retornar el valor del nodo al que llegaste.
        return -1;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        // TODO 5: Recorrer desde head hasta null construyendo un String con formato:
        //         "10 → 20 → 30 → null"
        //         Usar StringBuilder para eficiencia.
        return "null";
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 06: Singly LinkedList ===\n");

        Ejercicio06_LinkedListSimple lista = new Ejercicio06_LinkedListSimple();

        lista.addLast(10);
        lista.addLast(20);
        lista.addLast(30);
        System.out.println("addLast(10,20,30): " + lista);     // 10 → 20 → 30 → null

        lista.addFirst(5);
        System.out.println("addFirst(5):       " + lista);     // 5 → 10 → 20 → 30 → null

        System.out.println("get(0) = " + lista.get(0));        // 5
        System.out.println("get(2) = " + lista.get(2));        // 20
        System.out.println("size() = " + lista.size());        // 4

        int eliminado = lista.removeFirst();
        System.out.println("removeFirst() = " + eliminado);    // 5
        System.out.println("Después: " + lista);               // 10 → 20 → 30 → null

        System.out.println("\n=== FIN EJERCICIO 06 ===");
    }
}
