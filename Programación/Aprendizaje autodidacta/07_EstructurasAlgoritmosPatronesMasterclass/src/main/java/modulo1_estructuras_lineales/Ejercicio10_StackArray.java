package modulo1_estructuras_lineales;

/**
 * ============================================================================
 * EJERCICIO 10: Stack Manual con Array
 * ============================================================================
 * 📚 Teoría: teoria/01_Fundamentos_Estructuras_Lineales.md - Sección 1.5
 *
 * CONTEXTO:
 * Un Stack (Pila) es una estructura LIFO (Last In, First Out). Implementarlo
 * sobre un array es la forma más directa y eficiente en memoria. El puntero
 * 'top' indica la posición del último elemento insertado.
 *
 * Tu MiStack debe soportar:
 * - push(int valor): apilar un elemento en la cima.
 * - pop(): retirar y retornar el elemento de la cima.
 * - peek(): ver el elemento de la cima sin retirarlo.
 * - isEmpty(): ¿está vacía?
 * - size(): número de elementos.
 * - toString(): representación visual.
 *
 * RESTRICCIONES:
 * - Basado en un array interno con capacidad inicial de 8.
 * - Redimensionar al doble cuando se llene.
 * - 'top' se inicializa en -1 (stack vacío).
 * - pop() y peek() deben lanzar EmptyStackException (o IllegalStateException)
 *   si el stack está vacío.
 * - Sin usar java.util.Stack ni Deque ni nada similar.
 *
 * COMPLEJIDAD OBJETIVO:
 * - push(): O(1) amortizado
 * - pop():  O(1)
 * - peek(): O(1)
 * ============================================================================
 */
public class Ejercicio10_StackArray {

    private int[] datos;
    private int top;

    public Ejercicio10_StackArray() {
        // TODO 1: Inicializar el array 'datos' con capacidad 8.
        //         Inicializar 'top' a -1 (indica: no hay elementos).
    }

    public void push(int valor) {
        // TODO 2: Comprobar si el stack está lleno (top == datos.length - 1).
        //         Si lo está, redimensionar al doble.
        //         Incrementar top y colocar el valor en datos[top].
    }

    public int pop() {
        // TODO 3: Si el stack está vacío (top == -1), lanzar
        //         IllegalStateException("Stack vacío: no se puede hacer pop()").
        //         Guardar datos[top] en una variable, decrementar top,
        //         retornar el valor guardado.
        return -1;
    }

    public int peek() {
        // TODO 4: Si el stack está vacío, lanzar IllegalStateException.
        //         Retornar datos[top] SIN modificar top.
        return -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    private void redimensionar() {
        int[] nuevo = new int[datos.length * 2];
        for (int i = 0; i <= top; i++) {
            nuevo[i] = datos[i];
        }
        datos = nuevo;
    }

    @Override
    public String toString() {
        // TODO 5: Construir una representación visual del stack de arriba a abajo:
        //         "Stack[TOP → 30, 20, 10 ← BOTTOM]"
        //         Recorrer desde top hasta 0 (descendente).
        return "Stack[]";
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 10: Stack Manual con Array ===\n");

        Ejercicio10_StackArray stack = new Ejercicio10_StackArray();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Después de push(10,20,30): " + stack);

        System.out.println("peek() = " + stack.peek());       // 30
        System.out.println("pop()  = " + stack.pop());         // 30
        System.out.println("pop()  = " + stack.pop());         // 20
        System.out.println("Después de 2 pops: " + stack);     // Solo queda 10

        System.out.println("size()    = " + stack.size());     // 1
        System.out.println("isEmpty() = " + stack.isEmpty());  // false

        stack.pop(); // Vaciar
        System.out.println("isEmpty() = " + stack.isEmpty());  // true

        try {
            stack.pop();
        } catch (IllegalStateException e) {
            System.out.println("✅ Excepción: " + e.getMessage());
        }

        // Probar redimensionamiento
        for (int i = 1; i <= 20; i++) {
            stack.push(i);
        }
        System.out.println("\n20 elementos apilados: " + stack);
        System.out.println("size() = " + stack.size());

        System.out.println("\n=== FIN EJERCICIO 10 ===");
    }
}
