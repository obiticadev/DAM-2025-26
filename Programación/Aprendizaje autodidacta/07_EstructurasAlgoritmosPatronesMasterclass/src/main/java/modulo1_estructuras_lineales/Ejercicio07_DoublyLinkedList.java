package modulo1_estructuras_lineales;

/**
 * ============================================================================
 * EJERCICIO 07: Doubly LinkedList Completa
 * ============================================================================
 * 📚 Teoría: teoria/01_Fundamentos_Estructuras_Lineales.md - Sección 1.4
 *
 * CONTEXTO:
 * Una lista doblemente enlazada extiende la singly linked list añadiendo
 * un puntero 'anterior' (prev) a cada nodo. Esto permite recorridos
 * bidireccionales y eliminaciones más eficientes (sin necesidad de buscar
 * el nodo previo).
 *
 * Implementa una DoublyLinkedList con las operaciones:
 * - addFirst(int valor): insertar al inicio.
 * - addLast(int valor): insertar al final.
 * - removeFirst(): eliminar el primer nodo.
 * - removeLast(): eliminar el último nodo.
 * - printForward(): imprimir de head a tail.
 * - printBackward(): imprimir de tail a head.
 *
 * RESTRICCIONES:
 * - Clase interna 'NodoDoble' con campos: valor, siguiente, anterior.
 * - Mantener 'head' y 'tail'.
 * - En cada inserción/eliminación, actualizar AMBOS punteros (next y prev).
 * - Casos borde: lista vacía, lista con un solo elemento.
 *
 * COMPLEJIDAD OBJETIVO:
 * - addFirst(), addLast():       O(1)
 * - removeFirst(), removeLast(): O(1)
 * ============================================================================
 */
public class Ejercicio07_DoublyLinkedList {

    private static class NodoDoble {
        int valor;
        NodoDoble siguiente;
        NodoDoble anterior;

        NodoDoble(int valor) {
            this.valor = valor;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    private NodoDoble head;
    private NodoDoble tail;
    private int size;

    public Ejercicio07_DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addFirst(int valor) {
        // TODO 1: Crear un nuevo NodoDoble. Si la lista está vacía, head y tail
        //         apuntan al nuevo nodo. Si no, enlazar bidireccionalmente:
        //         nuevo.siguiente = head actual, head.anterior = nuevo.
        //         Luego head = nuevo. Incrementar size.
    }

    public void addLast(int valor) {
        // TODO 2: Crear un nuevo NodoDoble. Si la lista está vacía, delegar en addFirst().
        //         Si no, enlazar: tail.siguiente = nuevo, nuevo.anterior = tail.
        //         Luego tail = nuevo. Incrementar size.
    }

    public int removeFirst() {
        // TODO 3: Validar que no esté vacía. Guardar head.valor.
        //         Si solo hay un nodo (head == tail), poner ambos a null.
        //         Si hay más: head = head.siguiente, nuevo head.anterior = null.
        //         Decrementar size. Retornar el valor.
        return -1;
    }

    public int removeLast() {
        // TODO 4: Validar que no esté vacía. Guardar tail.valor.
        //         Si solo hay un nodo (head == tail), poner ambos a null.
        //         Si hay más: tail = tail.anterior, nuevo tail.siguiente = null.
        //         Decrementar size. Retornar el valor.
        return -1;
    }

    public void printForward() {
        // TODO 5: Recorrer desde head hasta null imprimiendo cada valor
        //         en formato: "HEAD ⇄ 10 ⇄ 20 ⇄ 30 ⇄ TAIL"
        //         Usar un bucle while avanzando con actual = actual.siguiente.
    }

    public void printBackward() {
        // TODO 6 (BONUS): Recorrer desde tail hasta null imprimiendo cada valor
        //         en formato: "TAIL ⇄ 30 ⇄ 20 ⇄ 10 ⇄ HEAD"
        //         Usar un bucle while retrocediendo con actual = actual.anterior.
    }

    public int size() { return size; }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 07: Doubly LinkedList ===\n");

        Ejercicio07_DoublyLinkedList lista = new Ejercicio07_DoublyLinkedList();

        lista.addLast(10);
        lista.addLast(20);
        lista.addLast(30);
        lista.addFirst(5);

        System.out.print("Forward:  "); lista.printForward();   // HEAD ⇄ 5 ⇄ 10 ⇄ 20 ⇄ 30 ⇄ TAIL
        System.out.print("Backward: "); lista.printBackward();  // TAIL ⇄ 30 ⇄ 20 ⇄ 10 ⇄ 5 ⇄ HEAD

        System.out.println("size() = " + lista.size());        // 4

        System.out.println("removeFirst() = " + lista.removeFirst()); // 5
        System.out.println("removeLast()  = " + lista.removeLast());  // 30

        System.out.print("Forward:  "); lista.printForward();   // HEAD ⇄ 10 ⇄ 20 ⇄ TAIL
        System.out.println("size() = " + lista.size());        // 2

        System.out.println("\n=== FIN EJERCICIO 07 ===");
    }
}
