package modulo1_estructuras_lineales;

/**
 * ============================================================================
 * EJERCICIO 11: Stack con LinkedList
 * ============================================================================
 * 📚 Teoría: teoria/01_Fundamentos_Estructuras_Lineales.md - Sección 1.5
 *
 * CONTEXTO:
 * En el Ejercicio 10 construiste un Stack sobre un array. Ahora implementarás
 * la misma interfaz pero usando nodos enlazados. La ventaja principal es que
 * NUNCA necesitas redimensionar: cada push() simplemente crea un nuevo nodo
 * y lo enlaza al anterior. El head de la lista es el TOP del stack.
 *
 * Tu StackLinkedList debe soportar:
 * - push(int valor): crear un nuevo nodo que apunte al top actual, nuevo top.
 * - pop(): retornar y eliminar el nodo top.
 * - peek(): ver el top sin eliminarlo.
 * - isEmpty(), size(), toString().
 *
 * RESTRICCIONES:
 * - Clase interna 'Nodo' con campos valor y siguiente.
 * - El campo 'top' es una referencia al nodo cabeza.
 * - Sin redimensionamiento. Sin arrays. Sin java.util.
 * - push() y pop() siempre operan sobre el head (top) de la lista.
 *
 * COMPLEJIDAD OBJETIVO:
 * - push(): O(1) SIEMPRE (no amortizado, genuino O(1))
 * - pop():  O(1)
 * - peek(): O(1)
 * ============================================================================
 */
public class Ejercicio11_StackLinkedList {

    private static class Nodo {
        int valor;
        Nodo siguiente;

        Nodo(int valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    private Nodo top;
    private int size;

    public Ejercicio11_StackLinkedList() {
        this.top = null;
        this.size = 0;
    }

    public void push(int valor) {
        // TODO 1: Crear un nuevo Nodo con el valor recibido.
        //         Hacer que nuevo.siguiente apunte al 'top' actual.
        //         Reasignar 'top' al nuevo nodo.
        //         Incrementar size.
        //         Nota: esto funciona incluso si top es null (lista vacía).
    }

    public int pop() {
        // TODO 2: Validar que el stack no esté vacío (top != null).
        //         Si está vacío, lanzar IllegalStateException("Stack vacío").
        //         Guardar top.valor en una variable temporal.
        //         Mover top al siguiente nodo (top = top.siguiente).
        //         Decrementar size. Retornar el valor guardado.
        return -1;
    }

    public int peek() {
        // TODO 3: Validar que el stack no esté vacío.
        //         Retornar top.valor SIN modificar el puntero top.
        return -1;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        // TODO 4: Recorrer la lista desde top hasta null construyendo:
        //         "Stack[TOP → 30 → 20 → 10 → BOTTOM]"
        //         Usar StringBuilder recorriendo con un puntero auxiliar.
        return "Stack[]";
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 11: Stack con LinkedList ===\n");

        Ejercicio11_StackLinkedList stack = new Ejercicio11_StackLinkedList();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("push(10,20,30): " + stack);

        System.out.println("peek() = " + stack.peek());       // 30
        System.out.println("pop()  = " + stack.pop());         // 30
        System.out.println("pop()  = " + stack.pop());         // 20
        System.out.println("Después: " + stack);               // Solo 10
        System.out.println("size() = " + stack.size());        // 1

        stack.pop();
        System.out.println("isEmpty() = " + stack.isEmpty());  // true

        // Probar excepción en vacío
        try {
            stack.pop();
        } catch (IllegalStateException e) {
            System.out.println("✅ " + e.getMessage());
        }

        // Probar con muchos elementos (sin preocupación de capacidad)
        for (int i = 1; i <= 1000; i++) stack.push(i);
        System.out.println("\n1000 elementos: size=" + stack.size());
        System.out.println("peek() = " + stack.peek()); // 1000

        System.out.println("\n=== FIN EJERCICIO 11 ===");
    }
}
